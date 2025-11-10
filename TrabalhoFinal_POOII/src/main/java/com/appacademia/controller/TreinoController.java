package com.appacademia.controller;

import com.appacademia.dao.TreinoDAO;
import com.appacademia.dao.UsuarioDAO;
import com.appacademia.model.Treino;
import com.appacademia.model.Usuario;
import com.appacademia.util.CloudinaryStorageService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;

public class TreinoController {

    @FXML private TableView<Treino> tableTreinos;
    @FXML private TableColumn<Treino, String> colTipo;
    @FXML private TableColumn<Treino, String> colUsuario;
    @FXML private Button btnNovoTreino;
    @FXML private Button btnVisualizar;
    @FXML private Button btnExcluir;

    private final TreinoDAO treinoDAO = new TreinoDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final ObservableList<Treino> list = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colTipo.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getTipoTreino()));
        colUsuario.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(String.valueOf(c.getValue().getIdUsuario())));
        loadTreinos();
    }

    private void loadTreinos() {
        list.clear();
        try {
            // ⚠️ Aqui deve haver um método que busque todos os treinos, se não existir, crie no DAO
            List<Treino> l = treinoDAO.findAllByUsuario(1); // exemplo: buscar por um usuário padrão
            list.addAll(l);
            tableTreinos.setItems(list);
        } catch (SQLException e) {
            showError(e);
        }
    }

    @FXML
    private void onNovoTreino() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File f = fc.showOpenDialog(tableTreinos.getScene().getWindow());
        if (f == null) return;
        CloudinaryStorageService cloudinaryStorageService = new CloudinaryStorageService();

        TextInputDialog td = new TextInputDialog();
        td.setTitle("Novo Treino");
        td.setHeaderText("Digite o ID do usuário que receberá o treino");
        td.setContentText("ID usuário:");
        td.showAndWait().ifPresent(idStr -> {
            try {
                int idUser = Integer.parseInt(idStr);
                Treino t = new Treino();
                t.setIdUsuario(idUser);
                t.setTipoTreino("Personalizado");
                t.setObservacoes("Enviado pelo app");
                try{
                    t.setPdfTreino(cloudinaryStorageService.uploadFiles(f));
                }
                catch (Exception e){
                    showError(new Exception("Erro ao fazer upload " + e.getMessage()));
                    return;
                }
                treinoDAO.insert(t);
                loadTreinos();
            } catch (Exception e) {
                showError(e);
            }
        });
    }

    @FXML
    private void onVisualizar() {
        Treino sel = tableTreinos.getSelectionModel().getSelectedItem();
        if (sel == null) {
            new Alert(Alert.AlertType.WARNING, "Selecione um treino.").showAndWait();
            return;
        }
        System.out.println("treino selecionado");
        System.out.println(sel);
        try{
            String urlPDF = sel.getPdfTreino();

            System.out.println(urlPDF);

            if (urlPDF == null || urlPDF.isEmpty()){
                new Alert(Alert.AlertType.WARNING, "PDF nao encontrado(URL vazio)").showAndWait();
                return;
            }
            System.out.println("Pre-uso navegador");
            try{
                Desktop.getDesktop().browse(new URI(urlPDF));
            }
            catch (Exception e){
                showError(new Exception("Erro ao abrir o navegador " + e.getMessage()));
            }
            System.out.printf("Pos-uso navegador");
        } catch (Exception e) {
            showError(e);
        }
    }

    @FXML
    private void onExcluir() {
        Treino sel = tableTreinos.getSelectionModel().getSelectedItem();
        if (sel == null) {
            new Alert(Alert.AlertType.WARNING, "Selecione um treino.").showAndWait();
            return;
        }

        Alert c = new Alert(Alert.AlertType.CONFIRMATION, "Excluir treino?", ButtonType.YES, ButtonType.NO);
        c.showAndWait().ifPresent(b -> {
            if (b == ButtonType.YES) {
                try {
                    treinoDAO.deleteById(sel.getId());
                    loadTreinos();
                } catch (SQLException e) {
                    showError(e);
                }
            }
        });
    }

    private void showError(Exception e) {
        e.printStackTrace();
        new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
    }
}
