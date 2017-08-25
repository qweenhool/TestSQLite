package com.example.testsqlite;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.example.testsqlite.entity.DaoSession;
import com.example.testsqlite.entity.HouseBean;
import com.example.testsqlite.entity.HouseBeanDao;
import com.example.testsqlite.entity.RoomBean;
import com.example.testsqlite.entity.RoomBeanDao;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddHouseActivity extends AppCompatActivity {

    @BindView(R.id.et_house_name)
    EditText etHouseName;
    @BindView(R.id.tv_house_type)
    TextView tvHouseType;
    @BindView(R.id.ll_house_type)
    LinearLayout llHouseType;
    @BindView(R.id.et_room1)
    EditText etRoom1;
    @BindView(R.id.et_room2)
    EditText etRoom2;
    @BindView(R.id.et_room3)
    EditText etRoom3;
    @BindView(R.id.bt_save)
    Button btSave;

    //相当于数据库中的house表
    private HouseBeanDao house;
    private HouseBean houseBean;

    private RoomBeanDao room;
    private RoomBean roomBean;

    private String[] houseTypeArr;
    private List<String> housePhotoList;
    private OptionsPickerView opvHouseType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_house);
        ButterKnife.bind(this);

        DaoSession daoSession = ((AppApplication) getApplication()).getDaoSession();
        house = daoSession.getHouseBeanDao();
        room = daoSession.getRoomBeanDao();

        houseBean = new HouseBean();
        roomBean = new RoomBean();

        initOptionPicker();
    }

    private void initOptionPicker() {
        houseTypeArr = new String[]{"住宅/小区/公寓", "商铺/门市房", "厂房/车间", "仓库/车库/停车位", "写字楼/办公室"};
        //开源库地址：https://github.com/Bigkoo/Android-PickerView
        opvHouseType = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                tvHouseType.setText(houseTypeArr[options1]);
                if (houseTypeArr[options1].equals("住宅/小区/公寓")) {
                    houseBean.setHouseType(1);
                }else if(houseTypeArr[options1].equals("商铺/门市房")){
                    houseBean.setHouseType(2);
                }else if(houseTypeArr[options1].equals("厂房/车间")){
                    houseBean.setHouseType(3);
                }else if(houseTypeArr[options1].equals("仓库/车库/停车位")){
                    houseBean.setHouseType(4);
                }else if(houseTypeArr[options1].equals("写字楼/办公室")){
                    houseBean.setHouseType(5);
                }

            }
        })
                .setTextColorCenter(Color.parseColor("#5287E7"))//滚轮文字颜色设置
                .setLineSpacingMultiplier(1.8f)//滚轮间距设置（1.2-2.0倍，此为文字高度的间距倍数）
                .build();
        opvHouseType.setPicker(Arrays.asList(houseTypeArr));
    }

    @OnClick({R.id.ll_house_type, R.id.bt_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_house_type:
                opvHouseType.show();
                break;
            case R.id.bt_save:

                String uuid = UUID.randomUUID().toString();
                houseBean.setId(uuid);
                houseBean.setDataUpload(0);
                houseBean.setHouseName(etHouseName.getText().toString());
                houseBean.setOrderNumber(new Date());
                houseBean.setUseFeeTemplate(1);
                house.insert(houseBean);

                if(!TextUtils.isEmpty(etRoom1.getText().toString().trim())){
                    roomBean.setHouseId(uuid);
                    roomBean.setId(UUID.randomUUID().toString());
                    roomBean.setRoomName(etRoom1.getText().toString().trim());
                    roomBean.setDataUpload(0);
                    roomBean.setOrderNumber(1);
                    roomBean.setCreateTime(null);
                    room.insert(roomBean);
                }
                if(!TextUtils.isEmpty(etRoom2.getText().toString().trim())){
                    roomBean.setHouseId(uuid);
                    roomBean.setId(UUID.randomUUID().toString());
                    roomBean.setRoomName(etRoom2.getText().toString().trim());
                    roomBean.setDataUpload(0);
                    roomBean.setOrderNumber(2);
                    roomBean.setCreateTime(null);
                    Log.e(this.getClass().getSimpleName(), ""+roomBean);
                    room.insert(roomBean);
                }
                if(!TextUtils.isEmpty(etRoom3.getText().toString().trim())){
                    roomBean.setHouseId(uuid);
                    roomBean.setId(UUID.randomUUID().toString());
                    roomBean.setRoomName(etRoom3.getText().toString().trim());
                    roomBean.setDataUpload(0);
                    roomBean.setOrderNumber(3);
                    roomBean.setCreateTime(null);
                    room.insert(roomBean);
                }

                finish();//返回主界面
                break;
        }
    }
}
