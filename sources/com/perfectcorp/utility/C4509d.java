package com.perfectcorp.utility;

import java.util.HashMap;

/* renamed from: com.perfectcorp.utility.d */
/* loaded from: classes2.dex */
public class C4509d {

    /* renamed from: a */
    public static final String[][] f15946a;

    /* renamed from: b */
    public static final HashMap<String, CharSequence> f15947b = new HashMap<>();

    static {
        String[][] strArr = {new String[]{"\"", "quot"}, new String[]{"&", "amp"}, new String[]{"<", "lt"}, new String[]{">", "gt"}, new String[]{" ", "nbsp"}, new String[]{"¡", "iexcl"}, new String[]{"¢", "cent"}, new String[]{"£", "pound"}, new String[]{"¤", "curren"}, new String[]{"¥", "yen"}, new String[]{"¦", "brvbar"}, new String[]{"§", "sect"}, new String[]{"¨", "uml"}, new String[]{"©", "copy"}, new String[]{"ª", "ordf"}, new String[]{"«", "laquo"}, new String[]{"¬", "not"}, new String[]{"\u00ad", "shy"}, new String[]{"®", "reg"}, new String[]{"¯", "macr"}, new String[]{"°", "deg"}, new String[]{"±", "plusmn"}, new String[]{"²", "sup2"}, new String[]{"³", "sup3"}, new String[]{"´", "acute"}, new String[]{"µ", "micro"}, new String[]{"¶", "para"}, new String[]{"·", "middot"}, new String[]{"¸", "cedil"}, new String[]{"¹", "sup1"}, new String[]{"º", "ordm"}, new String[]{"»", "raquo"}, new String[]{"¼", "frac14"}, new String[]{"½", "frac12"}, new String[]{"¾", "frac34"}, new String[]{"¿", "iquest"}, new String[]{"À", "Agrave"}, new String[]{"Á", "Aacute"}, new String[]{"Â", "Acirc"}, new String[]{"Ã", "Atilde"}, new String[]{"Ä", "Auml"}, new String[]{"Å", "Aring"}, new String[]{"Æ", "AElig"}, new String[]{"Ç", "Ccedil"}, new String[]{"È", "Egrave"}, new String[]{"É", "Eacute"}, new String[]{"Ê", "Ecirc"}, new String[]{"Ë", "Euml"}, new String[]{"Ì", "Igrave"}, new String[]{"Í", "Iacute"}, new String[]{"Î", "Icirc"}, new String[]{"Ï", "Iuml"}, new String[]{"Ð", "ETH"}, new String[]{"Ñ", "Ntilde"}, new String[]{"Ò", "Ograve"}, new String[]{"Ó", "Oacute"}, new String[]{"Ô", "Ocirc"}, new String[]{"Õ", "Otilde"}, new String[]{"Ö", "Ouml"}, new String[]{"×", "times"}, new String[]{"Ø", "Oslash"}, new String[]{"Ù", "Ugrave"}, new String[]{"Ú", "Uacute"}, new String[]{"Û", "Ucirc"}, new String[]{"Ü", "Uuml"}, new String[]{"Ý", "Yacute"}, new String[]{"Þ", "THORN"}, new String[]{"ß", "szlig"}, new String[]{"à", "agrave"}, new String[]{"á", "aacute"}, new String[]{"â", "acirc"}, new String[]{"ã", "atilde"}, new String[]{"ä", "auml"}, new String[]{"å", "aring"}, new String[]{"æ", "aelig"}, new String[]{"ç", "ccedil"}, new String[]{"è", "egrave"}, new String[]{"é", "eacute"}, new String[]{"ê", "ecirc"}, new String[]{"ë", "euml"}, new String[]{"ì", "igrave"}, new String[]{"í", "iacute"}, new String[]{"î", "icirc"}, new String[]{"ï", "iuml"}, new String[]{"ð", "eth"}, new String[]{"ñ", "ntilde"}, new String[]{"ò", "ograve"}, new String[]{"ó", "oacute"}, new String[]{"ô", "ocirc"}, new String[]{"õ", "otilde"}, new String[]{"ö", "ouml"}, new String[]{"÷", "divide"}, new String[]{"ø", "oslash"}, new String[]{"ù", "ugrave"}, new String[]{"ú", "uacute"}, new String[]{"û", "ucirc"}, new String[]{"ü", "uuml"}, new String[]{"ý", "yacute"}, new String[]{"þ", "thorn"}, new String[]{"ÿ", "yuml"}};
        f15946a = strArr;
        for (String[] strArr2 : strArr) {
            f15947b.put(strArr2[1].toString(), strArr2[0]);
        }
    }

    /* renamed from: a */
    public static boolean m18119a(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    /* renamed from: b */
    public static boolean m18120b(CharSequence charSequence) {
        return m18119a(charSequence) || "null".equalsIgnoreCase(charSequence.toString());
    }
}
