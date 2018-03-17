# test-smartfocus

Design and implement a RESTful API for any one endpoint form the following list.
## 1) List down all users

```json
[
    {
        "userId": 1,
        "name": "Test1"
    },
    {
        "userId": 2,
        "name": "Test 2"
    }
]
```

## 2) Fetch the requested user
## 3) Create a user Request Body { "id": 12345, "Name": "Radhika"}
## 4) Update a user
## 5) Delete a user

To represent the system we have created User.class. There is a DB class for mocking the database. We have used MAP to create the in memeory data layer.
You can create you own custom exception class or use the existing one i.e. UserNotFoundException
Feel free to change the structure of the program as per your programme. 

You are also free to choose any of the 
## 1) JAX-RS implementation e.g. Apache CXF, Jersey, Restlet etc.
## 2) Testing framework e.g restAssured, jersey-test-framework, spring-framework, resteasy.. etc.

Basic level testing should be fine e.g. Resource and service layer testing.