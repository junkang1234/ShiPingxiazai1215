package text.bwei.com.gouwuche.view;


import text.bwei.com.gouwuche.bean.MessageBean;


public interface Iview {
    void onSuccess(Object o);
    void onFailed(Exception e);

    void delSuccess(MessageBean listMessageBean);
}
