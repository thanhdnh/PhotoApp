package thanhdnh.ueh.edu.article_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  public GridView gridview;


  private AdapterView.OnItemClickListener onitemclick =
          new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(
                    AdapterView<?> parent,
                    View view,
                    int position,
                    long id) {


              Intent intent =
                      new Intent(
                              getBaseContext(),
                              ViewUserProfileActivity.class
                      );


              intent.putExtra(
                      "id",
                      gridview
                              .getAdapter()
                              .getItemId(position)
              );


              startActivity(intent);
            }
          };


  @Override
  protected void onCreate(
          Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    setContentView(
            R.layout.activity_main
    );


    if (getSupportActionBar() != null) {
      getSupportActionBar().hide();
    }


    gridview =
            findViewById(
                    R.id.gridview
            );


    /*
     * THAY URL NÀY bằng URL JSON users
     * mà thầy cung cấp / nhóm bạn tạo.
     */
    new UserData(
            getBaseContext(),
            gridview
    ).loadData(
            "https://raw.githubusercontent.com/PNem06/PhotoApp/refs/heads/PN_branch/users.json",
            this
    );


    gridview.setOnItemClickListener(
            onitemclick
    );
  }
}