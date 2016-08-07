package com.excitingboat.freshmanspecial.view.fragment.BigData;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.excitingboat.freshmanspecial.R;
import com.excitingboat.freshmanspecial.config.Data;
import com.excitingboat.freshmanspecial.presenter.GetInformationPresenter;
import com.excitingboat.freshmanspecial.view.iview.IGetInformation;
import com.excitingboat.yellowcake.Yellowcake;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by PinkD on 2016/8/4.
 * BigDataFragmentCake
 */
public class BigDataFragmentCake extends Fragment implements IGetInformation<String> {
    private static final String TAG = "BigDataFragmentCake";
    private List<String> name1;
    private List<String> name2;
    private Spinner spinner1;
    private Spinner spinner2;
    private Yellowcake yellowcake;
    private ArrayAdapter<String> arrayAdapter2;
    private GetInformationPresenter<String> presenter;

    public BigDataFragmentCake() {
        name1 = new ArrayList<>();
        name2 = new ArrayList<>();
        Collections.addAll(name1, Data.school);
        presenter = new GetInformationPresenter<>(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bd_cake, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinner1 = (Spinner) view.findViewById(R.id.spinner_1);
        spinner2 = (Spinner) view.findViewById(R.id.spinner_2);
        yellowcake = (Yellowcake) view.findViewById(R.id.cake);

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(getContext(), R.layout.spinner, name1);
        arrayAdapter2 = new ArrayAdapter<>(getContext(), R.layout.spinner, name2);
        arrayAdapter1.notifyDataSetChanged();
        spinner1.setAdapter(arrayAdapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemSelected: position:" + position);
                resetName2();
                if (position == 0) {
                    return;
                }
                Collections.addAll(name2, Data.major[position - 1]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        spinner2.setAdapter(arrayAdapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinner1.getSelectedItemId() > 0) {
                    //TODO 获取信息，处理
//                    presenter.getInformation(name2.get(position));
                    yellowcake.setData(new int[]{123, 321}, new int[]{0xFF66CCFF, 0xFFEE82EE});
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        resetName2();
    }


    @Override
    public void requestSuccess(List<String> list) {

    }

    @Override
    public void requestFail() {
    }

    private void resetName2() {
        name2.clear();
        name2.add("请选择专业");
        arrayAdapter2.notifyDataSetChanged();
        spinner2.setSelection(0);
    }
}
