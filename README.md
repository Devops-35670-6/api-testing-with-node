# Small Nodejs API with local testing in Node.js
- build the application with npm install
- Execute application with npm start
- Execute tests with npm test

# TEST2
```bash
docker build -t node-docker .
docker run --rm -d --name node-docker -p 5000:5000 node-docker:latest
docker stop node-docker
```