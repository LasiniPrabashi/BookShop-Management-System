package lk.ijse.BookShop.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.BookShop.dto.Login;
import lk.ijse.BookShop.model.LoginModel;
import lk.ijse.BookShop.util.Navigation;
import lk.ijse.BookShop.util.RegexUtil;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {


    public TextField Admin_name;
    public TextField Admin_password;


    public void btnStartOnAction(ActionEvent actionEvent) throws IOException {

      Login login = new Login();
       login.setUserName(Admin_name.getText());
       login.setPassword(Admin_password.getText());

       try {
            String role = LoginModel.check(login);

            if (role.equals("Admin 01")){
                Navigation.switchNavigation("dashbord_form.fxml",actionEvent);
            }else {
               new Alert(Alert.AlertType.ERROR, "Invalid username or password ").show();
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
         }
    }


}
