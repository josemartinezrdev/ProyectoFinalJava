module com.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires transitive javafx.base;
    requires transitive java.sql;

    exports com.proyectofinal.user.infrastructure;
    exports com.proyectofinal.roles.infrastructure;
    exports com.proyectofinal.users_roles.infrastructure;
    exports com.proyectofinal.paises.infrastructure;
    exports com.proyectofinal.empresas.infrastructure;
    exports com.proyectofinal.ciudades.infrastructure;
    exports com.proyectofinal.direcciones.infrastructure;
    exports com.proyectofinal.sucursales.infrastructure;
    exports com.proyectofinal.proveedores.infrastructure;
    exports com.proyectofinal.categorias.infrastructure;
    exports com.proyectofinal.productos.infrastructure;
    exports com.proyectofinal.estados.infrastructure;
    exports com.proyectofinal.pedidos.infrastructure;
    exports com.proyectofinal.detalles_pedidos.infrastructure;
    exports com.proyectofinal.bodegas.infrastructure;
    exports com.proyectofinal.productos_bodegas.infrastructure;
    exports com.proyectofinal.empleados.infrastructure;
    exports com.proyectofinal.telefonos_empleados.infrastructure;
    exports com.proyectofinal.proveedores_productos.infrastructure;
    exports com.proyectofinal.compras.infrastructure;
    exports com.proyectofinal.detalle_compras.infrastructure;
    // ! AÑADIR DOMAIN
    exports com.proyectofinal.user.domain;
    exports com.proyectofinal.roles.domain;
    exports com.proyectofinal.users_roles.domain;
    exports com.proyectofinal.paises.domain.entity; // | Entidades con entity
    exports com.proyectofinal.empresas.domain.entity; // | Entidades con entity
    exports com.proyectofinal.ciudades.domain.entity; // | Entidades con entity
    exports com.proyectofinal.direcciones.domain.entity; // | Entidades con entity
    exports com.proyectofinal.sucursales.domain.entity; // | Entidades con entity
    exports com.proyectofinal.proveedores.domain.entity; // | Entidades con entity
    exports com.proyectofinal.categorias.domain.entity; // | Entidades con entity
    exports com.proyectofinal.productos.domain.entity; // | Entidades con entity
    exports com.proyectofinal.estados.domain.entity; // | Entidades con entity
    exports com.proyectofinal.pedidos.domain.entity; // | Entidades con entity
    exports com.proyectofinal.detalles_pedidos.domain.entity; // | Entidades con entity
    exports com.proyectofinal.bodegas.domain.entity; // | Entidades con entity
    exports com.proyectofinal.productos_bodegas.domain.entity; // | Entidades con entity
    exports com.proyectofinal.empleados.domain.entity; // | Entidades con entity
    exports com.proyectofinal.telefonos_empleados.domain.entity; // | Entidades con entity
    exports com.proyectofinal.proveedores_productos.domain.entity; // | Entidades con entity
    exports com.proyectofinal.compras.domain.entity; // | Entidades con entity
    exports com.proyectofinal.detalle_compras.domain.entity; // | Entidades con entity

    // ! AÑADIR CADA ENTIDAD
    opens com.proyectofinal to javafx.fxml;
    opens com.proyectofinal.user.infrastructure to javafx.fxml;
    opens com.proyectofinal.roles.infrastructure to javafx.fxml;
    opens com.proyectofinal.users_roles.infrastructure to javafx.fxml;
    opens com.proyectofinal.paises.infrastructure to javafx.fxml;
    opens com.proyectofinal.empresas.infrastructure to javafx.fxml;
    opens com.proyectofinal.ciudades.infrastructure to javafx.fxml;
    opens com.proyectofinal.direcciones.infrastructure to javafx.fxml;
    opens com.proyectofinal.sucursales.infrastructure to javafx.fxml;
    opens com.proyectofinal.proveedores.infrastructure to javafx.fxml;
    opens com.proyectofinal.categorias.infrastructure to javafx.fxml;
    opens com.proyectofinal.productos.infrastructure to javafx.fxml;
    opens com.proyectofinal.estados.infrastructure to javafx.fxml;
    opens com.proyectofinal.pedidos.infrastructure to javafx.fxml;
    opens com.proyectofinal.detalles_pedidos.infrastructure to javafx.fxml;
    opens com.proyectofinal.bodegas.infrastructure to javafx.fxml;
    opens com.proyectofinal.productos_bodegas.infrastructure to javafx.fxml;
    opens com.proyectofinal.empleados.infrastructure to javafx.fxml;
    opens com.proyectofinal.telefonos_empleados.infrastructure to javafx.fxml;
    opens com.proyectofinal.proveedores_productos.infrastructure to javafx.fxml;
    opens com.proyectofinal.compras.infrastructure to javafx.fxml;
    opens com.proyectofinal.detalle_compras.infrastructure to javafx.fxml;

    exports com.proyectofinal;
}
