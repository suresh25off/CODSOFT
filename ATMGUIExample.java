import javax.swing.SwingUtilities;

public class ATMGUIExample {
    public static void main(String[] args) {
        // Create a bank account with an initial balance of $1000
        BankAccount bankAccount = new BankAccount(1000);

        // Create an ATM GUI and connect it to the bank account
        ATMGUI atmGUI = new ATMGUI(bankAccount);

        // Set the GUI visible
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                atmGUI.setVisible(true);
            }
        });
    }
}