package com.leo.impro.ui.chat.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leo.impro.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/**
 * Created by leo
 * Time：2019/1/22
 */
public class TestFragment extends Fragment {
    private int position;

    public static TestFragment newInstance(int position) {

        TestFragment fragment = new TestFragment();
        fragment.position = position;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_test, null);
        TextView text = view.findViewById(R.id.text);
        text.setText("页面 -- " + position);
        return view;
    }
}
