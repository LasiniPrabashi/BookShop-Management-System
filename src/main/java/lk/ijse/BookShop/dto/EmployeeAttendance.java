package lk.ijse.BookShop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeAttendance {
    private String attendance_Id;
    private String Emp_Id;
    private String workingHourse;
    private String date;

}
