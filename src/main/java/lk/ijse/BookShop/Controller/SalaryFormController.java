package lk.ijse.BookShop.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import lk.ijse.BookShop.dto.Employee;
import lk.ijse.BookShop.dto.EmployeeSalaryModel;
import lk.ijse.BookShop.dto.Salary;
import lk.ijse.BookShop.model.EmployeeAttendanceModel;
import lk.ijse.BookShop.model.EmployeeModel;
import lk.ijse.BookShop.tm.SalaryTm;
import lk.ijse.BookShop.util.DateTimeUtil;
import lk.ijse.BookShop.util.RegexUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SalaryFormController implements Initializable {
    public JFXTextField txtSearch;
    public JFXButton btnAdd;
    public TableColumn tblid;
    public TableColumn tblBonus;
    public TableColumn tblDate;
    public TableColumn tblTime;
    public TableColumn tblSalary;
    public TableView tbl;
    public JFXComboBox cmbIds;
    public Text txtName;
    public Text txtAttCount;
    public Text txtSalary;
    public JFXTextField txtDSalary;
    public JFXTextField txtBonus;
    public JFXButton btnDone;
    ObservableList<SalaryTm> list= FXCollections.observableArrayList();

    public void txtsearchKeyReleased(KeyEvent keyEvent) {

    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        try {
            if (EmployeeSalaryModel.add(new Salary(
                    id(),
                    (String) cmbIds.getValue(),
                    txtDSalary.getText(),
                    txtBonus.getText(),
                    DateTimeUtil.dateNow(),
                    DateTimeUtil.timeNow(),
                    txtSalary.getText()

            ))) {
                new Alert(Alert.AlertType.INFORMATION, "Ok").show();
                txtSalary.setText("");
                txtDSalary.setText("");
                txtName.setText("");
                txtAttCount.setText("00.00");
                txtBonus.setText("");

                cmbIds.getItems().clear();
                loadIds();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private String id() {
        try {
            ArrayList<String> allId = EmployeeSalaryModel.getAllId();
            String lastId = null;
            for (int i = 0; i < allId.size(); i++) {
                lastId = allId.get(i);
                System.out.println(allId.get(i));
            }
            try {
                String[] e00s = lastId.split("S00");
                int idIndex = Integer.parseInt(e00s[1]);
                idIndex++;
                System.out.println(idIndex);
                return "S00" + idIndex;
            } catch (Exception e) {
                return "S001";
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void idOnAction(ActionEvent actionEvent) {
        try {
            Employee employee = EmployeeModel.get((String) cmbIds.getValue());
            txtName.setText(employee.getFistName() + " " + employee.getLastName());
            String count = EmployeeAttendanceModel.getEmpAttCount((String) cmbIds.getValue());
            txtAttCount.setText(count);

        } catch (SQLException throwables) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadIds();
        loadTableProperty();
        getAllIdsForLoadTable();
           }

    private void getAllIdsForLoadTable() {
        try {
            ArrayList<String> allId = EmployeeSalaryModel.getAllId();
            for (int i = 0; i < allId.size(); i++) {
                loadData(allId.get(i));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadData(String id) {
        System.out.println(id);
        try {
            SalaryTm data = EmployeeSalaryModel.getData(id);
            System.out.println(data);
            list.add(data);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    private void loadTableProperty() {
        tblid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblBonus.setCellValueFactory(new PropertyValueFactory<>("bonus"));
        tblDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        tblSalary.setCellValueFactory(new PropertyValueFactory<>("total_salary"));
        tbl.setItems(list);

    }

    private void loadIds() {
        try {
            ArrayList<String> ids = EmployeeModel.getAllId();
            ArrayList<String> aIds = EmployeeAttendanceModel.getAllEmpId();
            for (int i = 0; i < ids.size(); i++) {
                for (int j = 0; j < aIds.size(); j++) {
                    if (ids.get(i).equals(aIds.get(j))) {
                        ids.remove(i);
                    }
                }
            }
            cmbIds.getItems().addAll(ids);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void DailySalaryOnkeyReleasd(KeyEvent keyEvent) {
        try {
            if (!txtDSalary.getText().equals("")) {
                double salary = Double.parseDouble(txtDSalary.getText()) * Double.parseDouble(txtAttCount.getText());
                txtSalary.setText(String.valueOf(salary));
            } else {
                txtSalary.setText("0.00");
            }
        } catch (NumberFormatException e) {
        }

    }

    public void BonusKeyReleased(KeyEvent keyEvent) {
        System.out.println("BonusKeyReleased");
        RegexUtil.regex(btnAdd,txtBonus,txtBonus.getText(),"([+-]?[0-9]+(?:\\.[0-9]*)?)","-fx-text-fill:black");
    }

    public void DalySalaryKeyReleased(KeyEvent keyEvent) {
        System.out.println("DalySalaryKeyReleased");
        RegexUtil.regex(btnAdd,txtDSalary,txtDSalary.getText(),"([+-]?[0-9]+(?:\\.[0-9]*)?)","-fx-text-fill:black");
    }
}
