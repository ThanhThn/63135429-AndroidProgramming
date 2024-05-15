package ntu.lhqthanh_63135429.api;

import android.os.AsyncTask;
import android.util.Pair;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ZingMP3Api {
    String VERSION, API_KEY, CTIME, URL, SECRET_KEY;

    public ZingMP3Api(String VERSION, String API_KEY, String CTIME, String URL, String SECRET_KEY) {
        this.VERSION = VERSION;
        this.API_KEY = API_KEY;
        this.CTIME = CTIME;
        this.URL = URL;
        this.SECRET_KEY = SECRET_KEY;
    }

    public ZingMP3Api() {
        this.VERSION = "1.10.28";
        this.API_KEY = "88265e23d4284f25963e6eedac8fbfa3";
        this.URL = "https://zingmp3.vn";
        this.CTIME = String.valueOf(System.currentTimeMillis() / 1000);
        this.SECRET_KEY = "2aa2d1c561e809b267f3638c4a307aab";
    }

    private String getHash256(String str) throws NoSuchAlgorithmException{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(str.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash){
            String hex = Integer.toHexString(0xff & b);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return  hexString.toString();
    }
    private String getHmac512(String str, String key) throws Exception{
        Mac hmac512 = Mac.getInstance("HmacSHA512");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA512");
        hmac512.init(secretKeySpec);
        byte[] bytes = hmac512.doFinal(str.getBytes());
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes){
            stringBuilder.append(String.format("%02x", b));
        }
        return  stringBuilder.toString();
    }
    private String hashParamNoId(String path) throws Exception{
        return getHmac512(path + getHash256("ctime=" + this.CTIME + "version=" + this.VERSION), this.SECRET_KEY);
    }
    private String hashParam(String path, String id) throws Exception {
        return getHmac512(
                path + getHash256("ctime=" + this.CTIME + "id=" + id + "version=" + this.VERSION),
                this.SECRET_KEY
        );
    }
    private String hashParamHome(String path) throws Exception {
        return getHmac512(
                path + getHash256("count=30ctime=" + this.CTIME + "page=1version=" + this.VERSION),
                this.SECRET_KEY
        );
    }
    private String hashCategoryMV(String path, String id, String type) throws Exception {
        return getHmac512(
                path + getHash256("ctime=" + this.CTIME + "id=" + id + "type=" + type + "version=" + this.VERSION),
                this.SECRET_KEY
        );
    }
    private String hashListMV(String path, String id, String type, String page, String count) throws Exception {
        return getHmac512(
                path + getHash256("count=" + count + "ctime=" + this.CTIME + "id=" + id + "page=" + page + "type=" + type + "version=" + this.VERSION),
                this.SECRET_KEY
        );
    }
    private String getCookie() throws IOException {
        try {
            return new NetworkTask<String>().execute(new Pair<>(this.URL, "")).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
    private String requestZingMp3(String path, okhttp3.HttpUrl.Builder urlBuilder) throws Exception {
        String cookie = getCookie();
        urlBuilder.addQueryParameter("ctime", this.CTIME);
        urlBuilder.addQueryParameter("version", this.VERSION);
        urlBuilder.addQueryParameter("apiKey", this.API_KEY);

        try {
            return new NetworkTask<HttpUrl>().execute(new Pair<>(urlBuilder.build(), cookie)).get();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    // getSong
    public String getSong(String songId) throws Exception {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(this.URL + "/api/v2/song/get/streaming").newBuilder();
        urlBuilder.addQueryParameter("id", songId);
        urlBuilder.addQueryParameter("sig", hashParam("/api/v2/song/get/streaming", songId));
        return requestZingMp3("/api/v2/song/get/streaming", urlBuilder);
    }
    // getDetailPlaylist
    public String getDetailPlaylist(String playlistId) throws Exception {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(this.URL + "/api/v2/page/get/playlist").newBuilder();
        urlBuilder.addQueryParameter("id", playlistId);
        urlBuilder.addQueryParameter("sig", hashParam("/api/v2/page/get/playlist", playlistId));
        return requestZingMp3("/api/v2/page/get/playlist", urlBuilder);
    }
    // getHome
    public String getHome() throws Exception {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(this.URL + "/api/v2/page/get/home").newBuilder();
        urlBuilder.addQueryParameter("page", "1");
        urlBuilder.addQueryParameter("segmentId", "-1");
        urlBuilder.addQueryParameter("count", "30");
        urlBuilder.addQueryParameter("sig", hashParamHome("/api/v2/page/get/home"));
        return requestZingMp3("/api/v2/page/get/home", urlBuilder);
    }
    // getTop100
    public String getTop100() throws Exception{
        HttpUrl.Builder urlBuilder = HttpUrl.parse(this.URL + "/api/v2/page/get/top-100").newBuilder();
        urlBuilder.addQueryParameter("sig", hashParamNoId("/api/v2/page/get/top-100"));
        return requestZingMp3("/api/v2/page/get/top-100", urlBuilder);
    }
    // getChartHome
    public String getChartHome() throws Exception{
        HttpUrl.Builder urlBuilder = HttpUrl.parse(this.URL + "/api/v2/page/get/chart-home").newBuilder();
        urlBuilder.addQueryParameter("sig", hashParamNoId("/api/v2/page/get/chart-home"));
        return requestZingMp3("/api/v2/page/get/chart-home", urlBuilder);
    }
    // getNewReleaseChart
    public String getNewReleaseChart() throws Exception{
        HttpUrl.Builder urlBuilder = HttpUrl.parse(this.URL + "/api/v2/page/get/newrelease-chart").newBuilder();
        urlBuilder.addQueryParameter("sig", hashParamNoId("/api/v2/page/get/newrelease-chart"));
        return requestZingMp3("/api/v2/page/get/newrelease-chart", urlBuilder);
    }
    // getInfoSong
    public String getInfoSong(String songId) throws Exception{
        HttpUrl.Builder urlBuilder = HttpUrl.parse(this.URL + "/api/v2/song/get/info").newBuilder();
        urlBuilder.addQueryParameter("id", songId);
        urlBuilder.addQueryParameter("sig", hashParam("/api/v2/song/get/info", songId));
        return requestZingMp3("/api/v2/song/get/info", urlBuilder);
    }
    // getListArtistSong
    public String getListArtistSong(String artistId, String page, String count) throws Exception{
        HttpUrl.Builder urlBuilder = HttpUrl.parse(this.URL + "/api/v2/song/get/list").newBuilder();
        urlBuilder.addQueryParameter("id", artistId);
        urlBuilder.addQueryParameter("type", "artist");
        urlBuilder.addQueryParameter("page", page);
        urlBuilder.addQueryParameter("count", count);
        urlBuilder.addQueryParameter("sort", "new");
        urlBuilder.addQueryParameter("sectionId", "aSong");
        urlBuilder.addQueryParameter("sig", hashListMV("/api/v2/song/get/list", artistId, "artist", page, count));
        return requestZingMp3("/api/v2/song/get/list", urlBuilder);
    }
    // getArtist
    public String getArtist(String name) throws Exception{
        HttpUrl.Builder urlBuilder = HttpUrl.parse(this.URL + "/api/v2/page/get/artist").newBuilder();
        urlBuilder.addQueryParameter("alias", name);
        urlBuilder.addQueryParameter("sig", hashParamNoId("/api/v2/page/get/artist"));
        return requestZingMp3("/api/v2/page/get/artist", urlBuilder);
    }
    // getLyric
    public String getLyric (String songId) throws Exception{
        HttpUrl.Builder urlBuilder = HttpUrl.parse(this.URL + "/api/v2/lyric/get/lyric").newBuilder();
        urlBuilder.addQueryParameter("id", songId);
        urlBuilder.addQueryParameter("sig", hashParam("/api/v2/lyric/get/lyric", songId));
        return requestZingMp3("/api/v2/lyric/get/lyric", urlBuilder);
    }
    // search
    public String search (String name) throws Exception{
        HttpUrl.Builder urlBuilder = HttpUrl.parse(this.URL + "/api/v2/search/multi").newBuilder();
        urlBuilder.addQueryParameter("q", name);
        urlBuilder.addQueryParameter("sig", hashParamNoId("/api/v2/search/multi"));
        return requestZingMp3("/api/v2/search/multi", urlBuilder);
    }
    // getListMV
    public String getListMV (String id, String page, String count) throws Exception{
        HttpUrl.Builder urlBuilder = HttpUrl.parse(this.URL + "/api/v2/video/get/list").newBuilder();
        urlBuilder.addQueryParameter("id", id);
        urlBuilder.addQueryParameter("type", "genre");
        urlBuilder.addQueryParameter("page", page);
        urlBuilder.addQueryParameter("count", count);
        urlBuilder.addQueryParameter("sort", "listen");
        urlBuilder.addQueryParameter("sig", hashListMV("/api/v2/video/get/list", id, "genre", page, count));
        return requestZingMp3("/api/v2/video/get/list", urlBuilder);
    }
    // getCategoryMV
    public String getCategoryMV (String id) throws  Exception{
        HttpUrl.Builder urlBuilder = HttpUrl.parse(this.URL + "/api/v2/genre/get/info").newBuilder();
        urlBuilder.addQueryParameter("id", id);
        urlBuilder.addQueryParameter("type", "video");
        urlBuilder.addQueryParameter("sig", hashCategoryMV("/api/v2/genre/get/info", id, "video"));
        return requestZingMp3("/api/v2/genre/get/info", urlBuilder);
    }
    // getVideo
    public String getVideo (String videoId) throws Exception{
        HttpUrl.Builder urlBuilder = HttpUrl.parse(this.URL + "/api/v2/page/get/video").newBuilder();
        urlBuilder.addQueryParameter("id", videoId);
        urlBuilder.addQueryParameter("sig", hashParam("/api/v2/page/get/video", videoId));
        return requestZingMp3("/api/v2/page/get/video", urlBuilder);
    }


    private class NetworkTask<T> extends AsyncTask<Pair<T, String>, Void, String> {
        @Override
        protected String doInBackground(Pair<T, String>... params) {
            if (params.length == 0) return null;

            if (params[0].first instanceof HttpUrl) {
                HttpUrl url = (HttpUrl) params[0].first;
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(url)
                            .header("Cookie", params[0].second)
                            .build();

                    Response response = client.newCall(request).execute();
                    return response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (params[0].first instanceof String) {
                String urlString = (String) params[0].first;
                try {
                    HttpUrl url = HttpUrl.parse(urlString);
                    if (url == null) {
                        return null;
                    }
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(url)
                            .build();

                    Response response = client.newCall(request).execute();
                    if (response.headers("Set-Cookie").size() > 1) {
                        return response.headers("Set-Cookie").get(1);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }
    }

}
