---
layout: post
---
#### nginx rtmp 坑记录  
1. nginx yum 安装不方便弄新模块  
> 替换/usr/bin/nginx  
2. yum 编译nginx需要其他程序  
> yum install 安装  
3. yum 安装32位64位同名包冲突  
> yum list all && yum erase (不要用--set==***false, 官方说了会引起其他问题) 
4. nginx rtmp restore premission denied 
> chmod o+w -R /tmp/rec 

真坑啊
 
