package com.julypetshop.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class dashboardController implements Initializable {

    @FXML
    private Button cliCadastrarBtn;

    @FXML
    private TextField cli_cep;

    @FXML
    private TableColumn<?, ?> cli_col_cep;

    @FXML
    private TableColumn<?, ?> cli_col_cpf;

    @FXML
    private TableColumn<?, ?> cli_col_id;

    @FXML
    private TableColumn<?, ?> cli_col_name;

    @FXML
    private TableColumn<?, ?> cli_col_rua;

    @FXML
    private TableColumn<?, ?> cli_col_telefone;

    @FXML
    private TextField cli_cpf;

    @FXML
    private TextField cli_name;

    @FXML
    private TextField cli_numero;

    @FXML
    private TextField cli_rua;

    @FXML
    private TextField cli_telefone;

    @FXML
    private StackPane main_form;

    @FXML
    private Button menuCadastroCliBtn;

    @FXML
    private Button menuAgendaBtn;

    @FXML
    private Button menuCadastroPetBtn;

    @FXML
    private Button menuConfigBtn;

    @FXML
    private AnchorPane cadastroCli_form;

    public void switchForm(ActionEvent event){

        if(event.getSource() == menuCadastroCliBtn ){
            cadastroCli_form.setVisible(true);
        } else if (event.getSource() == menuCadastroPetBtn) {
            cadastroCli_form.setVisible(false);
        } else if (event.getSource() == menuAgendaBtn) {

        }else if (event.getSource() ==  menuConfigBtn){

        }
    }


    public void limpaForm(){

        cli_cpf.setText("");
        cli_telefone.setText("");
        cli_rua.setText("");
        cli_name.setText("");
        cli_numero.setText("");
        cli_cep.setText("");

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
