package com.proyectofinal.empleados.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.proyectofinal.empleados.domain.entity.Empleado;
import com.proyectofinal.empleados.domain.service.EmpleadoService;

public class EmpleadoRepository implements EmpleadoService {
    private Connection connection;

    public EmpleadoRepository() {
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
    public void createEmpleado(Empleado empleado) {
        try {
            String query = "INSERT INTO empleados (id, nombre, idsucursal) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, empleado.getId());
            ps.setString(2, empleado.getNombre());
            ps.setInt(3, empleado.getIdsucursal());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEmpleado(Empleado empleado, String id) {
        try {
            String query = "UPDATE empleados SET id = ?, nombre = ?, idsucursal = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, empleado.getId());
            ps.setString(2, empleado.getNombre());
            ps.setInt(3, empleado.getIdsucursal());
            ps.setString(4, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmpleado(String id) {
        try {
            String query = "DELETE FROM empleados WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Empleado> findAllEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        try {
            String query = "SELECT id, nombre, idsucursal FROM empleados";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Empleado bodega = new Empleado(rs.getString("id"), rs.getString("nombre"), rs.getInt("idsucursal"));
                empleados.add(bodega);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empleados;
    }

    @Override
    public Empleado findByIdEmpleado(String id) {
        Empleado empleado = new Empleado();
        try {
            String query = "SELECT id, nombre, idsucursal FROM empleados WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                empleado.setId(rs.getString("id"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setIdsucursal(rs.getInt("idsucursal"));
                return empleado;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
