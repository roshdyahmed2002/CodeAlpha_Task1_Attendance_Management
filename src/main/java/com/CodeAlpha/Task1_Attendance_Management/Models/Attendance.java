package com.CodeAlpha.Task1_Attendance_Management.Models;

import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int attendance_ID;

    private LocalDate date;

    @OneToMany(mappedBy = "attendance", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<AttendanceGraduate> attendanceGraduates;


}
