FROM nginx

VOLUME /home/app/projects/web

RUN mkdir -p /home/app/projects/web

WORKDIR /home/app/projects/web

COPY ./conf/nginx.conf /etc/nginx/nginx.conf

COPY ./html/dist /home/app/projects/web
