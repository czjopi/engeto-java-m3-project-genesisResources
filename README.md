# Projekt GenesisResources

## MySQL nastaveni

### 1. Vytvoreni databaze

```sql
CREATE DATABASE <database_name> CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 2. Vytvoreni uzivatele a prideleni prav

```sql
CREATE USER '<database_user>'@'localhost' IDENTIFIED BY '<user_password>';
GRANT ALL PRIVILEGES ON <database_name>.* TO '<database_user>'@'localhost';
FLUSH PRIVILEGES;
```

### 3. Vytvoreni tabulky `user`

```sql
USE <database_name>;

CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255),
    person_id VARCHAR(12) NOT NULL UNIQUE,
    uuid VARCHAR(255) NOT NULL UNIQUE
);
```

## Nastaveni aplikace a environment variables

Aplikace pouziva pro pripojeni k databazi environment promenne. Muzete je nastavit primo v shellu, nebo vytvorit soubor `.env` v rootu projektu:

**Priklad `.env` souboru:**

```
export MYSQL_URL=jdbc:mysql://<host>:<port>/<database>
export MYSQL_USER=<user>
export MYSQL_PASSWORD=<password>
```

Pokud spoustite aplikaci primo, promenne nastavte pred startem. Pri pouziti docker-compose pridejte do sekce `environment` v compose.yml.

