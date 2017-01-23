package io.bzzzil.helper;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Locale;

/**
 * Class based on http://stackoverflow.com/questions/1453171/%C5%84-%C7%B9-%C5%88-%C3%B1-%E1%B9%85-%C5%86-%E1%B9%87-%E1%B9%8B-%E1%B9%89-%CC%88-%C9%B2-%C6%9E-%E1%B6%87-%C9%B3-%C8%B5-n-or-remove-diacritical-marks-from-unicode-cha
 */
public class StringSimplifier {
    private static final char DEFAULT_REPLACE_CHAR = ' ';
    private static final String DEFAULT_REPLACE = String.valueOf(DEFAULT_REPLACE_CHAR);
    private static final HashMap<String, String> NON_DIACRITICS = new HashMap<>();
    static {
        //Remove crap strings with no semantics
        NON_DIACRITICS.put(".", "");
        NON_DIACRITICS.put("\"", "");
        NON_DIACRITICS.put("'", "");

        //Keep relevant characters as separation
        NON_DIACRITICS.put(" ", DEFAULT_REPLACE);
        NON_DIACRITICS.put("]", DEFAULT_REPLACE);
        NON_DIACRITICS.put("[", DEFAULT_REPLACE);
        NON_DIACRITICS.put(")", DEFAULT_REPLACE);
        NON_DIACRITICS.put("(", DEFAULT_REPLACE);
        NON_DIACRITICS.put("=", DEFAULT_REPLACE);
        NON_DIACRITICS.put("!", DEFAULT_REPLACE);
        NON_DIACRITICS.put("/", DEFAULT_REPLACE);
        NON_DIACRITICS.put("\\", DEFAULT_REPLACE);
        NON_DIACRITICS.put("&", DEFAULT_REPLACE);
        NON_DIACRITICS.put(",", DEFAULT_REPLACE);
        NON_DIACRITICS.put("?", DEFAULT_REPLACE);
        NON_DIACRITICS.put("°", DEFAULT_REPLACE); //Remove ?? is diacritic?
        NON_DIACRITICS.put("|", DEFAULT_REPLACE);
        NON_DIACRITICS.put("<", DEFAULT_REPLACE);
        NON_DIACRITICS.put(">", DEFAULT_REPLACE);
        NON_DIACRITICS.put(";", DEFAULT_REPLACE);
        NON_DIACRITICS.put(":", DEFAULT_REPLACE);
        NON_DIACRITICS.put("_", DEFAULT_REPLACE);
        NON_DIACRITICS.put("#", DEFAULT_REPLACE);
        NON_DIACRITICS.put("~", DEFAULT_REPLACE);
        NON_DIACRITICS.put("+", DEFAULT_REPLACE);
        NON_DIACRITICS.put("*", DEFAULT_REPLACE);
        NON_DIACRITICS.put("«", DEFAULT_REPLACE);
        NON_DIACRITICS.put("„", DEFAULT_REPLACE);
        NON_DIACRITICS.put("»", DEFAULT_REPLACE);
        NON_DIACRITICS.put("“", DEFAULT_REPLACE);

        //Replace non-diacritics as their equivalent characters
        NON_DIACRITICS.put("\u0141", "l"); // BiaLystock
        NON_DIACRITICS.put("\u0142", "l"); // Bialystock
        NON_DIACRITICS.put("ß", "ss");
        NON_DIACRITICS.put("æ", "ae");
        NON_DIACRITICS.put("ø", "o");
        NON_DIACRITICS.put("©", "c");
        NON_DIACRITICS.put("\u00D0", "d"); // All Ð ð from http://de.wikipedia.org/wiki/%C3%90
        NON_DIACRITICS.put("\u00F0", "d");
        NON_DIACRITICS.put("\u0110", "d");
        NON_DIACRITICS.put("\u0111", "d");
        NON_DIACRITICS.put("\u0189", "d");
        NON_DIACRITICS.put("\u0256", "d");
        NON_DIACRITICS.put("\u00DE", "th"); // thorn Þ
        NON_DIACRITICS.put("\u00FE", "th"); // thorn þ
    }

    public static String simplifiedString(String orig) {
        String str = orig;
        if (str == null) {
            return null;
        }
        str = stripDiacritics(str);
        str = stripNON_DIACRITICS(str);
        if (str.length() == 0) {
            // Ugly special case to work around non-existing empty strings
            // in Oracle. Store original crapstring as simplified.
            // It would return an empty string if Oracle could store it.
            return orig;
        }
        return str.toLowerCase(Locale.US);
    }

    private static String stripNON_DIACRITICS(String orig) {
        StringBuilder ret = new StringBuilder();
        String lastChar = null;
        for (int i = 0; i < orig.length(); i++) {
            String source = orig.substring(i, i + 1);
            String replace = NON_DIACRITICS.get(source);
            String toReplace = replace == null ? String.valueOf(source) : replace;
            if (DEFAULT_REPLACE.equals(lastChar) && DEFAULT_REPLACE.equals(toReplace)) {
                toReplace = "";
            } else {
                lastChar = toReplace;
            }
            ret.append(toReplace);
        }
        if (ret.length() > 0 && DEFAULT_REPLACE_CHAR == ret.charAt(ret.length() - 1)) {
            ret.deleteCharAt(ret.length() - 1);
        }
        return ret.toString();
    }

    /*
    Special regular expression character ranges relevant for simplification -> see http://docstore.mik.ua/orelly/perl/prog3/ch05_04.htm
    InCombiningDiacriticalMarks: special marks that are part of "normal" ä, ö, î etc..
        IsSk: Symbol, Modifier see http://www.fileformat.info/info/unicode/category/Sk/list.htm
        IsLm: Letter, Modifier see http://www.fileformat.info/info/unicode/category/Lm/list.htm
     */
    /*public static final Pattern DIACRITICS_AND_FRIENDS
            = Pattern.compile("[\\p{InCombiningDiacriticalMarks}\\p{IsLm}\\p{IsSk}]+");*/


    private static String stripDiacritics(String str) {
        str = Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return str;
    }
}