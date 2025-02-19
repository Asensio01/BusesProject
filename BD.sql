CREATE TABLE `usuarios` (
  `id_usuario` INT PRIMARY KEY AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `apellido` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) UNIQUE NOT NULL,
  `password_hash` VARCHAR(255) NOT NULL,
  `telefono` VARCHAR(15),
  `rol` ENUM ('ADMIN', 'USER') DEFAULT 'USER'
);

CREATE TABLE `permisos` (
  `id_permiso` INT PRIMARY KEY AUTO_INCREMENT,
  `nombre` VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE `usuarios_permisos` (
  `id_usuario` INT NOT NULL,
  `id_permiso` INT NOT NULL,
  PRIMARY KEY (`id_usuario`, `id_permiso`)
);

CREATE TABLE `ciudades` (
  `id_ciudad` INT PRIMARY KEY AUTO_INCREMENT,
  `nombre` varchar(100),
  `departamento` ENUM ('AHUACHAPAN', 'CABANAS', 'CHALATENANGO', 'CUSCATLAN', 
        'LA_LIBERTAD', 'LA_PAZ', 'LA_UNION', 'MORAZAN', 
        'SAN_MIGUEL', 'SAN_SALVADOR', 'SANTA_ANA', 'SONSONATE', 
        'USULUTAN', 'SAN_VICENTE')
);

CREATE TABLE `auditorias` (
  `id_auditoria` INT PRIMARY KEY AUTO_INCREMENT,
  `id_usuario` INT NOT NULL,
  `tabla_modificada` ENUM ('reservas', 'rutas') NOT NULL,
  `id_registro` INT NOT NULL,
  `accion` ENUM ('CREAR', 'MODIFICAR', 'ELIMINAR') NOT NULL,
  `fecha` TIMESTAMP DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE `reservas` (
  `id_reserva` INT PRIMARY KEY AUTO_INCREMENT,
  `id_usuario` INT NOT NULL,
  `fecha_emision` DATE NOT NULL,
  `id_ciudad_origen` INT NOT NULL,
  `id_ciudad_destino` INT NOT NULL
);

CREATE TABLE `tipos_bus` (
  `id_tipo` INT PRIMARY KEY AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `capacidad_maxima` INT NOT NULL,
  `filas` int NOT NULL,
  `asientos_por_fila` int NOT NULL
);

CREATE TABLE `autobuses` (
  `id_autobus` INT PRIMARY KEY AUTO_INCREMENT,
  `placa` VARCHAR(20) UNIQUE NOT NULL,
  `id_tipo` INT NOT NULL
);

CREATE TABLE `caracteristicas_bus` (
  `id_caracteristica` INT PRIMARY KEY AUTO_INCREMENT,
  `nombre` VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE `autobuses_caracteristicas` (
  `id_autobus` INT NOT NULL,
  `id_caracteristica` INT NOT NULL,
  PRIMARY KEY (`id_autobus`, `id_caracteristica`)
);

CREATE TABLE `rutas` (
  `id_ruta` INT PRIMARY KEY AUTO_INCREMENT,
  `nombre_ruta` VARCHAR(100) NOT NULL
);

CREATE TABLE `tramos` (
  `id_tramo` INT PRIMARY KEY AUTO_INCREMENT,
  `id_ciudad_origen` INT NOT NULL,
  `id_ciudad_destino` INT NOT NULL,
  `hora_salida` TIME NOT NULL,
  `duracion` INT NOT NULL
);

CREATE TABLE `rutas_tramos` (
  `id_ruta` INT NOT NULL,
  `id_tramo` INT NOT NULL,
  `tipo_viaje` ENUM ('IDA', 'VUELTA') NOT NULL,
  PRIMARY KEY (`id_ruta`, `id_tramo`)
);

CREATE TABLE `transbordos` (
  `id_transbordo` INT PRIMARY KEY AUTO_INCREMENT,
  `id_reserva` INT NOT NULL,
  `id_tramo` INT NOT NULL,
  `id_autobus` INT NOT NULL,
  `numero_fila` INT NOT NULL,
  `letra_asiento` CHAR(1) NOT NULL
);

ALTER TABLE `reservas` ADD FOREIGN KEY (`id_ciudad_destino`) REFERENCES `ciudades` (`id_ciudad`);

ALTER TABLE `reservas` ADD FOREIGN KEY (`id_ciudad_origen`) REFERENCES `ciudades` (`id_ciudad`);

ALTER TABLE `tramos` ADD FOREIGN KEY (`id_ciudad_destino`) REFERENCES `ciudades` (`id_ciudad`);

ALTER TABLE `tramos` ADD FOREIGN KEY (`id_ciudad_origen`) REFERENCES `ciudades` (`id_ciudad`);

ALTER TABLE `usuarios_permisos` ADD FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE;

ALTER TABLE `usuarios_permisos` ADD FOREIGN KEY (`id_permiso`) REFERENCES `permisos` (`id_permiso`) ON DELETE CASCADE;

ALTER TABLE `auditorias` ADD FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE;

ALTER TABLE `reservas` ADD FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE;

ALTER TABLE `autobuses` ADD FOREIGN KEY (`id_tipo`) REFERENCES `tipos_bus` (`id_tipo`) ON DELETE CASCADE;

ALTER TABLE `autobuses_caracteristicas` ADD FOREIGN KEY (`id_autobus`) REFERENCES `autobuses` (`id_autobus`) ON DELETE CASCADE;

ALTER TABLE `autobuses_caracteristicas` ADD FOREIGN KEY (`id_caracteristica`) REFERENCES `caracteristicas_bus` (`id_caracteristica`) ON DELETE CASCADE;

ALTER TABLE `rutas_tramos` ADD FOREIGN KEY (`id_ruta`) REFERENCES `rutas` (`id_ruta`) ON DELETE CASCADE;

ALTER TABLE `rutas_tramos` ADD FOREIGN KEY (`id_tramo`) REFERENCES `tramos` (`id_tramo`) ON DELETE CASCADE;

ALTER TABLE `transbordos` ADD FOREIGN KEY (`id_reserva`) REFERENCES `reservas` (`id_reserva`) ON DELETE CASCADE;

ALTER TABLE `transbordos` ADD FOREIGN KEY (`id_tramo`) REFERENCES `tramos` (`id_tramo`) ON DELETE CASCADE;

ALTER TABLE `transbordos` ADD FOREIGN KEY (`id_autobus`) REFERENCES `autobuses` (`id_autobus`) ON DELETE CASCADE;
