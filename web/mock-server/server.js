
const jsonServer = require('json-server');
const server = jsonServer.create();

server.use(jsonServer.defaults());

const router = jsonServer.router('db.json');
server.use('/api', router);

server.listen(3000);
