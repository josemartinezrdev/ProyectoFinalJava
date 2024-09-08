package com.proyectofinal.bodegas.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.proyectofinal.bodegas.domain.entity.Bodega;
import com.proyectofinal.bodegas.domain.service.BodegaService;

public class BodegaRepository implements BodegaService {

    private Connection connection;

    public BodegaRepository() {
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
    public void createBodega(Bodega bodega) {
        try {
            String query = "INSERT INTO bodegas (nombre, iddireccion) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, bodega.getNombre());
            ps.setInt(2, bodega.getIddireccion());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateBodega(Bodega bodega) {
        try {
            String query = "UPDATE bodegas SET nombre = ?, iddireccion = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, bodega.getNombre());
            ps.setInt(2, bodega.getIddireccion());
            ps.setInt(3, bodega.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBodega(int id) {
        try {
            String query = "DELETE FROM bodegas WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Bodega> findAllBodegaes() {
        List<Bodega> bodegas = new ArrayList<>();
        try {
            String query = "SELECT id, nombre, iddireccion FROM bodegas";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Bodega bodega = new Bodega(rs.getInt("id"), rs.getString("nombre"), rs.getInt("iddireccion"));
                bodegas.add(bodega);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bodegas;
    }

    @Override
    public Bodega findByIdBodega(int id) {
        Bodega bodega = new Bodega();
        try {
            String query = "SELECT id, nombre, iddireccion FROM bodegas WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bodega.setId(rs.getInt("id"));
                bodega.setNombre(rs.getString("nombre"));
                bodega.setIddireccion(rs.getInt("iddireccion"));
                return bodega;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
