CREATE TABLE inventory (
  gun_id INT NOT NULL AUTO_INCREMENT,
  player_id INT,
  gun_name VARCHAR(24),
  gun_status VARCHAR(19),
  gun_ST VARCHAR(50),
  gun_price DECIMAL(8,2),
  on_sale VARCHAR(50),
  PRIMARY KEY (gun_id)
);
