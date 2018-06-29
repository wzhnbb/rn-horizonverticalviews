package com.horizonverticalviews.jade.library;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class HorizonVerticalView2 extends MyViewPager {

    private PagerOnClickListener pagerOnClickListener;
    private int currentIndex = 0;
    private ArrayList<ItemFragment> fragmentArrayList;
    private MyAdapter myAdapter;
    private int externalLocationIndex;
    private CurrentLocationOnClickListener currentLocationOnClickListener;
    private boolean isLoaclImg;


    public HorizonVerticalView2(Context context) {
        this(context, null);
    }

    public HorizonVerticalView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void initView(FragmentManager fragmentManager, final ArrayList<ArrayList<String>> datas, int currentItem, boolean isLoaclImg) {
        this.isLoaclImg = isLoaclImg;
        fragmentArrayList = new ArrayList<>();
        for (ArrayList<String> item : datas) {
            //            Bundle bundle = new Bundle();
            //            bundle.putStringArrayList("IMAGE_DATA", item);
            //            bundle.putBoolean("IS_LOACL_IMG", isLoaclImg);
            ItemFragment itemFragment = new ItemFragment();
            itemFragment.setData(isLoaclImg, item);
//            itemFragment.setArguments(bundle);
            fragmentArrayList.add(itemFragment);
        }
        myAdapter = new MyAdapter(fragmentManager, datas/* fragmentArrayList*/);
        this.setAdapter(myAdapter);
        this.setCurrentItem(currentItem);
        externalLocationIndex = currentItem;
        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                externalLocationIndex = position;
                ItemFragment itemFragment = (ItemFragment) myAdapter.getItem(position);
                itemFragment.setExternalLocationIndex(position);
                if (currentLocationOnClickListener != null) {
                    currentLocationOnClickListener.currentLocation(externalLocationIndex, fragmentArrayList.get(externalLocationIndex).getCurrentImgLocation());
                    currentLocationOnClickListener.externalLocation(externalLocationIndex);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private class MyAdapter extends FragmentPagerAdapter {
        private ArrayList<ArrayList<String>> datas;
        private ArrayList<String> updataDatas;

        public MyAdapter(FragmentManager fm, ArrayList<ArrayList<String>> datas) {
            super(fm);
            this.datas = datas;
        }

        public void updataData(int index, ArrayList<String> data) {
            this.updataDatas = data;
            notifyDataSetChanged();
        }

        @Override
        public Fragment getItem(int position) {
            ItemFragment itemFragment = new ItemFragment();
            itemFragment.setData(isLoaclImg, datas.get(position));
            return itemFragment;
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ItemFragment itemFragment = (ItemFragment) super.instantiateItem(container, position);
            if (position == 0) {
                itemFragment.setExternalLocationIndex(position);
                currentLocationOnClickListener.currentLocation(externalLocationIndex, fragmentArrayList.get(position).getCurrentImgLocation());
            }
            if (updataDatas != null) {
                itemFragment.setData(isLoaclImg, updataDatas);
                updataDatas = null;
            } else {
                itemFragment.setData(isLoaclImg, datas.get(position));
            }
            return itemFragment;
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }


    }

    public interface CurrentLocationOnClickListener {
        void currentLocation(int externalLocation, int innerLocation);

        void externalLocation(int externalLocation);
    }

    public void setCurrentLocationOnClickListener(CurrentLocationOnClickListener currentLocationOnClickListener) {
        this.currentLocationOnClickListener = currentLocationOnClickListener;
    }

    public interface PagerOnClickListener {
        void onPress(View v, int externalLocation, int innerLocation);
    }

    public void setPagerOnClickListener(PagerOnClickListener pagerOnClickListener) {
        this.pagerOnClickListener = pagerOnClickListener;
    }

    /**
     * 更新当前位置列显示列表
     *
     * @param columns
     */
    public void changeCurrent(ArrayList<String> columns) {
        myAdapter.updataData(externalLocationIndex, columns);

    }

    /**
     * 更新指定位置列显示列表
     *
     * @param location
     * @param columns
     */
    public void changeCurrent(int location, ArrayList<String> columns) {
        myAdapter.updataData(location, columns);
    }

    public int getExternalLocationIndex() {
        return externalLocationIndex;
    }
}
