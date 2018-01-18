var selectedRowId = null;
$(document).ready(function() {
    $(".line").click(function(event){
        console.log("click");
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
        }
    });
    $('[type="date"]').prop('max', function(){
        return new Date().toJSON().split('T')[0];
    });
});

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
            document.getElementById('user_command_id').value = 'delete_user';
            document.getElementById('user_id').value = selectedRowId;
            break;
        case "ban":
            document.getElementById('ban_command_id').value = 'unban_user';
            document.getElementById('user_id').value = selectedRowId;
            break;
    }
}

function banCommand() {
    document.getElementById('ban_id').value = selectedRowId;
}

function loadCommand(type) {
    switch (type){
        case "car":
            document.getElementById('car_command_id').value = 'load_valid_car_to_edit';
            document.getElementById('car_id').value = selectedRowId;
            break;
        case 'user':
            document.getElementById('user_command_id').value = 'load_edit_user';
            document.getElementById('user_id').value = selectedRowId;
            break;
        case 'ban':
            document.getElementById('user_command_id').value = 'fill_ban_form';
            document.getElementById('user_id').value = selectedRowId;
            break;
        case 'show_banned_users':
        case 'find_all_passenger':
        case 'load_sign_up_page':
        case 'find_all_driver':
        case 'find_all_valid_cars':
        case 'load_valid_car_to_add':
            document.getElementById('admin_command_id').value = type;
            break;
    }

}

function submitForm(id) {
    document.getElementById(id).submit();
}

function setCarFormData() {
    var selectedCarData = $('#carListId').val();
    var result = selectedCarData.split(' ');
    var date = document.getElementById('release_date_id');
    date.setAttribute("min", result[2]);
}


