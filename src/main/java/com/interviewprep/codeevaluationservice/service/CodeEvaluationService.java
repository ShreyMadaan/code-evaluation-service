package com.interviewprep.codeevaluationservice.service;

import com.interviewprep.codeevaluationservice.model.CodeSubmission;
import com.interviewprep.codeevaluationservice.repository.CodeSubmissionRepository;
import com.interviewprep.codeevaluationservice.util.DockerRunner;
import com.interviewprep.codeevaluationservice.util.FileUtil;
import org.springframework.stereotype.Service;

@Service
public class CodeEvaluationService {
    public final CodeSubmissionRepository codeSubmissionRepository;

    public CodeEvaluationService(CodeSubmissionRepository codeSubmissionRepository) {
        this.codeSubmissionRepository = codeSubmissionRepository;
    }

    public CodeSubmission submitCode(CodeSubmission codeSubmission) {
        codeSubmission.setStatus("Pending");

        String filePath = FileUtil.saveCodeToFile(codeSubmission.getCode(), "Main.java");

        DockerRunner dockerRunner = new DockerRunner();
        String output;
        switch (codeSubmission.getLanguage().toLowerCase()) {
            case "java":
                        filePath = FileUtil.saveCodeToFile(codeSubmission.getCode(), "Main.java");
                        output = dockerRunner.runJavaCode(filePath);
                        break;
            case "python":
                        filePath = FileUtil.saveCodeToFile(codeSubmission.getCode(), "Main.py");
                        output = dockerRunner.runPythonCode(filePath);
                        break;
            default:
                        throw new IllegalArgumentException("Unsupported language: " + codeSubmission.getLanguage());
                }

        codeSubmission.setOutput(output);
        codeSubmission.setStatus("Success");
        return codeSubmissionRepository.save(codeSubmission);
    }
    public CodeSubmission getSubmission(Long id) {
        return codeSubmissionRepository.findById(id).orElse(null);
    }
}
