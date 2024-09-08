package com.proyectofinal.categorias.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.proyectofinal.categorias.domain.entity.Categoria;
import com.proyectofinal.categorias.domain.service.CategoriaService;

public class CategoriaRepository implements CategoriaService {
    private Connection connection;

    public CategoriaRepository() {
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
    public void createCategoria(Categoria categoria) {
        try {
            String query = "INSERT INTO categorias (nombre) VALUES (?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, categoria.getNombre());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCategoria(Categoria categoria) {
        try {
            String query = "UPDATE categorias SET nombre = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, categoria.getNombre());
            ps.setInt(2, categoria.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCategoria(int id) {
        try {
            String query = "DELETE FROM categorias WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Categoria> findAllCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        try {
            String query = "SELECT id, nombre FROM categorias";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Categoria categoria = new Categoria(rs.getInt("id"), rs.getString("nombre"));
                categorias.add(categoria);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categorias;
    }

    @Override
    public Categoria findByIdCategoria(int id) {
        Categoria categoria = new Categoria();
        try {
            String query = "SELECT id, nombre FROM categorias WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
                return categoria;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
