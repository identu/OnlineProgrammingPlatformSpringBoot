package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.ProblemService;
import com.example.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/problems")

public class ProblemController {

    private final ProblemService problemService;
    private final TagService tagService;

    @Autowired
    public ProblemController(ProblemService problemService, TagService tagService) {
        this.problemService = problemService;
        this.tagService = tagService;
    }

    @GetMapping("/all")
    public List<Problem> getAllProblems() {
        return problemService.getAllProblems();
    }

    @GetMapping("/{problemId}")
    public ResponseEntity<Problem> getProblemById(@PathVariable Long problemId) {
        System.out.println("exec find");
        Problem problem=problemService.getProblemById(problemId);
        if (problem != null) {
            return new ResponseEntity<>(problem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/code/{problemId}")
    public List<PreCode> getPreCodesByProblemId(@PathVariable Long problemId){
        return problemService.getPreCodesByProblemId(problemId);
    }

    @PostMapping("/byTags")
    public List<Problem> getProblemsByTags(@RequestBody List<Long> tagIds){
        List<Tag> tags = tagService.findTagsByTagIdIn(tagIds);
        return problemService.getProblemsByTags(tags);
    }
    @GetMapping("/favorite/{userId}")
    public List<Problem> getFavoriteProblemsByUserId(@PathVariable Long userId){
        return problemService.getFavoriteProblemsByUserId(userId);
    }
    @GetMapping("/favorite")
    public ResponseEntity<String> addFavorite(@RequestParam Long userId, @RequestParam Long problemId) {
        try {
            problemService.addFavorite(userId, problemId);
            return new ResponseEntity<>("Problem successfully favorited by user with id " + userId, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while adding favorite", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/checkFavorite")
    public Favorite checkFavorite(@RequestParam Long userId, @RequestParam Long problemId) {
        try {
            return problemService.findFavoriteByUserIdAndProblemId(userId,problemId);
        } catch (Exception e) {
            return null;
        }
    }
    @DeleteMapping("deleteFavorite")
    public ResponseEntity<String> deleteFavorite(@RequestParam Long userId, @RequestParam Long problemId) {
        try {
            problemService.deleteFavorite(userId, problemId);
            return new ResponseEntity<>("Problem successfully delete favorited by user with id " + userId, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while deleting favorite", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}