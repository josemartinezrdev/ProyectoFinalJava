package com.proyectofinal.estados.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.proyectofinal.estados.domain.entity.Estado;
import com.proyectofinal.estados.domain.service.EstadoService;

public class EstadoRepository implements EstadoService {

    private Connection connection;

    public EstadoRepository() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");

            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createEstado(Estado estado) {
        try {
            String query = "INSERT INTO estados (nombre) VALUES (?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, estado.getNombre());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateEstado(Estado estado) {
        try {
            String query = "UPDATE estados SET nombre = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, estado.getNombre());
            ps.setInt(2, estado.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEstado(int id) {
        try {
            String query = "DELETE FROM estados WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Estado> findAllEstadoes() {
        List<Estado> estados = new ArrayList<>();
        try {
            String query = "SELECT id, nombre FROM estados";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Estado estado = new Estado(rs.getInt("id"), rs.getString("nombre"));
                estados.add(estado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estados;
    }

    @Override
    public Estado findByIdEstado(int id) {
        Estado estado = new Estado();
        try {
            String query = "SELECT id, nombre FROM estados WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                estado.setId(rs.getInt("id"));
                estado.setNombre(rs.getString("nombre"));
                return estado;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
