

# 1、UEditor

UEditor 只提供 JSP 版本的后端入口代码。但提供了项目源码，因此可以根据业务需求修改源代码。此处使用了 SpringBoot 框架，配备了 Thymeleaf模板引擎，所以没有必要再添加 JSP 来兼容 UEditor，可通过修改源码满足需要。

1. 新建 SpringBoot 项目，添加 web 和 thymeleaf 以及 ueditor 依赖的包

	```xml
	<dependency>
	    <groupId>org.springframework.boot</groupId>
	    <artifactId>spring-boot-starter-thymeleaf</artifactId>
	</dependency>
	<dependency>
	    <groupId>org.springframework.boot</groupId>
	    <artifactId>spring-boot-starter-web</artifactId>
	</dependency>
	<!--UEditor依赖的jar包 -->
	<dependency>
	    <groupId>org.json</groupId>
	    <artifactId>json</artifactId>
	    <version>20160810</version>
	</dependency>
	<dependency>
	    <groupId>commons-fileupload</groupId>
	    <artifactId>commons-fileupload</artifactId>
	    <version>1.3.2</version>
	</dependency>
	<dependency>
	    <groupId>commons-codec</groupId>
	    <artifactId>commons-codec</artifactId>
	    <version>1.9</version>
	</dependency>
	```

2. 下载源码并解压到项目中，注意 config.json 拷到了 resources 根路径下，记得修改 index.html 的资源文件引用地址（template 里面）

	<img src="../Images/Tools/image-20201020161146305.png" alt="image-20201020161146305" style="float:left" />

3. 添加 UeditorController，跳转到 index 页面

	```java
	@Controller
	public class UeditorController {
	    @GetMapping("/")
	    public String index() {
	        return "index";
	    }
	}
	```

4. 运行项目。访问路径localhost:8080，跳转到如下界面即是源码已拷贝成功

	<img src="../Images/Tools/image-20201016134822142.png" alt="image-20201016134822142" style="zoom:80%; float:left"  />

5. 此时发现上传图片功能不能用。照着源码里的 controller.jsp 依样画葫芦，写入 UEditorController 类，映射路径为 config。 

  ```java
  @RequestMapping("/config")
  public void config(HttpServletRequest request, HttpServletResponse response) {
      response.setContentType("application/json");
      String rootPath = request.getSession().getServletContext().getRealPath("/");
      String exec = new ActionEnter(request, rootPath).exec();
      PrintWriter writer = null;
      try {
          writer = response.getWriter();
      } catch (IOException e) {
          e.printStackTrace();
      } finally {
          if (writer != null) {
              writer.write(exec);
              writer.flush();
              writer.close();
          }
      }
  }
  ```

