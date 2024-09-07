package com.proyectofinal.ciudades.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.proyectofinal.ciudades.domain.entity.Ciudad;
import com.proyectofinal.ciudades.domain.service.CiudadService;

public class CiudadRepository implements CiudadService {

    private Connection connection;

    public CiudadRepository() {
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
    public void createCiudad(Ciudad ciudad) {
        try {
            String query = "INSERT INTO ciudades (nombre, idpais) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, ciudad.getNombre());
            ps.setInt(2, ciudad.getIdpais());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCiudad(Ciudad ciudad) {
        try {
            String query = "UPDATE ciudades SET nombre = ?, idpais = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, ciudad.getNombre());
            ps.setInt(2, ciudad.getIdpais());
            ps.setInt(3, ciudad.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCiudad(int id) {
        try {
            String query = "DELETE FROM ciudades WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Ciudad> findAllCiudades() {
        List<Ciudad> ciudades = new ArrayList<>();
        try {
            String query = "SELECT id, nombre, idpais FROM ciudades";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Ciudad ciudad = new Ciudad(rs.getInt("id"), rs.getString("nombre"), rs.getInt("idpais"));
                ciudades.add(ciudad);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ciudades;
    }

    @Override
    public Ciudad findByIdCiudad(int id) {
        Ciudad ciudad = new Ciudad();
        try {
            String query = "SELECT id, nombre, idpais FROM ciudades WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ciudad.setId(rs.getInt("id"));
                ciudad.setNombre(rs.getString("nombre"));
                ciudad.setIdpais(rs.getInt("idpais"));
                return ciudad;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
