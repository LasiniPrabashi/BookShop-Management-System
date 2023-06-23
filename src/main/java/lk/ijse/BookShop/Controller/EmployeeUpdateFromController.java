package lk.ijse.BookShop.Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import lk.ijse.BookShop.dto.Employee;
import lk.ijse.BookShop.model.EmployeeModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeUpdateFromController implements Initializable {

    @FXML
    private JFXTextField txtFistName;

    @FXML
    private JFXTextField txtLastName;

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXTextField txtStreet;

    @FXML
    private JFXTextField txtLane;

    @FXML
    private JFXTextField txtcontacNumber;

    @FXML
    private JFXTextField txtSearchId;

    @FXML
    void btnDonOnAction(ActionEvent event) {
        try {
            if (EmployeeModel.update(new Employee(
                    txtSearchId.getText(),
                    txtFistName.getText(),
                    txtLastName.getText(),
                    txtStreet.getText(),
                    txtCity.getText(),
                    txtLane.getText(),
                    txtcontacNumber.getText()

            ))) {
                EmployeeFormController.getInstance().pane.getChildren().clear();
                EmployeeFormController.getInstance().btnUpdate.setVisible(true);
                EmployeeFormController.getInstance().btnAdd.setVisible(true);
                EmployeeFormController.getInstance().pane2.setVisible(true);
                EmployeeFormController.getInstance().pane1.setVisible(true);
                EmployeeFormController.getInstance().loadDataTable();

                new Alert(Alert.AlertType.CONFIRMATION, "Employee Updated").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something Wong").show();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void setData() {
        try {
            Employee employee=EmployeeModel.get(txtSearchId.getText());
            if (employee.getFistName()!=null){
                txtFistName.setText(employee.getFistName());
                txtLastName.setText(employee.getLastName());
                txtcontacNumber.setText(employee.getContact());
                txtCity.setText(employee.getCity());
                txtLane.setText(employee.getLane());
                txtStreet.setText(employee.getStreet());
                txtSearchId.setEditable(false);
            }else {
                txtSearchId.setText("");
                new Alert(Alert.AlertType.WARNING,"enter Correct Id").show();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }


    }

    public void SearchOnAction(ActionEvent actionEvent) {
        setData();
    }

    public void closeOnMouseCick(MouseEvent mouseEvent) {
        EmployeeFormController.getInstance().pane.getChildren().clear();
        EmployeeFormController.getInstance().btnUpdate.setVisible(true);
        EmployeeFormController.getInstance().btnAdd.setVisible(true);
        EmployeeFormController.getInstance().pane2.setVisible(true);
        EmployeeFormController.getInstance().pane1.setVisible(true);
    }
}
