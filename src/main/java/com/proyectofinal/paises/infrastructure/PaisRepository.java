package com.proyectofinal.paises.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.proyectofinal.paises.domain.entity.Pais;
import com.proyectofinal.paises.domain.service.PaisService;

public class PaisRepository implements PaisService {

    private Connection connection;

    public PaisRepository() {
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
    public void createPais(Pais pais) {
        try {
            String query = "INSERT INTO paises (nombre) VALUES (?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, pais.getNombre());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePais(Pais pais) {
        try {
            String query = "UPDATE paises SET nombre = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, pais.getNombre());
            ps.setInt(2, pais.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePais(int id) {
        try {
            String query = "DELETE FROM paises WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Pais> findAllPaises() {
        List<Pais> paises = new ArrayList<>();
        try {
            String query = "SELECT id, nombre FROM paises";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Pais pais = new Pais(rs.getInt("id"), rs.getString("nombre"));
                paises.add(pais);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paises;
    }

    @Override
    public Pais findByIdPais(int id) {
        Pais pais = new Pais();
        try {
            String query = "SELECT id, nombre FROM paises WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pais.setId(rs.getInt("id"));
                pais.setNombre(rs.getString("nombre"));
                return pais;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
