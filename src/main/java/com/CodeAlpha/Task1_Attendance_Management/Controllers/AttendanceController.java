package com.CodeAlpha.Task1_Attendance_Management.Controllers;

import com.CodeAlpha.Task1_Attendance_Management.Models.Attendance;
import com.CodeAlpha.Task1_Attendance_Management.Models.AttendanceGraduate;
import com.CodeAlpha.Task1_Attendance_Management.Models.Graduate;
import com.CodeAlpha.Task1_Attendance_Management.Repository.AttendanceRepository;
import com.CodeAlpha.Task1_Attendance_Management.Services.AttendanceService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class AttendanceController {

    private AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/Attendance")
    public String getAllAttendance(Model model){
        List<Attendance> attendances= attendanceService.findAllAttendances();
        model.addAttribute("attendances",attendances);
        return "attendance-list-create";
    }


    @GetMapping("/attendanceForm")
    public String showForm(Model model) {
        model.addAttribute("attendance", new Attendance());
        return "attendance-list-create";
    }

    @PostMapping("/saveAttendance")
    public String saveAttendance(Attendance attendance) {

        attendanceService.saveAttendance(attendance);
        return "redirect:/Attendance";
    }

    @GetMapping("/attendanceDetails/{id}")
    public String attendanceDetails(@PathVariable int id,Model model) {
        Attendance attendance=null ;
        Optional<Attendance> a= attendanceService.findById((long) id);
        if(a.isPresent()){
            attendance= a.get();
        }
        List<AttendanceGraduate> attendanceGraduates= attendanceService.attendanceDetails(attendance);
        model.addAttribute("attendanceGraduates",attendanceGraduates);

        for(int i=0;i<attendanceGraduates.size();i++){
            System.out.println("H1 "+attendanceGraduates.get(i).getAttendanceGraduate_ID());
        }
        return "attendance-Details_ForOneGraduate";
    }

    /*@GetMapping("/allAttendanceDetails")
    public String attendanceDetails(Model model) {
        Attendance attendance=null ;
        Optional<Attendance> a= attendanceService.findById((long) id);
        if(a.isPresent()){
            attendance= a.get();
        }
        List<AttendanceGraduate> attendanceGraduates= attendanceService.attendanceDetails(attendance);
        model.addAttribute("attendanceGraduates",attendanceGraduates);

        for(int i=0;i<attendanceGraduates.size();i++){
            System.out.println("H1 "+attendanceGraduates.get(i).getAttendanceGraduate_ID());
        }
        return "attendance-Details";
    }*/
    @GetMapping("/deleteAttendance/{attendanceId}")
    public String deleteAttendance(@PathVariable int attendanceId) {
        System.out.println("H15");
        attendanceService.deleteAttendanceByID(attendanceId);
        System.out.println("H16");
        return "redirect:/Attendance";
    }


}
