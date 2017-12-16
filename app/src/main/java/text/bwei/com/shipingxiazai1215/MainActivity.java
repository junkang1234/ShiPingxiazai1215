package text.bwei.com.shipingxiazai1215;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;

import text.bwei.com.shipingxiazai1215.adapter.MyShouYeadapter;
import text.bwei.com.shipingxiazai1215.api.Api;
import text.bwei.com.shipingxiazai1215.bean.ShouYe;
import text.bwei.com.shipingxiazai1215.presenter.presenter;
import text.bwei.com.shipingxiazai1215.view.Iview;

public class MainActivity extends AppCompatActivity implements Iview {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        presenter presenter = new presenter(this);
        presenter.getOk(Api.URL);

    }


    @Override
    public void shouShouye(List<ShouYe.RetBean.ListBean> list) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyShouYeadapter(list, this));


    }
}
