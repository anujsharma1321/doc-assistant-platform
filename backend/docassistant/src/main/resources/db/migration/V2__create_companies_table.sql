CREATE TABLE companies (
                           id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

                           name VARCHAR(150) NOT NULL,

                           created_at TIMESTAMP WITH TIME ZONE
                                             NOT NULL DEFAULT CURRENT_TIMESTAMP,

                           updated_at TIMESTAMP WITH TIME ZONE
                                             NOT NULL DEFAULT CURRENT_TIMESTAMP,

                           CONSTRAINT uk_companies_name UNIQUE (name)
);