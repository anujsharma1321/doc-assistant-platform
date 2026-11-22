
CREATE TABLE users (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

                       company_id UUID NOT NULL,

                       full_name VARCHAR(100) NOT NULL,

                       email VARCHAR(255) NOT NULL UNIQUE,

                       password_hash VARCHAR(255) NOT NULL,

                       role VARCHAR(30) NOT NULL,

                       enabled BOOLEAN NOT NULL DEFAULT TRUE,

                       created_at TIMESTAMP WITH TIME ZONE
                           NOT NULL DEFAULT CURRENT_TIMESTAMP,

                       updated_at TIMESTAMP WITH TIME ZONE
                           NOT NULL DEFAULT CURRENT_TIMESTAMP,

                       CONSTRAINT fk_users_company
                           FOREIGN KEY (company_id)
                               REFERENCES companies(id)
                               ON DELETE RESTRICT,

                       CONSTRAINT chk_users_role
                           CHECK (role IN (
                                           'PLATFORM_ADMIN',
                                           'COMPANY_ADMIN',
                                           'COMPANY_MEMBER'
                               ))
);

CREATE INDEX idx_users_company_id
    ON users(company_id);
