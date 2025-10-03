package com.cyberlink.you.chat;

import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import p136m3.C5321e;

/* renamed from: com.cyberlink.you.chat.a */
/* loaded from: classes.dex */
public class C2876a {

    /* renamed from: a */
    public static List<C2904l> f12567a = new ArrayList();

    /* renamed from: a */
    public static boolean m14292a(String str, C2904l c2904l) {
        XMPPManager.HandleType handleTypeM14438r = c2904l.m14438r();
        if (!str.equals("client.rtc.invite") && !str.equals("client.rtc.hangup") && !str.equals("meeting.meeting.end") && !str.equals("client.rtc.pickup")) {
            return false;
        }
        if ((handleTypeM14438r != XMPPManager.HandleType.XMPP || !c2904l.m14399N()) && handleTypeM14438r != XMPPManager.HandleType.GCM && handleTypeM14438r != XMPPManager.HandleType.HEART_BEAT) {
            return false;
        }
        if (str.equals("client.rtc.invite")) {
            ULogUtility.m16680p("ArchiveMeetingEvent", "[" + c2904l.m14438r() + "] add to ArchivedMeetingEventList eventType:" + str);
            f12567a.add(c2904l);
            return true;
        }
        ULogUtility.m16680p("ArchiveMeetingEvent", "[" + c2904l.m14438r() + "] add to ArchivedMeetingEventList eventType:" + str);
        f12567a.add(c2904l);
        return false;
    }

    /* renamed from: b */
    public static synchronized void m14293b(XMPPManager.HandleType handleType) {
        ULogUtility.m16680p("ArchiveMeetingEvent", "[" + handleType + "] handleArchiveMeetingPackets start | size = " + f12567a.size());
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (C2904l c2904l : f12567a) {
            Map<String, String> mapM14373e = ((C2898i) c2904l.m14426l("event", "urn:xmpp:custom:event")).m14373e();
            String str = mapM14373e.get("eventType");
            if (str.equals("client.rtc.invite")) {
                String str2 = mapM14373e.get("callId");
                linkedHashMap.put(str2, c2904l);
                ULogUtility.m16680p("ArchiveMeetingEvent", "[" + handleType + "] handleArchiveMeetingPackets meetingId = " + str2 + "> add(" + str + ")");
            } else if (str.equals("client.rtc.hangup") || str.equals("meeting.meeting.end") || str.equals("client.rtc.pickup")) {
                String str3 = str.equals("meeting.meeting.end") ? mapM14373e.get(TtmlNode.ATTR_ID) : mapM14373e.get("callId");
                linkedHashMap.remove(str3);
                ULogUtility.m16680p("ArchiveMeetingEvent", "[" + handleType + "] handleArchiveMeetingPackets meetingId = " + str3 + " >> remove(" + str + ")");
            }
        }
        ULogUtility.m16680p("ArchiveMeetingEvent", "[" + handleType + "] handleArchiveMeetingPackets result map = " + linkedHashMap.keySet());
        if (linkedHashMap.size() > 0) {
            C2904l c2904l2 = (C2904l) linkedHashMap.values().toArray()[linkedHashMap.size() - 1];
            ULogUtility.m16680p("ArchiveMeetingEvent", "[" + handleType + "] handleArchiveMeetingPackets last handle meetingEvent = " + c2904l2);
            C5321e.m20824o().m20830A0(c2904l2);
        }
        f12567a.clear();
        ULogUtility.m16680p("ArchiveMeetingEvent", "[" + handleType + "] handleArchiveMeetingPackets end");
    }
}
