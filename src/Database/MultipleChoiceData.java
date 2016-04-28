package Database;
import java.sql.*;
import java.util.*;
public class MultipleChoiceData {
	private String question;
	private String answerTrue;
	private String answer0;
	private String answer1;
	private String answer2;
	private String answer3;
	private static Connection c = DBConnection.getConnection();
	private static ResultSet rs = null;
	private static Statement stmt;
	private MultipleChoiceData() throws SQLException {
		super();
		makeQuestion();
	}
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
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
	
	private int makeQuestion() throws SQLException{
		String question;
		String answer;
		if (Math.random()<0.5){
			question = "word";
			answer = "meaning";
		} else {
			question = "meaning";
			answer = "word";
		}
		stmt = c.createStatement();
		rs= stmt.executeQuery("SELECT * FROM wmt WHERE learned = 1 ORDER BY RANDOM();");
		if(rs.isBeforeFirst()==false){
				  //System.out.println("Ban chua hoc tu nao.\n");
				  return 0;
			 }
			  answerTrue=rs.getString(answer);
			  answer0=answerTrue;
			  this.question=rs.getString(question);
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
		   return 1;
	}
	public void print(){
		System.out.println(getQuestion());
		//System.out.println(getAnswerTrue());
		System.out.println(getAnswer0());
		System.out.println(getAnswer1());
		System.out.println(getAnswer2());
		System.out.println(getAnswer3());
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
	public static ArrayList<MultipleChoiceData> getQuestionList() throws SQLException{
		stmt = c.createStatement();
		
		rs = stmt.executeQuery("SELECT COUNT(*) FROM wmt where learned=1;");
		int count = rs.getInt(1);
		
		ArrayList<MultipleChoiceData> list = new ArrayList<MultipleChoiceData>();
		while (list.size()<=10 && list.size() < 2*count){
			MultipleChoiceData item = new MultipleChoiceData();
			int state = 0;
			for (int i=0;i<list.size();i++){
				if (list.get(i).getQuestion().equals(item.getQuestion())){
					state = 1;
					break;
				}
			}
			if (state==0)
				list.add(item);
		}
		return list;
	}


}
