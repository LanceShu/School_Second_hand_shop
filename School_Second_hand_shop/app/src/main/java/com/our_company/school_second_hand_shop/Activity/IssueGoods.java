package com.our_company.school_second_hand_shop.Activity;

import android.Manifest;
import android.content.ContentUris;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.our_company.school_second_hand_shop.MyAdapter.ExpandableListViewAdapter;
import com.our_company.school_second_hand_shop.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.our_company.school_second_hand_shop.Content.*;

/**
 * Created by Lance on 2017/4/26.
 */

public class IssueGoods extends AppCompatActivity implements View.OnClickListener{

    public static final int TAKE_PHOTO1 = 11;
    public static final int PICK_PHOTO1 = 12;
    public static final int TAKE_PHOTO2 = 21;
    public static final int PICK_PHOTO2 = 22;
    public static final int TAKE_PHOTO3 = 31;
    public static final int PICK_PHOTO3 = 32;
    String path = null;
    private String imagePath1 = null;
    private String imagePath2 = null;
    private String imagePath3 = null;
    private Uri imageUri;
    private Uri imageUri1;
    private Uri imageUri2;
    private Uri imageUri3;
    private int isImage1 = 0;
    private int isImage2 = 0;
    private int isImage3 = 0;
    private int preImage1 ;
    private int preImage2;
    private int preImage3;

    private  static final String PATH_ADD = Environment.getExternalStorageDirectory()+"/School/";
    private int mExitTime;
    private static final String TAG = "ChangeMyDataActivity";
    private Uri userImageUri;
    private File outputImage;

    private ExpandableListView elv1;
    private ExpandableListView elv2;
    private ImageView image1,image2,image3;
    private Button  issue;
    private ImageView back;
    private EditText content;

    private List<String> classify = new ArrayList<>();
    private List<String> area = new ArrayList<>();
    Map<String,List<String>> dataset1;
    Map<String,List<String>> dataset2;

