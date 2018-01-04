var selectedRowId = null;
$(document).ready(function() {
    $(".line").click(function(event){
        var tr = event.target.parentNode;
        var id = tr.getAttribute("id");
        var doc = document.getElementById(selectedRowId);
        if (doc){
            doc.classList.remove("click_color");
        }
        if (id === selectedRowId){
            selectedRowId = null;
            $("#btn-load1").hide();
            $("#btn-load2").hide();
        } else {
            selectedRowId = id;
            $(this).addClass("click_color");
            $("#btn-load1").show();
            $("#btn-load2").show();
        }
    });
});

function choosedItem() {
    return selectedRowId;
}

function reLoad(command) {
    window.location.href = command + selectedRowId;
}

function redirectPage(uri) {
    window.location.href = uri;
}
