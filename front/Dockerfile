FROM node:18.12.1

WORKDIR /frontend

COPY ./package*.json /frontend/

RUN npm install

RUN npm install -g serve

COPY / /frontend

RUN npm run build

CMD ["serve", "-s", "dist","-l","5000"]

EXPOSE 5000