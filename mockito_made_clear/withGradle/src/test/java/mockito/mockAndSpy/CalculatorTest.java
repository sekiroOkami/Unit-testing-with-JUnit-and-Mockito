package mockito.mockAndSpy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


/**
 * Mocks:
 *
 * 1. Created from scratch.
 * 2. You define the behavior of the methods you are interested in.
 * 3. All method calls are recorded and can be verified.
 *
 *
 * Spies:
 *
 * 1.Wrap an existing instance.
 * 2.The actual methods are called unless they are stubbed.
 * 3.You can choose to stub specific methods while leaving the rest to use the real implementation.
 */

@ExtendWith(MockitoExtension.class)
public class CalculatorTest {

    @Mock
    private Calculator calculator;

    @InjectMocks
    private CalculatorService calculatorService;

    @Captor
    private ArgumentCaptor<Integer> intCaptor;

    @BeforeEach
    void setUp() {
        calculatorService = new CalculatorService(calculator);
    }

    @Test
    @Disabled("demo ArgumentCaptor")
    void calculateSum_shouldCallAddWithCorrectArguments_manual() {
        //Arrange
        var calculator = mock(Calculator.class);
        var calService = new CalculatorService(calculator);
        ArgumentCaptor<Integer> intCap = ArgumentCaptor.forClass(Integer.class);

        // act
        calculatorService.calculateSum(5,10);

        // assert
        verify(calculator).add(intCap.capture(), intCap.capture());
        assertThat(intCap.getAllValues()).containsExactly(5, 10);
    }

    @Test
    void calculateSum_shouldCallAddWithCorrectArguments() {
        // act
        calculatorService.calculateSum(5,10);

        // Assert
        verify(calculator).add(intCaptor.capture(), intCaptor.capture());
        assertThat(intCaptor.getAllValues()).containsExactly(5, 10);
    }

    @Test
    void calculateSum_shouldReturnCorrectSum() {
        // Arrange
        when(calculator.add(anyInt(), anyInt())).thenAnswer(invocation -> {
            // we define the behavior of the 'add' method . inside the '.thenAnswer()' method
            // we implement the logic to dynamically calculate the sum of the arguments.
            int a = invocation.getArgument(0);
            int b = invocation.getArgument(1);
            return a + b;
        });

        // act
        int result = calculatorService.calculateSum(5,10);

        // Assert
        assertThat(result).isEqualTo(15);
    }

    @Test
    void calculateSum_shouldReturnDifferentSumsBasedOnArguments() {
        // arrange
        when(calculator.add(anyInt(), anyInt())).thenAnswer(invocation -> {
            int a = invocation.getArgument(0);
            int b = invocation.getArgument(1);

            if (a==5 && b==10) {
                return 100; // custom behavior for specific arguments
            } else {
                return a + b; // default behavior
            }
        });

        // act && assert
        assertThat(calculatorService.calculateSum(5, 10)).isEqualTo(100);
        assertThat(calculatorService.calculateSum(1,2)).isEqualTo(3);
    }

    @Test
    void testWithMock() {

        // SUT
        Calculator calculator = mock(Calculator.class);

        // Arrange
        when(calculator.add(2,3)).thenReturn(5);

        int result = calculator.add(2,3);

        // verify the call
        verify(calculator).add(2,3);
        assertThat(5).isEqualTo(result);

        // the multiply method is not config, so it returns the default value(0)
        assertThat(0).isEqualTo(calculator.multiply(2,3));

    }

    @Test
    void testWithSpy() {

        //  SUT, real instance
        var realCalculator = new Calculator();

        // create a spy of the calculator class
        Calculator calculatorSpy = spy(realCalculator);

        // config behavior
        when(calculatorSpy.add(2,3)).thenReturn(5);

        // cal the method
        int result = calculatorSpy.add(2,3);

        // verify
        verify(calculatorSpy).add(2,3);
        assertThat(5).isEqualTo(result);

        // the multiply method use the real implementation
        assertThat(6).isEqualTo(calculatorSpy.multiply(2,3));
    }
}
