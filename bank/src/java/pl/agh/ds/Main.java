package pl.agh.ds;

import BankAccountServices.BankAccountFactory;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Identity;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import pl.agh.ds.currencystream.CurrencyServiceGrpc;
import pl.agh.ds.currencystream.CurrencyStream;
import pl.agh.ds.ice.BankAccountFactoryImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class Main {

    public static Map<String, Float> currencies = new HashMap<>();

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("0.0.0.0", 50051)
                .usePlaintext()
                .build();

        CurrencyServiceGrpc.CurrencyServiceBlockingStub stub = CurrencyServiceGrpc.newBlockingStub(channel);
        CurrencyServiceGrpc.CurrencyServiceStub asyncStub = CurrencyServiceGrpc.newStub(channel);


        CurrencyStream.CurrencyList currencyData =
                stub.getAvailableCurrencies(CurrencyStream.AvailableCurrenciesRequest.newBuilder().build());

        System.out.println("Currencies retrieved");

        List<CurrencyStream.Currency> currencyList =  currencyData.getCurrenciesList();
        for (CurrencyStream.Currency c : currencyList) {
            System.out.println(c.getCode());
            currencies.put(c.getCode(), c.getValue());
            asyncStub.getCurrencyValues(c, new StreamObserver<CurrencyStream.CurrencyValue>() {
                @Override
                public void onNext(CurrencyStream.CurrencyValue value) {
                    System.out.println("New value for " + c.getCode() + ": " + value.getValue());
                    currencies.replace(c.getCode(), value.getValue());
                }

                @Override
                public void onError(Throwable t) {
                    System.err.println(t.toString());
                }

                @Override
                public void onCompleted() {
                    System.out.println("Currency value stream has ended");
                }
            });
        }


        Communicator communicator = Util.initialize();
        Runtime.getRuntime().addShutdownHook(new Thread(communicator::destroy));

        ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints(
                        "BankAccountService", "default -p 10000");

        BankAccountFactory factory = new BankAccountFactoryImpl();
        adapter.add(factory, Util.stringToIdentity("BankAccountFactory"));
        adapter.activate();

        communicator.waitForShutdown();
    }
}