    private SelectPictruePopupWindow sppw;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Fade fade = new Fade();
        fade.setDuration(250);
        getWindow().setEnterTransition(fade);
        setContentView(R.layout.goods_issue);
        initWight();                //实例化控件
        setClassify();              //设置分类
        setArea();
    }

    private void setArea() {
        dataset2 = new HashMap<>();
        area.add("西北工业大学");
        area.add("陕西师范大学");
        area.add("西安邮电大学");
        area.add("西安理工大学");
        area.add("西安外国语大学");
        area.add("西北政法大学");
        dataset2.put("学校",area);
        ExpandableListViewAdapter adapter = new ExpandableListViewAdapter(dataset2,"学校",this);
        elv2.setAdapter(adapter);
    }

    private void setClassify() {
        dataset1 = new HashMap<>();
        classify.add("兴趣爱好");
        classify.add("电脑");
        classify.add("图书素材");
        classify.add("鼠标配件");
        classify.add("收藏礼品");
        classify.add("运动健身");
        classify.add("衣服鞋帽");
        classify.add("手机");
        classify.add("保养化妆");
        classify.add("其他");
        dataset1.put("分类",classify);
        ExpandableListViewAdapter adapter = new ExpandableListViewAdapter(dataset1,"分类",this);
        elv1.setAdapter(adapter);
    }

    private void initWight() {
        elv1 = (ExpandableListView) findViewById(R.id.issue_auto1);
        elv2 = (ExpandableListView) findViewById(R.id.issue_auto2);
        image1 = (ImageView) findViewById(R.id.issue_image1);
        image2 = (ImageView) findViewById(R.id.issue_image2);
        image3 = (ImageView) findViewById(R.id.issue_image3);
        issue = (Button) findViewById(R.id.issue_issue);
        back = (ImageView) findViewById(R.id.issueBack);
        content = (EditText) findViewById(R.id.issue_content);

        issue.setOnClickListener(this);
        back.setOnClickListener(this);
        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);

        elv1.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(IssueGoods.this,classify.get(childPosition),Toast.LENGTH_SHORT).show();
                Log.e("11111111111",dataset1.get("分类").get(childPosition));
                return true;
            }
        });
    }

    private void openCamera(int i){
        if(Build.VERSION.SDK_INT>=24){
            imageUri = FileProvider.getUriForFile(IssueGoods.this,"com.our_company.school_second_hand_shop",outputImage);
        }else{
            imageUri = Uri.fromFile(outputImage);
        }
        Intent intent1 = new Intent("android.media.action.IMAGE_CAPTURE");
        intent1.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        if(i == 1){
            imageUri1 = imageUri;
            startActivityForResult(intent1,TAKE_PHOTO1);
        }else if(i == 2){
            imageUri2 = imageUri;
            startActivityForResult(intent1,TAKE_PHOTO2);
        }else if(i == 3){
            imageUri3 = imageUri;
            startActivityForResult(intent1,TAKE_PHOTO3);
        }

    }

    private void selectPhoto(final int i) {
        sppw = new SelectPictruePopupWindow(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sppw.dismiss();
                switch (v.getId()){
                    case R.id.picture_take:
                        if(ContextCompat.checkSelfPermission(IssueGoods.this,Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                            ActivityCompat.requestPermissions(IssueGoods.this,new String[]{Manifest.permission.CAMERA},1);
                        }else {
                            outputImage = new File(getExternalCacheDir(),"output_image.jpg");
                            try {
                                if(outputImage.exists()){
                                    outputImage.delete();
                                }
                                outputImage.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            openCamera(i);
                        }
                        break;
                    case R.id.picture_pick:
                        if(ContextCompat.checkSelfPermission(IssueGoods.this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                            ActivityCompat.requestPermissions(IssueGoods.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},2);
                        }else {
                            openPicture(i);
                        }
                        break;
                    case R.id.picture_cancel:
                        break;
                }

            }
        });
        sppw.showAtLocation(findViewById(R.id.issue_layout),Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    private void openPicture(int i) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        if(i ==1){
            startActivityForResult(intent,PICK_PHOTO1);
        }else if(i ==2){
            startActivityForResult(intent,PICK_PHOTO2);
        }else if(i == 3){
            startActivityForResult(intent,PICK_PHOTO3);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case TAKE_PHOTO1:
                if(resultCode == RESULT_OK){
                    Bitmap bitmap = null;
                    try {
                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    bitmap.createScaledBitmap(bitmap,100,100,true);
                    image1.setImageBitmap(bitmap);
//                    Glide.with(this).load(imageUri).into(image1);
                }
                isImage1 = 1;
                Message message1 = new Message();
                message1.what = 11;
                handler.sendMessage(message1);
                break;
            case PICK_PHOTO1:
                if(resultCode == RESULT_OK){
                    Log.e("path111111111",data+"");
                    if(Build.VERSION.SDK_INT >= 19){
                        handleImageOnKitKat(data,1);
                    }else{
                        handleImageBeforeKitKat(data,1);
                    }
                }
                isImage1 = 1;
                Message message2 = new Message();
                message2.what = 12;
                handler.sendMessage(message2);
                break;
            case TAKE_PHOTO2:
                if(resultCode == RESULT_OK){
                    Bitmap bitmap = null;
                    try {
                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    bitmap.createScaledBitmap(bitmap,100,100,true);
                    image2.setImageBitmap(bitmap);
//                    Glide.with(this).load(imageUri).into(image2);
                }
                isImage2 = 1;
                Message message3 = new Message();
                message3.what = 21;
                handler.sendMessage(message3);
                break;
            case PICK_PHOTO2:
                if(resultCode == RESULT_OK){
                    Log.e("path111111111",data+"");
                    if(Build.VERSION.SDK_INT >= 19){
                        handleImageOnKitKat(data,2);
                    }else{
                        handleImageBeforeKitKat(data,2);
                    }
                }
                isImage2 = 1;
                Message message4 = new Message();
                message4.what = 22;
                handler.sendMessage(message4);
                break;
            case TAKE_PHOTO3:
                if(resultCode == RESULT_OK){
                    Bitmap bitmap = null;
                    try {
                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    bitmap.createScaledBitmap(bitmap,100,100,true);
                    image3.setImageBitmap(bitmap);
//                    Glide.with(this).load(imageUri).into(image3);
                }
                isImage3 = 1;
                Message message5 = new Message();
                message5.what = 31;
                handler.sendMessage(message5);
                break;
            case PICK_PHOTO3:
                if(resultCode == RESULT_OK){
                    Log.e("path111111111",data+"");
                    if(Build.VERSION.SDK_INT >= 19){
                        handleImageOnKitKat(data,3);
                    }else{
                        handleImageBeforeKitKat(data,3);
                    }
                }
                isImage3 = 1;
                Message message6 = new Message();
                message6.what = 32;
                handler.sendMessage(message6);
                break;
            default:
                break;
        }
    }

    private void handleImageBeforeKitKat(Intent data,int i) {
        Uri uri = data.getData();
        String imagePathw = getImagePath(uri,null);
        displayImage(imagePathw,i);
    }

    private void displayImage(String imagePath,int i) {
        if(imagePath != null){
//            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            if(i == 1){
                imagePath1 = imagePath;
//                image1.setImageBitmap(bitmap);
                Glide.with(this).load(imagePath).into(image1);
            }else if(i ==2){
                imagePath2 = imagePath;
//                image2.setImageBitmap(bitmap);
                Glide.with(this).load(imagePath).into(image2);
            }else if(i ==3){
                imagePath3 = imagePath;
//                image3.setImageBitmap(bitmap);
                Glide.with(this).load(imagePath).into(image3);
            }
        }else {
            Toast.makeText(this,"failed to get image",Toast.LENGTH_SHORT).show();
        }
    }

    private String getImagePath(Uri uri, String selection) {

        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        Log.e("path",path);
        return path;
    }

    private void handleImageOnKitKat(Intent data,int i) {
        String imagePath = null;
        Uri uri = data.getData();
        Log.e("path123456",uri+"");
        if(DocumentsContract.isDocumentUri(this,uri)){
            String documentId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id  = documentId.split(":")[1];
                String selection = MediaStore.Images.Media._ID+"="+id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if("com.android.providers.downloads,documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(documentId));
                imagePath = getImagePath(contentUri,null);
            }
        }else if("content".equalsIgnoreCase(uri.getScheme())){
            imagePath = getImagePath(uri,null);
        }else if("file".equalsIgnoreCase(uri.getScheme())){
            imagePath = uri.getPath();
        }
        Log.e("path333",imagePath);
        displayImage(imagePath,i);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    openCamera();
                }else{
                    Snackbar.make(issue,"You denied the permission",Snackbar.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    openPicture();
                }else{
                    Snackbar.make(issue,"You denied the permission",Snackbar.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.issue_image1:
                if(isImage1 == 0){
                    selectPhoto(1);
                }else{
                    if(isImage2 == 0){
                        isImage1 = 0;
                    }
                    Message m1 = new Message();
                    m1.what = 13;
                    handler.sendMessage(m1);
                }
                break;
            case R.id.issue_image2:
                if(isImage2 == 0){
                    selectPhoto(2);
                }else{
                    if(isImage3 ==0){
                        isImage2 = 0;
                    }
                    Message m2 = new Message();
                    m2.what = 23;
                    handler.sendMessage(m2);
                }
                break;
            case R.id.issue_image3:
                if(isImage3 == 0){
                    selectPhoto(3);
                }else{
                    isImage3 = 0;
                    Message m3 = new Message();
                    m3.what = 33;
                    handler.sendMessage(m3);
                }
                break;
            case R.id.issueBack:
                finish();
                break;
            case R.id.issue_issue:
                if(issue_classify.equals("") || issue_school.equals("")){
                    Snackbar.make(issue,"请选择商品分类或学校",Snackbar.LENGTH_SHORT).show();
                }else{
                    Snackbar.make(issue,"suucess"+"-----"+issue_classify+"--"+issue_school+"\n"+content.getText().toString(),Snackbar.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 11:
                case 12:
                    preImage1 = msg.what;
                    image2.setVisibility(View.VISIBLE);
                    break;
                case 13:
                    if(isImage2 ==0){
                        image2.setVisibility(View.GONE);
                        image1.setImageResource(R.mipmap.issue);
                    }else if(isImage2 ==1){
                        if(isImage3 == 0){
                            image3.setVisibility(View.GONE);
                            image2.setImageResource(R.mipmap.issue);
                            isImage2 = 0;
                            Log.e("preImage",preImage2+"");
                            if(preImage2 == 21){
                                imageUri1 = imageUri2;
                                try {
                                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri1));
                                    image1.setImageBitmap(bitmap);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }else if(preImage2 == 22){
                                imagePath1 = imagePath2;
                                displayImage(imagePath1,1);
                            }
                        }
                        else if(isImage3 == 1){
                            image3.setImageResource(R.mipmap.issue);
                            isImage3 = 0;
                            if(preImage2 == 21){
                                imageUri1 = imageUri2;
                                try {
                                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri1));
                                    image1.setImageBitmap(bitmap);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }else if(preImage2 == 22){
                                imagePath1 = imagePath2;
                                displayImage(imagePath1,1);
                            }
                            if(preImage3 == 31){
                                imageUri2 = imageUri3;
                                try {
                                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri2));
                                    image2.setImageBitmap(bitmap);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }else if(preImage3 == 32){
                                imagePath2 = imagePath3;
                                displayImage(imagePath2,2);
                            }
                        }
                    }
                    break;
                case 21:
                case 22:
                    preImage2 = msg.what;
                    image3.setVisibility(View.VISIBLE);
                    break;
                case 23:
                    if(isImage3 == 0){
                        image3.setVisibility(View.GONE);
                        image2.setImageResource(R.mipmap.issue);
                        isImage2 = 0;
                    }else if(isImage3 == 1){
                        image3.setImageResource(R.mipmap.issue);
                        isImage3 = 0;
                        if(preImage3 == 31){
                            imageUri2 = imageUri3;
                            try {
                                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri2));
                                image2.setImageBitmap(bitmap);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }else if(preImage3 == 32){
                            imagePath2 = imagePath3;
                            displayImage(imagePath2,2);
                        }
                    }
                    break;
                case 31:
                case 32:
                    preImage3 = msg.what;
                    break;
                case 33:
                    image3.setImageResource(R.mipmap.issue);
                    isImage3 = 0;
                    break;
            }
        }
    };

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if ((System.currentTimeMillis() - mExitTime) > 2000) {
//                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
//                mExitTime = (int) System.currentTimeMillis();
//            } else {
//                ActivityCollector.finishAll();
//            }
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

}
