---
layout: post
---
## 2019年10月11日  
在制作raspberryPi远程下载文件服务的时候遇到了麻烦  

1. ntfs硬盘不能读写  
> sudo apt-get install ntfs-3g  
> ntfsfix /dev/***  
> mount -t ntfs -w /dev/*** /mnt/***  

2. python ssh 各种问题  
* ssh on python just like writing dockerfile or jenkins-file  
**they are just not ssh**
