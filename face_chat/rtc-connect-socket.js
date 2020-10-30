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

        var clientsInRoom = io.sockets.adapter.rooms[channel];
        var numClients = clientsInRoom ? Object.keys(clientsInRoom.sockets).length : 0;

        io.sockets.in(channel).emit('new member', numClients);
    });

    socket.on('new member', channel => {
        io.sockets.in(channel).emit('alert');
    })

    socket.on('alert member', info => {
        io.sockets.in(info.channel).emit('member', info.member);
    })
});