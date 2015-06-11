$(function() {
    var ws;
    ws = new WebSocket($("body").data("ws-url"));
    ws.onmessage = function(event) {
        var message;
        message = JSON.parse(event.data);
        switch (message.type) {
            case "message":
                console.log($("#msgtext").val() + "recieved");
                return $("#board tbody").append("<tr><td>" + message.uid + "</td><td>" + message.msg + "</td></tr>");
            default:
                return console.log(message);
        }
    };
    return $("#msgform").submit(function(event) {
        event.preventDefault();
        console.log($("#msgtext").val());
        ws.send(JSON.stringify({
            msg: $("#msgtext").val()
        }));
        return $("#msgtext").val("");
    });
});
