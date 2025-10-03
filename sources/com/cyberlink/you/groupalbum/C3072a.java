package com.cyberlink.you.groupalbum;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.cyberlink.p030U.R;
import com.cyberlink.p033u.glide.MediaLoader;
import com.cyberlink.you.activity.PhotoListActivity;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.database.GroupAlbumObj;
import com.cyberlink.you.groupalbum.GroupAlbumFragment;
import com.cyberlink.you.utility.CLUtility;
import java.util.List;
import p105j3.C5094a;

/* renamed from: com.cyberlink.you.groupalbum.a */
/* loaded from: classes.dex */
public class C3072a extends ArrayAdapter<GroupAlbumObj> {

    /* renamed from: f */
    public static final String f13874f = "a";

    /* renamed from: b */
    public List<GroupAlbumObj> f13875b;

    /* renamed from: c */
    public int f13876c;

    /* renamed from: d */
    public Context f13877d;

    /* renamed from: e */
    public Fragment f13878e;

    /* renamed from: com.cyberlink.you.groupalbum.a$b */
    public class b extends AsyncTask<Void, Void, List<C2973l0>> {

        /* renamed from: a */
        public GroupAlbumObj f13879a;

        /* renamed from: b */
        public View f13880b;

        /* renamed from: c */
        public int f13881c;

        public b(GroupAlbumObj groupAlbumObj, View view, int i9) {
            this.f13879a = groupAlbumObj;
            this.f13880b = view;
            this.f13881c = i9;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public List<C2973l0> doInBackground(Void... voidArr) {
            return C2950b0.m14914m().m14727x(this.f13879a.m14675b());
        }

        /* renamed from: b */
        public final void m15957b(int i9, c cVar) {
            if (this.f13880b != null) {
                MediaLoader.m7149h(C3072a.this.f13877d, cVar.f13887e);
                cVar.f13890h.removeAllViews();
            }
            C3072a.this.m15948f(cVar.f13890h, cVar, i9);
            if (i9 == 0) {
                cVar.f13890h.setVisibility(8);
            } else {
                cVar.f13890h.setVisibility(0);
            }
        }

        /* renamed from: c */
        public final boolean m15958c(c cVar, int i9) {
            ImageView[] imageViewArr = cVar.f13887e;
            if ((imageViewArr == null && i9 != 0) || (imageViewArr != null && imageViewArr.length != i9)) {
                if (!cVar.f13891i) {
                    return true;
                }
                cVar.f13891i = false;
            }
            return false;
        }

        /* renamed from: d */
        public final boolean m15959d(c cVar) {
            return this.f13881c == cVar.f13892j;
        }

        @Override // android.os.AsyncTask
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<C2973l0> list) {
            c cVar = (c) this.f13880b.getTag();
            if (m15959d(cVar)) {
                int size = list.size();
                if (m15958c(cVar, size)) {
                    m15957b(size, cVar);
                }
                if (C3072a.this.f13875b == null || C3072a.this.f13875b.size() <= 0) {
                    return;
                }
                C3072a.this.m15955o(cVar, this.f13879a, list, this.f13880b);
            }
        }
    }

    /* renamed from: com.cyberlink.you.groupalbum.a$c */
    public static class c {

        /* renamed from: a */
        public ImageView f13883a;

        /* renamed from: b */
        public TextView f13884b;

        /* renamed from: c */
        public TextView f13885c;

        /* renamed from: d */
        public TextView f13886d;

        /* renamed from: e */
        public ImageView[] f13887e;

        /* renamed from: f */
        public LinearLayout f13888f;

        /* renamed from: g */
        public Button f13889g;

        /* renamed from: h */
        public RelativeLayout f13890h;

        /* renamed from: i */
        public boolean f13891i;

        /* renamed from: j */
        public int f13892j;

        /* renamed from: k */
        public AsyncTask<Void, Void, List<C2973l0>> f13893k;

        public c() {
            this.f13891i = false;
        }
    }

