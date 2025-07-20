package States;

import VendingMachine.*;

public class DispenseState extends State {

    public DispenseState(VendingMachine machine) {
        super(machine);
    }

    @Override
    public void selectItem(String code) {
        System.out.println("Item is Already Selected");
    }

    @Override
    public void dispenseItem() {
        machine.dispenseItem();
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Already received full amount.");
    }
}
