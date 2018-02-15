function initMap() {
    var currentGeoLocation = {lat: 0.0, lng: 0.0};
    var destinationGeoLocation = {lat: 0.0, lng: 0.0};
    var mapButton = document.getElementById('map-button');
    var input = document.getElementById('pac-input');
    var searchBox = new google.maps.places.SearchBox(input);
    var markers = [];

    function success(position) {
        var directionsService = new google.maps.DirectionsService;
        var directionsDisplay = new google.maps.DirectionsRenderer;
        var distanceMatrixService = new google.maps.DistanceMatrixService();
        var duration;
        var distance;

        currentGeoLocation.lat = position.coords.latitude;
        currentGeoLocation.lng = position.coords.longitude;

        function calculateAndDisplayRoute(directionsService, directionsDisplay) {
            directionsService.route({
                origin: currentGeoLocation,
                destination: destinationGeoLocation,
                travelMode: 'DRIVING'
            }, function(response, status) {
                if (status === 'OK') {
                    var myRoute = response.routes[0].legs[0];

                    duration = myRoute.duration.value;
                    distance = myRoute.distance.value;

                    document.getElementById('duration_id').value = duration;
                    document.getElementById('distance_id').value = distance;

                    directionsDisplay.setDirections(response);
                } else {
                    window.alert('Directions request failed due to ' + status);
                }
            });
        }

        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 14,
            center: currentGeoLocation
        });

        var marker = new google.maps.Marker({
            position: currentGeoLocation,
            map: map,
            title: 'Hello World!'
        });

        map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
        map.controls[google.maps.ControlPosition.TOP_LEFT].push(mapButton);

        // Bias the SearchBox results towards current map's viewport.
        map.addListener('bounds_changed', function() {
            searchBox.setBounds(map.getBounds());
        });

        // Listen for the event fired when the user selects a prediction and retrieve
        // more details for that place.
        searchBox.addListener('places_changed', function() {
            var places = searchBox.getPlaces();
            if (places.length === 0) {
                return;
            }
            // Clear out the old markers.
            markers.forEach(function(marker) {
                marker.setMap(null);
            });
            markers = [];
            // For each place, get the icon, name and location.
            var bounds = new google.maps.LatLngBounds();
            places.forEach(function(place) {
                if (!place.geometry) {
                    console.log("Returned place contains no geometry");
                    return;
                }
                var icon = {
                    url: place.icon,
                    size: new google.maps.Size(71, 71),
                    origin: new google.maps.Point(0, 0),
                    anchor: new google.maps.Point(17, 34),
                    scaledSize: new google.maps.Size(25, 25)
                };

                // Create a marker for each place.
                markers.push(new google.maps.Marker({
                    map: map,
                    icon: icon,
                    title: place.name,
                    position: place.geometry.location,
                    distance: distance
                }));

                destinationGeoLocation.lat = place.geometry.location.lat();
                destinationGeoLocation.lng = place.geometry.location.lng();

                document.getElementById('departure_id').value = currentGeoLocation.lat + " " + currentGeoLocation.lng;
                document.getElementById('destination_id').value = destinationGeoLocation.lat + " " + destinationGeoLocation.lng;
                calculateAndDisplayRoute(directionsService, directionsDisplay);

                if (place.geometry.viewport) {
                    bounds.union(place.geometry.viewport);
                } else {
                    bounds.extend(place.geometry.location);
                }
            });
            map.fitBounds(bounds);
            directionsDisplay.setMap(map);
        });
    }

    navigator.geolocation.getCurrentPosition(success);
}


