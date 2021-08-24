import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Four 
{
	public static void main(String args[]) throws SQLException, ClassNotFoundException
	{
		String query=null;
		Connection con;
		Statement st;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");    
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sathish","sathish"); 
			st = con.createStatement();
			ResultSet result=st.executeQuery(" select count(*) from tab where tname='ADDRESS'");
			result.next();
			query = "create table address (id number(12) primary key,Firstname varchar2(20), lastname varchar2(20), phone varchar2(14), email varchar2(50))";
			if(result.getInt(1)==0)
			{
			st.execute(query);
			System.out.println("Table Created successfully");
			}
			
		JFrame f= new JFrame("Address Book");  
	    JLabel l1,l2,l3,l4,l5,l6; 
	    JTextField t1,t2,t3,t4,t5,t6;
	    JButton btn1,btn2,btn3;
	    l1=new JLabel("Address Id: ");  
	    l1.setBounds(50,50, 100,30); 
	    
	    t1=new JTextField();  
	    t1.setBounds(150,50, 100,30); 
	    
	    l2=new JLabel("First Name: ");  
	    l2.setBounds(50,100, 100,30);
	    
	    t2=new JTextField();  
	    t2.setBounds(150,100, 100,30); 
	    
	    l3=new JLabel("Last Name: ");  
	    l3.setBounds(50,150, 100,30);
	    
	    t3=new JTextField();  
	    t3.setBounds(150,150, 100,30); 
	    
	    l4=new JLabel("Phone no: ");  
	    l4.setBounds(50,200, 100,30);
	    
	    t4=new JTextField();  
	    t4.setBounds(150,200, 100,30); 
	    
	    l5=new JLabel("Email Id: ");  
	    l5.setBounds(50,250, 100,30);
	    
	    t5=new JTextField();  
	    t5.setBounds(150,250, 100,30); 
	    
	    btn1=new JButton("Insert");
	    btn1.setBounds(50,300, 70,30);
	    PreparedStatement pst=con.prepareStatement("insert into address values(?,?,?,?,?)");
	    Random rd=new Random();
	    int number=rd.nextInt(1000);
	    System.out.println(number);
	    t1.setText(number+"");
	    btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try {
					int addressId=Integer.parseInt(t1.getText());
					String firstname=t2.getText();
					String lastname=t3.getText();
					String phonenumber=t4.getText();
					String emailid=t5.getText();
					pst.setInt(1,addressId);
					pst.setString(2,firstname);
					pst.setString(3,lastname);
					pst.setString(4,phonenumber);
					pst.setString(5,emailid);
					pst.executeUpdate();
					System.out.println("Inserted Successfully");
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}	
			}
		});
	    
	    btn2=new JButton("delete");
	    btn2.setBounds(130,300, 70,30); 
	    btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int id=Integer.parseInt(t1.getText());
				String sql="delete from address where id="+id;
				try {
					st.execute(sql);
					System.out.println("deleted successfully");
					t2.setText("");t3.setText("");t4.setText("");t5.setText("");
					f.revalidate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	    
	    
	    l6=new JLabel("Search Name ");  
	    l6.setBounds(50,400, 100,20);
	    
	    t6=new JTextField();  
	    t6.setBounds(150,400, 100,20); 
	    
	    btn3=new JButton("search");
	    btn3.setBounds(250,400, 70,20); 
	    btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String firstname=t6.getText();
				String query="select * from address where firstname='"+firstname+"'";
				try {
					ResultSet rs=st.executeQuery(query);
					rs.next();
					t1.setText(rs.getInt(1)+"");
					t2.setText(rs.getString(2));
					t3.setText(rs.getString(3));
					t4.setText(rs.getString(4));
					t5.setText(rs.getString(5));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	    
	    f.add(l1); f.add(l2); 
	    f.add(l3); f.add(l4);
	    f.add(l5); 
	    f.add(t1); f.add(t2); 
	    f.add(t3); f.add(t4);
	    f.add(t5); 
	    f.add(btn1);
	    f.add(btn2);
	    f.add(btn3);
	    f.add(t6);
	    f.add(l6);
	   
	    f.setSize(500,500);  
	    f.setLayout(null);  
	    f.setVisible(true);  
		}
	    finally
	    {
	    	
	    }
	}

}
