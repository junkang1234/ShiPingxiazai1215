package text.bwei.com.shipingxiazai1215.model;

import java.util.HashMap;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import text.bwei.com.shipingxiazai1215.api.ApiService;
import text.bwei.com.shipingxiazai1215.bean.SecondBean;
import text.bwei.com.shipingxiazai1215.bean.ShouYe;

/**
 * Created by dell on 2017/12/15.
 */

public class model implements Imodel {


    @Override
    public void RequestShouye(String url, final Onselect onselect) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);

        Observable<ShouYe> getdates = apiService.getdates();

        getdates.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ShouYe>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ShouYe shouYe) {
                        List<ShouYe.RetBean.ListBean> list = shouYe.getRet().getList();
                        onselect.dataShouYe(list);

                    }
                });


    }

    @Override
    public void RequestDonw(String url, String mediaId, final Ondonwselect ondonwselect) {

        HashMap<String, String> map = new HashMap<>();
        map.put("mediaId",mediaId);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);

        Observable<SecondBean> getdaes = apiService.getdaes("videoDetailApi/videoDetail.do", map);

        getdaes.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SecondBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SecondBean secondBean) {

                        SecondBean.RetBean ret = secondBean.getRet();
                        ondonwselect.dataDnow(ret);

                    }
                });



    }
}
