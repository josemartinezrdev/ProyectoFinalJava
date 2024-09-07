package com.proyectofinal.empresas.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.proyectofinal.empresas.domain.entity.Empresa;
import com.proyectofinal.empresas.domain.service.EmpresaService;

public class EmpresaRepository implements EmpresaService {

    private Connection connection;

    public EmpresaRepository() {
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
    public void createEmpresa(Empresa empresa) {
        try {
            String query = "INSERT INTO empresas (nombre) VALUES (?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, empresa.getNombre());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEmpresa(Empresa empresa) {
        try {
            String query = "UPDATE empresas SET nombre = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, empresa.getNombre());
            ps.setInt(2, empresa.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmpresa(int id) {
        try {
            String query = "DELETE FROM empresas WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Empresa> findAllEmpresas() {
        List<Empresa> empresas = new ArrayList<>();
        try {
            String query = "SELECT id, nombre FROM empresas";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Empresa empresa = new Empresa(rs.getInt("id"), rs.getString("nombre"));
                empresas.add(empresa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empresas;
    }

    @Override
    public Empresa findByIdEmpresa(int id) {
        Empresa empresa = new Empresa();
        try {
            String query = "SELECT id, nombre FROM empresas WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                empresa.setId(rs.getInt("id"));
                empresa.setNombre(rs.getString("nombre"));
                return empresa;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
