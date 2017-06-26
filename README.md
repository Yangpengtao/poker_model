# poker_model
这是刚学习的时候做的一以卡片形式展开，飞出去，飞回来的展示效果。<br/>
没有 任何性能优化考虑。但是还是有一定的参考价值。<br/>
先看下效果：<br/>
![image](https://github.com/Yangpengtao/poker_model/blob/master/drawable/poker.gif)

#### 优化 参考：<br/>
animation 对象因为重复创建，显示调用recycle，gc及时回收。<br/>
使用属性动画实现更好。<br/>
封装动画类
