package Model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIUtils {

    private HttpURLConnection conn;

    public void connect(String urlInput){
        try{
            URL connUrl = new URL(urlInput);
            conn = (HttpURLConnection) connUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getResponse(){
        BufferedReader reader;
        StringBuilder responseContent = new StringBuilder();
        try{
            int status = conn.getResponseCode();
            if (status > 299){
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            else {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }
            String line;
            while ((line = reader.readLine()) != null){
                responseContent.append(line);
            }
            reader.close();
        }
        catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
        return responseContent.toString();
    }

    public void closeConnection(){
        conn.disconnect();
    }
}