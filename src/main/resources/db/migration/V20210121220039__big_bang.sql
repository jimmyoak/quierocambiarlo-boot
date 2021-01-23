CREATE TYPE AD_CATEGORY AS ENUM ('CARS','MOTORCYCLES','BICYCLES','MOBILE_PHONES','MOTOR','FASHION');

CREATE TYPE PROVINCE AS ENUM ('ALAVA','ALBACETE','ALICANTE','ALMERIA','ASTURIAS','AVILA','A_CORUNA','BADAJOZ','BALEARES','BARCELONA','BURGOS','CACERES','CADIZ','CANTABRIA','CASTELLON','CEUTA','CIUDAD_REAL','CORDOBA','CUENCA','GIPUZKOA','GIRONA','GRANADA','GUADALAJARA','HUELVA','HUESCA','JAEN','LAS_PALMAS','LA_RIOJA','LEON','LERIDA','LUGO','MADRID','MALAGA','MELILLA','MURCIA','NAVARRA','OURENSE','PALENCIA','PONTEVEDRA','SALAMANCA','SANTA_CRUZ_DE_TENERIFE','SEGOVIA','SEVILLA','SORIA','TARRAGONA','TERUEL','TOLEDO','VALENCIA','VALLADOLID','VIZCAYA','ZAMORA','ZARAGOZA');

CREATE TABLE users
(
    id         UUID                     NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    name       VARCHAR                  NOT NULL,
    email      VARCHAR UNIQUE           NOT NULL,
    phone      VARCHAR                  NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL             DEFAULT NOW()
);

CREATE TABLE ads
(
    id            UUID                     NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    title         VARCHAR                  NOT NULL,
    category      AD_CATEGORY              NOT NULL,
    province      PROVINCE                 NOT NULL,
    description   TEXT                     NOT NULL,
    interested_on VARCHAR                  NOT NULL,
    pictures      JSON                     NOT NULL             DEFAULT '[]'::JSON,
    user_id       UUID                     NOT NULL REFERENCES users (id),
    created_at    TIMESTAMP WITH TIME ZONE NOT NULL             DEFAULT NOW()
);
