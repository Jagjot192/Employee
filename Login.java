package employee;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class Login extends JFrame implements ActionListener{
    JTextField tusername;
    JPasswordField tpassword;
    JButton login, back;

    // no longer storing added components in a field

    Login() {
        // removed unused local 'text'
        setLayout(null);

        JLabel username = new JLabel("Username");
        username.setBounds(40,20,100,30);
        add(username);

        tusername = new JTextField();
        tusername.setBounds(150,20,150,30);
        add(tusername);


        JLabel password = new JLabel("Password");
        password.setBounds(40,70,100,30);
        add(password);

        tpassword = new JPasswordField();
        tpassword.setBounds(150,70,150,30);
        add(tpassword);

        login = new JButton("Login");
        login.setBounds(40,150,120,30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);


        back = new JButton("Back");
        back.setBounds(180,150,120,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);


        ImageIcon   i11 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i22 = i11.getImage().getScaledInstance(600,400, java.awt.Image.SCALE_DEFAULT);
        ImageIcon i33 = new ImageIcon(i22);
        JLabel images = new JLabel(i33);
        images.setBounds(350,10,600,400);
        add(images);


        ImageIcon   i1 = new ImageIcon(ClassLoader.getSystemResource("icons/LoginB.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600,300, java.awt.Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,600,300);
        add(image);

        
        setSize(600,300);
        setLocation(400,200);
        setVisible(true);
    }
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if(e.getSource() == login){
            try{
                conn c = new conn();
                String username = tusername.getText();
                char[] passwordChars = tpassword.getPassword();
                String password = new String(passwordChars);
                java.util.Arrays.fill(passwordChars, '\0');
                String query = "select * from login where username = '"+username+"' and password = '"+password+"'";
                java.sql.ResultSet rs = c.statement.executeQuery(query);
                if(rs.next()){
                    setVisible(false);
                    new Main_class();
        
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                    setVisible(false);
                }
            }catch(Exception E){
                E.printStackTrace();
            }

        } else if (e.getSource() == back){
            System.exit(90);
        }
        
    }

public static void main(String[] args) {
    new Login();
}
}
