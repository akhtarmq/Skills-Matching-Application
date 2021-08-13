package com.fyp.fyp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;


/**
 * This class represents a candidates ranking
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateRanking {
    /**
     * Job spec ID represents the ID of a Jobspec
     */
    Long job_spec_id;
    /**
     * Username represents the username of a candidate
     */
    String username;
    /**
     * Rank represent the total score for any one candidate against the job spec.
     * So it checks how well the answer matches the answer the hiring manager expects.
     */
    int rank;

    public CandidateRanking(Object[] values) {
        job_spec_id = ((BigInteger) values[0]).longValue();
        username = ((String) values[1]);
        rank = ((BigInteger) values[2]).intValue();
    }
}
