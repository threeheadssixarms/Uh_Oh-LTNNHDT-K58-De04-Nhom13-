package Database;
import java.sql.*;
import java.util.*;

public class Question {
	private static Connection c = DBConnection.getConnection();
	private static ResultSet rs;
	private static Statement stmt;
	private String question;
	private String answerTrue;
	private String answer0;
	private String answer1;
	private String answer2;
	private String answer3;
	private static long now = Calendar.getInstance().getTimeInMillis();
	public static String theme;
	
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
	
	private static int getWordCount() throws SQLException{
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM wmt where theme = '"+theme+"';");
		return rs.getInt(1);
	}
	
	private static ArrayList<Word> getWordList () throws SQLException{
		ArrayList<Word> list = new ArrayList<Word>();
		stmt = c.createStatement();
		rs = stmt.executeQuery("SELECT * FROM wmt WHERE theme = '"+theme+"' ORDER BY learned desc, time asc LIMIT 10;");
		int count = getWordCount();
		while (list.size()<10 && list.size()<count){
			rs.next();
			Word word = new Word(rs.getString("word"),rs.getString("meaning"), "");
			/*word.setEn(rs.getString("word"));
			word.setVn(rs.getString("meaning"));*/
			list.add(word);
		}
		Collections.shuffle(list);
		return list;
	}
	
	private void makeQuestion(Word word) throws SQLException{
		String answer;
		int count=getWordCount();
		stmt = c.createStatement();
		if (Math.random()<0.5){
			this.question = word.getEn();
			answerTrue=word.getVn();
			answer = "meaning";
		} else {
			this.question = word.getVn();
			answerTrue=word.getEn();
			answer = "word";
		}
		ArrayList<String> answerList = new ArrayList<String>();
		answerList.add(answerTrue);
		if (count<4){
			ResultSet randomAnswerSet=stmt.executeQuery("SELECT * FROM wmt WHERE theme != '"+theme+"' ORDER BY learned desc, RANDOM();");
			while (answerList.size()<(4-count)+1){
				randomAnswerSet.next();
				if (answerTrue.equals(randomAnswerSet.getString(answer))) continue;
				answerList.add(randomAnswerSet.getString(answer));
			}
		}
		ResultSet rs= stmt.executeQuery("SELECT * FROM wmt WHERE theme = '"+theme+"' ORDER BY learned desc, RANDOM();");
		while (answerList.size()<4){
			rs.next();
			if (answerTrue.equals(rs.getString(answer))) continue;
			answerList.add(rs.getString(answer));
		}
		Collections.shuffle(answerList);
		answer0 = answerList.get(0);
		answer1 = answerList.get(1);
		answer2 = answerList.get(2);
		answer3 = answerList.get(3);
		return;
	}
	
	public static ArrayList<Question> getQuestionList() throws SQLException{
		ArrayList<Word> wordList = getWordList();
		int count=getWordCount();
		ArrayList<Question> list = new ArrayList<Question>();
		for (int i=0; i<wordList.size() ;i++){
			Question question = new Question();
			question.makeQuestion(wordList.get(i));
			list.add(question);
			stmt.executeUpdate("UPDATE wmt SET time ='"+now+"' WHERE word ='"+wordList.get(i).getEn()+"' AND theme = '"+theme+"';");
			}
		for(int i=0;list.size()<2*count && list.size()<10;i++){
			Question question = new Question();
			do{
			question.makeQuestion(wordList.get(i));
			}while (list.get(i).getQuestion().equals(question.question));
			list.add(question);
			stmt.executeUpdate("UPDATE wmt SET time ='"+now+"' WHERE word ='"+wordList.get(i).getEn()+"' AND theme = '"+theme+"';");	
		}
		return list;
		}

}
