import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnect 
{
	public static Connection connect()
	{
		Connection con;
		try{  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sathish","sathish"); 
			return con;
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
		

}
}
