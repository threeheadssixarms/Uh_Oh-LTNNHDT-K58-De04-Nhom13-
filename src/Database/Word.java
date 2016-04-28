package Database;

import java.sql.*;
import java.util.*;

public class Word {
	public static String theme;
	private String en;
	private String vn;
	private static Connection c = DBConnection.getConnection();
	private static ResultSet rs;
	private static Statement stmt;
	public String getEn() {
		return en;
	}
	public String getVn() {
		return vn;
	}
	public Word(){
		super();
	}
	public static ArrayList<Word> getWordList()throws SQLException{
		rs = null;
		stmt = c.createStatement();	
		rs = stmt.executeQuery("SELECT * FROM wmt WHERE theme='"+theme+"';");
		ArrayList<Word> wordList = new ArrayList<Word>();
		while (rs.next()){
			Word item = new Word();
			item.en=rs.getString("word");
			item.vn=rs.getString("meaning");
			wordList.add(item);
		}
		return wordList;
	}
	public static int add(String en, String vn, String eg) throws SQLException{
		rs = null;
		stmt = c.createStatement();
		rs = stmt.executeQuery("SELECT * FROM wmt WHERE word='"+en+"';");
		if (rs.isBeforeFirst()==true){
		    	  //System.out.println("Da co tu nay.");
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
		    	  //System.out.println("Khong co tu nay.");
		    	  return 0;
		    }
		stmt.executeUpdate("DELETE FROM wmt WHERE word='"+en+"';");
		return 1;
	}
}
