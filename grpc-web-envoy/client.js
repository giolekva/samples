const {HelloReq, HelloResp} = require('./api_pb.js');
const {HelloServiceClient} = require('./api_grpc_web_pb.js');

var helloClient = new HelloServiceClient('http://localhost:8080');

var req = new HelloReq();
req.setName('Bowser');

helloClient.hello(req, {}, function(err, resp) {
    document.getElementById('msg').innerHTML = resp.getMessage();
});
