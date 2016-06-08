CREATE TABLE teste (
    uniqid     integer,
    key        varchar(50),
    value      varchar(100),
    host       varchar(40),
    clock      timestamp,
    PRIMARY KEY(uniqid)
);

INSERT INTO teste(uniqid,key,value,host,clock) VALUES
(1,'system.cpu.utilization','10','macos_localhost',now());

INSERT INTO teste(uniqid,key,value,host,clock) VALUES
((SELECT MAX( uniqid ) FROM teste) +1,'system.cpu.utilization','10','macos_localhost',now());


TRUNCATE TABLE teste;