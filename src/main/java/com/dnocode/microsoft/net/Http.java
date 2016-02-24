package com.dnocode.microsoft.net;

import com.dnocode.microsoft.functions.FnMapToString;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Optional;

/**
 * Created by dino on 24/02/16.
 */
public class Http {


  public <T> Optional<T> postAsJson(String url , Map paramsMap,Class<T> clazz) {

      return post(url, paramsMap)
              .map(s ->new Gson().fromJson((String)s,clazz));
  }

  public Optional<String> post(String url,Map<String,String> paramsMap){
        return post(url,Optional.of(paramsMap).map(new FnMapToString()).get());
    }
   public  Optional<String> post(String targetURL, String urlParameters) {
        HttpURLConnection connection = null;
        try {
            //Create connection
            URL url = new URL(targetURL);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Length",Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty( "charset", "utf-8");
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            //Send request
            DataOutputStream wr = new DataOutputStream( connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.close();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+
            String line;
            while((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return Optional.of(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
    }
}
