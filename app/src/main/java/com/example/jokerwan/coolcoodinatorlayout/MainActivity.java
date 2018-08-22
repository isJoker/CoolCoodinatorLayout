package com.example.jokerwan.coolcoodinatorlayout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.iv_left_btn)
    ImageView ivLeftHotBtn;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.iv_search_btn)
    ImageView ivSearchBtn;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.app_barlayout)
    AppBarLayout appBarlayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private ArrayList<Fragment> fragments;
    private HomeFragment homeFragment1;
    private HomeFragment homeFragment2;
    private String[] titles = new String[]{"恐怖电影", "喜剧专场"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Utils.setStatusBarTransparent(this);

        viewpager.clearDisappearingChildren();

        fragments = new ArrayList<>();
        List<Fragment> fragments1 = getSupportFragmentManager().getFragments();
        if (fragments1 != null && fragments1.size() > 1) {
            homeFragment1 = (HomeFragment) fragments1.get(0);
            homeFragment2 = (HomeFragment) fragments1.get(1);
        } else {
            homeFragment1 = new HomeFragment();
            homeFragment2 = new HomeFragment();
        }

        fragments.add(homeFragment1);
        fragments.add(homeFragment2);

        tabLayout.setViewPager(viewpager, titles,this,fragments);


        appBarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    rlTitle.setBackgroundColor(Color.argb(0, 221, 48, 10));
                    ivLeftHotBtn.setBackground(getResources().getDrawable(R.drawable.shap_left_btn_bg));
                    ivSearchBtn.setVisibility(View.VISIBLE);
                    ivSearchBtn.setBackground(getResources().getDrawable(R.drawable.shap_left_btn_bg));
                    llSearch.setVisibility(View.GONE);
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    rlTitle.setBackgroundColor(Color.argb(255, 221, 48, 10));
                    ivLeftHotBtn.setBackground(null);
                    llSearch.setVisibility(View.VISIBLE);
                    ivSearchBtn.setBackground(null);
                } else {
                    int alpha = (int) (255 - verticalOffset / (float) appBarLayout.getTotalScrollRange() * 255);
                    rlTitle.setBackgroundColor(Color.argb(alpha, 221, 48, 10));
                    llSearch.setVisibility(View.GONE);
                    ivSearchBtn.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
