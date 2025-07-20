package States;
import VendingMachine.VendingMachine;
import VendingMachine.Coin;
public abstract class State {
    VendingMachine machine;

    State(VendingMachine machine) {
        this.machine = machine;
    }

    public abstract void insertCoin(Coin coin);
    public abstract void selectItem(String code);
    public abstract void dispenseItem();
}
