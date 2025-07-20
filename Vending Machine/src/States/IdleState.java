package States;

import VendingMachine.*;

public class IdleState extends State {
    public IdleState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public void selectItem(String code) {
        if(!machine.getInventory().isAvailable(code)){
            System.out.println("Item is Out of Stock");
            return;
        }
        machine.setSelectedItemCode(code);
        machine.setCurrentState(new ItemSelectedState(machine));
    }

    @Override
    public void dispenseItem() {
        System.out.println("No Items Selected");
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Please select an item before inserting money.");
    }
}
