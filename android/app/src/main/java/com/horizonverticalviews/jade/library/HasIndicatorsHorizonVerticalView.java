package com.horizonverticalviews.jade.library;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.horizonverticalviews.R;

import java.util.ArrayList;

public class HasIndicatorsHorizonVerticalView extends LinearLayout {

    private HorizonVerticalView horizonVerticalView;
    private TextView pageIndex;
    private ArrayList<ArrayList<String>> datas;
    private  FragmentManager fragmentManager;
    public HasIndicatorsHorizonVerticalView(Context context) {
        this(context, null);
    }

    public HasIndicatorsHorizonVerticalView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HasIndicatorsHorizonVerticalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.has_indicators_view_layout, this);
        pageIndex = (TextView)findViewById(R.id.pager_index);
        horizonVerticalView =(HorizonVerticalView) this.findViewById(R.id.horizontal_scrollview);
    }

    public HorizonVerticalView getHorizonVerticalView() {
        return horizonVerticalView;
    }

    public void changeCurrent(ArrayList<String> columns) {
        horizonVerticalView.changeCurrent(columns);
        datas.set(horizonVerticalView.getExternalLocationIndex(), columns);
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
    public  void  removeFragment(){
        Fragment pdfFragment = (Fragment) fragmentManager.findFragmentByTag("");
    }
//    @Override
//    public void requestLayout() {
//        super.requestLayout();
//        post(measureAndLayout);
//    }
//
//    private final Runnable measureAndLayout = new Runnable() {
//        @Override
//        public void run() {
//            measure(
//                    MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.EXACTLY),
//                    MeasureSpec.makeMeasureSpec(getHeight(), MeasureSpec.EXACTLY));
//            layout(getLeft(), getTop(), getRight(), getBottom());
//        }
//    };
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       super.onMeasure(widthMeasureSpec,heightMeasureSpec);
    }
}