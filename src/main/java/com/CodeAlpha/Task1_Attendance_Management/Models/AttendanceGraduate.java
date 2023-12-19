package com.CodeAlpha.Task1_Attendance_Management.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class AttendanceGraduate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int attendanceGraduate_ID;

    @ManyToOne(/*cascade = CascadeType.REMOVE*/)
    @JoinColumn(name = "graduate_ID")
    private Graduate graduate;

    @ManyToOne(/*cascade = CascadeType.ALL*/)
    @JoinColumn(name = "attendance_ID")
    private Attendance attendance;

    private boolean isPresent;

}
