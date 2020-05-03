package sample.kv;

import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.HashMap;

public class KVServiceImpl implements KVServiceGrpc.KVService {
    private final Logger log;
    private Map<String, String> values;
    
    public KVServiceImpl() {
	log = LoggerFactory.getLogger(getClass());
	values = new HashMap<>();
    }
    
    @Override
    public void setValue(KVApi.SetValueRequest request, StreamObserver<KVApi.SetValueResponse> responseObserver) {
	log.info("---- SetValue: {} => {}", request.getKey(), request.getValue());	
	values.put(request.getKey(), request.getValue());
	responseObserver.onNext(KVApi.SetValueResponse.newBuilder().build());
	responseObserver.onCompleted();
    }
    
    @Override
    public void getValue(KVApi.GetValueRequest request, StreamObserver<KVApi.GetValueResponse> responseObserver) {
	log.info("---- GetValue: {}", request.getKey());
	responseObserver.onNext(KVApi.GetValueResponse.newBuilder()
				.setValue(values.get(request.getKey()))
				.build());
	responseObserver.onCompleted();
    }
}
