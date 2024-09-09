package com.proyectofinal.telefonos_empleados.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.proyectofinal.telefonos_empleados.domain.entity.TelEmpleado;
import com.proyectofinal.telefonos_empleados.domain.service.TelEmpleadoService;

public class TelEmpleadoRepository implements TelEmpleadoService {

    private Connection connection;

    public TelEmpleadoRepository() {
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
    public void createProducto(TelEmpleado telempleado) {
        try {
            String query = "INSERT INTO telefonos_empleados (telefono, idempleado) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, telempleado.getTelefono());
            ps.setString(2, telempleado.getIdempleado());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProducto(TelEmpleado telempleado) {
        try {
            String query = "UPDATE telefonos_empleados SET telefono = ?, idempleado = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, telempleado.getTelefono());
            ps.setString(2, telempleado.getIdempleado());
            ps.setInt(3, telempleado.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProducto(int id) {
        try {
            String query = "DELETE FROM telefonos_empleados WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TelEmpleado> findAllProductos() {
        List<TelEmpleado> telefonos_empleados = new ArrayList<>();
        try {
            String query = "SELECT id, telefono, idempleado FROM telefonos_empleados";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                TelEmpleado telempleado = new TelEmpleado(
                        rs.getInt("id"),
                        rs.getString("telefono"),
                        rs.getString("idempleado"));
                telefonos_empleados.add(telempleado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return telefonos_empleados;
    }

    @Override
    public TelEmpleado findByIdProducto(int id) {
        TelEmpleado telEmpleado = new TelEmpleado();
        try {
            String query = "SELECT id, telefono, idempleado FROM telefonos_empleados WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                telEmpleado.setId(rs.getInt("id"));
                telEmpleado.setTelefono(rs.getString("telefono"));
                telEmpleado.setIdempleado(rs.getString("idempleado"));
                return telEmpleado;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
