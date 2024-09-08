package com.proyectofinal.pedidos.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.proyectofinal.pedidos.domain.entity.Pedido;
import com.proyectofinal.pedidos.domain.service.PedidoService;

public class PedidoRepository implements PedidoService {
    private Connection connection;

    public PedidoRepository() {
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
    public void createPedido(Pedido pedido) {
        try {
            String query = "INSERT INTO pedidos (total, idestado) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setDouble(1, pedido.getTotal());
            ps.setInt(2, pedido.getIdestado());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePedido(Pedido pedido) {
        try {
            String query = "UPDATE pedidos SET total = ?, idestado = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setDouble(1, pedido.getTotal());
            ps.setInt(2, pedido.getIdestado());
            ps.setInt(3, pedido.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePedido(int id) {
        try {
            String query = "DELETE FROM pedidos WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Pedido> findAllPedidoes() {
        List<Pedido> pedidos = new ArrayList<>();
        try {
            String query = "SELECT id, total, idestado FROM pedidos";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Pedido pedido = new Pedido(
                        rs.getInt("id"),
                        rs.getDouble("total"),
                        rs.getInt("idestado"));
                pedidos.add(pedido);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    @Override
    public Pedido findByIdPedido(int id) {
        Pedido pedido = new Pedido();
        try {
            String query = "SELECT id, total, idestado FROM pedidos WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pedido.setId(rs.getInt("id"));
                pedido.setTotal(rs.getDouble("total"));
                pedido.setIdestado(rs.getInt("idestado"));
                return pedido;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
