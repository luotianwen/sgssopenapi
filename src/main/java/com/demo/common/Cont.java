package com.demo.common;


import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class Cont {

    public static Map ArrowSign=new HashMap();
    public static String SIGN="e509e958b76ae27326680c09b118c69e";
    //商品
    public static String PRODUCT="http://open.xingyunyezi.com/api/openapi/getCommodityList.do";
    //货源
    public static String BAND="http://open.xingyunyezi.com/api/openapi/getWareHouseNameInfo.do";
    //库存
    public static String STOCK="http://open.xingyunyezi.com/api/openapi/getInventoryList.do";
   //根据条件获取库存
    public static String GROUPSTOCK="http://open.xingyunyezi.com/api/openapi/getInventoryListByGroup.do";
    //快递
    public static String DELIVER="http://open.xingyunyezi.com/api/openapi/getOrderFeedBackList.do";
    //下单
    public static String ORDER="http://open.xingyunyezi.com/api/openapi/setOrderInfos.do";

    public static int SECONDS=500;
    public static void ThreadSleep(){
        try {
            long x = System.nanoTime();
            if(x%2==0) {
                Thread.sleep(SECONDS);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static {
        ArrowSign.put("e509e958b76ae2667326680c5679e","dlyb");
    }
    /**
     * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
     */
    public static String post(String url,Map<String, String> params) {
        String str="";
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(url);
        // 创建参数队列
        List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
        Iterator<String> it = params.keySet().iterator();
        while (it.hasNext()) {
            Object key = it.next();
            formparams.add(new BasicNameValuePair(key.toString(), params.get(key.toString())));
        }


        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);
            //System.out.println("executing request " + httppost.getURI());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    str=EntityUtils.toString(entity, "UTF-8");
                    /*System.out.println("--------------------------------------");
                    System.out.println("Response content: " + str);
                    System.out.println("--------------------------------------");*/
                }
            } finally {
                response.close();
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return str;
    }
        public static void main(String[]arg){
            String size="A/2XL".replace("A/","").replace("A","");
            System.out.println(size);
           /*String str=" [{\"total\":45276,\"rows\":[\n" +
                   "  {\"colour\":\"红色\",\"id\":2,\"sex\":\"女\",\"division\":\"鞋\",\"brandname\":\"阿迪达斯\",\"marketprice\":799.00,\"listingdate\":\"2014-11-01\",\"articleno\":\"M18120\",\"descr\":\"颜色分类:红色;鞋码:38\",\"quarter\":\"2014Q4\"}\n" +
                   "]}]";*/
           /* List<BackData> j=JSON.parseArray(str,BackData.class);
            System.out.println(j.get(0).getError_code());
            System.out.println(j.get(0).getTotal());*/
            //System.out.println(j.get(0).getRows().get(0).getId());
            //BackData b=JSON.parseObject(str,BackData.class);
           // System.out.println(b);

        }
}
