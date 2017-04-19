package com.mmp.cq.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class ApiUtils {

    private static HttpClient client = HttpClients.createDefault();


    public static String get(URI uri, Map<String, String> param) {
        URIBuilder builder = new URIBuilder(uri);
        if (null != param) {
            Set<Entry<String, String>> entrySet = param.entrySet();
            for (Entry<String, String> ent : entrySet) {
                builder.addParameter(ent.getKey(), ent.getValue());
            }
        }
        try {
            HttpGet request = new HttpGet(builder.build());
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, Charset.forName("utf-8"));
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
        catch (ClientProtocolException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static String post(URI uri, Map param) {
        HttpPost request = new HttpPost(uri);
        List formParams = new ArrayList();
        if (null != param) {
            Set<Entry<String, String>> entrySet = param.entrySet();
            for (Entry<String, String> ent : entrySet) {
                formParams.add(new BasicNameValuePair(ent.getKey(),ent.getValue()));
            }
        }
        try {
            if(!formParams.isEmpty()){
                HttpEntity entity = new UrlEncodedFormEntity(formParams, "UTF-8");
                request.setEntity(entity);
            }
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, Charset.forName("utf-8"));
        }
   
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch (ClientProtocolException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public static String post(URI uri ,String data){
        HttpPost request = new HttpPost(uri);
        HttpEntity requestEntity = new  StringEntity(data, Charset.forName("utf-8"));
        request.setEntity(requestEntity);
        HttpResponse response;
        try{
            response = client.execute(request);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, Charset.forName("utf-8"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
       return null;
    }
    
    public static String post(URI uri,File file){
        HttpPost request = new HttpPost(uri);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        FileBody fileBody = new FileBody(file); 
        builder.addPart("file_body",fileBody);
        HttpEntity requestEntity = builder.build();
        request.setEntity(requestEntity);
        HttpResponse response=null;
        try {
            response = client.execute(request);
            HttpEntity responseContent = response.getEntity();
            return EntityUtils.toString(responseContent, Charset.forName("utf-8"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
       
        return null;
    }   
}
