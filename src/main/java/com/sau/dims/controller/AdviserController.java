package com.sau.dims.controller;

import com.sau.dims.dto.AdviserDTO;
import com.sau.dims.dto.AdviserUpdateDTO;
import com.sau.dims.model.Adviser;
import com.sau.dims.repository.AdviserRepository;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Pattern;


@Controller
public class AdviserController {
    @Autowired
    private AdviserRepository adviserRepository;

    @GetMapping("/adviser")
    public String getAdviser(Model model) {
        List<Adviser> advisers = adviserRepository.findAllAscById();

        List<AdviserDTO> adviserDTOs = new ArrayList<>();

        for (Adviser adviser : advisers) {
            AdviserDTO adviserDTO = new AdviserDTO();
            adviserDTO.setId(adviser.getId());
            adviserDTO.setName(adviser.getName());
            adviserDTO.setDepartment(adviser.getDepartment());

            String encodedImage = Base64.getEncoder().encodeToString(adviser.getImgURL());
            adviserDTO.setPicture(encodedImage);

            adviserDTOs.add(adviserDTO);
        }

        model.addAttribute("advisers", adviserDTOs);
        return "adviser/index";
    }
    @GetMapping("/adviser/update/{id}")
    @ResponseBody
    public Adviser updateAdviser(@PathVariable("id") int id){
        return adviserRepository.findById(id).orElseThrow(
                ()-> {throw new RuntimeException("Adviser not found!:"+id);}
        );
    }
    @PostMapping("/adviser/update")
    public String updateAdviser(@Valid AdviserUpdateDTO adviserDto, BindingResult result) throws IOException {
        if (result.hasErrors()){
            return "redirect:/adviser";
        }
        Adviser adviser = adviserRepository.findById(adviserDto.getId()).get();
        adviser.setName(convertFirstLetterToUpperCase(adviserDto.getName()));
        adviser.setDepartment(convertFirstLetterToUpperCase(adviserDto.getDepartment()));
        if(!adviserDto.getPicture().isEmpty())
            adviser.setImgURL(adviserDto.getPicture().getBytes());

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
