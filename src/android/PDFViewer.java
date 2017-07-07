package com.ingensnetworks.plugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;

public class PDFViewer extends CordovaPlugin {

    private static final String TAG = "PDFViewerPlugin";
    private CallbackContext PUBLIC_CALLBACKS = null;
    private Integer showButtons = 0;
    private String cancel = "";
    private String ok = "";

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

      PUBLIC_CALLBACKS = callbackContext;
        String url = data.getString(0);

        if(data.length() > 1) {
          JSONObject options =  data.getJSONObject(1);
          showButtons = options.getInt("showButtons");
          cancel = options.getString("cancel");
          ok = options.getString("ok");
        }

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
        if (action.equals("1")) {
          PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, "1");
          pluginResult.setKeepCallback(true);
          PUBLIC_CALLBACKS.sendPluginResult(pluginResult);
        } else if (action.equals("0")) {
          PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, "0");
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
      intent.putExtra("showButtons", showButtons);
      intent.putExtra("filename", fileName);
      intent.putExtra("cancel", cancel);
      intent.putExtra("ok", ok);

      this.cordova.startActivityForResult((CordovaPlugin) this, intent, 0);
    }
}
