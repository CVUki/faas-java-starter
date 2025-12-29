# Java FaaS Demo - 5EHIF

This repository accompanies the diploma thesis/paper "Functions as a Service (FaaS): Serverless Computing with Java". \
It demonstrates a simple Spring Cloud Function programming model.

## Features
- **Function**: `processSignup` (Transforms data)
- **Consumer**: `notifySystem` (Side effects/Logging)
- **Supplier**: `systemHealth` (Data source)
- **Composition**: `fullRegistration` (Chains Function + Consumer)

## How to Run
1. Clone the repository.
2. Run `mvn spring-boot:run`.

## How to Test (cURL)

### 1. Test the Function (Transformation)
Returns the User JSON object plus current datetime.

```
curl -H "Content-Type: application/json" \
-d '{"name": "your name", "email": "abc@demo.at"}' \
http://localhost:8080/processSignup
```

### 2. Test the Composition (Pipeline)
Runs the transformation AND logs the notification. Returns nothing (because the last step is a Consumer). \
*So, check your server console logs to see the output!*

```
curl -H "Content-Type: application/json" \
-d '{"name": "new user", "email": "test@demo.at"}' \
http://localhost:8080/
```

### 3. Test the Supplier (Get Data)
It should return the current status of the Server!
```
curl http://localhost:8080/systemHealth
```



