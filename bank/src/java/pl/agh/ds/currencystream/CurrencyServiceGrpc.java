package pl.agh.ds.currencystream;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.11.0)",
    comments = "Source: currency_stream.proto")
public final class CurrencyServiceGrpc {

  private CurrencyServiceGrpc() {}

  public static final String SERVICE_NAME = "CurrencyService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @Deprecated // Use {@link #getGetCurrencyValuesMethod()} instead.
  public static final io.grpc.MethodDescriptor<CurrencyStream.Currency,
      CurrencyStream.CurrencyValue> METHOD_GET_CURRENCY_VALUES = getGetCurrencyValuesMethodHelper();

  private static volatile io.grpc.MethodDescriptor<CurrencyStream.Currency,
      CurrencyStream.CurrencyValue> getGetCurrencyValuesMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<CurrencyStream.Currency,
      CurrencyStream.CurrencyValue> getGetCurrencyValuesMethod() {
    return getGetCurrencyValuesMethodHelper();
  }

  private static io.grpc.MethodDescriptor<CurrencyStream.Currency,
      CurrencyStream.CurrencyValue> getGetCurrencyValuesMethodHelper() {
    io.grpc.MethodDescriptor<CurrencyStream.Currency, CurrencyStream.CurrencyValue> getGetCurrencyValuesMethod;
    if ((getGetCurrencyValuesMethod = CurrencyServiceGrpc.getGetCurrencyValuesMethod) == null) {
      synchronized (CurrencyServiceGrpc.class) {
        if ((getGetCurrencyValuesMethod = CurrencyServiceGrpc.getGetCurrencyValuesMethod) == null) {
          CurrencyServiceGrpc.getGetCurrencyValuesMethod = getGetCurrencyValuesMethod = 
              io.grpc.MethodDescriptor.<CurrencyStream.Currency, CurrencyStream.CurrencyValue>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "CurrencyService", "GetCurrencyValues"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  CurrencyStream.Currency.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  CurrencyStream.CurrencyValue.getDefaultInstance()))
                  .setSchemaDescriptor(new CurrencyServiceMethodDescriptorSupplier("GetCurrencyValues"))
                  .build();
          }
        }
     }
     return getGetCurrencyValuesMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @Deprecated // Use {@link #getGetAvailableCurrenciesMethod()} instead.
  public static final io.grpc.MethodDescriptor<CurrencyStream.AvailableCurrenciesRequest,
      CurrencyStream.CurrencyList> METHOD_GET_AVAILABLE_CURRENCIES = getGetAvailableCurrenciesMethodHelper();

  private static volatile io.grpc.MethodDescriptor<CurrencyStream.AvailableCurrenciesRequest,
      CurrencyStream.CurrencyList> getGetAvailableCurrenciesMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<CurrencyStream.AvailableCurrenciesRequest,
      CurrencyStream.CurrencyList> getGetAvailableCurrenciesMethod() {
    return getGetAvailableCurrenciesMethodHelper();
  }

  private static io.grpc.MethodDescriptor<CurrencyStream.AvailableCurrenciesRequest,
      CurrencyStream.CurrencyList> getGetAvailableCurrenciesMethodHelper() {
    io.grpc.MethodDescriptor<CurrencyStream.AvailableCurrenciesRequest, CurrencyStream.CurrencyList> getGetAvailableCurrenciesMethod;
    if ((getGetAvailableCurrenciesMethod = CurrencyServiceGrpc.getGetAvailableCurrenciesMethod) == null) {
      synchronized (CurrencyServiceGrpc.class) {
        if ((getGetAvailableCurrenciesMethod = CurrencyServiceGrpc.getGetAvailableCurrenciesMethod) == null) {
          CurrencyServiceGrpc.getGetAvailableCurrenciesMethod = getGetAvailableCurrenciesMethod = 
              io.grpc.MethodDescriptor.<CurrencyStream.AvailableCurrenciesRequest, CurrencyStream.CurrencyList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "CurrencyService", "GetAvailableCurrencies"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  CurrencyStream.AvailableCurrenciesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  CurrencyStream.CurrencyList.getDefaultInstance()))
                  .setSchemaDescriptor(new CurrencyServiceMethodDescriptorSupplier("GetAvailableCurrencies"))
                  .build();
          }
        }
     }
     return getGetAvailableCurrenciesMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CurrencyServiceStub newStub(io.grpc.Channel channel) {
    return new CurrencyServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CurrencyServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new CurrencyServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CurrencyServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new CurrencyServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class CurrencyServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getCurrencyValues(CurrencyStream.Currency request,
        io.grpc.stub.StreamObserver<CurrencyStream.CurrencyValue> responseObserver) {
      asyncUnimplementedUnaryCall(getGetCurrencyValuesMethodHelper(), responseObserver);
    }

    /**
     */
    public void getAvailableCurrencies(CurrencyStream.AvailableCurrenciesRequest request,
        io.grpc.stub.StreamObserver<CurrencyStream.CurrencyList> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAvailableCurrenciesMethodHelper(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetCurrencyValuesMethodHelper(),
            asyncServerStreamingCall(
              new MethodHandlers<
                CurrencyStream.Currency,
                CurrencyStream.CurrencyValue>(
                  this, METHODID_GET_CURRENCY_VALUES)))
          .addMethod(
            getGetAvailableCurrenciesMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                CurrencyStream.AvailableCurrenciesRequest,
                CurrencyStream.CurrencyList>(
                  this, METHODID_GET_AVAILABLE_CURRENCIES)))
          .build();
    }
  }

  /**
   */
  public static final class CurrencyServiceStub extends io.grpc.stub.AbstractStub<CurrencyServiceStub> {
    private CurrencyServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CurrencyServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected CurrencyServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CurrencyServiceStub(channel, callOptions);
    }

    /**
     */
    public void getCurrencyValues(CurrencyStream.Currency request,
        io.grpc.stub.StreamObserver<CurrencyStream.CurrencyValue> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetCurrencyValuesMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAvailableCurrencies(CurrencyStream.AvailableCurrenciesRequest request,
        io.grpc.stub.StreamObserver<CurrencyStream.CurrencyList> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAvailableCurrenciesMethodHelper(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CurrencyServiceBlockingStub extends io.grpc.stub.AbstractStub<CurrencyServiceBlockingStub> {
    private CurrencyServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CurrencyServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected CurrencyServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CurrencyServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<CurrencyStream.CurrencyValue> getCurrencyValues(
        CurrencyStream.Currency request) {
      return blockingServerStreamingCall(
          getChannel(), getGetCurrencyValuesMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public CurrencyStream.CurrencyList getAvailableCurrencies(CurrencyStream.AvailableCurrenciesRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetAvailableCurrenciesMethodHelper(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CurrencyServiceFutureStub extends io.grpc.stub.AbstractStub<CurrencyServiceFutureStub> {
    private CurrencyServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CurrencyServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected CurrencyServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CurrencyServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<CurrencyStream.CurrencyList> getAvailableCurrencies(
        CurrencyStream.AvailableCurrenciesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAvailableCurrenciesMethodHelper(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_CURRENCY_VALUES = 0;
  private static final int METHODID_GET_AVAILABLE_CURRENCIES = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CurrencyServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CurrencyServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_CURRENCY_VALUES:
          serviceImpl.getCurrencyValues((CurrencyStream.Currency) request,
              (io.grpc.stub.StreamObserver<CurrencyStream.CurrencyValue>) responseObserver);
          break;
        case METHODID_GET_AVAILABLE_CURRENCIES:
          serviceImpl.getAvailableCurrencies((CurrencyStream.AvailableCurrenciesRequest) request,
              (io.grpc.stub.StreamObserver<CurrencyStream.CurrencyList>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class CurrencyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CurrencyServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return CurrencyStream.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CurrencyService");
    }
  }

  private static final class CurrencyServiceFileDescriptorSupplier
      extends CurrencyServiceBaseDescriptorSupplier {
    CurrencyServiceFileDescriptorSupplier() {}
  }

  private static final class CurrencyServiceMethodDescriptorSupplier
      extends CurrencyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CurrencyServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (CurrencyServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CurrencyServiceFileDescriptorSupplier())
              .addMethod(getGetCurrencyValuesMethodHelper())
              .addMethod(getGetAvailableCurrenciesMethodHelper())
              .build();
        }
      }
    }
    return result;
  }
}
