package com.proyectofinal.direcciones.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.proyectofinal.direcciones.domain.entity.Direccion;
import com.proyectofinal.direcciones.domain.service.DireccionService;

public class DireccionRepository implements DireccionService {

    private Connection connection;

    public DireccionRepository() {
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
    public void createDireccion(Direccion direccion) {
        try {
            String query = "INSERT INTO direcciones (calle, carrera, idciudad) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, direccion.getCalle());
            ps.setString(2, direccion.getCarrera());
            ps.setInt(3, direccion.getIdciudad());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDireccion(Direccion direccion) {
        try {
            String query = "UPDATE direcciones SET calle = ?, carrera = ?, idciudad = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, direccion.getCalle());
            ps.setString(2, direccion.getCarrera());
            ps.setInt(3, direccion.getIdciudad());
            ps.setInt(4, direccion.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDireccion(int id) {
        try {
            String query = "DELETE FROM direcciones WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Direccion> findAllDirecciones() {
        List<Direccion> direcciones = new ArrayList<>();
        try {
            String query = "SELECT id, calle, carrera, idciudad FROM direcciones";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Direccion direccion = new Direccion(
                        rs.getInt("id"),
                        rs.getString("calle"),
                        rs.getString("carrera"),
                        rs.getInt("idciudad"));
                direcciones.add(direccion);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return direcciones;
    }

    @Override
    public Direccion findByIdDireccion(int id) {
        Direccion direccion = new Direccion();
        try {
            String query = "SELECT id, calle, carrera, idciudad FROM direcciones WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                direccion.setId(rs.getInt("id"));
                direccion.setCalle(rs.getString("calle"));
                direccion.setCarrera(rs.getString("carrera"));
                direccion.setIdciudad(rs.getInt("idciudad"));
                return direccion;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
