# What is this app?

This is a demo application demonstrating a login and registration that can send verification tokens to their email.

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
