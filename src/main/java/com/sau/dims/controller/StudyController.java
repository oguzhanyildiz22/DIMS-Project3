package com.sau.dims.controller;

import com.sau.dims.model.Study;
import com.sau.dims.repository.StudyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.regex.Pattern;

@Controller
public class StudyController {
    @Autowired
    private StudyRepository studyRepository;

    @GetMapping("/study")
    public String getStudy(Model model){
        Iterable<Study> studyList = studyRepository.findAllAscById();
        model.addAttribute("studyList", studyList);
        return "/study/index";
    }

    @PostMapping("/study/add")
    public String studyAdd(@Valid Study study, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("study",study);
            model.addAttribute("error",bindingResult.getAllErrors());
            return "/study/add";
        }

        study.setTitle(convertFirstLetterToUpperCase(study.getTitle()));
        study.setDescription(convertFirstLetterToUpperCase(study.getDescription()));
        studyRepository.save(study);
        return "redirect:/study";
    }

    @GetMapping("/study/update/{id}")
    @ResponseBody
    public Study studyUpdate (@PathVariable("id") int id){
        return studyRepository.findById(id).orElseThrow(()->{throw new RuntimeException("Study not found!"+id);});
     }

     @PostMapping("/study/update")
    public String studyUpdate(@Valid Study study, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "redirect:/study";
        }
         study.setTitle(convertFirstLetterToUpperCase(study.getTitle()));
         study.setDescription(convertFirstLetterToUpperCase(study.getDescription()));
         studyRepository.save(study);
        return "redirect:/study";
     }

     @GetMapping("/study/delete/{id}")
     @ResponseBody
    public Study studyDelete(@PathVariable("id") int id){
        return studyRepository.findById(id).orElseThrow(
                ()-> {throw new RuntimeException("Study not found!:"+id);}
        );
     }

     @PostMapping("/study/delete")
    public String studyDelete(Study study){
        studyRepository.delete(study);
        return "redirect:/study";
     }

    @GetMapping("/study/getStudies")
    public ResponseEntity<List<Study>> getAllStudies() {
        List<Study> studies = studyRepository.findAll();
        return ResponseEntity.ok(studies);
    }

    private String convertFirstLetterToUpperCase(String input){
        return Pattern.compile("\\b(\\w)").matcher(input).replaceAll(m -> m.group().toUpperCase());
    }
}
