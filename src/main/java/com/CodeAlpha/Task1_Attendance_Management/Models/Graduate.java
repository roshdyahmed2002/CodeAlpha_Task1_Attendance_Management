package com.CodeAlpha.Task1_Attendance_Management.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Graduate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int graduate_ID;

    private String name;

    @OneToMany(mappedBy = "graduate", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<AttendanceGraduate> attendanceGraduates;
}