6. 一步一步 debug，发现无法加载 config.json 文件。此时修改 ConfigManage 类的 getConfigPath() 方法。如下:

  ```java
  package com.baidu.ueditor;
  
  import java.io.BufferedReader;
  import java.io.File;
  import java.io.FileInputStream;
  import java.io.FileNotFoundException;
  import java.io.IOException;
  import java.io.InputStreamReader;
  import java.io.UnsupportedEncodingException;
  import java.util.HashMap;
  import java.util.Map;
  
  import com.alibaba.fastjson.JSON;
  import com.alibaba.fastjson.JSONArray;
  import com.alibaba.fastjson.JSONObject;
  import com.baidu.ueditor.define.ActionMap;
  import org.apache.commons.io.IOUtils;
  
  /**
   * 配置管理器
   *
   * @author hancong03@baidu.com
   */
  public final class ConfigManager {
  
      private final String rootPath;
      private final String originalPath;
      // private final String contextPath;
      private static final String configFileName = "config.json";
      private String parentPath = null;
      private JSONObject jsonConfig = null;
      // 涂鸦上传filename定义
      private final static String SCRAWL_FILE_NAME = "scrawl";
      // 远程图片抓取filename定义
      private final static String REMOTE_FILE_NAME = "remote";
  
      /*
       * 通过一个给定的路径构建一个配置管理器， 该管理器要求地址路径所在目录下必须存在config.properties文件
       */
      private ConfigManager(String rootPath, String contextPath, String uri)
              throws FileNotFoundException, IOException {
  
          rootPath = rootPath.replace("\\", "/");
  
          this.rootPath = rootPath;
          // this.contextPath = contextPath;
  
          if (contextPath.length() > 0 && uri.startsWith(contextPath)) {
              this.originalPath = this.rootPath + uri.substring(contextPath.length());
          } else {
              this.originalPath = this.rootPath + uri;
          }
  
          this.initEnv();
  
      }
  
      /**
       * 配置管理器构造工厂
       *
       * @param rootPath    服务器根路径
       * @param contextPath 服务器所在项目路径
       * @param uri         当前访问的uri
       * @return 配置管理器实例或者null
       */
      public static ConfigManager getInstance(String rootPath, String contextPath, String uri) {
  
          try {
              return new ConfigManager(rootPath, contextPath, uri);
          } catch (Exception e) {
              return null;
          }
  
      }
  
      // 验证配置文件加载是否正确
      public boolean valid() {
          return this.jsonConfig != null;
      }
  
      public JSONObject getAllConfig() {
  
          return this.jsonConfig;
  
      }
  
      public Map<String, Object> getConfig(int type) {
  
          Map<String, Object> conf = new HashMap<String, Object>();
          String savePath = null;
  
          switch (type) {
  
              case ActionMap.UPLOAD_FILE:
                  conf.put("isBase64", "false");
                  conf.put("maxSize", this.jsonConfig.getLong("fileMaxSize"));
                  conf.put("allowFiles", this.getArray("fileAllowFiles"));
                  conf.put("fieldName", this.jsonConfig.getString("fileFieldName"));
                  savePath = this.jsonConfig.getString("filePathFormat");
                  break;
  
              case ActionMap.UPLOAD_IMAGE:
                  conf.put("isBase64", "false");
                  conf.put("maxSize", this.jsonConfig.getLong("imageMaxSize"));
                  conf.put("allowFiles", this.getArray("imageAllowFiles"));
                  conf.put("fieldName", this.jsonConfig.getString("imageFieldName"));
                  savePath = this.jsonConfig.getString("imagePathFormat");
                  break;
  
              case ActionMap.UPLOAD_VIDEO:
                  conf.put("maxSize", this.jsonConfig.getLong("videoMaxSize"));
                  conf.put("allowFiles", this.getArray("videoAllowFiles"));
                  conf.put("fieldName", this.jsonConfig.getString("videoFieldName"));
                  savePath = this.jsonConfig.getString("videoPathFormat");
                  break;
  
              case ActionMap.UPLOAD_SCRAWL:
                  conf.put("filename", ConfigManager.SCRAWL_FILE_NAME);
                  conf.put("maxSize", this.jsonConfig.getLong("scrawlMaxSize"));
                  conf.put("fieldName", this.jsonConfig.getString("scrawlFieldName"));
                  conf.put("isBase64", "true");
                  savePath = this.jsonConfig.getString("scrawlPathFormat");
                  break;
  
              case ActionMap.CATCH_IMAGE:
                  conf.put("filename", ConfigManager.REMOTE_FILE_NAME);
                  conf.put("filter", this.getArray("catcherLocalDomain"));
                  conf.put("maxSize", this.jsonConfig.getLong("catcherMaxSize"));
                  conf.put("allowFiles", this.getArray("catcherAllowFiles"));
                  conf.put("fieldName", this.jsonConfig.getString("catcherFieldName") + "[]");
                  savePath = this.jsonConfig.getString("catcherPathFormat");
                  break;
  
              case ActionMap.LIST_IMAGE:
                  conf.put("allowFiles", this.getArray("imageManagerAllowFiles"));
                  conf.put("dir", this.jsonConfig.getString("imageManagerListPath"));
                  conf.put("count", this.jsonConfig.getInteger("imageManagerListSize"));
                  break;
  
              case ActionMap.LIST_FILE:
                  conf.put("allowFiles", this.getArray("fileManagerAllowFiles"));
                  conf.put("dir", this.jsonConfig.getString("fileManagerListPath"));
                  conf.put("count", this.jsonConfig.getInteger("fileManagerListSize"));
                  break;
  
          }
  
          conf.put("savePath", savePath);
          conf.put("rootPath", this.rootPath);
  
          return conf;
  
      }
  
      private void initEnv() throws FileNotFoundException, IOException {
  
          File file = new File(this.originalPath);
  
          if (!file.isAbsolute()) {
              file = new File(file.getAbsolutePath());
          }
  
          this.parentPath = file.getParent();
  
          String configContent = this.readFile( this.getConfigPath() );
  
          try {
              JSONObject jsonConfig = JSON.parseObject(configContent);
              this.jsonConfig = jsonConfig;
          } catch (Exception e) {
              this.jsonConfig = null;
          }
  
      }
  
      protected String getConfigPath() {
          //return this.parentPath + File.separator + ConfigManager.configFileName;
          try {
  			//获取classpath下的config.json路径
  			return this.getClass().getClassLoader().getResource("config.json").toURI().getPath();
  		} catch (URISyntaxException e) {
  			return null;
  		}
  
      }
  
      private String[] getArray(String key) {
  
          JSONArray jsonArray = this.jsonConfig.getJSONArray(key);
          String[] result = new String[jsonArray.size()];
  
          for (int i = 0, len = jsonArray.size(); i < len; i++) {
              result[i] = jsonArray.getString(i);
          }
  
          return result;
  
      }
  
      private String readFile(String path) throws IOException {
  
          StringBuilder builder = new StringBuilder();
  
          try {
  
              InputStreamReader reader = new InputStreamReader(new FileInputStream(path), "UTF-8");
              BufferedReader bfReader = new BufferedReader(reader);
  
              String tmpContent = null;
  
              while ((tmpContent = bfReader.readLine()) != null) {
                  builder.append(tmpContent);
              }
  
              bfReader.close();
  
          } catch (UnsupportedEncodingException e) {
              // 忽略
          }
  
          return this.filter(builder.toString());
  
      }
  
      // 过滤输入字符串, 剔除多行注释以及替换掉反斜杠
      private String filter(String input) {
  
          return input.replaceAll("/\\*[\\s\\S]*?\\*/", "");
  
      }
  
  }
  ```

  ```java
  this.getClass().getClassLoader().getResource("config.json").toURI().getPath(); 
  ```

  此处需要先转为URI再getPath()，否则如果项目路径带空格或者带中文则无法读取到文件

