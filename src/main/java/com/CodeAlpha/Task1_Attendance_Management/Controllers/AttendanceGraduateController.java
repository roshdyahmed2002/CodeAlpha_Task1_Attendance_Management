package com.CodeAlpha.Task1_Attendance_Management.Controllers;

import com.CodeAlpha.Task1_Attendance_Management.Models.AttendanceGraduate;
import com.CodeAlpha.Task1_Attendance_Management.Models.Graduate;
import com.CodeAlpha.Task1_Attendance_Management.Repository.*;
import com.CodeAlpha.Task1_Attendance_Management.Services.AttendanceGraduateService;
import com.CodeAlpha.Task1_Attendance_Management.Services.AttendanceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AttendanceGraduateController {

    private AttendanceGraduateRepository attendanceGraduateRepository;
    private AttendanceGraduateService attendanceGraduateService;

    public AttendanceGraduateController(AttendanceGraduateRepository attendanceGraduateRepository,
                                        AttendanceGraduateService attendanceGraduateService) {
        this.attendanceGraduateRepository = attendanceGraduateRepository;
        this.attendanceGraduateService = attendanceGraduateService;
    }

    @GetMapping("/setPresent/{id}")
    public String setPresent(@PathVariable int id) {
        // Retrieve the AttendanceGraduate record by ID
        AttendanceGraduate attendanceGraduate = attendanceGraduateRepository.findById((long)id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid AttendanceGraduate ID: " + id));

        attendanceGraduate.setPresent(true);

        attendanceGraduateRepository.save(attendanceGraduate);
        System.out.println("HERE1");
        return "redirect:/getAllAttendanceGraduate";
    }
    @GetMapping("/setPresentForOneGraduate/{id}")
    public String setPresentForOneGraduate(@PathVariable int id) {
        // Retrieve the AttendanceGraduate record by ID
        AttendanceGraduate attendanceGraduate = attendanceGraduateRepository.findById((long)id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid AttendanceGraduate ID: " + id));

        attendanceGraduate.setPresent(true);

        attendanceGraduateRepository.save(attendanceGraduate);
        System.out.println("HERE");
        return "redirect:/attendanceDetails/" + attendanceGraduate.getAttendance().getAttendance_ID();


    }

    @GetMapping("/getAllAttendanceGraduate")
    public String getAllAttendanceGraduate(Model model) {

            List<AttendanceGraduate> attendanceGraduates=attendanceGraduateService.findByAllOrderById();
            model.addAttribute("attendanceGraduates",attendanceGraduates);
            return "attendance-Details";

    }

}
