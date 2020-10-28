var nodeStatic = require('node-static')
var http = require('http');
var socketIO = require('socket.io');

var fileServer = new(nodeStatic.Server)();

var app = http.createServer(function(req, res) {
    fileServer.serve(req, res);
}).listen(8080);

var io = socketIO.listen(app);

io.sockets.on('connection', function(socket) {

    socket.on('offer connect', function(connect) {
        var channel = connect.channel;

        io.sockets.in(channel).emit('sender info', connect);
    });

    socket.on('answer connect', function(connect) {
        var channel = connect.channel;

        io.sockets.in(channel).emit('receiver info', connect);
    });

    socket.on('join channel', function(channel) {
        socket.join(channel);
    });
});