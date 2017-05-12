package com.share.core.component.internal;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.share.core.annotation.Require;
import com.share.core.util.FileSystem;
import com.share.core.util.JSONObject;
import com.share.core.util.StringUtil;
import com.share.core.util.SystemUtil;

/**
 * 自动生成文档
 */
@Component
public class ApiDoc {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(ApiDoc.class);
	/**
	 * 文档根目录
	 */
	private final static String basePath = FileSystem.getPropertyString("apidoc.basePath");
	/**
	 * 文档标题
	 */
	private final static String title = FileSystem.getPropertyString("apidoc.title");
	/**
	 * 文档图标
	 */
	private final static String favicon = FileSystem.getPropertyString("apidoc.favicon");

	/**
	 * 私有化构造函数
	 */
	private ApiDoc() {
		FileSystem.delete(basePath);
		FileSystem.mkdir(basePath);
	}

	@PostConstruct
	public void init() {
		Set<Class<?>> classSet = SystemUtil.getClasses("com.share.test.controller");
		if (classSet == null || classSet.isEmpty()) {
			return;
		}

		// 生成首页
		index(classSet);

		// 生成各个文档
		for (Class<?> controller : classSet) {
			doc(classSet, controller);
		}
	}

	private void index(Set<Class<?>> classSet) {
		FileSystem.delete(basePath);
		FileSystem.mkdir(basePath);
		StringBuilder html = new StringBuilder();
		html.append(head());
		html.append(category(classSet));
		html.append(foot());
		FileSystem.write(basePath + "/index.html", html.toString(), true);
	}

	private void doc(Set<Class<?>> classSet, Class<?> controller) {
		StringBuilder html = new StringBuilder();
		html.append(head());
		html.append(category(classSet, controller));

		// 模块说明
		Controller controllerAnnotation = controller.getAnnotation(Controller.class);
		String moduleName = StringUtil.getString(controllerAnnotation.value());
		if (moduleName.isEmpty()) {
			moduleName = controller.getSimpleName();
			moduleName = moduleName.replaceAll("Controller", "").toLowerCase() + "模块";
		}
		html.append("<header>");
		html.append("<h1>");
		html.append(moduleName);
		html.append("</h1>");
		html.append("<hr>");
		html.append("</header>");

		for (Method method : controller.getDeclaredMethods()) {
			html.append(method(controller, method));
		}

		html.append(foot());
		FileSystem.write(basePath + "/" + controller2FileName(controller), html.toString(), true);
	}

	private String method(Class<?> controller, Method method) {
		StringBuilder html = new StringBuilder();
		html.append(apiTitle(controller, method));
		html.append(param(method));
		html.append(returns(method));
		return html.toString();
	}

	private String returns(Method method) {
		try {
			StringBuilder html = new StringBuilder();
			html.append("<pre class=\"sh_sourceCode\">");
			Class<?> responseObject = method.getReturnType();
			Object obj = responseObject.newInstance();

			// 通过分析代码，找出字段的注释
			responseObject2FileContent(responseObject);

			html.append(parseResponseObject(obj));
			html.append("</pre>");
			return html.toString();
		} catch (Exception e) {
			logger.error("", e);
			System.exit(0);
		}
		return "";
	}

	private String parseResponseObject(Object obj) {
		StringBuilder html = new StringBuilder();
		String json = JSONObject.encode(obj);
		json = formatJson(json);
		json = json.replaceAll("\\:\\s+\\{", "\\:\\{").trim();
		json = json.replaceAll("\\:\\s+\\[", "\\:\\[").trim();
		json = json.replaceAll("\\[\\s+\\{", "\\[\\{").trim();
		json = json.replaceAll("\"[^0-9:,]+\"", "<span class=\"sh_string\">$0</span>").trim();
		html.append(json);
		return html.toString();
	}

	private String param(Method method) {
		StringBuilder html = new StringBuilder();
		html.append("<table width=\"100%\"><tr><th width=\"150\">参数</th><th width=\"40\">必选</th><th width=\"80\">类型</th><th>说明</th></tr>");
		Class<?> requestObject = method.getParameters()[0].getType();
		for (Field field : requestObject.getDeclaredFields()) {
			Require require = field.getAnnotation(Require.class);
			html.append("<tr>");
			html.append("<td>" + field.getName() + "</td>");
			html.append("<td>" + (require == null ? true : require.require()) + "</td>");
			html.append("<td>" + field.getType().getSimpleName().toLowerCase() + "</td>");

			String comment = requestObject2FileContent(requestObject);
			comment = comment.substring(0, comment.indexOf(field.getName()));
			comment = comment.substring(comment.lastIndexOf("/**"));
			comment = StringUtil.getString(comment.split("\\*")[3]);
			html.append("<td>" + comment + "</td>");
			html.append("</tr>");
		}
		html.append("</table>");
		return html.toString();
	}

