package thanhdnh.ueh.edu.article_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ArticleAdapter extends BaseAdapter {
  private ArrayList<Article> article_list;
  private Context context;

  public ArticleAdapter(ArrayList<Article> article_list, Context context) {
    this.article_list = article_list;
    this.context = context;
  }

  @Override
  public int getCount() {
    return article_list.size();
  }

  @Override
  public Object getItem(int position) {
    return article_list.get(position);
  }

  @Override
  public long getItemId(int position) {
    return article_list.get(position).getArticle_id();
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    final MyView dataitem;
    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    if (convertView == null) {
      dataitem = new MyView();
      convertView = inflater.inflate(R.layout.article_disp_tpl, null);
      dataitem.iv_photo = convertView.findViewById(R.id.imv_photo);
      dataitem.tv_caption = convertView.findViewById(R.id.tv_title);
      convertView.setTag(dataitem);
    } else {
      dataitem = (MyView) convertView.getTag();
    }

    Picasso.get().load(article_list.get(position).getArticle_image()).resize(300, 400).centerCrop().into(dataitem.iv_photo);
    dataitem.tv_caption.setText(article_list.get(position).getArticle_title());
    return convertView;
  }

  private static class MyView {
    ImageView iv_photo;
    TextView tv_caption;
  }
}
