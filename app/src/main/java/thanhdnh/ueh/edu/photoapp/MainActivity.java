package thanhdnh.ueh.edu.photoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gridview;
    private AdapterView.OnItemClickListener onitemclick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getBaseContext(), ViewPhotoActivity.class);
            intent.putExtra("id", gridview.getAdapter().getItemId(position));
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        gridview = findViewById(R.id.gridview);
        PhotoAdapter adapter = new PhotoAdapter(PhotoData.generatePhotoData(), getApplicationContext());
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(onitemclick);
    }

}