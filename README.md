# CHALLENGE MASTERTECH - The Point System Electronic

This challenge consists of developing a project that provides some APIs for the user's registration in the electronic point system.

In this challenge, the following technologies were used:
* Java 1.8
* Spring Boot
* Spring Data
* mySql 8

# Database settings such as host, user and password are in the `application.properties` file

#SERVER CONFIG
server.servlet.contextPath=/mastertech

## MySQL
hibernate.connection.driver_class = com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/mastertech?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false
spring.datasource.username=mastertech
spring.datasource.password=mastertech
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create
hibernate.hbm2ddl.auto=validate

## ENDPOINTS

### Users

#### GET - /usuario

List of all users registered in the database

```
[
    {
        "id": 1,
        "nome": "Jose",
        "cpf": "36910240847",
        "email": "jose@gmail.com",
        "data": "2020-08-03T05:10:04.744+00:00"
    },
    {
        "id": 2,
        "nome": "Maria",
        "cpf": "11111111111",
        "email": "maria@gmail.com",
        "data": "2020-08-03T05:22:58.626+00:00"
    }
]
```

#### GET - /usuario/{id}

User details found by id

```
{
    "id": 1,
    "nome": "Jose",
    "cpf": "36910240847",
    "email": "jose@gmail.com",
    "data": "2020-08-03T05:10:04.744+00:00"
}
```

#### POST - /usuario

New User
```
{
   "nome": "Jose",
   "cpf": "36910240847",
   "email": "jose@gmail.com",
}
```

#### PUT - /usuario/{id}

Update User
```
/usuario/1
{
    "id": 1,
    "nome": "Jose Maria",
    "cpf": "36910240847",
    "email": "jose@gmail.com"
}
```

### Electronic Point

#### GET - /pontoeletronico

```
[
    {
        "data": "03/08/2020",
        "horasTrabalhadas": "00:00:08",
        "marcacoes": [
            {
                "hora": "02:40:15",
                "tipo": "ENTRADA"
            },
            {
                "hora": "02:40:22",
                "tipo": "SAIDA"
            },
            {
                "hora": "02:40:23",
                "tipo": "ENTRADA"
            },
            {
                "hora": "02:40:24",
                "tipo": "SAIDA"
            }
        ]
    }
]
```