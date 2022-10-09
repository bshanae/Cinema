<html>
<head>
    <title>Chat WebSocket</title>
    <style>
        .container {
            height: 100%;
            padding: 0;
            margin: 0;
            display: flex;
            align-items: center;
            flex-direction: column;
        }
    </style>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.min.js"
            integrity="sha512-1QvjE7BtotQjkq8PxLeF6P46gEpBRXuskzIVgjFpekzFVF4yjRgrQvTG1MTOJ3yQgvTteKAcO7DSZI92+u/yZw=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"
            integrity="sha512-iKDtgDyTHjAitUDdLljGhenhPwrbBfqTKWO1mkhSFH3A7blITC9MhYon6SjnMhp4o0rADGw9yAC6EW4t5a4K3g=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script type="text/javascript">
        let stompClient = null;

        let userId = localStorage.getItem('user-id');
        if (userId == null) {
            userId = 'user' + Math.floor(Math.random() * (999 - 100 + 1) + 100);
            localStorage.setItem('user-id', userId);
        }

        function connect() {
            console.log('Connecting');

            const socket = new SockJS('/ws');
            stompClient = Stomp.over(socket);
            stompClient.connect(
                {},
                frame => {
                    console.log('Connected: ' + frame);

                    stompClient.subscribe(
                        '/films/${filmId}/chat/receive',
                        messageOutput => {
                            onMessageReceived(JSON.parse(messageOutput.body));
                        }
                    );
                },
                error => {
                    console.log('Connection error: ' + error);
                }
            );
        }

        function disconnect() {
            console.log('Disconnecting');

            if (stompClient != null)
                stompClient.disconnect();
        }

        function sendMessage() {
            console.log('Sending a message');

            stompClient.send(
                '/films/${filmId}/chat/send',
                {},
                JSON.stringify({
                    'filmId': ${filmId},
                    'sendTime': Date.now(),
                    'senderId': userId,
                    'body': document.getElementById('message-body').value
                })
            );
        }

        function onMessageReceived(messageOutput) {
            console.log('Received a message');

            let response = document.getElementById('response');

            let newElement = document.createElement('div');
            newElement.appendChild(
                document.createTextNode(
                    messageOutput.senderId + ': ' + messageOutput.body
                )
            );
            response.appendChild(newElement);

            response.scrollTop = response.scrollHeight;
        }

        window.addEventListener('beforeunload', disconnect, false);
        window.addEventListener('load', connect, false);
    </script>
</head>
<body>
<div class='container'>
    <div id="response" style="overflow-y: scroll; width: 400px; height: 200px; margin-bottom: 50px;">
        <#list messages as message>
            <div>${message.senderId}: ${message.body}</div>
        </#list>
    </div>
    <div style="width: 400px;">
        <input type="text" id="message-body" placeholder="Write a message..."/>
        <button id="sendMessage" onclick="sendMessage();">Send</button>
    </div>
</div>
</body>
</html>