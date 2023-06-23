package lk.ijse.BookShop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Login {
    private String UserName;
    private String Emp_Id;
    private String password;
    private String role;
}
