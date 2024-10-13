# spring-boot-thymeleaf
### Full stack project 


## Getting started

### Back-end

> [!NOTE]
> This projects uses spring docker compose support. To be able to leverage it during your
> development, [Docker](https://www.docker.com/) is required.

> [!NOTE]
> For an improved developer experience, the development configuration of the projects uses the observability stack
> (Prometheus, Grafana, Loki, Tempo) in combination to docker compose support and spring dev-tools.
> This will allow you to monitor performance, query logs, follow traces and leverage fast reload times after changes.

1. Clone the backend repo:

   ```git clone https://github.com/JavaSoftwareEntwickler/spring-boot-thymeleaf.git```

2. Run the project from the IDE or through maven with ```dev``` profile active and wait for the containers to start:

   ```mvn spring-boot:run```


3. To check that app is up and running, visit the URL in your browser and check the index app:
   - http://localhost:8080
