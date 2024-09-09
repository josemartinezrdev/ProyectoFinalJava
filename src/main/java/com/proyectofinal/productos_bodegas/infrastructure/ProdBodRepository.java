package com.proyectofinal.productos_bodegas.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.proyectofinal.productos_bodegas.domain.entity.ProdBod;
import com.proyectofinal.productos_bodegas.domain.service.ProdBodService;

public class ProdBodRepository implements ProdBodService {

    private Connection connection;

    public ProdBodRepository() {
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
    public void createProdBod(ProdBod prodbod) {
        try {
            String query = "INSERT INTO productos_bodegas (idproducto, idbodega) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, prodbod.getIdproducto());
            ps.setInt(2, prodbod.getIdbodega());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProdBod(ProdBod prodbod, int idproducto, int idbodega) {
        try {
            String query = """
                    UPDATE productos_bodegas SET idproducto = ?, idbodega = ? WHERE idproducto = ? AND idbodega = ?
                    """;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, prodbod.getIdproducto());
            ps.setInt(2, prodbod.getIdbodega());
            ps.setInt(3, idproducto);
            ps.setInt(4, idbodega);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProdBod(int idproducto, int idbodega) {
        try {
            String query = "DELETE FROM productos_bodegas WHERE idproducto = ? AND idbodega= ? ";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idproducto);
            ps.setInt(2, idbodega);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProdBod findProdBodById(int idproducto, int idbodega) {
        try {
            String query = "SELECT idproducto, idbodega FROM productos_bodegas WHERE idproducto = ? AND idbodega = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idproducto);
            ps.setInt(2, idbodega);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ProdBod prodBod = new ProdBod(rs.getInt("idproducto"), rs.getInt("idbodega"));
                    return prodBod;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProdBod> findAllProdBod() {
        List<ProdBod> productos_bodegas = new ArrayList<>();
        String query = "SELECT idproducto, idbodega FROM productos_bodegas";
        try (PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int idproducto = rs.getInt("idproducto");
                int idbodega = rs.getInt("idbodega");
                ProdBod prodBod = new ProdBod(idproducto, idbodega);
                productos_bodegas.add(prodBod);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos_bodegas;
    }
}
