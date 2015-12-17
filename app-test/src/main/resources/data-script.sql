INSERT INTO user(id_user, login, password, role, firstname, lastname) VALUES (1,'login1','password1', 'ROLE_ADMIN','firstname1','lastname1');
INSERT INTO user(id_user, login, password, role, firstname, lastname) VALUES (2,'login2','password2', 'ROLE_USER','firstname2','lastname2');

INSERT INTO check (id_check, checknumber, summa, id_user) VALUES (1, 1234, 89787, 1);
INSERT INTO check (id_check, checknumber, summa, id_user) VALUES (2, 121234, 321331, 1);

INSERT INTO transaction (id_transaction, checknumbersender, checknumberrecipient, summa, date ,id_check) VALUES (1,2345,2347,2000, TO_DATE('2015-10-21', 'YYYY-MM-DD'),2);
INSERT INTO transaction (id_transaction, checknumbersender, checknumberrecipient, summa, date ,id_check) VALUES (2,2346,2349,4542, TO_DATE('2015-11-24', 'YYYY-MM-DD'),2);


