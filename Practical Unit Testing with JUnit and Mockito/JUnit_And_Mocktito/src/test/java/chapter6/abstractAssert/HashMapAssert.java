package chapter6.abstractAssert;

import org.assertj.core.api.AbstractAssert;

import java.util.HashMap;

public class HashMapAssert extends AbstractAssert<HashMapAssert, HashMap<?, ?>> {
    public HashMapAssert(HashMap<?, ?> actual) {
        super(actual, HashMapAssert.class);
    }

    public static HashMapAssert assertThat(HashMap<?, ?> actual) {
        return new HashMapAssert(actual);
    }

    public HashMapAssert containsNullKeyWithValue(Object expectedValue) {
        isNotNull();

        if (!actual.containsKey(null)) {
            failWithMessage("Expected map to contain null key, but it did not.");
        }

        if (!(actual.get(null)).equals(expectedValue)){
            failWithMessage("Expected map's null key to have value <%s> but was <%s>", expectedValue, actual.get(null));
        }

        return this;
    }

    public HashMapAssert hasSize(int expectedSize) {
        isNotNull();

        if (actual.size() != expectedSize) {
            failWithMessage("Expected map size to be <%s> but was <%s>", expectedSize, actual.size());
        }

        return this;
    }
}
