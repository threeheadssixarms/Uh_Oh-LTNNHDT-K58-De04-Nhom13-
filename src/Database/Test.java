package Database;

import java.sql.*;
import java.util.*;


public class Test {
	public static void main(String[] args) throws SQLException{
		
		//Theme.add("vui", "ghe");
		//Theme.delete("vui");
		
		
		
		//Word.theme="test";
		//Word.add("hihihi", "nu cuoi","cuoi hihihi");
		//Word.delete("hihihi");
		/*
		Word.theme="Hoa va� c�y";
		ArrayList<Word> list=Word.getWordList();
		for (int i=0; i<list.size(); i++){
			Word stick=list.get(i);
			System.out.print(stick.getEn()+"\t"+stick.getVn());
			System.out.println(); 
		}
		/*
		
		ArrayList<Theme> list = Theme.getThemeList();
		for (int i=0; i<list.size(); i++){
			Theme stick=list.get(i);
			System.out.print(stick.getTheme()+"\t"+stick.getDes());
			System.out.println(); 
		}
		
		*/
		/*
		ArrayList<MultipleChoiceData> list = MultipleChoiceData.getQuestionList();
		for (int i=0; i<list.size();i++){
			list.get(i).print();
			System.out.println(); 
		}
		
		
		/*
		
		LearnData.theme="Ch��ng tri�nh truy��n hi�nh";
		LearnData word = new LearnData();
		for (int i=0; i<25; i++){
			
			if (word.review()==0){
				return;
			}
			word.print();
			System.out.println(); 
			
		}
		*/
		
		LearnData.theme="ch��ng tri�nh truy��n hi�nh";
		LearnData word = new LearnData();
		for (int i=0; i<25; i++){
			word.learn();
			word.print();
			System.out.println(); 
			
		}
		
		/*
		for (int i=0; i<10; i++){
		MultipleChoiceData item= new MultipleChoiceData();
		item.print();
		System.out.println(); 
		}
		*/
	}
}