package com.our_company.school_second_hand_shop.Fragment;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.our_company.school_second_hand_shop.Activity.Isurance;
import com.our_company.school_second_hand_shop.DataClass.GoodsInfoDescrib;
import com.our_company.school_second_hand_shop.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lance on 2017/4/17.
 */

public class FragmentHome extends Fragment implements View.OnClickListener{

    private List<GoodsInfoDescrib> goodsInfoDescribList = new ArrayList<>();


    private LinearLayout layout1;
    private LinearLayout layout2;
    private LinearLayout layout3;
    private LinearLayout layout4;
    private LinearLayout layout5;
    private LinearLayout layout6;

    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private ImageView image5;
    private ImageView image6;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goodsInfoDescribList.clear();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home,container,false);
        initWight(rootView);
        return rootView;
    }

    private void initWight(View rootView) {

        layout1 = (LinearLayout) rootView.findViewById(R.id.layout1);
        layout2 = (LinearLayout) rootView.findViewById(R.id.layout2);
        layout3 = (LinearLayout) rootView.findViewById(R.id.layout3);
        layout4 = (LinearLayout) rootView.findViewById(R.id.layout4);
        layout5 = (LinearLayout) rootView.findViewById(R.id.layout5);
        layout6 = (LinearLayout) rootView.findViewById(R.id.layout6);

        image1 = (ImageView) rootView.findViewById(R.id.image1);
        image2 = (ImageView) rootView.findViewById(R.id.image2);
        image3 = (ImageView) rootView.findViewById(R.id.image3);
        image4 = (ImageView) rootView.findViewById(R.id.image4);
        image5 = (ImageView) rootView.findViewById(R.id.image5);
        image6 = (ImageView) rootView.findViewById(R.id.image6);

        layout1.setOnClickListener(this);
        layout2.setOnClickListener(this);
        layout3.setOnClickListener(this);
        layout4.setOnClickListener(this);
        layout5.setOnClickListener(this);
        layout6.setOnClickListener(this);

    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), Isurance.class);
        String content = "";
        String uri = "";
        switch (v.getId()){
            case R.id.layout1:
                intent.putExtra("image",1);
                content = "交强险的全称是“机动车交通事故责任强制保险”，是由保险公司对被保险机动车发生道路交通事故造成受害人（不包括本车人员和被保险人）的人身伤亡、财产损失，在责任限额内予以赔偿的强制性责任保险。交强险是中国首个由国家法律规定实行的强制保险制度。其保费是实行全国统一收费标准的，由国家统一规定的，但是不同的汽车型号的交强险价格也不同，主要影响因素是“汽车座位数”。\n" +
                        "根据《交强险条例》的规定，在中华人民共和国境内道路上行驶的机动车的所有人或者管理人都应当投保交强险，机动车所有人、管理人未按照规定投保交强险的，公安机关交通管理部门有权扣留机动车，通知机动车所有人、管理人依照规定投保，并处应缴纳的保险费的2倍罚款。";
                intent.putExtra("content",content);
                uri = "http://baike.baidu.com/link?url=u6qROdy78Dgs154GWhzr1DYgCZONs3WziOqTYgJOt9_R46qeYZWWzy3jn5Xh-HFJAQVbc069lFr-pHyjEbCbQ9IZwXpZRjWZtNu7SH10HP7vhYHh2qvgG2T6kD3qCzgqYbClMGGzADZXShbQRU9uL81ohLaUjpwcCtBY03OylB_RjpCmHFmRHArTnPPCMB2zh5iRDGbYmDFuNs03zrG_IDWn7UpYazGbz8AYiAj4Psa42ldQDxCkk4SE9hUcZKBRb85zrqGr_qk8_6UtGkj9fOD8eKI8JmOSvR4HjyV2JNIdqvFyd61pNJKunleFQj_7";
                intent.putExtra("uri",uri);
                getContext().startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) getContext()).toBundle());
                break;
            case R.id.layout2:
                intent.putExtra("image",2);
                content = "第三者责任险（简称三责险），全称：商业第三者责任保险。是指被保险人允许的合格驾驶员在使用被保险车辆过程中发生的意外事故，致使第三者遭受人身伤亡或财产的直接损失，依法应当由被保险人支付的赔偿金额，保险人会按照保险合同中的有关规定给予赔偿。同时，若经保险公司书面同意，被保险人因此发生仲裁或诉讼费用的，由保险公司承担。该险种主要是保障道路交通事故中第三方受害人获得及时有效赔偿的险种。但因事故产生的善后工作，需要由被保险人负责处理。保险公司会在责任限额以外赔偿，但最高不超过责任限额的30%。\n" +
                        "以往绝大多数的地方政府将第三者责任险列为强制保险险种，不买这个保险，机动车便上不了 牌也不能年检。在机动车交通强制保险（简称交强险）出台后，第三者责任险已成为非强制性的保险。但是交强险在对第三者的财产损失和医疗费用部分赔偿较低，可考虑购买第三者责任险作为交强险的补充。\n";
                intent.putExtra("content",content);
                uri = "http://baike.baidu.com/item/%E7%AC%AC%E4%B8%89%E6%96%B9%E8%B4%A3%E4%BB%BB%E9%99%A9?sefr=ps";
                intent.putExtra("uri",uri);
                getContext().startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) getContext()).toBundle());
                break;
            case R.id.layout3:
                intent.putExtra("image",3);
                content = "车辆损失险是指保险车辆遭受保险责任范围内的自然灾害（不包括地震）或意外事故，造成保险车辆本身损失。作为多数私家车车险中保费最高的一个险种，许多车主对车辆损失险怎么计算不甚明了。其实一般车辆损失险的计算公式是：基本保费+新车购置价X费率，当然车主也可以选择不足额投保，在降低保费的同时保障也会打个折扣。 \n" +
                        "大多数保险公司的车辆损失保险,一般保障的是因雷击、暴风、暴雨、洪水等自然灾害和碰撞、倾覆等意外事故造成保险车辆的损失以及相关的施救费用。";
                intent.putExtra("content",content);
                uri = "http://baike.baidu.com/item/%E8%BD%A6%E8%BE%86%E6%8D%9F%E5%A4%B1%E9%99%A9?sefr=ps";
                intent.putExtra("uri",uri);
                getContext().startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) getContext()).toBundle());
                break;
            case R.id.layout4:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("温馨提醒：");
                builder.setMessage("该险种需要先购买车辆损失险或者第三者责任险.");
                builder.setPositiveButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getContext(), Isurance.class);
                        String content = "";
                        String uri = "";
                        intent.putExtra("image",4);
                        content = "不计免赔险的全称为“不计免赔率特约条款”，是一种商业险（车损险或三责险）的附加险。不计免赔险作为一种附加险，需要以投保的“主险”为投保前提条件，不可以单独进行投保，其保险责任通常是指“经特别约定，发生意外事故后，按照对应投保的主险条款规定的免赔率计算的、应当由被保险人自行承担的免赔金额部分，保险公司会在责任限额内负责赔偿”。一般来说，投保了这个险种，就能把本应由自己负责的5%到20%的赔偿责任再转嫁给保险公司。\n" +
                                "由于这个附加险保障全面，而费率却相对便宜，所以一经推出就很受车主欢迎。通常是指车主在投保购买此险种后，将车主由于事故责任所承担的免赔金额转给保险公司，车主领到的理赔额会更多。但不计免赔险只将车损险与第三者责任险的事故责任免赔率转嫁给保险公司。 在购买车险时，车主应给车损险与第三者责任险分别投保不计免赔险，使自身理赔权益达到最大化";
                        intent.putExtra("content",content);
                        uri = "http://baike.baidu.com/item/%E4%B8%8D%E8%AE%A1%E5%85%8D%E8%B5%94%E9%99%A9?sefr=ps";
                        intent.putExtra("uri",uri);
                        getContext().startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) getContext()).toBundle());
                    }
                });
                builder.setNegativeButton("取消",null);
                builder.create().show();
                break;
            case R.id.layout5:
                intent.putExtra("image",5);
                content = "车上人员责任险算是车辆商业险的主要保险，它主要功能是赔偿车辆因交通事故造成的车内人员的伤亡的保险。\n" +
                        "车上人员责任险，即车上座位险，是即车上人员责任险中的乘客部分，指的是被保险人允许的合格驾驶员在使用保险车辆过程中发生保险事故，致使车内乘客人身伤亡，依法应由被保险人承担的赔偿责任，保险公司会按照保险合同进行赔偿。";
                intent.putExtra("content",content);
                uri = "http://baike.baidu.com/item/%E8%BD%A6%E4%B8%8A%E4%BA%BA%E5%91%98%E8%B4%A3%E4%BB%BB%E9%99%A9?sefr=ps";
                intent.putExtra("uri",uri);
                getContext().startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) getContext()).toBundle());
                break;
            case R.id.layout6:
                intent.putExtra("image",6);
                content = "盗抢险全称是机动车辆全车盗抢险，机动车辆全车盗抢险的保险责任为全车被盗窃、被抢劫、被抢夺造成的车辆损失以及在被盗窃、被抢劫、被抢夺期间受到损坏或车上零部件、附属设备丢失需要修复的合理费用。可见，机动车辆全车盗抢险的保险责任包含两部分：一是因被盗窃、被抢劫、被抢夺造成的保险车辆的损失；二是因保险车辆被盗窃、被抢劫、被抢夺造成的合理费用支出。对上述两部分费用由保险公司在保险金额内负责赔偿保险车辆全车被盗窃、被抢劫、被抢夺造成的损失。";
                intent.putExtra("content",content);
                uri = "http://baike.baidu.com/item/%E7%9B%97%E6%8A%A2%E9%99%A9?sefr=ps";
                intent.putExtra("uri",uri);
                getContext().startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) getContext()).toBundle());
                break;
        }
    }

}
