package com.interviewprep.codeevaluationservice.controller;

import com.interviewprep.codeevaluationservice.model.CodeSubmission;
import com.interviewprep.codeevaluationservice.service.CodeEvaluationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/code-evaluation")
public class CodeEvaluationController {
    private final CodeEvaluationService codeEvaluationService;

    public CodeEvaluationController(CodeEvaluationService codeEvaluationService) {
        this.codeEvaluationService = codeEvaluationService;
    }

    @PostMapping("/submit")
    public CodeSubmission submitCode(@RequestBody CodeSubmission codeSubmission){
        return codeEvaluationService.submitCode(codeSubmission);
    }
    @GetMapping("/{id}")
    public CodeSubmission getSubmission(@PathVariable Long id){
        return codeEvaluationService.getSubmission(id);
    }

}
