# CoolCoodinatorLayout
 banner沉浸式+滚动视差+悬浮搜索框+标题置顶复杂联动效果


1.开门见上，先上效果图
                 

                                        代码传送门

2.CoodinatorLayout及相关控件简介
      CoordinatorLayout遵循Material Design风格，包含在 support Library中，结合AppBarLayout, CollapsingToolbarLayout,NestScrollView等 可 产生各种炫酷的折叠悬浮效果。作为一个容器可与一个或多个子View进行交互。

      AppBarLayout继承自LinearLayout，布局方向为垂直方向。所以你可以把它当成垂直布局的LinearLayout来使用。AppBarLayout是在LinearLayou上加了一些材料设计的概念，它可以让你定制当某个可滚动View的滚动手势发生变化时，其内部的子View实现何种动作。

       CollapsingToolbarLayout是用来对Toolbar进行再次包装的ViewGroup，主要是用于实现折叠的App Bar效果。它需要放在AppBarLayout布局里面，并且作为AppBarLayout的直接子View。CollapsingToolbarLayout主要属性：

       (1) layout_scrollFlags：

scroll - 想滚动就必须设置这个。

enterAlways - 实现quick return效果, 当向下移动时，立即显示View（比如Toolbar)。

exitUntilCollapsed - 向上滚动时收缩View，但可以固定Toolbar一直在上面。

enterAlwaysCollapsed - 当你的View已经设置minHeight属性又使用此标志时，你的View只能以最小高度进入，只有当滚动视图到达顶部时才扩大到完整高度。

contentScrim - 设置当完全CollapsingToolbarLayout折叠(收缩)后的背景颜色。

expandedTitleMarginStart - 设置扩张时候(还没有收缩时)title向左填充的距离。

        (2) layout_collapseMode (折叠模式) - 有两个值:

pin -  设置为这个模式时，当CollapsingToolbarLayout完全收缩后，Toolbar还可以保留在屏幕上。

parallax - 设置为这个模式时，在内容滚动时，CollapsingToolbarLayout中的View（比如ImageView)也可以同时滚动，实现视差滚动效果，通常和layout_collapseParallaxMultiplier(设置视差因子)搭配使用。

        (3) layout_collapseParallaxMultiplier(视差因子) - 设置视差滚动因子，值为：0~1。

        Toolbar主要是用来替换ActionBar的，换句话说，ActionBar能做的，Toolbar都能做。如果你对ActionBar的使用比较熟悉，你会发现Toolbar使用起来非常简单。ok，既然是替换，当然用Toolbar的时候就得先去把ActionBar给隐藏掉。隐藏掉ActionBar方法有多种，我是直接在application的主题里面设置成<style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar”>

        NestedScrollView是support-v4包提供的控件,继承至FrameLayout, 并实现了NestedScrollingParent,NestedScrollingChild, ScrollingView接口。它的作用类似于android.widget.ScrollView,不同点在于NestedScrollView支持嵌套滑动，常用于内部嵌套RecyclerView一起使用。

3.布局文件实现
先看看布局视图的结构，如下图：



接下看布局代码的实现：

