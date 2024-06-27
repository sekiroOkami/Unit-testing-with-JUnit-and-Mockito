package mockito.mockAndSpy;

public class CalculatorService {
    private Calculator calculator;

    public CalculatorService(Calculator calculator) {
        this.calculator = calculator;
    }

    public int calculateSum(int a, int b) {
        return calculator.add(a, b);
    }
}
