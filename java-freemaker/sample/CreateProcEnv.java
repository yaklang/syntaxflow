import freemarker.template.*;
import java.io.*;
import java.util.*;

public class FreemarkerDemo {
    public static void main(String[] args) {
        // 创建和配置 Freemarker
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        try {
            cfg.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);

            // 加载模板
            Template template = cfg.getTemplate("helloTemplate.ftl");

            // 准备数据模型
            Map<String, Object> templateData = new HashMap<>();
            templateData.put("name", "Freemarker User");

            // 准备输出流
            Writer out = new StringWriter();

            // 创建 Environment 对象并处理模板
            Environment env = template.createProcessingEnvironment(templateData, out);
            env.process();

            // 输出结果
            System.out.println(out.toString());

        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}