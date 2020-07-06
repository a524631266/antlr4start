[TOC]

### 静态程序分析
static program analysis

它是存在于计算机应用application最热门的领域

#### 特征
1. 安全 Security
sql注入，xss
2. 可靠 Reliability
memory leak，空指针
3. 编译优化 Optimization
孤立代码，死代码，code motion（for循环内部的int i = 2 * 10^6）。
4. 软件分析，understanding program
方法调用，类体系
> 目前最想了解的是如何通过静态的代码分析转换成plantUML语法，帮助理解
代码

### rice theory
不存在一个完美的方法或手段来结局你说关心或者感兴趣的issue。

既然不完美，那么得到一个有用的结果还是很合理的。

#### soundness and completeness
sound 会引起误报
complete 会引起漏报

soundness 正确性，有悬念，听起来是正确的。允许误报，但是不允许漏报
[SafeCast2](#SafeCast2.puml)

在实际工作中，我们更倾向于有误报，这样，我们可以通过人肉方式来识别，但是
不能有漏报，一旦漏报，那就是money的事情了

#### 一句化理解 静态分析
根据维护上下文信息的可以把sound分成
1. sound, precise,expensive(需要维护上下文信息)
2. sound, imprecise, cheap
good trade-offs 准确与分析速度


### 总结
1. abstraction
一个抽象层，用来描述静态分析行为的具体元素的类型(type of element)

一套语义的集合，提供分析所用(ctrl+ shift + number 标记头)
2. over-approximation
control flows
控制流，语句与语句的关联

## AST VS IR
1. AST 更贴近语法结构，更上层 ASD
2. AST 依赖语言的，而 IR更贴近机器语言（二进制）
3. AST 没有类型检查，很合理，它只是表达一种结构，而类型检查属于语义范畴
4. AST lack of control flow information
所以IR 是静态分析的基础，或者基石
IR = intermediate representation 中间表示层

### IR的形式
IR可以看到流程结构
```text
1： i = i + 1
2： t1 = a [ i ]
3:  if t1  < v goto 1 (控制流，可以看到)
```
其中【number：】 为程序位（位置）信息
为什么IR是有利于静态分析的呢？
静态分析目的是安全可靠有保证理解程序流程！切记流程才是重点。
### 三地址码（3AC 3-Addresss code）
1.引入临时变量
```commandline
a + b + 3
=>
t1 = a + b
t2 = t1 + 3

```
2. 地址=>逻辑地址
name
constant
compiler-generated temporary:t1 t2

#### 三地址码感
soot.test
本质是一个中间过程的代码表示
```text
1. 触发方法
virtual invoke r0.<java.lang.Object: void <init>()>("hello world");

2. public static void <clinit>()
{
    <...ClassName: double pi> = 3.14
    return;
}
3. this 表示
...ClassName r0;
r0 := @this: ...ClassName;
4. 标签 ，地址位
label1:
if $i0 < 10 goto label1;

5. 参数
r0:= @parameter0: java.lang.String[];

6. 初始化
$r3 = new java.lang.StringBuilder;

7. 触发初始化方法
spacial invoke $r3.<java.lang.StringBuilder: void <init>()>();

```
#### SSA static single assignment
每个定义都是唯一的即使定义的名称相等
```text

p = a + b
q = p - c
p = q * d
p = e - p
q = p + q
===>  SSA
p1 = a + b
q1 = p1 - c
p2 = q1 * d
p3 = e - p2
q2 = p3 + q1

```
或者引入一个fi-function
```text
if(condition)
    x0 = 0
else
    x1 = 1
x2 = O(x0, x1) 与运算？？
y = x2 + 7

```
精度与效率之间的平衡，SSA以损失效率为代价，提高了部分的sentive的精度，可以理解的
但是转化成二进制，由于引入了多个临时遍历，程序就损失效率

#### CFA control flow analysis CFG
node
> Basic Block :one in one out  for build node
找leader
1. first line
2. goto（i） i为header
3. goto 的next line 为heder
最后把所有header连接起来，其中的gap作为块的标记。

edge
1. 是否有goto关联或者直接相邻（条件goto）
2. 无条件 不用填边，同时以粗粒度的block标签来替换
    细粒度的指令标签 instruction labels。可以分块存储。虚拟
    内存管理方面的内容。
3. 在多线程

Entry， Exit
方便算法设计，