package com.proyectofinal.sucursales.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.proyectofinal.sucursales.domain.entity.Sucursal;
import com.proyectofinal.sucursales.domain.service.SucursalService;

public class SucursalRepository implements SucursalService {

    private Connection connection;

    public SucursalRepository() {
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
    public void createSucursal(Sucursal sucursal) {
        try {
            String query = "INSERT INTO sucursales (nombre, iddireccion, idempresa) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, sucursal.getNombre());
            ps.setInt(2, sucursal.getIddireccion());
            ps.setInt(3, sucursal.getIdempresa());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSucursal(Sucursal sucursal) {
        try {
            String query = "UPDATE sucursales SET nombre = ?, iddireccion = ?, idempresa = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, sucursal.getNombre());
            ps.setInt(2, sucursal.getIddireccion());
            ps.setInt(3, sucursal.getIdempresa());
            ps.setInt(4, sucursal.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSucursal(int id) {
        try {
            String query = "DELETE FROM sucursales WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Sucursal> findAllSucursales() {
        List<Sucursal> sucursales = new ArrayList<>();
        try {
            String query = "SELECT id, nombre, iddireccion, idempresa FROM sucursales";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Sucursal sucursal = new Sucursal(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("iddireccion"),
                        rs.getInt("idempresa"));
                sucursales.add(sucursal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sucursales;
    }

    @Override
    public Sucursal findByIdSucursal(int id) {
        Sucursal sucursal = new Sucursal();
        try {
            String query = "SELECT id, nombre, iddireccion, idempresa FROM sucursales WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sucursal.setId(rs.getInt("id"));
                sucursal.setNombre(rs.getString("nombre"));
                sucursal.setIddireccion(rs.getInt("iddireccion"));
                sucursal.setIdempresa(rs.getInt("idempresa"));
                return sucursal;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
