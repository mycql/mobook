# MoBook
## A simple mobile phone booking service
Written in Java on top of Spring Boot.

This is a very simple service to demonstrate a simple REST based service for booking (e.g. checking in, checking out)
mobile phone from an inventory.
### How to run
```sh
mvn clean package
mvn spring-boot:run
```

### Details
Given the limited time I had to develop this, several some functionality were left out. The objective was to implement
the features requested given a time window
- Spring security is disabled so any client can just invoke the endpoints just for demonstration
- No proper input validation at the moment
- Minimal error handling and logging
- Lack of tests

### Pre-requisites
Flyway is used to create the database using a schema in a file based h2 (./mobookdb) and seed the data.
The data is seeded with only one user. This user can be used to perform check-in, check-out
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
Samples below assuming using httpie client
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
- Properly document APIs (OpenAPI)

### What aspect in this exercise is interesting?
Since this is a full on exercise, you'd have to think from how you model the data and structure the relationships.
I think given that although it may seem simple, it does cover quite a breadth of concepts.

### What part is cumbersome?
I didn't spend too much time on this but writing documentation like this could have been better if I
had more time to bootstrap some proper docs framework (e.g. OpenAPI or Asciidoc)

Also, I apologize I have no tests no coverage so that is definitely something cumbersome to achieve in
a full service exercise although it definitely matters
