public class RuntimeExecCrossFunction {
    public String crossFunction(String cmd) {
        return "echo 'Hello World'";
    }

    @RequestMapping("/runtime")
    public String RuntimeExecCrossFunction(String cmd, Model model) {
        StringBuilder sb = new StringBuilder();
        String line;

        try {
            String actualCmd = crossFunction(cmd);
            Process proc = Runtime.getRuntime().exec(actualCmd);

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