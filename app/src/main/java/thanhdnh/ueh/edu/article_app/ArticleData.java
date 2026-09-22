package thanhdnh.ueh.edu.article_app;

import android.app.Activity;
import android.content.Context;
import android.widget.GridView;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ArticleData {
  public static ArticleList data;
  private Context context;
  private GridView gridview;
  private final ExecutorService executor = Executors.newSingleThreadExecutor();

  public ArticleData(Context context, GridView gridview) {
    this.context = context;
    this.gridview = gridview;
  }

  public static Article getPhotoFromId(int id) {
    for (int i = 0; i < data.getArticles().size(); i++)
      if (data.getArticles().get(i).getArticle_id() == id)
        return data.getArticles().get(i);
    return null;
  }

  public void loadData(String url, Activity activity){
      executor.execute(()->{
          File file = Downloader.downloadFile(url, context.getCacheDir());
          if(file!=null)
            activity.runOnUiThread(()->{
              Gson gson = new Gson();
              data = gson.fromJson(readText(file), (Type) ArticleList.class);
              ArticleAdapter adapter = new ArticleAdapter(data.getArticles(), context);
              gridview.setAdapter(adapter);
            });
        });
  }

  public String readText(File file){
    BufferedReader reader = null;
    try {
      InputStream stream = new FileInputStream(file);
      reader = new BufferedReader(new InputStreamReader(stream));
      StringBuffer buffer = new StringBuffer();
      String line = "";
      while ((line = reader.readLine()) != null) {
        buffer.append(line + "\n");
      }
      return buffer.toString();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
    }
    return reader.toString();
  }
}
