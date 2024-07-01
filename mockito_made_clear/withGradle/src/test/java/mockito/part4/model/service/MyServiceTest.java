package mockito.part4.model.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

/**
 * "Mockito does not delegate calls to the passed real instance; instead
 * , it actually creates a copy of it."
 *
 * implications
 * 1. 'State Changes' : if you change the state of the real instance directly, those changes won't be reflected in the spy
 * Conversely, change made through the spy won't affect the original instance unless you are specifically dealing with shared mutable state.
 *
 * 2. Method calls : when you call methods on the spy, Mockito keeps track of these calls. However, if you call methods directly on the real instance
 * (bypassing the spy), Mockito won't be able to track these interaction.
 *
 * Summary
 * Mockito Spy: It wraps a real instance and can track interactions and state changes when methods are called on the spy.
 * Direct Method Calls: If you call methods directly on the real instance, those calls are not tracked by the spy.
 * State Changes: State changes through the spy affect the wrapped instance, but direct changes to the real instance do not affect the spy's tracking.
 */
class MyServiceTest {
    private MyService realService;
    private MyService spyService;

    @BeforeEach
    void setUp() {
        realService = new MyService();
        spyService = spy(realService);
    }

    @Test
    void testOnSpyBehavior() {
        //Act on spy
        spyService.increment();
        spyService.increment();

        // assert on spy
        assertThat(spyService.getValue()).isEqualTo(2);

        // act on real instance
        realService.increment();

        // assert on spy again
        assertThat(spyService.getValue()).isEqualTo(2);
    }

    @Test
    void testRealInstance() {
        // Act on the real instance directly
        realService.increment();

        // Assert on the real instance
        assertThat(realService.getValue()).isEqualTo(1); // This will be true

        // Act on the spy
        spyService.increment();

        // Assert on the real instance again
        assertThat(realService.getValue()).isEqualTo(2); // This will be true
    }

}
