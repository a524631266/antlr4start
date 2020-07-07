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
其中[number:] 为程序位（位置）信息
为什么IR是有利于静态分析的呢？
静态分析目的是安全可靠有保证理解程序流程！切记流程才是重点。
### 三地址码（3AC 3-Addresss code）
1.引入临时变量
```text
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



## Data Flow 应用
1. 抓住，深入很难
2. 形式化数学表示，精确无歧义。读起来很有用
[Analysis.puml](./Analysis.puml)
### safe analysis
总体来说就是为了安全考虑，安全角度，可以从
用户和后续分析角度考虑
#### may analysis
使用场景，用户能够看，能够容忍的角度
#### must anlysis
用于under approximation场景，比如编译优化。不能把错误的
的IR分析作为后续的输入

### 重要组成
1. abstraction 用户层面的数据
2. CFG 
    2.1 转化函数 节点node，即表示一个节点拥有的功能,以输出OUT[B]，表示
    2.2 CF 控制流，边的作用，用于表示in[B]，可能有聚合，单节点，分离等控制的存在
    不同控制流代表不同的程序，∩，∪，单射等等不同运算。
3. CFG组构成一个程序program

4. 在分析的IR程序中的所有值，是关于abstraction的
所以，用户不关心具体值，而只关注语义上的。我们用
abstraction来代替，当然，当你的abstraction为具体的值，也是
可以的，比如true or false


#### 案例1：reaching definition
这是一个may analysis ，容许部分误报，但是不准漏报。
描述的是一个定义d： 从一个程序点p点（base block states）连接到q点的位置，
并且在p到q的路径中定义d没有被killed或者污染（不能被重新赋值）。
> definition d: v = x op y;

应用方向:
    检测重复定义，并报错，或者检测检测未定义，即IN[B]-kill中的位向量所使用到的
    定义未被置为1

数据抽象: 
1. 在reaching definition中，如何定义一组代表了该任务的抽象方法组？主要依据是
如何定义其中definition，以及definition由什么数据组成，比如是否被污染如何定义d？
也就是说在路径的寻找过程中，如果后续有定义d被重新赋值的话，那么之前的的所有定义d
都应该被置为0，即在一个新的base block(我们定义为程序点B)中的输入数据以kill(d)

2. 同时还应该有一个坐标的概念，一个definition d，有一个定义坐标指向了该定义

3. 定义转化规则，即base block的node所做的功能，每一个node
> OUT[B] = gen<sub>B</sub>∪(IN[B] - kill<sub>B</sub>)
其中gen<sub>B</sub>表示在blockB 或者程序点B中定义的所有状态都定义为一个新的状态，
即都假设被新定义了，此时的新定义都是以1显示，同时要删除掉所有全局存在相同变量名
定义坐标（状态置为0，下降为0 ，即in中的 1->0， 0->1）

4. CF: 一个INT[B]的所有状态集合是所有前驱节点OUT[P]的并
> INT[B] = U<sub>P a_predecessor_of_B</sub> OUT[P]

5. 算法
```TEXT
OUT[ENTRY]= Φ
for(each BaseBlock B\ENTRY){
    OUT[B] = Φ
}
OnePass()
while(has any state of OUT[B] has Changed){
    OnePass()
}
// 一遍 
OnePass(){
    for(each  BaseBlock B\ENTRY){
        // CF
        IN[B] =  U<sub>P a_predecessor_of_B</sub> OUT[P];
        // TR
        OUT[B] = gen<sub>B</sub>∪(IN[B] - kill<sub>B</sub>);
    }   
}
```

6. 关键点：在遍的过程中，所有向量位只能单调递增，且由于位向量的长度固定，在单调递增的过程
是有限的，如果都不动，那么直接停止，一旦有变化，那么就会不断递增，直到最大点11..(n-4)..11

#### 案例2: Live Variables Analysis

##### 定义存活变量
live表示有一个变量，在程序点p到q的路径中，没有被重新赋值或者定义，即被用到（reuse）的话，表示这个节点是
存活的，反之dead。这是对reaching definition的扩展（redefined）
这里隐藏的一个暗示是重新定义将被定义为dead，可以使用寄存器移除的方式来理解，一旦寄存器一般移除的是dead
变量，这样后续不需要再重新载入。这个至少在极致编程里面是非常受欢迎的。
（这里引入一个话题，编译原理的编程是最为精细的一类编程，这种编程可以不用去深究，但是可以作为训练自己思维
能力的一部分，来参与自己的实践中）

##### 数据流走向
1. backward
    程序点从后向前的数据流向
2. forward
    程序点从前向后的数据流向

根据live的定义，我们用backward方式更直观地表示。实际上没有哪个最好

##### 确定是may 还是 must
根据定义，**只要有一条path中有live**，那么该变量就是live的，不能放过任何一条path，所以是may的

##### 确定CF

> OUT[B] = U<sub>P a_successor_of_B</sub> IN[P]

切记，path定义的方向是forward的，但是数据流可以反向

##### 确定TF
因此 OUT[B]已知，那么定义一个transfer function(f)
INT[B] = f(OUT[B])
HOW TO Design?

> INT[B] = use<sub>B</sub> ∪ (OUT[B])

这个只考虑使用，如果在某个程序点加个定义呢？
那么此时所有的OUT[B]都应该被置为0，这样可以在IN[B]中删除掉这些被重新定义的节点。

> INT[B] = use<sub>B</sub> ∪ (OUT[B] - redefine<sub>B</sub>)
// INT[B]中所有在本B中定义的元素都应该被置为0，寄存器可以删除为0

切记，此时我们用但是backward流方式方式处理

##### 算法设计
backward算法，即针对反向数据流处理
```java 
IN[EXIT]= Φ
for(each BaseBlock B\EXIT){
    IN[B] = Φ
}
OnePass()
while(has any state of OUT[B] has Changed){
    OnePass()
}
// 一遍 
OnePass(){
    for(each  BaseBlock B\EXIT){
        // CF
        OUT[B] =  U<sub>P a_successor_of_B</sub> IN[P];
        // TR
        IN[B] = use<sub>B</sub>∪(OUT[B] - redefine<sub>B</sub>);
    }   
}
```

#### 案例3：Available Expressions Analysis
在计算领域，一个表达式这样的概念将作为一个数据结构来表示！！！！！！
如何定义一个表达式是否是可用的？
> expression e: x op y;
1. 所有从开始节点到当前节点中paths，都要经过e的评估或计算。没有一条拉掉。
2. 不能对 其中的x 和 y 有重新定义，也不可以重新赋值
```text
int x = y * z;
        ||
        \/ available
