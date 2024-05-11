package com.example.demo.dao;

import com.example.demo.model.Problem;
import com.example.demo.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {
    List<Problem> findAll();
    List<Problem> findByTagsContains(Tag tag);
    List<Problem> findByTagsIn(List<Tag> tags);
    Problem findByProblemId(Long problemId);
    List<Problem> findAllBySectionsSectionId(Long sectionId);
}
