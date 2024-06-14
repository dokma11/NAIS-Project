<h1 align="center">NAIS project</h1>

<div>
    <img src="https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot"/>
    <img src="https://img.shields.io/badge/Neo4j-018bff?style=for-the-badge&logo=neo4j&logoColor=white"/>
    <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white"/>
    <img src="https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white"/>
    <img src="https://img.shields.io/badge/Apache_Kafka-231F20?style=for-the-badge&logo=apache-kafka&logoColor=white"/>
    <img src="https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"/>
</div>

# Overview:
This is a project for Advanced Information Systems Architectures (Napredne arhitekture informacionih sistema - NAIS 👌👌👌👌👌) course. 

Frontend project can be found [here](https://github.com/dokma11/Museum-Information-System-Frontend).

Backend project can be found [here](https://github.com/dokma11/Museum-Information-System-Backend).

# Content
- <b>Eureka service:</b> A service registry for service discovery, enabling different microservices to locate and communicate with each other dynamically within a distributed system. 
- <b>Gateway service:</b> Serves as an entry point for clients, routing and managing requests to various backend services.
- <b>Graph Database service:</b> A service used as a recommendation system for tours. Tours are recommended usually
by their categories, exhibitions, exhibition themes.
Users also get recommended tours that other users have purchased previously.
Users purchase history is being tracked, so that the recommendation system can be credible. Neo4j database is used to implement these functionalities.
- <b>Museum service:</b> Necessary part of the backend project, so that SAGA pattern could be applied.

# Technologies:
- <b>Java:</b> Programming language used in the project.
- <b>Spring boot:</b> Framework used to build and run the backend application.
- <b>PostgreSQL:</b> Database system used for data storage and retrieval.
- <b>Neo4j:</b> Graph database used in tours microservice.
- <b>Docker:</b> Containerization tool to ensure consistent environments.
- <b>Maven:</b> Build automation and dependency management tool.
- <b>Kafka:</b> Apache Kafka is a distributed event streaming platform used for building real-time data pipelines and streaming applications.

# Getting started:

<h3>Prerequisites</h3>

- <b>Git:</b> Ensure you have Git installed. You can download it from [here](https://git-scm.com/downloads).
- <b>Docker:</b> Ensure you have Docker installed. You can download it from [here](https://docs.docker.com/desktop/install/windows-install/).

<h3>Steps</h3>

1. <b>Clone the repository:</b> <br>
git clone https://github.com/dokma11/NAIS-Project.git
2. <b>Navigate to the root directory</b>
3. <b>Run docker compose command:</b> <br>
docker compose up

# Authors:
- [Nina Kuzminac RA 119/2020](https://github.com/kuzminacc)
- [Vukašin Dokmanović RA 89/2020](https://github.com/dokma11)
