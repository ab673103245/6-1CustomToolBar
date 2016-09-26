package qianfeng.a6_1customtoolbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomToolbar customToolbar = (CustomToolbar) findViewById(R.id.customToolbar);

        customToolbar.setOnImageClick(new CustomToolbar.OnImageClick() { // 这个是Activity里面实现的接口的实例，在这里写的方法，会被回调的！！
            @Override
            public void leftClick() {
                Toast.makeText(MainActivity.this,"left",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(MainActivity.this,"right",Toast.LENGTH_SHORT).show();
            }
        });

    }

}
