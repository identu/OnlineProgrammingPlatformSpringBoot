package com.example.demo.service.impl;

import com.example.demo.dao.ChapterRepository;
import com.example.demo.dao.CourseRepository;
import com.example.demo.dao.SectionRepository;

import com.example.demo.model.Chapter;
import com.example.demo.model.Course;
import com.example.demo.model.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {


    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private SectionRepository sectionRepository;

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Chapter saveChapter(Chapter chapter) {
        return chapterRepository.save(chapter);
    }

    public List<Chapter> getChaptersByCourseId(Long courseId) {
        return chapterRepository.findByCourseId(courseId);
    }

    public Section saveSection(Section section) {
        return sectionRepository.save(section);
    }

    public List<Section> getSectionsByChapterId(Long chapterId) {
        return sectionRepository.findByChapterId(chapterId);
    }
}