<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/cl_content"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <android.support.design.widget.AppBarLayout
        android:id="@+id/app_barlayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <android.support.design.widget.CollapsingToolbarLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:contentScrim="@android:color/transparent"
            app:layout_scrollFlags="scroll|exitUntilCollapsed">

            <com.youth.banner.Banner
                android:id="@+id/banner"
                android:layout_width="match_parent"
                android:layout_height="220dp"
                android:fitsSystemWindows="true"
                app:image_scale_type="fit_xy"
                app:layout_collapseMode="parallax"
                android:background="@drawable/banner_default"/>

            <android.support.v7.widget.Toolbar
                android:id="@+id/toolbar"
                android:layout_width="match_parent"
                android:layout_height="65dp"
                app:contentInsetStart="0dp"
                app:layout_collapseMode="pin">

                <RelativeLayout
                    android:id="@+id/rl_title"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:background="@android:color/transparent"
                    android:gravity="center_vertical"
                    android:orientation="horizontal"
                    android:paddingTop="25dp">

                    <ImageView
                        android:id="@+id/iv_left_btn"
                        android:layout_width="30dp"
                        android:layout_height="30dp"
                        android:layout_centerVertical="true"
                        android:layout_marginStart="7dp"
                        android:background="@drawable/shap_left_btn_bg"
                        android:paddingBottom="9dp"
                        android:paddingEnd="5dp"
                        android:paddingStart="5dp"
                        android:paddingTop="9dp"
                        android:src="@drawable/left_hot_btn" />

                    <LinearLayout
                        android:id="@+id/ll_search"
                        android:layout_width="match_parent"
                        android:layout_height="match_parent"
                        android:layout_centerVertical="true"
                        android:layout_marginBottom="7dp"
                        android:layout_marginEnd="15dp"
                        android:layout_marginStart="15dp"
                        android:layout_marginTop="7dp"
                        android:layout_toEndOf="@+id/iv_left_btn"
                        android:layout_toStartOf="@+id/iv_search_btn"
                        android:background="@drawable/search_bg_shap_home"
                        android:gravity="center"
                        android:orientation="horizontal"
                        android:visibility="visible">

                        <ImageView
                            android:layout_width="wrap_content"
                            android:layout_height="wrap_content"
                            android:layout_marginEnd="5dp"
                            android:layout_marginStart="3dp"
                            android:paddingBottom="7dp"
                            android:paddingTop="7dp"
                            android:src="@drawable/search_gray" />

                        <TextView
                            android:id="@+id/tv_search_hint"
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            android:layout_marginStart="8dp"
                            android:background="@null"
                            android:drawablePadding="4dp"
                            android:lines="1"
                            android:singleLine="true"
                            android:textColor="#999999"
                            android:textSize="14sp" />

                    </LinearLayout>

                    <ImageView
                        android:id="@+id/iv_search_btn"
                        android:layout_width="30dp"
                        android:layout_height="30dp"
                        android:layout_alignParentEnd="true"
                        android:layout_centerVertical="true"
                        android:layout_marginEnd="7dp"
                        android:background="@drawable/shap_left_btn_bg"
                        android:paddingBottom="6dp"
                        android:paddingEnd="5dp"
                        android:paddingStart="5dp"
                        android:paddingTop="6dp"
                        android:src="@drawable/icon_search_white" />

                </RelativeLayout>
            </android.support.v7.widget.Toolbar>
        </android.support.design.widget.CollapsingToolbarLayout>

        <com.flyco.tablayout.SlidingTabLayout
            android:id="@+id/tab_layout"
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:background="@android:color/white"
            app:tl_indicator_color="@android:color/holo_red_light"
            app:tl_indicator_corner_radius="30dp"
            app:tl_indicator_height="3dp"
            app:tl_indicator_width_equal_title="true"
            app:tl_tab_padding="25dp"
            app:tl_tab_space_equal="true"
            app:tl_textBold="SELECT"
            app:tl_textSelectColor="@android:color/holo_red_light"
            app:tl_textUnselectColor="@android:color/black"
            app:tl_textsize="17sp" />

    </android.support.design.widget.AppBarLayout>

    <android.support.v4.widget.NestedScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_gravity="fill_vertical"
        android:fillViewport="true"
        android:focusableInTouchMode="true"
        app:layout_behavior="@string/appbar_scrolling_view_behavior">

        <android.support.v4.view.ViewPager
            android:id="@+id/viewpager"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />

    </android.support.v4.widget.NestedScrollView>
</android.support.design.widget.CoordinatorLayout>
布局中有几个点需要注意下

CollapsingToolbarLayout要设置app:layout_scrollFlags="scroll|exitUntilCollapsed”，scroll是让CollapsingToolbarLayout可折叠，exitUntilCollapsed是向上滚动时收缩view，但可以将Toolbar一直固定在顶部

Toolbar一直固定在顶部需要给Toolbar加上app:layout_collapseMode=“pin”属性

当折叠的时候让banner出现滚动视差需要设置app:layout_collapseMode="parallax"

我们这里用到了NestedScrollView嵌套ViewPager，ViewPager嵌套RecyclerView，这里需要注意，不管是NestedScrollView嵌套ViewPager还是嵌套RecyclerView，都需要设置android:fillViewport=“true”，否则会出现内容不显示或者内容显示不全

接下来看MainActivity中的代码：

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
        关于滑动改变搜索框的透明度和按钮的变化，起初我想的是通过监听CollapsingToolbarLayout的收缩来做，结果发现CollapsingToolbarLayout并没有提供监听收缩的方法，尴尬了，后来发现AppBarLayout有一个偏移量监听的方法addOnOffsetChangedListener()，符合我们的需求。对了，差点儿忘了banner沉浸式，在MainActivity中可以看到我将浸式状态栏的代码封装到Utils中，代码给大家贴出来。

public static void setStatusBarTransparent(Activity activity){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = activity.getWindow();

            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            window.setStatusBarColor(Color.TRANSPARENT);

        } else {

            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }

    }

好了，大功告成。

