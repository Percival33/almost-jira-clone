########################### DEV ##################################
# base image
FROM node:12.2.0-alpine

WORKDIR /app

# copy both 'package.json' and 'package-lock.json' (if available)
COPY package*.json ./

# install project dependencies
RUN npm install

# copy project files and folders to the current working directory (i.e. 'app' folder)
COPY . .

# build app for production with minification
RUN npm run build

CMD ["npm", "run", "serve"]

########################### PROD ##################################
#FROM node:latest as build-stage
#WORKDIR /app
#COPY package*.json ./
#RUN npm install
#COPY ./ .
#RUN npm run build
#
#FROM nginx as production-stage
#RUN mkdir /app
#COPY --from=build-stage /app/dist /app
#COPY nginx.conf /etc/nginx/nginx.conf