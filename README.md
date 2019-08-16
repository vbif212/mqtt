# mqtt-chat
<b>Deployment instruction</b>
1. mvn clean install dockerfile:build<br>
2. For change client parameters: mvn clean install -Dusername=yourusername -Dwill=yourwillmessage -Duri=uriofmosqittoserver
3. docker-compose up -d
4. java -jar client/target/client.jar
