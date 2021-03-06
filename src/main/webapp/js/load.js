var selectedRowId = null;

$(document).ready(function() {
    $(document).on('click',".line",function(){
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

    $('custom-date').prop('max', function(){
        return new Date().toJSON().split('T')[0];
    });
});

function redirectPage(uri) {
    console.log(uri);
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
        case 'orderByDriver':
            document.getElementById('driver_order_id').value = 'revoke_order';
            break;
        case 'delete_ban':
            document.getElementById('command_id').value = type;
            document.getElementById('ban_id').value = selectedRowId;
            break;
        case 'delete_bonus':
            document.getElementById('command_id').value = type;
            document.getElementById('bonus_id').value = selectedRowId;
            break;
    }
}

function banCommand() {
    document.getElementById('ban_id').value = selectedRowId;
}

function bonusCommand() {
    document.getElementById('bonus_id').value = selectedRowId;
}

function loadCommand(type) {
    switch (type){
        case 'order':
            document.getElementById('command_id').value = 'make_order';
            document.getElementById('driver_id').value = selectedRowId;
            break;
        case 'payOrder':
            document.getElementById('command_id').value = 'pay_order';
            document.getElementById('driver_id').value = selectedRowId;
            break;
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
        case 'show_order':
        case 'show_transaction':
        case 'show_ban':
        case 'show_bonus':
            document.getElementById('admin_command_id').value = type;
            break;
        case 'confirm':
            document.getElementById('driver_order_id').value = 'confirm_order';
            break;
        case 'complete':
            document.getElementById('driver_order_id').value = 'complete_order';
            submitForm('orderForm');
            break;
        case 'rollback_transaction':
            console.log(selectedRowId);
            document.getElementById('transactionId').value = selectedRowId;
            break;
        case 'load_ban_form':
            document.getElementById('command_id').value = type;
            document.getElementById('ban_id').value = selectedRowId;
            break;
        case 'load_bonus_form':
            document.getElementById('command_id').value = type;
            document.getElementById('bonus_id').value = selectedRowId;
            break;
        case 'fill_bonus_form':
            document.getElementById('user_command_id').value = type;
            document.getElementById('user_id').value = selectedRowId;
            break;
        case 'map':
            console.log('map');
            document.getElementById('user_command_id').value = 'load_map';
            break;
    }

}

function submitForm(id) {
    document.getElementById(id).submit();
}

function go(id) {
    var distance = document.getElementById('distance_id').value;
    var duration = document.getElementById('duration_id').value;
    var departure = document.getElementById('departure_id').value;
    var destination = document.getElementById('destination_id').value;

    if (distance && duration && departure && destination){
        document.getElementById(id).submit();
    } else {
        alert("Please, fill search box");
    }
}

function setCarFormData() {
    var selectedCarData = $('#carListId').val();
    var result = selectedCarData.split(' ');
    var date = document.getElementById('release_date_id');
    date.setAttribute("min", result[2]);
}


