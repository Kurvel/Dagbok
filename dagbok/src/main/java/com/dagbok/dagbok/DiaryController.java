package com.dagbok.dagbok;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class DiaryController {

    @Autowired
    private DiaryRepository diaryRepository;

    

    @GetMapping
    public String getIndex(Model model) {
        model.addAttribute("titles", diaryRepository.findAll());
        return "index";
    }

    // @DateTimeFormat(pattern="dd-MMM-YYYY")
    @PostMapping("/new-post")
    public String addNew(@RequestParam("title") String titleName, @RequestParam("post") String postName, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date postDate) {

        
        
        System.out.println("Ny title från form: " + titleName);
        System.out.println("Ny post från form: " + postName);
        System.out.println("Nytt datum från form: " + postDate);

        Diary diary = new Diary();
        diary.setTitle(titleName);
        diary.setPost(postName);
        diary.setDate(postDate);
        diaryRepository.save(diary);

        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id) {

        System.out.println("Delete mapping: " + id);
        diaryRepository.deleteById(id);
        return "redirect:/";
    }
}
