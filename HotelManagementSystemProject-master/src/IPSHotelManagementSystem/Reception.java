package IPSHotelManagementSystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Reception extends JDialog implements ActionListener {
    JButton checkInButton, driverStatusButton, allCustomerInfoButton, checkOutButton,searchRoomButton;
    Reception(){
        Toolkit tk = Toolkit.getDefaultToolkit();
        int width = (int) tk.getScreenSize().getWidth();

        checkInButton = new JButton("NEW CUSTOMER");
        checkInButton.setBackground(Color.black);
        checkInButton.setForeground(Color.white);
        checkInButton.setBounds(10,20,190,40);
        checkInButton.addActionListener(this);
        add(checkInButton);

        allCustomerInfoButton = new JButton("ALL CUSTOMER INFO");
        allCustomerInfoButton.setBackground(Color.black);
        allCustomerInfoButton.setForeground(Color.white);
        allCustomerInfoButton.setBounds(10,85,190,40);
        allCustomerInfoButton.addActionListener(this);
        add(allCustomerInfoButton);

        searchRoomButton = new JButton("SEARCH ROOM");
        searchRoomButton.setBackground(Color.black);
        searchRoomButton.setForeground(Color.white);
        searchRoomButton.setBounds(10,150,190,40);
        searchRoomButton.addActionListener(this);
        add(searchRoomButton);

        checkOutButton = new JButton("CHECK OUT");
        checkOutButton.setBackground(Color.black);
        checkOutButton.setForeground(Color.white);
        checkOutButton.setBounds(10,215,190,40);
        checkOutButton.addActionListener(this);
        add(checkOutButton);

        driverStatusButton = new JButton("DRIVER STATUS");
        driverStatusButton.setBackground(Color.black);
        driverStatusButton.setForeground(Color.white);
        driverStatusButton.setBounds(10,280,190,40);
        driverStatusButton.addActionListener(this);
        add(driverStatusButton);

        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("IPSHotelManagementSystem/images/receptionnew.jpg"));
        Image img2 = img1.getImage().getScaledInstance(450,300,Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        JLabel imagelabel = new JLabel(img3);
        imagelabel.setBounds( 215,10,450,300);
        add(imagelabel);

        setLayout(null);
        setTitle("RECEPTION");
        setModal(true);
        getContentPane().setBackground(new Color(199, 178, 160));
        setSize(700,380);
        setLocation((width - 700)/2,130);
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        new Reception().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkInButton){
            this.setVisible(false);
            new CheckIn().setVisible(true);
        }else if (e.getSource() == allCustomerInfoButton){
            this.setVisible(false);
            new AllCustomerInfo().setVisible(true);
        }else if (e.getSource() == searchRoomButton){
            this.setVisible(false);
            new AllRoomInfo().setVisible(true);
        }else if (e.getSource() == driverStatusButton){
            this.setVisible(false);
            new DriverStatus().setVisible(true);
        }else if (e.getSource() == checkOutButton){
            this.setVisible(false);
            new CheckOut().setVisible(true);
        }
    }
}

/** This is a class defining Check in DialogueBox for checking in new customer*/
@SuppressWarnings("ALL")
class CheckIn extends JDialog implements ActionListener, ItemListener {
    JTextField customerIDNumberField, customerNameField, customerCountryField,roomPriceField, roomDepositField;
    JButton addCustomerButton, receptionButton;
    JRadioButton maleRadioButton, femaleRadioButton, otherRadioButton;
    JComboBox idComboBox, allocatedRoomComboBox, bedTypeComboBox;

