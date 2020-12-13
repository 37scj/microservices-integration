FROM node as builder

WORKDIR /var/lib/app/
COPY ./package.json ./
#COPY ./yarn.lock ./
COPY ./src/ ./src
COPY ./public/ ./public

ARG PUBLIC_URL
RUN yarn --frozen-lockfile --no-cache --production --ignore-optional
RUN yarn run build

FROM nginx
#COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=builder /var/lib/app/build/ /usr/share/nginx/html
ARG REACT_APP_GMAPSKEY
EXPOSE 80
