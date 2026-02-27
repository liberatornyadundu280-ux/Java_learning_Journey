INSERT INTO items (name, price, quantity, category, restock_level) VALUES
('Notebook', 45.0, 30, 'Stationery', 3),
('Blue Pen', 10.0, 80, 'Stationery', 3),
('Pencil Box', 85.0, 20, 'Stationery', 3),
('Eraser', 5.0, 100, 'Stationery', 3),
('Geometry Box', 150.0, 15, 'Stationery', 3),
('Calculator', 499.0, 12, 'Electronics', 3),
('USB Drive 32GB', 399.0, 18, 'Electronics', 3),
('Wired Mouse', 650.0, 10, 'Electronics', 3),
('Math Textbook', 320.0, 14, 'Books', 3),
('Science Guide', 280.0, 16, 'Books', 3),
('Backpack', 899.0, 9, 'Accessories', 3),
('Water Bottle', 120.0, 25, 'Accessories', 3),
('Hand Sanitizer', 95.0, 22, 'Cleaning', 3),
('Tissue Pack', 40.0, 35, 'Cleaning', 3),
('Granola Bar', 30.0, 50, 'Snacks', 3),
('Juice Box', 25.0, 45, 'Snacks', 3);
INSERT INTO users (username, password_hash, role) VALUES
('admin', 'admin123', 'ADMIN'),
('user1', 'user123', 'STAFF'),
('user2', 'user123', 'STAFF');