package com.rockerhieu.emojicon;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.fragment.app.Fragment;
import com.rockerhieu.emojicon.emoji.Emojicon;
import com.rockerhieu.emojicon.emoji.People;

/* loaded from: classes2.dex */
public class EmojiconGridFragment extends Fragment implements AdapterView.OnItemClickListener {
    private Emojicon[] mData;
    private OnEmojiconClickedListener mOnEmojiconClickedListener;

    public interface OnEmojiconClickedListener {
        void onEmojiconClicked(Emojicon emojicon);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static EmojiconGridFragment newInstance(Emojicon[] emojiconArr) {
        EmojiconGridFragment emojiconGridFragment = new EmojiconGridFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("emojicons", emojiconArr);
        emojiconGridFragment.setArguments(bundle);
        return emojiconGridFragment;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnEmojiconClickedListener) {
            this.mOnEmojiconClickedListener = (OnEmojiconClickedListener) activity;
            return;
        }
        if (getParentFragment() instanceof OnEmojiconClickedListener) {
            this.mOnEmojiconClickedListener = (OnEmojiconClickedListener) getParentFragment();
            return;
        }
        throw new IllegalArgumentException(activity + " must implement interface " + OnEmojiconClickedListener.class.getSimpleName());
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(C4611R.layout.emojicon_grid, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        this.mOnEmojiconClickedListener = null;
        super.onDetach();
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
        OnEmojiconClickedListener onEmojiconClickedListener = this.mOnEmojiconClickedListener;
        if (onEmojiconClickedListener != null) {
            onEmojiconClickedListener.onEmojiconClicked((Emojicon) adapterView.getItemAtPosition(i9));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.rockerhieu.emojicon.emoji.Emojicon[], java.io.Serializable] */
    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("emojicons", this.mData);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        GridView gridView = (GridView) view.findViewById(C4611R.id.Emoji_GridView);
        this.mData = getArguments() == null ? People.DATA : (Emojicon[]) getArguments().getSerializable("emojicons");
        gridView.setAdapter((ListAdapter) new EmojiAdapter(view.getContext(), this.mData));
        gridView.setOnItemClickListener(this);
    }
}
