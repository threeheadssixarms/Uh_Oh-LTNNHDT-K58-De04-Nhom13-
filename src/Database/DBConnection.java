package Database;

import java.sql.*;

public class DBConnection {
	static Connection c = null;
	
    public static Connection getConnection()
    {	
    	if (c != null) return c;
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:word.db");
        }
        catch(Exception e)
        {
        	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    System.exit(0);
        }
        System.out.println("Opened database successfully");
        return c;        
    }
	 
}
