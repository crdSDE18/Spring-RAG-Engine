CREATE TABLE IF NOT EXISTS jobs (
    job_id UUID PRIMARY KEY,
    status VARCHAR(32) NOT NULL,
    createdAt TIMESTAMP NOT NULL,
    updatedAt TIMESTAMP NOT NULL,
    error TEXT
    );

