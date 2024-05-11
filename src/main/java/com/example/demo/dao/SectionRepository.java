package com.example.demo.dao;

import com.example.demo.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    int countByChapterChapterId(Long chapterId);
    List<Section> findByChapterCourseCourseId(Long courseId);
    List<Section> findByChapterChapterId(Long chapterId);



    // 可以添加自定义的查询方法
}