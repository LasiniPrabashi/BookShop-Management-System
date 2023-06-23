package lk.ijse.BookShop.Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import lk.ijse.BookShop.dto.Customer;
import lk.ijse.BookShop.dto.Item;
import lk.ijse.BookShop.model.CustomerModel;
import lk.ijse.BookShop.model.ItemModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemUpdateFromController implements Initializable {

    public JFXTextField txtItemName;
    public JFXTextField txtMType;

    public JFXTextField txtUnitPrice;

    public JFXTextField txtDiscription;
    public JFXTextField txtQOH;
    public JFXTextField txtSearchId;


    @FXML
    void btnDonOnAction(ActionEvent event) {
        try {
            if (ItemModel.update(new Item(
                    txtSearchId.getText(),
                    txtItemName.getText(),
                    txtMType.getText(),
                    txtDiscription.getText(),
                    Integer.parseInt(txtQOH.getText()),
                    Double.parseDouble(txtUnitPrice.getText())

            ))) {
                ItemFormController.getInstance().pane.getChildren().clear();
                ItemFormController.getInstance().navigationVisibleTrueController();
                ItemFormController.getInstance().loadDataTable();

                new Alert(Alert.AlertType.CONFIRMATION, "Item Updated").show();
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
           Item item = ItemModel.get(txtSearchId.getText());
            if (item.getItemName() != null) {
                txtItemName.setText(item.getItemName());
                txtMType.setText(item.getMatiriyalType());
                txtDiscription.setText(item.getDiscription());
                txtQOH.setText(String.valueOf(item.getQtyOnHand()));
                txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
                txtSearchId.setEditable(false);
            } else {
                txtSearchId.setText("");
                new Alert(Alert.AlertType.WARNING, "enter Correct Id").show();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }


    }

    public void SearchOnAction(ActionEvent actionEvent) {
        setData();
    }

    public void closeOnMouseCick(MouseEvent mouseEvent) {
        ItemFormController.getInstance().pane.getChildren().clear();
        ItemFormController.getInstance().navigationVisibleTrueController();

    }
}
