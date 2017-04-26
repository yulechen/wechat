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
    
    public static String getUserInfo(String accessToken,String userid) throws URISyntaxException{
        //https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
        URI userInfoUrl = new URI("https://api.weixin.qq.com/cgi-bin/user/info");
        Map param = new HashMap();
        param.put("access_token", accessToken);
        param.put("openid", userid);
        param.put("lang", "zh_CN");
        String responesJson = ApiUtils.get(userInfoUrl, param);
        System.out.println("access_token_api_response:   "+responesJson);
        return responesJson;
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
      //  System.out.println(getAccessToken());
        String accessToken ="_Mr5ZIzorXhi92-oiodEWyzfMJ-adSlASM65fqugZTLy6CES1x3O4oRUmxjlmWsVPo3cmHhG9KyLDAk-BjplUgGYCGwATmeI0vRVEHQk80Qov95N0tpNOHwP_18vv7HWDLBdAFAJRH";
       // URL resource = WeiXinApiUtils.class.getResource("/weixindata/apimenu/create.json");
        //URL resource = WeiXinApiUtils.class.getResource("/weixindata/resource/add.json");
        //File file = new File(resource.getFile());
       // addResource(accessToken,file);
        
        System.out.println(getUserInfo(accessToken,"oCumrs8ghvroQqRk6bN-PKDvChrI"));
    }
}
