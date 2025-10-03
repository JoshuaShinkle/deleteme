package com.cyberlink.you.chat.p035p;

import com.cyberlink.you.chat.C2898i;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.xmlpull.v1.XmlPullParser;
import p008a7.InterfaceC0051e;

/* loaded from: classes.dex */
public class LocationExtensionProvider implements InterfaceC0051e {
    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) {
        C2898i c2898i = new C2898i(FirebaseAnalytics.Param.LOCATION, "urn:xmpp:location:0");
        c2898i.m14375g(AppMeasurementSdk.ConditionalUserProperty.NAME, xmlPullParser.getAttributeValue(null, AppMeasurementSdk.ConditionalUserProperty.NAME));
        c2898i.m14375g("address", xmlPullParser.getAttributeValue(null, "address"));
        c2898i.m14375g("latitude", xmlPullParser.getAttributeValue(null, "latitude"));
        c2898i.m14375g("longitude", xmlPullParser.getAttributeValue(null, "longitude"));
        c2898i.m14375g("snapshotUrl", xmlPullParser.getAttributeValue(null, "snapshotUrl"));
        c2898i.m14375g("snapshotWidth", xmlPullParser.getAttributeValue(null, "snapshotWidth"));
        c2898i.m14375g("snapshotHeight", xmlPullParser.getAttributeValue(null, "snapshotHeight"));
        c2898i.m14375g("snapshotMediaId", xmlPullParser.getAttributeValue(null, "snapshotMediaId"));
        return c2898i;
    }
}
