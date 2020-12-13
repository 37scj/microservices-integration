# Microservices integration 
- REST
- RabbitMQ
- ReactJs

# Start Up docker things

## start docker 

### Portainer (management) as stack or compose (local)

Stack for Swarm: 
```shell
cd portainer
docker stack deploy -c portainer-agent-stack.yml portainer
```

Local compose:
```shell
cd portainer
docker-compose up -d
```

When ready you can access portainer by URL http://localhost:9000

## Drone Stack

### Start with docker compose


Stack for Swarm:
```shell
docker stack deploy -c docker-compose.yml microservices --prune
```


Local compose:
```shell
docker-compose up -d
```


# Documentação 

  -  http://localhost:8090/swagger-ui.html
  -  Assista a demonstração aqui: https://www.loom.com/share/b86948b290b8490c9c44e98019f11f91

# Base de dados H2
 - H2 Console  http://localhost:8090/h2-console
    - jdbc:h2:~/fiapstockdatabase;DB_CLOSE_ON_EXIT=FALSE

