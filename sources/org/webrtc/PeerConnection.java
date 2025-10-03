package org.webrtc;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.webrtc.DataChannel;

/* loaded from: classes3.dex */
public class PeerConnection {
    private final long nativeObserver;
    private final long nativePeerConnection;
    private final List<MediaStream> localStreams = new LinkedList();
    private List<RtpSender> senders = new LinkedList();
    private List<RtpReceiver> receivers = new LinkedList();

    public enum BundlePolicy {
        BALANCED,
        MAXBUNDLE,
        MAXCOMPAT
    }

    public enum CandidateNetworkPolicy {
        ALL,
        LOW_COST
    }

    public enum ContinualGatheringPolicy {
        GATHER_ONCE,
        GATHER_CONTINUALLY
    }

    public enum IceConnectionState {
        NEW,
        CHECKING,
        CONNECTED,
        COMPLETED,
        FAILED,
        DISCONNECTED,
        CLOSED
    }

    public enum IceGatheringState {
        NEW,
        GATHERING,
        COMPLETE
    }

    public static class IceServer {
        public final String password;
        public final TlsCertPolicy tlsCertPolicy;
        public final String uri;
        public final String username;

        public IceServer(String str) {
            this(str, "", "");
        }

        public String toString() {
            return this.uri + " [" + this.username + ":" + this.password + "] [" + this.tlsCertPolicy + "]";
        }

        public IceServer(String str, String str2, String str3) {
            this(str, str2, str3, TlsCertPolicy.TLS_CERT_POLICY_SECURE);
        }

        public IceServer(String str, String str2, String str3, TlsCertPolicy tlsCertPolicy) {
            this.uri = str;
            this.username = str2;
            this.password = str3;
            this.tlsCertPolicy = tlsCertPolicy;
        }
    }

    public enum IceTransportsType {
        NONE,
        RELAY,
        NOHOST,
        ALL
    }

    public enum KeyType {
        RSA,
        ECDSA
    }

    public interface Observer {
        void onAddStream(MediaStream mediaStream);

        void onAddTrack(RtpReceiver rtpReceiver, MediaStream[] mediaStreamArr);

        void onDataChannel(DataChannel dataChannel);

        void onIceCandidate(IceCandidate iceCandidate);

        void onIceCandidatesRemoved(IceCandidate[] iceCandidateArr);

        void onIceConnectionChange(IceConnectionState iceConnectionState);

        void onIceConnectionReceivingChange(boolean z8);

        void onIceGatheringChange(IceGatheringState iceGatheringState);

        void onRemoveStream(MediaStream mediaStream);

        void onRenegotiationNeeded();

        void onSignalingChange(SignalingState signalingState);
    }

    public static class RTCConfiguration {
        public List<IceServer> iceServers;
        public IceTransportsType iceTransportsType = IceTransportsType.ALL;
        public BundlePolicy bundlePolicy = BundlePolicy.BALANCED;
        public RtcpMuxPolicy rtcpMuxPolicy = RtcpMuxPolicy.REQUIRE;
        public TcpCandidatePolicy tcpCandidatePolicy = TcpCandidatePolicy.ENABLED;
        public CandidateNetworkPolicy candidateNetworkPolicy = CandidateNetworkPolicy.ALL;
        public int audioJitterBufferMaxPackets = 50;
        public boolean audioJitterBufferFastAccelerate = false;
        public int iceConnectionReceivingTimeout = -1;
        public int iceBackupCandidatePairPingInterval = -1;
        public KeyType keyType = KeyType.ECDSA;
        public ContinualGatheringPolicy continualGatheringPolicy = ContinualGatheringPolicy.GATHER_ONCE;
        public int iceCandidatePoolSize = 0;
        public boolean pruneTurnPorts = false;
        public boolean presumeWritableWhenFullyRelayed = false;
        public Integer iceCheckMinInterval = null;

        public RTCConfiguration(List<IceServer> list) {
            this.iceServers = list;
        }
    }

    public enum RtcpMuxPolicy {
        NEGOTIATE,
        REQUIRE
    }

    public enum SignalingState {
        STABLE,
        HAVE_LOCAL_OFFER,
        HAVE_LOCAL_PRANSWER,
        HAVE_REMOTE_OFFER,
        HAVE_REMOTE_PRANSWER,
        CLOSED
    }

    public enum TcpCandidatePolicy {
        ENABLED,
        DISABLED
    }

    public enum TlsCertPolicy {
        TLS_CERT_POLICY_SECURE,
        TLS_CERT_POLICY_INSECURE_NO_CHECK
    }

    static {
        System.loadLibrary("jingle_peerconnection_so");
    }

