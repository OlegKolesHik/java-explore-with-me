DROP TABLE IF EXISTS stats CASCADE;
CREATE TABLE IF NOT EXISTS stats (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    app VARCHAR(50) NOT NULL,
    uri VARCHAR(150) NOT NULL,
    ip VARCHAR(40) NOT NULL,
    timestamp TIMESTAMP WITHOUT TIME ZONE NOT NULL
    );