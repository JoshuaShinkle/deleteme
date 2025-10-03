package com.google.android.exoplayer2.util;

import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes.dex */
public final class XmlPullParserUtil {
    private XmlPullParserUtil() {
    }

    public static String getAttributeValue(XmlPullParser xmlPullParser, String str) {
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i9 = 0; i9 < attributeCount; i9++) {
            if (str.equals(xmlPullParser.getAttributeName(i9))) {
                return xmlPullParser.getAttributeValue(i9);
            }
        }
        return null;
    }

    public static boolean isEndTag(XmlPullParser xmlPullParser, String str) {
        return isEndTag(xmlPullParser) && xmlPullParser.getName().equals(str);
    }

    public static boolean isStartTag(XmlPullParser xmlPullParser, String str) {
        return isStartTag(xmlPullParser) && xmlPullParser.getName().equals(str);
    }

    public static boolean isEndTag(XmlPullParser xmlPullParser) {
        return xmlPullParser.getEventType() == 3;
    }

    public static boolean isStartTag(XmlPullParser xmlPullParser) {
        return xmlPullParser.getEventType() == 2;
    }
}
