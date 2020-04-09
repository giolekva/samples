package sample.kv;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public final class Client {

    public static void main(String[] args) {
	Logger log = LoggerFactory.getLogger(Client.class);
	
	String serverAddress = args[0];
	ManagedChannel channel = ManagedChannelBuilder.forTarget(serverAddress)
	    .usePlaintext(true) // - for debug only
	    .build();
	KVServiceGrpc.KVServiceBlockingClient blockingStub = KVServiceGrpc.newBlockingStub(channel);

	KVApi.SetValueRequest setRequest = KVApi.SetValueRequest.newBuilder()
	    .setKey("foo")
	    .setValue("bar")
	    .build();
	try {
	    KVApi.SetValueResponse setResponse = blockingStub.setValue(setRequest);
	    log.info("--- Set value: {} => {}", setRequest.getKey(), setRequest.getValue());	    
	} catch (StatusRuntimeException e) {
	    log.error("RPC failed: {}", e.getStatus());
	    return;
	}
	
	KVApi.GetValueRequest getRequest = KVApi.GetValueRequest.newBuilder()
	    .setKey("foo")
	    .build();
	try {
	    KVApi.GetValueResponse getResponse = blockingStub.getValue(getRequest);
	    log.info("--- Got value: {}", getResponse.getValue());
	} catch (StatusRuntimeException e) {
	    log.error("RPC failed: {}", e.getStatus());
	    return;
	}

	try {
	    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	} catch (InterruptedException e) {
	    log.error("Could not close connection");
	}
  }
}
