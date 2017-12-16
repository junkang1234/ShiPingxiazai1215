package text.bwei.com.shipingxiazai1215;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import text.bwei.com.shipingxiazai1215.api.Api;
import text.bwei.com.shipingxiazai1215.bean.EventMessage;
import text.bwei.com.shipingxiazai1215.bean.SecondBean;
import text.bwei.com.shipingxiazai1215.presenter.DonwPresenter;
import text.bwei.com.shipingxiazai1215.utils.DownloadUtil;
import text.bwei.com.shipingxiazai1215.view.Donwview;

/**
 * Created by dell on 2017/12/15.
 */

public class SecondActivity extends Activity implements Donwview {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ProgressBar mProgressBar;
    private Button start;
    private Button pause;


    private TextView total;
    private int max;
    private DownloadUtil mDownloadUtil;
    private String downloadURL;
    private String dataId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondactivity);
        EventBus.getDefault().register(this);
        total = (TextView) findViewById(R.id.textView);
        start = (Button) findViewById(R.id.start);
        pause = (Button) findViewById(R.id.delete);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        DonwPresenter donwPresenter = new DonwPresenter(this);
        donwPresenter.getDonw(Api.URL,dataId);


//        Intent intent = getIntent();
//        String urlString = intent.getStringExtra("mm");
//        String urlString = "http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4";



    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void F(EventMessage messEvent) {
        dataId = messEvent.getDataId();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    @Override
    public void shouDonw(SecondBean.RetBean list) {
        downloadURL = list.getHDURL();
        String localPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/local";
        mDownloadUtil = new DownloadUtil(2, localPath, "adc.mp4", downloadURL,
                this);
        mDownloadUtil.setOnDownloadListener(new DownloadUtil.OnDownloadListener() {

            @Override
            public void downloadStart(int fileSize) {
                // TODO Auto-generated method stub
                Log.w(TAG, "fileSize::" + fileSize);
                max = fileSize;
                mProgressBar.setMax(fileSize);
            }

            @Override
            public void downloadProgress(int downloadedSize) {
                // TODO Auto-generated method stub
                Log.w(TAG, "Compelete::" + downloadedSize);
                mProgressBar.setProgress(downloadedSize);
                total.setText((int) downloadedSize * 100 / max + "%");
            }

            @Override
            public void downloadEnd() {
                // TODO Auto-generated method stub
                Log.w(TAG, "ENd");
            }
        });
        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                mDownloadUtil.start();
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                mDownloadUtil.pause();
            }
        });

    }
}