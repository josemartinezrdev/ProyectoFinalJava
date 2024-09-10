package com.proyectofinal.detalles_ventas.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.proyectofinal.detalles_ventas.domain.entity.DetVenta;
import com.proyectofinal.detalles_ventas.domain.service.DetVentaService;

public class DetVentaRepository implements DetVentaService {
    private Connection connection;

    public DetVentaRepository() {
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
    public void createVenta(DetVenta detVenta) {
        try {
            String query = "INSERT INTO detalles_ventas (idventa, total, idproducto, cantidad) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, detVenta.getIdventa());
            ps.setInt(2, detVenta.getTotal());
            ps.setInt(3, detVenta.getIdproducto());
            ps.setInt(4, detVenta.getCantidad());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateVenta(DetVenta detVenta) {
        try {
            String query = """
                    UPDATE detalles_ventas SET idventa = ?, total = ?, idproducto = ?, cantidad = ? WHERE id = ?
                    """;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, detVenta.getIdventa());
            ps.setInt(2, detVenta.getTotal());
            ps.setInt(3, detVenta.getIdproducto());
            ps.setInt(4, detVenta.getCantidad());
            ps.setInt(5, detVenta.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteVenta(int id) {
        try {
            String query = "DELETE FROM detalles_ventas WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DetVenta> findAllVentas() {
        List<DetVenta> detallesVentas = new ArrayList<>();
        try {
            String query = "SELECT id, idventa, total, idproducto, cantidad FROM detalles_ventas";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                DetVenta detVenta = new DetVenta(
                        rs.getInt("id"),
                        rs.getInt("idventa"),
                        rs.getInt("total"),
                        rs.getInt("idproducto"),
                        rs.getInt("cantidad"));
                detallesVentas.add(detVenta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detallesVentas;
    }

    @Override
    public DetVenta findByIdVenta(int id) {
        try {
            String query = "SELECT id, idventa, total, idproducto, cantidad FROM detalles_ventas WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new DetVenta(
                        rs.getInt("id"),
                        rs.getInt("idventa"),
                        rs.getInt("total"),
                        rs.getInt("idproducto"),
                        rs.getInt("cantidad"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
