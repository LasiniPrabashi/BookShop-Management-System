package lk.ijse.BookShop.model;

import lk.ijse.BookShop.dto.Employee;
import lk.ijse.BookShop.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeModel {
    public static boolean add(Employee employee) throws SQLException, ClassNotFoundException {
        return CrudUtil.crudUtil("INSERT INTO Employee VALUES (?,?,?,?,?,?,?)",
                employee.getEmp_Id(),
                employee.getFistName(),
                employee.getLastName(),
                employee.getStreet(),
                employee.getCity(),
                employee.getLane(),
                employee.getContact()
        );
    }

    public static ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.crudUtil("SELECT Emp_Id FROM employee ORDER BY LENGTH(Emp_Id),Emp_Id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    public static Employee get(String id) throws SQLException, ClassNotFoundException {
        Employee employee=new Employee();
        ResultSet set = CrudUtil.crudUtil("SELECT * from Employee where Emp_Id=?", id);
        if (set.next()){
            employee.setEmp_Id(set.getString(1));
            employee.setFistName(set.getString(2));
            employee.setLastName(set.getString(3));
            employee.setStreet(set.getString(4));
            employee.setCity(set.getString(5));
            employee.setLane(set.getString(6));
            employee.setContact(set.getString(7));
        }
        return employee;
    }

    public static ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        ArrayList<String>ids=new ArrayList<>();
        ResultSet set=CrudUtil.crudUtil("SELECT Emp_Id from Employee where Emp_Id LIKE ? or fristName LIKE ? or lastName LIKE ?",id+"%",id+"%",id+"%");
        while (set.next()){
            ids.add(set.getString(1));
        }
        return ids ;
    }

    public static boolean remove(String emp_id) throws SQLException, ClassNotFoundException {
        return CrudUtil.crudUtil("DELETE FROM Employee WHERE Emp_Id=?",emp_id);
    }

    public static boolean update(Employee employee) throws SQLException, ClassNotFoundException {
        return CrudUtil.crudUtil("UPDATE Employee SET fristName=?,lastName=?,city=?,lane=?,street=?,contact=? WHERE Emp_Id=?",
                employee.getFistName(),
                employee.getLastName(),
                employee.getCity(),
                employee.getLane(),
                employee.getStreet(),
                employee.getContact(),
                employee.getEmp_Id()

                );
    }


    public static String getEmployeeCount() throws SQLException, ClassNotFoundException {
        ResultSet set= CrudUtil.crudUtil("SELECT COUNT(Emp_Id) FROM Employee");
        String count=null;
        if (set.next()){
            count=set.getString(1);
        }
        return count;
    }

}
