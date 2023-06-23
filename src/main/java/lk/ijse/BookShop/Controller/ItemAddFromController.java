package lk.ijse.BookShop.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.BookShop.dto.Customer;
import lk.ijse.BookShop.dto.Item;
import lk.ijse.BookShop.model.CustomerModel;
import lk.ijse.BookShop.model.ItemModel;
import lk.ijse.BookShop.util.Navigation;
import lk.ijse.BookShop.util.RegexUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemAddFromController {
    public JFXButton btnDone;
    @FXML
    private JFXTextField txtItemName;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtMType;

    @FXML
    private JFXTextField txtDiscription;

    @FXML
    private JFXTextField txtQty;

    public void btnDonOnAction(ActionEvent actionEvent) {
        try {
            if (ItemModel.add(new Item(
                    id(),
                    txtItemName.getText(),
                    txtMType.getText(),
                    txtDiscription.getText(),
                    Integer.parseInt(txtQty.getText()),
                    Double.valueOf(txtPrice.getText())
            ))) {
                ItemFormController.getInstance().pane.getChildren().clear();
                ItemFormController.getInstance().navigationVisibleTrueController();
                ItemFormController.getInstance().loadDataTable();


                new Alert(Alert.AlertType.CONFIRMATION, "Item Added").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something Wong").show();
            }
        } catch (SQLException throwables) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private String id() {
        try {
            ArrayList<String> allId = ItemModel.getAllId();
            String lastId = null;
            for (int i = 0; i < allId.size(); i++) {
                lastId = allId.get(i);
            }
           try {
                String[] e00s = lastId.split("I00");
                int idIndex = Integer.parseInt(e00s[1]);
                idIndex++;
                System.out.println(idIndex);
                return "I00" + idIndex;
           } catch (Exception e) {
                return "I001";
            }
       } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            }return null;
    }

    public void closeOnMouseCick(MouseEvent mouseEvent) {
        System.out.println("click");
        ItemFormController.getInstance().pane.getChildren().clear();
        ItemFormController.getInstance().navigationVisibleTrueController();

   }

    public void ItemNameKeyReleased(KeyEvent keyEvent) {
        System.out.println("ItemNameKeyReleased");
        RegexUtil.regex(btnDone,txtItemName,txtItemName.getText(),"[a-zA-Z-'`]+[ a-zA-Z-'`]","-fx-text-fill:black");
    }

    public void MatiriyalTypeKeyReleased(KeyEvent keyEvent) {
        System.out.println("MatiriyalTypeKeyReleased");
        RegexUtil.regex(btnDone,txtMType,txtMType.getText(),"[a-zA-Z-'`]+[ a-zA-Z-'`]","-fx-text-fill:black");
    }

    public void DiscriptionKeyReleased(KeyEvent keyEvent) {
        System.out.println("DiscriptionKeyReleased");
        RegexUtil.regex(btnDone,txtDiscription,txtDiscription.getText(),"[a-zA-Z-'`]+[ a-zA-Z-'`]","-fx-text-fill:black");
    }

    public void QtyKeyReleased(KeyEvent keyEvent) {
        System.out.println("QtyKeyReleased");
        RegexUtil.regex(btnDone,txtQty,txtQty.getText(),"[^a-z ]\\ *([.0-9])*\\d","-fx-text-fill:black");
    }

    public void UnitPriceKeyReleased(KeyEvent keyEvent) {
        System.out.println("UnitPriceKeyReleased");
        RegexUtil.regex(btnDone,txtPrice,txtPrice.getText(),"([+-]?[0-9]+(?:\\.[0-9]*)?)","-fx-text-fill:black");
    }
}
