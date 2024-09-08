package com.proyectofinal.productos.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.proyectofinal.productos.domain.entity.Producto;
import com.proyectofinal.productos.domain.service.ProductoService;

public class ProductoRepository implements ProductoService {
    private Connection connection;

    public ProductoRepository() {
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
    public void createProducto(Producto producto) {
        try {
            String query = "INSERT INTO productos (nombre, precio, idcategoria) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setInt(3, producto.getIdcategoria());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProducto(Producto producto) {
        try {
            String query = "UPDATE productos SET nombre = ?, precio = ?, idcategoria = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setInt(3, producto.getIdcategoria());
            ps.setInt(4, producto.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProducto(int id) {
        try {
            String query = "DELETE FROM productos WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Producto> findAllProductos() {
        List<Producto> productos = new ArrayList<>();
        try {
            String query = "SELECT id, nombre, precio, idcategoria FROM productos";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("idcategoria"));
                productos.add(producto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productos;
    }

    @Override
    public Producto findByIdProducto(int id) {
        Producto producto = new Producto();
        try {
            String query = "SELECT id, nombre, precio, idcategoria FROM productos WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setIdcategoria(rs.getInt("idcategoria"));
                return producto;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
