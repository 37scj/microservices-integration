FROM node:14 as builder
WORKDIR /app
COPY index.js /app
COPY package.json /app
RUN cd /app && npm install

FROM node:14
WORKDIR /app
COPY --from=builder /app /app
ENV NODE_RABBIT 'rabbit:5672'
RUN export TERM=xterm
CMD ["index.js"]
