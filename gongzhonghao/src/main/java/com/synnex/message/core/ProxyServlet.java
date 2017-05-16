package com.synnex.message.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mmp.cq.utils.ApiUtils;
import com.mmp.cq.utils.SignUtil;

/**
 * Servlet implementation class CoreServlet
 */
public class ProxyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public ProxyServlet() {
	// TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
        String proxyUrl = request.getParameter("proxy_url");
        String urlType = request.getParameter("url_type");
        request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8"); 
        try {
            String string = ApiUtils.get(new URI(proxyUrl), null);
            if(null == string){
                byte[] post = ApiUtils.post(new URI(proxyUrl));
                
                if(null != post){
                     ServletOutputStream outputStream = response.getOutputStream();
                     outputStream.write(post);
                     outputStream.close();
                }
            }else{
                PrintWriter writer = response.getWriter();
                writer.print(string);
                writer.close();
            }
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	   // 将请求、响应的编码均设置为UTF-8（防止中文乱码）  
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println(parameterMap.keySet());
        
        request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
  
        // 调用核心业务类接收消息、处理消息  
        String respMessage = CoreService.processRequest(request);  
          
        // 响应消息  
        PrintWriter out = response.getWriter();  
        out.print(respMessage);  
        out.close();  
	
    }  
   
    
    
}
