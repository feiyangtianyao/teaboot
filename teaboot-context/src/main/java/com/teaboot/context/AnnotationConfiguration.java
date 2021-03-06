package com.teaboot.context;

import com.teaboot.context.anno.PackageScan;
import com.teaboot.context.beans.BeanCollections;
import com.teaboot.context.resolver.AnnoScanResolver;
import com.teaboot.context.resolver.DefaultAnnoScanResolver;
import com.teaboot.context.utils.StringUtil;

/**
 * 配置注解生效
 *
 */
public class AnnotationConfiguration 
{
    public static void config(Class clz) throws Exception
    {
    	PackageScan packageScan = (PackageScan) clz.getAnnotation(PackageScan.class);
		String packageName = packageScan.value();
		if(StringUtil.isEmpty(packageName)){
			packageName = clz.getPackage().getName();
		}
		AnnoScanResolver annoScanResolver = new DefaultAnnoScanResolver();
		annoScanResolver.annotationAnalysis(packageName);
		BeanCollections.getInstance().genTips();
    }
}
