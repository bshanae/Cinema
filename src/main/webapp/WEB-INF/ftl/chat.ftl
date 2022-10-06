<html>
<head>
    <title>Chat WebSocket</title>
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
            let p = document.createElement('p');
            p.style.wordWrap = 'break-word';
            p.appendChild(
                document.createTextNode(
                    messageOutput.senderId + ': ' + messageOutput.body
                )
            );
            response.appendChild(p);
        }

        window.addEventListener('beforeunload', disconnect, false);
        window.addEventListener('load', connect, false);
    </script>
</head>
<div>
    <div>
        <input type="text" id="message-body" placeholder="Write a message..."/>
        <button id="sendMessage" onclick="sendMessage();">Send</button>
        <p id="response">
            <#list messages as message>
                ${message.senderId}: ${message.body}<br>
            </#list>
        </p>
    </div>
</div>
</body>
</html>