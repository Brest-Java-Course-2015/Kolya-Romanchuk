INSERT INTO transaction (id_transaction, checknumbersender, checknumberrecipient, summa, id_check) VALUES (1,2345,2347,2000, 2);
INSERT INTO transaction (id_transaction, checknumbersender, checknumberrecipient, summa, id_check) VALUES (2,2346,2349,4542, 2);

INSERT INTO check (id_check, checknumber, summa, id_user) VALUES (1, 1234, 89787, 12334);
INSERT INTO check (id_check, checknumber, summa, id_user) VALUES (2, 121234, 321331, 1123124);

INSERT INTO user(id_user, login, password, firstname, lastname) VALUES (1, 'login1', 'password1', 'firstname1', 'lastname1');
INSERT INTO user(id_user, login, password, firstname, lastname) VALUES (2, 'login2', 'password2', 'firstname2', 'lastname2');