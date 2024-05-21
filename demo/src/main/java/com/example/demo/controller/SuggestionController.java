package com.example.demo.controller;

import com.example.demo.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/suggestions")
public class SuggestionController {

    private final SuggestionService suggestionService;

    @Autowired
    public SuggestionController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @GetMapping("/suggestDoctor/{patientId}")
    public String suggestDoctor(@PathVariable Long patientId) {
        return suggestionService.suggestDoctor(patientId);
    }
}
