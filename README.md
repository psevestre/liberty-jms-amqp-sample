# WebSphere Liberty + RabbitMQ + JMS Sample

## Overview

Sample project using WebSphere Liberty, RabbitMQ and JMS. 

* Sender servlet sends a JMS text message to the RabbitMQ exchange using the RabbitMQ JMS client code.
* Message-driven bean consumes the RabbitMQ queue as a JMS queue.

## Requirements

* Maven
* RabbitMQ Broker
* RabbitMQ JMS Client + dependencies

The RabbitMQ broker should be configured with a direct exchange with a bound queue. The configuration for this can be seen in the src/main/wlp/server.xml config file.

The following environment variables should be set:

    WLP_INSTALL_DIR = Location of a local Liberty installation i.e. 'C:\IBM\liberty\'. Leave blank to have Maven download and configure a new Liberty instance.
    RMQ_LIB_DIR = Location of RabbitMQ JMS client and its dependencies i.e. 'C:\IBM\liberty\shared\'.
    RMQ_PASSWORD = Password for the RabbitMQ guest user.

## Install

Run the following commands:

    git checkout https://github.com/jrmcdonald/liberty-jms-amqp-sample.git
    cd liberty-jms-amqp-sample

Edit src/main/wlp/server.xml and change the WLP admin center user password and keystore password. Then:

    mvn clean install

## Usage

Once installed, navigate to [http://localhost:9080/liberty-jms-aqmp-1.0.0-SNAPSHOT/](http://localhost:9080/liberty-jms-aqmp-1.0.0-SNAPSHOT/) in a browser window. Check the liberty server logs for any problems. Check the RabbitMQ broker to confirm that the message has been placed onto the queue.

## Issues

Currently the MDB does not receive messages due to problems defining the activation spec.
