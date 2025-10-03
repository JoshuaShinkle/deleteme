package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors;

/* loaded from: classes2.dex */
public interface BlockingService {
    Message callBlockingMethod(Descriptors.MethodDescriptor methodDescriptor, RpcController rpcController, Message message);

    Descriptors.ServiceDescriptor getDescriptorForType();

    Message getRequestPrototype(Descriptors.MethodDescriptor methodDescriptor);

    Message getResponsePrototype(Descriptors.MethodDescriptor methodDescriptor);
}
