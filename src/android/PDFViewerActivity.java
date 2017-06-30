package com.ingensnetworks.plugin;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class PDFViewerActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    String package_name = getApplication().getPackageName();
    setContentView(getApplication().getResources().getIdentifier("activity_pdfviewer", "layout", package_name));
    Intent intent = getIntent();
    String name = intent.getStringExtra("filename");

    ActionBar actionbar = getActionBar();
    //actionbar.setHomeAsUpIndicator(R.mipmap.icon);
    //actionbar.setDisplayHomeAsUpEnabled(true);
    actionbar.setDisplayShowHomeEnabled(false);
    actionbar.setTitle("");

    PDFView pdfView = (PDFView)findViewById(getResources().getIdentifier("pdfView", "id", getPackageName()));
    File file = new File(name);

    if(file.exists()) {
      try {
        pdfView.fromFile(file)
          .defaultPage(0)
          .enableAntialiasing(true)
          .load();
      } catch (Exception ex) {
        int i = 0;
      }
    }

    Button btnSign = (Button)findViewById(getResources().getIdentifier("button1", "id", getPackageName()));
    btnSign.setOnClickListener( new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra("action", "sign");
        setResult(Activity.RESULT_OK, intent);
        finish();
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    menu.add(0, 0, 0, "Done").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      //case android.R.id.home:
      case 0:
        onBackPressed();
        return true;
    }
    return false;
  }

  @Override
  public void onBackPressed() {
    Intent intent = new Intent();
    intent.putExtra("action", "close");
    setResult(Activity.RESULT_OK, intent);
    finish();
  }
}