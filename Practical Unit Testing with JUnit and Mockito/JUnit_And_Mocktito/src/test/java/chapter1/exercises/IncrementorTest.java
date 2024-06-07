package chapter1.exercises;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class IncrementorTest {
    private Incrementor incrementor = null;

    @BeforeEach
    void setUp() {
        incrementor = new Incrementor();
    }

    @Test
    void shouldIncrementBy1() {
        incrementor.increment();
        assertThat(Incrementor.counter).isEqualTo(1);
    }

    @Test
    void assertJWithCollection() {
        List<Path> list = new ArrayList<>();
        list.add(Path.of(""));
        list.add(Path.of(".\\src\\test"));
        list.add(Path.of(".\\src\\main"));
        assertThat(list).noneMatch(path -> path.endsWith("resource"));
    }
}