    public PeerConnection(long j9, long j10) {
        this.nativePeerConnection = j9;
        this.nativeObserver = j10;
    }

    private static native void freeObserver(long j9);

    private static native void freePeerConnection(long j9);

    private native boolean nativeAddIceCandidate(String str, int i9, String str2);

    private native boolean nativeAddLocalStream(long j9);

    private native RtpSender nativeCreateSender(String str, String str2);

    private native List<RtpReceiver> nativeGetReceivers();

    private native List<RtpSender> nativeGetSenders();

    private native boolean nativeGetStats(StatsObserver statsObserver, long j9);

    private native boolean nativeRemoveIceCandidates(IceCandidate[] iceCandidateArr);

    private native void nativeRemoveLocalStream(long j9);

    private native boolean nativeStartRtcEventLog(int i9, int i10);

    private native void nativeStopRtcEventLog();

    public boolean addIceCandidate(IceCandidate iceCandidate) {
        return nativeAddIceCandidate(iceCandidate.sdpMid, iceCandidate.sdpMLineIndex, iceCandidate.sdp);
    }

    public boolean addStream(MediaStream mediaStream) {
        if (!nativeAddLocalStream(mediaStream.nativeStream)) {
            return false;
        }
        this.localStreams.add(mediaStream);
        return true;
    }

    public native void close();

    public native void createAnswer(SdpObserver sdpObserver, MediaConstraints mediaConstraints);

    public native DataChannel createDataChannel(String str, DataChannel.Init init);

    public native void createOffer(SdpObserver sdpObserver, MediaConstraints mediaConstraints);

    public RtpSender createSender(String str, String str2) {
        RtpSender rtpSenderNativeCreateSender = nativeCreateSender(str, str2);
        if (rtpSenderNativeCreateSender != null) {
            this.senders.add(rtpSenderNativeCreateSender);
        }
        return rtpSenderNativeCreateSender;
    }

    public void dispose() {
        close();
        for (MediaStream mediaStream : this.localStreams) {
            nativeRemoveLocalStream(mediaStream.nativeStream);
            mediaStream.dispose();
        }
        this.localStreams.clear();
        Iterator<RtpSender> it = this.senders.iterator();
        while (it.hasNext()) {
            it.next().dispose();
        }
        this.senders.clear();
        Iterator<RtpReceiver> it2 = this.receivers.iterator();
        while (it2.hasNext()) {
            it2.next().dispose();
        }
        this.receivers.clear();
        freePeerConnection(this.nativePeerConnection);
        freeObserver(this.nativeObserver);
    }

    public native SessionDescription getLocalDescription();

    public List<RtpReceiver> getReceivers() {
        Iterator<RtpReceiver> it = this.receivers.iterator();
        while (it.hasNext()) {
            it.next().dispose();
        }
        List<RtpReceiver> listNativeGetReceivers = nativeGetReceivers();
        this.receivers = listNativeGetReceivers;
        return Collections.unmodifiableList(listNativeGetReceivers);
    }

    public native SessionDescription getRemoteDescription();

    public List<RtpSender> getSenders() {
        Iterator<RtpSender> it = this.senders.iterator();
        while (it.hasNext()) {
            it.next().dispose();
        }
        List<RtpSender> listNativeGetSenders = nativeGetSenders();
        this.senders = listNativeGetSenders;
        return Collections.unmodifiableList(listNativeGetSenders);
    }

    public boolean getStats(StatsObserver statsObserver, MediaStreamTrack mediaStreamTrack) {
        return nativeGetStats(statsObserver, mediaStreamTrack == null ? 0L : mediaStreamTrack.nativeTrack);
    }

    public native IceConnectionState iceConnectionState();

    public native IceGatheringState iceGatheringState();

    public native boolean nativeSetConfiguration(RTCConfiguration rTCConfiguration, long j9);

    public boolean removeIceCandidates(IceCandidate[] iceCandidateArr) {
        return nativeRemoveIceCandidates(iceCandidateArr);
    }

    public void removeStream(MediaStream mediaStream) {
        nativeRemoveLocalStream(mediaStream.nativeStream);
        this.localStreams.remove(mediaStream);
    }

    public boolean setConfiguration(RTCConfiguration rTCConfiguration) {
        return nativeSetConfiguration(rTCConfiguration, this.nativeObserver);
    }

    public native void setLocalDescription(SdpObserver sdpObserver, SessionDescription sessionDescription);

    public native void setRemoteDescription(SdpObserver sdpObserver, SessionDescription sessionDescription);

    public native SignalingState signalingState();

    public boolean startRtcEventLog(int i9, int i10) {
        return nativeStartRtcEventLog(i9, i10);
    }

    public void stopRtcEventLog() {
        nativeStopRtcEventLog();
    }
}
