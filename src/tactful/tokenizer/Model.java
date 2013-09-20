package tactful.tokenizer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.FeaturesModel;
import models.LowerWordsModel;
import models.NonAbbrsModel;

public class Model {
	
	private double p0;
	
	public Model() throws IOException{
		this.p0 = Math.pow(FeaturesModel.getProbability("<prior>") , 4);
	}

	public static int getLower_words(String string) throws IOException {
		int result = LowerWordsModel.getProbability(string);
		return result;
	}

	public static int getNon_abbrs(String string) throws IOException {
		int result = NonAbbrsModel.getProbability(string);
		return result;
	}
	
	public static double getFeats(String string) throws IOException{
		double feat =  FeaturesModel.getProbability(string);
		return feat;
	}

	public String[] tokenize_text(String text) throws IOException {
		System.out.println("Given text is = "+text);
		Doc data = new Doc(text);
		featurize(data);
		classify(data);
		System.out.print("Response = ");
		return data.segment();
	}
	
	//Created Main for testing will remove from final jar
	 public static void main(String args[]) throws IOException{
		Model model=new Model();
		//String[] re = model.tokenize_text("this is u.s.a. please come home! where are you? This is meeting prep. for you.");
		//String[] re = model.tokenize_text("this is u.s.a. for you.");
		//String[] re = model.tokenize_text("Rs. 109 Limited Time Domain Sale! Book Your Domain And Get Online Today.");
		//String[] re = model.tokenize_text("Rs. 109  Limited Time \nDomain Sale! Book Your Domain    And \nGet Online Today.");
		//String[] re = model.tokenize_text("this is sumit. \n \n\n\n\n\n\n\n\n where are you.\n");
		//String[] re = model.tokenize_text("this is sumit/..........  ..ahow are ......... adhsf");
		Scanner sc=new Scanner(System.in);
		System.out.println("Please enter the Line to pass");
		String input = sc.nextLine();
		String[] re = model.tokenize_text(input);
		for(String print:re){
			System.out.println(print);
		}
	} 
	
	//this method will set score pred(probability of having sentance) from features model.
	private void classify(Doc data) throws IOException {
		
		double probs  ;
		int i=1;
		for(Frag frag : data.frags){
			probs = p0;
			for(String feat:frag.features){		

				probs *= getFeats(feat); 
			}
			frag.pred = (float) (probs / (probs + 1 )) ;	
		}
	}

	private void featurize(Doc data) throws IOException {
		//frag = nil
		ArrayList<Frag> frags = (ArrayList<Frag>) data.frags;
	
		for(Frag frag:frags){
			get_features(frag, this);
		}		
	}
	//# Finds the features in a text fragment of the form:
	// # ... w1. (sb?) w2 ...
	// # Features listed in rough order of importance:
	// # * w1: a word that includes a period.
	// # * w2: the next word, if it exists.
	// # * w1length: the number of alphabetic characters in w1.
	// # * both: w1 and w2 taken together.
	// # * w1abbr: logarithmic count of w1 occuring without a period.
	// # * w2lower: logarithmic count of w2 occuring lowercased.
	public void get_features(Frag frag, Model model) throws IOException {
		
		String w1 = frag.cleaned[frag.cleaned.length-1];
		String w2 = frag.getNext();
		
		List<String> features = new ArrayList<String>();
		if (w1 != null){
			features.add("w1_"+w1);
		}
		else{
			features.add("w1_");
		}
		
		if ( w2 != null){
		 features.add("w2_"+w2);
		 features.add("both_"+w1+"_"+w2);
		}
		else{
		 features.add("w2_");
     	 features.add("both_"+w1+"_");
		}
			
		frag.features.addAll(features);

		if( w2!=null && !w2.isEmpty()){
			if((w1.substring(0,w1.length() - 1).matches("[a-zA-Z]+"))){
				frag.features.add("w1length_"+Math.min(10,w1.length()));
				frag.features.add("w1abbr_"+model.getNon_abbrs( w1.substring( 0 ,w1.length()-1) ) );				
			}	
			
			if(! (w2.substring(0, w2.length()-1).matches(".*\\d+"))){
				frag.features.add("w2cap_"+!Character.isUpperCase(w2.charAt(0)));
				frag.features.add("w2lower_"+model.getLower_words(w2.toLowerCase()));				
			}
		}		
	}
}
