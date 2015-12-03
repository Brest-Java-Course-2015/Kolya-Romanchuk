# Kolya-Romanchuk
# BANK-ACCOUNT
mvn clean install

Start rest:
    cd app-rest
    mvn jetty:run

URL prefix: **localhost:8080/rest**
* list user /users
* count users /users/count
* list checks /checks/{id_user}
* list transactions /transactions/{id_user}


Start web client:
    cd app-web
    mvn jetty:run

URL prefix: **localhost:8090**
* admin page /admin or or [bank-account-webclient/admin](http://kolyaromanchuk-bankaccount.rhcloud.com/bank-account-webclient/admin)
* user page /user or [bank-account-webclient/user](http://kolyaromanchuk-bankaccount.rhcloud.com/bank-account-webclient/user)

Users login and password:
* user1 user1
* user2 user2

Admin login and password:
* admin admin