package IPSHotelManagementSystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

@SuppressWarnings("ALL")
public class ManageEmployee extends JDialog implements ActionListener, ItemListener {
    JTextField nameField, ageField, salaryField, phoneField, aadharField, emailCarField;
    JLabel emailCarLabel;
    JRadioButton maleRadioButton, femaleRadioButton, otherRadioButton;
    JComboBox jobComboBox;
    JButton addButton, showEmployeeButton;

    ManageEmployee(){
        JLabel nameLabel = new JLabel("NAME");
        nameLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        nameLabel.setBounds(10,10,120,30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100,10, 150,30);
        add(nameField);

        JLabel ageLabel = new JLabel("AGE");
        ageLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        ageLabel.setBounds(10,50,120,30);
        add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(100,50, 150,30);
        add(ageField);

        JLabel genderLabel = new JLabel("GENDER");
        genderLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        genderLabel.setBounds(10,90,120,30);
        add(genderLabel);

        maleRadioButton = new JRadioButton("M");
        maleRadioButton.setBounds(100,90,35,30);
        maleRadioButton.setBackground(new Color(254,227,200));
        add(maleRadioButton);

        femaleRadioButton = new JRadioButton("F");
        femaleRadioButton.setBounds(140,90,35,30);
        femaleRadioButton.setBackground(new Color(254,227,200));
        add(femaleRadioButton);

        otherRadioButton = new JRadioButton("Other");
        otherRadioButton.setBounds(180,90,65,30);
        otherRadioButton.setBackground(new Color(254,227,200));
        add(otherRadioButton);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        genderGroup.add(otherRadioButton);

        JLabel jobLabel = new JLabel("JOB");
        jobLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        jobLabel.setBounds(10,130,120,30);
        add(jobLabel);

        jobComboBox = new JComboBox(new String[] {"Hotel Receptionist","Driver","Manager","Accountant","Kitchen Staff","Waiter/Waitress","House Keeping","Maintenance Technician","Parking Attendant","Porter"});
        jobComboBox.setBounds(100,130,150,30);
        jobComboBox.setBackground(Color.white);
        jobComboBox.addItemListener(this);
        add(jobComboBox);

        JLabel salaryLabel = new JLabel("SALARY(Rs.)");
        salaryLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        salaryLabel.setBounds(10,170,120,30);
        add(salaryLabel);

        salaryField = new JTextField();
        salaryField.setBounds(100,170, 150,30);
        add(salaryField);

        JLabel phoneLabel = new JLabel("PHONE|+91");
        phoneLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        phoneLabel.setBounds(10,210,120,30);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(100,210, 150,30);
        add(phoneField);

        JLabel aadharLabel = new JLabel("AADHAR");
        aadharLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        aadharLabel.setBounds(10,250,120,30);
        add(aadharLabel);

        aadharField = new JTextField();
        aadharField.setBounds(100,250, 150,30);
        add(aadharField);

        emailCarLabel = new JLabel("E-MAIL");
        emailCarLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        emailCarLabel.setBounds(10,290,120,30);
        add(emailCarLabel);

        emailCarField = new JTextField();
        emailCarField.setBounds(100,290, 150,30);
        add(emailCarField);

        addButton = new JButton("ADD ");
        addButton.setBackground(Color.black);
        addButton.setForeground(Color.white);
        addButton.setBounds(5,340,100,30);
        add(addButton);
        addButton.addActionListener(this);

        showEmployeeButton = new JButton("SHOW EMPLOYEES");
        showEmployeeButton.setBackground(Color.black);
        showEmployeeButton.setForeground(Color.white);
        showEmployeeButton.setBounds(120,340,150,30);
        add(showEmployeeButton);
        showEmployeeButton.addActionListener(this);

        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("IPSHotelManagementSystem/images/Emp1.jpg"));
        Image img2 = img1.getImage().getScaledInstance(318,280,Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        JLabel imageLabel = new JLabel(img3);
        imageLabel.setBounds(280,70,318,280);
        add(imageLabel);

        setLayout(null);
        getContentPane().setBackground(new Color(254,227,200));
        setModal(true);
        setTitle("ADD EMPLOYEE DETAILS");
        pack();
        setSize(590,430);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        new ManageEmployee().setVisible(true);
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String name = nameField.getText();
            String age = ageField.getText();
            String salary = salaryField.getText();
            String phone = phoneField.getText();
            String aadhar = aadharField.getText();
            String emailCar = emailCarField.getText();
            String gender = null;
            if (maleRadioButton.isSelected()) {
                gender = "Male";
            } else if (femaleRadioButton.isSelected()){
                gender = "Female";
            }else if (otherRadioButton.isSelected()){
                gender = "Other";
            }
            String job = (String) jobComboBox.getSelectedItem();
            if (job == "Driver"){
                DatabaseConnect con = new DatabaseConnect();
                try {
                    con.stat.executeUpdate("insert into driver values('" + name + "','" + age + "','" + gender + "','" + salary + "','" + phone + "','" + aadhar + "','" + emailCar + "','AVAILABLE','','')");
                    JOptionPane.showMessageDialog(null, "New Driver Added");
                    this.setVisible(false);
                    new ManageEmployee().setVisible(true);
                } catch (Exception ae) {
                    System.out.println(ae);
                }
            }else {
                DatabaseConnect con = new DatabaseConnect();
                try {
                    con.stat.executeUpdate("insert into employee values('" + name + "','" + age + "','" + gender + "','" + job + "','" + salary + "','" + phone + "','" + aadhar + "','" + emailCar + "')");
                    JOptionPane.showMessageDialog(null, "New Employee Added");
                    this.setVisible(false);
                    new ManageEmployee().setVisible(true);
                } catch (Exception ae) {
                    System.out.println(ae);
                }
            }
        }else if (e.getSource() == showEmployeeButton){
            this.setVisible(false);
            new AllEmployeeInfo().setVisible(true);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == jobComboBox){
            String job = (String) jobComboBox.getSelectedItem();
            if (job == "Driver"){
                emailCarLabel.setText("CAR");
            }else {
                emailCarLabel.setText("E-MAIL");
            }
        }
    }
}
/** This is a class defining show employee DialogueBox for showing info of all employees*/
@SuppressWarnings("rawtypes")
class AllEmployeeInfo extends JDialog implements ActionListener {
    JTable table;
    JButton loadDataButton, backButton;
    JComboBox jobListComboBox;