	private String apiTitle(Class<?> controller, Method method) {
		StringBuilder html = new StringBuilder();
		String url = getMethodUrl(method).toLowerCase().replaceAll("[^a-zA-Z0-9]+", "");
		html.append("<h3>");
		html.append(getMethodName(controller, method));
		html.append("<span>");
		html.append("<a class=\"mark\" href=\"#" + url + "\" id=\"" + url + "\">");
		html.append("#");
		html.append("</a>");
		html.append("</span>");
		html.append("</h3>");
		return html.toString();
	}

	private String head() {
		StringBuilder html = new StringBuilder();
		html.append("<!doctype html>");
		html.append("<html lang=\"en\">");
		html.append("<head>");
		html.append("<meta charset=\"utf-8\">");
		html.append("<title>");
		html.append(title);
		html.append("</title>");
		if (FileSystem.exists(favicon)) {
			String[] arr = favicon.split("/");
			String faviconFile = arr[arr.length - 1];
			FileSystem.copy(favicon, basePath + faviconFile);
			html.append("<link rel=\"shortcut icon\" href=\"" + faviconFile + "\">");
		}
		html.append("<style>");
		html.append("html{-webkit-font-smoothing:antialiased;-moz-osx-font-smoothing:grayscale;-webkit-font-variant-ligatures:none;font-variant-ligatures:none}body{font-family:'微软雅黑';font-size:62.5%;margin:0;padding:0;color:#333;background:#fff}h1,h2,h3,h4{margin:.8em 0 .5em;line-height:1.2}h5,h6{margin:1em 0 .8em;line-height:1.2}h1{margin-top:0;font-size:2.441em}h2{font-size:1.953em}h3{font-size:1.563em}h4{font-size:1.25em}h5{font-size:1em}h6{font-size:.8em}.pre,a.type,code,pre,span.type,tt{font-family:'微软雅黑'}#content{font-size:1.8em;position:relative}a,a:active,a:link{color:#43853d;text-decoration:none;border-radius:2px;padding:.1em .2em;margin:-.1em}a:focus,a:hover{color:#fff;background-color:#43853d;outline:0}strong{font-weight:700}code a:hover{background:0 0}em code{font-style:normal}#changelog #gtoc{display:none}#gtoc{font-size:.8em}.line{width:calc(100% - 1em);display:block;padding-bottom:1px}.api_stability{color:#fff!important;margin:0 0 1em 0;font-family:'微软雅黑';font-weight:700}.api_stability *{color:#fff!important}.api_stability a{text-decoration:underline}.api_stability a:active,.api_stability a:focus,.api_stability a:hover{background:rgba(255,255,255,.4)}.api_stability a code{background:0 0}.api_stability_0{background-color:#D60027}.api_stability_1{background-color:#EC5315}.api_stability_2{background-color:#4EBA0F}.api_stability_3{background-color:#0084B6}.api_metadata{font-size:.75em;margin-bottom:1em}.api_metadata span{margin-right:1em}.api_metadata span:last-child{margin-right:0}ul.plain{list-style:none}abbr{border-bottom:1px dotted #454545}p{position:relative;text-rendering:optimizeLegibility;margin:0 0 1.125em 0;line-height:1.5em}#apicontent>:last-child{margin-bottom:0;padding-bottom:2em}table{border-collapse:collapse;margin:0 0 1.5em 0}td,th{border:1px solid #aaa}th{text-align:left;background:#ccc}dl,ol,ul{margin:0 0 .6em 0;padding:0}dl dl,dl ol,dl ul,ol dl,ol ol,ol ul,ul dl,ul ol,ul ul{margin-bottom:0}ol,ul{margin-left:2em}dl dt{position:relative;margin:1.5em 0 0}dl dd{position:relative;margin:0 1em 0}dd+dt.pre{margin-top:1.6em}h1,h2,h3,h4,h5,h6{text-rendering:optimizeLegibility;font-weight:700;position:relative}header h1{font-size:2em;line-height:2em;margin:0}#apicontent{padding-top:1em}#apicontent .line{width:calc(50% - 1em);margin:1em 1em .95em;background-color:#ccc}h2+h2{margin:0 0 .5em}h3+h3{margin:0 0 .5em}h2,h3,h4,h5{position:relative;padding-right:40px}h1 span,h2 span,h3 span,h4 span{position:absolute;display:block;top:0;right:0}h1 span:hover,h2 span:hover,h3 span:hover,h4 span:hover{opacity:1}h1 span a,h2 span a,h3 span a,h4 span a{font-size:.8em;color:#000;text-decoration:none;font-weight:700}code,pre,tt{line-height:1.5em;margin:0;padding:0}.pre{line-height:1.5em;font-size:1.2em}pre{padding:1em;vertical-align:top;background:#f2f2f2;margin:1em;overflow-x:auto}pre>code{font-size:.8em}pre+h3{margin-top:2.225em}code.pre{white-space:pre}#intro{margin-top:1.25em;margin-left:1em}#intro a{color:#ddd;font-size:1.25em;font-weight:700}hr{background:0 0;border:medium none;border-bottom:1px solid #7a7a7a;margin:0 0 1em 0}#toc h2{margin-top:0;font-size:1em;line-height:0;margin:1.5em 0}#toc ul{font-size:.8125em}#toc ul ul{font-size:1em}#toc ul a{text-decoration:none}#toc ul li{margin-bottom:.666em;list-style:square outside}#toc li>ul{margin-top:.666em}#toc .stability_0::after{background-color:#d50027;color:#fff}#toc .stability_0::after{content:'deprecated';font-size:.8em;position:relative;top:-.18em;left:.5em;padding:0 .3em .2em;border-radius:3px}#apicontent li{margin-bottom:.5em}#apicontent li:last-child{margin-bottom:0}code,tt{font-size:.9em;color:#040404;background-color:#f2f2f2;border-radius:2px;padding:.1em .3em}a code{color:inherit;background:inherit;padding:0}.type{font-size:.9em;line-height:1.5em}#column1.interior{margin-left:234px;padding:0 2em;-webkit-padding-start:1.5em}#column2.interior{width:234px;background:#333;position:fixed;left:0;top:0;bottom:0;overflow-x:hidden;overflow-y:scroll}#column2 ul{list-style:none;margin:.9em 0 .5em;background:#333}#column2>:first-child{margin:1.25em 1em}#column2>ul:nth-child(2){margin:1.25em 0 .5em}#column2>ul:last-child{margin:.9em 0 1.25em}#column2 ul li{padding-left:1.4em;margin-bottom:.5em;padding-bottom:.5em;font-size:.8em}#column2 .line{margin:0 .5em;background-color:#707070}#column2 ul li:last-child{margin-bottom:0}#column2 ul li a{color:#ccc;border-radius:0}#column2 ul li a.active,#column2 ul li a.active:focus,#column2 ul li a.active:hover{color:#43853d;border-radius:0;border-bottom:1px solid #43853d;background:0 0}#column2 ul li a:focus,#column2 ul li a:hover,#intro a:focus,#intro a:hover{color:#fff;background:0 0}span>.mark,span>.mark:visited{font-size:1em;color:#707070;top:0;right:0}span>.mark:active,span>.mark:focus,span>.mark:hover{color:#43853d;background:0 0}td,th{padding:.75em 1em .75em 1em;vertical-align:top}td>:last-child,th>:last-child{margin-bottom:0}.clearfix:after{content:'.';display:block;height:0;clear:both;visibility:hidden}@media only screen and (max-width:1024px){#content{font-size:2.5em;overflow:visible}#column1.interior{margin-left:0;padding-left:.5em;padding-right:.5em;width:auto;overflow-y:visible}#column2{display:none}}@media only screen and (max-width:1024px) and (orientation:portrait){#content{font-size:3.5em}}@media print{html{height:auto}#column2.interior{display:none}#column1.interior{margin-left:auto;overflow-y:auto}}#column2 ul li a:hover{color:#43853d;border-bottom:1px solid #43853d}#column2 .sub li a:hover{color:#E54305;border-bottom:none}#column2 .sub li{line-height:7px;margin-left:20px}.sh_sourceCode{font-weight:400;font-style:normal}.sh_sourceCode .sh_cbracket,.sh_sourceCode .sh_symbol{color:#333}.sh_sourceCode .sh_keyword{color:#338}.sh_sourceCode .sh_number,.sh_sourceCode .sh_regexp,.sh_sourceCode .sh_specialchar,.sh_sourceCode .sh_string{color:#E54305}.sh_sourceCode .sh_comment{color:#666;font-weight:lighter}");
		html.append("</style>");
		html.append("</head>");
		html.append("<body>");
		html.append("<div id=\"content\" class=\"clearfix\">");
		return html.toString();
	}

