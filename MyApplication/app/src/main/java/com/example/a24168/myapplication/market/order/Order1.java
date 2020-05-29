package com.example.a24168.myapplication.market.order;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.market.MarketFragment;
import com.example.a24168.myapplication.market.order.entity.JsonBean;
import com.example.a24168.myapplication.market.shopping.Shopping;
import com.example.a24168.myapplication.market.shopping.adpter.ShoppingAdapter;
import com.example.a24168.myapplication.market.shopping.entity.Order;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.a24168.myapplication.sign.Sign.user_id;


public class Order1 extends AppCompatActivity {
    private ArrayList<JsonBean> options1Items = new ArrayList<>(); //省
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();//市
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();//区
    private TextView diquxuanze;
    private TextView textView;
    private Handler handler;
    private EditText editText;
    private TextView fukuan;
    private TextView fanhuigouwuche;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:{

                        break;
                    }
                }


            }
        };


        setContentView(R.layout.market_order);
        fanhuigouwuche = findViewById(R.id.fanhuigouwuche);
        fukuan = findViewById(R.id.fukuan);
        diquxuanze = findViewById(R.id.diqux);
        editText = findViewById(R.id.diqux2);
        initJsonData();
        diquxuanze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initOptionPicker();
            }
        });
        fukuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOrder();
            }
        });
        fanhuigouwuche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.anim_int_right1,R.anim.anim_out_left1);
            }
        });
        Intent intent  = getIntent();
        String a = intent.getStringExtra("allprice");
        textView = findViewById(R.id.shifukuan);
        textView.setText(a);

    }

    private void initOptionPicker() {//条件选择器初始化

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                diquxuanze.setText(options1Items.get(options1).getPickerViewText() + "  "
                        + options2Items.get(options1).get(options2) + "  "
                        + options3Items.get(options1).get(options2).get(options3));
            }
        })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();

    }

    //解析数据 （省市区三级联动）
    private void initJsonData() {
        String JsonData = new GetJsonDataUtil().getJson(this, "area.json");//获取assets目录下的json文件数据
        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体
        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;
        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三级）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表
                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }
            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }
    }
        public ArrayList<JsonBean> parseData(String result){
            ArrayList<JsonBean> detail = new ArrayList<>();
            try {
                JSONArray data = new JSONArray(result);
                Gson gson = new Gson();
                for (int i = 0; i < data.length(); i++) {
                    JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                    detail.add(entity);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return detail;
        }


        public void addOrder(){
            OkHttpClient okHttpClient = new OkHttpClient();
            FormBody.Builder builder = new FormBody.Builder();
            builder.add("user_id",""+user_id);
            builder.add("address",""+diquxuanze.getText().toString()+editText.getText().toString());
            builder.add("price",""+textView.getText().toString().substring(3));
            builder.add("context","null");

            FormBody body = builder.build();

            Request request = new Request.Builder().post(body)
                    .url(getResources().getString(R.string.ip1)+"/marketcomment/12345addorder").build();
            final Call call = okHttpClient.newCall(request);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Response response = call.execute();
                        Message message = Message.obtain();
                        message.obj = response.body().string();
                        message.what = 1;
                        handler.sendMessage(message);
                        Looper.prepare();
                        Toast.makeText(Order1.this,"支付成功",Toast.LENGTH_SHORT).show();

                        finish();
                        overridePendingTransition(R.anim.anim_int_right1,R.anim.anim_out_left1);
                        Looper.loop();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();


        }
}
