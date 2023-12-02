import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true; // Successful withdrawal
        } else {
            return false; // Insufficient balance
        }
    }
}

class ATMGUI extends JFrame {
    private BankAccount bankAccount;

    private JTextField amountField;
    private JTextArea resultArea;

    public ATMGUI(BankAccount bankAccount) {
        this.bankAccount = bankAccount;

        setTitle("ATM GUI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeComponents();
    }

    private void initializeComponents() {
        JLabel amountLabel = new JLabel("Enter Amount:");
        amountField = new JTextField(10);

        JButton checkBalanceButton = new JButton("Check Balance");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton exitButton = new JButton("Exit");

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(amountLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(amountField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(checkBalanceButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(depositButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(withdrawButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(exitButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(resultArea, gbc);

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
    }

    private void checkBalance() {
        double balance = bankAccount.getBalance();
        resultArea.setText("Current Balance: $" + balance);
        clearInput();
    }

    private void deposit() {
        try {
            double amount = Double.parseDouble(amountField.getText());

            if (amount > 0) {
                bankAccount.deposit(amount);
                resultArea.setText("Deposit successful.\nCurrent Balance: $" + bankAccount.getBalance());
            } else {
                resultArea.setText("Invalid deposit amount. Please enter a positive amount.");
            }
        } catch (NumberFormatException ex) {
            resultArea.setText("Invalid input. Please enter a valid number.");
        }

        clearInput();
    }

    private void withdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText());

            if (amount > 0) {
                if (bankAccount.withdraw(amount)) {
                    resultArea.setText("Withdrawal successful.\nCurrent Balance: $" + bankAccount.getBalance());
                } else {
                    resultArea.setText("Insufficient balance. Withdrawal failed.");
                }
            } else {
                resultArea.setText("Invalid withdrawal amount. Please enter a positive amount.");
            }
        } catch (NumberFormatException ex) {
            resultArea.setText("Invalid input. Please enter a valid number.");
        }

        clearInput();
    }

    private void clearInput() {
        amountField.setText("");
    }
}