	private String category(Set<Class<?>> classSet) {
		return category(classSet, null);
	}

	private String category(Set<Class<?>> classSet, Class<?> currentController) {
		StringBuilder html = new StringBuilder();
		html.append("<div id=\"column2\" class=\"interior\">");
		html.append("<div id=\"intro\" class=\"interior\"><a href=\"index.html\">文档目录</a></div>");
		html.append("<div class=\"line\"></div>");
		html.append("<ul>");
		for (Class<?> controller : classSet) {
			Controller controllerAnnotation = controller.getAnnotation(Controller.class);
			if (controllerAnnotation == null) {
				continue;
			}
			String moduleName = StringUtil.getString(controllerAnnotation.value());
			if (moduleName.isEmpty()) {
				moduleName = controller.getSimpleName();
				moduleName = moduleName.replaceAll("Controller", "").toLowerCase() + "模块";
			}
			if (currentController != null && currentController.equals(controller)) {
				html.append("<li><a class=\"active\" href=\"" + controller2FileName(controller) + "\" title=\"" + moduleName + "\">" + moduleName + "</a></li>");
				html.append(" <ul class=\"sub\">");
				for (Method method : controller.getDeclaredMethods()) {
					String url = getMethodUrl(method);
					String title = getMethodName(controller, method);
					html.append("<li><a href=\"" + controller2FileName(controller) + "#" + url.toLowerCase().replaceAll("[^a-zA-Z0-9]+", "") + "\" title=\"" + title + "\">" + title + "</a></li>");
				}
				html.append("</ul>");
			} else {
				html.append("<li><a href=\"" + controller2FileName(controller) + "\" title=\"" + moduleName + "\">" + moduleName + "</a></li>");
			}
		}
		html.append("</ul>");
		html.append("</div>");
		html.append("<div id=\"column1\" class=\"interior\">");
		return html.toString();
	}

