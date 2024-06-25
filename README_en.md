# Welcome to SyntaxFlow 

> This doc is translated via AI from [Chinese README.md](/README.md)

SyntaxFlow is a compiler-level advanced static analysis language developed by Yaklang. You can use SyntaxFlow to analyze programs compiled by the Yaklang SSA compiler (IrCode in Database).

# !!DANGEROUS NOTICE:

**SyntaxFlow technology is currently only for technical communication. For commercial cooperation and licensing for development, please contact the Yak Project.**

**The development process does not represent the final quality presentation. If you want to experience the latest technology and implementations, please refer to the yaklang project source code.**

# Supported Features

SyntaxFlow addresses various challenges encountered in static analysis and can erase the characteristics of the language AST, eliminating assignments, and compiling branches and loops into basic block and Phi structures.

## Basic Features of SyntaxFlow

1. Code fault tolerance: can audit incomplete code;
2. Supports precise search, fuzzy search, and specific method search;
3. Supports data flow analysis under SSA format;
4. Supports Phi instruction processing IF For loops and other control flows;
5. Supports search after OOP compilation into SSA format;
6. Supports tracing of Java annotations and SSA instantiation, adapting to various annotation entry framework codes;
7. Supports the use-def chain operators (recursively finding definitions upwards, finding references downwards).

## Advanced Features of SyntaxFlow

1. Universal language architecture: Supports Yaklang / Java / PHP(Alpha*) / JavaScript(ES) Alpha*;
2. Automatic inter-procedural tracking, OOP object tracking, cross-procedural within OOP methods, context-sensitive and function stack-sensitive features, supporting complex data flow analysis;
3. Compilation product symbolization, constructing standardized symbols and IrCode tables in Sqlite format, supports visualization of intermediate expressions.
4. Supports cross-procedural and data flow visualization, supports DOT graph format.

# Quick Use

## 0x00 Prerequisites

Before using SyntaxFlow, you need to prepare the Yaklang environment. The simplest way is to use the Yaklang pre-compiled environment:

```bash
bash <(curl -sS -L http://oss.yaklang.io/install-latest-yak.sh)
```

With this installation, you can use `yak version` to check the version.

To keep up with the addition of new features in SyntaxFlow, try to stay on versions after `1.3.4-beta3`.

In addition to installing the basic execution environment, you also need some basic knowledge of "programs." Of course, it's best if you have a bit of PHP / JS / Java basics, which will facilitate our later discussion.

## 0x01 Starting from Zero

Suppose you currently do not know what SSA is or the basic syntax of SyntaxFlow, then let's start from truly "Zero"!

### 0x01.1 Clone This Project Locally

```bash
git clone https://github.com/yaklang/syntaxflow-zero-to-hero
cd syntaxflow-zero-to-hero/lesson-1-hello-world/
```

### 0x01.2 Compile the Hello World Program

Of course, SyntaxFlow cannot be proven to be Turing complete, nor is it suitable to `Println("Hello World")` like other languages. So Yaklang SyntaxFlow's Hello World has to be quite special.

We need to compile the code to be audited into a specific SSA format before we can start executing SyntaxFlow. The compilation command is very simple, after ensuring you have cloned it locally, enter the `lession-1-hello-world` repository and execute the following command:

```bash
yak ssa -t . --program lesson1
```

> Note that after you set --program lesson1, in subsequent uses, you need to use this program name because the analysis will know which program you are analyzing.

Once done, you should see:

```bash
➜  lesson-1-hello-world git:(main) ✗ yak ssa -t . --program lesson1
[INFO] 2024-06-25 22:57:36 [ssacli:131] start to compile file: .
[INFO] 2024-06-25 22:57:36 [ssacli:147] compile save to database with program name: lesson1
[INFO] 2024-06-25 22:57:36 [ssa:42] init ssa database: /Users/v1ll4n/yakit-projects/default-yakssa.db
...
...
...
...
[INFO] 2024-06-25 22:57:37 [language_parser:68] compile HelloWorld.java cost: 309.695625ms
[INFO] 2024-06-25 22:57:37 [language_parser:72] program include files: 2 will not be as the entry from project
[INFO] 2024-06-25 22:57:37 [ssacli:162] finished compiling..., results: 1
```

