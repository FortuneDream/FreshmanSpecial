package com.excitingboat.freshmanspecial.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.excitingboat.freshmanspecial.model.bean.Person;
import com.excitingboat.freshmanspecial.model.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PinkD on 2016/8/4.
 * BigDataFragment_2
 */
public class BigDataFragment_2 extends Fragment{
    private List<Person> persons;

    public BigDataFragment_2() {
        persons = new ArrayList<>();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        view.findViewById(R.id)
    }

}
