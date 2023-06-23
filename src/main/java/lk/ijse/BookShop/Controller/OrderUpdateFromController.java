package lk.ijse.BookShop.Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import lk.ijse.BookShop.dto.Customer;
import lk.ijse.BookShop.model.CustomerModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OrderUpdateFromController implements Initializable {

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
            if (CustomerModel.update(new Customer(
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

                new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated").show();
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
            Customer customer=CustomerModel.get(txtSearchId.getText());
            if (customer.getFistName()!=null){
                txtFistName.setText(customer.getFistName());
                txtLastName.setText(customer.getLastName());
                txtcontacNumber.setText(customer.getContact());
                txtCity.setText(customer.getCity());
                txtLane.setText(customer.getLane());
                txtStreet.setText(customer.getStreet());
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
