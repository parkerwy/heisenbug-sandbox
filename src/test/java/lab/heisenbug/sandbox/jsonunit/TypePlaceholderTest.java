package lab.heisenbug.sandbox.jsonunit;

import net.javacrumbs.jsonunit.JsonAssert;
import org.junit.Test;

public class TypePlaceholderTest {

    @Test
    public void shouldFindObjectEquals() throws Exception {
        JsonAssert.assertJsonEquals("{\"name\": [\"${json-unit.regex}^F.*\"]}", "{\"name\": [\"Foo\"]}");
    }

    @Test
    public void shouldFindArrayEquals() throws Exception {
        JsonAssert.assertJsonEquals("[\"${json-unit.regex}^F.*\"]", "[\"Foo\"]");
    }
}
