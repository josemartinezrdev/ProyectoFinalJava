package com.proyectofinal.detalles_pedidos.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.proyectofinal.detalles_pedidos.domain.entity.DetPed;
import com.proyectofinal.detalles_pedidos.domain.service.DetPedService;

public class DetPedRepository implements DetPedService {

    private Connection connection;

    public DetPedRepository() {
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
    public void createDetPed(DetPed detped) {
        try {
            String query = "INSERT INTO detalles_pedidos (idproducto, idpedido, idproveedor) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, detped.getIdproducto());
            ps.setInt(2, detped.getIdpedido());
            ps.setInt(3, detped.getIdproveedor());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDetPed(DetPed detped) {
        try {
            String query = "UPDATE detalles_pedidos SET idproducto = ?, idpedido = ?, idproveedor = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, detped.getIdproducto());
            ps.setInt(2, detped.getIdpedido());
            ps.setInt(3, detped.getIdproveedor());
            ps.setInt(4, detped.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDetPed(int id) {
        try {
            String query = "DELETE FROM detalles_pedidos WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DetPed> findAllDetPedes() {
        List<DetPed> detallesPedidos = new ArrayList<>();
        try {
            String query = "SELECT id, idproducto, idpedido, idproveedor FROM detalles_pedidos";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                DetPed detPed = new DetPed(
                        rs.getInt("id"),
                        rs.getInt("idproducto"),
                        rs.getInt("idpedido"),
                        rs.getInt("idproveedor"));
                detallesPedidos.add(detPed);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detallesPedidos;
    }

    @Override
    public DetPed findByIdDetPed(int id) {
        DetPed detPed = new DetPed();
        try {
            String query = "SELECT id, idproducto, idpedido, idproveedor FROM detalles_pedidos WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                detPed.setId(rs.getInt("id"));
                detPed.setIdproducto(rs.getInt("idproducto"));
                detPed.setIdpedido(rs.getInt("idpedido"));
                detPed.setIdproveedor(rs.getInt("idproveedor"));
                return detPed;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
