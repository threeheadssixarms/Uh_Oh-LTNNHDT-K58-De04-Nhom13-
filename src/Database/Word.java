package Database;

import java.sql.*;
import java.util.*;

public class Word {
	public static String theme;
	public static String key;
	private String en;
	private String vn;
	private String eg;
	private String theme0;
	private static long now = Calendar.getInstance().getTimeInMillis();
	private static Connection c = DBConnection.getConnection();
	private static ResultSet rs;
	private static Statement stmt;
	public String getVn() {
		return vn;
	}
	public String getEn() {
		return en;
	}
	public String getEg() {
		return eg;
	}
	public String getTheme0() {
		return theme0;
	}
	public void setTheme0(String theme0) {
		this.theme0 = theme0;
	}
	public Word(){
		super();
	}
	
	public void setEn(String en) {
		this.en = en;
	}
	public void setVn(String vn) {
		this.vn = vn;
	}
	public static ArrayList<Word> getWordList()throws SQLException{
		stmt = c.createStatement();	
		rs = stmt.executeQuery("SELECT * FROM wmt WHERE theme='"+theme+"';");
		ArrayList<Word> wordList = new ArrayList<Word>();
		while (rs.next()){
			Word item = new Word();
			item.en=rs.getString("word");
			item.vn=rs.getString("meaning");
			item.eg=rs.getString("example");
			wordList.add(item);
		}
		return wordList;
	}
	public static int add(String en, String vn, String eg) throws SQLException{
		rs = null;
		stmt = c.createStatement();
		rs = stmt.executeQuery("SELECT * FROM wmt WHERE word='"+en+"';");
		if (rs.isBeforeFirst()==true){
		    	  return 0;
		   }
		stmt.executeUpdate("INSERT INTO wmt VALUES ('"+en+"','"+vn+"','"+theme+"',0,'"+eg+"');");
		return 1;
	}
	
	public static int delete(String en) throws SQLException{
		rs = null;
		stmt = c.createStatement();
		rs = stmt.executeQuery("SELECT * FROM wmt WHERE word='"+en+"' AND theme = '"+theme+"';");
		if (rs.isBeforeFirst()==false){
		    	  return 0;
		    }
		stmt.executeUpdate("DELETE FROM wmt WHERE word='"+en+"';");
		return 1;
	}
	private void checkLearned() throws SQLException{
		stmt = c.createStatement();
		stmt.executeUpdate("UPDATE wmt SET learned = 1 WHERE theme='"+theme+"' AND word='"+en+"';");
		stmt.executeUpdate("UPDATE wmt SET time ='"+now+"' WHERE word ='"+en+"' AND theme='"+theme+"';");
	}
	public void learn() throws SQLException{
		if (rs!=null){				 //luot goi thu 2 tro di trong session
			if (rs.next()){         //neu chua di het rs hien tai
				en = rs.getString("word");
				vn = rs.getString("meaning");
				eg=(rs.getString("example"));
				checkLearned();
				return;
			} 
		}
		stmt = c.createStatement();
		rs = stmt.executeQuery("SELECT * FROM wmt WHERE theme LIKE '%"+theme+"%' AND learned = 0 ORDER BY RANDOM();");
		if (rs.isBeforeFirst()==false){	//neu da hoc het theme
			rs = stmt.executeQuery("SELECT * FROM wmt WHERE theme LIKE '%"+theme+"%' ORDER BY time;");
		}
		rs.next();
		en = rs.getString("word");
		vn = rs.getString("meaning");
		eg=rs.getString("example");
		checkLearned();
	}
	
	public static ArrayList<Word> searchWordList()throws SQLException{
		stmt = c.createStatement();	
		ArrayList<Word> wordList = new ArrayList<Word>();
		rs = stmt.executeQuery("SELECT * FROM wmt WHERE word='%"+key+"%' OR meaning LIKE '%"+key+"%';");
		while (rs.next()){
			Word item = new Word();
			item.en=rs.getString("word");
			item.vn=rs.getString("meaning");
			item.eg=rs.getString("example");
			wordList.add(item);
		}
		rs=null;
		return wordList;
	}
}
