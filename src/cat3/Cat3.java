package cat3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cat3 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RegistrationForm().setVisible(true);
        });
    }
}

class RegistrationForm extends JFrame {
    private JTextField nameField;
    private JTextField mobileField;
    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;
    private JTextField dayField;
    private JComboBox<String> monthCombo;
    private JTextField yearField;
    private JTextArea addressArea;
    private JCheckBox termsCheckBox;
    private JButton submitButton;
    private JButton resetButton;
    private JTextArea resultsArea;

    public RegistrationForm() {
        initializeForm();
    }

    private void initializeForm() {
        setTitle("Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create main panel with left and right split
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(350);
        splitPane.setDividerSize(3);
        splitPane.setEnabled(false); // Prevent user from moving divider

        // Left panel - Registration Form
        JPanel leftPanel = createFormPanel();
        
        // Right panel - Registration Details
        JPanel rightPanel = createResultsPanel();

        splitPane.setLeftComponent(leftPanel);
        splitPane.setRightComponent(rightPanel);

        add(splitPane);
        addEventListeners();
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(Color.WHITE);

        // Title
        JLabel titleLabel = new JLabel("Registration Form");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(titleLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Name Section
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(nameLabel);
        
        nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(280, 25));
        nameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        nameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        formPanel.add(nameField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Mobile Section
        JLabel mobileLabel = new JLabel("Mobile");
        mobileLabel.setFont(new Font("Arial", Font.BOLD, 12));
        mobileLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(mobileLabel);
        
        mobileField = new JTextField();
        mobileField.setMaximumSize(new Dimension(280, 25));
        mobileField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mobileField.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        formPanel.add(mobileField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Gender Section
        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setFont(new Font("Arial", Font.BOLD, 12));
        genderLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(genderLabel);
        
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        genderPanel.setBackground(Color.WHITE);
        genderPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        genderPanel.setMaximumSize(new Dimension(280, 25));
        
        maleRadio = new JRadioButton("Male");
        maleRadio.setFont(new Font("Arial", Font.PLAIN, 12));
        maleRadio.setBackground(Color.WHITE);
        
        femaleRadio = new JRadioButton("Female");
        femaleRadio.setFont(new Font("Arial", Font.PLAIN, 12));
        femaleRadio.setBackground(Color.WHITE);
        
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        
        formPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        formPanel.add(genderPanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // DOB Section
        JLabel dobLabel = new JLabel("DOB");
        dobLabel.setFont(new Font("Arial", Font.BOLD, 12));
        dobLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(dobLabel);
        
        JPanel dobPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        dobPanel.setBackground(Color.WHITE);
        dobPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        dobPanel.setMaximumSize(new Dimension(280, 25));
        
        dayField = new JTextField(2);
        dayField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        dayField.setPreferredSize(new Dimension(30, 20));
        
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", 
                          "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        monthCombo = new JComboBox<>(months);
        monthCombo.setPreferredSize(new Dimension(50, 20));
        
        yearField = new JTextField(4);
        yearField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        yearField.setPreferredSize(new Dimension(40, 20));
        
        JLabel dotsLabel = new JLabel("19...");
        dotsLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        
        dobPanel.add(dayField);
        dobPanel.add(new JLabel(" "));
        dobPanel.add(monthCombo);
        dobPanel.add(new JLabel(" "));
        dobPanel.add(yearField);
        dobPanel.add(new JLabel(" "));
        dobPanel.add(dotsLabel);
        
        formPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        formPanel.add(dobPanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Address Section
        JLabel addressLabel = new JLabel("Address");
        addressLabel.setFont(new Font("Arial", Font.BOLD, 12));
        addressLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(addressLabel);
        
        addressArea = new JTextArea(3, 20);
        addressArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addressArea.setLineWrap(true);
        addressArea.setWrapStyleWord(true);
        JScrollPane addressScroll = new JScrollPane(addressArea);
        addressScroll.setMaximumSize(new Dimension(280, 60));
        addressScroll.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        formPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        formPanel.add(addressScroll);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Terms and Conditions Checkbox
        termsCheckBox = new JCheckBox("Accept Terms And Conditions.");
        termsCheckBox.setFont(new Font("Arial", Font.PLAIN, 12));
        termsCheckBox.setBackground(Color.WHITE);
        termsCheckBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(termsCheckBox);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonPanel.setMaximumSize(new Dimension(280, 30));
        
        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 12));
        submitButton.setPreferredSize(new Dimension(80, 25));
        
        resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.PLAIN, 12));
        resetButton.setPreferredSize(new Dimension(80, 25));
        
        buttonPanel.add(submitButton);
        buttonPanel.add(resetButton);
        
        formPanel.add(buttonPanel);

        return formPanel;
    }

    private JPanel createResultsPanel() {
        JPanel resultsPanel = new JPanel(new BorderLayout());
        resultsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        resultsPanel.setBackground(Color.WHITE);

        // Title
        JLabel titleLabel = new JLabel("Registration Details");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        resultsPanel.add(titleLabel, BorderLayout.NORTH);

        // Results area
        resultsArea = new JTextArea();
        resultsArea.setEditable(false);
        resultsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        resultsArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        resultsArea.setText("No registration data yet...\n\nSubmit the form to see details here.");
        resultsArea.setBackground(new Color(248, 248, 248));
        
        JScrollPane resultsScroll = new JScrollPane(resultsArea);
        resultsPanel.add(resultsScroll, BorderLayout.CENTER);

        return resultsPanel;
    }

    private void addEventListeners() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitRegistration();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });
    }

    private void submitRegistration() {
        // Get all values from form
        String name = nameField.getText().trim();
        String mobile = mobileField.getText().trim();
        String gender = maleRadio.isSelected() ? "Male" : 
                       femaleRadio.isSelected() ? "Female" : "Not Selected";
        String day = dayField.getText().trim();
        String month = (String) monthCombo.getSelectedItem();
        String year = yearField.getText().trim();
        String address = addressArea.getText().trim();
        boolean termsAccepted = termsCheckBox.isSelected();

        // Validate required fields
        if (name.isEmpty() || mobile.isEmpty() || day.isEmpty() || year.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all required fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!termsAccepted) {
            JOptionPane.showMessageDialog(this, "Please accept terms and conditions!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Display results in the results area
        displayResultsInTextArea(name, mobile, gender, day, month, year, address);
    }

    private void displayResultsInTextArea(String name, String mobile, String gender, 
                                        String day, String month, String year, String address) {
        StringBuilder results = new StringBuilder();
        
        results.append("Name           : ").append(name).append("\n");
        results.append("Mobile         : ").append(mobile).append("\n");
        results.append("Gender         : ").append(gender).append("\n");
        results.append("Date of Birth  : ").append(day).append("/").append(month).append("/").append(year).append("\n");
        results.append("Terms Accepted : ").append("Yes").append("\n\n");
        results.append("---\n");
        results.append("ADDRESS ===\n");
        results.append(address).append("\n\n");
        results.append("---\n");
        results.append("FORM SUBMITTED AT ===\n");
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        results.append(dateFormat.format(new Date()));

        resultsArea.setText(results.toString());
    }

    private void resetForm() {
        nameField.setText("");
        mobileField.setText("");
        maleRadio.setSelected(false);
        femaleRadio.setSelected(false);
        dayField.setText("");
        monthCombo.setSelectedIndex(0);
        yearField.setText("");
        addressArea.setText("");
        termsCheckBox.setSelected(false);
        resultsArea.setText("No registration data yet...\n\nSubmit the form to see details here.");
    }
}