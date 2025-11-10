package com.appacademia.controller;

import com.appacademia.dao.UsuarioDAO;
import com.appacademia.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UsuariosController {

    @FXML private TableView<Usuario> tabelaUsuarios;
    @FXML private TableColumn<?, ?> colNome;
    @FXML private TableColumn<?, ?> colEmail;
    @FXML private TableColumn<?, ?> colTelefone;
    @FXML private TextField txtPesquisa;
    @FXML private Button btnAdicionarUsuario;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    private final ObservableList<Usuario> list = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        System.out.println("✅ Tela de usuários carregada com sucesso!");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        loadUsuario();
    }

    @FXML
    private void abrirTelaAdicionarUsuario() {
        System.out.println("🔸 Clique detectado no botão Adicionar Usuário");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/appacademia/view/TipoCadastro_view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Adicionar Novo Usuário");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();

            System.out.println("🟢 Tela de cadastro de usuário aberta!");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("❌ Erro ao abrir a tela de cadastro de usuário: " + e.getMessage());
        }
    }

    public void loadUsuario(){
        list.clear();
        try {
            List<Usuario> l = usuarioDAO.findAll(); // exemplo: buscar por um usuário padrão
            list.addAll(l);
            tabelaUsuarios.setItems(list);
        } catch (SQLException e) {
            showError(new Exception("Erro ao carregar usuarios;  " + e.getMessage()));
        }
    }

    public void BuscaUsuario(){
        list.clear();
        String nomePesquisa = txtPesquisa.getText().trim();
        System.out.println(nomePesquisa);
        try{
            List<Usuario> l = usuarioDAO.findByNome(nomePesquisa);
            list.addAll(l);
            tabelaUsuarios.setItems(list);
        } catch (SQLException e) {
            showError(new Exception("Erro ao carregar usuarios:  " + e.getMessage()));
        }
    }

    private void showError(Exception e) {
        e.printStackTrace();
        new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
    }
}
