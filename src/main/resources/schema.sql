CREATE TABLE IF NOT EXISTS products(
  id INTEGER IDENTITY PRIMARY KEY,
  category VARCHAR(255) NOT NULL,
  title VARCHAR(255) NOT NULL,
  img VARCHAR(255),
  description VARCHAR(255),
  date VARCHAR(255) NOT NULL
);


CREATE TABLE IF NOT EXISTS comments(
  id INTEGER IDENTITY PRIMARY KEY,
  id_product INTEGER NOT NULL,
  comment VARCHAR(255) NOT NULL,
  date VARCHAR(255) NOT NULL,
  rating INTEGER,
  useful_vote INTEGER NOT NULL,
  not_useful_vote INTEGER NOT NULL,
  FOREIGN KEY (id_product) REFERENCES products(id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS accounts(
  id INTEGER IDENTITY PRIMARY KEY,
  role VARCHAR (50) NOT NULL,
  username VARCHAR(50) NOT NULL,
  hash_password VARCHAR(512) NOT NULL,
  firstname VARCHAR(50) NOT NULL,
  name VARCHAR(50) NOT NULL,
);

CREATE TABLE IF NOT EXISTS comments_account (
  id_account INTEGER NOT NULL,
  id_comment INTEGER NOT NULL,
  PRIMARY KEY (id_account, id_comment),
  FOREIGN KEY (id_comment) REFERENCES comments(id) ON DELETE CASCADE,
  FOREIGN KEY (id_account) REFERENCES accounts(id)
);

CREATE TABLE IF NOT EXISTS products_account (
  id_account INTEGER NOT NULL,
  id_product INTEGER NOT NULL,
  PRIMARY KEY (id_account, id_product),
  FOREIGN KEY (id_product) REFERENCES products(id) ON DELETE CASCADE,
  FOREIGN KEY (id_account) REFERENCES accounts(id)
);
