package com.CodeAlpha.Task1_Attendance_Management.Repository;

import com.CodeAlpha.Task1_Attendance_Management.Models.Attendance;
import com.CodeAlpha.Task1_Attendance_Management.Models.AttendanceGraduate;
import com.CodeAlpha.Task1_Attendance_Management.Models.Graduate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;


import java.util.List;


public interface AttendanceGraduateRepository extends JpaRepository<AttendanceGraduate,Long> {
    List<AttendanceGraduate> findAllByAttendance(Attendance attendance);

    List<AttendanceGraduate> findByAttendanceOrderByAttendance(Attendance attendance);

    @Query("SELECT ag FROM AttendanceGraduate ag WHERE ag.attendance = :attendance ORDER BY ag.attendanceGraduate_ID ASC")
    List<AttendanceGraduate> findByAttendanceOrderByID(Attendance attendance);

   @Query("SELECT ag FROM AttendanceGraduate ag ORDER BY ag.attendanceGraduate_ID ASC")
    List<AttendanceGraduate> findByAllOrderByAttendanceGraduate_ID();

    /*@Query("SELECT ag FROM AttendanceGraduate ag WHERE ag.attendance = :attendance ORDER BY ag.attendanceGraduate_ID ASC")
    List<AttendanceGraduate> findByAttendanceOrderedByIdAsc(@Param("attendance") Attendance attendance);
*/
    void deleteByAttendance(Attendance attendance);
    void deleteByGraduate(Graduate graduate);



}
