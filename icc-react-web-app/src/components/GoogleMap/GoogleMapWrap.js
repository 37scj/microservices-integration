import React from 'react';
import { compose, withProps, withHandlers } from "recompose"
import {
    withScriptjs,
    withGoogleMap,
    GoogleMap,
    Marker,
} from "react-google-maps"
import { MarkerClusterer } from "react-google-maps/lib/components/addons/MarkerClusterer"

const key = process.env.REACT_APP_GMAPSKEY;

const MapWithMarkers = compose(
    withProps({
        googleMapURL: "https://maps.googleapis.com/maps/api/js?key=" + key + "&v=3.exp&libraries=geometry,drawing,places",
        loadingElement: <div style={{ height: `100%` }} />,
        containerElement: <div style={{ height: `600px` }} />,
        mapElement: <div style={{ height: `100%` }} />,
    }),
    withHandlers({
        onMarkerClustererClick: () => (markerClusterer) => {
            const clickedMarkers = markerClusterer.getMarkers()
            console.log(`Current clicked markers length: ${clickedMarkers.length}`)
            console.log(clickedMarkers)
        },
    }),
    withScriptjs,
    withGoogleMap,
)(props => {
    console.log(props);
    return (<GoogleMap
        defaultZoom={7}
        defaultCenter={props.defaultCenter ?? { lat: -23.533773, lng: -46.625290 }}//são paulo
    >
        <MarkerClusterer
            onClick={props.onMarkerClustererClick}
            averageCenter
            enableRetinaIcons
            gridSize={10}
        >
            {((props?.markers) ?? []).map((mark, i) => (<Marker key={props.keyName ? mark[props.keyName] : 'marker_' + i} 
                      position={mark.position || { lat: mark.latitude, lng: mark.longitude }}
                       />))}

        </MarkerClusterer>
    </GoogleMap>)
});

export default MapWithMarkers;

/*
    const { markers, setMarks } = useState(props.markers || []);
    function addMarks(key, lat, lng) {
        const mark = { key, position: {lat, lng} };
        let find=markers.find(m=>m.key===key);
        if(find){
            Object.assign(find, mark)
        }
        setMarks(markers.push(find));
    }

    addMarks(1, -34.397, 150.644);
*/
