package lab.heisenbug.sandbox.payroll.repositories;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public interface ReactiveRepository {

    static <T extends Object> Flux<T> createFlux(Supplier<List<T>> supplier) {
        return Flux.defer(() -> Flux.fromIterable(supplier.get())).publishOn(Schedulers.elastic());
    }

    static <T extends Object> Mono<T> createMono(Supplier<T> supplier) {
        return Mono.defer(() -> Mono.fromSupplier(supplier)).publishOn(Schedulers.elastic());
    }

    static <T extends Object> Mono<Optional<T>> createOptionalMono(Supplier<Optional<T>> supplier) {
        return Mono.defer(() -> Mono.fromSupplier(supplier)).publishOn(Schedulers.elastic());
    }
}