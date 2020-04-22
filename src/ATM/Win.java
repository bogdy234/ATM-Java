package ATM;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Win extends JFrame {

    public int generatePin() {
        Random r = new Random();
        int nr = 0;
        int digit;
        int rez = 0;
        while (nr < 4) {
            digit = r.nextInt(10);
            if (nr == 0 && digit == 0)
                digit += 3;
            rez = rez * 10 + digit;
            nr++;
        }
        return rez;
    }

    public String generateID() {
        Random r = new Random();
        String str = "";
        int digit;
        while (str.length() < 16) {
            digit = r.nextInt(10);
            str = str + String.valueOf(digit);
        }
        return str;
    }

    public Win() {

        int p1 = generatePin();
        int p2 = generatePin();
        String s1 = generateID();
        String s2 = generateID();

        System.out.println(s1 + " " + s2);
        Users usr1 = new Users(s1, p1, 0);
        System.out.println(p1 + " " + p2);
        Users usr2 = new Users(s2, p2, 100);
        Accounts ac1 = new Accounts();
        ac1.addAccount(usr1);
        ac1.addAccount(usr2);


        this.setSize(500, 500);
        this.setLocation(700, 240);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("ATM");
        this.setResizable(false);
        this.setLayout(null);

        JButton verifyPIN = new JButton("Verify PIN");
        verifyPIN.setBounds(270, 60, 200, 30);
        this.add(verifyPIN);

        JButton verifyButton = new JButton("Login");
        verifyButton.setBounds(350, 350, 100, 100);
        this.add(verifyButton);

        JLabel introMessage = new JLabel("Insert card number:");
        introMessage.setBounds(30, 20, 300, 50);
        this.add(introMessage);

        JTextField cardNumber = new JTextField();
        cardNumber.setBounds(30, 60, 200, 30);
        this.add(cardNumber);
        cardNumber.setDocument(new JTextFieldLimit(16));


        JLabel pinMessage = new JLabel("Insert PIN (4 digits):");
        pinMessage.setBounds(30, 100, 200, 30);
        this.add(pinMessage);

        JPasswordField pinNumber = new JPasswordField();
        pinNumber.setBounds(30, 140, 200, 30);
        this.add(pinNumber);
        pinNumber.setDocument(new JTextFieldLimit(4));

        JButton interogation = new JButton("Check balance");
        interogation.setBounds(10, 180, 150, 100);
        this.add(interogation);
        interogation.setEnabled(false);

        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(160, 180, 150, 100);
        this.add(depositButton);
        depositButton.setEnabled(false);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(310, 180, 150, 100);
        this.add(withdrawButton);
        withdrawButton.setEnabled(false);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(30, 350, 150, 100);
        this.add(exitButton);
        exitButton.setEnabled(false);


        verifyPIN.addActionListener(actionEvent -> {
                Users user = ac1.checkID(cardNumber.getText(), Integer.parseInt(pinNumber.getText()));
                if (user != null) {
                    JOptionPane pane2 = new JOptionPane("Correct data set!", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
                    JDialog dialog2 = pane2.createDialog(null, null);
                    dialog2.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                    dialog2.setModal(false);
                    dialog2.setVisible(true);

                    new Timer(3000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dialog2.setVisible(false);
                        }
                    }).start();
                }
                else
                {
                    JOptionPane pane2 = new JOptionPane("Incorrect data set!", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
                    JDialog dialog2 = pane2.createDialog(null, null);
                    dialog2.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                    dialog2.setModal(false);
                    dialog2.setVisible(true);

                    new Timer(3000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dialog2.setVisible(false);
                        }
                    }).start();
                }
        });


        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                interogation.setEnabled(false);
                depositButton.setEnabled(false);
                withdrawButton.setEnabled(false);
                cardNumber.setEnabled(true);
                pinNumber.setEnabled(true);
                verifyButton.setEnabled(true);
                exitButton.setEnabled(false);
                cardNumber.setText("");
                pinNumber.setText("");
            }
        });


        interogation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Users usr = ac1.checkID(cardNumber.getText(), Integer.parseInt(pinNumber.getText()));
                JOptionPane.showMessageDialog(null, "Current balance: " + usr.getBalance());
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Users usr = ac1.checkID(cardNumber.getText(), Integer.parseInt(pinNumber.getText()));
                String amount = JOptionPane.showInputDialog("Enter amount of money you want to deposit: ");
                int input = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
                if (input == 0) {
                    usr.deposit(Integer.parseInt(amount));
                    JOptionPane.showMessageDialog(null, "Current balance: " + usr.getBalance());
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Users usr = ac1.checkID(cardNumber.getText(), Integer.parseInt(pinNumber.getText()));
                String amount = JOptionPane.showInputDialog("Enter amount of money you want to withdraw: ");
                int input = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
                if (input == 0) {
                    if (Double.parseDouble(amount) <= usr.getBalance()) {
                        usr.withdraw(Integer.parseInt(amount));
                        JOptionPane.showMessageDialog(null, "Current balance: " + usr.getBalance());
                    } else {
                        JOptionPane.showMessageDialog(null, "You have insufficient funds!");
                    }
                }
            }
        });

        verifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Users usr = ac1.checkID(cardNumber.getText(), Integer.parseInt(pinNumber.getText()));
                if (usr != null) {
                    JOptionPane pane = new JOptionPane("Successful authentication!", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
                    JDialog dialog = pane.createDialog(null, null);
                    dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                    dialog.setModal(false);
                    dialog.setVisible(true);

                    new Timer(2000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dialog.setVisible(false);
                        }
                    }).start();
                    interogation.setEnabled(true);
                    depositButton.setEnabled(true);
                    withdrawButton.setEnabled(true);
                    cardNumber.setEnabled(false);
                    pinNumber.setEnabled(false);
                    verifyButton.setEnabled(false);
                    exitButton.setEnabled(true);
                } else {
                    JOptionPane pane2 = new JOptionPane("You inserted a wrong ID or PIN!", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
                    JDialog dialog2 = pane2.createDialog(null, null);
                    dialog2.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                    dialog2.setModal(false);
                    dialog2.setVisible(true);

                    new Timer(2000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dialog2.setVisible(false);
                        }
                    }).start();
                }
            }
        });

        this.setVisible(true);
    }

}
