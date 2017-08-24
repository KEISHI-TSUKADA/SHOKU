package com.example.tsukadamac.shoku;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class RecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);


        Button button = (Button) findViewById(R.id.buttonInstagram);
        // ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                intent.setType("image/*");
                startActivityForResult(intent, 1);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode == 1){
            if(resultCode == RESULT_OK){

                android.net.Uri imageUri = data.getData();

                // ボタンがクリックされた時に呼び出されます
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setData(imageUri);
                intent.setType("image/jpg");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                PackageManager pm = getPackageManager();
                List<ResolveInfo> resolveInfos = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                String className = null;
                String packegeName = null;
                for(ResolveInfo info :resolveInfos){
                    if("Instagram".equals(info.loadLabel(pm)) || "instagram".equals(info.loadLabel(pm))){
                        className = info.activityInfo.name;
                        packegeName = info.activityInfo.packageName;
                        break;
                    }
                }
                if(className != null && packegeName != null){
                    intent.setClassName(packegeName, className);
                    intent.setAction(Intent.ACTION_SEND);
                    intent.setType("image/jpg");
                    intent.putExtra(Intent.EXTRA_STREAM, imageUri);
                    startActivity(intent);
                }else{
                    //Instagramがない場合の処理
                }
            }
        }
    }
}
