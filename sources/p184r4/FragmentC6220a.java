package p184r4;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cyberlink.p030U.R;
import com.cyberlink.you.kernelctrl.glviewengine.GLViewEngine;
import com.cyberlink.you.kernelctrl.gpuimage.GPUImagePanZoomViewer;
import com.cyberlink.you.widgetpool.panel.coloreffectpanel.FragmentC3244b;
import p155o4.InterfaceC5466a;

/* renamed from: r4.a */
/* loaded from: classes.dex */
public class FragmentC6220a extends Fragment {

    /* renamed from: b */
    public GPUImagePanZoomViewer f20930b = null;

    /* renamed from: c */
    public InterfaceC5466a f20931c;

    /* renamed from: d */
    public View f20932d;

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public void m23772a(Fragment fragment) {
        InterfaceC5466a interfaceC5466a = (InterfaceC5466a) fragment;
        this.f20931c = interfaceC5466a;
        GPUImagePanZoomViewer gPUImagePanZoomViewer = this.f20930b;
        if (gPUImagePanZoomViewer == null || !(interfaceC5466a instanceof FragmentC3244b)) {
            return;
        }
        ((FragmentC3244b) interfaceC5466a).m17374S(gPUImagePanZoomViewer);
        ((FragmentC3244b) this.f20931c).m17377V(this.f20932d);
    }

    @Override // android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f20930b = (GPUImagePanZoomViewer) getActivity().findViewById(R.id.colorEffectGpuImageViewer);
        this.f20932d = getActivity().findViewById(R.id.presetSubPanel);
        InterfaceC5466a interfaceC5466a = this.f20931c;
        if (interfaceC5466a == null || !(interfaceC5466a instanceof FragmentC3244b)) {
            return;
        }
        ((FragmentC3244b) interfaceC5466a).m17374S(this.f20930b);
        ((FragmentC3244b) this.f20931c).m17377V(this.f20932d);
    }

    @Override // android.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.color_effect_view, viewGroup, false);
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        this.f20930b.setVisibility(4);
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        GLViewEngine.m15970g().m15975i();
        this.f20930b.setVisibility(0);
    }
}
