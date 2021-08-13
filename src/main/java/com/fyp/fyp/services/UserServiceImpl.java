package com.fyp.fyp.services;

import java.util.*;

import com.fyp.fyp.dao.UserDao;
import com.fyp.fyp.entity.User;
import com.fyp.fyp.entity.UserType;
import com.fyp.fyp.model.CandidateRanking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @PersistenceContext
    EntityManager em;

    /**
     * used for User entity persistence
     */
    @Autowired
    UserDao userdao;

    /**
     * Checks if user if valid
     * @param username
     * @param password
     * @return a valid user
     */
    //makin assumption that username is actually clientId
    //checks if user is valid from their username and password
    @Override
    public boolean validateUser(String username, String password) {
        return userdao.findByUsernameAndPassword(username, password).size() > 0;
    }

    /**
     * lists all of the users
     * @return a list of users
     */
    //Displays all of the users
    @Override
    public List<User> listUsers() {
        return userdao.findAll()
                      .stream()
                      .peek(u -> u.setPassword(null))
                      .toList();
    }

    /**
     * searches for users by username
     * @param username
     * @return username by ID
     */
    //searches for users via their username
    @Override
    public Optional<User> findById(String username) {
        return userdao.findById(username);
    }

    /**
     * updates user details
     * @param user
     * @return an updated user
     */
    //allows updating user details
    @Override
    public User updateUser(User user) {
        if(user.getPassword() != null &&
                user.getPassword().length() > 0) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String pwd = encoder.encode(user.getPassword());
            user.setPassword(pwd);
        }
        userdao.save(user);
        return user;
    }

    /**
     * Adds a new user
     * @param user
     * @return a new user
     */
    //allows to add a new user.
    @Override
    public User addUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd = encoder.encode(user.getPassword());
        user.setPassword(pwd);

        userdao.save(user);
        return user;
    }

    /**
     * lists all user types
     * @return list of all user types
     */
   //gets all user types
    @Override
    public List<UserType> getAllUserTypes() {
        return Arrays.stream(UserType.values()).toList();
    }

    /**
     * lists the matched candidates
     * @return a list of matched candidates.
     */
    //Displays a list of the matchedCandidates.
    @Override
    @Transactional
    public List<CandidateRanking> listMatchedCandidates(Long jobSpecId) {
        //List<User> users = userdao.findAll();
        String query =
                """
                        SELECT jsq.job_spec_job_specid job_spec_id , cr.USERNAME, sum(qo.WEIGHTING) rank
                        FROM JOB_SPEC_QUESTIONS jsq,
                                    CANDIDATE_RESPONSE cr,
                                    QUESTION_OPTIONS qo
                        WHERE
                        cr.QUESTION_QUESTIONID = jsq.QUESTION_QUESTIONID
                        AND cr.QUESTION_QUESTIONID = qo.QUESTION_QUESTIONID
                        AND cr.RESPONSE_NUM = qo.SORT_SEQUENCE
                        AND jsq.job_spec_job_specid = ?1
                        GROUP BY jsq.job_spec_job_specid , cr.USERNAME
                        ORDER BY rank
                """;
        Query q = em.createNativeQuery(query);//, java.util.Map.Entry.class);
        List<Object[]> res = q.setParameter(1, jobSpecId).getResultList();
        List<CandidateRanking> candidates = new ArrayList<>();

        for(Object[] o : res) {
            CandidateRanking cr = new CandidateRanking(o);
            candidates.add(cr);
        }

        return
            candidates;
    }
}