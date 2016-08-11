package com.excitingboat.freshmanspecial.view.fragment.Style;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.excitingboat.freshmanspecial.R;
import com.excitingboat.freshmanspecial.config.StyleData;

/**
 * Created by PinkD on 2016/8/9.
 * OrganizationFragment
 */
public class OrganizationFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "OrganizationFragment";
    private TextView detail;
    private TextView[] textViews;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        return inflater.inflate(R.layout.fragment_style_organization, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        textViews = new TextView[]{(TextView) view.findViewById(R.id.org_text_1),
                (TextView) view.findViewById(R.id.org_text_2),
                (TextView) view.findViewById(R.id.org_text_3),
                (TextView) view.findViewById(R.id.org_text_4),
                (TextView) view.findViewById(R.id.org_text_5),
                (TextView) view.findViewById(R.id.org_text_6)
        };
        detail = (TextView) view.findViewById(R.id.org_detail);
        for (TextView textView : textViews) {
            textView.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        for (TextView textView : textViews) {
            textView.setBackgroundResource(R.color.none);
        }
        v.setBackgroundResource(R.color.pressed);
        switch (v.getId()) {
            case R.id.org_text_1:
                detail.setText(Html.fromHtml(StyleData.ORGANIZATION[0]));
                break;
            case R.id.org_text_2:
                detail.setText(Html.fromHtml(StyleData.ORGANIZATION[1]));
                break;
            case R.id.org_text_3:
                detail.setText(Html.fromHtml(StyleData.ORGANIZATION[2]));
                break;
            case R.id.org_text_4:
                detail.setText(Html.fromHtml(StyleData.ORGANIZATION[3]));
                break;
            case R.id.org_text_5:
                detail.setText(Html.fromHtml(StyleData.ORGANIZATION[4]));
                break;
            case R.id.org_text_6:
                detail.setText(Html.fromHtml(StyleData.ORGANIZATION[5]));
                break;
        }

    }
}
