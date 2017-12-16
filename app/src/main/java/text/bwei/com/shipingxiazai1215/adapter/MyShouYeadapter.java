package text.bwei.com.shipingxiazai1215.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import text.bwei.com.shipingxiazai1215.R;
import text.bwei.com.shipingxiazai1215.SecondActivity;
import text.bwei.com.shipingxiazai1215.bean.EventMessage;
import text.bwei.com.shipingxiazai1215.bean.ShouYe;

/**
 * Created by dell on 2017/12/15.
 */

public class MyShouYeadapter extends RecyclerView.Adapter {
    List<ShouYe.RetBean.ListBean> list;
    Context context;

    public MyShouYeadapter(List<ShouYe.RetBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.shouye_item, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.textView.setText(list.get(position).getChildList().get(0).getTitle());
        Uri imgUrl = Uri.parse(list.get(position).getChildList().get(0).getPic());
        myViewHolder.simpleDraweeView.setImageURI(imgUrl);

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ShouYe.RetBean.ListBean.ChildListBean> childList = list.get(position).getChildList();
                EventBus.getDefault().postSticky(new EventMessage(childList.get(0).getDataId()));
                Intent intent = new Intent(context, SecondActivity.class);
                context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView simpleDraweeView;
        private final TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView = itemView.findViewById(R.id.sdv_image);
            textView = itemView.findViewById(R.id.text);

        }
    }


}
