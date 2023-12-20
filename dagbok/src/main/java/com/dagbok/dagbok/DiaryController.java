package com.dagbok.dagbok;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class DiaryController {

    @Autowired
    private DiaryRepository diaryRepository;
    
    Diary diary = new Diary();
    List<Diary> searchResult;

    @GetMapping
    public String getIndex(Model model) {
        // model.addAttribute("diaryPosts", diaryRepository.findAll());
        model.addAttribute("diaryPosts", diaryRepository.findCurrentDate());
        return "index";
    }

    
    @PostMapping("/new-post")
    public String addNew(@RequestParam("title") String titleName,
    @RequestParam("post") String postName,
    @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date postDate) {

        
        
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

    @GetMapping("/update")
    public String getUpdate(@RequestParam int id, Model model) {
        System.out.println("update");
        model.addAttribute("diaryPosts", diaryRepository.findAll());
        System.out.println("index: " + id);
        diary.setId(id);
        return "/update";
    }

    
    @PostMapping("/update/{id}")
    public String update(@RequestParam("title") String titleName, 
    @RequestParam("post") String postName,
    @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date postDate, Model model) {

        System.out.println("Ny title från update: " + titleName);
        System.out.println("Ny post från update: " + postName);
        System.out.println("Nytt datum från update: " + postDate);
        int id = diary.getId();
        System.out.println(id);
        diaryRepository.updatePost(titleName, postName, postDate, id);
        
        
        

        return "redirect:/";
     }


    @GetMapping("/search")
    public String getSearch(Model model) {
       
        model.addAttribute("diaryPosts", getSearchResult());
        return "/search";
    }
     
    @PostMapping("/search")
    public String search(
    @RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate, @RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {
   
        System.out.println("Nytt från datum: " + fromDate);
        System.out.println("Nytt till datum: " + toDate);

        searchResult = diaryRepository.findDatesBetween(fromDate, toDate);
        setSearchResult(searchResult);
        return "redirect:/search";
    }


    public List<Diary> getSearchResult() {
        return searchResult;
    }


    public void setSearchResult(List<Diary> searchResult) {
        this.searchResult = searchResult;
    }


    

    
}
