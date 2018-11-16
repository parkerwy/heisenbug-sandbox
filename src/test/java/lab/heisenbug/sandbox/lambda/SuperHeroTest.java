package lab.heisenbug.sandbox.lambda;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SuperHeroTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SuperHeroTest.class);

    @Test
    public void shouldUsePower() throws Exception {
        SuperHero hero = (enemy) -> LOGGER.info("use super power on {}.", enemy);
        hero.usePowerAgainst("Clown");

        SuperHero.dump();

        
    }
}
