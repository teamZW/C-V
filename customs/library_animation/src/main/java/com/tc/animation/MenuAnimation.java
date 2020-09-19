package com.tc.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class MenuAnimation extends RelativeLayout implements View.OnClickListener {
    private Context mContext;
    private Button mMenuButton;
    private Button mItemButton1;
    private Button mItemButton2;
    private Button mItemButton3;
    private Button mItemButton4;
    private Button mItemButton5;
    private boolean mIsMenuOpen = false;

    public MenuAnimation(Context context) {
        super(context);
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.menu_layout, this, true);

        initView();
    }
    public MenuAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.menu_layout, this, true);

        initView();
    }
    private void initView() {
        mMenuButton = (Button) findViewById(R.id.menu);
        mMenuButton.setOnClickListener(this);

        mItemButton1 = (Button) findViewById(R.id.item1);
        mItemButton1.setOnClickListener(this);

        mItemButton2 = (Button) findViewById(R.id.item2);
        mItemButton2.setOnClickListener(this);

        mItemButton3 = (Button) findViewById(R.id.item3);
        mItemButton3.setOnClickListener(this);

        mItemButton4 = (Button) findViewById(R.id.item4);
        mItemButton4.setOnClickListener(this);

        mItemButton5 = (Button) findViewById(R.id.item5);
        mItemButton5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!mIsMenuOpen) {
            mIsMenuOpen = true;
            openMenu();
        } else {
            mIsMenuOpen = false;
            closeMenu();
        }
    }

    private void openMenu() {
        doAnimateOpen(mItemButton1, 0, 5, 600);
        doAnimateOpen(mItemButton2, 1, 5, 600);
        doAnimateOpen(mItemButton3, 2, 5, 600);
        doAnimateOpen(mItemButton4, 3, 5, 600);
        doAnimateOpen(mItemButton5, 4, 5, 600);
    }

    private void closeMenu() {
        doAnimateClose(mItemButton1, 0, 5, 600);
        doAnimateClose(mItemButton2, 1, 5, 600);
        doAnimateClose(mItemButton3, 2, 5, 600);
        doAnimateClose(mItemButton4, 3, 5, 600);
        doAnimateClose(mItemButton5, 4, 5, 600);
    }

    /**
     * 打开菜单的动画
     *
     * @param view   执行动画的view
     * @param index  view在动画序列中的顺序,从0开始
     * @param total  动画序列的个数
     * @param radius 动画半径
     *               <p/>
     *               Math.sin(x):x -- 为number类型的弧度，角度乘以0.017(2π/360)可以转变为弧度
     */
    private void doAnimateOpen(View view, int index, int total, int radius) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        double degree = Math.toRadians(90) / (total - 1) * index;
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));
        AnimatorSet set = new AnimatorSet();
        //包含平移、缩放和透明度动画
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", 0, translationX),
                ObjectAnimator.ofFloat(view, "translationY", 0, translationY),
                ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f),
                ObjectAnimator.ofFloat(view, "alpha", 0f, 1));
        //动画周期为500ms
        set.setDuration(500).start();
    }

    /**
     * 关闭菜单的动画
     *
     * @param view   执行动画的view
     * @param index  view在动画序列中的顺序
     * @param total  动画序列的个数
     * @param radius 动画半径
     */
    private void doAnimateClose(final View view, int index, int total,
                                int radius) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        double degree = Math.PI * index / ((total - 1) * 2);
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));
        AnimatorSet set = new AnimatorSet();
        //包含平移、缩放和透明度动画
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", translationX, 0),
                ObjectAnimator.ofFloat(view, "translationY", translationY, 0),
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.1f),
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.1f),
                ObjectAnimator.ofFloat(view, "alpha", 1f, 0f));


        /**
         * 解决方案二
         */
//        set.addListener(new Animator.AnimatorListener() {
//            public void onAnimationStart(Animator animation) {
//
//            }
//            public void onAnimationEnd(Animator animation) {
//                view.setScaleX(1.0f);
//                view.setScaleY(1.0f);
//
//            }
//
//            public void onAnimationCancel(Animator animation) {
//
//            }
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
        set.setDuration(500).start();
    }
}
