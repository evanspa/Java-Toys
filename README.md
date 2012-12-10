JavaToys
========

A collection of various 'toy' projects written in Java for exploring various facets, including: (1) patterns, (2) testing, (3) distributed systems using web services, (4) multi-threading and (5) security.

The following is a brief description of each sub-project:

## JToy-JAXWSwithCDI

Builds a simple JAX-WS (SOAP-based) web service that leverages the new (relative to when this code was written) CDI (Contexts and Dependency Injection) capability of JEE.

## JToy-MultiThreadedLaundry

A simple little program that leverages multi-threading in a basic producer/consumer coordination context.  Java's 'wait()' and 'notify()' mechanisms are leveraged to attain thread coordination.

## JToy-SecureWebService

A SOAP-based web service built using Spring's WS-Security support to encrypted and digitally signed web service invocations.

## JToy-SecurityPolicy

A simple project that exemplifies how to exploit the mechanisms of the JVM for fine-grain tuning of security behaviors.

## JToy-SimpleTimer

A simple project that demonstrates usage of Java's javax.swing.Timer class.

## JToy-TestInfectedWebService

A SOAP-based web service that is fully unit tested; using standard toolkits such as JUnit, as well as mock techniques using EasyMock, as well as leveraging additional frameworks for testing the qualify of the other project artifacts (e.g., the XML-based configuration files).