When you see `finished compiling..., results: ...`, it means the compilation is complete.

Don't be afraid, the source code is very simple. As Hello World, we strive to showcase the features of SyntaxFlow in a simple case so that anyone with any foundation can learn how to audit:

This code doesn't even write `import` and `package`, which seems like incomplete Java classes on the surface, but in fact, such code can still be compiled by Yaklang SSA. XDD

```java
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
```

Next, we need to execute a SyntaxFlow rule to perform code auditing.

## 0x02 Executing a SyntaxFlow Rule

This time, we have created a `lesson-1.sf` file in the same directory with the following content:

```log
desc(title: "This is Hello World for SyntaxFlow, simple but great!")

Runtime.getRuntime().exec(* #-> * as $source) as $sink;

check $source then "Found system command execution parameter position (dependency)" else  "Parameter not found"
check $sink then "Found system command execution position" else "Command not executed";
```

Our example rule uses SyntaxFlow's query language to detect command execution in Java. The rule primarily consists of two parts: one to find the source of the command execution (user input) and another to find where the command execution occurs. The most critical line of code that actually takes effect is:

`Runtime.getRuntime().exec(* #-> * as $source) as $sink;`

### New Knowledge: Tracing the Top Dominator of a Value

The special symbol `#->` is used to trace the top dominator of a value, which we can simply understand as: **Who influences a particular value?** This operator is used to trace the Use-Def chain. It helps analyze the definition and use of a value in the code, determining the "top definition" of a variable. This is crucial for understanding data flows and potential security vulnerabilities.

The file is already saved in the `lesson-1-hello-world` directory, users can execute directly in that directory:

```bash
yak sf --program lesson1 lesson-1.sf
```

When executing the command, pay attention to the `--program` parameter which is `lesson1`, set during compilation.

The results can be obtained:

```
[INFO] 2024-06-25 23:35:24 [ssacli:221] start to use SyntaxFlow rule: lesson-1.sf
[INFO] 2024-06-25 23:35:24 [ssa:42] init ssa database: /Users/v1ll4n/yakit-projects/default-yakssa.db
[INFO] 2024-06-25 23:35:24 [ssacli:272] syntax flow query result:
rule md5 hash: 389009d4257afd3ee509af4749936a3b
rule preview: desc(title: "This is Hello World...then "Found system command execution position" else "Command not executed";
description: {title: "title", $source: "Found system command execution parameter position (dependency)", $sink: "Found system command execution position"}
Result Vars:
  source:
    t2612544: Parameter-cmd
        HelloWorld.java:5:30 - 5:62
  sink:
    t2612569: Undefined-runtimeInstance.exec(valid)
        HelloWorld.java:12:43 - 12:52
```

## 0x03 Analyzing SyntaxFlow Results

The results show the following:

1. `description: { ... }` Found system command execution parameter position (dependency)...
1. Specific locations and types of source and sink, which help developers understand and fix potential security issues.

Recalling the code, we have two lines:

```bash
Check $source then "Found system command execution parameter position (dependency)" else  "Parameter not found"
check $sink then "Found system command execution position" else "Command not executed"; 
```

When source exists, it will output "Found..parameter", and when sink exists, it will output "Found command execution position."

These two conditions basically allow us to determine that the vulnerability exists.

## 0x04 Quick Use Summary

In just a few minutes, you should have gone through several elements of auditing code with SyntaxFlow:

1. Compile `yak ssa -t ./project-path --program name`
1. Write a SyntaxFlow rule file `rule.sf`
1. Execute the SyntaxFlow rule file `yak sf --program name rule.sf`

And after completing the quick start, you also learned a new usage `#->`, an operator that can find the highest dominator. Although the journey ends quickly, you must admit, you may already be eager to start a new learning journey with SyntaxFlow!

# More Tutorials

1. [Quick Start: Hello-World](/lesson-1-hello-world/)
2. [SyntaxFlow: Syntax Tutorial](/SyntaxFlow-Cookbook.md)