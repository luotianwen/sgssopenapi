package com.demo.common;

import com.alibaba.fastjson.JSON;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;


/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 * 
 * API 引导式配置
 */
public class DemoConfig extends JFinalConfig {
	
	static Prop p;
	
	/**
	 * 启动入口，运行此 main 方法可以启动项目，此 main 方法可以放置在任意的 Class 类定义中，不一定要放于此
	 */
	public static void main(String[] args) {
		  UndertowServer.start(DemoConfig.class);
		//String str="{\"total\":1,\"rows\":[  {\"wareHouseName\":\"成都特供仓\",\"sex\":\"男\",\"division\":\"服\",\"marketprice\":348.0,\"ukSize\":\"S\",\"articleno\":\"288254-010\",\"brandName\":\"耐克\",\"discount\":2.3,\"quarter\":\"\",\"innerNum\":500,\"size\":\"S\",\"barcode\":\"4056561268379\"},  {\"wareHouseName\":\"成都特供仓\",\"sex\":\"男\",\"division\":\"服\",\"marketprice\":1399.0,\"ukSize\":\"10\",\"articleno\":\"304775-125\",\"brandName\":\"耐克\",\"discount\":10.1,\"quarter\":\"15Q2\",\"innerNum\":500,\"size\":\"10\",\"barcode\":\"4056561268378\"}]}";
		//BackData j = JSON.parseObject(str, BackData.class);

	}

    @Override
    public void onStart() {
		System.out.println("接口启动完成");
        super.onStart();

    }

    /**
	 * PropKit.useFirstFound(...) 使用参数中从左到右最先被找到的配置文件
	 * 从左到右依次去找配置，找到则立即加载并立即返回，后续配置将被忽略
	 */
	static void loadConfig() {
		if (p == null) {
			p = PropKit.useFirstFound("demo-config-pro.txt", "demo-config-dev.txt");
		}
	}
	
	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		loadConfig();
		me.setDevMode(p.getBoolean("devMode", false));
		/**
		 * 支持 Controller、Interceptor、Validator 之中使用 @Inject 注入业务层，并且自动实现 AOP
		 * 注入动作支持任意深度并自动处理循环注入
		 */
		me.setInjectDependency(true);
		
		// 配置对超类中的属性进行注入
		me.setInjectSuperClass(true);
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
      me.add("openapi",OpenApi.class);
	}
	
	public void configEngine(Engine me) {
		/*me.addSharedFunction("/common/_layout.html");
		me.addSharedFunction("/common/_paginate.html");*/
	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {

	}
	
	public static DruidPlugin createDruidPlugin() {
		loadConfig();
		
		return new DruidPlugin(p.get("new.jdbcUrl"), p.get("new.user"), p.get("new.password").trim());
	}
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		
	}
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		
	}
}
