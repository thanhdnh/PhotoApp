package thanhdnh.ueh.edu.photoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhotoAdapter extends BaseAdapter {
    private ArrayList<Photo> photo_list;
    private Context context;

    public PhotoAdapter(ArrayList<Photo> photo_list, Context context) {
        this.photo_list = photo_list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return photo_list.size();
    }

    @Override
    public Object getItem(int position) {
        return photo_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return photo_list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MyView dataitem;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            dataitem = new MyView();
            convertView = inflater.inflate(R.layout.photo_disp_tpl, null);
            dataitem.iv_photo = convertView.findViewById(R.id.imv_photo);
            dataitem.tv_caption = convertView.findViewById(R.id.tv_title);
            convertView.setTag(dataitem);
        } else {
            dataitem = (MyView) convertView.getTag();
        }
        //new DownloadImage(dataitem.iv_photo).execute(photo_list.get(position).getSource_photo());
        Picasso.get().load(photo_list.get(position).getSource_photo()).resize(300, 400).centerCrop().into(dataitem.iv_photo);
        dataitem.tv_caption.setText(photo_list.get(position).getTitle_photo());
        return convertView;
    }

    private static class MyView {
        ImageView iv_photo;
        TextView tv_caption;
    }
}
