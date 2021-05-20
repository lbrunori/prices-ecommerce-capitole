# Prices Rest API - Example

Example Rest API in Java 8 and Spring boot 2.

It uses an internal H2 database for storing example date that can be found in the resources files.

You can fetch the prices with a request like

`curl --location --request GET 'http://localhost:8080/api/prices?application-date=2020-06-14T10:00:00Z&product-id=35455&brand-id=1'`