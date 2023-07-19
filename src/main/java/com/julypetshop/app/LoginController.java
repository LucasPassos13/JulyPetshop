package com.julypetshop.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {
    @FXML
    private Button cancelBtn;

    @FXML
    private Button loginBtn;

    @FXML
    private BorderPane main_form;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    private PreparedStatement prepare;
    private Connection connect;
    private ResultSet result;


    public void loginAdmin(){
        String sql = "SELECT * FROM tb_admin WHERE username_tb_admin = ? and password_tb_admin = ?";

        connect = database.connectDb();

        try{
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2,password.getText());

            result = prepare.executeQuery();

            Alert alert;

            if(username.getText().isEmpty() || password.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Por favor, preencha todos os campos.");
                alert.showAndWait();
            }else{
                if (result.next()){

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Login efeituado com sucesso.");
                    alert.showAndWait();

                    loginBtn.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.show();


                }else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Username/Password incorretos.");
                    alert.showAndWait();
                }
            }


        }catch(Exception e ){
            e.printStackTrace();
        }

    }

    public void close(){
        System.exit(0);
    }

}