	private String foot() {
		StringBuilder html = new StringBuilder();
		html.append("</div>");
		html.append("</div>");
		html.append("</body>");
		html.append("</html>");
		return html.toString();
	}

	private String controller2FileName(Class<?> controller) {
		return controller.getSimpleName().replaceAll("Controller", "").toLowerCase() + ".html";
	}

	private String controller2FileContent(Class<?> controller) {
		return FileSystem.read(FileSystem.getSystemDir() + "../src/java/" + controller.getName().replaceAll("\\.", "/") + ".java");
	}

	private String requestObject2FileContent(Class<?> requestObject) {
		return FileSystem.read(FileSystem.getSystemDir() + "../../share-protocol/src/java/" + requestObject.getName().replaceAll("\\.", "/") + ".java");
	}

	private String responseObject2FileContent(Class<?> responseObject) {
		return FileSystem.read(FileSystem.getSystemDir() + "../../share-protocol/src/java/" + responseObject.getName().replaceAll("\\.", "/") + ".java");
	}

	private String getMethodUrl(Method method) {
		GetMapping getMapping = method.getAnnotation(GetMapping.class);
		PostMapping postMapping = method.getAnnotation(PostMapping.class);
		PutMapping putMapping = method.getAnnotation(PutMapping.class);
		DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
		PatchMapping patchMapping = method.getAnnotation(PatchMapping.class);
		String url = "";
		if (getMapping != null) {
			url = getMapping.value()[0];
		}
		if (postMapping != null) {
			url = postMapping.value()[0];
		}
		if (putMapping != null) {
			url = putMapping.value()[0];
		}
		if (deleteMapping != null) {
			url = deleteMapping.value()[0];
		}
		if (patchMapping != null) {
			url = patchMapping.value()[0];
		}
		return url;
	}

	private String getMethodName(Class<?> controller, Method method) {
		String java = controller2FileContent(controller);
		String url = getMethodUrl(method);
		java = java.substring(java.indexOf(url) - 100);
		java = java.substring(java.indexOf("/**"), java.indexOf(url));
		return StringUtil.getString(java.split("\\*")[3]);
	}

	/** 
	 * 返回格式化JSON字符串。 
	 * @param json 未格式化的JSON字符串。 
	 * @return 格式化的JSON字符串。 
	 */
	private String formatJson(String json) {
		StringBuilder result = new StringBuilder();
		int length = json.length();
		int number = 0;
		char key = 0;
		for (int i = 0; i < length; i++) {
			key = json.charAt(i);
			if ((key == '[') || (key == '{')) {
				if ((i - 1 > 0) && (json.charAt(i - 1) == ':')) {
					result.append('\n');
					result.append(indent(number));
				}
				result.append(key);
				result.append('\n');
				number++;
				result.append(indent(number));
				continue;
			}
			if ((key == ']') || (key == '}')) {
				result.append('\n');
				number--;
				result.append(indent(number));
				result.append(key);
				continue;
			}
			if ((key == ',')) {
				result.append(key);
				result.append('\n');
				result.append(indent(number));
				continue;
			}
			result.append(key);
		}
		return result.toString().trim();
	}

	/** 
	 * 返回指定次数的缩进字符串。每一次缩进三个空格，即SPACE。 
	 * @param number 缩进次数。 
	 * @return 指定缩进次数的字符串。 
	 */
	private String indent(int number) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < number; i++) {
			result.append("\t");
		}
		return result.toString();
	}
}