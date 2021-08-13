package com.fyp.fyp.services;

import com.fyp.fyp.dao.SelectedCandidateDao;
import com.fyp.fyp.entity.CandidateResponse;
import com.fyp.fyp.entity.JobSpec;
import com.fyp.fyp.entity.SelectedCandidate;
import com.fyp.fyp.entity.SelectedCandidateId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
//@AutoConfigureMockMvc
class CandidateServiceImplTest {

    @Autowired
    CandidateServiceImpl candidateServiceImpl;

    @Autowired
    JobSpecService jobSpecService;

    @Autowired
    SelectedCandidateDao selectedCandidateDao;

    @Test
    void saveQuestionResponse() {
    }

    @Test
    void approveUser() {
        //setup data
        JobSpec jobSpec = new JobSpec();
        jobSpec.setJobSpecID(1L);
        jobSpecService.saveNewJobSpec(jobSpec);
        jobSpecService.selectCandidate("can", 1L);

        //test method
        candidateServiceImpl.approveUser("can", 1L);

        //check if worked correctly
        SelectedCandidateId id = new SelectedCandidateId();
        id.setJobSpecID(1L);
        id.setUsername("can");
        SelectedCandidate candidate = selectedCandidateDao.findById(id).get();
        Assert.isTrue(candidate.getApproved(), "candidate got approved");
    }

    @Test
    void declineUser() {
    }
}