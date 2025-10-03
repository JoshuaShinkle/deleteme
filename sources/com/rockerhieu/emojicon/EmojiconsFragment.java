package com.rockerhieu.emojicon;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.fragment.app.AbstractC0368g;
import androidx.fragment.app.AbstractC0371j;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.rockerhieu.emojicon.emoji.Emojicon;
import com.rockerhieu.emojicon.emoji.Nature;
import com.rockerhieu.emojicon.emoji.Objects;
import com.rockerhieu.emojicon.emoji.People;
import com.rockerhieu.emojicon.emoji.Places;
import com.rockerhieu.emojicon.emoji.Symbols;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public class EmojiconsFragment extends Fragment implements ViewPager.InterfaceC0557j {
    private int mEmojiTabLastSelectedIndex = -1;
    private View[] mEmojiTabs;
    private OnEmojiconBackspaceClickedListener mOnEmojiconBackspaceClickedListener;

    public static class EmojisPagerAdapter extends AbstractC0371j {
        private List<EmojiconGridFragment> fragments;

        public EmojisPagerAdapter(AbstractC0368g abstractC0368g, List<EmojiconGridFragment> list) {
            super(abstractC0368g);
            this.fragments = list;
        }

        @Override // p189s0.AbstractC6243a
        public int getCount() {
            return this.fragments.size();
        }

        @Override // androidx.fragment.app.AbstractC0371j
        public Fragment getItem(int i9) {
            return this.fragments.get(i9);
        }
    }

    public interface OnEmojiconBackspaceClickedListener {
        void onEmojiconBackspaceClicked(View view);
    }

    public static class RepeatListener implements View.OnTouchListener {
        private final View.OnClickListener clickListener;
        private View downView;
        private Handler handler = new Handler();
        private Runnable handlerRunnable = new Runnable() { // from class: com.rockerhieu.emojicon.EmojiconsFragment.RepeatListener.1
            @Override // java.lang.Runnable
            public void run() {
                if (RepeatListener.this.downView == null) {
                    return;
                }
                RepeatListener.this.handler.removeCallbacksAndMessages(RepeatListener.this.downView);
                RepeatListener.this.handler.postAtTime(this, RepeatListener.this.downView, SystemClock.uptimeMillis() + RepeatListener.this.normalInterval);
                RepeatListener.this.clickListener.onClick(RepeatListener.this.downView);
            }
        };
        private int initialInterval;
        private final int normalInterval;

        public RepeatListener(int i9, int i10, View.OnClickListener onClickListener) {
            if (onClickListener == null) {
                throw new IllegalArgumentException("null runnable");
            }
            if (i9 < 0 || i10 < 0) {
                throw new IllegalArgumentException("negative interval");
            }
            this.initialInterval = i9;
            this.normalInterval = i10;
            this.clickListener = onClickListener;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action == 0) {
                this.downView = view;
                this.handler.removeCallbacks(this.handlerRunnable);
                this.handler.postAtTime(this.handlerRunnable, this.downView, SystemClock.uptimeMillis() + this.initialInterval);
                this.clickListener.onClick(view);
                return true;
            }
            if (action != 1 && action != 3 && action != 4) {
                return false;
            }
            this.handler.removeCallbacksAndMessages(this.downView);
            this.downView = null;
            return true;
        }
    }

    public static void backspace(EditText editText) {
        editText.dispatchKeyEvent(new KeyEvent(0L, 0L, 0, 67, 0, 0, 0, 0, 6));
    }

    public static void input(EditText editText, Emojicon emojicon) {
        if (editText == null || emojicon == null) {
            return;
        }
        int selectionStart = editText.getSelectionStart();
        int selectionEnd = editText.getSelectionEnd();
        if (selectionStart < 0) {
            editText.append(emojicon.getEmoji());
        } else {
            editText.getText().replace(Math.min(selectionStart, selectionEnd), Math.max(selectionStart, selectionEnd), emojicon.getEmoji(), 0, emojicon.getEmoji().length());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (getActivity() instanceof OnEmojiconBackspaceClickedListener) {
            this.mOnEmojiconBackspaceClickedListener = (OnEmojiconBackspaceClickedListener) getActivity();
            return;
        }
        if (getParentFragment() instanceof OnEmojiconBackspaceClickedListener) {
            this.mOnEmojiconBackspaceClickedListener = (OnEmojiconBackspaceClickedListener) getParentFragment();
            return;
        }
        throw new IllegalArgumentException(activity + " must implement interface " + OnEmojiconBackspaceClickedListener.class.getSimpleName());
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(C4611R.layout.emojicons, viewGroup, false);
        final ViewPager viewPager = (ViewPager) viewInflate.findViewById(C4611R.id.emojis_pager);
        viewPager.setOnPageChangeListener(this);
        viewPager.setAdapter(new EmojisPagerAdapter(getFragmentManager(), Arrays.asList(EmojiconGridFragment.newInstance(People.DATA), EmojiconGridFragment.newInstance(Nature.DATA), EmojiconGridFragment.newInstance(Objects.DATA), EmojiconGridFragment.newInstance(Places.DATA), EmojiconGridFragment.newInstance(Symbols.DATA))));
        View[] viewArr = new View[5];
        this.mEmojiTabs = viewArr;
        viewArr[0] = viewInflate.findViewById(C4611R.id.emojis_tab_0_people);
        this.mEmojiTabs[1] = viewInflate.findViewById(C4611R.id.emojis_tab_1_nature);
        this.mEmojiTabs[2] = viewInflate.findViewById(C4611R.id.emojis_tab_2_objects);
        this.mEmojiTabs[3] = viewInflate.findViewById(C4611R.id.emojis_tab_3_cars);
        this.mEmojiTabs[4] = viewInflate.findViewById(C4611R.id.emojis_tab_4_punctuation);
        final int i9 = 0;
        while (true) {
            View[] viewArr2 = this.mEmojiTabs;
            if (i9 >= viewArr2.length) {
                viewInflate.findViewById(C4611R.id.emojis_backspace).setOnTouchListener(new RepeatListener(CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT, 50, new View.OnClickListener() { // from class: com.rockerhieu.emojicon.EmojiconsFragment.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (EmojiconsFragment.this.mOnEmojiconBackspaceClickedListener != null) {
                            EmojiconsFragment.this.mOnEmojiconBackspaceClickedListener.onEmojiconBackspaceClicked(view);
                        }
                    }
                }));
                onPageSelected(0);
                return viewInflate;
            }
            viewArr2[i9].setOnClickListener(new View.OnClickListener() { // from class: com.rockerhieu.emojicon.EmojiconsFragment.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    viewPager.setCurrentItem(i9);
                }
            });
            i9++;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        this.mOnEmojiconBackspaceClickedListener = null;
        super.onDetach();
    }

    @Override // androidx.viewpager.widget.ViewPager.InterfaceC0557j
    public void onPageScrollStateChanged(int i9) {
    }

    @Override // androidx.viewpager.widget.ViewPager.InterfaceC0557j
    public void onPageScrolled(int i9, float f9, int i10) {
    }

    @Override // androidx.viewpager.widget.ViewPager.InterfaceC0557j
    public void onPageSelected(int i9) {
        int i10 = this.mEmojiTabLastSelectedIndex;
        if (i10 == i9) {
            return;
        }
        if (i9 == 0 || i9 == 1 || i9 == 2 || i9 == 3 || i9 == 4) {
            if (i10 >= 0) {
                View[] viewArr = this.mEmojiTabs;
                if (i10 < viewArr.length) {
                    viewArr[i10].setSelected(false);
                }
            }
            this.mEmojiTabs[i9].setSelected(true);
            this.mEmojiTabLastSelectedIndex = i9;
        }
    }
}
