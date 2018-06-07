package com.horizonverticalviews.jade.library;

import android.app.Activity;
import android.view.View;

import com.facebook.react.ReactFragmentActivity;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.horizonverticalviews.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/6/1.
 */

public  class HorizonVerticalViewEvent extends ReactContextBaseJavaModule  implements NativeModule {
private  ReactApplicationContext reactContexts ;
    public HorizonVerticalViewEvent(ReactApplicationContext reactContext) {
        super(reactContext);

        reactContexts = reactContext;
//        reactContext.getNativeModule(DeviceEventManagerModule.class);
    }

    @Override
    public String getName() {
        return "HorizonVerticalViewEventModule";
    }
    @ReactMethod
    public  void changeCurrent( ReadableArray data){
        ArrayList datas = data.toArrayList();
        //(HorizonVerticalView)getCurrentActivity().getCurrentFocus();  (HorizonVerticalView)getCurrentActivity().findViewById(com.facebook.react.R.id.);
        HorizonVerticalView rootView = (HorizonVerticalView)reactContexts.getCurrentActivity().getCurrentFocus();
        ReactFragmentActivity activity = (ReactFragmentActivity)getCurrentActivity();
//        activity.findViewById(R.id.horizontal_scrollview)
//        View rootviesw = activity.getWindow().getCurrentFocus();
//        View aaa = rootviesw.findFocus();
       View view =    activity.getCurrentFocus();
        System.out.println("===================>"+view);
        rootView.changeCurrent(datas);
//        HorizonVerticalView rootView =  (HorizonVerticalView) this.rootView.getBaseContext();
    }
}
