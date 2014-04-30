
var wsUri = "ws://" + (document.location.hostname === "" ? "localhost" : document.location.hostname) + ":" +
                (document.location.port === "" ? "8080" : document.location.port) + "/websocket-echo/echo";
        
var websocket = new WebSocket(wsUri);

websocket.onopen = function (evt){
    console.log("CONNECTED..");
};

websocket.onerror = function (evt){
    console.log("ERROR: " + evt.data);
};

websocket.onmessage = function (evt){
    console.log("RECIEVED: " + evt.data);
    
    var output = document.getElementById('msg');
    if (output){
        var pre = document.createElement("p");
        pre.style.wordWrap = "break-word";
        pre.innerHTML = evt.data;
        output.appendChild(pre);
    }
};

function send(){
    message = document.getElementById('sendText').value;
    console.log("SENDING: " + message);
    websocket.send(message);
}

