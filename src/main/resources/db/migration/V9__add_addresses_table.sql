CREATE TABLE IF NOT EXISTS organisations_schema.addresses (
    id              UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    street          VARCHAR NOT NULL,
    house_number    VARCHAR NOT NULL,
    additional      VARCHAR,
    zip_code        VARCHAR NOT NULL,
    city            VARCHAR NOT NULL,
    country_code    CHAR(2) NOT NULL
);

-- We probably have many organizations in production, so forcing a non null value would be a mistake.
-- Add constraint NOT NULL after all addresses of organizations are back filled
ALTER TABLE IF EXISTS organisations_schema.contact_details ADD COLUMN address_id VARCHAR;
