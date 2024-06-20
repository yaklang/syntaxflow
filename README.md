# Welcome to SyntaxFlow

this is an Advanced SSA IR Analysis DSL.

# Quick Start

## 0x00 Preparing for SyntaxFlow

1. Knwon something about Compiler.
1. Known something about SSA.
1. Known at least ONE language(common case): Java / JS / PHP(alpha).

## 0x01 Install yak

visit `yaklang.com` to install yak command line (core engine).


```bash
bash <(curl -sS -L http://oss.yaklang.io/install-latest-yak.sh)
```


## 0x02 Clone the Current Project 

```bash
git clone https://github.com/yaklang/syntaxflow-zero-to-hero
cd syntaxflow-zero-to-hero
```

## 0x03 Compile Hello World

Compile in lesson-1 is a bravo start for readers. just try `yak ssa-compile -t lesson-1-hello-world/ --program lesson1` u will see below:

```
❯ yak ssa-compile -t lesson-1-hello-world/  --program lesson1
[INFO] 2024-06-20 10:56:03 [ssacli:124] start to compile file: lesson-1-hello-world/
[INFO] 2024-06-20 10:56:03 [ssacli:140] compile save to database with program name: lesson1
[INFO] 2024-06-20 10:56:03 [ssa:42] init ssa database: /Users/v1ll4n/yakit-projects/default-yakssa.db
[INFO] 2024-06-20 10:56:03 [language_parser:46] parse project in fs: *filesys.LocalFs, localpath: lesson-1-hello-world/
[INFO] 2024-06-20 10:56:03 [language_parser:152] file[lesson-1-hello-world/HelloWorld.java] is supported by language [java], use this language
...
...
[INFO] 2024-06-20 10:56:03 [visit_modifier:126] start to build annotation ref to def: (1189212)RuntimeExec_RuntimeExec
[INFO] 2024-06-20 10:56:03 [language_parser:68] compile lesson-1-hello-world/HelloWorld.java cost: 39.68225ms
[INFO] 2024-06-20 10:56:03 [language_parser:72] program include files: 2 will not be as the entry from project
[INFO] 2024-06-20 10:56:03 [ssacli:156] finished compiling..., results: 1
```

finished with `compile lession-1-hello-word/... cost ...ms`

## 0x04 Start to Use SyntaxFlow

once u compiling the lession-1 source code. try execute syntaxflow code in cmd!

```bash
yak ssa  --program lesson1 --sf '.getRuntime().exec(* #-> * as $source)'
```

if the command is executed, u will see

```
[INFO] 2024-06-20 10:59:49 [ssacli:117] using syntaxflow rule will skip compile
[INFO] 2024-06-20 10:59:49 [ssa:42] init ssa database: /Users/v1ll4n/yakit-projects/default-yakssa.db
[ERRO] 2024-06-20 10:59:49 [scope:48] failed to sync from database: unquote values error: invalid syntax
[INFO] 2024-06-20 10:59:49 [database_marshal:410] load scope from id: 209999 when loading basic block
[INFO] 2024-06-20 10:59:49 [database_marshal:410] load scope from id: 209995 when loading basic block
[INFO] 2024-06-20 10:59:49 [database_marshal:410] load scope from id: 209993 when loading basic block
[INFO] 2024-06-20 10:59:49 [ssacli:183] syntax flow query result:
[INFO] 2024-06-20 10:59:49 [ssacli:185] ===================== Variable:source ===================
[INFO] 2024-06-20 10:59:49 [ssacli:211] lesson-1-hello-world/HelloWorld.java:5:30 - 5:40: String cmd
IR: 1189258: Parameter-cmd
    2| @RequestMapping("/home/rce")
    3| public class RuntimeExec {
    4|     @RequestMapping("/runtime")
    5|     public String RuntimeExec(String cmd, Model model) {
    6|         StringBuilder sb = new StringBuilder();
    7|         String line;
    8|
......
......
......
......
```

even through we do not explain the `.getRuntime().exec...` , maybe you already find some details in `SyntaxFlow`

## 0x05 Try by yourself

modify `/ession-1-hello-world/HelloWorld.java` as your will, try the same SyntaxFlow Query.

# Advanced Lesson

1. [Hello World](/lesson-1-hello-world/): Quick Start
2. [Cross Function Tester](/lesson-2)

Coming...

if you are hungry for SyntaxFlow really? see `github.com/yaklang/yaklang` project and track main branch.