package com.mmp.cq.httpclient.test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;


/**
 * 有两种方式可以为request提供参数：request-line方式与request-body方式。
 * @author Huoyunren
 *
 */
public class HttpClientTest {

    HttpClient client  = HttpClients.createDefault();
    
    @Test
    public void testUrl() throws URISyntaxException{
        URIBuilder builder  =new URIBuilder();
        List<NameValuePair> list = new  ArrayList<NameValuePair>();
        NameValuePair nameValuePair1 = new BasicNameValuePair("A", "B");
        NameValuePair nameValuePair2 = new BasicNameValuePair("C", "D");
        NameValuePair nameValuePair3 = new BasicNameValuePair("name", "张三");
        list.add(nameValuePair1);
        list.add(nameValuePair2);
        list.add(nameValuePair3);
        
       URI build = builder.setScheme("http")
               .setHost("www.baidu.com")
               .setPath("/news")
               .setParameters(list)
               .setCharset(Charset.forName("UTF-8"))
               .build();

       System.out.println(build);
    }
    
    
    // get 在URL 中传递参数
    @Test
    public void testGetMethod() throws URISyntaxException{
        HttpGet getResquest = new HttpGet(getTestURLWithQuery());
        
        getResquest.addHeader("Header1", "h1");
        getResquest.addHeader("Header2", "h2");
        getResquest.addHeader("Header3", "h3");
        
        showHttpGetInfo(getResquest);
    }
    
    // post 在body 中传递参数
    @Test
    public void testPostMethod() throws URISyntaxException, ParseException, IOException{
        HttpPost postRequeset  = new HttpPost(getTestURL());
        
        postRequeset.addHeader("Header1", "h1");
        postRequeset.addHeader("Header2", "h2");
        postRequeset.addHeader("Header3", "h3");
        
        List formParams = new ArrayList();
        formParams.add(new BasicNameValuePair("param1", "中国"));
        formParams.add(new BasicNameValuePair("param2", "value2"));
        HttpEntity entity = new UrlEncodedFormEntity(formParams, "UTF-8");
        
        postRequeset.setEntity(entity);
        
        showHttpPostInfo(postRequeset);
    }
    
    /**
    1. POST /api/feed/ HTTP/1.1  
    2. Accept-Encoding: gzip  
    3. Content-Length: 225873  
    4. Content-Type: multipart/form-data; boundary=OCqxMF6-JxtxoMDHmoG5W5eY9MGRsTBp  
    5. Host: www.myhost.com  
    6. Connection: Keep-Alive  
    7.   
    8. --OCqxMF6-JxtxoMDHmoG5W5eY9MGRsTBp  
    9. Content-Disposition: form-data; name="lng"  
    10. Content-Type: text/plain; charset=UTF-8  
    11. Content-Transfer-Encoding: 8bit  
    12.   
    13. 116.361545  
    14. --OCqxMF6-JxtxoMDHmoG5W5eY9MGRsTBp  
    15. Content-Disposition: form-data; name="lat"  
    16. Content-Type: text/plain; charset=UTF-8  
    17. Content-Transfer-Encoding: 8bit  
    18.   
    19. 39.979006  
    20. --OCqxMF6-JxtxoMDHmoG5W5eY9MGRsTBp  
    21. Content-Disposition: form-data; name="images"; filename="/storage/emulated/0/Camera/jdimage/1xh0e3yyfmpr2e35tdowbavrx.jpg"  
    22. Content-Type: application/octet-stream  
    23. Content-Transfer-Encoding: binary  
    24.   
    25. 这里是图片的二进制数据  
     --OCqxMF6-JxtxoMDHmoG5W5eY9MGRsTBp-- 
     */
    @Test
    public void testPostMethodWithMulti() throws URISyntaxException, ParseException, IOException{
        HttpPost postRequeset  = new HttpPost(getTestURL());
        
        postRequeset.addHeader("Header1", "h1");
        postRequeset.addHeader("Header2", "h2");
        postRequeset.addHeader("Header3", "h3");
        
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        //image should be a String
        FileBody fileBody = new FileBody(new File("image")); 
        builder.addPart("my_file", fileBody); 
        
        HttpEntity entity = builder.build();
        postRequeset.setEntity(entity);
        
        showHttpPostInfo(postRequeset);
    }
    
    private URI getTestURLWithQuery() throws URISyntaxException{
        URIBuilder builder  =new URIBuilder();
        List<NameValuePair> list = new  ArrayList<NameValuePair>();
        NameValuePair nameValuePair1 = new BasicNameValuePair("A", "B");
        NameValuePair nameValuePair2 = new BasicNameValuePair("C", "D");
        NameValuePair nameValuePair3 = new BasicNameValuePair("name", "张三");
        list.add(nameValuePair1);
        list.add(nameValuePair2);
        list.add(nameValuePair3);
        
       return builder.setScheme("http")
               .setHost("www.baidu.com")
               .setPath("/news")
               .setParameters(list)
               .setCharset(Charset.forName("UTF-8"))
               .build();
    }
    
    private URI getTestURL() throws URISyntaxException{
        URIBuilder builder  =new URIBuilder();
        List<NameValuePair> list = new  ArrayList<NameValuePair>();
        NameValuePair nameValuePair1 = new BasicNameValuePair("A", "B");
        NameValuePair nameValuePair2 = new BasicNameValuePair("C", "D");
        NameValuePair nameValuePair3 = new BasicNameValuePair("name", "张三");
        list.add(nameValuePair1);
        list.add(nameValuePair2);
        list.add(nameValuePair3);
        
       return builder.setScheme("http")
               .setHost("www.baidu.com")
               //.setPath("/news")
               //.setParameters(list)
               .setCharset(Charset.forName("UTF-8"))
               .build();
    }
    
    @Test
    public void testExecuteGet() throws URISyntaxException{
        HttpGet get = new HttpGet(getTestURL());
        try {
          String  result = client.execute(get, new ResponseHandler<String>() {
                @Override
                public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                    showResposeInfo(response);
                    return "";
                   }
            });
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    
    
    public void showHttpGetInfo(HttpGet getMethod){
        System.out.println(getMethod.getRequestLine());
        HeaderIterator headerIterator = getMethod.headerIterator();
        while(headerIterator.hasNext()){
            System.out.println(headerIterator.nextHeader());
        }
    }
    
    public void showHttpPostInfo(HttpPost postMeThod) throws ParseException, IOException{
        System.out.println(postMeThod.getRequestLine());
        HeaderIterator headerIterator = postMeThod.headerIterator();
        
        while(headerIterator.hasNext()){
            System.out.println(headerIterator.nextHeader());
        }
        HttpEntity entity = postMeThod.getEntity();
        System.out.println(EntityUtils.toString(entity));
    }
    
    
    public void showResposeInfo(HttpResponse response) throws UnsupportedEncodingException, IOException{
       System.out.println(response.getStatusLine());
       Header[] allHeaders = response.getAllHeaders();
       for(Header h : allHeaders){
           System.out.println(h);
       }
       System.out.println(new String(EntityUtils.toByteArray(response.getEntity()), "utf-8"));
    }
    
}
