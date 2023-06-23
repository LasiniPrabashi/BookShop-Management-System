package lk.ijse.BookShop.model;

import lk.ijse.BookShop.dto.Order;
import lk.ijse.BookShop.tm.OderItemTm;
import lk.ijse.BookShop.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsModel {
    public static boolean add(ArrayList<OderItemTm> list, Order order) throws SQLException, ClassNotFoundException {
       boolean added =true;
        for (int i = 0; i < list.size(); i++) {
         if (CrudUtil.crudUtil("INSERT INTO orderdetail VALUES (?,?,?)",
                 list.get(i).getId(),
                 order.getOrder_Id(),
                 list.get(i).getQty()
         )){
             added=true;
         }else {
             return false;
         }
        }
        return added;
    }
}
