package lk.ijse.BookShop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    private String order_Id;
    private String cust_Id;
    private String date;
    private String time;
    private String grand_total;



}
