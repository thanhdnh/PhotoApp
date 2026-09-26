package thanhdnh.ueh.edu.article_app;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {

  private ArrayList<UserProfile> user_list;
  private Context context;

  public UserAdapter(ArrayList<UserProfile> user_list, Context context) {
    this.user_list = user_list;
    this.context = context;
  }

  @Override
  public int getCount() {
    return user_list.size();
  }

  @Override
  public Object getItem(int position) {
    return user_list.get(position);
  }

  @Override
  public long getItemId(int position) {
    return user_list.get(position).getId();
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {

    final MyView dataitem;

    LayoutInflater inflater =
            (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE
            );

    if (convertView == null) {

      dataitem = new MyView();

      convertView = inflater.inflate(
              R.layout.user_disp_tpl,
              null
      );

      dataitem.iv_avatar =
              convertView.findViewById(
                      R.id.imv_avatar
              );

      dataitem.tv_username =
              convertView.findViewById(
                      R.id.tv_username
              );

      dataitem.progress_bar =
              convertView.findViewById(
                      R.id.progress_bar
              );

      convertView.setTag(dataitem);

    } else {

      dataitem =
              (MyView) convertView.getTag();
    }

    UserProfile user =
            user_list.get(position);

    dataitem.tv_username.setText(
            user.getUsername()
    );

    dataitem.progress_bar.setVisibility(
            ProgressBar.VISIBLE
    );

    File userCacheFolder =
            new File(
                    context.getCacheDir(),
                    "user_" + user.getId()
            );

    Handler mainHandler =
            new Handler(
                    Looper.getMainLooper()
            );

    Downloader.downloadWithProgress(
            user.getAvatar_url(),
            mainHandler,
            context,
            userCacheFolder,
            dataitem.progress_bar,
            dataitem.iv_avatar
    );

    return convertView;
  }

  private static class MyView {

    ImageView iv_avatar;

    TextView tv_username;

    ProgressBar progress_bar;
  }
}