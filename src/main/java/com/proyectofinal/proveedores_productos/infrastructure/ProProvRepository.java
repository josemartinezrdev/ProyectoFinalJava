package com.proyectofinal.proveedores_productos.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.proyectofinal.proveedores_productos.domain.entity.ProProv;
import com.proyectofinal.proveedores_productos.domain.service.ProProvService;

public class ProProvRepository implements ProProvService {
    private Connection connection;

    public ProProvRepository() {
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
    public void createProdBod(ProProv proprov) {
        try {
            String query = "INSERT INTO proveedores_productos (idproducto, idproveedor) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, proprov.getIdproducto());
            ps.setInt(2, proprov.getIdproveedor());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProdBod(ProProv proprov, int idproducto, int idproveedor) {
        try {
            String query = """
                    UPDATE proveedores_productos SET idproducto = ?, idproveedor = ? WHERE idproducto = ? AND idproveedor = ?
                    """;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, proprov.getIdproducto());
            ps.setInt(2, proprov.getIdproveedor());
            ps.setInt(3, idproducto);
            ps.setInt(4, idproveedor);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProdBod(int idproducto, int idproveedor) {
        try {
            String query = "DELETE FROM proveedores_productos WHERE idproducto = ? AND idproveedor = ? ";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idproducto);
            ps.setInt(2, idproveedor);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProProv findProdBodById(int idproducto, int idproveedor) {
        try {
            String query = "SELECT idproducto, idproveedor FROM proveedores_productos WHERE idproducto = ? AND idproveedor = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idproducto);
            ps.setInt(2, idproveedor);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ProProv proProv = new ProProv(rs.getInt("idproducto"), rs.getInt("idproveedor"));
                    return proProv;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProProv> findAllProdBod() {
        List<ProProv> proveedores_productos = new ArrayList<>();
        String query = "SELECT idproducto, idproveedor FROM proveedores_productos";
        try (PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int idproducto = rs.getInt("idproducto");
                int idproveedor = rs.getInt("idproveedor");
                ProProv proProv = new ProProv(idproducto, idproveedor);
                proveedores_productos.add(proProv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proveedores_productos;
    }

}
