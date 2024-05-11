package com.example.demo.controller;

import com.example.demo.model.Tag;

import com.example.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")

public class TagController {


    private final TagService tagService;
    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }
    @PostMapping("/Ids/")
    public List<Tag> getByIdIn(@RequestBody List<Long> tagIds) {
        return tagService.findTagsByTagIdIn(tagIds);
    }
    @GetMapping("/all")
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

}