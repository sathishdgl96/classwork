 import javax.sql.rowset.JdbcRowSet;  
import javax.sql.rowset.RowSetProvider;  
  
public class Three {  
        public static void main(String[] args) throws Exception {  
                 Class.forName("oracle.jdbc.driver.OracleDriver");  
        
        JdbcRowSet rowSet = RowSetProvider.newFactory().createJdbcRowSet();  
        rowSet.setUrl("jdbc:oracle:thin:@localhost:1521:xe");  
        rowSet.setUsername("sathish");  
        rowSet.setPassword("sathish");            
        rowSet.setCommand("select * from author");  
        rowSet.execute();  
                   
    while (rowSet.next()) {  
                        // Generating cursor Moved event  
                        System.out.print(rowSet.getString(1)+"\t");  
                        System.out.print(rowSet.getString(2)+"\t");  
                        System.out.println(rowSet.getString(3)+"\t"); 
                      
                }  
                 
        }  
}  