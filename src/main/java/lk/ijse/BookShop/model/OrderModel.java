package lk.ijse.BookShop.model;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import lk.ijse.BookShop.db.DBConnection;
import lk.ijse.BookShop.dto.Customer;
import lk.ijse.BookShop.dto.Order;
import lk.ijse.BookShop.tm.OderItemTm;
import lk.ijse.BookShop.tm.OrderTm;
import lk.ijse.BookShop.util.CrudUtil;
import lk.ijse.BookShop.util.DateTimeUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderModel {
    public static boolean placeOrder(ArrayList<OderItemTm> list, Customer customer, Order order) throws SQLException {
        Connection connection = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            if (OrderModel.addOrder(order)) {
                if (OrderDetailsModel.add(list, order)) {
                    if (ItemModel.itemUpdate(list)) {
                        return true;
                    } else {
                        connection.rollback();
                    }
                } else {
                    connection.rollback();
                }
            } else {
                connection.rollback();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        } finally {
            connection.commit();
            connection.setAutoCommit(true);
        }
        return false;
    }

    static boolean addOrder(Order order) throws SQLException, ClassNotFoundException {
        System.out.println(order.getTime());
        return CrudUtil.crudUtil("INSERT INTO `order` VALUES (?,?,?,?,?)",
                order.getOrder_Id(),
                order.getCust_Id(),
                order.getDate(),
                order.getTime(),
                order.getGrand_total()
        );

    }

    public static ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ArrayList<String> id = new ArrayList<>();
        ResultSet set = CrudUtil.crudUtil("SELECT order_Id FROM `order` ORDER BY LENGTH(order_Id),order_Id");
        while (set.next()) {
            id.add(set.getString(1));
        }
        return id;
    }

    public static OrderTm get(String id) throws SQLException, ClassNotFoundException {
        OrderTm tm=new OrderTm();
       ResultSet set= CrudUtil.crudUtil("SELECT o.order_Id,c.cust_Id,o.date,o.time,o.grand_total FROM `order` o INNER JOIN customer c on o.cust_Id = c.cust_Id WHERE order_Id=?",id);

        while (set.next()){
            tm.setId(set.getString(1));
            tm.setCus_id(set.getString(2));
            tm.setDate(set.getString(3));
            tm.setTime(set.getString(4));
            tm.setTotal(set.getString(5));
        }


        Button button2 = new Button();
        button2.setText("View");
        button2.setStyle("   -fx-text-fill:#ffff ;\n" +
                "   -fx-font-weight: bold;\n" +
                "   -fx-font-size: 15;\n" +
                "   -fx-background-color: #03DE5A;\n" +
                "   -fx-background-radius: 20;");
        button2.setOnAction(actionEvent -> {
            System.out.println("click2");
        });
        tm.setView(button2);

        return tm;
    }

    public static String getOrderCount() throws SQLException, ClassNotFoundException {
        ResultSet set= CrudUtil.crudUtil("SELECT COUNT(Order_Id) FROM `order`");
        String count=null;
        if (set.next()){
            count=set.getString(1);
        }
        return count;
    }


    public static ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        ArrayList<String>ids=new ArrayList<>();
        ResultSet set=CrudUtil.crudUtil("SELECT order_Id from `order` where `order`.order_Id LIKE ? or `order`.date LIKE ? or `order`.grand_total LIKE ?",id+"%",id+"%",id+"%");
        while (set.next()){
            ids.add(set.getString(1));
        }
        return ids ;
    }

    public static String getCustomerOrder(int i) throws SQLException, ClassNotFoundException {
        i++;
        String dateNow = DateTimeUtil.dateNow();
        String[] date = dateNow.split("-");
        String currentDate=null;
        if (String.valueOf(i).length()==1){
            currentDate="0"+i;
        }else {
            currentDate= String.valueOf(i);
        }
        System.out.println(date[0]+"-"+date[1]+"-"+currentDate);
        ResultSet set=CrudUtil.crudUtil("SELECT grand_total FROM `order` WHERE date=?;",date[0]+"-"+date[1]+"-"+currentDate);
          double t=0;
        while (set.next()){
            t+=set.getDouble(1);
        }
   return String.valueOf(t);
    }
}
