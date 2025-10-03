package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors;

/* loaded from: classes2.dex */
public interface BlockingRpcChannel {
    Message callBlockingMethod(Descriptors.MethodDescriptor methodDescriptor, RpcController rpcController, Message message, Message message2);
}
