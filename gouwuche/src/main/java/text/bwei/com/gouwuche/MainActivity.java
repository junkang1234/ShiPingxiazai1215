package text.bwei.com.gouwuche;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import text.bwei.com.gouwuche.Event.MessageEvent;
import text.bwei.com.gouwuche.Event.PriceAndCountEvent;
import text.bwei.com.gouwuche.Event.SomeId;
import text.bwei.com.gouwuche.adapter.MyAdapter;
import text.bwei.com.gouwuche.bean.DatasBean;
import text.bwei.com.gouwuche.bean.MessageBean;
import text.bwei.com.gouwuche.presenter.DelPresenter;
import text.bwei.com.gouwuche.presenter.NewsPresenter;
import text.bwei.com.gouwuche.view.Iview;

public class MainActivity extends AppCompatActivity implements Iview {
    private String uid = "1235";
    private NewsPresenter presenter;
    private CheckBox mCheckbox2;
    private ExpandableListView mElv;

    /**
     * 0
     */
    private TextView mTvPrice;
    /**
     * 结算(0)
     */
    private TextView mTvNum;
    private MyAdapter adapter;
    private List<DatasBean> groupList = new ArrayList<>();
    private List<List<DatasBean.ListBean>> childList = new ArrayList<>();
    private String pid;
    private DelPresenter delPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//《电商项目进阶》
//        1.	MVP架构模式
//        2.	OkHttp二次封装
//        3.	Retrofit使用
//        4.	图片加载框架：Fresco
//        5.	自定义View：组合控件
//        6.	checkbox选中状态错乱优化
//        7.	RecyclerView性能优化
//        8.	单例模式
//        9.	布局优化
//        10.	性能优化


        EventBus.getDefault().register(this);
        presenter = new NewsPresenter();
        presenter.attachView(this);
        delPresenter = new DelPresenter();
        delPresenter.attachView(this);
        initView();
        adapter = new MyAdapter(this, groupList, childList);
        mElv.setAdapter(adapter);


        mCheckbox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.changeAllListCbState(mCheckbox2.isChecked());
            }
        });

    }

    private void initView() {
        mElv = (ExpandableListView) findViewById(R.id.elv);
        mCheckbox2 = (CheckBox) findViewById(R.id.checkbox2);
        mTvPrice = (TextView) findViewById(R.id.tv_price);
        mTvNum = (TextView) findViewById(R.id.tv_num);
    }

    @Override
    public void onSuccess(Object o) {
        if (o != null) {
            List<DatasBean> list = (List<DatasBean>) o;
            if (list != null) {
                groupList.addAll(list);
                for (int i = 0; i < list.size(); i++) {
                    List<DatasBean.ListBean> datas = list.get(i).getList();
                    childList.add(datas);
                }

                adapter.notifyDataSetChanged();
                mCheckbox2.setChecked(true);

                adapter.changeAllListCbState(true);
                mElv.setGroupIndicator(null);
                for (int i = 0; i < groupList.size(); i++) {
                    mElv.expandGroup(i);
                }

            }
        }
    }

    @Override
    public void onFailed(Exception e) {

    }

    @Override
    public void delSuccess(MessageBean listMessageBean) {
        Toast.makeText(this, listMessageBean.getMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getData(uid, pid);

    }


    @Subscribe
    public void onMessageEvent(MessageEvent event) {
        mCheckbox2.setChecked(event.isChecked());
    }

    @Subscribe
    public void onMessageEvent(PriceAndCountEvent event) {
        mTvNum.setText("结算(" + event.getCount() + ")");
        mTvPrice.setText("￥" + event.getPrice());
    }

    @Subscribe
    public void onMessageEvent(SomeId event) {
        pid = event.getPid();
        Log.e("zxz", "我得到了pid:" + pid);
        delPresenter.getData(uid, pid);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (presenter != null) {
            presenter.detachView();
        }
    }

}
