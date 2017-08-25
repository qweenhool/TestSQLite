package com.example.testsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.testsqlite.adapter.MyAdapter;
import com.example.testsqlite.entity.DaoSession;
import com.example.testsqlite.entity.HouseBean;
import com.example.testsqlite.entity.HouseBeanDao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bt_add)
    Button btAdd;
    @BindView(R.id.bt_delete)
    Button btDelete;
    @BindView(R.id.rv_house)
    RecyclerView rvHouse;

    private HouseBeanDao house;
    private List<HouseBean> houseBeanList;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        houseBeanList = new ArrayList<>();

        DaoSession daoSession = ((AppApplication) getApplication()).getDaoSession();
        house = daoSession.getHouseBeanDao();
        rvHouse.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onResume() {
        super.onResume();





        adapter = new MyAdapter(this,houseBeanList);
        rvHouse.setAdapter(adapter);
    }

    @OnClick({R.id.bt_add, R.id.bt_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_add:
                Intent intent = new Intent(this,AddHouseActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_delete:

                break;
        }
    }
}
