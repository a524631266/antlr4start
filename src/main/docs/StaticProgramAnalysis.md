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
目前最想了解的是如何通过静态的代码分析转换成plantUML语法，帮助理解
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