return y * z;
```
案例，可以实际上减少不必要的计算。
1. 比如可以使用最近计算的值来替换掉可用路径下相同的表达式
2. 如果这样的表达式作用于多个模块，范围比较大，那么就叫做Global Common Subexpression，需要消除（eliminatin）

转换规则
> OUT[B] = gen<sub>B</sub>∪(IN[B] - kill<sub>B</sub>)
[AvailableExpressionAnalysis](E:\github\antlr4start\src\main\docs\AvailableExpressionAnalysis.puml)

##### 总结expression的分析
此分析结果只是程序优化流程的一部分存在，在这个分析过程中，如果一步操作误报了，那么会
对后期是有一个非常严重的影响的。

```text
OUT[ENTRY]= Φ
for(each BaseBlock B\ENTRY){
    OUT[B] = U // 表示的是一个全为1的位向量
}
OnePass()
while(has any state of OUT[B] has Changed){
    OnePass()
}
// 一遍 
OnePass(){
    for(each  BaseBlock B\ENTRY){
        // CF
        IN[B] =  ∩<sub>P a_predecessor_of_B</sub> OUT[P];
        // TR
        OUT[B] = gen<sub>B</sub>∪(IN[B] - kill<sub>B</sub>);
    }   
}

```

直观的理解由于∩的存在，初始化不能全为0
#### 总结图
|   |RD   |  LV | AE  |
| ------------ | ------------ | ------------ | ------------ |
| Domain  |set of definition  | set of variable  | set of expression  |
| Direction  | forward  |  backward | forward  |
| May/Must  | may  | may  | must  |
| Boundary  | OUT[entry] = Φ |IN[exit] =  Φ  | OUT[entry] =  Φ |
|  Initialization | OUT[B] = Φ  |IN[B] = Φ  |OUT[B] = U  |
| Transfer Function  | gen<sub>B</sub> + （IN[B] -kill<sub>B</sub>）  |  gen<sub>B</sub> + （OUT[B] -redefine<sub>B</sub>） |gen<sub>B</sub> + （IN[B] -kill<sub>B</sub>）   |
| Meet  | ∪  | ∪  | ∩  |
