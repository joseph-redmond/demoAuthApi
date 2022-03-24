# What is this app?

This is a demo application demonstrating a login and registration that can send verification tokens to their email.

## Installation

Must have docker installed

From the root folder run

```bash
./gradlew clean build
docker image build -t demoauthapi .
docker run --net authapi-net -p 1080:1080 -p 1025:1025 --name maildev soulteary/maildev
docker run -d -p 8080:8080 --net authapi-net --name demoauthapi demoauthapi

```

## Registration Usage

```
At localhost:8080 you are given the a login screen

At localhost:1080 you are given the webmail for the maildev smtp server

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
To login with a user run a post query at http://localhost:8080/login with the credentials in the body like so

{
    "email": "johnsmith@test.com",
    "password": "password"
}

Until the user is validated you will recieve a disabled user error if you try to login
```

## Validate User Usage
```
To validate the user send the validation token as a get request to 
localhost:8080/api/v1/registration/confirm?token=
```