    AllEmployeeInfo() {
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(5, 10, 970, 450);
        add(scrollPane);

        jobListComboBox = new JComboBox(new String[]{"All", "Hotel Receptionist", "Driver", "Manager", "Accountant", "Kitchen Staff", "Waiter/Waitress", "House Keeping", "Maintenance Technician", "Parking Attendant", "Porter"});
        jobListComboBox.setBounds(100, 475, 170, 30);
        jobListComboBox.setBackground(Color.white);
        add(jobListComboBox);

        loadDataButton = new JButton("LOAD INFO");
        loadDataButton.setBounds(330, 470, 120, 40);
        loadDataButton.setForeground(Color.white);
        loadDataButton.setBackground(Color.black);
        loadDataButton.addActionListener(this);
        add(loadDataButton);

        backButton = new JButton("ADD EMPLOYEE");
        backButton.setBounds(500, 470, 140, 40);
        backButton.setForeground(Color.white);
        backButton.setBackground(Color.black);
        backButton.addActionListener(this);
        add(backButton);

        setLayout(null);
        setModal(true);
        getContentPane().setBackground(new Color(254, 227, 200));
        setTitle("ALL EMPLOYEE INFORMATION");
        pack();
        setSize(1000, 570);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadDataButton) {
            try {
                String job = (String) jobListComboBox.getSelectedItem();
                if (job == "All") {
                    DatabaseConnect con = new DatabaseConnect();
                    ResultSet employeeResultSet = con.stat.executeQuery("select * from employee");
                    table.setModel(DbUtils.resultSetToTableModel(employeeResultSet));
                    table.getColumn("AGE").setMaxWidth(40);
                    table.getColumn("GENDER").setMaxWidth(65);
                    table.getColumn("SALARY").setMaxWidth(60);
                } else if (job == "Driver") {
                    try {
                        DatabaseConnect con = new DatabaseConnect();
                        ResultSet driverResultSet = con.stat.executeQuery("select NAME,AGE,GENDER,SALARY,PHONE,AADHAR,CAR from driver");
                        table.setModel(DbUtils.resultSetToTableModel(driverResultSet));
                        table.getColumn("AGE").setMaxWidth(50);
                        table.getColumn("GENDER").setMaxWidth(65);
                    } catch (Exception ae) {
                        System.out.println(ae);
                    }
                } else {
                    DatabaseConnect con = new DatabaseConnect();
                    ResultSet employeeResultSet = con.stat.executeQuery("select * from employee where job = '" + job + "'");
                    table.setModel(DbUtils.resultSetToTableModel(employeeResultSet));
                    table.getColumn("age").setMaxWidth(50);
                    table.getColumn("gender").setMaxWidth(60);
                    table.getColumn("salary").setMaxWidth(60);
                }
            } catch (Exception ae) {
                System.out.println(ae);
            }
        } else if (e.getSource() == backButton) {
            this.setVisible(false);
            new ManageEmployee().setVisible(true);
        }
    }
}