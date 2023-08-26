# <img src="https://devkico.itexto.com.br/wp-content/uploads/2014/08/spring-boot-project-logo-300x270.png" width="40"> Spring Boot | <img src="https://www.alura.com.br/assets/api/cursos/kafka-introducao-a-streams-em-microservicos.svg" width="40"> Kafka | <img src="https://www.alura.com.br/assets/api/cursos/docker-criando-gerenciando-containers.svg" width="40"> Docker

## <p>Produtores, Consumidores e streams</p>
<img src="https://i.imgur.com/Dyn3DRP.png" width="800"><br>

** docker-compose up -d <br><img src="https://i.imgur.com/gnZMF7K.png" width="800"><br>
<em>arquivo docker-compose.yml, baixando e subindo o kafka, zookeeper e o kafka-ui, acabei não utilizando o kafka-ui, mas baixei.</em><hr>

** Docker Desktop <br><img src="https://i.imgur.com/UvczTHE.png" width="800"> 
<br><em>três containeres, zookeeper, kafka e kafka-ui, também utilizei a ide para o kafka Offset Explorer, apenas para ver as mensagen</em><hr>

** Offset Explorer <br><img src="https://i.imgur.com/AF3lARS.png" width="800"> 
<br><em>Cluster, contendo os tópicos disponíveis e também os consumers</em><hr>

**  Lista os tópicos
  .\bin\kafka-topics.sh --bootstrap-server 127.0.0.1:9092 --describe

** Altera quantidade de partições
  .\kafka-topics.sh --alter --zookeeper localhost:2181 --topic ECOMMERCE_NEW_ORDER --partitions 4

** Para Salvar o registro
  Crie duas pastas na raiz da pasta kakfa chama data, dentroi dela, crie duas, uma chamada kafka e outra zookeeper
  Após criar os caminhos, edite os arquivos zookeeper.properties e server.properties, ambos se encontra na pasta do kafka:
  ** # A comma separated list of directories under which to store log files -> log.dirs=\\kafka\\kafka_2.13-3.5.1\\data\\kafka
  ** # the directory where the snapshot is stored -> dataDir=C:\\kafka\\kafka_2.13-3.5.1\\data\\zookeeper

