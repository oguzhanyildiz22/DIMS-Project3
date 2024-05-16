package com.sau.dims.controller;

import com.sau.dims.model.Adviser;
import com.sau.dims.model.Study;
import com.sau.dims.repository.AdviserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;


@Controller
public class AdviserController {
    @Autowired
    private AdviserRepository adviserRepository;

    @GetMapping("/adviser")
    public String getAdviser(Model model){
        List<Adviser> advisers = adviserRepository.findAllAscById();
        model.addAttribute("advisers", advisers);
        return "adviser/index";
    }
    @PostMapping("/adviser/add")
    public String addAdviser(@Valid Adviser adviser, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("adviser",adviser);
            model.addAttribute("error",result.getAllErrors());
            return "/adviser/add";
        }
        adviser.setName(convertFirstLetterToUpperCase(adviser.getName()));
        adviser.setDepartment(convertFirstLetterToUpperCase(adviser.getDepartment()));

        adviserRepository.save(adviser);
        return "redirect:/adviser";
    }
    @GetMapping("/adviser/update/{id}")
    @ResponseBody
    public Adviser updateAdviser(@PathVariable("id") int id){
        return adviserRepository.findById(id).orElseThrow(
                ()-> {throw new RuntimeException("Adviser not found!:"+id);}
        );
    }
    @PostMapping("/adviser/update")
    public String updateAdviser(@Valid Adviser adviser, BindingResult result){
        if (result.hasErrors()){
            return "redirect:/adviser";
        }
        adviser.setName(convertFirstLetterToUpperCase(adviser.getName()));
        adviser.setDepartment(convertFirstLetterToUpperCase(adviser.getDepartment()));

        adviserRepository.save(adviser);
        return "redirect:/adviser";
    }
    @GetMapping("/adviser/delete/{id}")
    @ResponseBody
    public Adviser deleteAdviser(@PathVariable("id") int id){
        return adviserRepository.findById(id).orElseThrow(
                ()-> {throw new RuntimeException("Adviser not found!:"+id);}
        );
    }
    @PostMapping("/adviser/delete")
    public String deleteAdviser(Adviser adviser){
        adviserRepository.delete(adviser);
        System.out.println("Adviser is deleted:"+ adviser.getId());
        return "redirect:/adviser";
    }

    @GetMapping("/adviser/getAdvisers")
    public ResponseEntity<List<Adviser>> getAllStudies() {
        List<Adviser> advisers = adviserRepository.findAll();
        return ResponseEntity.ok(advisers);
    }
    private String convertFirstLetterToUpperCase(String input){
        return Pattern.compile("\\b(\\w)").matcher(input).replaceAll(m -> m.group().toUpperCase());
    }
}
