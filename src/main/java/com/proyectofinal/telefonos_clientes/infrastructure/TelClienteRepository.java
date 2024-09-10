package com.proyectofinal.telefonos_clientes.infrastructure;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.proyectofinal.telefonos_clientes.domain.entity.TelCliente;
import com.proyectofinal.telefonos_clientes.domain.service.TelClienteService;

public class TelClienteRepository implements TelClienteService {

    private Connection connection;

    public TelClienteRepository() {
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
    public void createProducto(TelCliente telcliente) {
        try {
            String query = "INSERT INTO telefonos_clientes (telefono, idcliente) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, telcliente.getTelefono());
            ps.setString(2, telcliente.getIdcliente());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProducto(TelCliente telcliente) {
        try {
            String query = "UPDATE telefonos_clientes SET telefono = ?, idcliente = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, telcliente.getTelefono());
            ps.setString(2, telcliente.getIdcliente());
            ps.setInt(3, telcliente.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProducto(int id) {
        try {
            String query = "DELETE FROM telefonos_clientes WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TelCliente> findAllProductos() {
        List<TelCliente> telefonos_clientes = new ArrayList<>();
        try {
            String query = "SELECT id, telefono, idcliente FROM telefonos_clientes";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                TelCliente telcliente = new TelCliente(
                        rs.getInt("id"),
                        rs.getString("telefono"),
                        rs.getString("idcliente"));
                telefonos_clientes.add(telcliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return telefonos_clientes;
    }

    @Override
    public TelCliente findByIdProducto(int id) {
        TelCliente telCliente = new TelCliente();
        try {
            String query = "SELECT id, telefono, idcliente FROM telefonos_clientes WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                telCliente.setId(rs.getInt("id"));
                telCliente.setTelefono(rs.getString("telefono"));
                telCliente.setIdcliente(rs.getString("idcliente"));
                return telCliente;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
