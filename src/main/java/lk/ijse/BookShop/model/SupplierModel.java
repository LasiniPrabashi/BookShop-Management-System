package lk.ijse.BookShop.model;

import lk.ijse.BookShop.dto.Employee;
import lk.ijse.BookShop.dto.Supplier;
import lk.ijse.BookShop.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierModel {
    public static boolean add(Supplier supplier) throws SQLException, ClassNotFoundException {
        return CrudUtil.crudUtil("INSERT INTO Supplier VALUES (?,?,?,?,?)",
                supplier.getSup_Id(),
                supplier.getContact(),
                supplier.getCompany(),
                supplier.getEmail(),
                supplier.getLocation()
        );
    }

    public static ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.crudUtil("SELECT sup_Id FROM supplier ORDER BY LENGTH(sup_Id),sup_Id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    public static Supplier get(String id) throws SQLException, ClassNotFoundException {
       Supplier supplier=new Supplier();
        ResultSet set = CrudUtil.crudUtil("SELECT * from Supplier where Sup_Id=?", id);
        if (set.next()){
            supplier.setSup_Id(set.getString(1));
            supplier.setContact(set.getString(2));
            supplier.setCompany(set.getString(3));
            supplier.setEmail(set.getString(4));
            supplier.setLocation(set.getString(5));
        }
        return supplier;
    }

    public static ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        ArrayList<String>ids=new ArrayList<>();
        ResultSet set=CrudUtil.crudUtil("SELECT sup_Id  from supplier where Supplier.sup_Id LIKE ? or  Supplier.company  LIKE ? or location LIKE ?",id+"%",id+"%",id+"%");
        while (set.next()){
            ids.add(set.getString(1));
        }
        return ids ;
    }

    public static boolean remove(String sup_Id) throws SQLException, ClassNotFoundException {
        return CrudUtil.crudUtil("DELETE FROM Supplier WHERE Sup_Id=?",sup_Id);
    }

    public static boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {
        return CrudUtil.crudUtil("UPDATE Supplier SET contact=?,company=?,supplier.email=?,supplier.location=? WHERE sup_Id=?",
                supplier.getContact(),
                supplier.getCompany(),
                supplier.getEmail(),
                supplier.getLocation(),
                supplier.getSup_Id()
        );
    }


    public static String getSupplierCount() throws SQLException, ClassNotFoundException {
        ResultSet set= CrudUtil.crudUtil("SELECT COUNT(Sup_Id) FROM supplier");
        String count=null;
        if (set.next()){
            count=set.getString(1);
        }
        return count;
    }

}
