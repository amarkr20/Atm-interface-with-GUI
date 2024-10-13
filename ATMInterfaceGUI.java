import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMInterfaceGUI {
    private static double balance = 5000.0;  
    private static final String PIN = "4321"; 
    private JFrame frame; 

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ATMInterfaceGUI::new); 
    }

    
    public ATMInterfaceGUI() {
        showPinWindow();
    }

    
    private void showPinWindow() {
        JFrame pinFrame = new JFrame("Enter PIN");
        pinFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pinFrame.setSize(300, 150);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JLabel pinLabel = new JLabel("Enter your 4-digit PIN:");
        JPasswordField pinField = new JPasswordField(10);
        JButton submitButton = new JButton("Submit");

        panel.add(pinLabel);
        panel.add(pinField);
        panel.add(submitButton);

        pinFrame.add(panel);
        pinFrame.setLocationRelativeTo(null);
        pinFrame.setVisible(true);

        
        submitButton.addActionListener(e -> {
            String enteredPin = new String(pinField.getPassword());
            if (enteredPin.equals(PIN)) {
                pinFrame.dispose();
                showATMMenu();
            } else {
                JOptionPane.showMessageDialog(pinFrame, "Invalid PIN. Please try again.", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // Method to display the main ATM menu
    private void showATMMenu() {
        frame = new JFrame("ATM Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JButton checkBalanceButton = new JButton("Check Balance");
        JButton withdrawButton = new JButton("Withdraw Cash");
        JButton depositButton = new JButton("Deposit Funds");
        JButton exitButton = new JButton("Exit");

        panel.add(checkBalanceButton);
        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(exitButton);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Action listeners for the buttons
        checkBalanceButton.addActionListener(e -> checkBalance());
        withdrawButton.addActionListener(e -> withdrawCash());
        depositButton.addActionListener(e -> depositFunds());
        exitButton.addActionListener(e -> System.exit(0)); // Close the program
    }

    // Method to display the current balance
    private void checkBalance() {
        JOptionPane.showMessageDialog(frame, 
                String.format("Your current balance is: $%.2f", balance), 
                "Balance", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to withdraw cash
    private void withdrawCash() {
        String input = JOptionPane.showInputDialog(frame, 
                "Enter the amount to withdraw:", 
                "Withdraw Cash", JOptionPane.PLAIN_MESSAGE);

        try {
            double amount = Double.parseDouble(input);
            if (amount <= 0) {
                JOptionPane.showMessageDialog(frame, 
                        "Invalid amount. Please enter a positive value.", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else if (amount > balance) {
                JOptionPane.showMessageDialog(frame, 
                        "Insufficient funds. Please try again.", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                balance -= amount;
                JOptionPane.showMessageDialog(frame, 
                        String.format("Withdrawal successful! New balance: $%.2f", balance), 
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, 
                    "Invalid input. Please enter a valid number.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to deposit funds
    private void depositFunds() {
        String input = JOptionPane.showInputDialog(frame, 
                "Enter the amount to deposit:", 
                "Deposit Funds", JOptionPane.PLAIN_MESSAGE);

        try {
            double amount = Double.parseDouble(input);
            if (amount <= 0) {
                JOptionPane.showMessageDialog(frame, 
                        "Invalid amount. Please enter a positive value.", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                balance += amount;
                JOptionPane.showMessageDialog(frame, 
                        String.format("Deposit successful! New balance: $%.2f", balance), 
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, 
                    "Invalid input. Please enter a valid number.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
