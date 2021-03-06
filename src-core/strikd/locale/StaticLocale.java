package strikd.locale;

import java.io.File;

import strikd.locale.LocaleBundle.DictionaryType;
import strikd.words.WordDictionary;

public class StaticLocale
{
	static
	{
		LocaleBundleManager locMgr = new LocaleBundleManager(new File("locale.test"));
		locMgr.reload();
		LocaleBundle enUS = locMgr.getBundle("en_US");
		dict = enUS.getDictionary(DictionaryType.COMMON);
	}
	
	private static final WordDictionary dict;
	
	public static final WordDictionary getDictionary()
	{
		return dict;
	}
}
