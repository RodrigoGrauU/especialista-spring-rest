#pegando a imagem base
FROM adoptopenjdk/openjdk12:jre-12.0.2_10
#definindo o diretorio de trabalho onde as demais instrucoes serao executadas
WORKDIR /app

COPY target/*.jar /app/api.jar

EXPOSE 8080

#recomendado colocar como array o comando
CMD ["java", "-jar", "api.jar"]
