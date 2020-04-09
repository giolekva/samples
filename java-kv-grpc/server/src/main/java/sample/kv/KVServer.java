package sample.kv;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public final class KVServer {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final int port;
    private final Server server;
    
    public KVServer(int port) {
	this.port = port;
	this.server = ServerBuilder.forPort(port)
	    .addService(KVServiceGrpc.bindService(new KVServiceImpl()))
	    .build();
    }    
    
    public void start() throws IOException {
	server.start();
	log.info("Server started, listening on {}", port);
	
	Runtime.getRuntime().addShutdownHook(new Thread() {
		
		@Override
		public void run() {
		    // Use stderr here since the logger may has been reset by its JVM shutdown hook.
		    System.err.println("Shutting down gRPC server since JVM is shutting down");
		    KVServer.this.stop();
		    System.err.println("Server shut down");
		}
	    });
    }

    public void stop() {
	if (server != null) {
	    server.shutdown();
	}
    }    
    
    private void blockUntilShutdown() throws InterruptedException {
	if (server != null) {
	    server.awaitTermination();
	}
    }    
    
    public static void main(String[] args) throws Exception {
	KVServer server = new KVServer(Integer.valueOf(args[0]));
	server.start();
	server.blockUntilShutdown();
    }
}
