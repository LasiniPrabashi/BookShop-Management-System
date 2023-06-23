package lk.ijse.BookShop.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import lk.ijse.BookShop.db.DBConnection;
import lk.ijse.BookShop.dto.Employee;
import lk.ijse.BookShop.dto.EmployeeAttendance;
import lk.ijse.BookShop.model.EmployeeAttendanceModel;
import lk.ijse.BookShop.model.EmployeeModel;
import lk.ijse.BookShop.tm.EmployeeAttendanceTm;
import lk.ijse.BookShop.util.DateTimeUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AttendanceFormController implements Initializable {
    public JFXTextField txtSearch;
    public JFXButton btnAdd;

    public TableView tbl;
    public TableColumn tblid;
    public TableColumn tblfName;
    public TableColumn tbllName;
    public TableColumn tblcity;
    public TableColumn tblcontactNumber;

    public JFXComboBox cmbEmpIds;
    public Text txtName;
    public JFXTextField txtHoue;
    public Text txtAllEmp;
    public Text txtEmpAttendanceCount;

    ObservableList<EmployeeAttendanceTm> list = FXCollections.observableArrayList();

    public void txtsearchKeyReleased(KeyEvent keyEvent) {

        try {
            list.clear();
            tbl.getItems().clear();
            ArrayList<String> id = EmployeeAttendanceModel.getSearchIds(txtSearch.getText());

            for (int i = 0; i < id.size(); i++) {
                setEmployeeData(id.get(i));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    private void setEmployeeData(String id) {

        System.out.println("");

        try {
            EmployeeAttendanceTm tm =  EmployeeAttendanceModel.get(id);
            System.out.println(tm.toString());
            list.add(tm);
        } catch (SQLException throwables) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void setAllId(){
        try {
           ArrayList<String>ids=EmployeeAttendanceModel.getTodayAllId();
            for (int i = 0; i < ids.size(); i++) {
                setEmployeeData(ids.get(i));
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        try {
            if (EmployeeAttendanceModel.add(new EmployeeAttendance(
                    id(),
                    (String) cmbEmpIds.getValue(),
                    txtHoue.getText(),
                    DateTimeUtil.dateNow()

            ))) {
                setAllId();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private String id() {
        try {
            ArrayList<String> allId = EmployeeAttendanceModel.getAllId();
            String lastId = null;
            for (int i = 0; i < allId.size(); i++) {
                lastId = allId.get(i);
                System.out.println(allId.get(i));
            }
            try {
                String[] e00s = lastId.split("A00");
                int idIndex = Integer.parseInt(e00s[1]);
                idIndex++;
                System.out.println(idIndex);
                return "A00" + idIndex;
            } catch (Exception e) {
                return "A001";
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public void cmbEmpId(ActionEvent actionEvent) {

        try {
            Employee employee = EmployeeModel.get(String.valueOf(cmbEmpIds.getValue()));
            txtName.setText(employee.getFistName() + " " + employee.getLastName());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAllEmployeeIds();
        setTableProperty();
        setAllId();
        setAttendanceCount();
        setEmpCount();
    }

    private void setEmpCount() {
        try {
            txtAllEmp.setText(EmployeeAttendanceModel.getEmpCount());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setAttendanceCount() {
        try {
            txtEmpAttendanceCount.setText(EmployeeAttendanceModel.getAttendanceCount());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setTableProperty() {

        tblid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblfName.setCellValueFactory(new PropertyValueFactory<>("fist_name"));
        tbllName.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        tblcity.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcontactNumber.setCellValueFactory(new PropertyValueFactory<>("time"));
        tbl.setItems(list);
    }

    private void setAllEmployeeIds() {
        try {

            ArrayList<String> allId = EmployeeModel.getAllId();
            cmbEmpIds.getItems().addAll(allId);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btnReportOnAction(ActionEvent actionEvent) {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\hp\\Desktop\\BookShop\\src\\main\\resources\\report\\EmployeeAttendance.jrxml");
            JRDataSource jrDataSource = new JRBeanCollectionDataSource(Arrays.asList(new Object()));

            HashMap hm = new HashMap();


            Connection connection = DBConnection.getInstance().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hm, connection);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}

