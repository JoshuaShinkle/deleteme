package androidx.media;

import android.content.Context;
import android.media.browse.MediaBrowser;
import android.service.media.MediaBrowserService;
import androidx.media.C0423a;

/* renamed from: androidx.media.b */
/* loaded from: classes.dex */
public class C0424b extends C0423a.b {
    public C0424b(Context context, InterfaceC0425c interfaceC0425c) {
        super(context, interfaceC0425c);
    }

    @Override // android.service.media.MediaBrowserService
    public void onLoadItem(String str, MediaBrowserService.Result<MediaBrowser.MediaItem> result) {
        ((InterfaceC0425c) this.f2352b).mo2164b(str, new C0423a.c<>(result));
    }
}
