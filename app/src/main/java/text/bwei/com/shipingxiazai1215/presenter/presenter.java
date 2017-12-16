package text.bwei.com.shipingxiazai1215.presenter;

import java.util.List;

import text.bwei.com.shipingxiazai1215.bean.ShouYe;
import text.bwei.com.shipingxiazai1215.model.Imodel;
import text.bwei.com.shipingxiazai1215.model.Onselect;
import text.bwei.com.shipingxiazai1215.model.model;
import text.bwei.com.shipingxiazai1215.view.Iview;

/**
 * Created by dell on 2017/12/15.
 */

public class presenter {
    Imodel imodel;
    Iview iview;

    public presenter(Iview iview) {
        this.iview = iview;
        imodel = new model();
    }

    public void getOk(String url) {
        imodel.RequestShouye(url, new Onselect() {
            @Override
            public void dataShouYe(List<ShouYe.RetBean.ListBean> list) {
                iview.shouShouye(list);
            }
        });


    }


}
