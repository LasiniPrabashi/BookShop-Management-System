package lk.ijse.BookShop.dto;

import lk.ijse.BookShop.tm.SalaryTm;
import lk.ijse.BookShop.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeSalaryModel {
    public static ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ArrayList<String>ids=new ArrayList<>();
        ResultSet set= CrudUtil.crudUtil("SELECT salary_Id FROM salary ORDER BY LENGTH(salary_Id),salary_Id");
        while (set.next())
        {
            ids.add(set.getString(1));
        }
        return ids;
    }

    public static boolean add(Salary salary) throws SQLException, ClassNotFoundException {
        return CrudUtil.crudUtil("INSERT INTO salary VALUES (?,?,?,?,?,?,?)",
                salary.getSalary_Id(),
                salary.getEmp_Id(),
                salary.getDaily_salary(),
                salary.getBonus(),
                salary.getDate(),
                salary.getTime(),
                salary.getTotal_salary()
                );
    }

    public static SalaryTm getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet set=CrudUtil.crudUtil("SELECT * FROM salary WHERE salary_Id=?",id);
        SalaryTm tm=new SalaryTm();
        if (set.next()){
            tm.setId(set.getString(1));
            tm.setDate(set.getString(5));
            tm.setBonus(set.getString(4));
            tm.setTime(set.getString(6));
            tm.setTotal_salary(set.getString(7));

        }
        return tm;
    }
}
