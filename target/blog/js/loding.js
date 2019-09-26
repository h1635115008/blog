/**
 * 加载条
 */
var loding = document.getElementById("loding");
var count = parseInt(loding.style.width);
var maxSize = window.screen.width;
var timer = setInterval(function() {
	count += 10;
	if(count > maxSize -30) {
		clearInterval(timer);
	}
	loding.style.width = count + "px";
}, 1);
window.onload = function() {
	setTimeout(function() {
		clearInterval(timer);
		loding.style.display = "none";
	}, 1000);
}