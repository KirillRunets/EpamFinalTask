var $star_rating = $('.star-rating .fa');
var $static_rating = $('.static-rating .fa');
var ratingValue = 0;

var setRatingStar = function() {
    return $star_rating.each(function() {
        if (parseInt($star_rating.siblings('input.rating-value').val()) >= parseInt($(this).data('rating'))) {
            return $(this).removeClass('fa-star-o').addClass('fa-star');
        } else {
            return $(this).removeClass('fa-star').addClass('fa-star-o');
        }
    });
};

var setStaticRatingStar = function() {
    return $static_rating.each(function() {
        if (parseInt($(this).siblings('input.rating-value').val()) >= parseInt($(this).data('rating'))) {
            return $(this).removeClass('fa-star-o').addClass('fa-star');
        } else {
            return $(this).removeClass('fa-star').addClass('fa-star-o');
        }
    });
};

$star_rating.on('click', function() {
    ratingValue = $(this).attr('data-rating');
    $star_rating.siblings('input.rating-value').val($(this).data('rating'));
    return setRatingStar();
});

function setValue() {
    document.getElementById('rating').value = ratingValue;
}
setRatingStar();
setStaticRatingStar();
$(document).ready(function() {
});