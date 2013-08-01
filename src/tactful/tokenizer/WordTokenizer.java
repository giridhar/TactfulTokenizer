package tactful.tokenizer;

public class WordTokenizer {
	private String [][] tokenize_regexps = {
			{"''|``", "\"" },
			
//	        # Separate punctuation (except for periods) from words.
	        {"(^|\\s)(')", "\\1\\2"},
	        {"(?=[\\(\"`{\\[:;&#*@])(.)", "\1 "},
			
	        {"(?=[?!\\)\";}\\]*:@'])|(?=[\\)}\\]])(.)|(.)(?=[({\\[])|((^|\\s)-)(?=[^-])", "\1 "},

//	        # Treat double-hyphen as a single token.
	        {"([^-])(--+)([^-])", "\1 \2 \3"},
	        {"(\\s|^)(,)(?=(\\S))", "\1\2 "},
			
//	        # Only separate a comma if a space follows.
	        {"(.)(,)(\\s|$)", "\1 \2\3"},

//	        # Combine dots separated by whitespace to be a single token.
	        {"\\.\\s\\.\\s\\.", "..."},

//	        # Separate "No.6"
	        {"([\\W]\\.)(\\d+)", "\1 \2"},

//	        # Separate words from ellipses
	        {"([^\\.]|^)(\\.{2,})(.?)", "\1 \2 \3"},
	        {"(^|\\s)(\\.{2,})([^\\.\\s])", "\1\2 \3"},
	        {"(^|\\s)(\\.{2,})([^\\.\\s])", "\1 \2\3"},

//	        ##### Some additional fixes.

//	        # Fix %, $, &
	        {"(\\d)%", "\1 %"},
	        {"\\$(\\.?\\d)", "$ \1"},
	        {"(\\W)& (\\W)", "\1&\2"},
	        {"(\\W\\W+)&(\\W\\W+)", "\1 & \2"},

//	        # Fix (n 't) -> ( n't)
	        {"n 't( |$)", " n't\\1"},
	        {"N 'T( |$)", " N'T\\1"},

//	        # Treebank tokenizer special words
	        {"([Cc])annot", "\1an not"}

	    };
	
	public String tokenize(String s){
		for(String[] tokenize_regexp:tokenize_regexps){
			s=s.replaceAll(tokenize_regexp[0],tokenize_regexp[1]);
		}
		return s;
	}
}
