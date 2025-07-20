package VendingMachine;

public enum Coin {
    TEN(10),
    TWENTY(20),
    TWENTYFIVE(25),
    THIRTY(30);

    private final int value;

    Coin(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }
}
