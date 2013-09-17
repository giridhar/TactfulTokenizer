package tactful.tokenizer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import models.FeaturesModel;
import models.LowerWordsModel;
import models.NonAbbrsModel;

public class Model {
	
	private double p0;
	/*private HashMap<String, Float> feats;
	private HashMap<String, Float> lower_words;
	private HashMap<String, Float> non_abbrs;
	private double p0;*/
	
	public Model() throws IOException{
		FeaturesModel object = new FeaturesModel();
		System.out.println("p0 value"+object.getProbability("<prior>"));
		this.p0 = Math.pow(object.getProbability("<prior>") , 4);
	}

   /*
	// Initialize the model. feats, lower_words, and non_abbrs
	// indicate the locations of the respective Marshal dumps.
	public Model(HashMap<String, Float> feats,
		HashMap<String, Float> lower_words, HashMap<String, Float> non_abbrs) {
		this.feats = feats;
		this.lower_words = lower_words;
		this.non_abbrs = non_abbrs;
		this.p0 = Math.pow(feats.get("<prior>"), 4);
	}

	public HashMap<String, Float> getFeats() {
		return feats;
	}  */

	public static int getLower_words(String string) throws IOException {
		LowerWordsModel object = new LowerWordsModel();
		int result = object.getProbability(string);
		return result;
	}

	//Getting Score Directly from NonAbbrsModel for given word..
	public static int getNon_abbrs(String string) throws IOException {
		NonAbbrsModel object = new NonAbbrsModel();
		int result = object.getProbability(string);
		return result;
	}
	
	public static double getFeats(String string) throws IOException{
		
		FeaturesModel object=new FeaturesModel();
		double feat =  object.getProbability(string);
		//System.out.println("last return "+feat+"for word "+string);
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
		//String[] re = model.tokenize_text("this is u.s.a. please come home. where are you. This is meeting prep. for you.");
		//String[] re = model.tokenize_text("Rs. 109 Limited Time Domain Sale! Book Your Domain And Get Online Today.");
		//String[] re = model.tokenize_text("For more details or demo, call @ 0161 - 4682334, 45 (10am - 5pm, Mon-Fri)");
		String[] re = model.tokenize_text("this is meeting prep. for you");
		for(String print:re){
			System.out.println(print);
		}
		//FeaturesModel obj = new FeaturesModel();
		//System.out.println(obj.getProbability("w2_where"));
	} 
	
	//this method will score pred(inside Frag) by taking values from features.
	private void classify(Doc data) throws IOException {

		double probs  ;
		int i=1;
		for(Frag frag : data.frags){
			probs = p0;
			for(String feat:frag.features){		
				
				probs *= getFeats(feat); 
				//System.out.println("passinsg "+feat+" coming "+getFeats(feat)+" times "+i++);
			}
			//i = i+1;
			frag.pred = (float) (probs / (probs + 1 )) ;	

		/*	if ( i%2 == 1)
				 frag.pred = (float) 0.0;
			else
				 frag.pred = (float) 0.999403233859149; */
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
	// # * w2lower: logarithmiccount of w2 occuring lowercased.
	public void get_features(Frag frag, Model model) throws IOException {
		
		String w1 = frag.cleaned[frag.cleaned.length-1];
		String w2 = frag.getNext();

	//	String[] features = {"w1_"+w1,"w2_"+w2, "both_"+w1+""+w2};
	//	frag.setFeatures(features);
		
		List<String> features = new ArrayList<String>();
		features.add("w1_"+w1);
		if ( w2 != null){
		 features.add("w2_"+w2);
		 features.add("both_"+w1+"_"+w2);
		}
		else{
		 features.add("w2_");
     	 features.add("both_"+w1+"_");
		}
		//frag.setFeatures(features);	
		frag.features.addAll(features);

		if( w2!=null && !w2.isEmpty()){
			if(! (w1.substring(0,w1.length() - 1).matches(".*\\d+"))){
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
