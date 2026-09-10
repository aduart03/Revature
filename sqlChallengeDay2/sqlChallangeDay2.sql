SELECT * FROM invoice;
SELECT * FROM customer;

-- 1. Get all invoice ids with the customers first name, last name, and the invoice total
-- invoice id's must match the customers id in order to get the first and last name
-- You need the invoice id, first name , last name, and invoice totals. Those are your columns.
SELECT i.invoice_id, c.first_name, c.last_name, i.total
FROM invoice i
JOIN customer c ON i.customer_id = c.customer_id;

-- 2. Print the invoice id, customer's first name, and invoice total. But only if the invoice is over $30.
SELECT i.invoice_id, c.first_name, c.last_name, i.total
FROM invoice i
JOIN customer c ON i.customer_id = c.customer_id
WHERE i.total > 30;

-- 3. Get all the invoices for USA customers in the last 6 months. Use a CTE. 
WITH all_invoices AS (
    SELECT *
    FROM invoice
    WHERE billing_country = 'USA'
    )
SELECT * FROM all_invoices
WHERE invoice_date >= NOW() - INTERVAL '6 months';

-- Create a new table called record_logs
-- Fields: log_id, record_id, field_changed, last_update, old_value, new_value

DROP TABLE IF EXISTS record_logs;

CREATE TABLE record_logs(
    log_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    record_id INT,
    field_changed VARCHAR,
    last_update TIMESTAMP DEFAULT NOW(), 
    old_value TEXT,
    new_value TEXT
);

SELECT * FROM record_logs;

DROP TRIGGER IF EXISTS customer_change_log ON customer;

-- Create a trigger that tracks changes to customer records and logs the changes in our new table
CREATE OR REPLACE FUNCTION log_customer_changes()
RETURNS TRIGGER AS $$
BEGIN
    
    IF OLD.city IS DISTINCT FROM NEW.city THEN
        INSERT INTO record_logs(record_id, field_changed, old_value, new_value)
        VALUES (OLD.customer_id, 'city', OLD.city, NEW.city);
    END IF;

    IF OLD.phone IS DISTINCT FROM NEW.phone THEN
        INSERT INTO record_logs(record_id, field_changed, old_value, new_value)
        VALUES (OLD.customer_id, 'phone', OLD.phone, NEW.phone);
    END IF;

    IF OLD.email IS DISTINCT FROM NEW.email THEN
        INSERT INTO record_logs(record_id, field_changed, old_value, new_value)
        VALUES (OLD.customer_id, 'email', OLD.email, NEW.email);
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER customer_change_log
AFTER UPDATE ON customer
FOR EACH ROW
EXECUTE FUNCTION log_customer_changes();


UPDATE customer SET city = 'Dallas', phone = '+1 (214) 555-0100' WHERE customer_id = 1;
SELECT * FROM record_logs;