package IPSHotelManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class HotelManagementSystemLogin extends JFrame implements ActionListener {
    HotelManagementSystemLogin() {

        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("IPSHotelManagementSystem/images/newhotel.jpg"));
        Image img2 = img1.getImage().getScaledInstance(600,416,Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        JLabel imageLabel = new JLabel(img3);
        imageLabel.setBounds(0, 0, 600,416);
        add(imageLabel);

        JLabel projectNameLabel = new JLabel("Hotel Management System");
        projectNameLabel.setBounds(10, 330, 400, 50);
        projectNameLabel.setForeground(Color.BLACK);
        projectNameLabel.setFont(new Font("serif", Font.BOLD, 30));
        imageLabel.add(projectNameLabel);

        JLabel hotelTagLineLabel = new JLabel(" Delivering Luxurious Stays");
        hotelTagLineLabel.setBounds(40, 10, 600, 50);
        hotelTagLineLabel.setForeground(Color.white);
        hotelTagLineLabel.setFont(new Font( Font.SERIF,Font.BOLD, 40));
        imageLabel.add(hotelTagLineLabel);

        JButton continueButton = new JButton("CONTINUE");
        continueButton.setBackground(Color.black);
        continueButton.setForeground(Color.white);
        continueButton.setBounds(450,340, 100,30);
        continueButton.addActionListener(this);
        imageLabel.add(continueButton);

        setTitle("WELCOME TO FULLERTON");
        setLayout(null);
        setResizable(false);
        pack();
        setSize( 600,416);
        setLocationRelativeTo(null);
        setVisible(true);

        while (true) {
            projectNameLabel.setVisible(false);
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(e);
            }
            projectNameLabel.setVisible(true);
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(false);
        new Login().setVisible(true);
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        new HotelManagementSystemLogin();
    }
}
/** This is a class defining Login DialogueBox for loging in to the dashboard*/
class Login extends JDialog implements ActionListener {
    JLabel userNameLabel, PasswordLabel;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton, cancelButton;

    Login(){
        userNameLabel = new JLabel("Username ");
        userNameLabel.setBounds(10,20,80,30);
        userNameLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(userNameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(100,20,120,30);
        add(usernameField);

        PasswordLabel = new JLabel("Password ");
        PasswordLabel.setBounds(10,70,80,30);
        PasswordLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(PasswordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100,70,120,30);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBackground(Color.black);
        loginButton.setForeground(Color.white);
        loginButton.setBounds(20,130,90,30);
        loginButton.addActionListener(this);
        add(loginButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(Color.black);
        cancelButton.setForeground(Color.white);
        cancelButton.setBounds(130,130,90,30);
        cancelButton.addActionListener(this);
        add(cancelButton);

        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("IPSHotelManagementSystem/images/log.jpg"));
        Image img2 = img1.getImage().getScaledInstance(200,163,Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        JLabel imageLabel = new JLabel(img3);
        imageLabel.setBounds(225,10,200,163 );
        add(imageLabel);

        setTitle("LOGIN INTERFACE");
        getContentPane().setBackground(new Color(235, 237, 243, 238));
        setLayout(null);
        setModal(true);
        pack();
        setSize(460,230);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton){

            String Username = usernameField.getText();
            String Password = String.valueOf(passwordField.getPassword());
            DatabaseConnect conn = new DatabaseConnect();
            try {
                ResultSet loginResultSet = conn.stat.executeQuery("select * from login where username = '"+ Username+"' and password = '"+ Password+ "'");
                if (loginResultSet.next()){
                    this.setVisible(false);
                    new Dashboard().setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(null, "Invalid Username and Password!");
                    this.setVisible(false);
                }
            } catch (Exception ae) {
                System.out.println(ae);
            }
        }
        else if (e.getSource() == cancelButton){
            System.exit(0);
        }
    }
}

