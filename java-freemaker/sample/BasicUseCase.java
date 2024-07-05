import freemarker.template.*;

import java.io.*;
import java.util.*;

public class FreemarkerExample {
    public static void main(String[] args) {
        // 配置 Freemarker
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        try {
            cfg.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);

            // 加载模板
            Template template = cfg.getTemplate("welcome.ftl");

            // 数据模型
            Map<String, Object> templateData = new HashMap<>();
            templateData.put("user", "John Doe");

            // 渲染模板
            Writer out = new StringWriter();
            template.process(templateData, out);

            // 输出渲染后的文本
            System.out.println(out.toString());

        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}