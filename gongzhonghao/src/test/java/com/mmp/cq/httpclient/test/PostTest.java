package com.mmp.cq.httpclient.test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.mmp.cq.utils.ApiUtils;

public class PostTest {

    @Test
    public void postParamTest() throws URISyntaxException{
        Map map =new HashMap();
        map.put("a", "b");
        ApiUtils.post(new URI("http://localhost:8080/gongzhonghao/CoreServlet?param=1"),map);
        // 请求得到的参数为  parma=1&a=b
    }
}
