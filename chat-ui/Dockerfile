FROM node:14

# Create app directory
WORKDIR /usr/src/app

COPY . .

# If you are building your code for production
RUN npm i
RUN npm run build
RUN npm prune production

# Bundle app source
#COPY . .

EXPOSE 3000
CMD [ "npm", "start" ]