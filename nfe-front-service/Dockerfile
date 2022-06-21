FROM node:18-alpine3.15

WORKDIR /nfe-front-service

COPY ./package.json ./



RUN npm install -g npm@8.12.2
RUN npm install -g svgo

RUN npm install

COPY ./public ./public
COPY ./src ./src

EXPOSE 3000

CMD ["npm", "start"]

