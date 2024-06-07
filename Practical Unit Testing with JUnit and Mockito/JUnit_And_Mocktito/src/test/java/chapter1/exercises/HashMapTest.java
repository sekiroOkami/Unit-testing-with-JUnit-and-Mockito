package chapter1.exercises;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class HashMapTest {
    Map<String, String> map = null;
    @BeforeEach
    void setUp() {
        map = new HashMap<>();
    }

    @ParameterizedTest
    @CsvSource({
            "key1, value1",
            "key2, value2",
            "null, null",
            "null, nonNullValue",
            "nonNullKey, null",
            "'', emptyValue",
    })
    void shouldReturnTheObjectThatStoreWithPutMethod(String key, String value) {
        if ("null".equals(key)) key = null;
        if ("null".equals(value)) value = null;
        map.put(key, value);
        assertThat(map.get(key)).isEqualTo(value);

    }

    @ParameterizedTest
    @CsvSource({
            "key1, value1",
            "key1, value2"
    })
    void shouldReplaceValueWhenAddSecondObjectWithSameKey(String key, String value) {
        map.put("key1", "value");
        map.put(key, value);
        assertThat(map.get(key)).isEqualTo(value);
    }

    @ParameterizedTest
    @CsvSource({
            "key1, value1",
            "key1, value2"
    })
    void shouldClearRemoveAllItsContent(String key, String value) {
        map.put(key, value);
        map.clear();
        assertThat(map.size()).isEqualTo(0);
    }

    @ParameterizedTest
    @CsvSource({
            "null, value1",
            "null, value2",
            "null, value3"
    })
    void nullValueCanBeUseAsAKey(String key, String value) {
        if ("null".equals(key)) key = null;
        map.put(null, "test");
        map.put(key, value);
        assertThat(map.get(key)).isEqualTo(value);
        assertThat(map.size()).isEqualTo(1);
    }

    @RepeatedTest(5)
    void repeatNullValueCanBeUsedAsKey() {
        String key = null;
        map.put(key, "Value");
        assertThat(map.get(key)).isEqualTo("Value");
        assertThat(map.size()).isEqualTo(1);
    }
}
