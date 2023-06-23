package lk.ijse.BookShop.Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import lk.ijse.BookShop.dto.*;
import lk.ijse.BookShop.model.CustomerModel;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class CustomerUpdateFromController implements Initializable {

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
                CustomerFormController.getInstance().pane.getChildren().clear();
                CustomerFormController.getInstance().navigationVisibleTrueController();
                CustomerFormController.getInstance().loadDataTable();

                new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something Wong").show();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }


    private void setData() {
        try {
            Customer customer= CustomerModel.get(txtSearchId.getText());
            System.out.println(customer.getFistName());
            System.out.println(customer.getFistName()!=null);
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
        CustomerFormController.getInstance().pane.getChildren().clear();
        CustomerFormController.getInstance().navigationVisibleTrueController();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
