package atmmachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMMachine extends JFrame {
    private BankAccount account;
    private JTextField amountField;
    private JLabel messageLabel;

    public ATMMachine() {
        account = new BankAccount(1000); // Initial balance

        setTitle("ATM Interface");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 5, 5));

        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField();
        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton balanceButton = new JButton("Check Balance");
        messageLabel = new JLabel();
        
        
        withdrawButton.setPreferredSize(new Dimension(120, 40));
        depositButton.setPreferredSize(new Dimension(120, 40));
        balanceButton.setPreferredSize(new Dimension(120, 40));

        Font buttonFont = new Font("Arial", Font.PLAIN, 16);
        withdrawButton.setFont(buttonFont);
        depositButton.setFont(buttonFont);
        balanceButton.setFont(buttonFont);

        // Adjust the size and font for amount field and message label
        amountField.setPreferredSize(new Dimension(200, 40));
        messageLabel.setPreferredSize(new Dimension(300, 80));

        Font largeFont = new Font("Arial", Font.PLAIN, 24);
        amountField.setFont(largeFont);
        messageLabel.setFont(largeFont);
        amountLabel.setFont(largeFont);
        amountLabel.setPreferredSize(new Dimension(200, 40));


        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Withdraw button clicked");
                double amount = Double.parseDouble(amountField.getText());
                if (account.withdraw(amount)) {
                    double remainingBalance = account.checkBalance();
                    messageLabel.setText("Withdrawal successful. Amount withdrawn: $" + amount +
                            ". Remaining balance: $" + remainingBalance);
                } else {
                    messageLabel.setText("Insufficient funds");
                }
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Deposit button clicked");
                double amount = Double.parseDouble(amountField.getText());
                account.deposit(amount);
                double newBalance = account.checkBalance();
                messageLabel.setText("Deposit successful. New balance: $" + newBalance);
            }
        });

        balanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Balance button clicked");
                double balance = account.checkBalance();
                messageLabel.setText("Current Balance: $" + balance);
            }
        });

        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(balanceButton);
        panel.add(messageLabel);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ATMMachine().setVisible(true);
            }
        });
    }
}
