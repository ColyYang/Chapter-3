package com.example.chapter3.homework;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.ClipData;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

public class PlaceholderFragment extends Fragment {

    private LottieAnimationView lottieAnimationView;
    private ListView listView;

    private ArrayAdapter<Item> adapterItems;

    private AnimatorSet animatorSet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create arraylist from item fixtures
        ArrayList<Item> items = Item.getItems();
        adapterItems = new ArrayAdapter<Item>(getActivity(),
                R.layout.list_item, items);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件


        View view = inflater.inflate(R.layout.fragment_placeholder, container,
                false);

        listView = (ListView) view.findViewById(R.id.list_view);
        lottieAnimationView = (LottieAnimationView)view.findViewById(R.id.animation_view);

        listView.setAdapter(adapterItems);
        listView.setAlpha(0.0f);
        lottieAnimationView.setAlpha(1.0f);
        return view;

 //       return inflater.inflate(R.layout.fragment_placeholder, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入

                ObjectAnimator LoAlphaAnimator =  ObjectAnimator.ofFloat(lottieAnimationView,
                        "alpha", 1.0f, 0.0f);
                ObjectAnimator LiAlphaAnimator = ObjectAnimator.ofFloat(listView,
                        "alpha", 0.0f, 1.0f);


                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(LoAlphaAnimator, LiAlphaAnimator);
                animatorSet.start();


            }
        }, 5000);
    }
}
