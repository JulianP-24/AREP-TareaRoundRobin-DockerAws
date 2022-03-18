# Taller RoundRobin-DockeryAws

## Despliege en localhost
Una vez crado el proyecto en nuestro computador, se debe desplegar las imagenes y los contenedores, con lo
una vez ubicados en la raiz del proyecto escribimos el siguiente comando:

```docker-compose up -d```

Este comando lo que hace es generar una configuracion automatica dada en un archivo llamado ```docker-compose.yml```
que tiene el siguiente contenido

   ```
       version: '2'

        services:
          db:
            image: mongo:latest
            container_name: mongoDB
            environment:
              MONGO_INITDB_DATABASE: RoundRobinService
              MONGO_INITDB_ROOT_USERNAME: julian
              MONGO_INITDB_ROOT_PASSWORD: julian24
            volumes:
              - ./init-mongo.js:/docker-entrypoint-initdb.d/init-mongo.js:ro
              - mongodb:/data/db
              - mongodb_config:/data/configdb
            ports:
              - 27017:27017
            command: mongod
        
        
          app-lb-roundrobin:
            build:
              context: ./RoundRobin
              dockerfile: Dockerfile
            depends_on:
              - logservice1
              - logservice2
              - logservice3
            container_name: app-lb-roundrobin
            ports:
              - "35000:6000"
        
          logservice1:
            build:
              context: ./RoundRobin
              dockerfile: Dockerfile
            depends_on:
              - db
            container_name: logservices1
            ports:
              - "35001:6000"
        
          logservice2:
            build:
              context: ./RoundRobin
              dockerfile: Dockerfile
            depends_on:
              - db
            container_name: logservices2
            ports:
              - "35002:6000"
        
          logservice3:
            build:
              context: ./RoundRobin
              dockerfile: Dockerfile
            depends_on:
              - db
            container_name: logservices3
            ports:
              - "35003:6000"
        
        
        volumes:
          mongodb:
          mongodb_config:
   ```

Una vez ejecutado el comando anterior, se obtiene en consola lo siguiente

![](img/CreacionDespliegeImagenes.png)

Para revisar por consola que se hallan creado las imagenes utilizamos el siguiente comando

   ```
      docker images
   ```
El cual nos mostrara el siguiente resultado

![](img/verificacionImagenes.png)

Para saber por consola si estan corriendo, se utiliza el siguiente comando

   ```
      docker ps
   ```
Obtenemos el siguiente resultado

![](img/verificarQueCorren.png)


Utilizando el docker desktop tambien podemos verificar de una forma grafica, la creacion de las imagenes y los
contenedores y si estos estan corriendo, como se muestra a continuacion

![](img/dockerhub.png)


Despues podremos acceder al proyecto desplegado en nuestro docker local, consultando la siguiente url

   ```
      http://localhost:35000/
   ```
Donde el puerto: 35000 es el puerto al que se enlazo con uno fisico de la maquina local y que este nos permite
acceder, como se muestra a continuacion