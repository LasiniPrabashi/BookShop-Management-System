package lk.ijse.BookShop.model;

import lk.ijse.BookShop.dto.*;
import lk.ijse.BookShop.util.*;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    public static boolean add(Customer customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.crudUtil("INSERT INTO Customer VALUES (?,?,?,?,?,?,?)",
                customer.getCust_Id(),
                customer.getFistName(),
                customer.getLastName(),
                customer.getStreet(),
                customer.getCity(),
                customer.getLane(),
                customer.getContact()
        );
    }

    public static ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.crudUtil("SELECT cust_Id FROM customer ORDER BY LENGTH(Cust_Id),Cust_Id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    public static Customer get(String id) throws SQLException, ClassNotFoundException {
        Customer customer=new Customer();
        ResultSet set = CrudUtil.crudUtil("SELECT * from customer where cust_Id=?", id);
        if (set.next()){
            customer.setCust_Id(set.getString(1));
            customer.setFistName(set.getString(2));
            customer.setLastName(set.getString(3));
            customer.setStreet(set.getString(4));
            customer.setCity(set.getString(5));
            customer.setLane(set.getString(6));
            customer.setContact(set.getString(7));
        }
        return customer;
    }

    public static ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        ArrayList<String>ids=new ArrayList<>();
        ResultSet set=CrudUtil.crudUtil("SELECT Cust_Id from Customer where Cust_Id LIKE ? or fristName LIKE ? or lastName LIKE ?",id+"%",id+"%",id+"%");
        while (set.next()){
            ids.add(set.getString(1));
        }
        return ids ;
    }

    public static boolean remove(String cust_id) throws SQLException, ClassNotFoundException {
        return CrudUtil.crudUtil("DELETE FROM Customer WHERE cust_Id=?",cust_id);
    }

    public static boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.crudUtil("UPDATE Customer SET fristName=?,lastName=?,city=?,lane=?,street=?,contact=? WHERE Cust_Id=?",
                customer.getFistName(),
                customer.getLastName(),
                customer.getCity(),
                customer.getLane(),
                customer.getStreet(),
                customer.getContact(),
                customer.getEmp_Id()

                );
    }


    public static String getCustomerCount() throws SQLException, ClassNotFoundException {
        ResultSet set= CrudUtil.crudUtil("SELECT COUNT(Cust_Id) FROM Customer");
        String count=null;
        if (set.next()){
            count=set.getString(1);
        }
        return count;
    }

}
