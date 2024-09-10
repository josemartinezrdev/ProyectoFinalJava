package com.proyectofinal.clientes.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import com.proyectofinal.clientes.domain.entity.Cliente;
import com.proyectofinal.clientes.domain.service.ClienteService;

public class ClienteRepository implements ClienteService {
    private Connection connection;

    public ClienteRepository() {
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
    public void createCliente(Cliente cliente) {
        try {
            String query = """
                    INSERT INTO clientes (id, nombre, apellido, idtipo, iddireccion) VALUES (?,?,?,?,?)
                    """;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, cliente.getId());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellido());
            ps.setInt(4, cliente.getIdtipocliente());
            ps.setInt(5, cliente.getIddireccion());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCliente(Cliente cliente, String id) {
        try {
            String query = """
                    UPDATE clientes SET id = ?, nombre = ?, apellido = ?, idtipo = ?, iddireccion = ? WHERE id = ?
                    """;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, cliente.getId());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellido());
            ps.setInt(4, cliente.getIdtipocliente());
            ps.setInt(5, cliente.getIddireccion());
            ps.setString(6, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCliente(String id) {
        try {
            String query = "DELETE FROM clientes WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Cliente> findAllClientes() {
        List<Cliente> clientes = new ArrayList<>();
        try {
            String query = """
                    SELECT id, nombre, apellido, idtipo, iddireccion FROM clientes
                    """;
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("idtipo"),
                        rs.getInt("iddireccion"));
                clientes.add(cliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public Cliente findByIdCliente(String id) {
        // Cliente cliente = new Cliente();
        try {
            String query = "SELECT id, nombre, apellido, idtipo, iddireccion FROM clientes WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Cliente(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("idtipo"),
                        rs.getInt("iddireccion"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
