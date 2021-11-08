# ViewModel

1.诞生
* 瞬态数据丢失
* 异步调用的内存泄漏
* 类膨胀提高维护难度和测试难度

2.作用
* 是介于View(试图)和Model(数据模型)之间的桥梁
* 使试图和数据能够分离，也能保持通信

这个方法最大的好处是：
1. 当Activity曾经通过某个资源得到一些图片或者信息，那么当再次恢复后，无需重新通过原始资源地址获取，可以快速的加载整个Activity状态信息。
2. 当Activity包含有许多线程时，在变化后依然可以持有原有线程，无需通过重新创建进程恢复原有状态。
3. 当Activity包含某些Connection Instance时，同样可以在整个变化过程中保持连接状态。

tips:
1. onRetainNonConfigurationInstance()在onSaveInstanceState()之后被调用。
2. 调用顺序同样介于onStop() 和 onDestroy()之间。