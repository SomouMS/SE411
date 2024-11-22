package student.information.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * A class to display student results in a table format.
 */
public class Result extends javax.swing.JFrame {

    // Components for the GUI
    private JTable resultTable;
    private JScrollPane jScrollPane;
    private JLabel jLabelTitle;

    /**
     * Creates new form Result
     */
    public Result() {
        initComponents();
    }

    /**
     * Initializes the components of the form.
     */
    private void initComponents() {
        // Title label
        jLabelTitle = new JLabel();
        resultTable = new JTable();
        jScrollPane = new JScrollPane();

        // Set up the frame properties
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Student Results");

        // Configure the title label
        jLabelTitle.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelTitle.setText("Student Results");

        // Configure the table
        resultTable.setModel(new DefaultTableModel(
            new Object[][] {}, // Empty table initially
            new String[] { "Roll No", "Name", "Marks" } // Table columns
        ));
        jScrollPane.setViewportView(resultTable);

        // Set up the layout for the components
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelTitle))
                    .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jLabelTitle)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }

    /**
     * Adds a new result to the table.
     *
     * @param rollNo The roll number of the student.
     * @param name   The name of the student.
     * @param marks  The marks obtained by the student.
     */
    public void addResult(String rollNo, String name, String marks) {
        DefaultTableModel model = (DefaultTableModel) resultTable.getModel();
        model.addRow(new Object[]{rollNo, name, marks});
    }

    /**
     * Main method to run the application.
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Result.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            Result result = new Result();
            result.setVisible(true);

            // Adding sample data
            result.addResult("1", "John Doe", "85");
            result.addResult("2", "Jane Smith", "90");
            result.addResult("3", "Alice Brown", "78");
        });
    }

    // Variables declaration
    private JLabel jLabelTitle;
}