package cornelluniversity.dribbble.dribbble.auth;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Ruihao on 8/16/17.
 */

public class Auth {

    public static final int REQ_CODE = 100;

    private static final String KEY_CLIENT_ID = "client_id";
    private static final String KEY_REDIRECT_URL = "redirect_uri";
    private static final String KEY_SCOPE = "scope";
    private static final String KEY_CLIENT_SECRET = "client_secret";
    private static final String KEY_CODE = "code";
    private static final String KEY_ACCESS_TOKEN = "access_token";

    private static final String CLIENT_ID = "88f48ce5e74dadf2d84392be72ae2391b9ef7c3914974db0ed1c54a24130f687";
    private static final String CLIENT_SECRET = "e4a31e46e9b77003ce1f23b4e3c2fcb0481db7c23fad3bdef9e3b25ad5cde440";
    private static final String SCOPE = "public+write";

    private static final String URI_AUTHORIZE = "https://Dribbble.com/oauth/authorize";
    private static final String URI_TOKEN = "https://Dribbble.com/oauth/token";
    public static final String REDIRECT_URI = "http://www.dribbbo.com";

    private static String getAuthorizeUrl() {
        String url = Uri.parse(URI_AUTHORIZE)
                .buildUpon()
                .appendQueryParameter(KEY_CLIENT_ID, CLIENT_ID)
                .build()
                .toString();

        url += "&" + KEY_REDIRECT_URL + "=" + REDIRECT_URI;
        url += "&" + KEY_SCOPE + "=" + SCOPE;
        return url;
    }

    public static void openAuthActivity(Activity activity) {
        Intent intent = new Intent(activity, AuthActivity.class);
        intent.putExtra(AuthActivity.KEY_URL, getAuthorizeUrl());

        activity.startActivityForResult(intent, REQ_CODE);
    }

    public static String fetchAccessToken(String authCode) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add(KEY_CLIENT_ID, CLIENT_ID)
                .add(KEY_CLIENT_SECRET, CLIENT_SECRET)
                .add(KEY_CODE, authCode)
                .add(KEY_REDIRECT_URL, REDIRECT_URI)
                .build();

        Request request = new Request.Builder()
                .url(URI_TOKEN)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        String responseString = response.body().string();

        try {
            JSONObject object = new JSONObject(responseString);
            return object.getString(KEY_ACCESS_TOKEN);
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }
}

