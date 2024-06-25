@Controller
@RequestMapping("/home/rce")
public class RuntimeExec {
    @RequestMapping("/runtime")
    public String RuntimeExec(@GetParam(value="id") String cmd, Model model) {
        StringBuilder sb = new StringBuilder();
        String line;


        try {
            var runtimeInstance = Runtime.getRuntime();
            Process proc = runtimeInstance.exec(cmd);
        } catch (IOException e) {

        }
        return "basevul/rce/runtime";
    }
}