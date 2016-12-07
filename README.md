# InnovationLab
## General Idea ##


#Installation
## Prerequisites ##
* Java 8 SE Development Kit (binaries on PATH, JAVA_HOME set)
* Maven (mvn on PATH)
* Ant (on PATH)
* Passion for software development ;)

## Quick Introduction ##

* git clone whole repo
* go to innovationlab
* run ```ant setup_project```
* browse to http://localhost:8080/innovationlab

## Architecture ##

For Architecture, see projects `lirejarp` and `lj-project builder`. 

The following class diagramm shows the general architecture.

![](https://github.com/witchpou/innovationlab/blob/master/docs/images/classDiagramm.png?raw=true)

## Sub Projects ##
InnovationLab contains the innovationlab application itself and the application server as well as builds scripts to install, start and stop the application.
* [InnovationLab project] (https://github.com/witchpou/innovationlab/tree/master/innovationlab) which contains the actual project template and is the base for developing your application. It consists of following subprojects:
  * [persistence]: entities and services for database access
  * [business]: REST API to connect the frontend with the backend
  * [frontend]: HTML and AngularJS based frontend
  * [packagewar]: Used to create the war-file
* [TomEE] (https://github.com/witchpou/innovationlab/tree/master/tomee) provides a JEE runtime for your application. 

The following component diagram shows the structure of the project.

![](https://github.com/witchpou/innovationlab/blob/master/docs/images/komponentendiagrammPS.png?raw=true)

