package com.ingensnetworks.plugin;

import java.io.File;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

public class PDFViewer extends CordovaPlugin {

    private static final String TAG = "PDFViewerPlugin";
    private CallbackContext PUBLIC_CALLBACKS = null;

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

      PUBLIC_CALLBACKS = callbackContext;
        String url = data.getString(0);

        if (action.equals("showPDF")) {
        	showPDF(url);
        }
        else if(action.equals("close")) {
            close();
        }
        PluginResult pluginResult = new  PluginResult(PluginResult.Status.NO_RESULT);
        pluginResult.setKeepCallback(true);
        PUBLIC_CALLBACKS.sendPluginResult(pluginResult);
        return true;
    }

    private void close() {
        // not implemented
    }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if(data != null) {
      String action = data.getStringExtra("action");
      if(action != null) {
        if (action.equals("sign")) {
          PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, "signed");
          pluginResult.setKeepCallback(true);
          PUBLIC_CALLBACKS.sendPluginResult(pluginResult);
        } else if (action.equals("close")) {
          PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, "closed");
          pluginResult.setKeepCallback(true);
          PUBLIC_CALLBACKS.sendPluginResult(pluginResult);
        }
      }
    }

    super.onActivityResult(requestCode, resultCode, data);
  }

    private void showPDF(final String fileName) {
      Context context=this.cordova.getActivity().getApplicationContext();
      Intent intent=new Intent(context,PDFViewerActivity.class);
      intent.putExtra("filename", fileName);
      this.cordova.startActivityForResult((CordovaPlugin) this, intent, 0);        
    }
}
