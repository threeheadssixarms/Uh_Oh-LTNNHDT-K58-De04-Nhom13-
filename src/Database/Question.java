package Database;
import java.sql.*;
import java.util.*;

public class Question {
	private static Connection c = DBConnection.getConnection();
	private static ResultSet rs = null;
	private static Statement stmt;
	private String question;
	private String answerTrue;
	private String answer0;
	private String answer1;
	private String answer2;
	private String answer3;
	private String en;
	private String vn;
	private boolean completeType;
	
	public String getQuestion() {
		return question;
	}

	public String getAnswerTrue() {
		return answerTrue;
	}

	public String getAnswer0() {
		return answer0;
	}

	public String getAnswer1() {
		return answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public String getEn() {
		return en;
	}

	public String getVn() {
		return vn;
	}
	

	public boolean isCompleteType() {
		return completeType;
	}

	private static ArrayList<Word> getWordList () throws SQLException{
		ArrayList<Word> list = new ArrayList<Word>();
		stmt = c.createStatement();
		rs = stmt.executeQuery("SELECT * FROM wmt WHERE learned = 1 ORDER BY RANDOM();");
		for (int i=0;i<20;i++){
			Word word = new Word();
			if(!rs.next()){
				rs=stmt.executeQuery("SELECT * FROM wmt WHERE learned = 0 ORDER BY RANDOM();");
				rs.next();
			}
			word.setEn(rs.getString("word"));
			word.setVn(rs.getString("meaning"));
			list.add(word);
		}
		return list;
	}
	
	
	private void makeQuestion(Word word) throws SQLException{
		String answer;
		if (Math.random()<0.5){
			question = word.getEn();
			answerTrue=word.getVn();
			answer = "meaning";
		} else {
			question = word.getVn();
			answerTrue=word.getEn();
			answer = "word";
		}
		if (Math.random()<0.5){
			completeType=true;
			return;
		}
		completeType=false;
		stmt = c.createStatement();
		rs= stmt.executeQuery("SELECT * FROM wmt WHERE learned = 1 ORDER BY RANDOM();");
		if(rs.isBeforeFirst()==false){
			rs=stmt.executeQuery("SELECT * FROM wmt WHERE learned = 0 ORDER BY RANDOM();");
			 }
			  answer0=answerTrue;
			  if(rs.next())
			   if(rs.next()){	
				   answer1=rs.getString(answer);
				   if(rs.next()){
					   answer2=rs.getString(answer);
				   	   if(rs.next())
			             answer3=rs.getString(answer);
				   	   else {
				   		   rs=stmt.executeQuery("SELECT * FROM wmt WHERE learned = 0 ORDER BY RANDOM();");
						   answer3=rs.getString(answer);
				   	   }
				   }else{
					   rs.close();
					   rs=stmt.executeQuery("SELECT * FROM wmt WHERE learned = 0 ORDER BY RANDOM();");
					   rs.next();
					   answer2=rs.getString(answer);
					   rs.next();
					   answer3=rs.getString(answer);
				   }
			   }else{
				   rs.close();
				   rs=stmt.executeQuery("SELECT * FROM wmt WHERE learned = 0 ORDER BY RANDOM();");
				   rs.next();
				   answer1=rs.getString(answer);
				   rs.next();
				   answer2=rs.getString(answer);
				   rs.next();
				   answer3=rs.getString(answer);
			   }
		   unscramble();	  
		   return;
	}
	
	public static ArrayList<Question> getQuestionList() throws SQLException{
		ArrayList<Word> wordList = getWordList();
		ArrayList<Question> list = new ArrayList<Question>();
		for (int i=0; i<wordList.size() ;i++){
			Question question = new Question();
			question.makeQuestion(wordList.get(i));
			list.add(question);
			}
		return list;
		}
		
	
	private void unscramble(){
		ArrayList<String> list = new ArrayList<String>();
		list.add(answer0);
		list.add(answer1);
		list.add(answer2);
		list.add(answer3);
		Collections.shuffle(list);
		answer0=list.get(0);
		answer1=list.get(1);
		answer2=list.get(2);
		answer3=list.get(3);
	}
	
}
