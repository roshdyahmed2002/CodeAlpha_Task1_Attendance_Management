package com.CodeAlpha.Task1_Attendance_Management.Repository;

import com.CodeAlpha.Task1_Attendance_Management.Models.Attendance;
import com.CodeAlpha.Task1_Attendance_Management.Models.Graduate;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
}
