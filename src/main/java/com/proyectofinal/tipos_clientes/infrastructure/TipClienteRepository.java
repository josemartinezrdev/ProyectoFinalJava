package com.proyectofinal.tipos_clientes.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.proyectofinal.tipos_clientes.domain.entity.TipCliente;
import com.proyectofinal.tipos_clientes.domain.service.TipClienteService;

public class TipClienteRepository implements TipClienteService {

    private Connection connection;

    public TipClienteRepository() {
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
    public void createTipCliente(TipCliente tipcliente) {
        try {
            String query = "INSERT INTO tipos_clientes (nombre) VALUES (?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, tipcliente.getNombre());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTipCliente(TipCliente tipcliente) {
        try {
            String query = "UPDATE tipos_clientes SET nombre = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, tipcliente.getNombre());
            ps.setInt(2, tipcliente.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTipCliente(int id) {
        try {
            String query = "DELETE FROM tipos_clientes WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TipCliente> findAllTipClientees() {
        List<TipCliente> tipos_clientes = new ArrayList<>();
        try {
            String query = "SELECT id, nombre FROM tipos_clientes";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                TipCliente tipCliente = new TipCliente(rs.getInt("id"), rs.getString("nombre"));
                tipos_clientes.add(tipCliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tipos_clientes;
    }

    @Override
    public TipCliente findByIdTipCliente(int id) {
        TipCliente tipCliente = new TipCliente();
        try {
            String query = "SELECT id, nombre FROM tipos_clientes WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tipCliente.setId(rs.getInt("id"));
                tipCliente.setNombre(rs.getString("nombre"));
                return tipCliente;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
