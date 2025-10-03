package com.cyberlink.clrtc;

import android.graphics.PointF;
import android.util.Pair;
import com.cyberlink.clrtc.NileNetwork;
import com.cyberlink.clrtc.model.ActiveMedia;
import com.cyberlink.clrtc.model.ChatMsg;
import java.util.Collection;
import java.util.List;
import p003a2.C0012b;
import p209u2.C6382s;

/* renamed from: com.cyberlink.clrtc.s */
/* loaded from: classes.dex */
public interface InterfaceC1113s {
    /* renamed from: B */
    default void mo5102B(C0012b c0012b, C0012b c0012b2) {
    }

    /* renamed from: C0 */
    default void mo5103C0(boolean z8) {
    }

    /* renamed from: D */
    default void mo5104D(NileNetwork.PhoneLineInviteResult phoneLineInviteResult) {
    }

    /* renamed from: E */
    default void mo5105E(boolean z8) {
    }

    /* renamed from: F0 */
    default void mo5106F0(int i9) {
    }

    /* renamed from: G0 */
    default void mo5107G0(int i9, String[] strArr, String[] strArr2, String str) {
    }

    /* renamed from: H */
    default void mo5108H(List<C0012b> list, List<C0012b> list2) {
    }

    /* renamed from: H0 */
    default void mo5109H0(NileNetwork.HostControlStatus hostControlStatus, int i9) {
    }

    /* renamed from: J */
    default void mo5110J(NileNetwork.HostControlStatus hostControlStatus, int i9) {
    }

    /* renamed from: L */
    default void mo5111L() {
    }

    /* renamed from: N */
    default void mo5112N(C6382s c6382s) {
    }

    /* renamed from: O */
    default void mo5113O(C0012b c0012b) {
    }

    default void OnAddHostNotify(int i9, int i10) {
    }

    default void OnBRBroadcast(String str, String str2) {
    }

    default void OnBREventTime(int i9, int i10) {
    }

    default void OnBRListHost(int i9, String str) {
    }

    default void OnBRMove(String str) {
    }

    default void OnBRRegisterAck(boolean z8) {
    }

    default void OnBRRename(int i9, String str, String str2) {
    }

    default void OnDowngradeVideoQuality() {
    }

    default void OnHaveBeenKickedout(int i9) {
    }

    default void OnMeetingHost() {
    }

    default void OnNewBlackListURL(String str) {
    }

    default void OnParticipantReset() {
    }

    default void OnParticipantUpdateDone() {
    }

    default void OnPinVideoResult(int i9, int i10) {
    }

    default void OnQNAHostHangup() {
    }

    default void OnQNAHostPicking() {
    }

    default void OnRemoveHostNotify(int i9, int i10) {
    }

    default void OnRollCallStartNotify(int i9, int i10, int i11) {
    }

    default void OnRollCallStop() {
    }

    default void OnStopDTByHost() {
    }

    default void OnUpdateRCDuration(int i9) {
    }

    default void OnWRBackNotify(int i9, int i10) {
    }

    default void OnWRMsg(String str) {
    }

    default void OnWaitingRoomEnabled(boolean z8) {
    }

    /* renamed from: P */
    default void mo5114P(List<C0012b> list, List<ActiveMedia.Type> list2, boolean z8) {
    }

    /* renamed from: Q */
    default void mo5115Q(Collection<ChatMsg> collection) {
    }

    /* renamed from: X */
    default void mo5116X(ChatMsg chatMsg) {
    }

    /* renamed from: Y */
    default void mo5117Y(ChatMsg chatMsg) {
    }

    /* renamed from: Z */
    default void mo5118Z(List<C0012b> list) {
    }

    /* renamed from: b */
    default void mo5119b(boolean z8, boolean z9) {
    }

    /* renamed from: c */
    default void mo5120c(C0012b c0012b, boolean z8) {
    }

    /* renamed from: d */
    default void mo5121d() {
    }

    /* renamed from: d0 */
    default void m5122d0(NileNetwork.RTCError rTCError, int i9) {
    }

    /* renamed from: e */
    default void mo5123e(List<String> list, boolean z8) {
    }

    /* renamed from: e0 */
    default void mo5124e0(NileNetwork.Status status, int i9) {
    }

    /* renamed from: f */
    default void mo5125f(List<Pair<Integer, String>> list) {
    }

    /* renamed from: g */
    default void mo5126g(NileNetwork.StopDTStatus stopDTStatus) {
    }

    /* renamed from: g0 */
    default void mo5127g0(String str) {
    }

    /* renamed from: i */
    default void m5128i(C0012b c0012b, PointF pointF) {
    }

    /* renamed from: i0 */
    default void mo5129i0(List<Integer> list) {
    }

    /* renamed from: j0 */
    default void mo5130j0(List<Pair<String, Integer>> list) {
    }

    /* renamed from: k */
    default void mo5131k(int i9, NileNetwork.BackToWaitingRoomStatus backToWaitingRoomStatus) {
    }

    /* renamed from: k0 */
    default void mo5132k0() {
    }

    /* renamed from: l */
    default void mo5133l(boolean z8) {
    }

    /* renamed from: l0 */
    default void mo5134l0(C0012b c0012b, boolean z8, boolean z9) {
    }

    /* renamed from: m0 */
    default void mo5135m0(int i9) {
    }

    /* renamed from: o */
    default void mo5136o(List<Pair<String, String>> list) {
    }

    /* renamed from: o0 */
    default void mo5137o0(C0012b c0012b) {
    }

    /* renamed from: p0 */
    default void mo5138p0(int i9) {
    }

    /* renamed from: q */
    default void mo5139q(long j9, long j10) {
    }

    /* renamed from: r */
    default void mo5140r(C0012b c0012b) {
    }

    /* renamed from: s */
    default void mo5141s(List<C0012b> list) {
    }

    /* renamed from: s0 */
    default void mo5142s0(int i9) {
    }

    /* renamed from: u */
    default void m5143u() {
    }

    /* renamed from: u0 */
    default void mo5144u0(boolean z8) {
    }

    /* renamed from: w */
    default void mo5145w(int i9) {
    }

    /* renamed from: w0 */
    default void mo5146w0(boolean z8) {
    }

    /* renamed from: x0 */
    default void mo5147x0(String str) {
    }

    /* renamed from: y */
    default void mo5148y(ChatMsg chatMsg, ChatMsg chatMsg2) {
    }

    /* renamed from: y0 */
    default void mo5149y0(int i9, int i10, String str, int i11, String str2) {
    }

    /* renamed from: z */
    default void mo5150z(NileError nileError, int i9) {
    }

    /* renamed from: z0 */
    default void m5151z0(NileNetwork.DisplayMode displayMode, int i9) {
    }
}
