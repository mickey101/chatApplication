# Getting Started with the Chat App

A simple Chat application based
- Chat-api
- Chat-ui

## How to RUN the App

1. Build a docker image of the chat-api
#### `cd chat-api`
#### `mvn clean package`
#### `docker build --tag chat-api-v1:latest .`

2. Build a docker image of the chat-ui
#### `cd chat-ui`
#### `docker build --tag chat-ui-v1:latest .`

3. Docker Compose up
#### `docker-compose up`

