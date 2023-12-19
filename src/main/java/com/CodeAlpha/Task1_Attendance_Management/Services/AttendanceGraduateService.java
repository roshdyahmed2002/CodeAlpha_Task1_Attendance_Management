package com.CodeAlpha.Task1_Attendance_Management.Services;

import com.CodeAlpha.Task1_Attendance_Management.Models.Attendance;
import com.CodeAlpha.Task1_Attendance_Management.Models.AttendanceGraduate;
import com.CodeAlpha.Task1_Attendance_Management.Models.Graduate;
import com.CodeAlpha.Task1_Attendance_Management.Repository.AttendanceGraduateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceGraduateService {

    private AttendanceGraduateRepository attendanceGraduateRepository;

    public AttendanceGraduateService(AttendanceGraduateRepository attendanceGraduateRepository) {
        this.attendanceGraduateRepository = attendanceGraduateRepository;
    }

    public List<AttendanceGraduate> saveAllAttendanceGraduates(List<AttendanceGraduate> attendanceGraduates){
        return attendanceGraduateRepository.saveAll(attendanceGraduates);
    }

    /*public List<AttendanceGraduate> findByAttendance(Attendance attendance){
        return attendanceGraduateRepository.findAllByAttendance(attendance);
    }*/

    public List<AttendanceGraduate> findByAttendanceOrderById(Attendance attendance) {
        return attendanceGraduateRepository.findByAttendanceOrderByID(attendance);
    }

    public List<AttendanceGraduate> findByAllOrderById() {
        return attendanceGraduateRepository.findByAllOrderByAttendanceGraduate_ID();
    }

    public List<AttendanceGraduate> findAll() {
        return attendanceGraduateRepository.findAll();
    }

    public void deleteAttendanceGraduateByAttendance(Attendance a){
        attendanceGraduateRepository.deleteByAttendance(a);
    }
    public void deleteAttendanceGraduateByGraduate(Graduate g){
        attendanceGraduateRepository.deleteByGraduate(g);
    }
}