    CheckIn(){
        JLabel idLabel = new JLabel("ID");
        idLabel.setBounds(10,10,100,30);
        idLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(idLabel);

        idComboBox = new JComboBox(new String[] {"PASSPORT","AADHAR CARD","DRIVING LICENSE","VOTER ID"});
        idComboBox.setBounds(150,10,135,30);
        idComboBox.setBackground(Color.white);
        add(idComboBox);

        JLabel idNumberLabel = new JLabel("ID NUMBER");
        idNumberLabel.setBounds(10,50,100,30);
        idNumberLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(idNumberLabel);

        customerIDNumberField = new JTextField();
        customerIDNumberField.setBounds(150,50,135,30);
        add(customerIDNumberField);

        JLabel nameLabel = new JLabel("NAME");
        nameLabel.setBounds(10,90,100,30);
        nameLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(nameLabel);

        customerNameField = new JTextField();
        customerNameField.setBounds(150,90,135,30);
        add(customerNameField);

        JLabel customerGenderLabel = new JLabel("GENDER");
        customerGenderLabel.setBounds(10,130,100,30);
        customerGenderLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(customerGenderLabel);

        maleRadioButton = new JRadioButton("M");
        maleRadioButton.setBounds(145,130,35,30);
        maleRadioButton.setBackground(new Color(242,179,162));
        add(maleRadioButton);

        femaleRadioButton = new JRadioButton("F");
        femaleRadioButton.setBounds(190,130,35,30);
        femaleRadioButton.setBackground(new Color(242,179,162));
        add(femaleRadioButton);

        otherRadioButton = new JRadioButton("Other");
        otherRadioButton.setBounds(235,130,65,30);
        otherRadioButton.setBackground(new Color(242,179,162));
        add(otherRadioButton);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        genderGroup.add(otherRadioButton);

        JLabel countryLabel = new JLabel("COUNTRY");
        countryLabel.setBounds(10,170,100,30);
        countryLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(countryLabel);

        customerCountryField = new JTextField();
        customerCountryField.setBounds(150,170,135,30);
        add(customerCountryField);

        JLabel bedTypeLabel = new JLabel("BED TYPE ?");
        bedTypeLabel.setBounds(10,210,100,30);
        bedTypeLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(bedTypeLabel);

        bedTypeComboBox = new JComboBox(new String[] {"","SINGLE BED","DOUBLE BED"});
        bedTypeComboBox.setBounds(150,210,135,30);
        bedTypeComboBox.setBackground(Color.white);
        bedTypeComboBox.addItemListener(this);
        add(bedTypeComboBox);

        JLabel roomLabel = new JLabel("ALLOCATED ROOM");
        roomLabel.setBounds(10,250,130,30);
        roomLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(roomLabel);

        allocatedRoomComboBox = new JComboBox();
        allocatedRoomComboBox.setBounds(150,250,135,30);
        allocatedRoomComboBox.setBackground(Color.white);
        allocatedRoomComboBox.addItemListener(this);
        add(allocatedRoomComboBox);

        JLabel priceLabel = new JLabel("PRICE(Rs.)");
        priceLabel.setBounds(10,290,100,30);
        priceLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(priceLabel);

        roomPriceField = new JTextField();
        roomPriceField.setBounds(150,290,135,30);
        roomPriceField.setEditable(false);
        add(roomPriceField);

        JLabel depositLabel = new JLabel("DEPOSIT(Rs.)");
        depositLabel.setBounds(10,330,100,30);
        depositLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(depositLabel);

        roomDepositField = new JTextField();
        roomDepositField.setBounds(150,330,135,30);
        add(roomDepositField);

        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("IPSHotelManagementSystem/images/checkin1.jpg"));
        Image img2 = img1.getImage().getScaledInstance(383,400,Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        JLabel imagLabel = new JLabel(img3);
        imagLabel.setBounds(310,0,383,400);
        add(imagLabel);

        addCustomerButton = new JButton("CHECK IN");
        addCustomerButton.setBackground(Color.black);
        addCustomerButton.setForeground(Color.white);
        addCustomerButton.setBounds(5,370,140,30);
        add(addCustomerButton);
        addCustomerButton.addActionListener(this);

        receptionButton = new JButton("RECEPTION");
        receptionButton.setBackground(Color.black);
        receptionButton.setForeground(Color.white);
        receptionButton.setBounds(155,370,140,30);
        add(receptionButton);
        receptionButton.addActionListener(this);

        setLayout(null);
        getContentPane().setBackground(new Color(242,179,162));
        setTitle("NEW CUSTOMER DETAILS");
        setModal(true);
        pack();
        setSize(720,460);
        setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCustomerButton) {
            String id = (String) idComboBox.getSelectedItem();
            String idnumber = customerIDNumberField.getText();
            String name = customerNameField.getText();
            String gender = null;
            if (maleRadioButton.isSelected()) {
                gender = "Male";
            } else if (femaleRadioButton.isSelected()){
                gender = "Female";
            }else if (otherRadioButton.isSelected()){
                gender = "Other";
            }
            String country = customerCountryField.getText();
            String room = (String) allocatedRoomComboBox.getSelectedItem();
            String deposit = roomDepositField.getText();
            DatabaseConnect con = new DatabaseConnect();
            try {
                con.stat.executeUpdate("insert into customer values('" + id + "','" + idnumber + "','" + name + "','" + gender + "','" +country + "','" + room + "','YES','" + deposit + "')");
                con.stat.executeUpdate("update room set AVAILABILITY = 'OCCUPIED' where ROOMNUMBER = '"+room+"'");
                JOptionPane.showMessageDialog(null, "New Customer Added");
                this.setVisible(false);
                new CheckIn().setVisible(true);
            } catch (Exception c) {
                System.out.println(c);
            }
        }else if (e.getSource() == receptionButton){
            this.setVisible(false);
            new Reception().setVisible(true);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == bedTypeComboBox){
            String bedType = (String) bedTypeComboBox.getSelectedItem();
            allocatedRoomComboBox.removeAllItems();
            try {
                DatabaseConnect con = new DatabaseConnect();
                ResultSet roomResult = con.stat.executeQuery( "Select * from room where AVAILABILITY = 'AVAILABLE' and bedType = '"+ bedType+"' order by roomNumber");
                while (roomResult.next()) {
                    allocatedRoomComboBox.addItem(roomResult.getString("ROOMNUMBER"));
                }
            }catch (Exception ae){
                System.out.println(ae);
            }
        }else if (e.getSource() == allocatedRoomComboBox){
            String allocatedRoom = (String) allocatedRoomComboBox.getSelectedItem();
            try {
                DatabaseConnect con = new DatabaseConnect();
                ResultSet roomResult = con.stat.executeQuery( "Select * from room where ROOMNUMBER = '"+allocatedRoom+"'");
                while (roomResult.next()) {
                    roomPriceField.setText(roomResult.getString("PRICE"));
                }
            }catch (Exception ae){
                System.out.println(ae);
            }
        }
    }
}
/** This is a class defining Check out DialogueBox for checking out customers*/
@SuppressWarnings("ALL")
class CheckOut extends JDialog implements ActionListener, ItemListener {
    JButton checkOutButton, receptionButton;
    JComboBox customerIdComboBox;
    JTextField roomNumberField, nameField, checkedInField, amountPaidField, pendingAmountField;

