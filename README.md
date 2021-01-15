# Project description

Solução para o agronegócio que coleta dados via sensores de temperatura e umidade. Esses sensores são instalados em um drone com uma altíssima autonomia de energia, A cada 10 segundos são enviados para o droneDTO broker os dados de temperatura e umidade capturado naquele instante. Além da primeira função também é possível acionar o rastreamento do drone. Esta funcionalidade resulta na localização do equipamento em um mapa.

# Microservices integration 

usage in this project:
- MySQL
- RabbitMQ
- Java
- ReactJs
- Docker (compose)

# Start Up docker things

## setup

First setup the environment:
```shell
export MS_VERSION=0.1.1-SNAPSHOT
export WEB_VERSION=0.1.0

echo $REACT_APP_GMAPSKEY $MS_VERSION $WEB_VERSION
```

## building images
### FRONT END
```
docker build icc-react-web-app/ -t gabrielltr/icc-react-web-app:0.1.0
docker build droneBackend/ -t gabrielltr/icc-react-web-app:0.1.1-SNAPSHOT
```

## Drone compose up

Local compose:
```shell
docker-compose up -d
```

## health check

To check backend health check

http://localhost:8090/actuator/health

this show health of this backend and if there is a rabbit and mysql connection

```json
{
   "status": "UP",
   "components": {
      "db": {
      "status": "UP",
      "details": {
         "database": "MySQL",
         "validationQuery": "isValid()"
         }
      },
      "diskSpace": {
         "status": "UP",
         "details": {
         "total": 269490393088,
         "free": 233015492608,
         "threshold": 10485760,
         "exists": true
         }
      },
      "ping": {
         "status": "UP"
      },
      "rabbit": {
         "status": "UP",
         "details": {
         "version": "3.8.9"
      }
      }
   }
}
```

# Documentação Swagger v2

  -  http://localhost:8090/swagger-ui.html
  -  Assista a demonstração em detalhes aqui:
   
       https://www.loom.com/share/b86948b290b8490c9c44e98019f11f91
       https://www.loom.com/share/30e400ed77334ea288f9bafebb6d4580
       https://www.loom.com/share/8edf32012d624960898af84f86e10149
       https://www.loom.com/share/64bb64d6feea469b8621d4a602ca2e5a -> Load Balance
       
  
---
# Using swarm

## Init a swarm

    docker swarm init

### Add worker to your cluster
```
docker swarm join-token worker
```

The response will be like above, then you can add your workers
```
docker swarm join --token SWMTKN-1-22h12q8oxptbi8ivc143mx07yixzqirdww0h641mewq3zs909h-eqyfsev9tff5mj8s83uj32bw1 192.168.65.3:2377
```

# Portainer (management) as stack or compose (local)

## Portainer stack for Swarm:
```shell
cd portainer
docker stack deploy -c portainer-agent-stack.yml portainer
```

## portainer compose
   ```shell
cd portainer
docker-compose up -d
```

When ready you can access portainer by URL http://localhost:9000

## Microservices integration as stack

```shell
docker stack deploy -c docker-compose.yml microservices --prune
```

