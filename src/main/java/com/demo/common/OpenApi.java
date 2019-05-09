package com.demo.common;

import com.alibaba.fastjson.JSON;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;

import java.util.HashMap;
import java.util.Map;

public class OpenApi extends Controller {



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
      if(!f){
          Map map = new HashMap();
          map.put("sign", Cont.SIGN);
          map.put("wareHouseName", "天马总仓1仓");
          map.put("articleno",articleno.trim());
          String str =Cont.post(Cont.STOCK, map);
          BackData j = JSON.parseObject(str, BackData.class);
          if (j.getRows() != null && j.getRows().size() > 0) {
            renderJson(j);
          }
          else{
              renderJson(j);
          }
      }
  }


}
