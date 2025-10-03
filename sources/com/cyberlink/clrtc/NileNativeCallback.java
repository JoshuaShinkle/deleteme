package com.cyberlink.clrtc;

import androidx.annotation.Keep;

/* loaded from: classes.dex */
interface NileNativeCallback {
    @Keep
    void OnAddHostAck(int i9, int i10);

    @Keep
    void OnAddHostNotify(int i9, int i10);

    @Keep
    void OnAnUserHadBeenKicked(int i9, int i10);

    @Keep
    void OnBRBroadcast(String str, String str2);

    @Keep
    void OnBREventTime(int i9, int i10);

    @Keep
    void OnBRList(String[] strArr, String[] strArr2);

    @Keep
    void OnBRListHost(int i9, String str);

    @Keep
    void OnBRMove(String str);

    @Keep
    void OnBRRegisterAck(boolean z8);

    @Keep
    void OnBRRename(int i9, String str, String str2);

    @Keep
    void OnCallError(int i9);

    @Keep
    void OnCalleeAnotherDeviceAnswered();

    @Keep
    void OnCalleeAnotherDeviceBusy();

    @Keep
    void OnCalleeAnotherDeviceReject();

    @Keep
    void OnCalleeBusy();

    @Keep
    void OnCalleeRefused();

    @Keep
    void OnCallerHangUp();

    @Keep
    void OnChatMsgCount(int i9);

    @Keep
    void OnChatMsgQueried(boolean[] zArr, int[] iArr, int[] iArr2, long[] jArr, boolean[] zArr2, String[] strArr, int[] iArr3);

    @Keep
    void OnChatMsgReceived(int i9, int i10, long j9, boolean z8, String str, int i11);

    @Keep
    void OnChatMsgSendFailed(int i9);

    @Keep
    void OnChatMsgSent(int i9, int i10, long j9);

    @Keep
    void OnChatUsersInfo(int[] iArr, String[] strArr, String[] strArr2);

    @Keep
    void OnConfirmJoinCompleted();

    @Keep
    void OnDesktopHostResolutionNotified(int i9, int i10);

    @Keep
    void OnDeviceChanged();

    @Keep
    void OnDisplayModeChanged(int i9, int i10);

    @Keep
    void OnDisplayNameChanged(int i9, String str);

    @Keep
    void OnDowngradeVideoQuality();

    @Keep
    void OnEventTime(int i9, int i10);

    @Keep
    void OnFileUploadCanceled(int i9);

    @Keep
    void OnFileUploadCompleted(int i9, int i10, String str, int i11, String str2);

    @Keep
    void OnFileUploadPrepared(int i9, String[] strArr, String[] strArr2, String str);

    @Keep
    void OnForwardOptionNotify(int i9, int i10, int i11, int i12, int i13, String[] strArr);

    @Keep
    void OnHaveBeenKickedout(int i9);

    @Keep
    void OnJoinCompleted(int i9, boolean z8, boolean z9);

    @Keep
    void OnKickOldUserFailed(int i9);

    @Keep
    void OnLaserPointerEnabled(boolean z8);

    @Keep
    void OnLaserPointerReceived(int i9, float f9, float f10);

    @Keep
    void OnLaserPointerRequestAck(int i9);

    @Keep
    void OnLogUploadUrl(String str);

    @Keep
    void OnMeetingHadBeenClosed(int i9);

    @Keep
    void OnMeetingHost();

    @Keep
    void OnMeetingHost2(int[] iArr);

    @Keep
    void OnNetworkQualityNotify(int i9, int i10, int i11, int i12, int i13, int i14);

    @Keep
    void OnNewBlackListURL(String str);

    @Keep
    void OnOldApp(boolean z8);

    @Keep
    void OnParticipantReset();

    @Keep
    void OnParticipantUpdateDone();

    @Keep
    void OnPhonelineInviteResult(int i9);

    @Keep
    void OnPinVideoResult(int i9, int i10);

    @Keep
    void OnPreJoinCompleted(int i9, boolean z8, boolean z9);

    @Keep
    void OnPreJoinUserState(int i9, int i10);

    @Keep
    void OnQNAHostHangup();

    @Keep
    void OnQNAHostPicking();

    @Keep
    void OnRTCError(int i9);

    @Keep
    void OnRejectLimitUser(String str);

    @Keep
    void OnRemoveHostAck(int i9, int i10);

    @Keep
    void OnRemoveHostNotify(int i9, int i10);

    @Keep
    void OnRequestAllowRecording(int i9);

    @Keep
    void OnRequestCollectLog(boolean z8);

    @Keep
    void OnRequestCollectLogNow(boolean z8);

    @Keep
    void OnRequestMuteStatusChanged(int i9, boolean z8, boolean z9);

    @Keep
    void OnRequestVerifyUser(int[] iArr);

    @Keep
    void OnRollCallStartNotify(int i9, int i10, int i11);

    @Keep
    void OnRollCallStop();

    @Keep
    void OnStats(String str);

    @Keep
    void OnStatusChanged(int i9);

    @Keep
    void OnStopDTAck(int i9);

    @Keep
    void OnStopDTByHost();

    @Keep
    void OnUnderRecording(int[] iArr);

    @Keep
    void OnUpdateActiveList(int[] iArr, int[] iArr2, boolean z8);

    @Keep
    void OnUpdateBRStatus(String[] strArr, int[] iArr);

    @Keep
    void OnUpdateParticipants(int[] iArr, boolean[] zArr, boolean[] zArr2, String[] strArr, String[] strArr2, boolean[] zArr3, boolean[] zArr4, String[] strArr3, boolean[] zArr5, int[] iArr2, int[] iArr3, int[] iArr4, int[] iArr5, int[] iArr6);

    @Keep
    void OnUpdateRCDuration(int i9);

    @Keep
    void OnUpdateSpeechList(int[] iArr, boolean[] zArr);

    @Keep
    void OnVerifyFailed(int i9);

    @Keep
    void OnWRBackAck(int i9, int i10);

    @Keep
    void OnWRBackNotify(int i9, int i10);

    @Keep
    void OnWRMsg(String str);

    @Keep
    void OnWRUserList(int[] iArr, String[] strArr);

    @Keep
    void OnWaitingRoomEnabled(boolean z8);
}
