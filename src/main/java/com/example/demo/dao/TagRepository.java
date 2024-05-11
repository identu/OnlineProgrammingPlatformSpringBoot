package com.example.demo.dao;

import com.example.demo.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findTagsByTagIdIn(List<Long> tagIds);
    List<Tag> findByNameLike(String name);
    List<Tag> findByNameContaining(String name);
    Tag findByTagId(Long tagId);

}