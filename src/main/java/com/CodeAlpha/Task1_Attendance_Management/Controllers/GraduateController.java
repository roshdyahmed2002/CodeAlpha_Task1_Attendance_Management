package com.CodeAlpha.Task1_Attendance_Management.Controllers;


import com.CodeAlpha.Task1_Attendance_Management.Models.Graduate;
import com.CodeAlpha.Task1_Attendance_Management.Services.GraduateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class GraduateController {

    GraduateService graduateService;

    public GraduateController(GraduateService graduateService) {
        this.graduateService = graduateService;
    }


    @GetMapping("/")
    public String home(Model model){

        return "home";

    }
    @GetMapping("/getAllGraduates")
    public String getAllGraduates(Model model){

        List<Graduate> graduates=graduateService.findAllGraduates();
        model.addAttribute("graduates",graduates);
        return "graduates-list";

    }
    @GetMapping("/graduateForm")
    public String showForm(Model model) {
            model.addAttribute("graduate", new Graduate());
            return "graduates-create";
        }

        @PostMapping("/saveGraduate")
        public String saveGraduate(Graduate graduate) {
            System.out.println("Received graduate name: " + graduate.getName());

            graduateService.saveGraduate(graduate);
            // Redirect to a confirmation page or wherever you want to navigate after saving
            return "redirect:/getAllGraduates";
        }


    @GetMapping("/deleteGraduate/{id}")
    public String deleteGraduate(@PathVariable int id) {
        System.out.println("H10");
        graduateService.deleteGraduateById(id);
        System.out.println("DONE");
        return "redirect:/getAllGraduates";
    }

}
