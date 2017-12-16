package text.bwei.com.shipingxiazai1215.api;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;
import text.bwei.com.shipingxiazai1215.bean.SecondBean;
import text.bwei.com.shipingxiazai1215.bean.ShouYe;

/**
 * Created by dell on 2017/12/15.
 */

public interface ApiService {
    @GET("homePageApi/homePage.do")
    Observable<ShouYe> getdates();

    @POST
    Observable<SecondBean> getdaes(@Url String url, @QueryMap Map<String,String> map);

}
