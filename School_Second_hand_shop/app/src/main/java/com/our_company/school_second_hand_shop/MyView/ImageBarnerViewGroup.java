package com.our_company.school_second_hand_shop.MyView;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 实现轮播核心类
 * Created by Lance on 2017/3/8.
 */
public class ImageBarnerViewGroup  extends ViewGroup {

    private int chirldren;      //子视图个数;
    private  int childWidth;         //子视图宽度;
    private  int childHeight;        //子视图高度;

    private int x;              //第一次按下的位置的横坐标;每一次移动之前的横坐标;
    private  int index = 0;     //代表我们的每张图片的;

    private Scroller scroller;

    //实现图片的 单击事件的获取，
    //采用的方法就是利用一个单击变量开关进行判断，；

    private  boolean isClick;//true的时候，代表的是点击事件；

    private ImageBarnnerLister lister;

    public ImageBarnnerLister getLister(){
        return lister;
    }

    public void setLister(ImageBarnnerLister lister){
        this.lister = lister;
    }

    public void setBarnnerViewGroupListner(ImageBarnnerViewGroupListner barnnerViewGroupListner) {
        this.barnnerViewGroupListner = barnnerViewGroupListner;
    }

    public ImageBarnnerViewGroupListner getBarnnerViewGroupListner() {
        return barnnerViewGroupListner;
    }

    public  interface  ImageBarnnerLister{
        void clickImageIndex(int pos);  //pos代表的使我们当前的图片的具体索引;
    }


    private ImageBarnnerViewGroupListner barnnerViewGroupListner;

    //实现底部原点以及底部原点切换的步骤；
    //利用FrameLayout的布局;利用FrameLayout的特性;
    //

    private boolean isAuto = true; //默认情况下自动开启轮播图;
    private Timer timer = new Timer();
    private TimerTask task;
    private Handler autoHandle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
           switch (msg.what){
               case 3:
                   if(++index >= chirldren){        //轮播到最后一张,index =0 ;
                       index = 0;
                   }
                   scrollTo(childWidth * index,0);
                   barnnerViewGroupListner.selectImage(index);
                   break;

           }
        }
    };

    private void startAuto(){
        isAuto = true;
    }

    private void stopAuto(){
        isAuto = false;
    }

    //在自定义ViewGroup中，我们必须实现的是:测量->布局->绘制;

    public ImageBarnerViewGroup(Context context) {
        super(context);
        initObj();
    }

    public ImageBarnerViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initObj();
    }

    public ImageBarnerViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initObj();
    }

    private void initObj() {
        scroller = new Scroller(getContext());

        task = new TimerTask() {
            @Override
            public void run() {
                if(isAuto){
                    autoHandle.sendEmptyMessage(3);

                }
            }
        };

        timer.schedule(task,100,5000);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),0);
            invalidate();
        }
    }

    //首先需要测量出自视图的宽度与高度，从而设定ViewGroup的宽度与高度；
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        chirldren = getChildCount();    //测出子视图的个数；
        if(chirldren == 0){
            setMeasuredDimension(0,0);      //初始化;
        }else
        {
            measureChildren(widthMeasureSpec,heightMeasureSpec);
            //测量第一个子视图的宽度与高度；高度等于子视图的高度；宽度等于子视图的宽度之和;
            View view = getChildAt(0);
            childWidth = view.getMeasuredWidth();               //一个子视图的宽度
            childHeight = view.getMeasuredHeight();
            int width = view.getMeasuredWidth() * chirldren;      //所有子视图的宽度总和;
            setMeasuredDimension(width,childHeight);
        }
    }


    //在事件传递过程中，我们需要调用容器的拦截方法:onInterceptTouchEvent;
    // 针对该方法我们可以理解为 返回值为TRUE的时候，我们自定义的ViewGroup会处理拦截事件；
    //返回值为FALSE的时候，我们的ViewGroup 不会处理此次事件的处理过程，将会向下传递下去
    //返回值为TRUE的时候，我们真正处理事件的方法为   onTouchEvent   方法；
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    //轮播图的手动轮播有两种方式：
    //1.利用scrollTo、scrollBy 完成轮播图的手动轮播;
    //2.利用scroller 对象 完成轮播图的手动轮播;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:       //按下一瞬间;
                stopAuto();
                if(!scroller.isFinished()){
                    scroller.abortAnimation();
                }
                isClick = true;
                x = (int) event.getX();
                break;

            case MotionEvent.ACTION_MOVE:       //滑动;
                int moveX = (int) event.getX();
                int distace = moveX - x;
                scrollBy(-distace,0);
                x = moveX;
                isClick = false;
                break;

            case MotionEvent.ACTION_UP:         //抬起一瞬间;

                int scrollX = getScrollX();
                index = (scrollX + childWidth / 2) / childWidth;
                if(index<0){            //说明滑动到了最左边;
                    index = 0;
                }else if(index > chirldren-1){           //说明了此时活动到了最右边;
                    index = chirldren -1;
                }

                if(isClick){
                    lister.clickImageIndex(index);
                }else{
                    int dx = index * childWidth - scrollX;      //滑动的距离;
                    scroller.startScroll(scrollX,0,dx,0);
                    barnnerViewGroupListner.selectImage(index);
                    postInvalidate();
                }
                startAuto();
//                scrollTo(index * childWidth,0);
                break;
            default:
                break;
        }
        return true;            //返回ture 的目的是告诉ViewGroup容器的父view，我已经处理好了该事件;
    }

    //实现ViewGroup布局；
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed){
            int leftMargin = 0;
            for(int i = 0;i<chirldren;i++){
                View view = getChildAt(i);
                view.layout(leftMargin,0,leftMargin + childWidth,childHeight);
                leftMargin += childWidth;
            }
        }
    }

    public interface  ImageBarnnerViewGroupListner{
        void selectImage(int index);
    }
}
