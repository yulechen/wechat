package com.mmp.cq.weixin.api;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.mmp.cq.utils.ApiUtils;
import com.mmp.cq.utils.GsonUtil;

public class WeiXinApiUtils {

    
    public static String getAccessToken() throws URISyntaxException{
        URI accessTokenURI = new URI("https://api.weixin.qq.com/cgi-bin/token");
        Map param = new HashMap();
        param.put("grant_type", "client_credential");
        param.put("appid", "wx552f9b0b4fa40829");
        param.put("secret", "b749193534b1c9a5750002a931793759");
        String responesJson = ApiUtils.get(accessTokenURI, param);
        System.out.println("access_token_api_response:   "+responesJson);
        Map<String,String> map = GsonUtil.toObject(responesJson, Map.class);
        return map.get("access_token"); 
    }
    
    public static void createMenu(String accessToken,File menu) throws URISyntaxException{
        URI createMenu = new URI("https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+accessToken);
        String responesJson = ApiUtils.post(createMenu, menu);
        System.out.println(responesJson);
    }
    
    public static void addResource(String accessToken,File res) throws URISyntaxException{
        URI addResource = new URI("https://api.weixin.qq.com/cgi-bin/material/add_news?access_token="+accessToken);
        String responesJson = ApiUtils.post(addResource, res);
        System.out.println(responesJson);
    }
    
    public static void main(String[] args) throws URISyntaxException {
       // System.out.println(getAccessToken());
        String accessToken ="zEADgS3-k73TATUsn0GbsZS1a_cgdl-ODoiGPwKgMonc_NGQm0LcSB6oV7UM-Zr6dCejIjkbsNo85W7bYuSRyxW9X0diNt9pEvxCR--CEEdms1hmGqjRIkXc7ttO7txxFHGeAFAKML";
       // URL resource = WeiXinApiUtils.class.getResource("/weixindata/apimenu/create.json");
        URL resource = WeiXinApiUtils.class.getResource("/weixindata/resource/add.json");
        File file = new File(resource.getFile());
        addResource(accessToken,file);
    }
}
