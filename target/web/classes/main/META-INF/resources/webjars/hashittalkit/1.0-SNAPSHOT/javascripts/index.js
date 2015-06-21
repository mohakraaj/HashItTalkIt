var roomName = "";

$(function () {
    var ws;
    ws = new WebSocket($("body").data("ws-url"));
    ws.onmessage = function (event) {
        var message;
        message = JSON.parse(event.data);
        switch (message.type) {
            case "message":
                console.log($("#msgtext").val() + "recieved");
                if (message.uid != $("#uid").val())
                    return $("#board tbody").append("<tr  class=\"text-right " + message.roomname + " \" ><td>" + DecryptText(message.msg, message.roomname).toString() + "</td></tr>");
                else
                    return $("#board tbody").append("<tr  class=\"text-left " + message.roomname + " \" ><td>" + DecryptText(message.msg, message.roomname).toString() + "</td></tr>");
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
    var password = $("#" + roomName).val();
    console.log(password);
    if (typeof password === 'undefined') {
        console.log("password is not set");
        return "";
    }
    else {
        console.log("Encrypting text " + text);
        var encrypted = CryptoJS.AES.encrypt(text, password);
        return encrypted.toString();
    }
}

function DecryptText(encrypted, roomName) {
    var password = $("#" + roomName).val();
    console.log(password);
    if (typeof password === 'undefined') {
        console.log("password is not set");
        return "";
    }
    else {
        console.log("Decrypting text " + encrypted);
        var decrypted = CryptoJS.AES.decrypt(encrypted, password);
        return decrypted.toString(CryptoJS.enc.Utf8);
    }
}

function EnableChat() {
    console.log("Entered function")
    document.getElementById("messagediv").style.display = "block";
    document.getElementById("passworddiv").style.display = "none";
}


$(document).ready(function () {
    $('[data-toggle="popover"]').popover();
});

function getUserRooms(callback) {
    var httpRequest = new XMLHttpRequest();
    httpRequest.onload = function () { // when the request is loaded
        callback(httpRequest.responseText);// we're calling our method
    };
    httpRequest.open('GET', "/getroomdetails");
    httpRequest.send();
}

function processUserRooms(roomList) {
    var rooms = JSON.parse(roomList);
    for (var i = 0; i < rooms.length; i++) {
        console.log("Adding room " + rooms[i] + " to the table")
        $("#roomList tbody").append("<tr class='expired clickable " + rooms[i] + "' onclick='enableChatRoom(\"" + rooms[i] + "\", true)' data-href='url://' ><td>" + rooms[i] + " <span title=\"Password Expired\" class=\"orange pull-right glyphicon glyphicon-alert\" aria-label=\"true\"></span></td></tr>");
    }

    //select first room by default
    if(rooms.length > 0) {
        selectFirstRoombyDefault();
    }
    else {
        $("#chatform").css("display",'block')
    }
}


// TODO : make this event driven

function enableChatRoom(element, askforupdate) {
    console.log("Row clicked" + element);

    var passwordupdated = true;

    // making askforUpdate by default false
    if (typeof(askforupdate) === 'undefined') askforupdate = false;

    // Remove "active class from other rows"
    var roomList = $("tr.clickable");
    roomList.removeClass("active")

    if (askforupdate && $("tr.expired." + element).length != 0) {
        bootbox.prompt({
            title: "Password for room: " + element + " has expired",
            placeholder: "Update password",
            callback: function (result) {
                if (result === null) {
                    passwordupdated = false;
                } else {

                    $("tr.clickable."+element).removeClass("expired")

                    $("tr.clickable."+element).attr('onclick','enableChatRoom(\"'+element+'\")')
                    $("tr.clickable." + element).append("<input id=\"" + element + "\"type=\"hidden\" value=\"" + result + "\"/>")
                    $("tr." + element + " span").remove();
                }
            }
        });
    }

    //Add "active" class to the clicked TR
    if (passwordupdated) {
        $("tr." + element).addClass("active");
        roomName = element;
        $("#chatform").css("display",'block')
    }


    $("#board tbody tr").css("display", "none");

    $("#board tbody tr." + element).css("display", "block");

}

/* attach a submit handler to the form */

$(document).on('click', '#roomList th', function (event) {

        bootbox.dialog({
                message: '<div class="row">  <form id="addroom" class="form-horizontal">' +
                '<input type="text" maxlength="20" name="name" class="form-control" placeholder="Room Name"/>' +
                '<input type="text" maxlength="20" name="password" class="form-control" placeholder="Passwd" />' +

                '</div > ',
                buttons: {
                    success: {
                        label:'Save <span class="glyphicon glyphicon-ok"></span>',
                        className: "btn-success",
                        callback: function () {
                            /* get some values from elements on the page: */
                            var $form = $(this),
                                term = $form.find('input[name="name"]').val(),
                                password = $form.find('input[name="password"]').val(),
                                url = $form.attr('action');

                            /* Send the data using post */
                            var posting = $.post('/addroomdetails', {
                                name: term
                            });

                            /* Put the results in a table */
                            posting.done(function (data) {
                                console.log(data);
                                $("#roomList tbody").append("<tr class='active clickable " + data + "' onclick='enableChatRoom(\"" + data + "\")' data-href='url://' ><td>" + data + "</td>");
                                $("#roomList tbody").append("<input type=\"hidden\" value=\"" + password + "\" id=\"" + data + "\" > </tr>");
                            });

                            console.log(term + " selected by default");
                            enableChatRoom(term); // select the room entered by default

                        }
                    }
                }

            }
        );
    });

// Select first room by default
    function selectFirstRoombyDefault() {
        var firstRoom = $("#roomList tbody").find("td:first").text();

        console.log("First room is : " + firstRoom);
        if (firstRoom.length != 0)
            enableChatRoom(firstRoom, true);

    }

    getUserRooms(processUserRooms);
