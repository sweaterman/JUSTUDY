FROM node:18.12.1

WORKDIR /frontend

COPY ./justudy-front/package*.json /frontend/

RUN npm install

RUN npm install -g serve

COPY /justudy-front/ /frontend

RUN npm run build

CMD ["serve", "-s", "dist","-l","5000"]

EXPOSE 5000