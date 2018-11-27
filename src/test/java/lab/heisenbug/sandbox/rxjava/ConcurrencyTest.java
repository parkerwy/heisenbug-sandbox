package lab.heisenbug.sandbox.rxjava;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Observable;

public class ConcurrencyTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConcurrencyTest.class);

    @Test
    public void shouldBookTicket() throws Exception {
        Observable<String> flight = rxLookupFlight("XXX123");
        Observable<String> passenger = rxLookupPassenger("Parker");
        Observable<String> ticket = flight.zipWith(passenger, (f, p) -> bookTicket(f, p));
        ticket.subscribe(LOGGER::info);
    }

    private String lookupFlight(String flightNo) {
        LOGGER.info("look up flight for {}", flightNo);
        return "Flight " + flightNo;
    }

    private String lookupPassenger(String id) {
        LOGGER.info("look up passenger for {}", id);
        return "Passenger " + id;
    }

    private String bookTicket(String flight, String passenger) {
        LOGGER.info("book ticket for {} on {}", passenger, flight);
        return passenger + "@" + flight;
    }

    private Observable<String> rxLookupFlight(String flightNo) {
        return Observable.defer(() -> Observable.just(lookupFlight(flightNo)));
    }

    private Observable<String> rxLookupPassenger(String id) {
        return Observable.defer(() -> Observable.just(lookupPassenger(id)));
    }

}