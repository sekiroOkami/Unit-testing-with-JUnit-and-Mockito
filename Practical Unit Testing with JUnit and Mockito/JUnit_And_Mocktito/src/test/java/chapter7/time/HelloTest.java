package chapter7.time;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Calendar;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HelloTest {

    private TimeProvider timeProvider;
    private Hello hello;

    @BeforeEach
    void setUp() {
        timeProvider = mock(TimeProvider.class);
        hello = new Hello(timeProvider);
    }

    private static final IntStream morningHours() {
        return IntStream.range(1, 12);
    }

    private static final IntStream afternoonHours() {
        return IntStream.range(12, 24);
    }

    @ParameterizedTest
    @MethodSource("morningHours")
    void shouldSayGoodMorningInTheMorning(int morningHour) {
        when(timeProvider.getTime()).thenReturn(getCalendar(morningHour));
        assertEquals("Good Morning", hello.sayHello());
    }

    @ParameterizedTest
    @MethodSource("afternoonHours")
    void shouldSayGoodAfternoonInTheAfternoon(int afternoonHour) {
        when(timeProvider.getTime()).thenReturn(getCalendar(afternoonHour));
        assertEquals("Good Afternoon", hello.sayHello());
    }

    private Calendar getCalendar(int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        return calendar;
    }



}