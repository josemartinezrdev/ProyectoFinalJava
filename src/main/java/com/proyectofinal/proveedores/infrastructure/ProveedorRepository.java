package com.proyectofinal.proveedores.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.proyectofinal.proveedores.domain.entity.Proveedor;
import com.proyectofinal.proveedores.domain.service.ProveedorService;

public class ProveedorRepository implements ProveedorService {

    private Connection connection;

    public ProveedorRepository() {
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
    public void createProveedor(Proveedor proveedor) {
        try {
            String query = "INSERT INTO proveedores (nombre, iddireccion) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, proveedor.getNombre());
            ps.setInt(2, proveedor.getIddireccion());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProveedor(Proveedor proveedor) {
        try {
            String query = "UPDATE proveedores SET nombre = ?, iddireccion = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, proveedor.getNombre());
            ps.setInt(2, proveedor.getIddireccion());
            ps.setInt(3, proveedor.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProveedor(int id) {
        try {
            String query = "DELETE FROM proveedores WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Proveedor> findAllProveedores() {
        List<Proveedor> proveedores = new ArrayList<>();
        try {
            String query = "SELECT id, nombre, iddireccion FROM proveedores";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Proveedor proveedor = new Proveedor(rs.getInt("id"), rs.getString("nombre"), rs.getInt("iddireccion"));
                proveedores.add(proveedor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proveedores;
    }

    @Override
    public Proveedor findByIdProveedor(int id) {
        Proveedor proveedor = new Proveedor();
        try {
            String query = "SELECT id, nombre, iddireccion FROM proveedores WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                proveedor.setId(rs.getInt("id"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setIddireccion(rs.getInt("iddireccion"));
                return proveedor;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
