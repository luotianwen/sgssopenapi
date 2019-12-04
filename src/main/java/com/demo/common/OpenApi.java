package com.demo.common;

import com.alibaba.fastjson.JSON;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenApi extends Controller {
 Log log=Log.getLog(OpenApi.class);

  public void getInventoryList(){
      String sign=getPara("sign");
      String articleno=getPara("articleno");
      boolean f=false;
      if(StrKit.isBlank(sign)&&!f){
        if(null==Cont.ArrowSign.get(sign)){
            //{"error_code":"1","error_info":"您没有被授权访问此API，请联系客服！"}
            BackData b=new BackData();
            b.setError_code("1");
            b.setError_info("您没有被授权访问此API，请联系客服！");
            f=true;
            renderJson(b);
        }
      }
      if(StrKit.isBlank(articleno)&&!f) {
          BackData b=new BackData();
          b.setError_code("100");
          b.setError_info("货号不能为空");
          f=true;
          renderJson(b);
      }
      if(!f) {
          Map map = new HashMap();
          map.put("sign", Cont.SIGN);
          map.put("articleno", articleno.trim());
          map.put("pickingRate", PropKit.get("pickingRate"));
          map.put("maxDiscount",PropKit.get("maxDiscount"));
          map.put("return_type",PropKit.get("return_type"));
          String str = Cont.post(Cont.GROUPSTOCK, map);
          log.info(str);
          //System.out.println(str);
          // String str="{\"total\":1,\"rows\":[  {\"wareHouseName\":\"成都特供仓\",\"sex\":\"男\",\"division\":\"服\",\"marketprice\":348.0,\"ukSize\":\"S\",\"articleno\":\"288254-010\",\"brandName\":\"耐克\",\"discount\":2.3,\"quarter\":\"\",\"innerNum\":500,\"size\":\"S\",\"barcode\":\"4056561268379\"},  {\"wareHouseName\":\"成都特供仓\",\"sex\":\"男\",\"division\":\"服\",\"marketprice\":1399.0,\"ukSize\":\"10\",\"articleno\":\"304775-125\",\"brandName\":\"耐克\",\"discount\":10.1,\"quarter\":\"15Q2\",\"innerNum\":500,\"size\":\"10\",\"barcode\":\"4056561268378\"}]}";
        // String str="{\"error_code\":\"1\",\"error_info\":\"您没有被授权访问此API，请联系客服！\"}";
          BackData j = JSON.parseObject(str, BackData.class);
          Map<String,Stock> dataMap=new HashMap();
          if (j.getRows() != null && j.getRows().size() > 0) {
              BackData backData=j;
              List<Stock> ss=j.getRows();
              List<Stock>datass=new ArrayList<>();
              for (Stock s:ss
                   ) {
                  if(null==dataMap.get(s.getArticleno()+"||"+s.getSize())){

                      datass.add(s);
                      dataMap.put(s.getArticleno()+"||"+s.getSize(),s);
                  }
              }
              backData.setRows(datass);
              backData.setTotal(datass.size());
              renderJson(backData);
          } else {
              renderJson(j);
          }
      }
  }

}
