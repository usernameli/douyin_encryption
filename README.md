
##douyin-encryption 是使用Java写的基于抖音Android客户端的接口签名算法



##加密原理（本方法采用的是老版本接口）

* 通过反编译抖音APP，破解抖音加密的libuserinfo.so库，把c翻译成Java代码
    *  （比如该接口的加密参宿cp和as的生成）https://aweme.snssdk.com/aweme/v1/user/
    *  ?user_id=101528985686&retry_type=no_retry&iid=41159612939&device_id=55867298418
    *  &ac=wifi&channel=tengxun&aid=1128& app_name=aweme&version_code=168&version_name=1.6.8&device_platform=android&
    *  ssmix=a&device_type=Nexus+5&device_brand=google&language=zh&os_api=
    *  19&os_version=4.4.3&uuid=359250051841177&openudid=4a303e11a75ed6e1&
    *  manifest_version_code=168&resolution=1080*1776&dpi=480&update_version_code=1682
    *  &_rticket=1534454338636&ts=1534411176&as=a1350437782adb81c5&cp=48a1b85c89537013e1
    *  对查询串进行按键排序并取值，对空格和+进行转义为a
    *  排序完毕给这个值进行拼接，中介插入一点固定的值，类似秘钥
    *  然后取MD5；如果时间轴&1为1，那么取多一次MD5
    *  给时间戳进行处理（生成两串八个字符字符串，用来后台校验时间戳）583babdc
    *  将生成的MD5值截取两段，与时间戳生成的参数进行错位排序处理，得到36位字符
    *  将字符分别取18位给到as和cp字段，追加到查询串最后

##需要注意的问题
* 在最新的SDK版本有了新的mas字段辅助校验，还在破解中，但是用旧版可以忽略这个参数，只需要把跟版本相关的调到169之前就可以了

##有问题反馈

* 陌陌，微博，网易云，keeper，悦动圈等app也已经破解，后期持续更新
* 有问题可以QQ群：193990770，点个星星进群，谢谢


##代码样例
* https://github.com/yuanchao1893/douyin_encryption/tree/master/example

