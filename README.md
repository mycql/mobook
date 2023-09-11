# MoBook
## A simple mobile phone booking service
Written in Java on top of Spring Boot.

This is a very simple service to demonstrate a simple REST based service for booking (e.g. checking in, checking out)
mobile phone from an inventory.
### How to run
```sh
./mvnw clean package
./mvnw spring-boot:run
```
View the APIs by navigating to: http://localhost:8080/swagger-ui/index.html


### Details
Given the limited time I had to develop this, several some functionality were left out. The objective was to implement
the features requested given a time window
- Spring security is disabled so any client can just invoke the endpoints just for demonstration
- No proper input validation at the moment
- Minimal error handling and logging
- Lack of tests

### Pre-requisites
[Flyway](https://flywaydb.org/) is used for db migrations using a [schema](./src/main/resources/db/migration/V1_1__create_tables.sql) in an [H2](https://h2database.com/html/main.html) in-memory db and seed the data.
The data is [seeded](./src/main/resources/db/migration/V1_2__seed_tables.sql) with only one user. This user can be used to perform check-in, check-out. To add more users at the moment, add them to the seed data.
```json
{
  "clientId": 1,
  "firstName": "Chris",
  "lastName": "Luna"
}
```
The data is seeded with these phones:
- Apple iPhone X
- Apple iPhone 11
- Apple iPhone 12
- Apple iPhone 13
- Samsung Galaxy S 8 (2 counts)
- Samsung Galaxy S 9
- Nokia 3310
- OnePlus 9
- Motorola Nexus 6

### APIs
Samples below assuming using [httpie](https://httpie.io/) client
```sh
http localhost:8080/api/mobile-phone/search brand='Motorola' # POST to search the inventory by brand
```
```json
[
    {
        "id": 8,
        "model": {
            "brand": "Motorola",
            "device": "Nexus",
            "version": "6"
        },
        "status": "CHECKED_IN"
    }
]
```
```sh
htto localhost:8080/api/mobile-phone/checkout clientId=1 brand='Samsung' device='Galaxy S' version=9 # POST checks-out a phone given model details and the user 
```
```json
{
    "checkoutReferenceId": 11,
    "checkoutTime": "2023-09-11T03:50:08.807721",
    "mobilePhone": {
        "id": 7,
        "model": {
            "brand": "Samsung",
            "device": "Galaxy S",
            "version": "9"
        },
        "status": "CHECKED_OUT"
    }
}
```
```sh
http localhost:8080/api/mobile-phone/checkin clientId=1 phoneId=1 # POST checks-in the device back in.
```
```json
{
    "checkinReferenceId": 12,
    "checkinTime": "2023-09-11T03:51:11.124588"
}
```
```sh
http localhost:8080/api/booking/{id} # GET retrieve the booking details made (either checkout or checkin)
```
```json
{
    "action": "CHECK_OUT",
    "client": {
        "firstName": "Chris",
        "id": 1,
        "lastName": "Luna"
    },
    "id": 1,
    "phone": {
        "id": 5,
        "model": {
            "brand": "Samsung",
            "device": "Galaxy S",
            "version": "8"
        }
    },
    "status": "ACCEPTED",
    "timestamp": "2023-09-10T21:41:34.370584"
}
```
```sh
http localhost:8080/api/booking/search clientId=1 # POST search for all bookings of this client
OR
http localhost:8080/api/booking/search phoneId=1 # POST search for all bookings of this device
```
```json
[
    {
        "action": "CHECK_OUT",
        "client": {
            "firstName": "Chris",
            "id": 1,
            "lastName": "Luna"
        },
        "id": 11,
        "phone": {
            "id": 7,
            "model": {
                "brand": "Samsung",
                "device": "Galaxy S",
                "version": "9"
            }
        },
        "status": "ACCEPTED",
        "timestamp": "2023-09-11T03:50:08.807721"
    },
    {
        "action": "CHECK_IN",
        "client": {
            "firstName": "Chris",
            "id": 1,
            "lastName": "Luna"
        },
        "id": 12,
        "phone": {
            "id": 7,
            "model": {
                "brand": "Samsung",
                "device": "Galaxy S",
                "version": "9"
            }
        },
        "status": "ACCEPTED",
        "timestamp": "2023-09-11T03:51:11.124588"
    }
]
```
### What I could have done better
- Writing tests definitely
- Applying some sort of security
- CI setup

### What aspect in this exercise is interesting?
Since this is a full on exercise, you'd have to think from how you model the data and structure the relationships.
I think given that although it may seem simple, it does cover quite a breadth of concepts.

### What part is cumbersome?
I was trying to integrate with FonoAPI as requested, but that project is dead so there is no 
really working API for some time now so I left that feature out. 

Also, I apologize I have no tests no coverage so that is definitely something cumbersome to achieve in
a full service exercise although it definitely matters
