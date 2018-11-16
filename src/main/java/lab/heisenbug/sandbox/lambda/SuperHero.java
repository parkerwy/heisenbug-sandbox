package lab.heisenbug.sandbox.lambda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FunctionalInterface
public interface SuperHero {

    Logger LOGGER = LoggerFactory.getLogger(SuperHero.class);
    
    void usePowerAgainst(String enemy);

    default boolean wearCape() {
        return true;
    }
    
    static void dump() {
        LOGGER.info("Hero");
    }
}
