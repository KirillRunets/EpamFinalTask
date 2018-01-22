var modal = document.getElementById('myModal');
var btn = document.getElementById("modal-button");
var span = document.getElementsByClassName("close")[0];

btn.onclick = function() {
    modal.style.display = "inline-block";
};

span.onclick = function() {
    modal.style.display = "none";
};

window.onclick = function(event) {
    if (event.target === modal) {
        modal.style.display = "none";
    }
};

