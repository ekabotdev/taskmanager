CREATE TABLE users (
                       id BIGINT NOT NULL AUTO_INCREMENT,
                       username VARCHAR(50) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       password VARCHAR(60) NOT NULL,
                       role VARCHAR(20) NOT NULL,
                       created_at DATETIME NOT NULL,
                       updated_at DATETIME NOT NULL,

                       PRIMARY KEY (id),

                       CONSTRAINT uk_users_username UNIQUE (username),
                       CONSTRAINT uk_users_email UNIQUE (email)
);