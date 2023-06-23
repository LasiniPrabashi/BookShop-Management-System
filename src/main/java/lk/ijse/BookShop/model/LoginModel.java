package lk.ijse.BookShop.model;

import lk.ijse.BookShop.dto.Login;
import lk.ijse.BookShop.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    public static boolean login(Login login) throws SQLException, ClassNotFoundException {
        return CrudUtil.crudUtil("INSERT INTO admin VALUES(?,?,?,?)",
                login.getUserName(),
                login.getEmp_Id(),
                login.getPassword(),
                login.getRole()
        );
    }


    public static Login get(String user_name, String password) throws SQLException, ClassNotFoundException {
        Login login = new Login();
        ResultSet set = CrudUtil.crudUtil("SELECT * from admin where userName=? and password =?",user_name, password);
        if (set.next()) {
            login.setUserName(set.getString(1));
            login.setEmp_Id(set.getString(2));
            login.setPassword(set.getString(3));
            login.setRole(set.getString(4));

        }
        return login;
    }

    public static String check(Login login) throws SQLException, ClassNotFoundException {
        ResultSet set=CrudUtil.crudUtil("SELECT role FROM admin WHERE userName=? AND password=?", login.getUserName(), login.getPassword());
        while (set.next()){
            return  set.getString(1);
        }
        return"XXX";
   }
}
