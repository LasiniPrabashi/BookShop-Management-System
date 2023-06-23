package lk.ijse.BookShop.Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import lk.ijse.BookShop.dto.Supplier;
import lk.ijse.BookShop.model.SupplierModel;

import java.sql.SQLException;


public class SupplierUpdateFormController {

    public JFXTextField txtcompany;
    public JFXTextField txtLocation;
    public JFXTextField txtEamil;
    public JFXTextField txtSearchId;
    public JFXTextField txtContactNumber;

    public void btnDonOnAction(ActionEvent actionEvent) {
        try {
            if (SupplierModel.update(new Supplier(
                    txtSearchId.getText(),
                    txtContactNumber.getText(),
                    txtcompany.getText(),
                    txtEamil.getText(),
                    txtLocation.getText()

            ))) {
                SupplierFormController.getInstance().pane.getChildren().clear();
                SupplierFormController.getInstance().navigationVisibleTrueController();
                SupplierFormController.getInstance().loadDataTable();


                new Alert(Alert.AlertType.INFORMATION, "OK").show();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void SearchOnAction(ActionEvent actionEvent) {
        try {
            Supplier supplier = SupplierModel.get(txtSearchId.getText());
            if (!supplier.getSup_Id().equals(null)) {
                txtcompany.setText(supplier.getCompany());
                txtContactNumber.setText(supplier.getContact());
                txtEamil.setText(supplier.getEmail());
                txtLocation.setText(supplier.getLocation());
                txtSearchId.setEditable(false);
            } else {
                new Alert(Alert.AlertType.WARNING, "something Wong").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void closeOnMouseCick(MouseEvent mouseEvent) {
        SupplierFormController.getInstance().pane.getChildren().clear();
        SupplierFormController.getInstance().navigationVisibleTrueController();

    }
}