7. 运行项目路径http://localhost:8080/config?action=config，如下图显示则表示可读取到 config.json 文件

<img src="../Images/Tools/image-20201016135415285.png" alt="image-20201016135415285"  />

  此时进行上传图片，已经能够成功上传了。

9. 可是图片究竟上传到哪里了呢？继续一步步 debug 发现，上传到 tomcat 缓存路径，只要重启下 tomcat 该文件就会被删除。我们需要将其存储到磁盘中。此时修改config.json文件：

	<img src="../Images/Tools/image-20201016140155230.png" alt="image-20201016140155230" style="float:left" />

	红色为修改处。我需要将文件存储到 E:/upload/image/** 下，此处多添加了 basePath，是想把视频、音乐等静态资源都存储到 E 盘。由于添加了 basePath，需要修改配置。通过 debug 来到 ConfigManage：

	<img src="../Images/Tools/image-20201020160047275.png" alt="image-20201020160047275" style="float:left"  />

	将 basePath 塞进配置文件里。之后继续来到上传文件类 BinaryUploader，修改如下代码：

	<img src="../Images/Tools/image-20201020160801870.png" alt="image-20201020160801870" style="float:left" />

	运行项目，点击添加图片。打开 E 盘的 image 目录，成功上传到 E 盘对应路径。

10. 打开浏览器，发现页面无法加载图片。因为图片存在E盘了，而 spring 并没有对 E 盘目录进行映射。此时加入路径映射。打开application.properties 文件，添加如下代码

	```properties
	web.upload-path=E:/upload
	spring.mvc.static-path-pattern=/**
	spring.resources.static-locations=classpath:/META-INF/resources/,classpath:/resources/,classpath:/static/,classpath:/public/,file:${web.upload-path}
	```

	此时重新运行项目，点击上传图片，图片已经能够正常显示了。

11. 用Maven来打包运行试试，打开项目地址，点击上传图片，发现竟然上传不了了！？在 Jar 包里无法以 ClassLoader.getResource().getPath() 获得的路径读取文件，得用 Class 类的 getResourceAsStream() 来读取。打开 ConfigManager 类，修改 initEnv 方法：

	```java
	 private void initEnv() throws FileNotFoundException, IOException {
	
	     File file = new File(this.originalPath);
	
	     if (!file.isAbsolute()) {
	         file = new File(file.getAbsolutePath());
	     }
	
	     this.parentPath = file.getParent();
	
	     //String configContent = this.readFile(this.getConfigPath());
	     String configContent = this.filter(IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("config.json")));
	
	     try {
	         JSONObject jsonConfig = new JSONObject(configContent);
	         this.jsonConfig = jsonConfig;
	     } catch (Exception e) {
	         this.jsonConfig = null;
	     }
	
	 }
	```

12.  ok了，再次打包，运行项目，完成。
