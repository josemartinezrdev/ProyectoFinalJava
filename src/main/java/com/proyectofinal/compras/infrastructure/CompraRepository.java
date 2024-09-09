package com.proyectofinal.compras.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.proyectofinal.compras.domain.entity.Compra;
import com.proyectofinal.compras.domain.service.CompraService;

public class CompraRepository implements CompraService {

    private Connection connection;

    public CompraRepository() {
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
    public void createCompra(Compra compra) {
        try {
            String query = """
                    INSERT INTO compras (fecha, total, idempleado) VALUES (?,?,?)
                    """;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setDate(1, compra.getFecha());
            ps.setDouble(2, compra.getTotal());
            ps.setString(3, compra.getIdempleado());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCompra(Compra compra) {
        try {
            String query = """
                    UPDATE compras SET fecha = ?, total = ?, idempleado = ? WHERE id = ?
                    """;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setDate(1, compra.getFecha());
            ps.setDouble(2, compra.getTotal());
            ps.setString(3, compra.getIdempleado());
            ps.setInt(4, compra.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCompra(int id) {
        try {
            String query = "DELETE FROM compras WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Compra> findAllCompras() {
        List<Compra> compras = new ArrayList<>();
        try {
            String query = """
                    SELECT id, fecha, total, idempleado FROM compras
                    """;
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Compra compra = new Compra(
                        rs.getInt("id"),
                        rs.getDate("fecha"),
                        rs.getDouble("total"),
                        rs.getString("idempleado"));
                compras.add(compra);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return compras;
    }

    @Override
    public Compra findByIdCompra(int id) {
        Compra compra = new Compra();
        try {
            String query = "SELECT id, fecha, total, idempleado FROM compras WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                compra.setId(rs.getInt("id"));
                compra.setFecha(rs.getDate("fecha"));
                compra.setTotal(rs.getDouble("total"));
                compra.setIdempleado(rs.getString("idempleado"));
                return compra;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
