package employee;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.Choice;

public class View_Employee extends JFrame implements ActionListener {

    JTable table;

    Choice choiceEMP;

    JButton searchbtn, print, update, back;

    View_Employee() {

        getContentPane().setBackground(new Color(255, 131, 122));

        JLabel search = new JLabel("Search by Employee ID");
        search.setBounds(20,20,150,20);
        add(search);

        choiceEMP = new Choice();
        choiceEMP.setBounds(180,20,150,20);
        add(choiceEMP);

        try{

            conn connection = new conn();
            java.sql.ResultSet resultset = connection.statement.executeQuery("select * from employee");

            while(resultset.next()){
                choiceEMP.add(resultset.getString("empid"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        table = new JTable();
        try{
            conn connection = new conn();
            java.sql.ResultSet resultset = connection.statement.executeQuery("select * from employee");

            table.setModel(DbUtils.resultSetToTableModel(resultset));
            table.setBounds(0,100,900,600);
            add(table);
        }catch(Exception e){
            e.printStackTrace();
        }

        JScrollPane jp = new JScrollPane(table);
        jp.setBounds(0,100,900,600);
        add(jp);

        searchbtn = new JButton("Search");
        searchbtn.setBounds(20,70,80,20);
        searchbtn.addActionListener(this);
        add(searchbtn);

        print = new JButton("Print");
        print.setBounds(120,70,80,20);
        print.addActionListener(this);
        add(print);

        update = new JButton("Update");
        update.setBounds(220,70,80,20);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBounds(320,70,80,20);
        back.addActionListener(this);
        add(back);

        setSize(900,700);
        setLayout(null);
        setLocation(300,100);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == searchbtn){
            String query = "select * from employee where empID = '"+choiceEMP.getSelectedItem()+"'";
            try{
                conn c = new conn();
                ResultSet resultset = c.statement.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(resultset));
            }catch(Exception E){
                E.printStackTrace();
            }
        } else if (e.getSource() == print){
            try{

                table.print();

            }catch(Exception E){
                E.printStackTrace();
            }
        } else if (e.getSource() == update){
            setVisible(false);
            new Update_Employee( choiceEMP.getSelectedItem());


        } else {
            setVisible(false);
            new Main_class();
        }
    }

    public static void main(String[] args) {
        new View_Employee();
    }

}
