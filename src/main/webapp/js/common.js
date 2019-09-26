var back = document.getElementById("back");
window.onscroll = function () {
    var t = document.documentElement.scrollTop || document.body.scrollTop;
    if (t >= 300) {
        back.style.display = "inline";
    } else {
        back.style.display = "none";
    }
}
back.onclick = function () {
    var timer = setInterval(function () {
        document.documentElement.scrollTop -= 30;
        document.body.scrollTop -= 30;
        if ((document.documentElement.scrollTop || document.body.scrollTop) <= 0) {
            clearInterval(timer);
        }
    }, 5);
}

var result = document.getElementById("result");
var input = document.getElementById("input");
var timer;

function serach(e, url) {
    if (e.value.trim() != "") {
        clearTimeout(timer);
        timer = setTimeout(function () {
            var xmlhttp;
            if (window.XMLHttpRequest) {
                // IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
                xmlhttp = new XMLHttpRequest();
            } else {
                // IE6, IE5 浏览器执行代码
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    var response = JSON.parse(xmlhttp.responseText);
                    var str = "";
                    for (var i = 0; i < response.serachItems.length; i++) {
                        console.log(response.serachItems[i])
                        str += "<a class='resultItem' title = '" + response.serachItems[i].title + "'href='" + url + "/text/" + response.serachItems[i].id + "'>" + response.serachItems[i].title + "</a>"
                    }
                    result.innerHTML = str;
                }
            }
            console.log(e.value);
            xmlhttp.open("POST", url + "/serach", true);
            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
            xmlhttp.send("keywords=" + encodeURIComponent(encodeURIComponent(e.value)));
        }, 300);
    } else {
        result.innerHTML = "";
    }
}

function clearWord() {
    input.value = "";
    result.innerHTML = "";
}
