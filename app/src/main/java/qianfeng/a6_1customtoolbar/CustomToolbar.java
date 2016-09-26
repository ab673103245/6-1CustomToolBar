package qianfeng.a6_1customtoolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/9/26 0026.
 */
public class CustomToolbar extends RelativeLayout {

    private Drawable leftDrawable;
    private Drawable rightDrawable;
    private int titleColor = Color.BLACK;
    private int titleSize = 12;
    private String titleText;

    private ImageView leftIv;
    private ImageView rightIv;
    private TextView tv;

    public CustomToolbar(Context context) {
        this(context,null);
    }

    public CustomToolbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomToolbar);// 选用第三个构造方法，注意在View里面可以直接有Context，而不必去get

        leftDrawable = ta.getDrawable(R.styleable.CustomToolbar_leftImg);
        rightDrawable = ta.getDrawable(R.styleable.CustomToolbar_rightImg);
        titleColor = ta.getColor(R.styleable.CustomToolbar_titleColor,titleColor);
        titleSize = ta.getDimensionPixelSize(R.styleable.CustomToolbar_titleSize,titleSize); // xml里面的sp，dp等，都在java代码里转换为px
        titleText = ta.getString(R.styleable.CustomToolbar_titleText);
        ta.recycle(); // 注意回收ta

        // 需要两个ImageView接收用户设置的Drawable
        leftIv = new ImageView(context);
        leftIv.setImageDrawable(leftDrawable);
        LayoutParams leftLp = new LayoutParams((int)TypedValue.applyDimension((int)TypedValue.COMPLEX_UNIT_DIP,48,getResources().getDisplayMetrics()),
                (int)(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,48,getResources().getDisplayMetrics())));
        leftLp.addRule(ALIGN_PARENT_LEFT,TRUE); // 添加规则，其实就是添加在RelativeLayout布局里面的align_parent_bottom,第二个参数是这个规则的值，写TRUE

        addView(leftIv,leftLp); // 注意是选用两个参数的addView

        rightIv = new ImageView(context);
        rightIv.setImageDrawable(rightDrawable);
        LayoutParams rightLp = new LayoutParams((int) TypedValue.applyDimension((int) TypedValue.COMPLEX_UNIT_DIP, 48, getResources().getDisplayMetrics()),
                (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, getResources().getDisplayMetrics())));
        rightLp.addRule(ALIGN_PARENT_RIGHT, TRUE); // 添加规则，其实就是添加在RelativeLayout布局里面的align_parent_bottom,第二个参数是这个规则的值，写TRUE

        addView(rightIv, rightLp); // 注意是选用两个参数的addView

        tv = new TextView(context);
        tv.setText(titleText);
        tv.setTextColor(titleColor);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,titleSize);// 而不是使用setText（），这里是会将sp当成px，就是将sp转换为px，再将px等量变成sp，再将sp转换为px，值会越来越大
        LayoutParams tvLp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvLp.addRule(CENTER_IN_PARENT,TRUE);
        addView(tv,tvLp);

        // 接口回调 : 接口回调方法，设置一个点击事件！ 这种方式也可以实现在Fragment里面吧！猜想！
        // 自定义一个接口，来实现点击事件的处理，该控件封装一个setOnclick()方法，这个是可以监听到所有View的点击事件
        // 自定义监听事件！！接口回调

        leftIv.setOnClickListener(new OnClickListener() { // 利用这个来看一看安卓机制里面的回调，还有我们一直熟悉的监听。
            @Override
            public void onClick(View v) {
                if(onImageClick != null)
                {
                    onImageClick.leftClick(); // 这个onImageClick实例是从Activity里面new出来然后传进来这里的，就是说它可以在外面的Activity里面的点击事件里面传好逻辑，我这里帮忙回调而已。
                }
            }
        });

        rightIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onImageClick != null)
                {
                    onImageClick.rightClick();
                }
            }
        });

    }

    public interface OnImageClick{
        void leftClick();
        void rightClick();
    }

    private OnImageClick onImageClick;

    // 这个setOnImageClick只是给本类的接口变量 赋值而已，就是这么简单。
    public void setOnImageClick(OnImageClick onImageClick) //是自定义的leftImage可以直接调用这个public方法
    {
                                            // 从外面传进来的匿名内部类的实例，被传进来这个类，给本类的接口变量onImagelick赋值，本类的接口变量会根据哪个按钮的setOnClick()被触发，来调用你在Activity里面实现好的方法，就是层层调用嘛。
        this.onImageClick = onImageClick;  // 哪个Activity里面new出来的接口实例被传进来，就是哪个Activity调用了这个方法，
        // 就不必给每个Activity在这里作区分了，因为利用谁实现了接口的实例，就可以唯一知道该返回给谁，就是知道谁在调用。
        // Activity里面的setOnImageClick方法里面有个匿名内部类，这就是待会被传进这里来的实例。
    }


    // 接口回调
    public interface OnImageClick2
    {
        void leftClick();
        void rightClick();
    }
    // 这个setOnImageClick只是传入一个实例，这个实例是给本类接口赋值的
    private OnImageClick2 onImageClick2;

    public void setOnImageClick2(OnImageClick2 onImageClick2){ // 这个setOnImageClick只是给本类的接口变量 赋值而已，就是这么简单。
        this.onImageClick2 = onImageClick2;
    }



}
