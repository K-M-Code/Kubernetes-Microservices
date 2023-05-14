# Kubernetes-Microservices

## Kubernetes, Containers, Spring Boot, React, MongoDB, Redis

[Github K-M-Code Kubernetes Microservices](https://github.com/K-M-Code/Kubernetes-Microservices)

This repository contains multiple microservices developed using different technologies, each serving a specific purpose in a larger system. The following is a brief overview of each microservice:

## customerServicePython

This microservice is developed using Python and is responsible for handling customer-related operations. It provides APIs to manage customer information, such as creating new customers, updating existing ones, and retrieving customer details. It interacts with the database to store and retrieve customer data.

## databases

The databases microservice is responsible for managing the database infrastructure for the entire system. It includes scripts and configurations for setting up and maintaining the required databases. Details about the database schemas and configurations can be found within this microservice.

## orderServiceNode

Developed using Node.js, the orderServiceNode microservice handles order-related functionalities. It exposes APIs to create new orders, update existing ones, and retrieve order details. It communicates with other microservices, such as the customerServicePython and productService, to gather the necessary information.

## productService

The productService microservice provides functionalities related to managing products within the system. It includes APIs for creating new products, updating existing ones, and retrieving product information. This microservice communicates with the databases and the orderServiceNode to ensure accurate and up-to-date product data.

## productServiceNode

Similar to the productService, the productServiceNode microservice is developed using Node.js and serves the same purpose. It provides additional flexibility and scalability options for managing the product-related operations. Both productService and productServiceNode can be used interchangeably based on the specific requirements of the system.

## shippingServicePython

The shippingServicePython microservice is responsible for managing the shipping process within the system. It provides APIs to initiate the shipping process, track shipment status, and update shipping details. This microservice interacts with other microservices, such as the orderServiceNode, to retrieve order information and trigger shipping actions.

## .env

The .env file contains environment variables required for the microservices to function correctly. It includes configurations such as database connection details, API keys, and other sensitive information. Ensure that this file is properly configured with the required values before deploying the microservices.

## docker-compose.yml

The docker-compose.yml file is used to define and orchestrate the deployment of the microservices within a containerized environment. It specifies the services, their configurations, and how they interact with each other. Use this file to launch the microservices as a group, ensuring their seamless integration and communication.

Please refer to the individual microservice directories for more detailed information on how to set up, configure, and utilize each microservice.
