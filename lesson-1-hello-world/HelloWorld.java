@Controller
@RequestMapping("/home/rce")
public class RuntimeExec {
    @RequestMapping("/runtime")
    public String RuntimeExec(String cmd, Model model) {
        StringBuilder sb = new StringBuilder();
        String line;



        // -------------------------------------------------
        // -------------------------------------------------
        // -------------------------------------------------
        // -------------------------------------------------
        // -------------------------------------------------



        try {
            Process proc = Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
        }
        return "basevul/rce/runtime";
    }
}