    public C3072a(Context context, int i9, List<GroupAlbumObj> list) {
        super(context, i9, list);
        this.f13875b = list;
        this.f13877d = context;
        this.f13876c = i9;
        this.f13878e = null;
    }

    /* renamed from: g */
    public static MediaLoader.Option m15946g(int i9, int i10) {
        return i10 <= 2 ? MediaLoader.Option.ORIGINAL : (i9 == 0 && (i10 == 3 || i10 == 5)) ? MediaLoader.Option.ORIGINAL : (i10 == 3 || i10 == 4) ? MediaLoader.Option.ORIGINAL_PREFER_CACHE : ((i9 == 0 || i9 == 3) && i10 == 6) ? MediaLoader.Option.ORIGINAL_PREFER_CACHE : (i9 == 0 && i10 == 7) ? MediaLoader.Option.ORIGINAL_PREFER_CACHE : MediaLoader.Option.THUMBNAIL;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l */
    public /* synthetic */ void m15947l(c cVar, GroupAlbumObj groupAlbumObj, View view) {
        Fragment fragment;
        cVar.f13891i = true;
        if (this.f13877d == null || (fragment = this.f13878e) == null) {
            return;
        }
        if (((GroupAlbumFragment) fragment).m15889G0().equals(GroupAlbumFragment.SelectDataType.Internal_Media_Select)) {
            ((GroupAlbumFragment) this.f13878e).m15885C0(groupAlbumObj);
            notifyDataSetChanged();
        } else {
            if (((GroupAlbumFragment) this.f13878e).m15889G0().equals(GroupAlbumFragment.SelectDataType.External_Media_Select)) {
                ((GroupAlbumFragment) this.f13878e).m15893K0(groupAlbumObj.m14675b());
                return;
            }
            Intent intent = new Intent(this.f13877d, (Class<?>) PhotoListActivity.class);
            intent.putExtra("album", groupAlbumObj);
            this.f13878e.startActivityForResult(intent, 0);
        }
    }

    /* renamed from: f */
    public final void m15948f(RelativeLayout relativeLayout, c cVar, int i9) {
        if (i9 < 1) {
            return;
        }
        if (i9 > 8) {
            i9 = 8;
        }
        ImageView[] imageViewArr = new ImageView[i9];
        LayoutInflater layoutInflater = (LayoutInflater) this.f13877d.getSystemService("layout_inflater");
        switch (i9) {
            case 1:
                View viewInflate = layoutInflater.inflate(R.layout.myalbum_priview_1, (ViewGroup) relativeLayout, false);
                imageViewArr[0] = (ImageView) viewInflate.findViewById(R.id.GroupAlbumPreviewLayout1_1);
                relativeLayout.addView(viewInflate);
                break;
            case 2:
                View viewInflate2 = layoutInflater.inflate(R.layout.myalbum_priview_2, (ViewGroup) relativeLayout, false);
                imageViewArr[0] = (ImageView) viewInflate2.findViewById(R.id.GroupAlbumPreviewLayout2_1);
                imageViewArr[1] = (ImageView) viewInflate2.findViewById(R.id.GroupAlbumPreviewLayout2_2);
                relativeLayout.addView(viewInflate2);
                break;
            case 3:
                View viewInflate3 = layoutInflater.inflate(R.layout.myalbum_priview_3, (ViewGroup) relativeLayout, false);
                imageViewArr[0] = (ImageView) viewInflate3.findViewById(R.id.GroupAlbumPreviewLayout3_1);
                imageViewArr[1] = (ImageView) viewInflate3.findViewById(R.id.GroupAlbumPreviewLayout3_2);
                imageViewArr[2] = (ImageView) viewInflate3.findViewById(R.id.GroupAlbumPreviewLayout3_3);
                relativeLayout.addView(viewInflate3);
                break;
            case 4:
                View viewInflate4 = layoutInflater.inflate(R.layout.myalbum_priview_4, (ViewGroup) relativeLayout, false);
                imageViewArr[0] = (ImageView) viewInflate4.findViewById(R.id.GroupAlbumPreviewLayout4_1);
                imageViewArr[1] = (ImageView) viewInflate4.findViewById(R.id.GroupAlbumPreviewLayout4_2);
                imageViewArr[2] = (ImageView) viewInflate4.findViewById(R.id.GroupAlbumPreviewLayout4_3);
                imageViewArr[3] = (ImageView) viewInflate4.findViewById(R.id.GroupAlbumPreviewLayout4_4);
                relativeLayout.addView(viewInflate4);
                break;
            case 5:
                View viewInflate5 = layoutInflater.inflate(R.layout.myalbum_priview_5, (ViewGroup) relativeLayout, false);
                imageViewArr[0] = (ImageView) viewInflate5.findViewById(R.id.GroupAlbumPreviewLayout5_1);
                imageViewArr[1] = (ImageView) viewInflate5.findViewById(R.id.GroupAlbumPreviewLayout5_2);
                imageViewArr[2] = (ImageView) viewInflate5.findViewById(R.id.GroupAlbumPreviewLayout5_3);
                imageViewArr[3] = (ImageView) viewInflate5.findViewById(R.id.GroupAlbumPreviewLayout5_4);
                imageViewArr[4] = (ImageView) viewInflate5.findViewById(R.id.GroupAlbumPreviewLayout5_5);
                relativeLayout.addView(viewInflate5);
                break;
            case 6:
                View viewInflate6 = layoutInflater.inflate(R.layout.myalbum_priview_6, (ViewGroup) relativeLayout, false);
                imageViewArr[0] = (ImageView) viewInflate6.findViewById(R.id.GroupAlbumPreviewLayout6_1);
                imageViewArr[1] = (ImageView) viewInflate6.findViewById(R.id.GroupAlbumPreviewLayout6_2);
                imageViewArr[2] = (ImageView) viewInflate6.findViewById(R.id.GroupAlbumPreviewLayout6_3);
                imageViewArr[3] = (ImageView) viewInflate6.findViewById(R.id.GroupAlbumPreviewLayout6_4);
                imageViewArr[4] = (ImageView) viewInflate6.findViewById(R.id.GroupAlbumPreviewLayout6_5);
                imageViewArr[5] = (ImageView) viewInflate6.findViewById(R.id.GroupAlbumPreviewLayout6_6);
                relativeLayout.addView(viewInflate6);
                break;
            case 7:
                View viewInflate7 = layoutInflater.inflate(R.layout.myalbum_priview_7, (ViewGroup) relativeLayout, false);
                imageViewArr[0] = (ImageView) viewInflate7.findViewById(R.id.GroupAlbumPreviewLayout7_1);
                imageViewArr[1] = (ImageView) viewInflate7.findViewById(R.id.GroupAlbumPreviewLayout7_2);
                imageViewArr[2] = (ImageView) viewInflate7.findViewById(R.id.GroupAlbumPreviewLayout7_3);
                imageViewArr[3] = (ImageView) viewInflate7.findViewById(R.id.GroupAlbumPreviewLayout7_4);
                imageViewArr[4] = (ImageView) viewInflate7.findViewById(R.id.GroupAlbumPreviewLayout7_5);
                imageViewArr[5] = (ImageView) viewInflate7.findViewById(R.id.GroupAlbumPreviewLayout7_6);
                imageViewArr[6] = (ImageView) viewInflate7.findViewById(R.id.GroupAlbumPreviewLayout7_7);
                relativeLayout.addView(viewInflate7);
                break;
            case 8:
                View viewInflate8 = layoutInflater.inflate(R.layout.myalbum_priview_8, (ViewGroup) relativeLayout, false);
                imageViewArr[0] = (ImageView) viewInflate8.findViewById(R.id.GroupAlbumPreviewLayout8_1);
                imageViewArr[1] = (ImageView) viewInflate8.findViewById(R.id.GroupAlbumPreviewLayout8_2);
                imageViewArr[2] = (ImageView) viewInflate8.findViewById(R.id.GroupAlbumPreviewLayout8_3);
                imageViewArr[3] = (ImageView) viewInflate8.findViewById(R.id.GroupAlbumPreviewLayout8_4);
                imageViewArr[4] = (ImageView) viewInflate8.findViewById(R.id.GroupAlbumPreviewLayout8_5);
                imageViewArr[5] = (ImageView) viewInflate8.findViewById(R.id.GroupAlbumPreviewLayout8_6);
                imageViewArr[6] = (ImageView) viewInflate8.findViewById(R.id.GroupAlbumPreviewLayout8_7);
                imageViewArr[7] = (ImageView) viewInflate8.findViewById(R.id.GroupAlbumPreviewLayout8_8);
                relativeLayout.addView(viewInflate8);
                break;
        }
        cVar.f13887e = imageViewArr;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i9, View view, ViewGroup viewGroup) {
        c cVar;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.f13877d.getSystemService("layout_inflater");
            int i10 = this.f13876c;
            if (i10 == R.layout.album_list) {
                view = layoutInflater.inflate(i10, (ViewGroup) null);
                cVar = new c();
                cVar.f13883a = (ImageView) view.findViewById(R.id.img_photo);
                cVar.f13884b = (TextView) view.findViewById(R.id.txt_name);
                view.setTag(cVar);
            } else {
                view = layoutInflater.inflate(R.layout.view_item_group_album, viewGroup, false);
                cVar = new c();
                cVar.f13884b = (TextView) view.findViewById(R.id.GroupAlbumNameTextView);
                cVar.f13885c = (TextView) view.findViewById(R.id.GroupAlbumModifiedTextView);
                cVar.f13886d = (TextView) view.findViewById(R.id.GroupAlbumAmountTextView);
                cVar.f13890h = (RelativeLayout) view.findViewById(R.id.GroupAlbumPreviewLayout);
                cVar.f13888f = (LinearLayout) view.findViewById(R.id.AlbumListLayout);
                cVar.f13889g = (Button) view.findViewById(R.id.GroupAlbumSelectBtn);
                cVar.f13891i = false;
                view.setTag(cVar);
            }
        } else {
            cVar = (c) view.getTag();
        }
        cVar.f13892j = i9;
        AsyncTask<Void, Void, List<C2973l0>> asyncTask = cVar.f13893k;
        if (asyncTask != null) {
            asyncTask.cancel(false);
        }
        b bVar = new b(this.f13875b.get(i9), view, i9);
        cVar.f13893k = bVar;
        bVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        return view;
    }

