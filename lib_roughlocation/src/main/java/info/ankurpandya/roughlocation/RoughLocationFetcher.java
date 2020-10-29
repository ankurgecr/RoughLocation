package info.ankurpandya.roughlocation;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

/**
 * Create by Ankur @ Worktable.sg
 */
public final class RoughLocationFetcher {

    private static final String TAG = "RF_LOCATION";
    private static boolean DEBUG = false;

    public static void setDebug(boolean debug) {
        DEBUG = debug;
    }

    private static class API {
        private final String url;
        private final int type;

        public API(String url, int type) {
            this.url = url;
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public int getType() {
            return type;
        }
    }

    private static final API API_LOCATION_DETAILS = new API(
            "http://ip-api.com/json",
            Request.Method.GET
    );

    private RoughLocationFetcher() {
        //No default construction
    }

    public static void getLocationInBackground(Context context, final LocationListener listener) {
        if (context == null) {
            if (DEBUG) {
                Log.e(TAG, "Location failed due to NULL Context passed!");
            }
            listener.onLocationFailed();
            return;
        }
        if (!isNetworkConnected(context)) {
            listener.onNetworkError();
            return;
        }
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(
                API_LOCATION_DETAILS.type,
                API_LOCATION_DETAILS.url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        RoughLocation roughLocation = null;
                        try {
                            roughLocation = new Gson().fromJson(response, RoughLocation.class);
                        } catch (Exception e) {
                            if (DEBUG) {
                                e.printStackTrace();
                            }
                        }

                        if (roughLocation != null) {
                            if (DEBUG) {
                                Log.d(TAG, "Location fetch success: [" + roughLocation.toString() + "]");
                            }
                            if (listener != null) {
                                listener.onLocationReceived(roughLocation);
                            }
                        } else {
                            if (DEBUG) {
                                Log.e(TAG, "Location fetch failed. Check the above exception.");
                            }
                            if (listener != null) {
                                listener.onLocationFailed();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (DEBUG) {
                    error.printStackTrace();
                    Log.e(TAG, "Location fetch failed. Check the above exception.");
                }
                if (listener != null) {
                    listener.onLocationFailed();
                }
            }
        });
        queue.add(stringRequest);
    }

    private static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        return true;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        return true;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                        return true;
                    }
                }
            } else {
                try {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                        return true;
                    }
                } catch (Exception e) {
                    if (DEBUG) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }
}
