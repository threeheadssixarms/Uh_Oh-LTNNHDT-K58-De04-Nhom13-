package Database;
import java.sql.*;
public class LearnData {
	private String en;
	private String vn;
	private String eg;
	public static String theme;
	private static ResultSet rs;
	static Connection c = DBConnection.getConnection();
	static Statement stmt;
	public String getEn() {
		return en;
	}
	
	public String getVn() {
		return vn;
	}
	
	public String getEg() {
		return eg;
	}
	
	public LearnData() {
		super();
	}
	
	public void learn() throws SQLException{
		if (rs!=null){				 //luot goi thu 2 tro di trong session
			if (rs.next()){         //neu chua di het rs hien tai
				en = rs.getString("word");
				vn = rs.getString("meaning");
				eg = rs.getString("example");
				checkLearned();
				return;
			} 
		}
		stmt = c.createStatement();
		String query="SELECT * FROM wmt WHERE theme='"+theme+"' AND learned = 0;";
		rs = stmt.executeQuery(query);
		if (rs.isBeforeFirst()==false){	//neu da hoc het theme
			query="SELECT * FROM wmt WHERE theme='"+theme+"';";
			rs = stmt.executeQuery(query);
		}
		rs.next();
		en = rs.getString("word");
		vn = rs.getString("meaning");
		eg = rs.getString("example");
		checkLearned();
	}
	
	public void print(){
		System.out.println(getEn());
		System.out.println(getVn());
		System.out.println(getEg());
	}
	
	public int review() throws SQLException{
		if (rs!=null){				 //luot goi thu 2 tro di trong session
			if (rs.next()){         //neu chua di het rs hien tai
				en = rs.getString("word");
				vn = rs.getString("meaning");
				eg = rs.getString("example");
				
				return 1;
			} 
		}
		stmt = c.createStatement();
		String query="SELECT * FROM wmt WHERE theme='"+theme+"' AND learned = 1 ORDER BY RANDOM();";
		rs = stmt.executeQuery(query);
		if (rs.isBeforeFirst()==false){	//neu da hoc het theme
			return 0;
		}
		rs.next();
		en = rs.getString("word");
		vn = rs.getString("meaning");
		eg = rs.getString("example");
		return 1;
	}
	
	public int checkLearned() throws SQLException{
		stmt = c.createStatement();
		String update="UPDATE wmt SET learned = 1 WHERE theme='"+theme+"' AND word='"+en+"';";
		stmt.executeUpdate(update);
		return 1;
	}
}
