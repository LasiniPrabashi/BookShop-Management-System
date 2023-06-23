package lk.ijse.BookShop.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import lk.ijse.BookShop.dto.EmployeeAttendance;
import lk.ijse.BookShop.tm.EmployeeAttendanceTm;
import lk.ijse.BookShop.util.CrudUtil;
import lk.ijse.BookShop.util.DateTimeUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeAttendanceModel {

    public static boolean add(EmployeeAttendance employeeAttendance) throws SQLException, ClassNotFoundException {
        return CrudUtil.crudUtil("INSERT INTO  EmployeeAttendance VALUES (?,?,?,?)",
                employeeAttendance.getAttendance_Id(),
                employeeAttendance.getEmp_Id(),
                employeeAttendance.getWorkingHourse(),
                employeeAttendance.getDate()
        );
    }

    public static ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ArrayList<String> list = new ArrayList<>();
        ResultSet set = CrudUtil.crudUtil("SELECT attendance_Id FROM EmployeeAttendance ORDER BY LENGTH(attendance_Id),attendance_Id");

        while (set.next()) {
            list.add(set.getString(1));
        }

        return list;
    }

    public static ArrayList<String> getTodayAllId() throws SQLException, ClassNotFoundException {
        ArrayList<String> list = new ArrayList<>();
        ResultSet set = CrudUtil.crudUtil("SELECT attendance_Id FROM EmployeeAttendance WHERE date=?", DateTimeUtil.dateNow());

        while (set.next()) {
            list.add(set.getString(1));
        }

        return list;
    }

    public static ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        ArrayList<String> list = new ArrayList<>();
        String editId = id + "%";
        ResultSet set = CrudUtil.crudUtil("SELECT  attendance_Id FROM EmployeeAttendance INNER JOIN Employee WHERE attendance_Id LIKE ? OR Employee.Emp_Id LIKE ? OR  fristName LIKE ? OR lastName LIKE ?", editId, editId, editId, editId);
        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    public static EmployeeAttendanceTm get(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.crudUtil("SELECT a.attendance_Id,e.fristName,e.lastName,a.date,a.workingHourse FROM employeeattendance a JOIN employee e On a.Emp_Id = e.Emp_Id where attendance_Id=?", id);
        EmployeeAttendanceTm attendance = new EmployeeAttendanceTm();

        ObservableList<EmployeeAttendanceTm> list = FXCollections.observableArrayList();


        while (set.next()) {
            attendance.setId(set.getString(1));
            attendance.setFist_name(set.getString(2));
            attendance.setLast_name(set.getString(3));
            attendance.setDate(set.getString(4));
            attendance.setTime(set.getString(5));

        }
        return attendance;
    }

    public static String getAttendanceCount() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.crudUtil("SELECT COUNT(Emp_Id) FROM EmployeeAttendance");
        while (set.next()) {
            return set.getString(1);
        }
        return null;
    }

    public static String getEmpCount() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.crudUtil("SELECT COUNT(Emp_Id) FROM Employee");
        while (set.next()) {
            return set.getString(1);
        }
        return null;
    }

    public static ArrayList<String> getAllEmpId() throws SQLException, ClassNotFoundException {
        ArrayList<String> list = new ArrayList<>();
        String month = DateTimeUtil.dateNow();
        String[] split = month.split("-");
        System.out.println(split[0] + "-" + split[1] + "%");
        ResultSet set = CrudUtil.crudUtil("SELECT Emp_Id FROM salary where date LIKE ?", split[0] + "-" + split[1] + "%");

        while (set.next()) {
            list.add(set.getString(1));
        }

        return list;
    }

    public static String getEmpAttCount(String value) throws SQLException, ClassNotFoundException {
        String month = DateTimeUtil.dateNow();
        String[] split = month.split("-");
        ResultSet set = CrudUtil.crudUtil("SELECT COUNT(Emp_Id) FROM EmployeeAttendance WHERE Emp_Id=? AND date LIKE ?", value, split[0] + "-" + split[1] + "%");
        if (set.next()){
            return set.getString(1);
        }
        return null;
    }

    public static String getEmpAttCount() throws SQLException, ClassNotFoundException {
            ResultSet set= CrudUtil.crudUtil("SELECT COUNT(Emp_Id) FROM employeeattendance");
            String count=null;
            if (set.next()){
                count=set.getString(1);
            }
            return count;

        }
}
