# learn-spring
learn-spring



## 注解
| 注解 | 核心作用 | 适用场景 | 注意事项 |
| :--- | :--- | :--- | :--- |
| `@Component` | 让 Spring 接管类（创建实例、管理生命周期） | 工具类、通用组件（如 FileUploadUtil） | 1. 仅加在类上；<br>2. 需在启动类包 / 子包下；<br>3. 默认单例 |
| `@Controller` | 语义化 @Component，标记控制器（处理 HTTP 请求） | 传统 MVC 返回页面的控制器 | 1. 配合 @RequestMapping；<br>2. 返回 JSON 需加 @ResponseBody |
| `@RestController` | = @Controller + @ResponseBody | 前后端分离接口（如文件上传 API） | 1. 返回值自动转 JSON；<br>2. 无法返回页面 |
| `@Service` | 语义化 @Component，标记业务逻辑类 | 核心业务处理（如用户登录、订单服务） | 仅语义区分，便于代码维护 |
| `@Repository` | 语义化 @Component，标记数据访问类 | DAO/Mapper（操作数据库） | 自动转换数据库异常（如 SQLException） |
| `@Autowired` | 自动注入 Spring 管理的 Bean | 类依赖其他 Bean（如 Controller 注入 Util） | 1. 按类型注入；<br>2. 类型重复需配 @Qualifier；<br>3. 不能注入静态属性 |
| `@Resource` | 注入 Bean（JDK 注解，替代 @Autowired） | 同 @Autowired，更灵活 | 1. 默认按名称注入；<br>2. 可指定 name 属性；<br>3. 跨框架兼容性好 |
| `@Value` | 从配置文件读取值注入属性 | 读取单个配置（如上传目录、文件大小） | 1. 靠配合 @Component 使用；<br>2. 缺省值写法：` $ {key:默认值}`；<br>3. 支持字符串 / 数字 / 布尔 |
```aiignore
1. 工具类（@Component + @Value 组合）
@Component // 交给Spring管理
public class FileUploadUtil {
    // 读取配置文件值，缺省为upload
    @Value(" $ {file.upload.base-dir:upload}")
    private String baseDir;

    @Value(" $ {file.upload.allowed-types:jpg,png}")
    private String allowedTypes;
    // 工具方法...
}

2. 控制器（@RestController + @Resource 组合）
@RestController // 接口控制器，返回JSON
@RequestMapping("/api/file")
public class FileUploadController {
    // 注入工具类（不用new，Spring自动提供实例）
    @Resource
    private FileUploadUtil fileUploadUtil;

    // 接口方法...
}
```
-----------
----------
| 注解 | 核心作用 | 适用场景 | 注意事项 |
| :--- | :--- | :--- | :--- |
| `@RequestMapping` | 映射 HTTP 请求（路径 + 请求方式） | 定义接口基础路径（类 / 方法级） | 1. 可指定 method=RequestMethod.POST；<br>2. 支持通配符 |
| `@GetMapping` | 简化 @RequestMapping，仅处理 GET 请求 | 查询类接口（如查文件列表） | 不能接收请求体（JSON） |
| `@PostMapping` | 简化 @RequestMapping，仅处理 POST 请求 | 提交 / 上传接口（如文件上传） | 上传文件需用 multipart/form-data 格式 |
| `@RequestParam` | 获取 URL / 表单参数 | 接收简单参数（如？page=1&size=10） | 1. required 默认 true（必传）；<br>2. 缺省值：`defaultValue="0"` |
| `@PathVariable` | 获取 URL 路径参数（RESTful） | REST 接口（如 /api/file/{id}） | 参数名需和路径 {} 内名称一致 |
| `@RequestBody` | 接收 JSON 请求体，转 Java 对象 | 接收复杂参数（如提交用户信息） | 1. 请求头需为 application/json；<br>2. 仅支持 POST/PUT |
```aiignore
@RestController
@RequestMapping("/api/file")
public class FileUploadController {
    @Resource
    private FileUploadUtil fileUploadUtil;

    // POST请求，接收文件参数
    @PostMapping("/upload")
    public UploadResult upload(
            // 接收文件参数，必传
            @RequestParam("file") MultipartFile file,
            // 可选参数，缺省为0
            @RequestParam(value = "type", defaultValue = "0") int type
    ) {
        // 接口逻辑...
        return new UploadResult();
    }

    // RESTful接口，路径传参
    @GetMapping("/{id}")
    public UploadResult getFile(@PathVariable String id) {
        // 根据ID查询文件...
        return new UploadResult();
    }
}
```
---------
---------

| 注解 | 核心作用 | 适用场景 | 注意事项 |
| :--- | :--- | :--- | :--- |
| `@Configuration` | 标记配置类（替代 XML 配置） | 自定义 Spring 配置（如静态资源映射） | 配合 @Bean 注册第三方 Bean |
| `@Bean` | 手动注册 Bean 到 Spring 容器 | 第三方类（无法加 @Component）如 RedisTemplate | 1. 加在方法上，返回值即为 Bean；<br>2. 默认名称 = 方法名 |
| `@ConfigurationProperties` | 批量读取配置（替代多个 @Value） | 多配置项场景（如文件上传全量配置） | 1. 需指定 prefix 前缀（如 prefix="file.upload"）；<br>2. 需加 @Component 或 @EnableConfigurationProperties |
````aiignore
// 1. 批量配置类（@ConfigurationProperties）
@Component
@ConfigurationProperties(prefix = "file.upload") // 批量读取file.upload下配置
public class FileUploadProperties {
    private String baseDir; // 对应file.upload.base-dir
    private String allowedTypes; // 对应file.upload.allowed-types
    private int maxSize; // 对应file.upload.max-size
    // getter/setter（Lombok @Data可省略）
}

// 2. 静态资源映射配置（@Configuration + @Bean）
@Configuration // 标记为配置类
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    private FileUploadProperties uploadProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 映射上传目录，支持浏览器访问
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:" + uploadProperties.getBaseDir() + "/");
    }
}
````
--------
--------
| 注解 | 核心作用 | 适用场景 | 注意事项 |
| :--- | :--- | :--- | :--- |
| `@Data` | 自动生成 get/set/toString/equals 等方法 | 所有需 getter/setter 的类（如返回结果类） | 需引入 lombok 依赖 |
| `@RequiredArgsConstructor` | 生成 final 属性构造方法 | 构造器注入 Bean（替代 @Autowired） | 配合 final 属性使用，代码更简洁 |
| `@NoArgsConstructor` | 生成无参构造方法 | Spring 创建 Bean（默认需无参构造） | 加了 @AllArgsConstructor 后必须手动加这个 |

```aiignore
// 1. 返回结果类（@Data 简化）
@Data // 自动生成get/set/toString
public class UploadResult {
    private boolean success;
    private String message;
    private String filePath;
}

// 2. 构造器注入（@RequiredArgsConstructor）
@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor // 生成final属性构造器
public class FileUploadController {
    // final属性，自动注入
    private final FileUploadUtil fileUploadUtil;
    // 接口方法...
}
```
----
----
