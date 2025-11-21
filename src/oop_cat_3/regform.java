package oop_cat_3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class regform {
    private JFrame frame;
    private JTextField idField;
    private JTextField nameField;
    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;
    private JTextArea addressArea;
    private JTextField contactField;
    private JButton registerButton;
    private JButton exitButton;
    private JTable dataTable;
    private DefaultTableModel tableModel;
    
    // List to store all registrations
    private final List<Registration> registrations;
    
    // Registration data class
    private static class Registration {
        String id;
        String name;
        String gender;
        String address;
        String contact;
        
        public Registration(String id, String name, String gender, String address, String contact) {
            this.id = id;
            this.name = name;
            this.gender = gender;
            this.address = address;
            this.contact = contact;
        }
        
        public Object[] toTableRow() {
            return new Object[]{id, name, gender, contact, address};
        }
    }

    public regform() {
        registrations = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Registration Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 500);
        frame.setLocationRelativeTo(null);
        
        // Create main panel with split layout
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(400);
        splitPane.setDividerSize(3);

        // Left panel - Input Form
        JPanel formPanel = createFormPanel();
        
        // Right panel - Display Area with Table
        JPanel displayPanel = createDisplayPanel();

        splitPane.setLeftComponent(formPanel);
        splitPane.setRightComponent(displayPanel);

        frame.add(splitPane);
    }
    
    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(Color.WHITE);

        // Title
        JLabel titleLabel = new JLabel("Registration Form");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(titleLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        // ID Field
        formPanel.add(createLabel("ID"));
        idField = createTextField();
        formPanel.add(idField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Name Field
        formPanel.add(createLabel("Name"));
        nameField = createTextField();
        formPanel.add(nameField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Gender Section
        JLabel genderLabel = createLabel("Gender");
        formPanel.add(genderLabel);
        
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        genderPanel.setBackground(Color.WHITE);
        genderPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        genderPanel.setMaximumSize(new Dimension(350, 30));
        
        maleRadio = createRadioButton("Male");
        femaleRadio = createRadioButton("Female");
        
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        
        formPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        formPanel.add(genderPanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Address Field
        formPanel.add(createLabel("Address"));
        addressArea = new JTextArea(3, 20);
        addressArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addressArea.setLineWrap(true);
        addressArea.setWrapStyleWord(true);
        JScrollPane addressScroll = new JScrollPane(addressArea);
        addressScroll.setMaximumSize(new Dimension(350, 80));
        addressScroll.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(addressScroll);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Contact Field
        formPanel.add(createLabel("Contact"));
        contactField = createTextField();
        formPanel.add(contactField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonPanel.setMaximumSize(new Dimension(350, 40));
        
        // Register Button
        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.BOLD, 12));
        registerButton.setBackground(new Color(70, 130, 180));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setPreferredSize(new Dimension(100, 30));
        
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        
        // Exit Button
        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 12));
        exitButton.setBackground(new Color(220, 80, 60));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);
        exitButton.setPreferredSize(new Dimension(100, 30));
        
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitApplication();
            }
        });
        
        buttonPanel.add(registerButton);
        buttonPanel.add(exitButton);
        
        formPanel.add(buttonPanel);

        return formPanel;
    }
    
    private JPanel createDisplayPanel() {
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        displayPanel.setBackground(Color.WHITE);

        // Title
        JLabel titleLabel = new JLabel("Registered Users");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        displayPanel.add(titleLabel, BorderLayout.NORTH);

        // Create table with columns
        String[] columnNames = {"ID", "Name", "Gender", "Contact", "Address"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        
        dataTable = new JTable(tableModel);
        dataTable.setFont(new Font("Arial", Font.PLAIN, 12));
        dataTable.setRowHeight(25);
        dataTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        dataTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Set column widths
        dataTable.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
        dataTable.getColumnModel().getColumn(1).setPreferredWidth(100); // Name
        dataTable.getColumnModel().getColumn(2).setPreferredWidth(60);  // Gender
        dataTable.getColumnModel().getColumn(3).setPreferredWidth(80);  // Contact
        dataTable.getColumnModel().getColumn(4).setPreferredWidth(150); // Address
        
        JScrollPane tableScroll = new JScrollPane(dataTable);
        tableScroll.setPreferredSize(new Dimension(400, 300));
        displayPanel.add(tableScroll, BorderLayout.CENTER);
        
        // Bottom panel with buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(Color.WHITE);
        
        JButton clearButton = new JButton("Clear All");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAllRegistrations();
            }
        });
        
        bottomPanel.add(clearButton);
        displayPanel.add(bottomPanel, BorderLayout.SOUTH);

        return displayPanel;
    }
    
    // Helper methods
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }
    
    private JTextField createTextField() {
        JTextField field = new JTextField();
        field.setMaximumSize(new Dimension(350, 25));
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        return field;
    }
    
    private JRadioButton createRadioButton(String text) {
        JRadioButton radio = new JRadioButton(text);
        radio.setFont(new Font("Arial", Font.PLAIN, 12));
        radio.setBackground(Color.WHITE);
        return radio;
    }
    
    private void registerUser() {
        // Get values from form
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String gender = maleRadio.isSelected() ? "Male" : 
                       femaleRadio.isSelected() ? "Female" : "Not Selected";
        String address = addressArea.getText().trim();
        String contact = contactField.getText().trim();
        
        // Validate required fields
        if (id.isEmpty() || name.isEmpty() || address.isEmpty() || contact.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!maleRadio.isSelected() && !femaleRadio.isSelected()) {
            JOptionPane.showMessageDialog(frame, "Please select gender!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Check for duplicate ID
        for (Registration reg : registrations) {
            if (reg.id.equals(id)) {
                JOptionPane.showMessageDialog(frame, "ID already exists! Please use a different ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        // Create new registration
        Registration newRegistration = new Registration(id, name, gender, address, contact);
        registrations.add(newRegistration);
        
        // Update table
        updateTable();
        
        // Clear form for next entry
        clearForm();
        
        JOptionPane.showMessageDialog(frame, 
            "User registered successfully!\n\nID: " + id + "\nName: " + name, 
            "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void updateTable() {
        // Clear existing table data
        tableModel.setRowCount(0);
        
        // Add all registrations to table
        for (Registration reg : registrations) {
            tableModel.addRow(reg.toTableRow());
        }
    }
    
    private void clearForm() {
        idField.setText("");
        nameField.setText("");
        maleRadio.setSelected(false);
        femaleRadio.setSelected(false);
        addressArea.setText("");
        contactField.setText("");
        idField.requestFocus(); // Set focus back to ID field for next entry
    }
    
    private void clearAllRegistrations() {
        if (registrations.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No registrations to clear!", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(frame, 
            "Are you sure you want to clear all " + registrations.size() + " registrations?",
            "Confirm Clear", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            registrations.clear();
            updateTable();
            JOptionPane.showMessageDialog(frame, "All registrations cleared!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void exitApplication() {
        int confirm = JOptionPane.showConfirmDialog(frame, 
            "Are you sure you want to exit?", 
            "Confirm Exit", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    public void show() {
        frame.setVisible(true);
    }
    
    // Main method to test the form
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new regform().show();
        });
    }
}