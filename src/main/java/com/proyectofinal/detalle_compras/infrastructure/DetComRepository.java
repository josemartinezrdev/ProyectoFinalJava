package com.proyectofinal.detalle_compras.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.proyectofinal.detalle_compras.domain.entity.DetCom;
import com.proyectofinal.detalle_compras.domain.service.DetComService;

public class DetComRepository implements DetComService {
    private Connection connection;

    public DetComRepository() {
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
    public void createDetCom(DetCom detcom) {
        try {
            String query = "INSERT INTO detalle_compras (idcompra, idpedido) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, detcom.getIdcompra());
            ps.setInt(2, detcom.getIdpedido());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDetCom(DetCom detcom, int idcompra, int idpedido) {
        try {
            String query = """
                    UPDATE detalle_compras SET idcompra = ?, idpedido = ? WHERE idcompra = ? AND idpedido = ?
                    """;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, detcom.getIdcompra());
            ps.setInt(2, detcom.getIdpedido());
            ps.setInt(3, idcompra);
            ps.setInt(4, idpedido);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDetCom(int idcompra, int idpedido) {
        try {
            String query = "DELETE FROM detalle_compras WHERE idcompra = ? AND idpedido = ? ";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idcompra);
            ps.setInt(2, idpedido);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public DetCom findDetComById(int idcompra, int idpedido) {
        try {
            String query = "SELECT idcompra, idpedido FROM detalle_compras WHERE idcompra = ? AND idpedido = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idcompra);
            ps.setInt(2, idpedido);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    DetCom detCom = new DetCom(rs.getInt("idcompra"), rs.getInt("idpedido"));
                    return detCom;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DetCom> findAllDetCom() {
        List<DetCom> detalle_compras = new ArrayList<>();
        String query = "SELECT idcompra, idpedido FROM detalle_compras";
        try (PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                DetCom detCom = new DetCom(rs.getInt("idcompra"), rs.getInt("idpedido"));
                detalle_compras.add(detCom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalle_compras;
    }

}
