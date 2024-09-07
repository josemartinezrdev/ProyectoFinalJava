DROP DATABASE IF EXISTS finalJava;
CREATE DATABASE IF NOT EXISTS finalJava;
USE finalJava;


-- Register and login

CREATE TABLE users ( --' YA
    id INT AUTO_INCREMENT,
    enabled BOOLEAN,
    username VARCHAR(12) ,
    password VARCHAR(255),
    CONSTRAINT pk_id_users PRIMARY KEY (id),
    CONSTRAINT Uq_user UNIQUE (username)
);

CREATE TABLE roles ( --' YA
    id INT AUTO_INCREMENT,
    name VARCHAR(255),
    CONSTRAINT pk_id_roles PRIMARY KEY (id)
);

CREATE TABLE users_roles ( --¨¨ NO OLVIDAR CORREGIR UPDATE
    user_id INT,
    role_id INT,
    CONSTRAINT users_roles_ids UNIQUE (role_id, user_id),
    CONSTRAINT fk_users_roles_user FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_users_roles_role FOREIGN KEY (role_id) REFERENCES roles (id)
);

-- Tabla empresas
CREATE TABLE empresas (
    id INT AUTO_INCREMENT,
    nombre VARCHAR(50),
    CONSTRAINT pk_empresas PRIMARY KEY (id)
);

-- Tabla paises
CREATE TABLE paises (
    id INT AUTO_INCREMENT,
    nombre VARCHAR(50),
    CONSTRAINT pk_paises PRIMARY KEY (id)
);

-- Tabla ciudades
CREATE TABLE ciudades (
    id INT AUTO_INCREMENT,
    nombre VARCHAR(50),
    idpais INT,
    CONSTRAINT pk_ciudades PRIMARY KEY (id),
    CONSTRAINT fk_ciudades_paises FOREIGN KEY (idpais) REFERENCES paises(id)
);

-- Tabla direcciones
CREATE TABLE direcciones (
    id INT AUTO_INCREMENT,
    calle VARCHAR(50),
    carrera VARCHAR(50),
    idciudad INT,
    CONSTRAINT pk_direcciones PRIMARY KEY (id),
    CONSTRAINT fk_direcciones_ciudades FOREIGN KEY (idciudad) REFERENCES ciudades(id)
);

-- Tabla sucursales
CREATE TABLE sucursales (
    id INT AUTO_INCREMENT,
    nombre VARCHAR(50),
    iddireccion INT,
    idempresa INT,
    CONSTRAINT pk_sucursales PRIMARY KEY (id),
    CONSTRAINT fk_sucursales_direcciones FOREIGN KEY (iddireccion) REFERENCES direcciones(id),
    CONSTRAINT fk_sucursales_empresas FOREIGN KEY (idempresa) REFERENCES empresas(id)
);

-- Tabla proveedores
CREATE TABLE proveedores (
    id INT AUTO_INCREMENT,
    nombre VARCHAR(50),
    iddireccion INT,
    CONSTRAINT pk_proveedores PRIMARY KEY (id),
    CONSTRAINT fk_proveedores_direcciones FOREIGN KEY (iddireccion) REFERENCES direcciones(id)
);

-- Tabla categorias
CREATE TABLE categorias (
    id INT AUTO_INCREMENT,
    nombre VARCHAR(50),
    CONSTRAINT pk_categorias PRIMARY KEY (id)
);

-- Tabla productos
CREATE TABLE productos (
    id INT AUTO_INCREMENT,
    nombre VARCHAR(50),
    precio DECIMAL(10,2),
    idcategoria INT,
    CONSTRAINT pk_productos PRIMARY KEY (id),
    CONSTRAINT fk_productos_categorias FOREIGN KEY (idcategoria) REFERENCES categorias(id)
);

-- Tabla estados
CREATE TABLE estados (
    id INT AUTO_INCREMENT,
    nombre VARCHAR(50),
    CONSTRAINT pk_estados PRIMARY KEY (id)
);

-- Tabla pedidos
CREATE TABLE pedidos (
    id INT AUTO_INCREMENT,
    total DECIMAL(10,2),
    idestado INT,
    CONSTRAINT pk_pedidos PRIMARY KEY (id),
    CONSTRAINT fk_pedidos_estados FOREIGN KEY (idestado) REFERENCES estados(id)
);

-- Tabla detalles_pedidos
CREATE TABLE detalles_pedidos (
    id INT AUTO_INCREMENT,
    idproducto INT,
    idpedido INT,
    idproveedor INT,
    CONSTRAINT pk_detalles_pedidos PRIMARY KEY (id),
    CONSTRAINT fk_detalles_pedidos_productos FOREIGN KEY (idproducto) REFERENCES productos(id),
    CONSTRAINT fk_detalles_pedidos_pedidos FOREIGN KEY (idpedido) REFERENCES pedidos(id),
    CONSTRAINT fk_detalles_pedidos_proveedores FOREIGN KEY (idproveedor) REFERENCES proveedores(id)
);

