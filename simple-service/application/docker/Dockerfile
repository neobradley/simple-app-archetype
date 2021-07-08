FROM        adoptopenjdk/openjdk11:alpine-jre
LABEL       app=simple-service
ENV         TZ=Asia/Taipei
RUN         ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
RUN         addgroup --system app && adduser --system --no-create-home --ingroup app app
RUN         apk add bash
RUN         mkdir -p /var/log/app
RUN         chmod 777 -R /var/log/app
USER        app
COPY        package/dependency     /opt/app
COPY        package/app            /opt/app
CMD         ["/opt/app/bin/application"]
