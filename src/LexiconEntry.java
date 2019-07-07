/**
 * builds one entry in the off-line lexicon
 *  
 * @author Cristina Vertan
 *
 */
public class LexiconEntry {
	/**
	 *  unique id of the lexicon entry
	 */
	String id;
	/**
	 *  search key - first letter of the lemme
	 */
	String key;
	/**
	 * the string representing the lemma
	 */
	String lemma;
	/**
	 * the entry in the dictionary with all information
	 */
	String dict;
	/**
	 *  builds a lexicon entry
	 * @param id a string  the unique identifier
	 * @param key the first letter og the lemma
	 * @param lemma the string representing the lemma
	 * @param dict the complete entry in the lexicon
	 */
	public LexiconEntry(String id, String key, String lemma, String dict){
		this.id=id;
		this.key=key;
		this.lemma=lemma;
		this.dict=dict;
	}
/**
 * 
 * @return a string the id of the lexicon entry
 */
	public String getLexiconEntryId(){
		return id;
	}
	/**
	 * 
	 * @return a string, the lemma
	 */
	public String getLexiconEntryLemma(){
		return lemma;
	}
	/**
	 * 
	 * @return a string: the complete entry in the dictionary
	 */
	public String getLexiconentryDict(){
		return dict;
	}
	/**
	 * 
	 * @return a string: the search key
	 */
	public String getLexiconentryKey(){
		return key;
	}
}
