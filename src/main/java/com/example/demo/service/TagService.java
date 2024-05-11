package com.example.demo.service;

import com.example.demo.model.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findTagsByTagIdIn(List<Long> tagIds);

    List<Tag> getAllTags();


    List<Tag> getTagsByNameContaining(String name);

    Tag createTag(Tag tag);

    Tag updateTag(Long tagId, Tag newTag);

    void deleteTag(Long tagId);
}
