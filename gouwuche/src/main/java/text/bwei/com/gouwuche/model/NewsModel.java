package text.bwei.com.gouwuche.model;


import java.util.List;

import io.reactivex.Flowable;
import text.bwei.com.gouwuche.bean.DatasBean;
import text.bwei.com.gouwuche.bean.MessageBean;
import text.bwei.com.gouwuche.presenter.NewsPresenter;
import text.bwei.com.gouwuche.utils.RetrofitUtils;

/**
 * Created by SDC on 2017/12/11.
 */

public class NewsModel implements IModel {
    private NewsPresenter presenter;

    public NewsModel(NewsPresenter presenter) {
        this.presenter = (NewsPresenter) presenter;

    }


    @Override
    public void getData(String uid, String pid) {
        Flowable<MessageBean<List<DatasBean>>> flowable = RetrofitUtils.getInstance().getApiService().getDatas(uid);
        presenter.getNews(flowable);

    }
}