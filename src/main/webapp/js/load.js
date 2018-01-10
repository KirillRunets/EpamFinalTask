var selectedRowId = null;
$(document).ready(function() {
    $(".line").click(function(event){
        var id = $(this).attr('id');
        var doc = document.getElementById(selectedRowId);
        if (doc){
            doc.classList.remove("click_color");
        }
        if (id === selectedRowId){
            selectedRowId = null;
            $("#btn-load1").hide();
            $("#btn-load2").hide();
            $("#ban-button").hide();
            $("#bonus-button").hide();
        } else {
            selectedRowId = id;
            $(this).addClass("click_color");
            $("#btn-load1").show();
            $("#btn-load2").show();
            $("#ban-button").show();
            $("#bonus-button").show();
            document.getElementById('ban_id').value = selectedRowId;
        }
    });
});

function getSelectedRowId() {
    return selectedRowId;
}

function reLoad(command) {
    window.location.href = command + selectedRowId;
}

function redirectPage(uri) {
    window.location.href = uri;
}

function deleteCommand(type) {
    switch (type){
        case "car":
            document.getElementById('car_command_id').value = 'delete_car';
            document.getElementById('car_id').value = selectedRowId;
            break;
        case "user":
            break;
        case "ban":
            break;
    }
}

function editCommand(type) {
    switch (type){
        case "car":
            document.getElementById('car_command_id').value = 'edit_car';
            document.getElementById('car_id').value = selectedRowId;
            break;
        case "user":
            break;
        case "ban":
            break;
    }
}

function loadCommand(type) {
    switch (type){
        case "car":
            document.getElementById('car_command_id').value = 'load_valid_car_to_edit';
            document.getElementById('car_id').value = selectedRowId;
            break;
        case "user":
            break;
        case "ban":
            break;
    }
}
