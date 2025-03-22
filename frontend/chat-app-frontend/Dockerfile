FROM node:20-alpine as build

WORKDIR /app

# Copy package files
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy source code
COPY . .

# Build the application
RUN npm run build

# Serve with Node.js
FROM node:20-alpine

WORKDIR /app

COPY --from=build /app/dist/chat-app-frontend /app/dist/chat-app-frontend
COPY --from=build /app/package*.json /app/

RUN npm install --production

# Expose port 4000 (matches your server.ts)
EXPOSE 4000

# Start the server using the correct script
CMD ["npm", "run", "serve:ssr:chat-app-frontend"] 