/**
 * Created by User on 12/29/2015.
 */
var marker;

function initMap() {
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 13,
        center: {lat: 43.850572, lng: 18.393421}

    });

    marker = new google.maps.Marker({
        map: map,
        draggable: true,
        animation: google.maps.Animation.DROP,
        position: {lat: 43.850572, lng: 18.393421}
    });
    marker.addListener('click', toggleBounce);
}

function toggleBounce() {
    if (marker.getAnimation() !== null) {
        marker.setAnimation(null);
    } else {
        marker.setAnimation(google.maps.Animation.BOUNCE);
    }
}
google.maps.event.addDomListener(window, 'load', initMap());
