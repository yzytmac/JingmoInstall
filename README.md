# JingmoInstall
**免root静默安装apk，全网唯一**  
阅读源码得知系统安装apk的形式就是调用了IPackageManager中的installPackage方法。  
而IPackageManager我们是拿不到的，所以我们复制IPackageManager相关的所有AIDL文件到我们的项目中，  
拿到IPackageManager后就可以进行调用相关的安装方法了。  
但是安装apk只有系统应用才能做到，所以我们还需要系统签名来给我们的apk签名，使用系统签名工具中的的方法来签名。  
签完名之后应用就具备了免root静默安装apk的能力。demo中输入框输入被安装apk的名称（sd卡根目录中），点击安装即可。  
- 如果觉得有用，不吝啬在右上角给我一个Star。谢谢！！  
![](https://raw.githubusercontent.com/yzytmac/yzytmac.github.io/master/images/star.png)  

目前测试Android5.0之前完美。5.0之后会报installPackage方法找不到错误。5.0以后的源码比较大，正在下载研究中，敬请期待。。。


