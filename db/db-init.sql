CREATE ROLE "projectuser" WITH
	LOGIN
	NOSUPERUSER
	NOCREATEDB
	NOCREATEROLE
	INHERIT
	NOREPLICATION
	CONNECTION LIMIT -1
	PASSWORD 'password';
CREATE DATABASE projectmngr
    WITH
    OWNER = projectuser
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;