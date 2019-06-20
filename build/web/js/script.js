/*$(document).ready( function() {
    $( "#slider" ).slider();
    })
*/
var slideInterval = 3000;
var $imgVisible, $img;
var i=0;
var nbImg;

function moveForward() {
        $imgVisible.hide();
        $imgVisible=$imgVisible.next('img');
        $imgVisible.show();
        i++;
        if (i>= nbImg) {
            i = 0;
            
            setTimeout(startCarousel, slideInterval);
        }
        else {
            setTimeout(moveForward, slideInterval);
        }
}

function startCarousel() {
    $('.slider').hide();
    nbImg=$('img').length;
    if (nbImg > 0) {
        $imgVisible = $('img:first');
        $imgVisible.show();
        i++; 
        if (i< nbImg) {  
            setTimeout(moveForward, slideInterval);
        }
    }
}
$(document).ready( function() {
    /* $('.carousel').carousel();    */
   startCarousel();
});