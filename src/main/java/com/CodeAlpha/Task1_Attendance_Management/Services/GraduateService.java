package com.CodeAlpha.Task1_Attendance_Management.Services;

import com.CodeAlpha.Task1_Attendance_Management.Models.Attendance;
import com.CodeAlpha.Task1_Attendance_Management.Models.AttendanceGraduate;
import com.CodeAlpha.Task1_Attendance_Management.Models.Graduate;
import com.CodeAlpha.Task1_Attendance_Management.Repository.AttendanceRepository;
import com.CodeAlpha.Task1_Attendance_Management.Repository.GraduateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class GraduateService {

    private GraduateRepository graduateRepository;
    private AttendanceGraduateService attendanceGraduateService;
    private AttendanceRepository attendanceRepository;

    public GraduateService(GraduateRepository graduateRepository,
                           AttendanceGraduateService attendanceGraduateService,
                           AttendanceRepository attendanceRepository) {
        this.graduateRepository = graduateRepository;
        this.attendanceGraduateService = attendanceGraduateService;
        this.attendanceRepository = attendanceRepository;
    }

    public List<Graduate> findAllGraduates(){

        return graduateRepository.findAll();

    }
    public Graduate saveGraduate(Graduate graduate) {
        System.out.println("YARAB");
        Graduate grad = graduateRepository.save(graduate);
        List<AttendanceGraduate> attendanceGraduates = attendanceGraduateService.findAll();

        List<AttendanceGraduate> attendanceGradL = new ArrayList<AttendanceGraduate>();
        List<List<AttendanceGraduate>> attendanceGradLL = new ArrayList<List<AttendanceGraduate>>();

        List<AttendanceGraduate> attendanceGraduatesUpdated = new ArrayList<AttendanceGraduate>();

        if(attendanceGraduates.size()==0){
            List<Attendance> attendances= attendanceRepository.findAll();
            for(int i=0;i<attendances.size();i++){
                AttendanceGraduate attendanceGraduate1 = new AttendanceGraduate();
                attendanceGraduate1.setAttendance(attendances.get(i));
                attendanceGraduate1.setPresent(false);
                attendanceGraduate1.setGraduate(grad);
                attendanceGraduatesUpdated.add(attendanceGraduate1);
            }
        }
        else{

            LocalDate date = attendanceGraduates.get(0).getAttendance().getDate();
            boolean addToLL = false;
            for (int i = 0; i < attendanceGraduates.size(); i++) {
                addToLL = false;
                if (attendanceGraduates.get(i).getAttendance().getDate().equals(date)) {
                    System.out.println("H01");
                    attendanceGradL.add((attendanceGraduates.get(i)));
                } else {
                    System.out.println("H02");
                    attendanceGradLL.add(attendanceGradL);
                    date = attendanceGraduates.get(i).getAttendance().getDate();
                    i--;
                    addToLL = true;
                }
            }
            if (addToLL == false) {
                System.out.println("H03");
                attendanceGradLL.add(attendanceGradL);
            }

            System.out.println("H1");
            for (int i = 0; i < attendanceGradLL.size(); i++) {
                List<AttendanceGraduate> attendanceGradL1 = attendanceGradLL.get(i);
                Attendance a = attendanceGradL1.get(i).getAttendance();
                boolean gradExist = false;
                System.out.println("H2");
                for (int j = 0; j < attendanceGradL1.size(); j++) {
                    System.out.println("H3");
                    if (attendanceGradL1.get(j).getGraduate().getGraduate_ID() == grad.getGraduate_ID()) {
                        System.out.println("H4");
                        gradExist = true;
                        break;
                    }

                }
                if (gradExist == false) {
                    System.out.println("H5");
                    AttendanceGraduate attendanceGraduate = new AttendanceGraduate();
                    attendanceGraduate.setAttendance(a);
                    attendanceGraduate.setPresent(false);
                    attendanceGraduate.setGraduate(grad);
                    attendanceGradL1.add(attendanceGraduate);
                    attendanceGradLL.set(i, attendanceGradL1);
                }
            }
            for (int i = 0; i < attendanceGradLL.size(); i++) {
                System.out.println("H6");
                for (int j = 0; j < attendanceGradLL.get(i).size(); j++) {
                    attendanceGraduatesUpdated.add(attendanceGradLL.get(i).get(j));
                }
            }
            for (int i = 0; i < attendanceGraduatesUpdated.size(); i++) {
                System.out.println("H7" + attendanceGraduatesUpdated.get(i).getAttendanceGraduate_ID());

            }
        }
        attendanceGraduateService.saveAllAttendanceGraduates(attendanceGraduatesUpdated);


        return grad;
    }

    public void deleteGraduateById(int graduateId) {

        graduateRepository.deleteById((long)graduateId);
       // attendanceGraduateService.deleteAttendanceGraduateByGraduate(graduateRepository.getById((long)graduateId));
    }
}
