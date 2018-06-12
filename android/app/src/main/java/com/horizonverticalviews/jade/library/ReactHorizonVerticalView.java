package com.horizonverticalviews.jade.library;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

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
    private int Index = 0;
    private int currIndex = 0;
    private int ScrollTimes = 0;
    private ThemedReactContext reactContexts;

    @Override
    public String getName() {
        return "RCTAndroidHorizonVerticalView";
    }

    @Override
    protected HasIndicatorsHorizonVerticalView createViewInstance(final ThemedReactContext reactContext) {
//        rootView = (HasIndicatorsHorizonVerticalView)LayoutInflater.from(reactContext).inflate(R.layout.has_indicators_view_layout,null);
        rootView = new HasIndicatorsHorizonVerticalView(reactContext);
        reactContexts = reactContext;
        return rootView;
    }

    @ReactProp(name = "dataList", customType = "ReadableArray")
    public void setDataList(final HasIndicatorsHorizonVerticalView contentx, ReadableArray dataList) {

        final TextView pageIndex = (TextView) contentx.findViewById(R.id.pager_index);
        list = dataList.toArrayList();
        FragmentActivity activity = (FragmentActivity) reactContexts.getCurrentActivity();
        if (fragmentManager == null) {
            fragmentManager = activity.getSupportFragmentManager();
        }
        rootView.getHorizonVerticalView().setPagerOnClickListener(new HorizonVerticalView.PagerOnClickListener() {
            @Override
            public void onPress(View v, int externalLocation, int innerLocation) {
                //监听点击事件
                WritableMap map = Arguments.createMap();
                map.putString("name", "onPagePress");
                sendEvent(reactContexts, "onPagePress", map);
            }
        });
        rootView.getHorizonVerticalView().setCurrentLocationOnClickListener(new HorizonVerticalView.CurrentLocationOnClickListener() {
            @Override
            public void currentLocation(int externalLocation, int innerLocation) {
                //监听滑动事件
                //执行事件
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
//                ReactHorizonVerticalView.super.updateExtraData(rootView,list);
                currIndex = externalLocation;
            }
        });
        contentx.initView(fragmentManager, list, Index, false);
    }

    @ReactProp(name = "defaultIndex")
    public void setDefaultIndex(final HasIndicatorsHorizonVerticalView contentx, int defaultIndex) {
        if (Index == 0) currIndex = defaultIndex;
        Index = defaultIndex;
    }

    //事件发送
    void sendEvent(ThemedReactContext reactContext, String eventName, @Nullable WritableMap params) {
        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }

    //
    @Override
    public void onDropViewInstance(HasIndicatorsHorizonVerticalView view) {
        super.onDropViewInstance(view);
    }

    @Override
    public @Nullable
    Map<String, Integer> getCommandsMap() {
        return MapBuilder.of(
                "changeCurrent", 1,
                "AotuScroll", 2
        );
    }

    @Override
    public void receiveCommand(HasIndicatorsHorizonVerticalView view, int commandId, @Nullable ReadableArray args) {
        switch (commandId) {
            case 1:
                ArrayList datas = args.toArrayList();
                view.changeCurrent(datas);
//                ReactHorizonVerticalView.super.onAfterUpdateTransaction(rootView);
//                WindowManager m =   reactContexts.getCurrentActivity().getWindowManager();
                break;
            case 2:
                //z暂时不添加自动播放功能
                break;
        }
    }

    @Override
    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    @Override
    protected void onAfterUpdateTransaction(HasIndicatorsHorizonVerticalView view) {
        super.onAfterUpdateTransaction(view);
    }


//    @Override
//    protected void addEventEmitters(ThemedReactContext reactContext, HasIndicatorsHorizonVerticalView view) {
//        // Do not register default touch emitter and let WebView implementation handle touches
//    }
//    public void onReceiveNativeEvent() {
//        WritableMap event = Arguments.createMap();
//        event.putString("message", "MyMessage");
//
//        ReactContext reactContext = (ReactContext)rootView.getContext();
//        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
//                rootView.getId(),
//                "topChange",
//                event);
//    }


//, final Callback callback

//    @Override
//    public boolean shouldPromoteGrandchildren() {
//        return true;
//    }
}
