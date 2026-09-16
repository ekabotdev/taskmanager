CREATE TABLE tasks (
                       id BIGINT NOT NULL AUTO_INCREMENT,
                       title VARCHAR(100) NOT NULL,
                       description VARCHAR(1000),
                       status VARCHAR(20) NOT NULL,
                       priority VARCHAR(20) NOT NULL,
                       due_date DATETIME,
                       created_at DATETIME NOT NULL,
                       updated_at DATETIME NOT NULL,
                       user_id BIGINT NOT NULL,

                       PRIMARY KEY (id),

                       CONSTRAINT fk_tasks_user
                           FOREIGN KEY (user_id)
                               REFERENCES users(id)
                               ON DELETE CASCADE
);