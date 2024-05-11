package com.example.demo.dao;

import com.example.demo.model.Section;
import com.example.demo.model.User;
import com.example.demo.model.UserGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserGradeRepository  extends JpaRepository<UserGrade, Long> {
    UserGrade findByUserAndSection(User user, Section section);
    List<UserGrade> findByUser(User user);
    Optional<UserGrade> findByUserUserIdAndSectionSectionId(Long userId, Long sectionId);
}
