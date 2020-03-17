/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author XOD
 */
public class AboutController implements Initializable {

    //Properties
    private HashMap<String, String> data;

    @FXML
    private Label version_lbl, developer_lbl;

    @FXML
    private JFXButton quit_btn;

    //Methods
    public void initData(HashMap data) {
        this.data = data;
        version_lbl.setText(this.data.get("Version"));
        developer_lbl.setText(this.data.get("Developer"));
    }

    @FXML
    private void quit() {
        Stage stage = (Stage) quit_btn.getScene().getWindow();
        stage.close();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
