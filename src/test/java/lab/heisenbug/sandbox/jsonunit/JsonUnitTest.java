package lab.heisenbug.sandbox.jsonunit;

import net.javacrumbs.jsonunit.JsonAssert;
import org.junit.Test;

public class JsonUnitTest {

    private static String foo = "    \n" +
            "{                       \n" +
            "  \"name\": \"John\",   \n" +
            "  \"activated\": true,  \n" +
            "  \"cases\": [     ]    \n" +
            "}                       \n" ;

    private static String bar = "    \n" +
            "{               \n\n\r\r\n" +
            "  \"activated\": true,  \n" +
            "  \"name\": \"John\",   \n" +
            "  \"cases\": []         \n" +
            "}                       \n" ;

    @Test
    public void shouldFindJsonStringsEquals() throws Exception {
        JsonAssert.assertJsonEquals(foo, bar);
    }
}
