package com.example.demo.service;

import com.example.demo.model.*;


import java.util.List;

public interface ProblemService {


    void addFavorite(Long userId, Long problemId);

    void deleteFavorite(Long userId, Long problemId);

    Favorite findFavoriteByUserIdAndProblemId(Long userId, Long problemId);

    List<Problem> getAllProblems();

    List<Problem> getProblemsBySectionId(Long sectionId);

    List<Problem> getFavoriteProblemsByUserId(Long userId);

    List<Problem> getProblemsByTags(List<Tag> tags);

    List<Problem> getProblemsByTag(Tag tag);

    Problem getProblemById(Long problemId);

    List<PreCode> getPreCodesByProblemId(Long problemId);

    Problem createProblem(Problem problem);

    Problem updateProblem(Problem problem);

    TestCase createTestCase(TestCase testCase,Long problemId);

    TestCase updateTestCase(TestCase testCase);

    void deleteProblem(Long problemId);

    void addProblemToSection(Long problemId, Long sectionId);

    void removeProblemFromSection(Long problemId, Long sectionId);

    void addTagToProblem(Long problemId, Long tagId);

    void removeTagFromProblem(Long problemId, Long tagId);

    List<TestCase> getTestcasesByProblemId(Long problemId);

    List<PreCode> getPrecodeByProblemId(Long problemId);

    void deleteTestCase(Long caseId);

    PreCode createPreCode(PreCode preCode,Long problemId);

    PreCode updatePreCode(PreCode preCode);

    void deletePreCode(Long preCodeId);
}
