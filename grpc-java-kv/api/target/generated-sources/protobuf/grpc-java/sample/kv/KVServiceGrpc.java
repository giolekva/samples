package sample.kv;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;

@javax.annotation.Generated("by gRPC proto compiler")
public class KVServiceGrpc {

  private KVServiceGrpc() {}

  public static final String SERVICE_NAME = "sample.kv.KVService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<sample.kv.KVApi.SetValueRequest,
      sample.kv.KVApi.SetValueResponse> METHOD_SET_VALUE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "sample.kv.KVService", "SetValue"),
          io.grpc.protobuf.ProtoUtils.marshaller(sample.kv.KVApi.SetValueRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(sample.kv.KVApi.SetValueResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<sample.kv.KVApi.GetValueRequest,
      sample.kv.KVApi.GetValueResponse> METHOD_GET_VALUE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "sample.kv.KVService", "GetValue"),
          io.grpc.protobuf.ProtoUtils.marshaller(sample.kv.KVApi.GetValueRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(sample.kv.KVApi.GetValueResponse.getDefaultInstance()));

  public static KVServiceStub newStub(io.grpc.Channel channel) {
    return new KVServiceStub(channel);
  }

  public static KVServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new KVServiceBlockingStub(channel);
  }

  public static KVServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new KVServiceFutureStub(channel);
  }

  public static interface KVService {

    public void setValue(sample.kv.KVApi.SetValueRequest request,
        io.grpc.stub.StreamObserver<sample.kv.KVApi.SetValueResponse> responseObserver);

    public void getValue(sample.kv.KVApi.GetValueRequest request,
        io.grpc.stub.StreamObserver<sample.kv.KVApi.GetValueResponse> responseObserver);
  }

  public static interface KVServiceBlockingClient {

    public sample.kv.KVApi.SetValueResponse setValue(sample.kv.KVApi.SetValueRequest request);

    public sample.kv.KVApi.GetValueResponse getValue(sample.kv.KVApi.GetValueRequest request);
  }

  public static interface KVServiceFutureClient {

    public com.google.common.util.concurrent.ListenableFuture<sample.kv.KVApi.SetValueResponse> setValue(
        sample.kv.KVApi.SetValueRequest request);

    public com.google.common.util.concurrent.ListenableFuture<sample.kv.KVApi.GetValueResponse> getValue(
        sample.kv.KVApi.GetValueRequest request);
  }

  public static class KVServiceStub extends io.grpc.stub.AbstractStub<KVServiceStub>
      implements KVService {
    private KVServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private KVServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KVServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new KVServiceStub(channel, callOptions);
    }

    @java.lang.Override
    public void setValue(sample.kv.KVApi.SetValueRequest request,
        io.grpc.stub.StreamObserver<sample.kv.KVApi.SetValueResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_SET_VALUE, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void getValue(sample.kv.KVApi.GetValueRequest request,
        io.grpc.stub.StreamObserver<sample.kv.KVApi.GetValueResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_VALUE, getCallOptions()), request, responseObserver);
    }
  }

  public static class KVServiceBlockingStub extends io.grpc.stub.AbstractStub<KVServiceBlockingStub>
      implements KVServiceBlockingClient {
    private KVServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private KVServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KVServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new KVServiceBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public sample.kv.KVApi.SetValueResponse setValue(sample.kv.KVApi.SetValueRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_SET_VALUE, getCallOptions(), request);
    }

    @java.lang.Override
    public sample.kv.KVApi.GetValueResponse getValue(sample.kv.KVApi.GetValueRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_VALUE, getCallOptions(), request);
    }
  }

  public static class KVServiceFutureStub extends io.grpc.stub.AbstractStub<KVServiceFutureStub>
      implements KVServiceFutureClient {
    private KVServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private KVServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KVServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new KVServiceFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<sample.kv.KVApi.SetValueResponse> setValue(
        sample.kv.KVApi.SetValueRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_SET_VALUE, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<sample.kv.KVApi.GetValueResponse> getValue(
        sample.kv.KVApi.GetValueRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_VALUE, getCallOptions()), request);
    }
  }

  private static final int METHODID_SET_VALUE = 0;
  private static final int METHODID_GET_VALUE = 1;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final KVService serviceImpl;
    private final int methodId;

    public MethodHandlers(KVService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SET_VALUE:
          serviceImpl.setValue((sample.kv.KVApi.SetValueRequest) request,
              (io.grpc.stub.StreamObserver<sample.kv.KVApi.SetValueResponse>) responseObserver);
          break;
        case METHODID_GET_VALUE:
          serviceImpl.getValue((sample.kv.KVApi.GetValueRequest) request,
              (io.grpc.stub.StreamObserver<sample.kv.KVApi.GetValueResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServerServiceDefinition bindService(
      final KVService serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
        .addMethod(
          METHOD_SET_VALUE,
          asyncUnaryCall(
            new MethodHandlers<
              sample.kv.KVApi.SetValueRequest,
              sample.kv.KVApi.SetValueResponse>(
                serviceImpl, METHODID_SET_VALUE)))
        .addMethod(
          METHOD_GET_VALUE,
          asyncUnaryCall(
            new MethodHandlers<
              sample.kv.KVApi.GetValueRequest,
              sample.kv.KVApi.GetValueResponse>(
                serviceImpl, METHODID_GET_VALUE)))
        .build();
  }
}
