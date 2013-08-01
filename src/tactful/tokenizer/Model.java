package tactful.tokenizer;

import java.util.ArrayList;
import java.util.HashMap;

public class Model {
	private HashMap<String, Float> feats;
	private HashMap<String, Float> lower_words;
	private HashMap<String, Float> non_abbrs;
	private double p0;

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
	}

	public HashMap<String, Float> getLower_words() {
		return lower_words;
	}

	public HashMap<String, Float> getNon_abbrs() {
		return non_abbrs;
	}

	public String[] tokenize_text(String text) {
		Doc data = new Doc(text);
		featurize(data);
		classify(data);
		return data.segment();
	}

	private void classify(Doc data) {
		// TODO Auto-generated method stub

	}

	private void featurize(Doc data) {
		//frag = nil
		ArrayList<Frag> frags = data.frags;
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
	private void get_features(Frag frag, Model model) {
//w1 = (frag.cleaned.last or '')
//        w2 = (frag.next or '')
//
//        frag.features = ["w1_#{w1}", "w2_#{w2}", "both_#{w1}_#{w2}"]
//
//        if not w2.empty?
//            if w1.chop.is_alphabetic? 
//                frag.features.push "w1length_#{[10, w1.length].min}", "w1abbr_#{model.non_abbrs[w1.chop]}"
//            end
//
//            if w2.chop.is_alphabetic?
//                frag.features.push "w2cap_#{w2[0].chr.is_upper_case?}", "w2lower_#{model.lower_words[w2.downcase]}"
//            end
//        end
//		w1 = (frag.getCleaned() or '')
		
		
	}

}
