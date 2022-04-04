CREATE SCHEMA IF NOT EXISTS product DEFAULT CHARACTER SET utf8 ;
USE product ;

/************ Update: Tables ***************/

/******************** Add Table: categoria ************************/

/* Build Table Structure */
CREATE TABLE categoria
(
	cat_id INTEGER UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL,
	cat_nombre VARCHAR(80) NOT NULL,
	cat_created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
insert into categoria(cat_id,cat_nombre,cat_created_at)
value(1,'lacteos',"2022-01-01");
insert into categoria(cat_id,cat_nombre,cat_created_at)
value(2,'belleza',"2022-01-01");
insert into categoria(cat_id,cat_nombre,cat_created_at)
value(3,'carniceria',"2022-01-01");
insert into categoria(cat_id,cat_nombre,cat_created_at)
value(4,'panaderia',"2022-01-02");
insert into categoria(cat_id,cat_nombre,cat_created_at)
value(5,'granos',"2022-02-02");
insert into categoria(cat_id,cat_nombre,cat_created_at)
value(6,'bebidas',"2022-01-01");
/* Add Indexes */
CREATE UNIQUE INDEX categoria_cat_nombre_Idx ON categoria (cat_nombre) USING BTREE;


/******************** Add Table: descarga ************************/

/* Build Table Structure */
CREATE TABLE descarga
(
	dwn_id INTEGER UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL,
	dwn_usuario_id INTEGER UNSIGNED NOT NULL,
	dwn_item_id INTEGER UNSIGNED NOT NULL,
	dwn_created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/* Add Indexes */
CREATE INDEX descarga_dwn_item_id_Idx ON descarga (dwn_item_id) USING BTREE;

CREATE INDEX descarga_dwn_usuario_id_Idx ON descarga (dwn_usuario_id) USING BTREE;

CREATE INDEX descarga_dwn_usuario_id_dwn_item_id_Idx ON descarga (dwn_usuario_id, dwn_item_id) USING BTREE;


/******************** Add Table: item ************************/

/* Build Table Structure */
CREATE TABLE item
(
	itm_id INTEGER UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL,
	itm_subcategoria_id INTEGER UNSIGNED NOT NULL,
	itm_nombre VARCHAR(80) NOT NULL,
	itm_created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/* Add Indexes */
CREATE UNIQUE INDEX item_itm_nombre_Idx ON item (itm_nombre) USING BTREE;

CREATE INDEX item_itm_subcategoria_id_Idx ON item (itm_subcategoria_id) USING BTREE;


/******************** Add Table: session ************************/

/* Build Table Structure */
CREATE TABLE session
(
	ses_id INTEGER UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL,
	ses_usuario_id INTEGER UNSIGNED NOT NULL,
	ses_token VARCHAR(32) 
		COMMENT 'Hash con md5' NOT NULL,
	ses_created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/* Add Indexes */
CREATE INDEX session_ses_usuario_id_Idx ON session (ses_usuario_id) USING BTREE;


/******************** Add Table: subcategoria ************************/

/* Build Table Structure */
CREATE TABLE subcategoria
(
	scat_id INTEGER UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL,
	scat_categoria_id INTEGER UNSIGNED NOT NULL,
	scat_nombre VARCHAR(80) NOT NULL,
	scat_created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/* Add Indexes */
CREATE INDEX subcategoria_scat_categoria_id_Idx ON subcategoria (scat_categoria_id) USING BTREE;

CREATE UNIQUE INDEX subcategoria_scat_nombre_scat_categoria_id_Idx ON subcategoria (scat_nombre, scat_categoria_id) USING BTREE;


/******************** Add Table: usuario ************************/

/* Build Table Structure */
CREATE TABLE usuario
(
	usu_id INTEGER UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL,
	usu_username VARCHAR(80) NOT NULL,
	usu_password VARCHAR(32) 
		COMMENT 'Hash con md5' NOT NULL,
	usu_created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
	usu_updated_at DATETIME NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/* Add Indexes */
CREATE UNIQUE INDEX usuario_usu_username_Idx ON usuario (usu_username) USING BTREE;

CREATE INDEX usuario_usu_username_usu_password_Idx ON usuario (usu_username, usu_password) USING BTREE;

/************ Add Foreign Keys ***************/

/* Add Foreign Key: fk_descarga_item */
ALTER TABLE descarga ADD CONSTRAINT fk_descarga_item
	FOREIGN KEY (dwn_item_id) REFERENCES item (itm_id)
	ON UPDATE NO ACTION ON DELETE NO ACTION;

/* Add Foreign Key: fk_descarga_usuario */
ALTER TABLE descarga ADD CONSTRAINT fk_descarga_usuario
	FOREIGN KEY (dwn_usuario_id) REFERENCES usuario (usu_id)
	ON UPDATE NO ACTION ON DELETE NO ACTION;

/* Add Foreign Key: fk_item_subcategoria */
ALTER TABLE item ADD CONSTRAINT fk_item_subcategoria
	FOREIGN KEY (itm_subcategoria_id) REFERENCES subcategoria (scat_id)
	ON UPDATE NO ACTION ON DELETE NO ACTION;

/* Add Foreign Key: fk_session_usuario */
ALTER TABLE session ADD CONSTRAINT fk_session_usuario
	FOREIGN KEY (ses_usuario_id) REFERENCES usuario (usu_id)
	ON UPDATE NO ACTION ON DELETE NO ACTION;

/* Add Foreign Key: fk_subcategoria_categoria */
ALTER TABLE subcategoria ADD CONSTRAINT fk_subcategoria_categoria
	FOREIGN KEY (scat_categoria_id) REFERENCES categoria (cat_id)
	ON UPDATE NO ACTION ON DELETE NO ACTION;