    /* renamed from: h */
    public void m15949h(String str) {
        for (int i9 = 0; i9 < this.f13875b.size(); i9++) {
            GroupAlbumObj groupAlbumObj = this.f13875b.get(i9);
            if (groupAlbumObj.m14675b().equals(str)) {
                remove(groupAlbumObj);
                return;
            }
        }
    }

    /* renamed from: i */
    public List<GroupAlbumObj> m15950i() {
        return this.f13875b;
    }

    /* renamed from: j */
    public GroupAlbumObj m15951j(String str) {
        for (int i9 = 0; i9 < this.f13875b.size(); i9++) {
            GroupAlbumObj groupAlbumObj = this.f13875b.get(i9);
            if (groupAlbumObj.m14675b().equals(str)) {
                return groupAlbumObj;
            }
        }
        return null;
    }

    /* renamed from: k */
    public final boolean m15952k(C2973l0 c2973l0) {
        int iM15151w = c2973l0.m15151w();
        int iM15141m = c2973l0.m15141m();
        return iM15151w > 0 && iM15141m > 0 && (iM15151w <= iM15141m ? iM15141m / iM15151w > 50 : iM15151w / iM15141m > 50);
    }

    /* renamed from: m */
    public void m15953m(Fragment fragment) {
        this.f13878e = fragment;
    }

