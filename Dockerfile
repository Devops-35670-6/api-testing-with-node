FROM node:14.15.4 as base

WORKDIR /app

COPY package.json package.json
COPY package-lock.json package-lock.json

RUN npm install

COPY . .
CMD ["npm", "start"]