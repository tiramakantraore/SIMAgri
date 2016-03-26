(function(e,t){if(typeof exports==="object"){module.exports=t()}else if(typeof define==="function"&&define.amd){define("GMaps",[],t)}e.GMaps=t()})(this,function(){if(!(typeof window.google==="object"&&window.google.maps)){throw"Google Maps API is required. Please register the following JavaScript library http://maps.google.com/maps/api/js?sensor=true."}var t=function(e,t){var n;if(e===t){return e}for(n in t){e[n]=t[n]}return e};var n=function(e,t){var n;if(e===t){return e}for(n in t){if(e[n]!=undefined){e[n]=t[n]}}return e};var r=function(e,t){var n=Array.prototype.slice.call(arguments,2),r=[],i=e.length,s;if(Array.prototype.map&&e.map===Array.prototype.map){r=Array.prototype.map.call(e,function(e){callback_params=n;callback_params.splice(0,0,e);return t.apply(this,callback_params)})}else{for(s=0;s<i;s++){callback_params=n;callback_params.splice(0,0,e[s]);r.push(t.apply(this,callback_params))}}return r};var i=function(e){var t=[],n;for(n=0;n<e.length;n++){t=t.concat(e[n])}return t};var s=function(e,t){var n=e[0],r=e[1];if(t){n=e[1];r=e[0]}return new google.maps.LatLng(n,r)};var o=function(e,t){var n;for(n=0;n<e.length;n++){if(!(e[n]instanceof google.maps.LatLng)){if(e[n].length>0&&typeof e[n][0]=="object"){e[n]=o(e[n],t)}else{e[n]=s(e[n],t)}}}return e};var u=function(e,t){var n,e=e.replace("#","");if("jQuery"in this&&t){n=$("#"+e,t)[0]}else{n=document.getElementById(e)}return n};var a=function(e){var t=0,n=0;if(e.offsetParent){do{t+=e.offsetLeft;n+=e.offsetTop}while(e=e.offsetParent)}return[t,n]};var f=function(e){"use strict";var n=document;var r=function(e){if(!this)return new r(e);e.zoom=e.zoom||15;e.mapType=e.mapType||"roadmap";var i=this,s,o=["bounds_changed","center_changed","click","dblclick","drag","dragend","dragstart","idle","maptypeid_changed","projection_changed","resize","tilesloaded","zoom_changed"],f=["mousemove","mouseout","mouseover"],l=["el","lat","lng","mapType","width","height","markerClusterer","enableNewStyle"],c=e.el||e.div,h=e.markerClusterer,p=google.maps.MapTypeId[e.mapType.toUpperCase()],d=new google.maps.LatLng(e.lat,e.lng),v=e.zoomControl||true,m=e.zoomControlOpt||{style:"DEFAULT",position:"TOP_LEFT"},g=m.style||"DEFAULT",y=m.position||"TOP_LEFT",b=e.panControl||true,w=e.mapTypeControl||true,E=e.scaleControl||true,S=e.streetViewControl||true,x=x||true,T={},N={zoom:this.zoom,center:d,mapTypeId:p},C={panControl:b,zoomControl:v,zoomControlOptions:{style:google.maps.ZoomControlStyle[g],position:google.maps.ControlPosition[y]},mapTypeControl:w,scaleControl:E,streetViewControl:S,overviewMapControl:x};if(typeof e.el==="string"||typeof e.div==="string"){this.el=u(c,e.context)}else{this.el=c}if(typeof this.el==="undefined"||this.el===null){throw"No element defined."}window.context_menu=window.context_menu||{};window.context_menu[i.el.id]={};this.controls=[];this.overlays=[];this.layers=[];this.singleLayers={};this.markers=[];this.polylines=[];this.routes=[];this.polygons=[];this.infoWindow=null;this.overlay_el=null;this.zoom=e.zoom;this.registered_events={};this.el.style.width=e.width||this.el.scrollWidth||this.el.offsetWidth;this.el.style.height=e.height||this.el.scrollHeight||this.el.offsetHeight;google.maps.visualRefresh=e.enableNewStyle;for(s=0;s<l.length;s++){delete e[l[s]]}if(e.disableDefaultUI!=true){N=t(N,C)}T=t(N,e);for(s=0;s<o.length;s++){delete T[o[s]]}for(s=0;s<f.length;s++){delete T[f[s]]}this.map=new google.maps.Map(this.el,T);if(h){this.markerClusterer=h.apply(this,[this.map])}var k=function(e,t){var n="",r=window.context_menu[i.el.id][e];for(var s in r){if(r.hasOwnProperty(s)){var o=r[s];n+='<li><a id="'+e+"_"+s+'" href="#">'+o.title+"</a></li>"}}if(!u("gmaps_context_menu"))return;var f=u("gmaps_context_menu");f.innerHTML=n;var l=f.getElementsByTagName("a"),c=l.length,s;for(s=0;s<c;s++){var h=l[s];var p=function(n){n.preventDefault();r[this.id.replace(e+"_","")].action.apply(i,[t]);i.hideContextMenu()};google.maps.event.clearListeners(h,"click");google.maps.event.addDomListenerOnce(h,"click",p,false)}var d=a.apply(this,[i.el]),v=d[0]+t.pixel.x-15,m=d[1]+t.pixel.y-15;f.style.left=v+"px";f.style.top=m+"px";f.style.display="block"};this.buildContextMenu=function(e,t){if(e==="marker"){t.pixel={};var n=new google.maps.OverlayView;n.setMap(i.map);n.draw=function(){var r=n.getProjection(),i=t.marker.getPosition();t.pixel=r.fromLatLngToContainerPixel(i);k(e,t)}}else{k(e,t)}};this.setContextMenu=function(e){window.context_menu[i.el.id][e.control]={};var t,r=n.createElement("ul");for(t in e.options){if(e.options.hasOwnProperty(t)){var s=e.options[t];window.context_menu[i.el.id][e.control][s.name]={title:s.title,action:s.action}}}r.id="gmaps_context_menu";r.style.display="none";r.style.position="absolute";r.style.minWidth="100px";r.style.background="white";r.style.listStyle="none";r.style.padding="8px";r.style.boxShadow="2px 2px 6px #ccc";n.body.appendChild(r);var o=u("gmaps_context_menu");google.maps.event.addDomListener(o,"mouseout",function(e){if(!e.relatedTarget||!this.contains(e.relatedTarget)){window.setTimeout(function(){o.style.display="none"},400)}},false)};this.hideContextMenu=function(){var e=u("gmaps_context_menu");if(e){e.style.display="none"}};var L=function(t,n){google.maps.event.addListener(t,n,function(t){if(t==undefined){t=this}e[n].apply(this,[t]);i.hideContextMenu()})};google.maps.event.addListener(this.map,"zoom_changed",this.hideContextMenu);for(var A=0;A<o.length;A++){var O=o[A];if(O in e){L(this.map,O)}}for(var A=0;A<f.length;A++){var O=f[A];if(O in e){L(this.map,O)}}google.maps.event.addListener(this.map,"rightclick",function(t){if(e.rightclick){e.rightclick.apply(this,[t])}if(window.context_menu[i.el.id]["map"]!=undefined){i.buildContextMenu("map",t)}});this.refresh=function(){google.maps.event.trigger(this.map,"resize")};this.fitZoom=function(){var e=[],t=this.markers.length,n;for(n=0;n<t;n++){if(typeof this.markers[n].visible==="boolean"&&this.markers[n].visible){e.push(this.markers[n].getPosition())}}this.fitLatLngBounds(e)};this.fitLatLngBounds=function(e){var t=e.length;var n=new google.maps.LatLngBounds;for(var r=0;r<t;r++){n.extend(e[r])}this.map.fitBounds(n)};this.setCenter=function(e,t,n){this.map.panTo(new google.maps.LatLng(e,t));if(n){n()}};this.getElement=function(){return this.el};this.zoomIn=function(e){e=e||1;this.zoom=this.map.getZoom()+e;this.map.setZoom(this.zoom)};this.zoomOut=function(e){e=e||1;this.zoom=this.map.getZoom()-e;this.map.setZoom(this.zoom)};var M=[],_;for(_ in this.map){if(typeof this.map[_]=="function"&&!this[_]){M.push(_)}}for(s=0;s<M.length;s++){(function(e,t,n){e[n]=function(){return t[n].apply(t,arguments)}})(this,this.map,M[s])}};return r}(this);f.prototype.createControl=function(e){var t=document.createElement("div");t.style.cursor="pointer";if(e.disableDefaultStyles!==true){t.style.fontFamily="Roboto, Arial, sans-serif";t.style.fontSize="11px";t.style.boxShadow="rgba(0, 0, 0, 0.298039) 0px 1px 4px -1px"}for(var n in e.style){t.style[n]=e.style[n]}if(e.id){t.id=e.id}if(e.classes){t.className=e.classes}if(e.content){if(typeof e.content==="string"){t.innerHTML=e.content}else if(e.content instanceof HTMLElement){t.appendChild(e.content)}}if(e.position){t.position=google.maps.ControlPosition[e.position.toUpperCase()]}for(var r in e.events){(function(t,n){google.maps.event.addDomListener(t,n,function(){e.events[n].apply(this,[this])})})(t,r)}t.index=1;return t};f.prototype.addControl=function(e){var t=this.createControl(e);this.controls.push(t);this.map.controls[t.position].push(t);return t};f.prototype.removeControl=function(e){var t=null;for(var n=0;n<this.controls.length;n++){if(this.controls[n]==e){t=this.controls[n].position;this.controls.splice(n,1)}}if(t){for(n=0;n<this.map.controls.length;n++){var r=this.map.controls[e.position];if(r.getAt(n)==e){r.removeAt(n);break}}}return e};f.prototype.createMarker=function(e){if(e.lat==undefined&&e.lng==undefined&&e.position==undefined){throw"No latitude or longitude defined."}var n=this,r=e.details,i=e.fences,s=e.outside,o={position:new google.maps.LatLng(e.lat,e.lng),map:null},u=t(o,e);delete u.lat;delete u.lng;delete u.fences;delete u.outside;var a=new google.maps.Marker(u);a.fences=i;if(e.infoWindow){a.infoWindow=new google.maps.InfoWindow(e.infoWindow);var f=["closeclick","content_changed","domready","position_changed","zindex_changed"];for(var l=0;l<f.length;l++){(function(t,n){if(e.infoWindow[n]){google.maps.event.addListener(t,n,function(t){e.infoWindow[n].apply(this,[t])})}})(a.infoWindow,f[l])}}var c=["animation_changed","clickable_changed","cursor_changed","draggable_changed","flat_changed","icon_changed","position_changed","shadow_changed","shape_changed","title_changed","visible_changed","zindex_changed"];var h=["dblclick","drag","dragend","dragstart","mousedown","mouseout","mouseover","mouseup"];for(var l=0;l<c.length;l++){(function(t,n){if(e[n]){google.maps.event.addListener(t,n,function(){e[n].apply(this,[this])})}})(a,c[l])}for(var l=0;l<h.length;l++){(function(t,n,r){if(e[r]){google.maps.event.addListener(n,r,function(n){if(!n.pixel){n.pixel=t.getProjection().fromLatLngToPoint(n.latLng)}e[r].apply(this,[n])})}})(this.map,a,h[l])}google.maps.event.addListener(a,"click",function(){this.details=r;if(e.click){e.click.apply(this,[this])}if(a.infoWindow){n.hideInfoWindows();a.infoWindow.open(n.map,a)}});google.maps.event.addListener(a,"rightclick",function(t){t.marker=this;if(e.rightclick){e.rightclick.apply(this,[t])}if(window.context_menu[n.el.id]["marker"]!=undefined){n.buildContextMenu("marker",t)}});if(a.fences){google.maps.event.addListener(a,"dragend",function(){n.checkMarkerGeofence(a,function(e,t){s(e,t)})})}return a};f.prototype.addMarker=function(e){var t;if(e.hasOwnProperty("gm_accessors_")){t=e}else{if(e.hasOwnProperty("lat")&&e.hasOwnProperty("lng")||e.position){t=this.createMarker(e)}else{throw"No latitude or longitude defined."}}t.setMap(this.map);if(this.markerClusterer){this.markerClusterer.addMarker(t)}this.markers.push(t);f.fire("marker_added",t,this);return t};f.prototype.addMarkers=function(e){for(var t=0,n;n=e[t];t++){this.addMarker(n)}return this.markers};f.prototype.hideInfoWindows=function(){for(var e=0,t;t=this.markers[e];e++){if(t.infoWindow){t.infoWindow.close()}}};f.prototype.removeMarker=function(e){for(var t=0;t<this.markers.length;t++){if(this.markers[t]===e){this.markers[t].setMap(null);this.markers.splice(t,1);if(this.markerClusterer){this.markerClusterer.removeMarker(e)}f.fire("marker_removed",e,this);break}}return e};f.prototype.removeMarkers=function(e){var t=[];if(typeof e=="undefined"){for(var n=0;n<this.markers.length;n++){this.markers[n].setMap(null)}this.markers=t}else{for(var n=0;n<e.length;n++){if(this.markers.indexOf(e[n])>-1){this.markers[n].setMap(null)}}for(var n=0;n<this.markers.length;n++){if(this.markers[n].getMap()!=null){t.push(this.markers[n])}}this.markers=t}};f.prototype.drawOverlay=function(e){var t=new google.maps.OverlayView,n=true;t.setMap(this.map);if(e.auto_show!=null){n=e.auto_show}t.onAdd=function(){var n=document.createElement("div");n.style.borderStyle="none";n.style.borderWidth="0px";n.style.position="absolute";n.style.zIndex=100;n.innerHTML=e.content;t.el=n;if(!e.layer){e.layer="overlayLayer"}var r=this.getPanes(),i=r[e.layer],s=["contextmenu","DOMMouseScroll","dblclick","mousedown"];i.appendChild(n);for(var o=0;o<s.length;o++){(function(e,t){google.maps.event.addDomListener(e,t,function(e){if(navigator.userAgent.toLowerCase().indexOf("msie")!=-1&&document.all){e.cancelBubble=true;e.returnValue=false}else{e.stopPropagation()}})})(n,s[o])}if(e.click){r.overlayMouseTarget.appendChild(t.el);google.maps.event.addDomListener(t.el,"click",function(){e.click.apply(t,[t])})}google.maps.event.trigger(this,"ready")};t.draw=function(){var r=this.getProjection(),i=r.fromLatLngToDivPixel(new google.maps.LatLng(e.lat,e.lng));e.horizontalOffset=e.horizontalOffset||0;e.verticalOffset=e.verticalOffset||0;var s=t.el,o=s.children[0],u=o.clientHeight,a=o.clientWidth;switch(e.verticalAlign){case"top":s.style.top=i.y-u+e.verticalOffset+"px";break;default:case"middle":s.style.top=i.y-u/2+e.verticalOffset+"px";break;case"bottom":s.style.top=i.y+e.verticalOffset+"px";break}switch(e.horizontalAlign){case"left":s.style.left=i.x-a+e.horizontalOffset+"px";break;default:case"center":s.style.left=i.x-a/2+e.horizontalOffset+"px";break;case"right":s.style.left=i.x+e.horizontalOffset+"px";break}s.style.display=n?"block":"none";if(!n){e.show.apply(this,[s])}};t.onRemove=function(){var n=t.el;if(e.remove){e.remove.apply(this,[n])}else{t.el.parentNode.removeChild(t.el);t.el=null}};this.overlays.push(t);return t};f.prototype.removeOverlay=function(e){for(var t=0;t<this.overlays.length;t++){if(this.overlays[t]===e){this.overlays[t].setMap(null);this.overlays.splice(t,1);break}}};f.prototype.removeOverlays=function(){for(var e=0,t;t=this.overlays[e];e++){t.setMap(null)}this.overlays=[]};f.prototype.drawPolyline=function(e){var t=[],n=e.path;if(n.length){if(n[0][0]===undefined){t=n}else{for(var r=0,i;i=n[r];r++){t.push(new google.maps.LatLng(i[0],i[1]))}}}var s={map:this.map,path:t,strokeColor:e.strokeColor,strokeOpacity:e.strokeOpacity,strokeWeight:e.strokeWeight,geodesic:e.geodesic,clickable:true,editable:false,visible:true};if(e.hasOwnProperty("clickable")){s.clickable=e.clickable}if(e.hasOwnProperty("editable")){s.editable=e.editable}if(e.hasOwnProperty("icons")){s.icons=e.icons}if(e.hasOwnProperty("zIndex")){s.zIndex=e.zIndex}var o=new google.maps.Polyline(s);var u=["click","dblclick","mousedown","mousemove","mouseout","mouseover","mouseup","rightclick"];for(var a=0;a<u.length;a++){(function(t,n){if(e[n]){google.maps.event.addListener(t,n,function(t){e[n].apply(this,[t])})}})(o,u[a])}this.polylines.push(o);f.fire("polyline_added",o,this);return o};f.prototype.removePolyline=function(e){for(var t=0;t<this.polylines.length;t++){if(this.polylines[t]===e){this.polylines[t].setMap(null);this.polylines.splice(t,1);f.fire("polyline_removed",e,this);break}}};f.prototype.removePolylines=function(){for(var e=0,t;t=this.polylines[e];e++){t.setMap(null)}this.polylines=[]};f.prototype.drawCircle=function(e){e=t({map:this.map,center:new google.maps.LatLng(e.lat,e.lng)},e);delete e.lat;delete e.lng;var n=new google.maps.Circle(e),r=["click","dblclick","mousedown","mousemove","mouseout","mouseover","mouseup","rightclick"];for(var i=0;i<r.length;i++){(function(t,n){if(e[n]){google.maps.event.addListener(t,n,function(t){e[n].apply(this,[t])})}})(n,r[i])}this.polygons.push(n);return n};f.prototype.drawRectangle=function(e){e=t({map:this.map},e);var n=new google.maps.LatLngBounds(new google.maps.LatLng(e.bounds[0][0],e.bounds[0][1]),new google.maps.LatLng(e.bounds[1][0],e.bounds[1][1]));e.bounds=n;var r=new google.maps.Rectangle(e),i=["click","dblclick","mousedown","mousemove","mouseout","mouseover","mouseup","rightclick"];for(var s=0;s<i.length;s++){(function(t,n){if(e[n]){google.maps.event.addListener(t,n,function(t){e[n].apply(this,[t])})}})(r,i[s])}this.polygons.push(r);return r};f.prototype.drawPolygon=function(e){var n=false;if(e.hasOwnProperty("useGeoJSON")){n=e.useGeoJSON}delete e.useGeoJSON;e=t({map:this.map},e);if(n==false){e.paths=[e.paths.slice(0)]}if(e.paths.length>0){if(e.paths[0].length>0){e.paths=i(r(e.paths,o,n))}}var s=new google.maps.Polygon(e),u=["click","dblclick","mousedown","mousemove","mouseout","mouseover","mouseup","rightclick"];for(var a=0;a<u.length;a++){(function(t,n){if(e[n]){google.maps.event.addListener(t,n,function(t){e[n].apply(this,[t])})}})(s,u[a])}this.polygons.push(s);f.fire("polygon_added",s,this);return s};f.prototype.removePolygon=function(e){for(var t=0;t<this.polygons.length;t++){if(this.polygons[t]===e){this.polygons[t].setMap(null);this.polygons.splice(t,1);f.fire("polygon_removed",e,this);break}}};f.prototype.removePolygons=function(){for(var e=0,t;t=this.polygons[e];e++){t.setMap(null)}this.polygons=[]};f.prototype.getFromFusionTables=function(e){var t=e.events;delete e.events;var n=e,r=new google.maps.FusionTablesLayer(n);for(var i in t){(function(e,n){google.maps.event.addListener(e,n,function(e){t[n].apply(this,[e])})})(r,i)}this.layers.push(r);return r};f.prototype.loadFromFusionTables=function(e){var t=this.getFromFusionTables(e);t.setMap(this.map);return t};f.prototype.getFromKML=function(e){var t=e.url,n=e.events;delete e.url;delete e.events;var r=e,i=new google.maps.KmlLayer(t,r);for(var s in n){(function(e,t){google.maps.event.addListener(e,t,function(e){n[t].apply(this,[e])})})(i,s)}this.layers.push(i);return i};f.prototype.loadFromKML=function(e){var t=this.getFromKML(e);t.setMap(this.map);return t};f.prototype.addLayer=function(e,t){t=t||{};var n;switch(e){case"weather":this.singleLayers.weather=n=new google.maps.weather.WeatherLayer;break;case"clouds":this.singleLayers.clouds=n=new google.maps.weather.CloudLayer;break;case"traffic":this.singleLayers.traffic=n=new google.maps.TrafficLayer;break;case"transit":this.singleLayers.transit=n=new google.maps.TransitLayer;break;case"bicycling":this.singleLayers.bicycling=n=new google.maps.BicyclingLayer;break;case"panoramio":this.singleLayers.panoramio=n=new google.maps.panoramio.PanoramioLayer;n.setTag(t.filter);delete t.filter;if(t.click){google.maps.event.addListener(n,"click",function(e){t.click(e);delete t.click})}break;case"places":this.singleLayers.places=n=new google.maps.places.PlacesService(this.map);if(t.search||t.nearbySearch||t.radarSearch){var r={bounds:t.bounds||null,keyword:t.keyword||null,location:t.location||null,name:t.name||null,radius:t.radius||null,rankBy:t.rankBy||null,types:t.types||null};if(t.radarSearch){n.radarSearch(r,t.radarSearch)}if(t.search){n.search(r,t.search)}if(t.nearbySearch){n.nearbySearch(r,t.nearbySearch)}}if(t.textSearch){var i={bounds:t.bounds||null,location:t.location||null,query:t.query||null,radius:t.radius||null};n.textSearch(i,t.textSearch)}break}if(n!==undefined){if(typeof n.setOptions=="function"){n.setOptions(t)}if(typeof n.setMap=="function"){n.setMap(this.map)}return n}};f.prototype.removeLayer=function(e){if(typeof e=="string"&&this.singleLayers[e]!==undefined){this.singleLayers[e].setMap(null);delete this.singleLayers[e]}else{for(var t=0;t<this.layers.length;t++){if(this.layers[t]===e){this.layers[t].setMap(null);this.layers.splice(t,1);break}}}};var l,c;f.prototype.getRoutes=function(e){switch(e.travelMode){case"bicycling":l=google.maps.TravelMode.BICYCLING;break;case"transit":l=google.maps.TravelMode.TRANSIT;break;case"driving":l=google.maps.TravelMode.DRIVING;break;default:l=google.maps.TravelMode.WALKING;break}if(e.unitSystem==="imperial"){c=google.maps.UnitSystem.IMPERIAL}else{c=google.maps.UnitSystem.METRIC}var n={avoidHighways:false,avoidTolls:false,optimizeWaypoints:false,waypoints:[]},r=t(n,e);r.origin=/string/.test(typeof e.origin)?e.origin:new google.maps.LatLng(e.origin[0],e.origin[1]);r.destination=/string/.test(typeof e.destination)?e.destination:new google.maps.LatLng(e.destination[0],e.destination[1]);r.travelMode=l;r.unitSystem=c;delete r.callback;delete r.error;var i=this,s=new google.maps.DirectionsService;s.route(r,function(t,n){if(n===google.maps.DirectionsStatus.OK){for(var r in t.routes){if(t.routes.hasOwnProperty(r)){i.routes.push(t.routes[r])}}if(e.callback){e.callback(i.routes)}}else{if(e.error){e.error(t,n)}}})};f.prototype.removeRoutes=function(){this.routes=[]};f.prototype.getElevations=function(e){e=t({locations:[],path:false,samples:256},e);if(e.locations.length>0){if(e.locations[0].length>0){e.locations=i(r([e.locations],o,false))}}var n=e.callback;delete e.callback;var s=new google.maps.ElevationService;if(!e.path){delete e.path;delete e.samples;s.getElevationForLocations(e,function(e,t){if(n&&typeof n==="function"){n(e,t)}})}else{var u={path:e.locations,samples:e.samples};s.getElevationAlongPath(u,function(e,t){if(n&&typeof n==="function"){n(e,t)}})}};f.prototype.cleanRoute=f.prototype.removePolylines;f.prototype.drawRoute=function(e){var t=this;this.getRoutes({origin:e.origin,destination:e.destination,travelMode:e.travelMode,waypoints:e.waypoints,unitSystem:e.unitSystem,error:e.error,callback:function(n){if(n.length>0){t.drawPolyline({path:n[n.length-1].overview_path,strokeColor:e.strokeColor,strokeOpacity:e.strokeOpacity,strokeWeight:e.strokeWeight});if(e.callback){e.callback(n[n.length-1])}}}})};f.prototype.travelRoute=function(e){if(e.origin&&e.destination){this.getRoutes({origin:e.origin,destination:e.destination,travelMode:e.travelMode,waypoints:e.waypoints,unitSystem:e.unitSystem,error:e.error,callback:function(t){if(t.length>0&&e.start){e.start(t[t.length-1])}if(t.length>0&&e.step){var n=t[t.length-1];if(n.legs.length>0){var r=n.legs[0].steps;for(var i=0,s;s=r[i];i++){s.step_number=i;e.step(s,n.legs[0].steps.length-1)}}}if(t.length>0&&e.end){e.end(t[t.length-1])}}})}else if(e.route){if(e.route.legs.length>0){var t=e.route.legs[0].steps;for(var n=0,r;r=t[n];n++){r.step_number=n;e.step(r)}}}};f.prototype.drawSteppedRoute=function(e){var t=this;if(e.origin&&e.destination){this.getRoutes({origin:e.origin,destination:e.destination,travelMode:e.travelMode,waypoints:e.waypoints,error:e.error,callback:function(n){if(n.length>0&&e.start){e.start(n[n.length-1])}if(n.length>0&&e.step){var r=n[n.length-1];if(r.legs.length>0){var i=r.legs[0].steps;for(var s=0,o;o=i[s];s++){o.step_number=s;t.drawPolyline({path:o.path,strokeColor:e.strokeColor,strokeOpacity:e.strokeOpacity,strokeWeight:e.strokeWeight});e.step(o,r.legs[0].steps.length-1)}}}if(n.length>0&&e.end){e.end(n[n.length-1])}}})}else if(e.route){if(e.route.legs.length>0){var n=e.route.legs[0].steps;for(var r=0,i;i=n[r];r++){i.step_number=r;t.drawPolyline({path:i.path,strokeColor:e.strokeColor,strokeOpacity:e.strokeOpacity,strokeWeight:e.strokeWeight});e.step(i)}}}};f.Route=function(e){this.origin=e.origin;this.destination=e.destination;this.waypoints=e.waypoints;this.map=e.map;this.route=e.route;this.step_count=0;this.steps=this.route.legs[0].steps;this.steps_length=this.steps.length;this.polyline=this.map.drawPolyline({path:new google.maps.MVCArray,strokeColor:e.strokeColor,strokeOpacity:e.strokeOpacity,strokeWeight:e.strokeWeight}).getPath()};f.Route.prototype.getRoute=function(t){var n=this;this.map.getRoutes({origin:this.origin,destination:this.destination,travelMode:t.travelMode,waypoints:this.waypoints||[],error:t.error,callback:function(){n.route=e[0];if(t.callback){t.callback.call(n)}}})};f.Route.prototype.back=function(){if(this.step_count>0){this.step_count--;var e=this.route.legs[0].steps[this.step_count].path;for(var t in e){if(e.hasOwnProperty(t)){this.polyline.pop()}}}};f.Route.prototype.forward=function(){if(this.step_count<this.steps_length){var e=this.route.legs[0].steps[this.step_count].path;for(var t in e){if(e.hasOwnProperty(t)){this.polyline.push(e[t])}}this.step_count++}};f.prototype.checkGeofence=function(e,t,n){return n.containsLatLng(new google.maps.LatLng(e,t))};f.prototype.checkMarkerGeofence=function(e,t){if(e.fences){for(var n=0,r;r=e.fences[n];n++){var i=e.getPosition();if(!this.checkGeofence(i.lat(),i.lng(),r)){t(e,r)}}}};f.prototype.toImage=function(e){var e=e||{},t={};t["size"]=e["size"]||[this.el.clientWidth,this.el.clientHeight];t["lat"]=this.getCenter().lat();t["lng"]=this.getCenter().lng();if(this.markers.length>0){t["markers"]=[];for(var n=0;n<this.markers.length;n++){t["markers"].push({lat:this.markers[n].getPosition().lat(),lng:this.markers[n].getPosition().lng()})}}if(this.polylines.length>0){var r=this.polylines[0];t["polyline"]={};t["polyline"]["path"]=google.maps.geometry.encoding.encodePath(r.getPath());t["polyline"]["strokeColor"]=r.strokeColor;t["polyline"]["strokeOpacity"]=r.strokeOpacity;t["polyline"]["strokeWeight"]=r.strokeWeight}return f.staticMapURL(t)};f.staticMapURL=function(e){function b(e,t){if(e[0]==="#"){e=e.replace("#","0x");if(t){t=parseFloat(t);t=Math.min(1,Math.max(t,0));if(t===0){return"0x00000000"}t=(t*255).toString(16);if(t.length===1){t+=t}e=e.slice(0,8)+t}}return e}var t=[],n,r="http://maps.googleapis.com/maps/api/staticmap";if(e.url){r=e.url;delete e.url}r+="?";var i=e.markers;delete e.markers;if(!i&&e.marker){i=[e.marker];delete e.marker}var s=e.styles;delete e.styles;var o=e.polyline;delete e.polyline;if(e.center){t.push("center="+e.center);delete e.center}else if(e.address){t.push("center="+e.address);delete e.address}else if(e.lat){t.push(["center=",e.lat,",",e.lng].join(""));delete e.lat;delete e.lng}else if(e.visible){var u=encodeURI(e.visible.join("|"));t.push("visible="+u)}var a=e.size;if(a){if(a.join){a=a.join("x")}delete e.size}else{a="630x300"}t.push("size="+a);if(!e.zoom&&e.zoom!==false){e.zoom=15}var f=e.hasOwnProperty("sensor")?!!e.sensor:true;delete e.sensor;t.push("sensor="+f);for(var l in e){if(e.hasOwnProperty(l)){t.push(l+"="+e[l])}}if(i){var c,h;for(var p=0;n=i[p];p++){c=[];if(n.size&&n.size!=="normal"){c.push("size:"+n.size);delete n.size}else if(n.icon){c.push("icon:"+encodeURI(n.icon));delete n.icon}if(n.color){c.push("color:"+n.color.replace("#","0x"));delete n.color}if(n.label){c.push("label:"+n.label[0].toUpperCase());delete n.label}h=n.address?n.address:n.lat+","+n.lng;delete n.address;delete n.lat;delete n.lng;for(var l in n){if(n.hasOwnProperty(l)){c.push(l+":"+n[l])}}if(c.length||p===0){c.push(h);c=c.join("|");t.push("markers="+encodeURI(c))}else{c=t.pop()+encodeURI("|"+h);t.push(c)}}}if(s){for(var p=0;p<s.length;p++){var d=[];if(s[p].featureType){d.push("feature:"+s[p].featureType.toLowerCase())}if(s[p].elementType){d.push("element:"+s[p].elementType.toLowerCase())}for(var v=0;v<s[p].stylers.length;v++){for(var m in s[p].stylers[v]){var g=s[p].stylers[v][m];if(m=="hue"||m=="color"){g="0x"+g.substring(1)}d.push(m+":"+g)}}var y=d.join("|");if(y!=""){t.push("style="+y)}}}if(o){n=o;o=[];if(n.strokeWeight){o.push("weight:"+parseInt(n.strokeWeight,10))}if(n.strokeColor){var w=b(n.strokeColor,n.strokeOpacity);o.push("color:"+w)}if(n.fillColor){var E=b(n.fillColor,n.fillOpacity);o.push("fillcolor:"+E)}var S=n.path;if(S.join){for(var v=0,x;x=S[v];v++){o.push(x.join(","))}}else{o.push("enc:"+S)}o=o.join("|");t.push("path="+encodeURI(o))}var T=window.devicePixelRatio||1;t.push("scale="+T);t=t.join("&");return r+t};f.prototype.addMapType=function(e,t){if(t.hasOwnProperty("getTileUrl")&&typeof t["getTileUrl"]=="function"){t.tileSize=t.tileSize||new google.maps.Size(256,256);var n=new google.maps.ImageMapType(t);this.map.mapTypes.set(e,n)}else{throw"'getTileUrl' function required."}};f.prototype.addOverlayMapType=function(e){if(e.hasOwnProperty("getTile")&&typeof e["getTile"]=="function"){var t=e.index;delete e.index;this.map.overlayMapTypes.insertAt(t,e)}else{throw"'getTile' function required."}};f.prototype.removeOverlayMapType=function(e){this.map.overlayMapTypes.removeAt(e)};f.prototype.addStyle=function(e){var t=new google.maps.StyledMapType(e.styles,{name:e.styledMapName});this.map.mapTypes.set(e.mapTypeId,t)};f.prototype.setStyle=function(e){this.map.setMapTypeId(e)};f.prototype.createPanorama=function(e){if(!e.hasOwnProperty("lat")||!e.hasOwnProperty("lng")){e.lat=this.getCenter().lat();e.lng=this.getCenter().lng()}this.panorama=f.createPanorama(e);this.map.setStreetView(this.panorama);return this.panorama};f.createPanorama=function(e){var n=u(e.el,e.context);e.position=new google.maps.LatLng(e.lat,e.lng);delete e.el;delete e.context;delete e.lat;delete e.lng;var r=["closeclick","links_changed","pano_changed","position_changed","pov_changed","resize","visible_changed"],i=t({visible:true},e);for(var s=0;s<r.length;s++){delete i[r[s]]}var o=new google.maps.StreetViewPanorama(n,i);for(var s=0;s<r.length;s++){(function(t,n){if(e[n]){google.maps.event.addListener(t,n,function(){e[n].apply(this)})}})(o,r[s])}return o};f.prototype.on=function(e,t){return f.on(e,this,t)};f.prototype.off=function(e){f.off(e,this)};f.custom_events=["marker_added","marker_removed","polyline_added","polyline_removed","polygon_added","polygon_removed","geolocated","geolocation_failed"];f.on=function(e,t,n){if(f.custom_events.indexOf(e)==-1){if(t instanceof f)t=t.map;return google.maps.event.addListener(t,e,n)}else{var r={handler:n,eventName:e};t.registered_events[e]=t.registered_events[e]||[];t.registered_events[e].push(r);return r}};f.off=function(e,t){if(f.custom_events.indexOf(e)==-1){if(t instanceof f)t=t.map;google.maps.event.clearListeners(t,e)}else{t.registered_events[e]=[]}};f.fire=function(e,t,n){if(f.custom_events.indexOf(e)==-1){google.maps.event.trigger(t,e,Array.prototype.slice.apply(arguments).slice(2))}else{if(e in n.registered_events){var r=n.registered_events[e];for(var i=0;i<r.length;i++){(function(e,t,n){e.apply(t,[n])})(r[i]["handler"],n,t)}}}};f.geolocate=function(e){var t=e.always||e.complete;if(navigator.geolocation){navigator.geolocation.getCurrentPosition(function(n){e.success(n);if(t){t()}},function(n){e.error(n);if(t){t()}},e.options)}else{e.not_supported();if(t){t()}}};f.geocode=function(e){this.geocoder=new google.maps.Geocoder;var t=e.callback;if(e.hasOwnProperty("lat")&&e.hasOwnProperty("lng")){e.latLng=new google.maps.LatLng(e.lat,e.lng)}delete e.lat;delete e.lng;delete e.callback;this.geocoder.geocode(e,function(e,n){t(e,n)})};if(!google.maps.Polygon.prototype.getBounds){google.maps.Polygon.prototype.getBounds=function(e){var t=new google.maps.LatLngBounds;var n=this.getPaths();var r;for(var i=0;i<n.getLength();i++){r=n.getAt(i);for(var s=0;s<r.getLength();s++){t.extend(r.getAt(s))}}return t}}if(!google.maps.Polygon.prototype.containsLatLng){google.maps.Polygon.prototype.containsLatLng=function(e){var t=this.getBounds();if(t!==null&&!t.contains(e)){return false}var n=false;var r=this.getPaths().getLength();for(var i=0;i<r;i++){var s=this.getPaths().getAt(i);var o=s.getLength();var u=o-1;for(var a=0;a<o;a++){var f=s.getAt(a);var l=s.getAt(u);if(f.lng()<e.lng()&&l.lng()>=e.lng()||l.lng()<e.lng()&&f.lng()>=e.lng()){if(f.lat()+(e.lng()-f.lng())/(l.lng()-f.lng())*(l.lat()-f.lat())<e.lat()){n=!n}}u=a}}return n}}if(!google.maps.Circle.prototype.containsLatLng){google.maps.Circle.prototype.containsLatLng=function(e){if(google.maps.geometry){return google.maps.geometry.spherical.computeDistanceBetween(this.getCenter(),e)<=this.getRadius()}else{return true}}}google.maps.LatLngBounds.prototype.containsLatLng=function(e){return this.contains(e)};google.maps.Marker.prototype.setFences=function(e){this.fences=e};google.maps.Marker.prototype.addFence=function(e){this.fences.push(e)};google.maps.Marker.prototype.getId=function(){return this["__gm_id"]};if(!Array.prototype.indexOf){Array.prototype.indexOf=function(e){"use strict";if(this==null){throw new TypeError}var t=Object(this);var n=t.length>>>0;if(n===0){return-1}var r=0;if(arguments.length>1){r=Number(arguments[1]);if(r!=r){r=0}else if(r!=0&&r!=Infinity&&r!=-Infinity){r=(r>0||-1)*Math.floor(Math.abs(r))}}if(r>=n){return-1}var i=r>=0?r:Math.max(n-Math.abs(r),0);for(;i<n;i++){if(i in t&&t[i]===e){return i}}return-1}}return f})