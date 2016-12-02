# WebSphere Liberty + RabbitMQ + JMS Sample

## Overview

Sample project using WebSphere Liberty, RabbitMQ and JMS. 

* Sender servlet sends a JMS text message to the RabbitMQ exchange using the RabbitMQ JMS client code.
* Message-driven bean consumes the RabbitMQ queue as a JMS queue.

## Requirements

* Maven
* RabbitMQ Broker
* RabbitMQ JMS Client + dependencies
* Generic JMS Resource Adapter ([https://genericjmsra.java.net/](https://genericjmsra.java.net/))

The RabbitMQ broker should be configured with a direct exchange with a bound queue. The configuration for this can be seen in the src/main/wlp/server.xml config file.

The following environment variables should be set:

    IBM_LIBERTY_LICENSE = Set to the appropriate licence code as described at https://github.com/WASdev/ci.maven#build.
    WLP_INSTALL_DIR = Location of a local Liberty installation i.e. 'C:\IBM\liberty\'. Leave blank to have Maven download and configure a new Liberty instance.
    RMQ_PASSWORD = Password for the RabbitMQ guest user.

Add the RabbitMQ JMS Client and its dependencies to the root of the GenericJMSRA RAR file and place the RAR file at the location specified in the server.xml file i.e. 'C:/IBM/liberty/adapters/genericra.rar'.

## Install

Run the following commands:

    git checkout https://github.com/jrmcdonald/liberty-jms-amqp-sample.git
    cd liberty-jms-amqp-sample

Edit src/main/wlp/server.xml and change the WLP admin center user password and keystore password. Then:

    mvn clean install

## Usage

Once installed, navigate to [http://localhost:9080/liberty-jms-aqmp-1.0.0-SNAPSHOT/](http://localhost:9080/liberty-jms-aqmp-1.0.0-SNAPSHOT/) in a browser window. Check the liberty server logs for any problems. Check the RabbitMQ broker to confirm that the message has been placed onto the queue.

## Notes

* RabbitMQ JMS Client properties must be passed in the ConnectionFactoryProperties and the DestinationProperties of the GenericJMSRA. These must be capitalised as the GenericJMSRA code tries to match the property name to the setter method using map.containsKey() which is case sensitive.  
* Currently UnsupportedOperationExceptions are output when the ManagedConnection is closed by Liberty. This is because the GenericJMSRA attempts to set the message listener to null on the RMQSession but the RMQ JMS Client doesn't support that method.
