package AccountsManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static javax.swing.JOptionPane.*;

public class UI extends JFrame implements ActionListener {
    private static Controller controller = new Controller();
    private List<Account> list = new ArrayList<>();
    private JTabbedPane tabbedPane;
    private JPanel panel1;
    private JPanel panel2;
    static ObservedObject observedObject = new ObservedObject();

    private JPasswordField loginPass;

    private JTextField applicationName;
    private JTextField loginId;
    static JTextField password;
    private JTextField email;
    private JTextField emailPassword;
    private JTextField securityQuestion1;
    private JTextField securityQuestion2;
    private JTextField generatedPassword;
    static JTextField passwordCheck;

    public UI() {

        setTitle("Account Manager v1.0");
        setSize(480, 360);
        setBackground(Color.gray);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        getContentPane().add(topPanel);

        // Create the tab pages
        createLoginPage();
        createPasswordGeneratorPage();

        // Create a tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Accounts", panel1);
        topPanel.add(tabbedPane, BorderLayout.CENTER);
        ObservableObject observableObject = new ObservableObject();
        observedObject.register(observableObject);

    }


    public void createLoginPage() {
        panel1 = new JPanel();
        panel1.setLayout(null);
        setResizable(false);

        JLabel loginImage = new JLabel(new ImageIcon("login.jpg"));
        loginImage.setBounds(0, 0, 480, 360);


        JLabel loginPasswordLabel = new JLabel("Login");
        loginPasswordLabel.setBounds(185, 10, 100, 50);
        loginPasswordLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 35));
        loginPasswordLabel.setForeground(Color.white);
        panel1.add(loginPasswordLabel);

        loginPass = new JPasswordField();
        loginPass.setBounds(170, 100, 120, 20);
        panel1.add(loginPass);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(196, 128, 70, 20);
        panel1.add(loginButton);
        loginButton.addActionListener(e -> {
            String password = new String(loginPass.getPassword());
            if (password.equals(controller.getMasterPassword()) || password.equals(controller.getUserPassword())) {
                tabbedPane.addTab("Password Generator", panel2);
                createAccountsPage();
            } else
                showMessageDialog(null, "Wrong password. Come on, this is the only password you have to remember !", "ERROR", ERROR_MESSAGE);
        });
        panel1.add(loginImage);
    }

    public void createAccountsPage() {
        panel1.removeAll();
        panel1.revalidate(); //revalidate, repaint removing lag produced by button press
        panel1.repaint();
        JLabel image = new JLabel(new ImageIcon("ui.jpg"));
        image.setBounds(0, 0, 1100, 520);
        setSize(1100, 520);

        Object[] row = new Object[8];
        JTable table = new JTable();

        Object[] columns = {"Application Name", "Login ID", "Password", "E-Mail", "E-Mail Password", "Security Question1", "Security Question2"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        table.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
        table.setRowHeight(30);

        JScrollPane scrollableList = new JScrollPane(table);
        scrollableList.setSize(1100, 200);
        panel1.add(scrollableList);

        applicationName = new JTextField();
        applicationName.setBounds(5, 210, 130, 20);
        panel1.add(applicationName);

        loginId = new JTextField();
        loginId.setBounds(165, 210, 130, 20);
        panel1.add(loginId);

        email = new JTextField();
        email.setBounds(485, 210, 130, 20);
        panel1.add(email);

        emailPassword = new JTextField();
        emailPassword.setBounds(640, 210, 130, 20);
        panel1.add(emailPassword);

        securityQuestion1 = new JTextField();
        securityQuestion1.setBounds(795, 210, 130, 20);
        panel1.add(securityQuestion1);

        securityQuestion2 = new JTextField();
        securityQuestion2.setBounds(947, 210, 130, 20);
        panel1.add(securityQuestion2);

        passwordCheck = new JTextField();
        passwordCheck.setBounds(325, 235, 130, 20);
        passwordCheck.setEditable(false);
        panel1.add(passwordCheck);

        password = new JTextField();
        password.setBounds(325, 210, 130, 20);
        panel1.add(password);
        password.addKeyListener(new Keychecker());

        JButton showAccounts = new JButton("Show Accounts");
        showAccounts.setBounds(485, 270, 130, 20);
        panel1.add(showAccounts);
        showAccounts.addActionListener(e -> {
            model.getDataVector().removeAllElements();
            List<Account> list = controller.loadAccounts();
            for (Account account : list) {
                row[0] = account.getApplicationName();
                row[1] = account.getLoginId();
                row[2] = account.getPassword();
                row[3] = account.getEmail();
                row[4] = account.getEmailPassword();
                row[5] = account.getSecurityQuestion1();
                row[6] = account.getSecurityQuestion2();
                model.addRow(row);
            }
        });

        JButton addAccount = new JButton("Add Account");
        addAccount.setBounds(490, 240, 120, 20);
        panel1.add(addAccount);
        addAccount.addActionListener(e -> {
            if (!applicationName.getText().equals("") && !loginId.getText().equals("") && !password.getText().equals("")) {
                Account account = new Account.AccountBuilder(applicationName.getText(), loginId.getText(), password.getText())
                        .email(email.getText())
                        .emailPassword(emailPassword.getText())
                        .securityQuestion1(securityQuestion1.getText())
                        .securityQuestion2(securityQuestion2.getText())
                        .build();
                controller.addAccount(account);
                controller.saveAccountList();
                row[0] = account.getApplicationName();
                row[1] = account.getLoginId();
                row[2] = account.getPassword();
                row[3] = account.getEmail();
                row[4] = account.getEmailPassword();
                row[5] = account.getSecurityQuestion1();
                row[6] = account.getSecurityQuestion2();
                model.addRow(row);
            } else
                showMessageDialog(null, "Application name, loginID and password should not be empty !", "ERROR", ERROR_MESSAGE);
        });

        JButton deleteAccount = new JButton("Delete");
        deleteAccount.setBounds(490, 300, 120, 20);
        panel1.add(deleteAccount);
        deleteAccount.addActionListener(e -> {
            int i = table.getSelectedRow();
            if (i >= 0) {
                list = controller.loadAccounts();
                int dialogResult = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to delete your " + list.get(i).getApplicationName() + " account ?",
                        "Option", JOptionPane.YES_NO_OPTION);
                if (dialogResult == 0) {
                    model.removeRow(i);
                    controller.removeAccount(i);
                    controller.saveAccountList();
                }
            } else {
                showMessageDialog(null, "Please select account !", "ERROR", ERROR_MESSAGE);
            }
        });
        panel1.add(image);
    }

    public void createPasswordGeneratorPage() {
        panel2 = new JPanel();
        panel2.setLayout(null);
        JLabel image = new JLabel(new ImageIcon("ui.jpg"));
        image.setBounds(0, 0, 1100, 520);

        generatedPassword = new JTextField();
        generatedPassword.setBounds(500, 100, 100, 20);
        panel2.add(generatedPassword);

        JLabel passwordGeneratorLabel = new JLabel("Generate a STRONG password to secure your accounts");
        passwordGeneratorLabel.setBounds(400, 190, 400, 20);
        panel2.add(passwordGeneratorLabel);

        passwordGeneratorLabel = new JLabel("Info: copy the password using ctrl+c and paste it with ctrl+v");
        passwordGeneratorLabel.setBounds(391, 220, 400, 20);
        panel2.add(passwordGeneratorLabel);

        JButton generate = new JButton("Generate");
        generate.setBounds(500, 140, 100, 20);
        panel2.add(generate);
        generate.addActionListener(e -> {
            int leftLimit = 48; // '0'
            int rightLimit = 122; // 'z'
            int targetStringLength = 10;
            Random random = new Random();

            String generatedString = random.ints(leftLimit, rightLimit + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            generatedPassword.setText(generatedString);
        });
        panel2.add(image);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

}