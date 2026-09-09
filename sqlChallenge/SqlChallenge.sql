/*
    If you need to rebuild the database, for mac here are the commands, cd into your repo containing the sql file then :
    docker exec -it chinook-postgres psql -U chinook -d postgres -c "DROP DATABASE IF EXISTS chinook;"
    docker exec -it chinook-postgres psql -U chinook -d postgres -c "CREATE DATABASE chinook;"
    docker exec -i chinook-postgres psql -U chinook -d chinook < Week_3/Chinook_PostgreSql.sql
*/

-- Get all fields and records from customer
SELECT * FROM public.customer;

-- Get all fields from customer, but only if they are from Arizona
SELECT * FROM public.customer WHERE public.customer.state = 'AZ';

-- Get all invoices older than 6 months 
SELECT * FROM public.invoice WHERE public.invoice.invoice_date < NOW() - INTERVAL '6 months';

-- Update all customer phone numbers to NULL if they don’t follow this format: ‘+1 555 555-5555`
UPDATE public.customer SET phone = NULL WHERE phone !~ '^\+1\s\(\d{3}\)\s\d{3}-\d{4}$';
-- UPDATE public.customer SET phone = NULL WHERE phone !~ '^\+1\s\d{3}\s\d{3}-\d{4}$';
SELECT phone FROM public.customer;

-- Get all tracks that are longer than 180000 milliseconds 
SELECT * FROM track WHERE milliseconds > 180000;
SELECT * FROM track WHERE milliseconds > 180000 ORDER BY milliseconds ASC;

-- Update all customers not in the USA so that their country=USA and address, city, & state are NULL
UPDATE customer SET country = 'USA', address = NULL, city = NULL, state = NULL WHERE country != 'USA';
SELECT phone FROM public.customer;


-- Given a customer_id, return their total spending across all invoices using a function 
DROP FUNCTION IF EXISTS total_spending(INT);

CREATE OR REPLACE FUNCTION total_spending(i_customer_id INT)
RETURNS NUMERIC AS $$
BEGIN 
    RETURN (SELECT SUM(total) FROM invoice WHERE customer_id = i_customer_id);
END;
$$ LANGUAGE plpgsql;

SELECT customer_id, total_spending(customer_id) FROM customer;

-- Given an employee_id + new_manager_id, create a stored procedure to update an Employee’s ReportsTo field.
--      Prevent an employee reporting to themselves, reporting to a non-existence employee, or creating a circular management relationship

SELECT * FROM public.employee;

CREATE OR REPLACE PROCEDURE update_report_to(p_employee_id INT, p_new_manager_id INT)
LANGUAGE plpgsql
AS $$
DECLARE
    current_id INT;
BEGIN
    -- Prevent an employee reporting to themselves
    IF p_employee_id = p_new_manager_id THEN
        RAISE EXCEPTION 'Employee % cannot report to themselves', p_employee_id;
    END IF;

    IF NOT EXISTS (SELECT 1 FROM employee WHERE employee_id = p_employee_id) THEN
        RAISE EXCEPTION 'Employee % does not exist', p_employee_id;
    END IF;

    IF p_new_manager_id IS NOT NULL
       AND NOT EXISTS (SELECT 1 FROM employee WHERE employee_id = p_new_manager_id) THEN
        RAISE EXCEPTION 'Manager % does not exist', p_new_manager_id;
    END IF;

    -- Prevent a circular management relationship
    current_id := p_new_manager_id;
    WHILE current_id IS NOT NULL LOOP
        IF current_id = p_employee_id THEN
            RAISE EXCEPTION 'Circular management: % already reports up to %',
                p_new_manager_id, p_employee_id;
        END IF;
        SELECT reports_to INTO current_id FROM employee WHERE employee_id = current_id;
    END LOOP;

    UPDATE employee SET reports_to = p_new_manager_id WHERE employee_id = p_employee_id;
END;
$$;

-- CALL update_report_to(2, 2); -- raises Exception
-- CALL update_report_to(2, 99); -- raises Exception
-- CALL update_report_to(1, 8); -- raises Exception
CALL update_report_to(3, 6);

-- Create a new schema: pets
--      Create two related tables: Customer + Pets
--      Demonstrate populating records into these tables

CREATE SCHEMA pets;

-- PRIMARY KEY IS ALREADY NOT NULL AND UNIQUE SO YOU CAN JUST USE PRIMARY KEY
CREATE TABLE pets.Customer(
    ID INT PRIMARY KEY,
    name TEXT,
    type_interest TEXT
);


CREATE TABLE pets.Pets(
    ID INT PRIMARY KEY,
    name TEXT,
    type_of_dog TEXT,
    possible_match INT NOT NULL REFERENCES pets.Customer(ID)
);

INSERT INTO pets.Customer(id, name)
VALUES
    ( 1,'Jhon' ),
    ( 2,'Maria' );

INSERT INTO pets.pets(id, name, type_of_dog, possible_match)
VALUES (1, 'Rex', 'German Shepherd', 1);

SELECT * FROM  pets.Customer;
SELECT * FROM  pets.Pets;