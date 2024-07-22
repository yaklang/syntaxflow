# Java Freemaker

使用 Springboot Freemaker Starter 启动后，在 Controller Method 中，返回一个 String 则为 freemaker template 的模版名称（文件名）。

找到文件名比较关键。

一般作为一个 Controller 来说，他的方法是一个纯 Literal String，例如为 $ret，则需要在数据库中搜索 f`${ret}\.\w+` 类似的文件存在，一般来说这个文件中的 ${...} 是可以供 SSTI 的点。

一般的模版注入的审计都类似这种情况，Java 中出现裸 TPL 的渲染和调用的机会不多，框架会包裹一系列的用法。