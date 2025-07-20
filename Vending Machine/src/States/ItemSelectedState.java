package States;

import VendingMachine.Coin;
import VendingMachine.VendingMachine;

public class ItemSelectedState extends State {
    public ItemSelectedState(VendingMachine machine) {
        super(machine);
    }

    @Override
    public void insertCoin(Coin coin) {
        machine.setBalance(coin.getValue());
        System.out.println("Coin Inserted: " + coin.getValue());
        int price = machine.getSelectedItem().getPrice();
        if (machine.getBalance() >= price) {
            System.out.println("Sufficient money received.");
            machine.setCurrentState(new DispenseState(machine));
        }
    }

    @Override
    public void selectItem(String code) {
        System.out.println("Item already selected.");
    }

    @Override
    public void dispenseItem() {
        System.out.println("Please insert sufficient money.");
    }
}
