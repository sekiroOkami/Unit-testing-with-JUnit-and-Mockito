package chapter6.abstractAssert;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;

public class HashMapTest {

    private HashMap<String, String> map;
    @BeforeEach
    void setUp() {
        map = new HashMap<>();
    }

    @ParameterizedTest
    @CsvSource({
            "null, Value1",
            "null, Value2"
    })
    void nullKeyHandlingTest(String key, String value) {
        if (key.equals("null")) key = null;
        map.put(key, value);

        HashMapAssert.assertThat(map)
                .containsNullKeyWithValue(value)
                .hasSize(1);
    }
}
