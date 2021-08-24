import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Second 
{
	public static void main(String args[])
	{
	DbConnect obj=new DbConnect();
	Connection con=obj.connect();
	JFrame f= new JFrame("Library Database"); 
	JButton btn=new JButton("Submit");
    JTextField t1=new JTextField();
    t1.setBounds(50,100, 200,30); 
    btn.setBounds(50,150, 200,30); 
    btn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			DefaultTableModel model = new DefaultTableModel(new String[]{"Id", "Firstname", "Last Name"}, 0);
			String query=t1.getText();
			System.out.println(query);
			try {
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(query);
				ResultSetMetaData rsMetaData = rs.getMetaData();
				Vector<String> meta=new Vector<String>();
		     	model.addRow(new Object[]{rsMetaData.getColumnName(1),rsMetaData.getColumnName(2),rsMetaData.getColumnName(3)});
				
				while(rs.next())
				{
					int id = rs.getInt(1);
				    String fname = rs.getString(2);
				    String lastname = rs.getString(3);
				    model.addRow(new Object[]{id, fname, lastname});
				}
				 JTable jt=new JTable(model); 
				 jt.setBounds(50, 200, 220, 100);
				 f.add(jt);
				 f.revalidate();
				 f.repaint();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	});
   f.add(t1);
   f.add(btn);
   f.setSize(400,400);  
   f.setLayout(null);  
   f.setVisible(true);
	}
}
