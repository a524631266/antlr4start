## 指针分析

### 过程初步分析
过程内分析
过程间分析
这两种区别是 过程内分析把一个方法内部最为一个过程
方法调用之间是相互隔离的，此时，会丢失很多对于常量
传播的精度问题。
过程间分析，使用一条edges关联两个过程，call graph

### call graph
call site to callee relation by an edges
调用点与函数所在位置之间用一条边表示。这个边就是用指针来
存储。这个连接的表示过程就是用call pointer的方式实现

#### dispatch
1. Dispatch(c, m)
其中c是一个class名，m是一个方法签名(signature)
2. 当c类名中没有m方法前面会调用父类或者子类的方法

```java
class A{
    void foo(){
        System.out.println("a");
    }
}
class B extends A{

}

class C extends B{
        @Override
        void foo(){
            System.out.println("C");
        }

}

public class Dispatch {
    public static void main(String[] args) {
        A x = new B();// Dispatch(B, A.foo());
        x.foo();
        A y = new C(); // Dispatch(C, A.foo());
        y.foo();
    }
}
```


### 各种算法解析
> CHA Class Hierarchy Analysis
对于 call static and special call（construct ,private method and super.method）的call site（调用点）(cs)处理是很简单的
因为这些调用在compiler time就已经确定
而对于 virtual call 这些调用是runtime的，所以，需要更复杂的逻辑处理这个，各种算法的核心，就是处理runtime

> 对于 CHA 而言，virtual call 的处理是，Type c 中的c，会把c中的所有子类和父类都一次性加载到内存中，
对方法的调用均会处理子类和父类的方式。
```java
// 根据上面的类
B b = new B()
b.foo() => Dispatch(B, b.foo()) => 可以是C.foo(),y也可以是A.foo()
```
显然这种方式是不精确的(忽略了一些数据流path)，缺少精度的，但是这种方式处理快！！

> 小提醒： idea 中使用ctrl + shift + H 可以查看 call graph
> 如何用antlr实现一个调用图的重要部分