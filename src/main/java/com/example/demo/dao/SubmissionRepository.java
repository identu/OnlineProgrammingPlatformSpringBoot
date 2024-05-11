package com.example.demo.dao;

import com.example.demo.model.Problem;
import com.example.demo.model.Submission;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    List<Submission> findByUserUserIdAndProblemProblemId(Long userId, Long problemId);
    Submission findFirstByUserUserIdAndProblemProblemIdOrderBySubmissionTimeDesc(Long userId, Long problemId);
    Submission findFirstByUserUserIdAndProblemProblemIdOrderBySubmissionIdDesc(Long userId, Long problemId);

    List<Submission> findByUserAndProblemIn(User user, Set<Problem> problems);
    List<Submission> findByUser(User user);
    List<Submission> findByProblemProblemId(Long problemId);
}