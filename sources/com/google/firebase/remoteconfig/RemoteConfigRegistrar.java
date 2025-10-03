package com.google.firebase.remoteconfig;

import android.content.Context;
import androidx.annotation.Keep;
import com.google.firebase.FirebaseApp;
import com.google.firebase.abt.FirebaseABTesting;
import com.google.firebase.abt.component.AbtComponent;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.Arrays;
import java.util.List;

@Keep
/* loaded from: classes2.dex */
public class RemoteConfigRegistrar implements ComponentRegistrar {
    public static /* synthetic */ RemoteConfigComponent lambda$getComponents$0(ComponentContainer componentContainer) {
        return new RemoteConfigComponent((Context) componentContainer.get(Context.class), (FirebaseApp) componentContainer.get(FirebaseApp.class), (FirebaseInstallationsApi) componentContainer.get(FirebaseInstallationsApi.class), ((AbtComponent) componentContainer.get(AbtComponent.class)).get(FirebaseABTesting.OriginService.REMOTE_CONFIG), (AnalyticsConnector) componentContainer.get(AnalyticsConnector.class));
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<Component<?>> getComponents() {
        return Arrays.asList(Component.builder(RemoteConfigComponent.class).add(Dependency.required((Class<?>) Context.class)).add(Dependency.required((Class<?>) FirebaseApp.class)).add(Dependency.required((Class<?>) FirebaseInstallationsApi.class)).add(Dependency.required((Class<?>) AbtComponent.class)).add(Dependency.optional(AnalyticsConnector.class)).factory(RemoteConfigRegistrar$$Lambda$1.instance).eagerInDefaultApp().build(), LibraryVersionComponent.create("fire-rc", BuildConfig.VERSION_NAME));
    }
}
