package com.proyectofinal.ventas.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.proyectofinal.ventas.domain.entity.Venta;
import com.proyectofinal.ventas.domain.service.VentaService;

public class VentaRepository implements VentaService {
    private Connection connection;

    public VentaRepository() {

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
    public void createVenta(Venta venta) {
        try {
            String query = """
                    INSERT INTO ventas (fecha, total, idempleado, idcliente) VALUES (?,?,?,?)
                    """;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setDate(1, venta.getFecha());
            ps.setDouble(2, venta.getTotal());
            ps.setString(3, venta.getIdempleado());
            ps.setString(4, venta.getIdcliente());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateVenta(Venta venta) {
        try {
            String query = """
                    UPDATE ventas SET fecha = ?, total = ?, idempleado = ?, idcliente = ? WHERE id = ?
                    """;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setDate(1, venta.getFecha());
            ps.setDouble(2, venta.getTotal());
            ps.setString(3, venta.getIdempleado());
            ps.setString(4, venta.getIdcliente());
            ps.setInt(5, venta.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteVenta(int id) {
        try {
            String query = "DELETE FROM ventas WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Venta> findAllVentas() {
        List<Venta> ventas = new ArrayList<>();
        try {
            String query = """
                    SELECT id, fecha, total, idempleado, idcliente FROM ventas
                    """;
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Venta compra = new Venta(
                        rs.getInt("id"),
                        rs.getDate("fecha"),
                        rs.getDouble("total"),
                        rs.getString("idempleado"),
                        rs.getString("idcliente"));
                ventas.add(compra);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ventas;
    }

    @Override
    public Venta findByIdVenta(int id) {
        try {
            String query = "SELECT id, fecha, total, idempleado, idcliente FROM ventas WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Venta(
                        rs.getInt("id"),
                        rs.getDate("fecha"),
                        rs.getDouble("total"),
                        rs.getString("idempleado"),
                        rs.getString("idcliente")); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
