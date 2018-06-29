package com.horizonverticalviews.jade.library;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.horizonverticalviews.R;

import java.util.ArrayList;

public class HasIndicatorsHorizonVerticalView extends LinearLayout {

    private HorizonVerticalView3 horizonVerticalView;
    private TextView pageIndex;
    private ArrayList<ArrayList<String>> datas;
    private Context context;
    private RadioGroup radioGroup;
    private  ContactInterface  myCallBack;
    public HasIndicatorsHorizonVerticalView(Context context) {
        this(context, null);
    }

    public HasIndicatorsHorizonVerticalView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HasIndicatorsHorizonVerticalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        inflate(context, R.layout.has_indicators_view_layout, this);
        pageIndex = (TextView) findViewById(R.id.pager_index);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        horizonVerticalView =(HorizonVerticalView3) this.findViewById(R.id.horizontal_scrollview);
    }

    public HorizonVerticalView3 getHorizonVerticalView() {
        return horizonVerticalView;
    }
    public interface ContactInterface  {
        void updateWindow();
    }
    public void changeCurrent(ArrayList<String> columns,ContactInterface   myCallBack) {
        horizonVerticalView.changeCurrent(columns);
        generateIndicator(columns.size());
        myCallBack.updateWindow();
    }

    public void setCarousel(int replaceTimes, boolean isCarousel) {
        horizonVerticalView.setCarousel(replaceTimes, isCarousel);
    }

    public void stopCarousel() {
        horizonVerticalView.stopCarousel();
    }

    public void setPagerOnClickListener(HorizonVerticalView3.PagerOnClickListener pagerOnClickListener) {
        horizonVerticalView.setPagerOnClickListener(pagerOnClickListener);
    }

    public void initView(final ArrayList<ArrayList<String>> datas, int currentItem, boolean isLoaclImg) {
        this.datas = datas;

        if (datas.get(currentItem).size() <= 0) {
            radioGroup.setVisibility(View.GONE);
        } else {
            radioGroup.setVisibility(View.VISIBLE);
            generateIndicator(datas.get(currentItem).size());
        }
        horizonVerticalView.setCurrentLocationOnClickListener(new HorizonVerticalView3.CurrentLocationOnClickListener() {
            @Override
            public void currentLocation(int externalLocation, int innerLocation) {
                radioGroup.check(innerLocation);
            }

            @Override
            public void externalLocation(int externalLocation) {
                pageIndex.setText(externalLocation + 1 + "/" + datas.size());
                if (datas.get(externalLocation).size() <= 0) {
                    radioGroup.setVisibility(View.GONE);
                } else {
                    radioGroup.setVisibility(View.VISIBLE);
                    generateIndicator(datas.get(externalLocation).size());
                }

            }
        });
        horizonVerticalView.initView(context, datas, currentItem, isLoaclImg);
        pageIndex.setText(currentItem + 1 + "/" + datas.size());
    }

    private void generateIndicator(int size) {
        radioGroup.removeAllViews();
        if (size > 1) {
            int radius = DisplayUtil.getPxByDp(getContext(), 8);
            int margin = DisplayUtil.getPxByDp(getContext(), 3);
            for (int i = 0; i < size; i++) {
                RadioButton radioButton = new RadioButton(getContext());
                radioButton.setId(i);
                radioButton.setButtonDrawable(android.R.color.transparent);
                radioButton.setBackgroundResource(R.drawable.indicator_selector);
                radioButton.setClickable(false);
                RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(radius, radius);
                lp.setMargins(margin, margin, margin, margin);
                radioGroup.addView(radioButton, lp);
            }
            radioGroup.clearCheck();
            radioGroup.check(0);
        }
    }


}
