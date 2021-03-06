package com.teaboot.web;

import com.teaboot.context.anno.PackageScan;
import com.teaboot.context.beans.BeanCollections;
import com.teaboot.context.common.PropertiesManager;
import com.teaboot.context.resolver.AnnoScanResolver;
import com.teaboot.context.resolver.DefaultAnnoScanResolver;
import com.teaboot.context.utils.StringUtil;
import com.teaboot.web.anno.EnableMvc;
import com.teaboot.web.filter.WebHttpUrlFilter;
import com.teaboot.web.manager.WebManager;
import com.teaboot.web.server.HttpServer;
import com.teaboot.web.session.ServerContext;

/**
 * 配置注解生效
 *
 */
public class WebConfiguration {
	public static void config(Class clz) throws Exception {
		EnableMvc enableMvc = (EnableMvc) clz.getAnnotation(EnableMvc.class);
		String portStr = enableMvc.value();
		if(StringUtil.isEmpty(portStr)){
			portStr = PropertiesManager.get("server.port");
			portStr = (portStr == null ? "" : portStr);
		}
		int port = 8080;
		if (portStr.matches("[0-9]+")) {
			port = Integer.parseInt(portStr);
		}
		ServerContext.registerFilter(WebHttpUrlFilter.class);
		HttpServer httpServer = new HttpServer(port);
		WebManager.addServer(httpServer);
		
	}
}
