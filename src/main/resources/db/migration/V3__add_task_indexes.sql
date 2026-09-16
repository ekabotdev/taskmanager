CREATE INDEX idx_tasks_user_id
    ON tasks(user_id);

CREATE INDEX idx_tasks_user_status
    ON tasks(user_id, status);

CREATE INDEX idx_tasks_user_priority
    ON tasks(user_id, priority);

CREATE INDEX idx_tasks_user_due_date
    ON tasks(user_id, due_date);