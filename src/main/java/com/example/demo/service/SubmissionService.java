package com.example.demo.service;

import com.example.demo.model.Submission;

public interface SubmissionService {
    Submission submitSolution(Long userId, Long ProblemId, String code, String language);


    void updateSectionGrades(Long userId, Long problemId);
}
