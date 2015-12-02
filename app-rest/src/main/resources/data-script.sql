INSERT INTO transaction (id_transaction, checknumbersender, checknumberrecipient, summa, date ,id_check) VALUES (1,1234,12345,2000, TO_DATE('2015-10-21', 'YYYY-MM-DD'),1);
INSERT INTO transaction (id_transaction, checknumbersender, checknumberrecipient, summa, date ,id_check) VALUES (2,12345,123456,4542, TO_DATE('2015-11-24', 'YYYY-MM-DD'),2);
INSERT INTO transaction (id_transaction, checknumbersender, checknumberrecipient, summa, date ,id_check) VALUES (3,123456,1234,2000, TO_DATE('2015-10-21', 'YYYY-MM-DD'),3);
INSERT INTO transaction (id_transaction, checknumbersender, checknumberrecipient, summa, date ,id_check) VALUES (4,1234567,12345,4542, TO_DATE('2015-11-24', 'YYYY-MM-DD'),4);

INSERT INTO check (id_check, checknumber, summa, id_user) VALUES (1, 1234, 89787, 2);
INSERT INTO check (id_check, checknumber, summa, id_user) VALUES (2, 12345, 321331, 2);
INSERT INTO check (id_check, checknumber, summa, id_user) VALUES (3, 123456, 89787, 3);
INSERT INTO check (id_check, checknumber, summa, id_user) VALUES (4, 1234567, 321331, 3);

INSERT INTO user(id_user, login, password, role, firstname, lastname) VALUES (1,'admin','admin', 'ROLE_ADMIN','firstname','lastname');
INSERT INTO user(id_user, login, password, role, firstname, lastname) VALUES (2,'user1','user1', 'ROLE_USER','firstname','lastname');
INSERT INTO user(id_user, login, password, role, firstname, lastname) VALUES (3,'user2','user2', 'ROLE_USER','firstname','lastname');