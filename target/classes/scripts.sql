CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username TEXT  NOT NULL UNIQUE,
    password_hash TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    phone_number TEXT,
    address TEXT,
    user_role TEXT NOT NULL CHECK (user_role IN ('ADMIN', 'TECHNICIAN', 'EMPLOYEE')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE service_plans (
    id SERIAL PRIMARY KEY,
    plan_type TEXT NOT NULL,
    plan_description TEXT,
    plan_price DECIMAL(10,2) NOT NULL,
    date_purchased DATE DEFAULT CURRENT_DATE,
    user_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE tickets (
    id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    description TEXT,
    category TEXT NOT NULL CHECK (category IN ('Hardware', 'Software', 'Network', 'Account Access')),
    priority TEXT NOT NULL CHECK (priority IN ('Low', 'Medium', 'High', 'Critical')),
    status TEXT NOT NULL DEFAULT 'Open' CHECK (status IN ('Open', 'In Progress', 'Resolved', 'Closed')),
    submitted_by INTEGER NOT NULL,
    assigned_to INTEGER,
    date_opened DATE DEFAULT CURRENT_DATE,
    date_resolved DATE,
    FOREIGN KEY (submitted_by) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (assigned_to)  REFERENCES users(id) ON DELETE SET NULL
);

CREATE TABLE hardware_inventory (
    id SERIAL PRIMARY KEY,
    item_name TEXT NOT NULL,
    item_type TEXT NOT NULL,
    item_price DECIMAL(10,2) NOT NULL,
    quantity_in_stock INTEGER NOT NULL DEFAULT 0
);