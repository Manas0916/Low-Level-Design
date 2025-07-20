package VendingMachine;

import States.*;

public class VendingMachine {
    private final static VendingMachine instance = new VendingMachine();
    private final Inventory inventory = new Inventory();
    private static State currentState;
    private int balance = 0;
    private String selectedItemCode;

    private VendingMachine(){
        currentState = new IdleState(this);
    }
    public static VendingMachine getInstance() {
        return instance;
    }
    public void insertCoin(Coin coin) {
        currentState.insertCoin(coin);
    }

    public Product addItem(String code, String name, int price, int quantity) {
        Product item = new Product(code, name, price);
        inventory.addItem(code, item, quantity);
        return item;
    }
    public void selectItem(String code) {
        currentState.selectItem(code);
    }

    public void dispense() {
        currentState.dispenseItem();
    }
    public void dispenseItem(){
        Product item = inventory.getItem(selectedItemCode);
        if (balance >= item.getPrice()) {
            inventory.reduceStock(selectedItemCode);
            balance -= item.getPrice();
            System.out.println("Dispensed: " + item.getName());
            if (balance > 0) {
                System.out.println("Returning change: " + balance);
            }
        }
    }

//    Getter and Setters
    public Inventory getInventory() {
        return inventory;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        VendingMachine.currentState = currentState;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance += balance;
    }

    public Product getSelectedItem() {
        return inventory.getItem(selectedItemCode);
    }

    public Product getSelectedItemCode() {
        return inventory.getItem(selectedItemCode);
    }

    public void setSelectedItemCode(String selectedItemCode) {
        this.selectedItemCode = selectedItemCode;
    }
}
