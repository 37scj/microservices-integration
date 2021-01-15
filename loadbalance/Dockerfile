FROM nginx:1.19

# HTTP
EXPOSE 80
# HTTPS
#EXPOSE 443

COPY nginx.conf /etc/nginx/nginx.conf

CMD ["nginx-debug", "-g", "daemon off;"]
