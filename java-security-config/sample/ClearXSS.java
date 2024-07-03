@ApiIgnore
@Controller("dynamicPageAction")
@RequestMapping("/demo/clearXSS")
public class MCmsAction extends net.demo.cms.action.BaseAction {
    private String clearXss(String value) {

        if (value == null || "".equals(value)) {
            return value;
        }

        value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        value = value.replaceAll("\\(", "&#40;").replace("\\)", "&#41;");
        value = value.replaceAll("'", "&#39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']",
                "\"\"");
        value = value.replace("script", "");

        return value;
    }
}