    CheckOut() {
        JLabel customerIdLabel = new JLabel("CUSTOMER ID");
        customerIdLabel.setBounds(10, 10, 130, 30);
        customerIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(customerIdLabel);

        customerIdComboBox = new JComboBox(new String[]{""});
        try {
            DatabaseConnect con = new DatabaseConnect();
            ResultSet customerIdResultSet = con.stat.executeQuery("select * from customer");
            while (customerIdResultSet.next()) {
                customerIdComboBox.addItem(customerIdResultSet.getString("idNumber"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        customerIdComboBox.setBounds(150, 10, 130, 30);
        customerIdComboBox.addItemListener(this);
        add(customerIdComboBox);

        JLabel roomNumberLabel = new JLabel("ROOM NUMBER");
        roomNumberLabel.setBounds(10, 50, 130, 30);
        roomNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(roomNumberLabel);

        roomNumberField = new JTextField();
        roomNumberField.setBounds(150, 50, 130, 30);
        roomNumberField.setEditable(false);
        add(roomNumberField);

        JLabel nameLabel = new JLabel("NAME");
        nameLabel.setBounds(10, 90, 130, 30);
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 90, 130, 30);
        nameField.setEditable(false);
        add(nameField);

        JLabel checkedInStatLabel = new JLabel("CHECKED IN?");
        checkedInStatLabel.setBounds(10, 130, 130, 30);
        checkedInStatLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(checkedInStatLabel);

        checkedInField = new JTextField();
        checkedInField.setBounds(150, 130, 130, 30);
        checkedInField.setEditable(false);
        add(checkedInField);

        JLabel amountPaidLabel = new JLabel("AMOUNT PAID");
        amountPaidLabel.setBounds(10, 170, 130, 30);
        amountPaidLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(amountPaidLabel);

        amountPaidField = new JTextField();
        amountPaidField.setBounds(150, 170, 130, 30);
        amountPaidField.setEditable(false);
        add(amountPaidField);

        JLabel pendingAmountLabel = new JLabel("PENDING AMOUNT");
        pendingAmountLabel.setBounds(10, 210, 130, 30);
        pendingAmountLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(pendingAmountLabel);

        pendingAmountField = new JTextField();
        pendingAmountField.setBounds(150, 210, 130, 30);
        pendingAmountField.setEditable(false);
        add(pendingAmountField);

        checkOutButton = new JButton("CHECK OUT");
        checkOutButton.setBackground(Color.BLACK);
        checkOutButton.setForeground(Color.white);
        checkOutButton.setBounds(10, 260, 120, 30);
        checkOutButton.addActionListener(this);
        add(checkOutButton);

        receptionButton = new JButton("RECEPTION");
        receptionButton.setBackground(Color.BLACK);
        receptionButton.setForeground(Color.white);
        receptionButton.setBounds(150, 260, 120, 30);
        receptionButton.addActionListener(this);
        add(receptionButton);

        ImageIcon img4 = new ImageIcon(ClassLoader.getSystemResource("IPSHotelManagementSystem/images/checkout2.jpg"));
        Image img5 = img4.getImage().getScaledInstance(311, 350, Image.SCALE_DEFAULT);
        ImageIcon img6 = new ImageIcon(img5);
        JLabel imageLabel = new JLabel(img6);
        imageLabel.setBounds(320, 0, 311, 350);
        add(imageLabel);

        setLayout(null);
        setTitle("CHECK OUT");
        getContentPane().setBackground(new Color(242, 179, 162));
        setModal(true);
        pack();
        setSize(660, 350);
        setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkOutButton) {
            try {
                DatabaseConnect con = new DatabaseConnect();
                String custIdNumber = (String) customerIdComboBox.getSelectedItem();
                String roomNumber = roomNumberField.getText();
                con.stat.executeUpdate("delete from customer where idNumber = '" + custIdNumber + "'");
                con.stat.executeUpdate("update room set availability = 'AVAILABLE' where roomNumber = '" + roomNumber + "'");
                JOptionPane.showMessageDialog(null, "CHECK OUT DONE");
                this.setVisible(false);
                new Reception().setVisible(true);
            } catch (Exception ee) {
                System.out.println(ee);
            }
        } else if (e.getSource() == receptionButton) {
            this.setVisible(false);
            new Reception().setVisible(true);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == customerIdComboBox) {
            String roomNumber = null;
            String deposit = null;
            int pendingAmount;
            String price;
            try {
                String idNumber = (String) customerIdComboBox.getSelectedItem();
                DatabaseConnect con = new DatabaseConnect();
                ResultSet customerResultSet = con.stat.executeQuery("select * from customer where idNumber = '" + idNumber + "'");
                while (customerResultSet.next()) {
                    roomNumberField.setText(customerResultSet.getString("roomNumber"));
                    nameField.setText(customerResultSet.getString("name"));
                    checkedInField.setText(customerResultSet.getString("status"));
                    amountPaidField.setText(customerResultSet.getString("deposit"));
                    roomNumber = customerResultSet.getString("roomNumber");
                    deposit = customerResultSet.getString("deposit");
                }
                ResultSet pendingAmountResultSet = con.stat.executeQuery("select * from room where roomNumber = '" + roomNumber + "'");
                while (pendingAmountResultSet.next()) {
                    price = pendingAmountResultSet.getString("price");
                    pendingAmount = Integer.parseInt(price) - Integer.parseInt(deposit);
                    pendingAmountField.setText(Integer.toString(pendingAmount));
                }
            } catch (Exception ae) {
                System.out.println(ae);
            }
        }
    }
}
/** This is a class defining Driver Status DialogueBox for showing status of drivers*/
@SuppressWarnings("ALL")
class DriverStatus extends JDialog implements ActionListener, ItemListener {
    JComboBox statusComboBox, driveInUseComboBox;
    JTextField roomNumberField, locationField;
    JButton updateButton, receptionButton;

    DriverStatus(){

        JLabel driverUsageLabel = new JLabel("DRIVER ACTIVITY");
        driverUsageLabel.setBounds(10,20,130,30);
        driverUsageLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(driverUsageLabel);

        statusComboBox = new JComboBox(new String[] {"","AVAILABLE","BUSY"});
        statusComboBox.setBounds(180,20,120,30);
        statusComboBox.addItemListener(this);
        add(statusComboBox);

        JLabel driverInUseLabel = new JLabel("DRIVER IN USE");
        driverInUseLabel.setBounds(10,70,140,30);
        driverInUseLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(driverInUseLabel);

        driveInUseComboBox = new JComboBox(new String[]{""});
        driveInUseComboBox.setBounds(180,70,120,30);
        add(driveInUseComboBox);

        JLabel roomNumberLabel = new JLabel("ROOM NUMBER");
        roomNumberLabel.setBounds(10,120,120,30);
        roomNumberLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(roomNumberLabel);

        roomNumberField = new JTextField();
        roomNumberField.setBounds(180,120,120,30);
        add(roomNumberField);

        JLabel locationLabel = new JLabel("LOCATION");
        locationLabel.setBounds(10,170,120,30);
        locationLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(locationLabel);

        locationField = new JTextField();
        locationField.setBounds(180,170,120,30);
        add(locationField);

        updateButton = new JButton("UPDATE");
        updateButton.setBackground(Color.black);
        updateButton.setForeground(Color.white);
        updateButton.setBounds(40,230,100,30);
        add(updateButton);
        updateButton.addActionListener(this);

        receptionButton = new JButton("RECEPTION");
        receptionButton.setBackground(Color.black);
        receptionButton.setForeground(Color.white);
        receptionButton.setBounds(170,230,120,30);
        add(receptionButton);
        receptionButton.addActionListener(this);

        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("IPSHotelManagementSystem/images/hotelpickup.jpg"));
        Image img2 = img1.getImage().getScaledInstance(300,340,Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        JLabel imageLabel = new JLabel(img3);
        imageLabel.setBounds(260,-60,300,398);
        add(imageLabel);

        setLayout(null);
        setResizable(false);
        setModal(true);
        setTitle("PICKUP/DROP-OFF");
        getContentPane().setBackground(new Color(119,177,165));
        pack();
        setSize(570,320);
        setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton){
            if (statusComboBox.getSelectedItem() == "BUSY"){

                String driver = (String) driveInUseComboBox.getSelectedItem();
                DatabaseConnect con = new DatabaseConnect();
                try {
                    con.stat.executeUpdate("update driver set AVAILABLE = 'AVAILABLE', location = '', PICKUP = '' where name = '"+driver+"'");
                    JOptionPane.showMessageDialog(null,"DRIVER STATUS UPDATED");
                }catch (Exception ae){
                    System.out.println(ae);
                }
                this.setVisible(false);
                new DriverStatus().setVisible(true);
            }else if (statusComboBox.getSelectedItem() =="AVAILABLE"){

                String driver = (String) driveInUseComboBox.getSelectedItem();
                String location = locationField.getText();
                String roomNumber = roomNumberField.getText();
                DatabaseConnect con = new DatabaseConnect();
                try {
                    con.stat.executeUpdate("update driver set AVAILABLE = 'BUSY', location = '"+location+"', PICKUP = '"+roomNumber+"' where name = '"+driver+"'");
                    JOptionPane.showMessageDialog(null,"DRIVER STATUS UPDATED");
                }catch (Exception ae){
                    System.out.println(ae);
                }
                this.setVisible(false);
                new DriverStatus().setVisible(true);
            }
        }else if (e.getSource() == receptionButton){
            this.setVisible(false);
            new Reception().setVisible(true);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String status = (String) statusComboBox.getSelectedItem();
        driveInUseComboBox.removeAllItems();
        roomNumberField.setText("");
        locationField.setText("");
        try {
            DatabaseConnect con = new DatabaseConnect();
            ResultSet result = con.stat.executeQuery("select * from driver where AVAILABLE = '"+status+"'");
            while (result.next()){
                driveInUseComboBox.addItem(result.getString("name"));
                roomNumberField.setText(result.getString("PICKUP"));
                locationField.setText(result.getString("LOCATION"));
            }
        }catch (Exception ae){
            System.out.println(ae);
        }
    }
}
/** This is a class defining Customer info DialogueBox for showing info of customers*/
class AllCustomerInfo extends JDialog implements ActionListener {
    JTable table;
    JButton loadDataButton, receptionButton;
    AllCustomerInfo(){
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(5,10,900,450);
        add(scrollPane);

        loadDataButton = new JButton("LOAD INFO");
        loadDataButton.setBounds(300,490,100,30);
        loadDataButton.setForeground(Color.white);
        loadDataButton.setBackground(Color.black);
        loadDataButton.addActionListener(this);
        add(loadDataButton);

        receptionButton = new JButton("RECEPTION");
        receptionButton.setBounds(500,490,100,30);
        receptionButton.setForeground(Color.white);
        receptionButton.setBackground(Color.black);
        receptionButton.addActionListener(this);
        add(receptionButton);

        setLayout(null);
        setModal(true);
        setTitle("CUSTOMER INFORMATION");
        getContentPane().setBackground(new Color(254,227,200));
        pack();
        setSize(920,570);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadDataButton) {
            try {
                DatabaseConnect con = new DatabaseConnect();
                ResultSet result = con.stat.executeQuery("select ID,IDNUMBER, NAME, GENDER,COUNTRY, ROOMNUMBER, STATUS, DEPOSIT from customer");
                table.setModel(DbUtils.resultSetToTableModel(result));
                table.getColumn("GENDER").setMaxWidth(70);
                table.getColumn("ROOMNUMBER").setMaxWidth(110);
                table.getColumn("COUNTRY").setMaxWidth(110);
                table.getColumn("STATUS").setMaxWidth(70);
                table.getColumn("DEPOSIT").setMaxWidth(70);
            }catch (Exception ae){
                System.out.println(ae);
            }
        } else if (e.getSource() == receptionButton) {
            this.setVisible(false);
            new Reception().setVisible(true);
        }
    }
}

