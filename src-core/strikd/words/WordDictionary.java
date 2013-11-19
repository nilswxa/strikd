package strikd.words;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import strikd.util.RandomUtil;
import strikd.words.index.WordDictionaryIndex;

public class WordDictionary
{
	private final String locale;
	
	private final Word[] select;
	private final Set<String> check;
	private final WordDictionaryIndex index;
	
	public WordDictionary(String locale, File file, boolean useIndexes) throws IOException
	{
		// Determine whether to use indexes
		this.locale = locale;
		this.index = useIndexes ? new WordDictionaryIndex() : null;
		
		List<Word> select = new ArrayList<Word>();
		Set<String> check = new HashSet<String>();
		
		// Load and normalize words, one word per line
		String line;
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file))))
		{
			while((line = reader.readLine()) != null)
			{
				Word word = Word.parse(line);
				select.add(word);
				check.add(word.toString());
				if(useIndexes)
				{
					this.index.addWord(word.toString());
				}
			}
		}
		
		// Create immutable collections for all kinds of purposes
		this.select = select.toArray(new Word[0]);
		this.check = check;
	}
	
	public Word pickOne()
	{
		return RandomUtil.pickOne(this.select);
	}
	
	public boolean contains(Word word)
	{
		return this.check.contains(word.toString());
	}
	
	public String getLocale()
	{
		return this.locale;
	}
	
	public WordDictionaryIndex getIndex()
	{
		return this.index;
	}
	
	public int size()
	{
		return this.select.length;
	}
}
