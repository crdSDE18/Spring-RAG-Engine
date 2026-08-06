-- vector comes from the pg extension, but still required
CREATE EXTENSION IF NOT EXISTS vector;

CREATE TABLE IF NOT EXISTS embeddings (
    id UUID PRIMARY KEY,
    job_id String NOT NULL,
    createdAt TIMESTAMP NOT NULL,
    chunk_text TEXT NOT NULL,
    embedding VECTOR(768) NOT NULL,
    metadata TEXT NOT NULL,

    FOREIGN KEY (job_id) REFERENCES jobs(job_id)

);