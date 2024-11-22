package student.information.system;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Login class for the Student Information System
 */
public class Login extends javax.swing.JFrame {
    // Database-related variables
    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        conn = db.java_db(); // Initialize database connection
        centerFrame();
        currentDate();
    }

    /**
     * Centers the frame on the screen
     */
    private void centerFrame() {
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
    }

    /**
     * Displays the current date in the menu bar
     */
    private void currentDate() {
        Calendar cal = new GregorianCalendar();
        int month = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH is zero-based
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        txt_date.setText(month + "/" + day + "/" + year);
    }

    /**
     * Validates user input and performs the login operation
     */
    private void handleLogin() {
        String role = txt_combo.getSelectedItem().toString();
        String username = txt_username.getText();
        String password = String.valueOf(txt_password.getPassword());

        // Validate input
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username and Password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if ("Admin".equals(role)) {
            if ("admin".equals(username) && "admin".equals(password)) {
                // Successful admin login
                JOptionPane.showMessageDialog(this, "Login successful as Admin!");
                openMainMenu();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Admin credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if ("Student".equals(role)) {
            // Attempt student login
            handleStudentLogin(username, password);
        }
    }

    /**
     * Handles login for a student role
     */
    private void handleStudentLogin(String username, String password) {
        File file = new File("output.txt");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] tokens = data.split("\\s+");

                // Assuming the username is in tokens[0] and password is in tokens[3]
                if (tokens.length >= 4 && username.equals(tokens[0]) && password.equals(tokens[3])) {
                    JOptionPane.showMessageDialog(this, "Login successful as Student!");
                    new StudentInformation(tokens[3]).setVisible(true);
                    this.dispose();
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Invalid Student credentials!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error reading student data file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Opens the main menu
     */
    private void openMainMenu() {
        MainMenu mainMenu = new MainMenu();
        mainMenu.setVisible(true);
        this.dispose();
    }

    /**
     * Initializes components of the UI
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txt_username = new javax.swing.JTextField();
        txt_password = new javax.swing.JPasswordField();
        txt_combo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        txt_date = new javax.swing.JMenu();
        txt_time = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Username:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 280, 90, 17);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Password:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(20, 320, 80, 17);

        jButton1.setText("Login");
        jButton1.addActionListener(evt -> handleLogin());
        jPanel1.add(jButton1);
        jButton1.setBounds(180, 390, 70, 30);

        jPanel1.add(txt_username);
        txt_username.setBounds(110, 270, 160, 30);
        jPanel1.add(txt_password);
        txt_password.setBounds(110, 310, 160, 30);

        txt_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Student" }));
        jPanel1.add(txt_combo);
        txt_combo.setBounds(110, 350, 160, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Role:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(20, 360, 70, 17);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/student/information/system/images/Presentation1.jpg"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 660, 430);

        txt_date.setText("Date");
        jMenuBar1.add(txt_date);

        txt_time.setText("Time");
        jMenuBar1.add(txt_time);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
        );

        pack();
    }

    /**
     * Main method to run the application
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new Login().setVisible(true));
    }

    // Variables declaration
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> txt_combo;
    private javax.swing.JMenu txt_date;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JMenu txt_time;
    private javax.swing.JTextField txt_username;
    // End of variables declaration
}
