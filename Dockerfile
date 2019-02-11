FROM nginx:alpine
COPY config.nginx /etc/nginx/nginx.conf

WORKDIR /usr/share/nginx/html
COPY dist/JobFinder-frontend .

VOLUME /var/opt/jobfinder-frontend
VOLUME /var/log/jobfinder-frontend

EXPOSE 80
EXPOSE 443
