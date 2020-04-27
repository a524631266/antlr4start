# antlr4start
antlr4start

download from 
curl -O http://www.antlr.org/download/antlr-4.0-complete.jar


```
$ export CLASSPATH=".:/home/zhangll/github/antlr4start/lib/antlr-4.0-complete.jar:$CLASSPATH"

$ java -jar /home/zhangll/github/antlr4start/lib/antlr-4.0-complete.jar
$ java org.antlr.v4.Tool
$ alias antlr4='java -jar /home/zhangll/github/antlr4start/lib/antlr-4.0-complete.jar'
```

2. 运行
```
antlr4 Hello.g4


```
3. TestRig
```
alias grun='java org.antlr.v4.runtime.misc.TestRig'
```

通过一个grammar名与rule名为参数
输入识别的语句
并通过ctrl+D得出结果
```
javac *.java
(base) zhangll 22:27:40 install$ grun Hello r -tokens
hello parrt
[@0,0:4='hello',<1>,1:0]
[@1,6:10='parrt',<2>,1:6]
[@2,12:11='<EOF>',<-1>,2:0]
```
```
C(base) zhangll 22:27:40 install$ grun Hello r -tokens
hello parrt
[@0,0:4='hello',<1>,1:0]
[@1,6:10='parrt',<2>,1:6]
[@2,12:11='<EOF>',<-1>,2:0]
```
```
 zhangll 22:32:07 install$ grun Hello r -tree
hello parrt
(r hello parrt)
```
```
grun Hello r-gui
```


## 插件下载
```$xslt
https://www.antlr.org/tools.html

```
```