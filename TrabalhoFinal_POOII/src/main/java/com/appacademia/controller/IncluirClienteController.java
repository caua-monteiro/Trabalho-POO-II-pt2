package com.appacademia.controller;

import com.appacademia.dao.ClienteDAO;
import com.appacademia.dao.UsuarioDAO;
import com.appacademia.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;

public class IncluirClienteController {

    private final ClienteDAO clienteDAO = new ClienteDAO();
    @FXML private TextField txtNome;
    @FXML private TextField txtCpf;
    @FXML private TextField txtEmail;
    @FXML private DatePicker dpNascimento;
    @FXML private PasswordField txtSenha;
    @FXML private TextField txtPlano;


    @FXML
    public void initialize() {
        System.out.println("✅ Tela de cadastro de usuário carregada!");
    }

    @FXML
    private void salvarCliente() {
        try {
            String nome = txtNome.getText().trim();
            String cpf = txtCpf.getText().trim();
            String email = txtEmail.getText().trim();
            LocalDate data = dpNascimento.getValue();
            String tipo = "cliente";
            String senha = txtSenha.getText().trim();
            String plano = txtPlano.getText().trim();


            if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || senha.isEmpty() || plano.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Preencha todos os campos obrigatórios!").showAndWait();
                return;
            }

            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setCpf(cpf);
            usuario.setEmail(email);
            usuario.setDataNascimento(data);
            usuario.setTipoUsuario(tipo);

            clienteDAO.insertCliente(usuario, senha, plano);

            new Alert(Alert.AlertType.INFORMATION, "Usuário cadastrado com sucesso!").showAndWait();
            fecharJanela();

        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Erro ao salvar usuário: " + e.getMessage()).showAndWait();
        }
    }

    @FXML
    private void fecharJanela() {
        Stage stage = (Stage) txtNome.getScene().getWindow();
        stage.close();
    }
}
