/**
 *
 */
var messageBlocks = document.getElementsByClassName("messageBlock");
var textarea = document.getElementById("textarea");
for (var i = 0; i < messageBlocks.length; i++) {
    var reply = messageBlocks[i].getElementsByClassName("reply");
    reply[0].number = i;
    reply[0].onclick = function () {
        var floor = messageBlocks[this.number].getElementsByClassName("floor");
        textarea.value = ">> " + floor[0].innerHTML + " ";
    }
}