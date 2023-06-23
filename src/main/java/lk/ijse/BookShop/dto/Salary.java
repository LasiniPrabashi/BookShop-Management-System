package lk.ijse.BookShop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Salary {
    private String salary_Id;
    private String Emp_Id;
    private String daily_salary;
    private String bonus;
    private String date;
    private String time;
    private String total_salary;

}
