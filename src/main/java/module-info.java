module com.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires transitive javafx.base;

    // ! AÑADIR INFRASTRUCTURE
    exports com.proyectofinal.user.infrastructure;
    exports com.proyectofinal.roles.infrastructure;
    exports com.proyectofinal.users_roles.infrastructure;
    exports com.proyectofinal.paises.infrastructure;
    exports com.proyectofinal.empresas.infrastructure;
    exports com.proyectofinal.ciudades.infrastructure;
    exports com.proyectofinal.direcciones.infrastructure;
    // ! AÑADIR DOMAIN
    exports com.proyectofinal.user.domain;
    exports com.proyectofinal.roles.domain;
    exports com.proyectofinal.users_roles.domain;
    exports com.proyectofinal.paises.domain.entity; // | Entidades con entity
    exports com.proyectofinal.empresas.domain.entity; // | Entidades con entity
    exports com.proyectofinal.ciudades.domain.entity; // | Entidades con entity
    exports com.proyectofinal.direcciones.domain.entity; // | Entidades con entity

    // ! AÑADIR CADA ENTIDAD
    opens com.proyectofinal to javafx.fxml;
    opens com.proyectofinal.user.infrastructure to javafx.fxml;
    opens com.proyectofinal.roles.infrastructure to javafx.fxml;
    opens com.proyectofinal.users_roles.infrastructure to javafx.fxml;
    opens com.proyectofinal.paises.infrastructure to javafx.fxml;
    opens com.proyectofinal.empresas.infrastructure to javafx.fxml;
    opens com.proyectofinal.ciudades.infrastructure to javafx.fxml;
    opens com.proyectofinal.direcciones.infrastructure to javafx.fxml;

    exports com.proyectofinal;
}
