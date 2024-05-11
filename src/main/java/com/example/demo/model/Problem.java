package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Problem")
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long problemId;

    private String title;


    @Column(name = "description", length = 600, columnDefinition = "TEXT")
    private String description;
    @Column(name = "example", length = 400, columnDefinition = "TEXT")
    private String example;
    private String difficulty;


    @ManyToMany
    @JoinTable(
            name = "ProblemTag",
            joinColumns = @JoinColumn(name = "problemId"),
            inverseJoinColumns = @JoinColumn(name = "tagId")
    )
    private Set<Tag> tags = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "Problem_Section",
            joinColumns = @JoinColumn(name = "problem_id"),
            inverseJoinColumns = @JoinColumn(name = "section_id")
    )
    @JsonIgnoreProperties("problems") // 忽略与小节的关联
    private Set<Section> sections = new HashSet<>();

    // Getters and setters

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public Set<Section> getSections() {
        return sections;
    }

    public void setSections(Set<Section> sections) {
        this.sections = sections;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }





}