-- Tabla bodegas
CREATE TABLE bodegas (
    id INT AUTO_INCREMENT,
    nombre VARCHAR(50),
    iddireccion INT,
    CONSTRAINT pk_bodegas PRIMARY KEY (id),
    CONSTRAINT fk_bodegas_direcciones FOREIGN KEY (iddireccion) REFERENCES direcciones(id)
);

-- Tabla productos_bodegas
CREATE TABLE productos_bodegas (
    idproducto INT,
    idbodega INT,
    CONSTRAINT pk_productos_bodegas PRIMARY KEY (idproducto, idbodega),
    CONSTRAINT fk_productos_bodegas_productos FOREIGN KEY (idproducto) REFERENCES productos(id),
    CONSTRAINT fk_productos_bodegas_bodegas FOREIGN KEY (idbodega) REFERENCES bodegas(id)
);

-- Tabla empleados
CREATE TABLE empleados (
    id VARCHAR(50),
    nombre VARCHAR(50),
    idsucursal INT,
    CONSTRAINT pk_empleados PRIMARY KEY (id),
    CONSTRAINT fk_empleados_sucursales FOREIGN KEY (idsucursal) REFERENCES sucursales(id)
);

-- Tabla telefonos_empleados
CREATE TABLE telefonos_empleados (
    id INT AUTO_INCREMENT,
    telefono VARCHAR(20),
    idempleado VARCHAR(50),
    CONSTRAINT pk_telefonos_empleados PRIMARY KEY (id),
    CONSTRAINT fk_telefonos_empleados_empleados FOREIGN KEY (idempleado) REFERENCES empleados(id)
);

-- Tabla proveedores_productos
CREATE TABLE proveedores_productos (
    idproducto INT,
    idproveedor INT,
    CONSTRAINT pk_proveedores_productos PRIMARY KEY (idproducto, idproveedor),
    CONSTRAINT fk_proveedores_productos_productos FOREIGN KEY (idproducto) REFERENCES productos(id),
    CONSTRAINT fk_proveedores_productos_proveedores FOREIGN KEY (idproveedor) REFERENCES proveedores(id)
);

-- Tabla compras
CREATE TABLE compras (
    id INT AUTO_INCREMENT,
    fecha DATE,
    total DECIMAL(10,2),
    idempleado VARCHAR(50),
    CONSTRAINT pk_compras PRIMARY KEY (id),
    CONSTRAINT fk_compras_empleados FOREIGN KEY (idempleado) REFERENCES empleados(id)
);

-- Tabla detalle_compras
CREATE TABLE detalle_compras (
    idcompra INT,
    idpedido INT,
    CONSTRAINT pk_detalle_compras PRIMARY KEY (idcompra, idpedido),
    CONSTRAINT fk_detalle_compras_compras FOREIGN KEY (idcompra) REFERENCES compras(id),
    CONSTRAINT fk_detalle_compras_pedidos FOREIGN KEY (idpedido) REFERENCES pedidos(id)
);

-- Tabla tipos_clientes
CREATE TABLE tipos_clientes (
    id INT AUTO_INCREMENT,
    nombre VARCHAR(50),
    CONSTRAINT pk_tipos_clientes PRIMARY KEY (id)
);

-- Tabla clientes
CREATE TABLE clientes (
    id VARCHAR(50),
    nombre VARCHAR(50),
    apellido VARCHAR(50),
    idtipo INT,
    iddireccion INT,
    CONSTRAINT pk_clientes PRIMARY KEY (id),
    CONSTRAINT fk_clientes_tipos_clientes FOREIGN KEY (idtipo) REFERENCES tipos_clientes(id),
    CONSTRAINT fk_clientes_direcciones FOREIGN KEY (iddireccion) REFERENCES direcciones(id)
);

-- Tabla telefonos_clientes
CREATE TABLE telefonos_clientes (
    id INT AUTO_INCREMENT,
    telefono VARCHAR(20),
    idcliente VARCHAR(50),
    CONSTRAINT pk_telefonos_clientes PRIMARY KEY (id),
    CONSTRAINT fk_telefonos_clientes_clientes FOREIGN KEY (idcliente) REFERENCES clientes(id)
);

-- Tabla ventas
CREATE TABLE ventas (
    id INT AUTO_INCREMENT,
    fecha DATE,
    total DECIMAL(10,2),
    idempleado VARCHAR(50),
    idcliente VARCHAR(50),
    CONSTRAINT pk_ventas PRIMARY KEY (id),
    CONSTRAINT fk_ventas_empleados FOREIGN KEY (idempleado) REFERENCES empleados(id),
    CONSTRAINT fk_ventas_clientes FOREIGN KEY (idcliente) REFERENCES clientes(id)
);

-- Tabla detalles_ventas
CREATE TABLE detalles_ventas (
    idventa INT,
    total INT,
    idproducto INT,
    cantidad INT,
    CONSTRAINT pk_detalles_ventas PRIMARY KEY (idventa, idproducto),
    CONSTRAINT fk_detalles_ventas_ventas FOREIGN KEY (idventa) REFERENCES ventas(id),
    CONSTRAINT fk_detalles_ventas_productos FOREIGN KEY (idproducto) REFERENCES productos(id)
);