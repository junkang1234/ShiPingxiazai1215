package text.bwei.com.gouwuche.model;


import io.reactivex.Flowable;
import text.bwei.com.gouwuche.bean.MessageBean;
import text.bwei.com.gouwuche.presenter.DelPresenter;
import text.bwei.com.gouwuche.utils.RetrofitUtils;

/**
 * Created by SDC on 2017/12/11.
 */

public class DelModel implements IModel {
    private DelPresenter presenter;

    public DelModel(DelPresenter presenter){
        this.presenter =  presenter;

    }
    @Override
    public void getData(String uid,String pid) {

        Flowable<MessageBean> delFlowable = RetrofitUtils.getInstance().getApiService().deleteData(uid,pid);
        presenter.delData(delFlowable);
    }
}