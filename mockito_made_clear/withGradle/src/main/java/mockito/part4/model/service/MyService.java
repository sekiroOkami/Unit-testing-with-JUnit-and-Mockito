package mockito.part4.model.service;

public class MyService {
    private  int value = 0;
    public void increment() {
        value++;
    }

    public int getValue() {
        return value;
    }
}
