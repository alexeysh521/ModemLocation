CREATE TABLE IF NOT EXISTS router_metrics(
                               id BIGSERIAL PRIMARY KEY UNIQUE,
                               router_id VARCHAR(50) NOT NULL,
                               connected_clients INT NOT NULL,
                               mcc INT NOT NULL,
                               mnc INT NOT NULL,
                               lac INT NOT NULL,
                               cell_id INT NOT NULL,
                               radio VARCHAR(10) NOT NULL,
                               latitude DOUBLE PRECISION,
                               longitude DOUBLE PRECISION,
                               created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_router_time ON router_metrics(router_id, created_at);