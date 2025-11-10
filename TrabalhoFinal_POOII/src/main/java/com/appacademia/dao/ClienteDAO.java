package com.appacademia.dao;

import com.appacademia.Database;
import com.appacademia.model.Usuario;

import java.sql.*;
import java.time.LocalDate;

public class ClienteDAO {

    public Usuario insertCliente(Usuario u, String senha, String Plano) throws SQLException {
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
                INSERT INTO cliente (id_usuario, plano, data_vencimento, status, senha)
                VALUES (?, ?, ?, TRUE, ?)
                RETURNING id_usuario
                """;
        try(Connection c = Database.getConnection();
            PreparedStatement ps = c.prepareStatement(sqlCliente)){

                ps.setInt(1, u.getId());
                ps.setString(2, Plano);
                LocalDate ld = LocalDate.now().plusYears(1);
                ps.setDate(3, java.sql.Date.valueOf(ld));
                ps.setString(4, senha);

                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        int id_user = rs.getInt("id_usuario");
                        System.out.println("CLIENTE " + id_user + " cadastrado com sucesso");
                    }
                }
            }

        return u;
    }
}
