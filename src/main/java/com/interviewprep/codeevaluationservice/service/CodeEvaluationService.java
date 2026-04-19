package com.interviewprep.codeevaluationservice.service;

import com.interviewprep.codeevaluationservice.model.CodeSubmission;
import com.interviewprep.codeevaluationservice.repository.CodeSubmissionRepository;
import org.springframework.stereotype.Service;

@Service
public class CodeEvaluationService {
    public final CodeSubmissionRepository codeSubmissionRepository;

    public CodeEvaluationService(CodeSubmissionRepository codeSubmissionRepository) {
        this.codeSubmissionRepository = codeSubmissionRepository;
    }

    public CodeSubmission submitCode(CodeSubmission codeSubmission) {
        codeSubmission.setStatus("Pending");

        codeSubmission.setOutput("Code execution is not implemented yet.");
        codeSubmission.setStatus("Success");
        return codeSubmissionRepository.save(codeSubmission);
    }
    public CodeSubmission getSubmission(Long id) {
        return codeSubmissionRepository.findById(id).orElse(null);
    }
}
