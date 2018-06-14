package com.horizonverticalviews.jade.library;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.horizonverticalviews.R;

import java.util.ArrayList;

public class HasIndicatorsHorizonVerticalView extends LinearLayout {

    private HorizonVerticalView horizonVerticalView;
    private TextView pageIndex;
    private ArrayList<ArrayList<String>> datas;
    private FragmentManager fragmentManager;
    private  ContactInterface  myCallBack;


    public HasIndicatorsHorizonVerticalView(Context context) {
        this(context, null);
    }

    public HasIndicatorsHorizonVerticalView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public HasIndicatorsHorizonVerticalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.has_indicators_view_layout, this);
        pageIndex = (TextView) findViewById(R.id.pager_index);
        horizonVerticalView = (HorizonVerticalView) this.findViewById(R.id.horizontal_scrollview);
    }

    public HorizonVerticalView getHorizonVerticalView() {
        return horizonVerticalView;
    }


    public interface ContactInterface  {
        void updateWindow();
    }
    public void changeCurrent(ArrayList<String> columns,ContactInterface   myCallBack) {//,myCallBack  mycallback
        horizonVerticalView.changeCurrent(columns);
        datas.set(horizonVerticalView.getExternalLocationIndex(), columns);
        myCallBack.updateWindow();
    }

    public void initView(FragmentManager fragmentManager, final ArrayList<ArrayList<String>> datas, int currentItem, boolean isLoaclImg) {
        this.fragmentManager = fragmentManager;
        this.datas = datas;
//        horizonVerticalView.setCurrentLocationOnClickListener(new HorizonVerticalView.CurrentLocationOnClickListener() {
//            @Override
//            public void currentLocation(int externalLocation, int innerLocation) {
//                pageIndex.setText(externalLocation + 1 + "/" + datas.size());
//            }
//
//            @Override
//            public void externalLocation(int externalLocation) {
//            }
//
//        });
        horizonVerticalView.initView(fragmentManager, datas, currentItem, isLoaclImg);
        pageIndex.setText(currentItem + 1 + "/" + datas.size());
    }

    //    @Override
//    protected void onAttachedToWindow() {
//        super.onAttachedToWindow();
////        if (mRemoveClippedSubviews) {
////            updateClippingRect();
////        }
//    }
    public void removeFragment() {
        Fragment pdfFragment = (Fragment) fragmentManager.findFragmentByTag("");
    }
}
