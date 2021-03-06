var roomName="";

$(function () {
    var ws;
    ws = new WebSocket($("body").data("ws-url"));
    ws.onmessage = function (event) {
        var message;
        message = JSON.parse(event.data);
        switch (message.type) {
            case "message":
                console.log($("#msgtext").val() + "recieved");
                if(message.uid != $("#uid").val())
                  return $("#board tbody").append("<tr  class=\"text-right "+ message.roomname+" \" ><td>" + DecryptText(message.msg).toString() + "</td></tr>");
                else
                    return $("#board tbody").append("<tr  class=\"text-left "+ message.roomname+" \" ><td>" + DecryptText(message.msg).toString() + "</td></tr>");
            default:
                return console.log(message);
        }
    };
    return $("#msgform").submit(function (event) {
        event.preventDefault();
        var msgEncrypt = EncryptText($("#msgtext").val())
        console.log(msgEncrypt);
        ws.send(JSON.stringify({
            msg: msgEncrypt.toString(),
            roomname: roomName //TODO : add empty string validation on server
        }));
        $("#msgtext").val("") // clear input form
        return msgEncrypt.toString()
    });
});

function EncryptText(text) {
    //var password = document.getElementById("password").value
    var password ="password"
    if (password.length == 0 ) { console.log("password is not set"); return ""; }
    else {
        console.log("Encrypting text " + text);
        var encrypted = CryptoJS.AES.encrypt(text, password);
        return encrypted.toString();
    }
}

function DecryptText(encrypted){
    //var password = document.getElementById("password").value
    var password ="password"
    if (password.length == 0 ) { console.log("password is not set"); return ""; }
    else {
        console.log("Decrypting text "+ encrypted);
        var decrypted = CryptoJS.AES.decrypt(encrypted, password);
        return decrypted.toString(CryptoJS.enc.Utf8);
    }
}

function EnableChat() {
    console.log("Entered function")
    document.getElementById("messagediv").style.display="block";
    document.getElementById("passworddiv").style.display="none";
}


$(document).ready(function(){
    $('[data-toggle="popover"]').popover();
});

function getUserRooms(callback) {
    var httpRequest = new XMLHttpRequest();
    httpRequest.onload = function(){ // when the request is loaded
        callback(httpRequest.responseText);// we're calling our method
    };
    httpRequest.open('GET', "/getroomdetails");
    httpRequest.send();
}

function processUserRooms(roomList) {
    var rooms = JSON.parse(roomList);
    for (var i = 0; i < rooms.length; i++) {
        console.log("Adding room "+ rooms[i] + " to the table")
        $("#roomList tbody").append("<tr class='clickable "+rooms[i]+"' onclick='enableChatRoom(\""+rooms[i]+"\")' data-href='url://' ><td>" + rooms[i] + "</td></tr>");
    }
}


// TODO : make this event driven

function enableChatRoom(element) {
    console.log("Row clicked" + element);
    roomName = element;
   // Remove "active class from other rows"
    var roomList = document.getElementsByClassName("clickable");
    for(var i=0; i< roomList.length;i++)
        roomList[i].classList.remove("active");

    //Add "active" class to the clicked TR
    var tractive= document.getElementsByClassName(element);
    tractive[0].classList.add("active");

    $("#board tbody tr").css("display","none");

    $("#board tbody tr." +element).css("display","block");

}

/* attach a submit handler to the form */

$(document).on('submit','#addroom',function(event){

    /* stop form from submitting normally */
    event.preventDefault();

    /* get some values from elements on the page: */
    var $form = $(this),
        term = $form.find('input[name="name"]').val(),
        url = $form.attr('action');

    /* Send the data using post */
    var posting = $.post(url, {
        name: term
    });

    /* Put the results in a div */
    posting.done(function(data) {
        console.log(data);
        $("#roomList tbody").append("<tr class='clickable "+data+"' onclick='enableChatRoom(\""+data+"\")' data-href='url://' ><td>" + data + "</td></tr>");
    });

    $("#roomList th a").click();
});


getUserRooms(processUserRooms);