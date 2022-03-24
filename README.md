# What is this app?

This is a demo application demonstrating a login and registration that can send verification tokens to their email.

## Technologies used in this project
```
Java, Spring boot, h2, Lombok, Spring web, Spring Security, Java Mail Sender, Gradle, Docker
```

## Verifying data in the database
```
To access the h2 database i have enabled h2-console in application.properities
Go to localhost:8080/h2-console
Before connecting make sure the database is set to jdbc:h2:mem:testdb then click connect
```

## Running with docker-compose
'''
You can run the application via docker compose up -d however email functionality will be unavailable as there is no maildev setup with docker-compose
'''

## Installation

Must have docker installed

From the root folder run

```bash
./gradlew clean build
docker image build -t demoauthapi .
docker network create authapi-net
docker run --net authapi-net -p 1080:1080 -p 1025:1025 --name maildev soulteary/maildev
Open a new terminal as maildev will close when that terminal does
docker run -d -p 8080:8080 --net authapi-net --name demoauthapi demoauthapi

```

## If you are running on linux or wsl
```
chmod +x startme.sh
chmod +x endme.sh
chmod +x gradlew

If any permission errors occur run endme.sh to clear docker before running startme.sh again
Run endme.sh when finished using the program
```

## Registration Usage

```
At localhost:8080 you are given the a login screen

To register a user run a post query at http://localhost:8080/localhost:8080/api/v1/registration with a body like this example
{
	"firstName": "john",
	"lastName": "smith",
	"email": "johnsmith@test.com",
	"password": "password"
}
```

## Login Usage
```
To login with a user enter the credentials at http://localhost:8080/login 

{
    "email": "johnsmith@test.com",
    "password": "password"
}

```

## Validate User Usage
```
To validate the user send the validation token as a get request to 
localhost:8080/api/v1/registration/confirm?token=
```

## Webmail Usage
```
At localhost:1080 you are given the webmail for the maildev smtp server. When a user signs up the token will be sent here.

```
