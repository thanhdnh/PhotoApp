package thanhdnh.ueh.edu.article_app;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class ViewUserProfileActivity
        extends AppCompatActivity {

  ImageView iv_detail;

  TextView tv_detail_username;
  TextView tv_detail_email;
  TextView tv_detail_description;
  TextView tv_detail_hobby;

  ProgressBar progress_bar;


  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    setContentView(
            R.layout.activity_view_user_profile
    );

    if (getSupportActionBar() != null) {
      getSupportActionBar().hide();
    }


    iv_detail =
            findViewById(
                    R.id.iv_detail
            );


    tv_detail_username =
            findViewById(
                    R.id.tv_detail_username
            );


    tv_detail_email =
            findViewById(
                    R.id.tv_detail_email
            );


    tv_detail_description =
            findViewById(
                    R.id.tv_detail_description
            );


    tv_detail_hobby =
            findViewById(
                    R.id.tv_detail_hobby
            );


    progress_bar =
            findViewById(
                    R.id.progress_bar
            );


    int id =
            (int) getIntent().getLongExtra(
                    "id",
                    0
            );


    UserProfile user =
            UserData.getUserFromId(id);


    if (user != null) {

      tv_detail_username.setText(
              user.getUsername()
      );

      tv_detail_email.setText(
              user.getEmail()
      );

      tv_detail_description.setText(
              user.getDesc()
      );

      tv_detail_hobby.setText(
              user.getHobby()
      );


      progress_bar.setVisibility(
              ProgressBar.VISIBLE
      );


      File userCacheFolder =
              new File(
                      getCacheDir(),
                      "user_detail_" + user.getId()
              );

      if (!userCacheFolder.exists()) {
        userCacheFolder.mkdirs();
      }

      Handler mainHandler =
              new Handler(
                      Looper.getMainLooper()
              );


      Downloader.downloadWithProgress(
              user.getAvatar_url(),
              mainHandler,
              this,
              userCacheFolder,
              progress_bar,
              iv_detail
      );
    }
  }
}