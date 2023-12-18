package com.dagbok.dagbok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class DiaryController {

    @Autowired
    private DiaryRepository diaryRepository;

    @GetMapping
    public String getIndex(Model model) {
        model.addAttribute("titles", diaryRepository.findAll());
        return "index";
    }

    @GetMapping("/new-post")
    public String addNew() {

        Diary diary = new Diary();
        diary.setTitle("Lägg till nya");
        diaryRepository.save(diary);

        return "redirect:/";
    }
}
