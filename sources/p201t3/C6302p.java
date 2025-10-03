package p201t3;

import android.util.Log;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.database.C2950b0;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.C5187v0;
import p218v2.C6456d;

/* renamed from: t3.p */
/* loaded from: classes.dex */
public final class C6302p {

    /* renamed from: a */
    public static final String[] f21249a = {"chat.listScheduleSend", "backup.listAlbum", "friend.listSuggestions", "group.listMembersV2", "group.listAlbum"};

    /* renamed from: a */
    public static void m24112a(String str, String str2) {
        if ((str.equals("cancelScheduleSend") && str2.equals("400")) || str2.equals("200")) {
            return;
        }
        m24113b();
    }

    /* renamed from: b */
    public static void m24113b() {
        C5187v0.m20267c(R.string.error_server_response);
    }

    /* renamed from: c */
    public static String m24114c(List<C6301o> list) {
        if (list == null) {
            return null;
        }
        for (C6301o c6301o : list) {
            if (c6301o.m24110a().equals("accountSource")) {
                return c6301o.m24111b();
            }
        }
        return null;
    }

    /* renamed from: d */
    public static String m24115d(List<C6301o> list) {
        if (list == null) {
            return null;
        }
        for (C6301o c6301o : list) {
            if (c6301o.m24110a().equals("userId")) {
                return c6301o.m24111b();
            }
        }
        return null;
    }

    /* renamed from: e */
    public static void m24116e(String str, String str2) {
        if (str.equals("friendInfoViaPublicID") && str2.equals("404")) {
            C5187v0.m20267c(R.string.error_friend_friendInfoViaPublicID_404);
            return;
        }
        if (str.equals(ProductAction.ACTION_ADD) && str2.equals("400")) {
            C5187v0.m20267c(R.string.error_friend_add_400);
        } else {
            if (str2.equals("200")) {
                return;
            }
            m24113b();
        }
    }

