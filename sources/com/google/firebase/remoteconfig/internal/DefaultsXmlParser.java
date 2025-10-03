package com.google.firebase.remoteconfig.internal;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.Log;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes2.dex */
public class DefaultsXmlParser {
    private static final String XML_TAG_ENTRY = "entry";
    private static final String XML_TAG_KEY = "key";
    private static final String XML_TAG_VALUE = "value";

    /* JADX WARN: Removed duplicated region for block: B:37:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0083 A[Catch: IOException -> 0x008c, IOException | XmlPullParserException -> 0x008e, TryCatch #2 {IOException | XmlPullParserException -> 0x008e, blocks: (B:3:0x0007, B:5:0x000d, B:7:0x0013, B:12:0x0025, B:43:0x0087, B:15:0x002e, B:19:0x003e, B:20:0x0042, B:26:0x0050, B:40:0x0078, B:41:0x007e, B:42:0x0083, B:31:0x005f, B:34:0x0069), top: B:50:0x0007 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Map<String, String> getDefaultsFromXml(Context context, int i9) throws XmlPullParserException, Resources.NotFoundException, IOException {
        Resources resources;
        char c9;
        HashMap map = new HashMap();
        try {
            resources = context.getResources();
        } catch (IOException | XmlPullParserException e9) {
            Log.e(FirebaseRemoteConfig.TAG, "Encountered an error while parsing the defaults XML file.", e9);
        }
        if (resources == null) {
            Log.e(FirebaseRemoteConfig.TAG, "Could not find the resources of the current context while trying to set defaults from an XML.");
            return map;
        }
        XmlResourceParser xml = resources.getXml(i9);
        String name = null;
        String text = null;
        String text2 = null;
        for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
            if (eventType == 2) {
                name = xml.getName();
            } else if (eventType == 3) {
                if (xml.getName().equals(XML_TAG_ENTRY)) {
                    if (text == null || text2 == null) {
                        Log.w(FirebaseRemoteConfig.TAG, "An entry in the defaults XML has an invalid key and/or value tag.");
                    } else {
                        map.put(text, text2);
                    }
                    text = null;
                    text2 = null;
                }
                name = null;
            } else if (eventType == 4 && name != null) {
                int iHashCode = name.hashCode();
                if (iHashCode != 106079) {
                    c9 = (iHashCode == 111972721 && name.equals("value")) ? (char) 1 : (char) 65535;
                    if (c9 != 0) {
                        text = xml.getText();
                    } else if (c9 != 1) {
                        Log.w(FirebaseRemoteConfig.TAG, "Encountered an unexpected tag while parsing the defaults XML.");
                    } else {
                        text2 = xml.getText();
                    }
                } else {
                    if (name.equals(XML_TAG_KEY)) {
                        c9 = 0;
                    }
                    if (c9 != 0) {
                    }
                }
            }
        }
        return map;
    }
}
