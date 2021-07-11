# IoT Demo

My intention with this project is to demonstrate some code and architectural skills and explain some decisions
made during the implementation.

I have no specific requirements for this project, so I decided to go with a very generic and simple approach of some IoT
sensors communicating with a master application. Sensors are as simple as possible, and the main application is the one 
responsible for the data orchestration (which is also not defined) and data processing. 

## Description

IoT devices send out continuous data which we want to collect (e.g., thermostat, heart rate meter, car fuel readings, etc).

The task is to build a pipeline via which we can process the IoT data in a scalable manner. In addition to that, a web service
for querying the readings (e.g average/median/max/min values) of specific sensors or groups of sensors for a specific timeframe.

This project is not a production ready system.

**Requirements**

- Simulate data for some IoT devices which send out a value every X second(s)
- Scalable and extendable to work with more IoT devices
- Fast
- Self-contained

## Solution Architecture

<img src="architecture_diagram.png" alt="Architecture Diagram">

## Decisions

I decided to go with RabbitMq and JSON serialization to transfer data from devices to the main application. If performance is
an issue we could use binary or even another protocol like GRPC, the only requirement is to implement the interface `Messaging`.

The main application works completely asynchronous. The idea is that whenever it receives a message, it only stores it and
triggers an async internal event so that message can be processed.
The advantages in this architecture design are the transport layer being completely decoupled from the processing layer.
In this concrete scenario, it means that even if we get an exception while processing, because the message was already successful
acknowledged, there's no need for RabbitMq to publish it again. it should be up to the processing service to recover from
the encountered error.

For demonstrative purposes, there are two message listeners, one only receives messages of the type DeviceData while the
other reacts on any message on the queue.

## Run dependencies

Be sure to first start RabbitMq and MongoDB with Docker

```
docker-compose up
```

There are 2 applications you need to run, the Device that sends data and the Main application.
