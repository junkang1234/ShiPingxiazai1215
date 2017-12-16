package text.bwei.com.shipingxiazai1215.presenter;

import text.bwei.com.shipingxiazai1215.bean.SecondBean;
import text.bwei.com.shipingxiazai1215.model.Imodel;
import text.bwei.com.shipingxiazai1215.model.Ondonwselect;
import text.bwei.com.shipingxiazai1215.model.model;
import text.bwei.com.shipingxiazai1215.view.Donwview;

/**
 * Created by dell on 2017/12/15.
 */

public class DonwPresenter {
    Imodel imodel;
    Donwview donwview;

    public DonwPresenter(Donwview donwview) {
        this.donwview = donwview;
        imodel = new model();
    }

    public void getDonw(String url, String mediaId) {
        imodel.RequestDonw(url, mediaId, new Ondonwselect() {
            @Override
            public void dataDnow(SecondBean.RetBean list) {
                donwview.shouDonw(list);
            }
        });


    }

}
