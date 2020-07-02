三大在概念上较为抽象
实现可以多样化，并不太固定的设计模式，概念理解比较简单的。
无固定套路的模式。

命令模式，解释器模式，中介模式

> 命令模式

命令模式的核心在命令两个字，在不支持函数作为参数传递的java语言上，通过封装一个函数的类，并提交这个类给其他类。
其本质是对命令进行排队/延时加载/异步/撤消等等控制命令的方式

在实现上，很多应用比如
mybatis中的ParameterHandler，ResultSetHandler，ResultHandler，等等都是一种通过handler方式

可以把这种命令方式，混合数据与执行逻辑到同一个命令中
核心是把请求request作为event，分别生成对应的命令，并对其进行
```java
interface Command{
    void execute();
}
class OneCommand implements Command{
    @Override
    void execute(){
        System.out.println("1");
    }
}
class TwoCommand implements Command{
    @Override
    void execute(){
        System.out.println("2");
    }
}
class Main{
    void main(){
        // 方式1
        Type type = request.getType();
        Command command = null;
        if(type == One){
            command = new OneCommand();
        }
        if (type == Two){
            command = new TwoCommand();
        }
        command.execute();
        
        // 方式二
        // 延时特性的化
        // 用一个队列来表示
        queue.add(command);
        while(queue.isNotEmpty()){
            Command command = queue.take();
            command.execute();
        }
    }
}


```
扩展的类似的模式
多线程涉及模式中的事件驱动模式
参数资料
1. 面向模式的软件体系结构：第四卷 分布式计算模式语言(有时间就看看，其他不用看了)
2. reactor经典设计模式
```java

```
> 解释器模式

解释器模式用于特殊领域
> 当用于识别某个语句或或者计算/执行某个语句的程序，把这个程序叫做解释器interpreter

例子有
1. 配置文件reader
2. 计算器
3. python语言的解释器
。。。。 



编译器或者解释器系统，规则引擎，正则表达式引擎
规则引擎可以去了解一下，这个比较好玩，处理的是实时动态的变化，把变化作为不变的东西
来考虑问题。

常规来说用编译器语法解



> translator

如果把一个语句转换为另一个语言的等价语句
那么这个过程就是翻译器