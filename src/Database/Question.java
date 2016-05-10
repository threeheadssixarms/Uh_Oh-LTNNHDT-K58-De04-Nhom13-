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

	private static ArrayList<Word> getWordList () throws SQLException{
		ArrayList<Word> list = new ArrayList<Word>();
		stmt = c.createStatement();
		if (rs==null)
			rs = stmt.executeQuery("SELECT * FROM(SELECT * FROM wmt WHERE theme LIKE '%"+theme+"%' and learned = 1)ORDER BY RANDOM();");
		while (list.size()<10){
			Word word = new Word();
			if(!rs.next()){
				rs=stmt.executeQuery("SELECT * FROM(SELECT * FROM wmt WHERE theme LIKE '%"+theme+"%' ORDER BY time) ORDER BY RANDOM();");
				rs.next();
			}
			word.setEn(rs.getString("word"));
			word.setVn(rs.getString("meaning"));
			word.setTheme0(rs.getString("theme"));
			list.add(word);
		}
		Collections.shuffle(list);
		return list;
	}
	
	private void makeQuestion(Word word) throws SQLException{
		String answer;
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
		String queryAll="SELECT * FROM wmt WHERE theme LIKE '%"+theme+"%' AND "+answer+"!='"+answerTrue+"' ORDER BY RANDOM();";
		stmt = c.createStatement();
		while (answerList.size()<3){
			ResultSet rs= stmt.executeQuery("SELECT * FROM wmt WHERE theme LIKE '%"+theme+"%' and learned = 1 AND "+answer+"!='"+answerTrue+"' ORDER BY RANDOM();");
			if(rs.isBeforeFirst()==false){
				rs=stmt.executeQuery(queryAll);
			 }
			  answer0=answerTrue;
			  while (rs.next()||answerList.size()<3){
				  int state = 0;
				  for (int i=0; i<answerList.size();i++){
					  if (answerList.get(i).equals(rs.getString(answer))){
						  state=1;
						  break;
					  }
				  }
				  if (state==0)
				  answerList.add(rs.getString(answer));
			  }
		}
		answerList.add(answerTrue);
		Collections.shuffle(answerList);
		answer0 = answerList.get(0);
		answer1 = answerList.get(1);
		answer2 = answerList.get(2);
		answer3 = answerList.get(3);
		return;
	}
	
	public static ArrayList<Question> getQuestionList() throws SQLException{
		ArrayList<Word> wordList = getWordList();
		ArrayList<Question> list = new ArrayList<Question>();
		for (int i=0; i<wordList.size() ;i++){
			Question question = new Question();
			question.makeQuestion(wordList.get(i));
			list.add(question);
			stmt.executeUpdate("UPDATE wmt SET time ='"+now+"' WHERE word ='"+wordList.get(i).getEn()+"' AND theme='"+wordList.get(i).getTheme0()+"';");
			}
		return list;
		}

}
