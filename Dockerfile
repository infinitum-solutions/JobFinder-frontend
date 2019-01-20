FROM nginx:alpine
COPY nginx.conf /etc/nginx/nginx.conf2

WORKDIR /usr/share/nginx/html
COPY dist/JobFinder-frontend .

EXPOSE 80
