package com.paddy.tests;

import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity 
{	
	private Button butTakeScreenShot = null;
	private View content = null;

    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        setupEvents();
    }
    
    private void setupViews()
    {
    	butTakeScreenShot = (Button) findViewById(R.id.but_take_screenshot);
    	
    	content = findViewById(R.id.tv_hello_world);
        content.setDrawingCacheEnabled(true);
    }
    
    private void setupEvents()
    {
    	butTakeScreenShot.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{		
				getScreen(content);
				Toast.makeText(MainActivity.this, "Taking a screenshot... checkout /sdcard/test.png", Toast.LENGTH_LONG).show();
			}
		});
    }
    
    private void getScreen(View content)
    {
        Bitmap bitmap = content.getDrawingCache();
        File file = new File("/sdcard/test.png");
        try 
        {
            file.createNewFile();
            FileOutputStream ostream = new FileOutputStream(file);
            bitmap.compress(CompressFormat.PNG, 100, ostream);
            ostream.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
}
