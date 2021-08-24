import java.sql.*;
import java.util.Scanner;

public class First {
	public static void main(String args[]) throws SQLException {
		Connection con=null;
		String query = null;
		try {
			DbConnect db = new DbConnect();
			con=db.connect();
			Statement st = con.createStatement();
			ResultSet result=st.executeQuery(" select count(*) from tab where tname='AUTHOR'");
			result.next();
			query = "create table author (Authorid number(2) primary key,Firstname varchar2(20), lastname varchar2(20))";
			if(result.getInt(1)==0)
			{
			st.execute(query);
			System.out.println("Table Created successfully");
			}
			else
			{
				System.out.println("Table already exists, Do you want to delete and create new ? \n\t1. yes \n\t 2. No\n");
				Scanner s=new Scanner(System.in);
				int choice=s.nextInt();
				if(choice==1)
				{
					st.execute("drop table AUTHOR");
					st.execute(query);
					System.out.println("Old table deleted and new one created");
				}
			}
	
			// inserting value into author db
			st.execute("insert into author values(1,'paul','deital')");
			st.execute("insert into author values(2,'Havey','deital')");
			st.execute("insert into author values(3,'Abbey','deital')");
			st.execute("insert into author values(4,'dann','deital')");
			st.execute("insert into author values(5,'paul','deital')");
			System.out.println("Five Records inserted successfully");
		} catch (Exception e) {
			System.out.println(e);
		}
		finally
		{
			con.close();
		}

	}
}