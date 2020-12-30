---
layout: post
---
```shell script
#!/usr/bin/bash
python -u -m http.server >> http.log
java -jar web-car.jar >> car.log
```

上面的代码不会顺序执行

因为python语句会一直卡住 只到退出才行 
所以要用nohup &
