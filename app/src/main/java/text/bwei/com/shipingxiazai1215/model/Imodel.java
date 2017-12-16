package text.bwei.com.shipingxiazai1215.model;

/**
 * Created by dell on 2017/12/15.
 */

public interface Imodel {
    void RequestShouye(String url, Onselect onselect);

    void RequestDonw(String url,String mediaId,Ondonwselect ondonwselect);


}
