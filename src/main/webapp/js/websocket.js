var ws;

function connect() {
    ws = new WebSocket("ws://" + document.location.host + "/buber/order/");

   /* ws.onmessage = function(event) {
        var log = document.getElementById("log");
        console.log(event.data);
        var message = JSON.parse(event.data);
        log.innerHTML += message.from + " : " + message.content + "\n";
    };*/
}

function doCommand() {
    var command = document.getElementById("command").value;
    var id = getSelectedRowId();
    var json = JSON.stringify({
        "command":command,
        "to":id
    });
    ws.send(json);
}