    /* renamed from: n */
    public final void m15954n(c cVar, GroupAlbumObj groupAlbumObj, List<C2973l0> list, View view) {
        if (this.f13877d == null) {
            return;
        }
        Log.d(f13874f, "-----------updateGroupAlbumView ");
        LinearLayout linearLayout = cVar.f13888f;
        if (linearLayout != null) {
            linearLayout.setVisibility(0);
        }
        if (groupAlbumObj == null) {
            return;
        }
        String strM14676c = groupAlbumObj.m14676c();
        if (strM14676c != null) {
            String strM14677d = groupAlbumObj.m14677d();
            if (strM14677d == null || !strM14677d.equals("Product")) {
                cVar.f13884b.setText(strM14676c);
            } else {
                cVar.f13884b.setText(C5094a.m19947a(this.f13877d, strM14676c));
            }
        }
        if (groupAlbumObj.m14681h() != null) {
            cVar.f13885c.setText(CLUtility.m16470N2(groupAlbumObj.m14681h()));
        }
        cVar.f13886d.setText(String.valueOf(groupAlbumObj.m14682i()));
        int iMin = Math.min(list.size(), 8);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.GroupAlbumPreviewLayout);
        if (relativeLayout != null) {
            relativeLayout.setVisibility(iMin > 0 ? 0 : 8);
            ImageView[] imageViewArr = cVar.f13887e;
            if (imageViewArr != null && iMin != imageViewArr.length) {
                MediaLoader.m7149h(this.f13877d, imageViewArr);
                cVar.f13887e = null;
                relativeLayout.removeAllViews();
                m15948f(relativeLayout, cVar, iMin);
            }
        }
        ImageView[] imageViewArr2 = cVar.f13887e;
        if (imageViewArr2 == null || imageViewArr2.length != iMin) {
            return;
        }
        for (int i9 = 0; i9 < iMin; i9++) {
            C2973l0 c2973l0 = list.get(i9);
            MediaLoader.Option optionM15946g = m15946g(i9, iMin);
            if (m15952k(c2973l0)) {
                optionM15946g = MediaLoader.Option.THUMBNAIL;
            }
            MediaLoader.m7156o(this.f13877d, cVar.f13887e[i9], c2973l0, optionM15946g);
        }
    }

    /* renamed from: o */
    public final void m15955o(final c cVar, final GroupAlbumObj groupAlbumObj, List<C2973l0> list, View view) {
        Button button;
        if (this.f13877d == null) {
            return;
        }
        if (this.f13876c != R.layout.album_list) {
            Fragment fragment = this.f13878e;
            if (fragment == null || !((GroupAlbumFragment) fragment).m15890H0().equals(GroupAlbumFragment.GroupAlbumStatus.Select_Case)) {
                Fragment fragment2 = this.f13878e;
                if (fragment2 != null && ((GroupAlbumFragment) fragment2).m15890H0().equals(GroupAlbumFragment.GroupAlbumStatus.Normal_Case) && (button = cVar.f13889g) != null && button.getVisibility() == 0) {
                    cVar.f13889g.setVisibility(8);
                }
            } else {
                Button button2 = cVar.f13889g;
                if (button2 != null) {
                    button2.setVisibility(0);
                    cVar.f13889g.setOnClickListener(new View.OnClickListener() { // from class: u3.a
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view2) {
                            this.f21569b.m15947l(cVar, groupAlbumObj, view2);
                        }
                    });
                }
            }
            m15954n(cVar, groupAlbumObj, list, view);
            return;
        }
        if (!list.isEmpty()) {
            MediaLoader.m7156o(this.f13877d, cVar.f13883a, list.get(0), MediaLoader.Option.ORIGINAL_PREFER_CACHE);
        }
        String strM14676c = groupAlbumObj.m14676c();
        if (strM14676c != null) {
            String strM14677d = groupAlbumObj.m14677d();
            if (strM14677d != null && strM14677d.equals("Product")) {
                strM14676c = C5094a.m19947a(this.f13877d, strM14676c);
            }
            cVar.f13884b.setText(strM14676c + " (" + list.size() + ")");
        }
    }
}
