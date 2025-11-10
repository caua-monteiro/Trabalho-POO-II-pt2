package com.appacademia.controller;

import com.appacademia.dao.UsuarioDAO;
import com.appacademia.model.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

public class IncluirUsuarioController {

    @FXML private ComboBox<String> cbTipoUsuario;

    @FXML
    public void initialize() {
        System.out.println("✅ Tela de cadastro de usuário carregada!");


        cbTipoUsuario.getItems().addAll("cliente", "funcionario");
    }

    @FXML
    public void DirecionamentoCadastro(){
        String tipo = cbTipoUsuario.getValue();
        try {
            if (Objects.equals(tipo, "cliente")) {
                fecharJanela();
                abrirTelaCliente();
            } else if (Objects.equals(tipo, "funcionario")) {
                fecharJanela();
                abrirTelaFuncionario();
            }
            else{
                throw new Exception("Tipo nao encontrado");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void abrirTelaCliente(){
        System.out.println("🔸 Clique detectado no botão Adicionar Usuário");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/appacademia/view/cadastroCliente_view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Adicionar Novo Cliente");
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
    private void abrirTelaFuncionario(){
        System.out.println("🔸 Clique detectado no botão Adicionar Usuário");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/appacademia/view/cadastrofuncionario_view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Adicionar Novo Funcionario");
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

    @FXML
    private void fecharJanela() {
        Stage stage = (Stage) cbTipoUsuario.getScene().getWindow();
        stage.close();
    }
}
