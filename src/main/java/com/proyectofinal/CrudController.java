package com.proyectofinal;

import java.io.IOException;

public class CrudController {
    public void usersFun() throws IOException {
        App.setRoot("users");
    }

    public void rolesFun() throws IOException {
        App.setRoot("roles");
    }

    public void userRolesFun() throws IOException {
        App.setRoot("users_roles");
    }

    public void empresasFun() throws IOException {
        App.setRoot("empresas");
    }

    public void paisesFun() throws IOException {
        App.setRoot("paises");
    }

    public void ciudadesFun() throws IOException {
        App.setRoot("ciudades");
    }

    public void direccionesFun() throws IOException {
        App.setRoot("direcciones");
    }

    public void sucursalesFun() throws IOException {
        App.setRoot("sucursales");
    }

    public void proveedoresFun() throws IOException {
        App.setRoot("proveedores");
    }

    public void categoriasFun() throws IOException {
        App.setRoot("categorias");
    }

    public void productosFun() throws IOException {
        App.setRoot("productos");
    }

    public void estadosFun() throws IOException {
        App.setRoot("estados");
    }

    public void pedidosFun() throws IOException {
        App.setRoot("pedidos");
    }

    public void detallesPedidosFun() throws IOException {
        App.setRoot("detalles_pedidos");
    }

    public void bodegasFun() throws IOException {
        App.setRoot("bodegas");
    }

    public void bodegasProductosFun() throws IOException {
        App.setRoot("productos_bodegas");
    }

    public void empleadosFun() throws IOException {
        App.setRoot("empleados");
    }

    public void telefonosEmpleadosFun() throws IOException {
        App.setRoot("telefonos_empleados");
    }

    public void proveedoresProductosFun() throws IOException {
        App.setRoot("proveedores_productos");
    }

    public void comprasFun() throws IOException {
        App.setRoot("compras");
    }

    public void detallesComprasFun() throws IOException {
        App.setRoot("detalle_compras");
    }

    public void tiposClientesFun() throws IOException {
        App.setRoot("tipos_clientes");
    }

    public void clientesFun() throws IOException {
        App.setRoot("clientes");
    }

    public void telefonosClientesFun() throws IOException {
        App.setRoot("telefonos_clientes");
    }

    public void ventasFun() throws IOException {
        App.setRoot("ventas");
    }

    public void detallesVentasFun() throws IOException {
        App.setRoot("detalles_ventas");
    }

    public void back() throws IOException {
        App.setRoot("home");
    }
}
