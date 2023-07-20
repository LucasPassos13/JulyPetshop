package com.julypetshop.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class dashboardController implements Initializable {

    @FXML
    private Button cliCadastrarBtn;

    @FXML
    private TextField cli_busca;

    @FXML
    private TableView<clienteData> addCliente_tableView;

    @FXML
    private TextField cli_cep;

    @FXML
    private TableColumn<clienteData, String> cli_col_cep;

    @FXML
    private TableColumn<clienteData, String> cli_col_cpf;

    @FXML
    private TableColumn<?, ?> cli_col_id;

    @FXML
    private TableColumn<clienteData, String> cli_col_name;

    @FXML
    private TableColumn<clienteData, String> cli_col_rua;

    @FXML
    private TableColumn<clienteData, String> cli_col_telefone;

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

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public void addClienteCadastrar(){

        String sql = "INSERT INTO tb_cliente (cpf_tb_cliente, telefone_tb_cliente,cep_tb_cliente,rua_tb_cliente,numero_tb_cliente,nome_tb_cliente) VALUES (?,?,?,?,?,?)";

        connect = database.connectDb();

        try{
            if(cli_cpf.getText().isEmpty() || cli_telefone.getText().isEmpty() || cli_name.getText().isEmpty()){

                Alert alert;
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Mensagem de ERRO:");
                alert.setHeaderText(null);
                alert.setContentText("CPF, NOME e TELEFONE são obrigatórios");
                alert.showAndWait();

            }else {

                String checkData = "SELECT cpf_tb_cliente FROM tb_cliente WHERE cpf_tb_cliente = '" + cli_cpf.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if(result.next()){
                    Alert alert;
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Mensagem de ERRO:");
                    alert.setHeaderText(null);
                    alert.setContentText("CPF: " + cli_cpf.getText() + " já existente na base de dados" );
                    alert.showAndWait();

                }else{
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, cli_cpf.getText());
                    prepare.setString(2, cli_telefone.getText());
                    prepare.setString(3, cli_cep.getText());
                    prepare.setString(4, cli_rua.getText());
                    prepare.setString(5, cli_numero.getText());
                    prepare.setString(6, cli_name.getText());

                    prepare.executeUpdate();

                    addClienteShowListData();
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public ObservableList<clienteData> addClienteListData(){

        String sql = "SELECT * FROM tb_cliente";

        ObservableList<clienteData> listData = FXCollections.observableArrayList();

        connect = database.connectDb();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            clienteData cliData;

            while(result.next()){
                cliData = new clienteData(result.getString("nome_tb_cliente")
                        , result.getString("cpf_tb_cliente")
                        , result.getString("telefone_tb_cliente")
                        , result.getString("cep_tb_cliente")
                        , result.getString("rua_tb_cliente")
                        , result.getString("numero_tb_cliente"));

                listData.add(cliData);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<clienteData> addClienteList;
    public void addClienteShowListData(){

        addClienteList = addClienteListData();

        cli_col_name.setCellValueFactory(new PropertyValueFactory<>("cli_name"));
        cli_col_cpf.setCellValueFactory(new PropertyValueFactory<>("cli_cpf"));
        cli_col_telefone.setCellValueFactory(new PropertyValueFactory<>("cli_telefone"));
        cli_col_cep.setCellValueFactory(new PropertyValueFactory<>("cli_cep"));
        cli_col_rua.setCellValueFactory(new PropertyValueFactory<>("cli_rua")); // + "cli_numero"));


        addCliente_tableView.setItems(addClienteList);

    }

    public void addClienteSelect(){
        clienteData cliData = addCliente_tableView.getSelectionModel().getSelectedItem();
        int num = addCliente_tableView.getSelectionModel().getSelectedIndex();

        if((num - 1) < 1){return;}

        cli_name.setText(cliData.getCli_name());
        cli_cpf.setText(cliData.getCli_cpf());
        cli_telefone.setText(cliData.getCli_telefone());
        cli_cep.setText(cliData.getCli_cep());
        cli_rua.setText(cliData.getCli_rua());
    }


    public void switchForm(ActionEvent event){

        if(event.getSource() == menuCadastroCliBtn ){
            cadastroCli_form.setVisible(true);

            addClienteShowListData();
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
        addClienteShowListData();
    }
}
