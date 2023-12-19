package com.CodeAlpha.Task1_Attendance_Management.Repository;

import com.CodeAlpha.Task1_Attendance_Management.Models.Graduate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface GraduateRepository extends JpaRepository<Graduate,Long> {
}
