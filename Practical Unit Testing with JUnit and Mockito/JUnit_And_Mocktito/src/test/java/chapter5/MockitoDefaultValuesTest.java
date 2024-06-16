package chapter5;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class MockitoDefaultValuesTest {
    @Test
    void testDefaultBehaviorOfTestDouble() {
        Car car = mock(Car.class);

        assertThat(car.needsFuel()).isFalse();
        assertThat(car.getEngineTemperature()).isEqualTo(0.0);
        assertThat(car.getName()).isNull();
    }
}

interface Car {
    boolean needsFuel();
    double getEngineTemperature();
    String getName();
}
