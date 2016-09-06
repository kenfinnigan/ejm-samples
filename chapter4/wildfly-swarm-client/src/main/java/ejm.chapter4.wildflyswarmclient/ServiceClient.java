package ejm.chapter4.wildflyswarmclient;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.naming.InitialContext;

/**
 * @author Ken Finnigan
 */
public interface ServiceClient<T> {
//    <R> CompletableFuture<R> async(Class<R> returnType, AsyncResponse response);

    default <U> void execInThread(Supplier<U> restMethod, Consumer<U> handler, Consumer<Throwable> exceptionHandler) throws Exception {
        CompletableFuture
                .supplyAsync(restMethod, executorService())
                .thenAccept(handler)
                .exceptionally(t -> {
                    exceptionHandler.accept(t);
                    return null;
                });
    }

    default ManagedExecutorService executorService() throws Exception {
        InitialContext ctx = new InitialContext();
        return (ManagedExecutorService) ctx.lookup("java:jboss/ee/concurrency/executor/default");
    }
}
