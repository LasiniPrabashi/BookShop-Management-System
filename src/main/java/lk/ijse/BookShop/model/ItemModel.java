package lk.ijse.BookShop.model;

import lk.ijse.BookShop.dto.Customer;
import lk.ijse.BookShop.dto.Item;
import lk.ijse.BookShop.tm.OderItemTm;
import lk.ijse.BookShop.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModel {
    public static boolean add(Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.crudUtil("INSERT INTO item VALUES (?,?,?,?,?,?)",
                item.getItem_Id(),
                item.getItemName(),
                item.getMatiriyalType(),
                item.getDiscription(),
                item.getQtyOnHand(),
                item.getUnitPrice()
        );
    }
    public static ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.crudUtil("SELECT item_Id FROM item ORDER BY LENGTH(item_Id),item_Id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;

    }

    public static Item get(String id) throws SQLException, ClassNotFoundException {
        Item item=new Item();
         ResultSet resultSet = CrudUtil.crudUtil("SELECT * From item WHERE item_Id=?", id);
         while (resultSet.next()){
           item.setItem_Id(resultSet.getString(1));
           item.setItemName(resultSet.getString(2));
           item.setMatiriyalType(resultSet.getString(3));
           item.setDiscription(resultSet.getString(4));
           item.setQtyOnHand(Integer.parseInt(resultSet.getString(5))); ;
           item.setUnitPrice(Double.parseDouble(resultSet.getString(6)));

         }
       return item;
    }


    public static boolean remove(String item_Id) throws SQLException, ClassNotFoundException {
        return CrudUtil.crudUtil("DELETE FROM item WHERE item_Id=? ",item_Id);
    }

    public static boolean update(Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.crudUtil("UPDATE item SET itemName=?,MatiriyalType=?,discription=?,QtyOnHand=?,UnitPrice=? WHERE item_Id=?",
                item.getItemName(),
                item.getMatiriyalType(),
                item.getDiscription(),
                item.getQtyOnHand(),
                item.getUnitPrice(),
                item.getItem_Id()
);
    }

    public static boolean itemUpdate(ArrayList<OderItemTm> list) throws SQLException, ClassNotFoundException {
        boolean updated=true;
        for (int i = 0; i < list.size(); i++) {
            if (CrudUtil.crudUtil("UPDATE item SET QtyOnHand=QtyOnHand-? WHERE item_Id=?",
                    list.get(i).getQty(),
                    list.get(i).getId()
                    )){

            }else {
                return false;
            }

        }
        return updated;
    }

    public static ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        ArrayList<String>ids=new ArrayList<>();
        ResultSet set=CrudUtil.crudUtil("SELECT item_Id from item where item.item_Id LIKE ? or item.itemName LIKE ? or item.MatiriyalType LIKE ?",id+"%",id+"%",id+"%");
            while (set.next()){
            ids.add(set.getString(1));
        }
        return ids ;
    }

    public static String getItemCount() throws SQLException, ClassNotFoundException {
        ResultSet set= CrudUtil.crudUtil("SELECT COUNT(Cust_Id) FROM Customer");
        String count=null;
        if (set.next()){
            count=set.getString(1);
        }
        return count;
    }
}
