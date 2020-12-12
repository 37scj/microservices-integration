# WEBSERVICES-RESTFUL-TECHNOLOGIES


# Documentação 

  -  http://localhost:8090/swagger-ui.html
  -  Assista a demonstração aqui: https://www.loom.com/share/b86948b290b8490c9c44e98019f11f91

# Base de dados H2
 - H2 Console  http://localhost:8090/h2-console
    - jdbc:h2:~/fiapstockdatabase;DB_CLOSE_ON_EXIT=FALSE
    
    
# Adicionando pre-drones na aplicação :

- Pre-adicionando drones na base
  - Altere na properties.yml a propriedade ddl-auto do hibernate para criar as tabela automaticamente para : create-drop 
  - Crie um arquivo "data.sql" dentro de : /src/main/resources
  - Passe a seguinte instrução no arquivo data.sql: 
                 
        insert into tb_drone (latitude, longitude, nome, temperatura, tracking, umidade, id) 
                values (123213.0, 3213123.0,'Drone',-1.6, false, 25.3, 1);
