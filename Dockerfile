FROM amazoncorretto:21
LABEL maintainer="lisbethsoriagrefa1608@gmail.com"
ENV SPRING_BOOT_APP     transactionalapi-0.0.1-SNAPSHOT.jar
ENV SPRING_BOOT_JAR     transactionalapi-0.0.1-SNAPSHOT.jar
ENV SPRING_BOOT_PATH    /opt/springboot/
ENV LANG=es_EC.UTF-8

RUN echo LANG=es_EC.UTF-8 >> /etc/environment && echo LC_ALL=es_EC.UTF-8 >> /etc/environment
RUN echo "LANG=\"es_EC.UTF-8\"" > /etc/locale.conf
RUN ln -s -f /usr/share/zoneinfo/America/Guayaquil /etc/localtime

WORKDIR ${SPRING_BOOT_PATH}

COPY ./target/${SPRING_BOOT_JAR} ${SPRING_BOOT_APP}

EXPOSE 8080

CMD java -jar ${SPRING_BOOT_PATH}${SPRING_BOOT_APP}
