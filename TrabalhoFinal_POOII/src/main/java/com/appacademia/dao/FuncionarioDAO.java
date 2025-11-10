package com.appacademia.dao;

import com.appacademia.Database;
import com.appacademia.model.Usuario;

import java.sql.*;
import java.time.LocalDate;

public class FuncionarioDAO {

    public Usuario insertFuncionario(Usuario u, String senha, String cargo, double salario) throws SQLException {
        String sqlUsuario = """
        INSERT INTO usuario (nome, cpf, email, data_nascimento, tipo_usuario)
        VALUES (?, ?, ?, ?, ?)
        RETURNING id_usuario
    """;

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sqlUsuario)) {

            ps.setString(1, u.getNome());
            ps.setString(2, u.getCpf());
            ps.setString(3, u.getEmail());

            if (u.getDataNascimento() != null)
                ps.setDate(4, Date.valueOf(u.getDataNascimento()));
            else
                ps.setNull(4, Types.DATE);

            ps.setString(5, u.getTipoUsuario());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    u.setId(rs.getInt("id_usuario"));
                }
            }
        }

        String sqlCliente = """
                INSERT INTO funcionario (id_usuario, cargo, salario, senha)
                VALUES (?, ?, ?, ?)
                RETURNING id_usuario
                """;
        try(Connection c = Database.getConnection();
            PreparedStatement ps = c.prepareStatement(sqlCliente)){

            ps.setInt(1, u.getId());
            ps.setString(2, cargo);
            ps.setDouble(3, salario);
            ps.setString(4, senha);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    int id_user = rs.getInt("id_usuario");
                    System.out.println("Funcionario " + id_user + " cadastrado com sucesso");
                }
            }
        }

        return u;
    }
}
