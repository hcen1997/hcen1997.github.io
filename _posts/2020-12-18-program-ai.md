---
layout: post
---

这个迭代法,,和机器学习有啥不一样?
```golang
func Sqrt(x float64) float64 {
  d  :=0.001
  z :=1.000
  for x-z*z>d || z*z-x>d {
    z -=(z*z-x)/(2*z)
  }
  return z
}
```

## hash速度
ai制造必须克服的是