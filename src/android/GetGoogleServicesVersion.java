package com.bm.plugins;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.common.GoogleApiAvailability;

/**
 * This class receive a specified minimum Google Services version in order to compare it with the current one.
 * If the mobile's version is lower than expected, "false" value will be returned.
 * If mobile's version is equals or higher than the specified, "true" value will be returned.
 */
public class GetGoogleServicesVersion extends CordovaPlugin {
	
    private static final String TAG = "[Plug-in] getGservicesVersion:";

	/**
     * This method execute another specified method
     * @param 	action				It contains the method to execute
     * @param 	args  				It contains the variable(s) sent by GetGoogleServicesVersion.js
     * @param 	callbackContext		It helps to retrieve the app's context, also it helps to return successful or failed responses.
     * @returns	boolean
     */
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("isValidVersion")) {
            this.isValidVersion(args, callbackContext);
            return true;
        }
        return false;
    }

	/**
     * This method compares current mobile's Google Services version versus a specified version
     * @param 	message				It contains the specified version
     * @param 	callbackContext		It helps to retrieve the app's context, also it helps to return successful or failed responses.
     * @returns	"true"/"false"
     */
    private void isValidVersion(JSONArray args, CallbackContext callbackContext) {
		Context context = this.cordova.getActivity().getApplicationContext();
        JSONArray result = new JSONArray();
		try {
            long version = context.getPackageManager().getPackageInfo(GoogleApiAvailability.GOOGLE_PLAY_SERVICES_PACKAGE, 0).versionCode;
			long versionRequired = Long.parseLong(args.getString(0));
			result.put((version >= versionRequired)? "true" : "false");
			result.put(version);
			result.put(versionRequired);
        }
        catch(Exception e) {
			callbackContext.error(TAG + e.getMessage());
        }
        if (result != null && result.length() > 0) {
            callbackContext.success(result);
        } else {
            callbackContext.error(TAG + "Expected one non-empty string argument.");
        }
    }
}
