public class External {
    public String crossFunction(String cmd) {
        return "echo 'Hello World'";
    }
}
public class RuntimeExecCrossFunction {
    @RequestMapping("/runtime")
    public String RuntimeExecCross(String cmd, Model model) {
        StringBuilder sb = new StringBuilder();
        String line;

        try {
            External extern = new External();
            Process proc = Runtime.getRuntime().exec(extern.crossFunction(cmd));

            InputStream fis = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(fis, "GBK");
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }

        } catch (IOException e) {
            e.printStackTrace();
            sb.append(e);
        }
        model.addAttribute("results", sb.toString());
        return "basevul/rce/runtime";
    }
}