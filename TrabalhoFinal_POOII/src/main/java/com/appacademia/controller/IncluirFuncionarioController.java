package com.appacademia.controller;

import com.appacademia.dao.ClienteDAO;
import com.appacademia.dao.FuncionarioDAO;
import com.appacademia.dao.UsuarioDAO;
import com.appacademia.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.function.UnaryOperator;

public class IncluirFuncionarioController {

    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    @FXML private TextField txtNome;
    @FXML private TextField txtCpf;
    @FXML private TextField txtEmail;
    @FXML private DatePicker dpNascimento;
    @FXML private PasswordField txtSenha;
    @FXML private TextField txtSalario;
    @FXML private TextField txtCargo;



    @FXML
    public void initialize() {
        System.out.println("✅ Tela de cadastro de usuário carregada!");

        String regex = "^\\d*[\\.,]?\\d*$";

        UnaryOperator<TextFormatter.Change> filter = change -> {
          String newText = change.getControlNewText();
          if(newText.matches(regex)){
              return change;
          }
          return null;
        };

        TextFormatter<String> formatter = new TextFormatter<>(filter);
        txtSalario.setTextFormatter(formatter);
    }

    @FXML
    private void salvarFuncionario() {
        try {
            String nome = txtNome.getText().trim();
            String cpf = txtCpf.getText().trim();
            String email = txtEmail.getText().trim();
            LocalDate data = dpNascimento.getValue();
            String tipo = "funcionario";
            String senha = txtSenha.getText().trim();
            String cargo = txtCargo.getText().trim();
            Double salario = SalarioToDouble(txtSalario.getText().trim());


            if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || senha.isEmpty() || cargo.isEmpty() || salario.isNaN()) {
                new Alert(Alert.AlertType.WARNING, "Preencha todos os campos obrigatórios!").showAndWait();
                return;
            }

            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setCpf(cpf);
            usuario.setEmail(email);
            usuario.setDataNascimento(data);
            usuario.setTipoUsuario(tipo);

            funcionarioDAO.insertFuncionario(usuario, senha, cargo, salario);

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

    private Double SalarioToDouble(String salario){
        double SalarioFinal = 0;
        try {
            String salarioParseDouble = salario.replace(",", ".");
            SalarioFinal = Double.parseDouble(salarioParseDouble);
            if (SalarioFinal < 0) {
                showError(new Exception("Salario invalido, verifique o valor"));
                throw new Exception();
            }

        } catch (Exception e) {
            showError(new Exception("Salario invalido, verifique o valor;  " + e.getMessage()));
        }
        return SalarioFinal;
    }

    private void showError(Exception e) {
        e.printStackTrace();
        new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
    }
}
