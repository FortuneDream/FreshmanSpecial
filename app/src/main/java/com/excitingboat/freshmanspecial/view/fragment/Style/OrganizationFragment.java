package com.excitingboat.freshmanspecial.view.fragment.Style;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.excitingboat.freshmanspecial.R;

/**
 * Created by PinkD on 2016/8/9.
 * OrganizationFragment
 */
public class OrganizationFragment extends Fragment {
    private static final String TAG = "OrganizationFragment";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        return inflater.inflate(R.layout.fragment_style_organization, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }
}
