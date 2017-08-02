package com.fontesoft.cordova;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import java.io.InputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SignatureFingerprint extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        try {
            if (action.equals("coolMethod")) {
                String message = args.getString(0);
                this.coolMethod(message, callbackContext);
                return true;
            }
            if (action.equals("getSignature")) {
                String packageName = this.cordova.getActivity().getPackageName();
                PackageManager pm = getPackageManager();
                int flags = PackageManager.GET_SIGNATURES;
                PackageInfo packageInfo = null;
                try {
                    packageInfo = pm.getPackageInfo(packageName, flags);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                Signature[] signatures = packageInfo.signatures;
                byte[] cert = signatures[0].toByteArray();
                InputStream input = new ByteArrayInputStream(cert);
                CertificateFactory cf = null;
                try {
                    cf = CertificateFactory.getInstance("X509");
                } catch (CertificateException e) {
                    e.printStackTrace();
                }
                X509Certificate c = null;
                try {
                    c = (X509Certificate) cf.generateCertificate(input);
                } catch (CertificateException e) {
                    e.printStackTrace();
                }
                String hexString = null;
                try {
                    MessageDigest md = MessageDigest.getInstance("SHA1");
                    byte[] publicKey = md.digest(c.getEncoded());
                    hexString = byte2HexFormatted(publicKey);
                } catch (NoSuchAlgorithmException e1) {
                    e1.printStackTrace();
                } catch (CertificateEncodingException e) {
                    e.printStackTrace();
                }
                callbackContext.success(hexString);
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
