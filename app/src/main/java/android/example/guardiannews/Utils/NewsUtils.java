package android.example.guardiannews.Utils;

import android.example.guardiannews.data.News;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class NewsUtils {

    private NewsUtils(){}


   public static List<News> fetchNews(String requestUrl) {

        URL url = createUrl(requestUrl);

        String jsonResponse = "";

        try {

            jsonResponse = makeHttpsRequest(url);

        } catch (IOException e){

            Log.e("fetchNews_METHOD", "Problem making the HTTP request for the search criteria");

        }

        return NewsUtils.extractNews(jsonResponse);
    }


   private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);

        }catch (MalformedURLException e) {
            Log.e("createUrl_METHOD", "Problem building the url!");
        }

       return url;
    }

    public static String makeHttpsRequest(URL url) throws IOException {

        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;

        InputStream inputStream = null;


        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();

            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);

            } else {
                Log.e("Error", "Error response code:" + urlConnection.getResponseCode());
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {

        StringBuilder sb = new StringBuilder();
        if (inputStream != null) {

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();

            while (line != null) {
                sb.append(line);
                line = bufferedReader.readLine();

            }
        }
        return sb.toString();
    }


    private static ArrayList<News> extractNews(String jsonResponse) {

        if (TextUtils.isEmpty(jsonResponse)) {
            return null;
        }
        ArrayList<News> newsArrayLists = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);

            JSONObject response = jsonObject.getJSONObject("response");

            JSONArray results = response.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {

                JSONObject currentNewsObj = results.getJSONObject(i);


                String dateArticle="No date here Sigma ";

                if(currentNewsObj.has("webPublicationDate")) {
                    dateArticle = currentNewsObj.getString("webPublicationDate");
                }

                String sectionName = "";
                if (currentNewsObj.has("sectionName")) {

                    sectionName = currentNewsObj.getString("sectionName");
                }

                String webTitle = "";
                if (currentNewsObj.has("webTitle")) {
                    webTitle = currentNewsObj.getString("webTitle");
                }

                String webUrl="";
                if (currentNewsObj.has("webUrl"))
                {
                    webUrl=currentNewsObj.getString("webUrl");
                }




                JSONObject fields = currentNewsObj.getJSONObject("fields");

                String thumbnail = "";
                if (fields.has("thumbnail")) {
                    thumbnail = fields.getString("thumbnail");


                }

                News news = new News(sectionName, webTitle,dateArticle, thumbnail,webUrl);

                newsArrayLists.add(news);
            }


        } catch (JSONException e) {
            Log.e("ExtractNews ", "Error parsing json response:", e);
        }


        return newsArrayLists;
    }
}
