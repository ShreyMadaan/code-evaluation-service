package com.interviewprep.codeevaluationservice.repository;

import com.interviewprep.codeevaluationservice.model.CodeSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeSubmissionRepository extends JpaRepository<CodeSubmission, Long> {

}
