$(function() {

    var userConsole = $("textarea#user-console");

    function getISODateTime(d){
        // padding function
        var s = function(a,b){return(1e15+a+"").slice(-b)};

        // default date parameter
        if (typeof d === 'undefined'){
            d = new Date();
        };

        // return ISO datetime
        return s(d.getHours(),2) + ':' +
                s(d.getMinutes(),2) + ':' +
                s(d.getSeconds(),2);
    }

    $("#friends").selectable({
        stop: function() {
            var result = $( "#select-result" ).empty();

            //TODO fix this this context!
            $( ".ui-selected", this ).each(function() {
                //original - var index = $( "#friends li" ).index( this );
                var index = $( "li" ).index( this );
                console.log(index);
                if (result[0].innerText !== "") {
                    result.append(", ");
                }
                result.append( this.innerText );
            });
        }
    });

    var chatarea = $("#chatarea");
    function updateChatArea(sender, message) {

        if (message !== null) {
            chatarea.append("\n(" + getISODateTime(new Date()) + ") " + sender + ": " + message);
        } else {
            chatarea.append("\n");
        }
        chatarea.scrollTop(chatarea[0].scrollHeight);
    }

    var addFriend = function (friends) {
        friends.forEach(function (friend) {

            //don't add if the friend already exists - FIXME there isn't an easier way to select all users but the current one
            if ($('#friends #friendlist-'+ friend).length === 0) {
                var e = $('<li id="friendlist-' + friend + '" class="ui-widget-content">' + friend + '</li>');
                $("#friends").append(e);
            }
        });
    }

    function delFriend(friend) {
        console.log(friend);
        //$('#friends li:contains("hi")')
        $('#friends #friendlist-'+ friend).remove();
    }

    function dummyInit() {

        /*for (var i = 0; i < 100; i++ ) {
            var e = $('<li class="ui-widget-content">Friend '+ i +'</li>');
            $("#friends").append(e);
        }*/

        for (var i = 0; i < 24; i++) {
            updateChatArea(null, null);
        }

        /*for (var i = 0; i < 5; i++) {
            updateChatArea("Random fella", "hola");
        }*/
    }

    dummyInit();

    $(document).ready(function() {

        var webSocket;
        $.blockUI({ message: $('#login'), css: { width: '275px' } });

        $('#login-button').click(function() {

            // update the block message
            $.blockUI({ message: "<h1>Logging in...</h1>" });

            var userName = $("#username").val();
            console.log("Logging in as " + userName);
            webSocket = new WebSocket('ws://localhost:8025/websocket/message/' + userName);

            webSocket.onerror = function (event) {
                console.log(event)
            };

            webSocket.onopen = function (event) {
                $("#loggedin-username").append(userName);
                $("textarea#user-console").focus();
                $.unblockUI();
                $.growlUI('User Notification', 'Joining!');
            };

            webSocket.onmessage = function (event) {
                console.log(event.data);
                var message = JSON.parse(event.data);
                if (message.UserMessage) {
                    var userMessage = message.UserMessage;
                    updateChatArea(userMessage.userName, userMessage.message);
                } else if (message.UserList) {
                    var userList = message.UserList.users;
                    addFriend(userList);
                } else if (message.UserJoin) {
                    var userJoinName = message.UserJoin.userName;
                    addFriend([ userJoinName]);
                    $.growlUI('User Notification', 'Joining: ' + userJoinName);
                    updateChatArea(userName, "Joining...");
                } else if (message.UserLeave) {
                    var userLeaveName = message.UserLeave.userName;
                    delFriend(userLeaveName);
                    $.growlUI('User Notification', 'Leaving: ' + userLeaveName);
                    updateChatArea(userName, "Leaving...");
                }
            };

            webSocket.onclose = function(event) {
                $.blockUI({ message: $('#loggedout'), css: { width: '275px' } });
            }

            //session keep-alive pinging
            setInterval(function() {
                webSocket.send(".P.");
            }, 3000);
        });

        var handleConsoleText = function handleConsoleText () {
             var message = userConsole.val();
             webSocket.send(message);
             userConsole.val("");
         };

        $( "#submit" )
            .button()
            .click(handleConsoleText);

        $( "#retry" )
            .button()
            .click(function() {
                location.reload();
            });

        $('#loggedin-username').click(function() {
            webSocket.close();
        });

        $("textarea#user-console").keydown(function(event) {

            var keyPressed = event.keyCode;
            if (keyPressed === 13) {
                handleConsoleText();
            }
        }).keyup(function(event) {

            var keyPressed = event.keyCode;
            if (keyPressed === 13) {
                userConsole.val("");
            }
        });
    });

});
