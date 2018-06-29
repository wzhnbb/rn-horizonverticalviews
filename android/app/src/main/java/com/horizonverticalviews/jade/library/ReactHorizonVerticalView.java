package com.horizonverticalviews.jade.library;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactFragmentActivity;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.horizonverticalviews.R;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Nullable;

//java.security.AccessControlContext

/**
 * Created by Administrator on 2018/5/28.
 */
public class ReactHorizonVerticalView extends ViewGroupManager<HasIndicatorsHorizonVerticalView> {

    private HasIndicatorsHorizonVerticalView rootView = null;
    private FragmentManager fragmentManager = null;
    private ArrayList list = null;
    private ArrayList<ArrayList<String>>tempList = null;
    private int Index = -1;
    private int currIndex = 0;
    private int ScrollTimes = 0;
    private ThemedReactContext reactContexts;
    private Activity activity;
    private  int ChangeTimes = 0;
    private  boolean isNeedLoad = false;
    private RadioGroup radioGroup;
    @Override
    public String getName() {
        return "RCTAndroidHorizonVerticalView";
    }

    @Override
    protected HasIndicatorsHorizonVerticalView createViewInstance(final ThemedReactContext reactContext) {
//        rootView = (HasIndicatorsHorizonVerticalView)LayoutInflater.from(reactContext).inflate(R.layout.has_indicators_view_layout,null);
        rootView = new HasIndicatorsHorizonVerticalView(reactContext.getCurrentActivity());
        reactContexts = reactContext;
        isNeedLoad = true;
        list = null;
        activity = reactContexts.getCurrentActivity();

        return rootView;
    }
    @ReactProp(name = "dataList", customType = "ReadableArray")
    public void setDataList(final HasIndicatorsHorizonVerticalView contentx, ReadableArray dataList) {
        final TextView pageIndex = (TextView) contentx.findViewById(R.id.pager_index);
//        radioGroup = (RadioGroup) contentx.findViewById(R.id.radiogroup);
        list = dataList.toArrayList();
        tempList = list;
        rootView.getHorizonVerticalView().setPagerOnClickListener(new HorizonVerticalView3.PagerOnClickListener() {
            @Override
            public void onPress(View v, int externalLocation, int innerLocation) {
                //监听点击事件
                WritableMap map = Arguments.createMap();
                map.putString("name", "onPagePress");
                sendEvent(reactContexts, "onPagePress", map);
            }
        });
        rootView.getHorizonVerticalView().setCurrentLocationOnClickListener(new HorizonVerticalView3.CurrentLocationOnClickListener() {
            @Override
            public void currentLocation(int externalLocation, int innerLocation) {
                //监听滑动事件
                //执行事件
//                radioGroup.check(innerLocation);
                if (currIndex != externalLocation && ScrollTimes > 0) {
                    WritableMap map = Arguments.createMap();
                    map.putString("name", "ontHorizonPageScroll");
                    map.putInt("HorizonIndex", externalLocation);
                    map.putInt("verticalIndex", innerLocation);
                    sendEvent(reactContexts, "ontHorizonPageScroll", map);
                    currIndex = externalLocation;
                }
                if (ScrollTimes == 0) ScrollTimes = 1;
            }
            @Override
            public void externalLocation(int externalLocation) {
                pageIndex.setText(externalLocation + 1 + "/" + list.size());
//               ReactHorizonVerticalView.super.updateExtraData(rootView,list);
//                if (tempList.get(externalLocation).size() <= 0) {
//                    radioGroup.setVisibility(View.GONE);
//                } else {
//                    radioGroup.setVisibility(View.VISIBLE);
//                    rootView.generateIndicator(tempList.get(externalLocation).size());
//                }
                currIndex = externalLocation;
            }
         });
        if (Index>=0&&isNeedLoad){
            contentx.initView(list, Index, false);
            isNeedLoad = false;
        }
    }

    @ReactProp(name = "defaultIndex")
    public void setDefaultIndex(final HasIndicatorsHorizonVerticalView contentx, int defaultIndex) {
        if (Index == 0) currIndex = defaultIndex;
        Index = defaultIndex;
        if (list!=null&&list.size()>0&&isNeedLoad){
            contentx.initView(list, defaultIndex, false);
            isNeedLoad = false;
        }
    }

    //事件发送
    void sendEvent(ThemedReactContext reactContext, String eventName, @Nullable WritableMap params) {
        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }

    @Override
    public @Nullable
    Map<String, Integer> getCommandsMap() {
        return MapBuilder.of(
                "changeCurrent", 1,
                "AotuScroll", 2,
                "StopScroll",3
        );
    }
    @Override
    public void receiveCommand( final HasIndicatorsHorizonVerticalView view, int commandId, @Nullable ReadableArray args) {
        switch (commandId) {
            case 1:
                ArrayList datas = args.toArrayList();
                view.changeCurrent(datas,new HasIndicatorsHorizonVerticalView.ContactInterface(){
                    @Override
                    public void updateWindow() {
                        int add = 0;
                        isNeedLoad = false;
                        if (ChangeTimes%2==0) add=1;else add=-1;
                        Window w = reactContexts.getCurrentActivity().getWindow();
                        w.setLayout(DisplayUtil.getScreenWidth(activity)+add,DisplayUtil.getScreenHeight(activity));
                        ChangeTimes++;
                    }
                });
                break;
            case 2://开始自动播放 传入值为 间隔时间 （毫秒）
                ArrayList times = args.toArrayList();
                if (times!=null&&times.size()>0){
                    String timeStr = times.get(0).toString();
                    int time =Integer.parseInt(timeStr);
                    view.setCarousel(time,true);
                }
                break;
            case 3:
                view.stopCarousel();
                break;
        }
    }

    @Override
    public boolean needsCustomLayoutForChildren() {
        return false;
    }
}
