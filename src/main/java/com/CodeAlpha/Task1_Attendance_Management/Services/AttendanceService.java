package com.CodeAlpha.Task1_Attendance_Management.Services;

import com.CodeAlpha.Task1_Attendance_Management.Models.Attendance;
import com.CodeAlpha.Task1_Attendance_Management.Models.AttendanceGraduate;
import com.CodeAlpha.Task1_Attendance_Management.Models.Graduate;
import com.CodeAlpha.Task1_Attendance_Management.Repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    private AttendanceRepository attendanceRepository;
    private AttendanceGraduateService attendanceGraduateService;
    private GraduateService graduateService;
    public AttendanceService(AttendanceRepository attendanceRepository,
                             AttendanceGraduateService attendanceGraduateService,
                             GraduateService graduateService) {
        this.attendanceRepository = attendanceRepository;
        this.attendanceGraduateService = attendanceGraduateService;
        this.graduateService = graduateService;
    }

    public List<Attendance> findAllAttendances() {
        return attendanceRepository.findAll();
    }

    public Attendance saveAttendance(Attendance attendance) {
        // Set createdAt to the current timestamp (if not set already)

        LocalDate date = LocalDate.now();
        List<Attendance> attendances= findAllAttendances();
        for (int i=0;i<attendances.size();i++) {

            if(attendances.get(i).getDate().equals(date)){
                return new Attendance();
            }
        }
        if (attendance.getDate() == null) {
            attendance.setDate(LocalDate.now());
        }

        Attendance a= attendanceRepository.save(attendance);


        List<AttendanceGraduate> attendanceGraduates = new ArrayList<>();
        List<Graduate> graduates= graduateService.findAllGraduates();
        for (int i=0;i<graduates.size();i++){
            AttendanceGraduate attendanceGraduate= new AttendanceGraduate();
            attendanceGraduate.setPresent(false);
            attendanceGraduate.setAttendance(a);
            attendanceGraduate.setGraduate(graduates.get(i));
            attendanceGraduates.add(attendanceGraduate);
        }
        attendanceGraduateService.saveAllAttendanceGraduates(attendanceGraduates);
        return a;
    }

    public Optional<Attendance> findById(Long id){
        return  attendanceRepository.findById(id);
    }

    public List<AttendanceGraduate> attendanceDetails(Attendance attendance){
        return attendanceGraduateService.findByAttendanceOrderById(attendance);
    }


    public void deleteAttendanceByID(int attendanceId) {
        attendanceRepository.deleteById((long) attendanceId);
       /* Attendance  a= attendanceRepository.getById((long)attendanceId);
        attendanceGraduateService.deleteAttendanceGraduateByAttendance(a);
*/
    }
}