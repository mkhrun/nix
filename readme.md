# Project Title

Interview exercise

### Prerequisites

Install docker

## Deployment

No need to configure environment.
Run provide docker image
```
docker run -p <your port>: 8080 -p <your port>: 8081 -p <your port>: 9091 <image tag>
```

### Port mapping

**9091** (or **`<your port>`**) - services registry server http://localhost:9091/shop-eureka-server/

**8080** (or **`<your port>`**) - phone search service http://localhost:8080/phone/all/ returns dummy data in JSON format

**8081** (or **`<your port>`**) - phone order service http://localhost:8081/order/new/. Use any postman to generate request containing following body keys (x-www-form-urlencoded):
* customerName
* customerSurname
* customerEmail
* phones[0-n].article (existing values MA5712, S234FR89, G1234OO; free to specify any)
## Authors

Maksym Khrun
