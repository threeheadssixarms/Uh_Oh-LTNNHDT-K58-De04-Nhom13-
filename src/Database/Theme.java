package Database;
import java.sql.*;
import java.util.*;
public class Theme {
	private String theme;
	private String des;
	private static Connection c = DBConnection.getConnection();
	private static ResultSet rs;
	private static Statement stmt;
	public String getTheme() {
		return theme;
	}
	public String getDes() {
		return des;
	}
	public Theme(){
		super();
	}
	
	public static ArrayList<Theme> getThemeList()throws SQLException{
		stmt = c.createStatement();	
		ArrayList<Theme> themeList = new ArrayList<Theme>();
		rs = stmt.executeQuery("SELECT * FROM theme;");
		while (rs.next()){
			Theme item = new Theme();
			item.theme=rs.getString("theme");
			item.des=rs.getString("des");
			themeList.add(item);
		}
		return themeList;
	}
	
	public static int add(String theme, String des) throws SQLException{
		stmt = c.createStatement();
		rs=stmt.executeQuery("SELECT * FROM theme WHERE theme='"+theme+"';");
		if (rs.isBeforeFirst()==true){
		  	  //System.out.println("Da co chu de nay.");
		  	  return 0;
		    }
		stmt.executeUpdate("INSERT INTO theme VALUES ('"+theme+"','"+des+"');");
		return 1;
	}
	
	public static int delete(String theme) throws SQLException{
		stmt = c.createStatement();
		rs=stmt.executeQuery("SELECT * FROM theme WHERE theme='"+theme+"';");
		if (rs.isBeforeFirst()==false){
		  	  //System.out.println("Khong co chu de nay.");
		   	  return 0;
		   }
		String delete="DELETE FROM theme WHERE theme='"+theme+"';";
		stmt.executeUpdate(delete);
		return 1;
	}
}
