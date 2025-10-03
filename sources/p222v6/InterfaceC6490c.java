package p222v6;

import org.jivesoftware.smack.XMPPConnection;

/* renamed from: v6.c */
/* loaded from: classes.dex */
public interface InterfaceC6490c {
    void authenticated(XMPPConnection xMPPConnection);

    void connected(XMPPConnection xMPPConnection);

    void connectionClosed();

    void connectionClosedOnError(Exception exc);

    void reconnectingIn(int i9);

    void reconnectionFailed(Exception exc);

    void reconnectionSuccessful();
}
