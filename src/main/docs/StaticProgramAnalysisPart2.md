### 理论部分

#### Partial Order
定义一个元素以及操作<=(这个操作可以任一，简单起见用小于等于来讲)，作为偏序的空间
在这个空间中，使得每个元素都有如下性质
1. 自反性 x<=x
2. 反对称性 if y<=x and x<=y then x = y
3. 传递性 if y <= x and x <= z then y <= z
4. 在空间元素中的每个元素可以不用作操作
即有些元素跟元素无法比较。也是可以的，因为1，2，3都是假设在可操作的基础上作的文章

其实有一个计算机的算法用到了偏序理论，拓扑排序

我们用P代表一个偏序集合，其中的操作用（⊆）表示
那么 (P,⊆)组成的空间代表了一个偏序Partial Order。偏序既空间。是一个宏观概念。


#### Lattice
格中的pair都有上界和下界的一个偏序集合
lattice有最小上界和最大下界。

##### complete lattice
lattice中的一个子集的上界与下界也在lattice中，但不要理解成
recur lattice ，因为子集的上界与下界，不一定是包含在子集中的，可能包含在其互斥集合中
。所以子集不一定为lattice。

finite lattice是complete的，但是complete不一定是单调的。在我们程序观点上看，基本的domain是
finite的，所以可以把domain看成是一个complete lattcie

#### function on lattice
偏序意味着单调（严格单调或者普通单调）
在data flow 中
transfer function 以及 meet所组成的大的F，是单调的。
即tf 和meet分别也是同向单调的，这样才能满足大F的单调性质。

##### product lattice

L1^L2^L3,product lattice中每个L:(P,⊆) 
其中L<sub>k</sub> : (P<sub>k</sub>,⊆)
其中 P<sub>k</sub> 是 P的一个子集。
在data flow中的每个nodes集合以及每个f(nodes)所组成的空间就是一个product lattice


### 心法
may and must analysis
在may 中，是从一个unsafe result 向 safe result过度的一个过程

拿Reaching Definition为例子unsafe result，就是全部为0，所有的变量都没有被初始化
那么这也意味着，全部报错，这显然是不合理的。
通过iterative algorithm，向safe result过度，在这个算法遍历过程中
一旦没有达到safe result，那么就是一个不好的结果。bad result。
那么 safe 由什么决定？由场景的业务决定，那Reaching Definition。自己设计。

在作数据流分析的过程中，先把一个function作成safe，然后再单调。
由可能monotonic会达到一个不safe的fixed point。so safe is top target/aim！


### MOP vs Ours(Iterative Algorithm)
MOP is find a solution by Meeting over  all paths 
> P(偏序集) = Entry -> s<sub>1</sub> -> s<sub>2</sub> ... -> s<sub>i</sub>

> MOP[s<sub>i</sub>]  = ∪/∩ F(OUT[ENTRY])
即每条path的并
而 Ours是对每个transfer function的并
即
> Ours[s<sub>i</sub>] = F(∪/∩ OUT[ENTRY])

一般来说 MOP是OURS的一个最小上界/最大上界最优解。
```text
证明
F IS monotonic，
x⊆x ∪ y,y⊆x ∪ y 
有
F(x)⊆F(x ∪ y) and F(y)⊆F(x ∪ y)
即F(x ∪ y)是F(x)和F(y)的一个上界
又F(x)∪F(y)是F(x)和F(y)的一个最小上界，所以
有F(x) ∪/∩ F(y) ⊆ F(x ∪/∩ y)
```
推论1：
当 F(x) ∪/∩ F(y) = F(x ∪/∩ y)
Ours == MOP 
意味着，F是可以分配（distributive）的，符合分配律的函数或者操作时，有
一个最优解。

一般来说，bit-vector或者gen-kill算法的union/intersection操作都是可以分配的。
why？


### Constant Propagation
这用来检测一个变量是否是常量的算法，在java中可以用
static修饰，所以不需要Constant Propagation算法，但是有些语言
没有常量的含义，比如一些函数式语言Lisp等等。
此时的常量不是java中的static final v，而是在计算过程中，有些
值是用到了前面的值，此时的值是constant

1. 确定这个主题的定义，常量就是表示所有路径都不能够被重新定义，一旦重新定义就
设置成非常量(0),这里有一种must analysis的意味
2. 从top到bottom的过程是一种unsafe到safe的状态，我们先定义所有变量
是常量，所以这是不安全的，到所有变量都是非常量，这是一个bottom，我们直到一旦
判断为常量，那么就直接用value替代，我们检测的所有变量非常量，也就是不用替换，显然
是最安全的。但是不是我们想要的。我们必须要确保一个least 下界保证能够以不能以误报作为
代价useless。尽可能缩小漏报的可能性。
3. 确定domain of the values V

UNDEF(未定义，但是是一个常量) -> NAC（不是一个常量not a constant）


4. 确定操作符号是∪/∩/Other
一般先使用meet ∩ ,与domain构成一个lattice。


### 进化 iterative algorithm
workList algorithm
只遍历计算有变化的节点就行了。这是一个work list的关键。


```text
worklist<- all basic blocks
while(worklist is not empty){
    // 从list中提取一个BB
    pick a basic block B from WorkList
    old_OUT = OUT[B]
    process_function(OUT[B])
    if(OUT[B] !== old_OUT){
        addinto BB into worklist
    }
}
}

```