    /* renamed from: f */
    public static String m24117f(List<C6301o> list) {
        if (list == null) {
            return "";
        }
        for (C6301o c6301o : list) {
            if (c6301o.m24110a().equals("addOrJoin2Group")) {
                return c6301o.m24111b();
            }
        }
        return "";
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0043  */
    /* renamed from: g */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void m24118g(String str, List<C6301o> list, String str2, String str3) throws JSONException {
        char c9 = 0;
        if (!"addMembers".equals(str) || !str2.equals("403")) {
            if ("leave".equals(str) && str2.equals("400")) {
                C5187v0.m20267c(R.string.error_group_leave);
                return;
            }
            if ("deleteMembers".equals(str) && str2.equals("400")) {
                C5187v0.m20268d(String.format(Globals.m7388i0().getString(R.string.error_group_member_leave), C2950b0.m14899A().m15003C(m24115d(list)).m15621b()));
                return;
            } else {
                if (str.equals("createAlbum") && str2.equals("403") && str3.contains("Max album limit exceeded")) {
                    return;
                }
                m24113b();
                return;
            }
        }
        try {
            String string = new JSONObject(str3).getString("errorMessage");
            String strM24117f = m24117f(list);
            int iHashCode = strM24117f.hashCode();
            if (iHashCode != -985460402) {
                c9 = (iHashCode == 533605143 && strM24117f.equals("join2Group")) ? (char) 1 : (char) 65535;
            } else if (!strM24117f.equals("add2Group")) {
            }
            if (c9 == 0) {
                if (string.startsWith("(G001)")) {
                    C5187v0.m20267c(R.string.error_group_addMember_403_add);
                    return;
                } else {
                    C5187v0.m20267c(R.string.error_group_addMember_403_addE2EE);
                    return;
                }
            }
            if (c9 != 1) {
                m24113b();
            } else if (string.startsWith("(G001)")) {
                C5187v0.m20267c(R.string.error_group_addMember_403_join);
            } else {
                C5187v0.m20267c(R.string.error_group_addMember_403_joinE2EE);
            }
        } catch (JSONException e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: h */
    public static void m24119h(String str, String str2) {
        if (str.equals("inviteFriend") && str2.equals("400")) {
            C5187v0.m20267c(R.string.error_invite_inviteFriend_400);
        } else {
            if (str2.equals("200")) {
                return;
            }
            m24113b();
        }
    }

    /* renamed from: i */
    public static boolean m24120i(String str, String str2) {
        return Arrays.asList(f21249a).contains(str + "." + str2);
    }

    /* renamed from: j */
    public static void m24121j(String str, String str2, String str3) {
        if (str.equals("mediaInfo") && str2.equals("404")) {
            C5187v0.m20267c(R.string.error_media_mediaInfo_404);
        } else {
            if ((str.equals("uploadMedia") && str2.equals("403") && str3.contains("Max media limit exceeded")) || str2.equals("200")) {
                return;
            }
            m24113b();
        }
    }

    /* renamed from: k */
    public static void m24122k(String str, String str2) {
        if ((str.equals("adUnitInfo") && str2.equals("400")) || str2.equals("200")) {
            return;
        }
        m24113b();
    }

    /* renamed from: l */
    public static void m24123l(String str, String str2, String str3, String str4) {
        m24125n(str, str2, null, str3, str4);
    }

    /* renamed from: m */
    public static void m24124m(String str, String str2, String str3, String str4, boolean z8) {
        if (str3 == null) {
            m24113b();
        } else if (!z8) {
            m24123l(str, str2, str3, str4);
        } else {
            if (str3.equals("200")) {
                return;
            }
            C5187v0.m20267c(R.string.click_deleted_album);
        }
    }

    /* renamed from: n */
    public static void m24125n(String str, String str2, List<C6301o> list, String str3, String str4) {
        if (m24120i(str, str2)) {
            return;
        }
        if (!C6456d.m24714D().m24748G()) {
            C5187v0.m20267c(R.string.error_no_network);
        } else if (str3 != null) {
            if (!str3.equals("200")) {
                str.hashCode();
                switch (str) {
                    case "friend":
                        m24116e(str2, str3);
                        break;
                    case "invite":
                        m24119h(str2, str3);
                        break;
                    case "chat":
                        m24112a(str2, str3);
                        break;
                    case "misc":
                        m24122k(str2, str3);
                        break;
                    case "user":
                        m24126o(str2, list, str3);
                        break;
                    case "group":
                        m24118g(str2, list, str3, str4);
                        break;
                    case "media":
                        m24121j(str2, str3, str4);
                        break;
                    default:
                        m24113b();
                        break;
                }
            } else {
                return;
            }
        } else {
            m24113b();
        }
        Log.e("ServerErrorResponse", "statusCode is " + str3 + " : " + str + "." + str2);
    }

    /* renamed from: o */
    public static void m24126o(String str, List<C6301o> list, String str2) {
        if (str.equals("registerPhone") && str2.equals("400")) {
            return;
        }
        if (str.equals("registerPhone") && str2.equals("429")) {
            C5187v0.m20267c(R.string.error_user_registerPhone_429);
            return;
        }
        if (str.equals("verifyPhone") && str2.equals("400")) {
            C5187v0.m20267c(R.string.error_user_verifyPhone_400);
            return;
        }
        if (str.equals("verifyPhone") && str2.equals("429")) {
            C5187v0.m20267c(R.string.error_user_verifyPhone_429);
            return;
        }
        if (str.equals("checkPhone") && str2.equals("400")) {
            return;
        }
        if (str.equals("bindLoginURL") && str2.equals("404")) {
            C5187v0.m20267c(R.string.pc_login_fail_text);
            return;
        }
        if (str.equals("userInfoV2") || str.equals("verifyPinForOthers")) {
            return;
        }
        if (!str.equals("bindAccount") || !str2.equals("400")) {
            if ("checkAccount".equals(str) && "400".equals(str2)) {
                C5187v0.m20267c(R.string.check_google_error);
                return;
            } else {
                if (str2.equals("200")) {
                    return;
                }
                m24113b();
                return;
            }
        }
        String strM24114c = m24114c(list);
        if ("Facebook".equals(strM24114c)) {
            C5187v0.m20267c(R.string.bind_facebook_error);
            return;
        }
        if ("Google".equals(strM24114c)) {
            C5187v0.m20267c(R.string.bind_google_error);
        } else if ("Phone".equals(strM24114c)) {
            C5187v0.m20267c(R.string.bind_phone_number_error);
        } else {
            m24113b();
        }
    }
}
