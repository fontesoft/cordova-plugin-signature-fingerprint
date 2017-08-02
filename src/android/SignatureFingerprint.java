package com.fontesoft.cordova;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;

public class SignatureFingerprint extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
      try {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }
        if (action.equals("getVersionCode")) {
          PackageManager packageManager = this.cordova.getActivity().getPackageManager();
          callbackContext.success(packageManager.getPackageInfo(this.cordova.getActivity().getPackageName(), 0).versionCode);
          return true;
        }
        return false;
      } catch (NameNotFoundException e) {
        callbackContext.success("N/A");
        return true;
      }
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}
