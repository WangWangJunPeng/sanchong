<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

  <meta charset="UTF-8">

  <title>加载动画</title>

    <style>
body {
  font-size: 12px;
}

.base {
  height: 9em;
  left: 50%;
  margin: -7.5em;
  padding: 3em;
  position: absolute;
  top: 50%;
  width: 9em;
  transform: rotateX(45deg) rotateZ(45deg);
  transform-style: preserve-3d;
}

.cube,
.cube:after,
.cube:before {
  content: '';
  float: left;
  height: 3em;
  position: absolute;
  width: 3em;
}

/* Top */
.cube {
  background-color: #05afd1;
  position: relative;
  transform: translateZ(3em);
  transform-style: preserve-3d;
  transition: .25s;
  box-shadow: 13em 13em 1.5em rgba(0, 0, 0, 0.1);
  animation: anim 1s infinite;
}
.cube:after {
  background-color: #049dbc;
  transform: rotateX(-90deg) translateY(3em);
  transform-origin: 100% 100%;
}
.cube:before {
  background-color: #048ca7;
  transform: rotateY(90deg) translateX(3em);
  transform-origin: 100% 0;
}
.cube:nth-child(1) {
  animation-delay: 0.05s;
}
.cube:nth-child(2) {
  animation-delay: 0.1s;
}
.cube:nth-child(3) {
  animation-delay: 0.15s;
}
.cube:nth-child(4) {
  animation-delay: 0.2s;
}
.cube:nth-child(5) {
  animation-delay: 0.25s;
}
.cube:nth-child(6) {
  animation-delay: 0.3s;
}
.cube:nth-child(7) {
  animation-delay: 0.35s;
}
.cube:nth-child(8) {
  animation-delay: 0.4s;
}
.cube:nth-child(9) {
  animation-delay: 0.45s;
}

@keyframes anim {
  50% {
    transform: translateZ(0.5em);
  }
}

</style>

    <script src="static/js/prefixfree.min.js"></script>

</head>

<body>
<div style="text-align:center;clear:both;">
 <div style="margin-top:10%;font-size:18px;">正在加载中...</div>
</div>
  <div class='base'>
  <div class='cube'></div>
  <div class='cube'></div>
  <div class='cube'></div>
  <div class='cube'></div>
  <div class='cube'></div>
  <div class='cube'></div>
  <div class='cube'></div>
  <div class='cube'></div>
  <div class='cube'></div>
</div>

</body>

</html>