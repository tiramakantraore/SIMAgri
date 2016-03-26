<script type="text/javascript">

   var sondageChart;


   function cacher_selecteur(selecteur){
       selecteur.html("Il n y'a pas de données pour cette requête").addClass('errorClass');
    //   pas_de_donnees();
   }
   function montrer_selecteur(selecteur){
       selecteur.css("display", "inline-block").removeClass('errorClass');
   }


    $(document).ready(function(){

        openMenuOnHover();
        jQuery('#myCarousel').carousel('cycle');
        jQuery('.carousel').carousel({
            interval: 8000
        });
        $.mask.definitions['~']='[+]';
        $('#username').mask("~99999999999");
//        var player = new MediaElementPlayer('#player1');
        $('video').mediaelementplayer({
            // if the <video width> is not specified, this is the default
            defaultVideoWidth: 480,
            // if the <video height> is not specified, this is the default
            defaultVideoHeight: 270,
            // if set, overrides <video width>
            videoWidth: -1,
            // if set, overrides <video height>
            videoHeight: -1,
            // width of audio player
            audioWidth: 400,
            // height of audio player
            audioHeight: 30,
            // initial volume when the player starts
            startVolume: 0.8,
            // useful for <audio> player loops
            loop: false,
            // enables Flash and Silverlight to resize to content size
            enableAutosize: true,
            // the order of controls you want on the control bar (and other plugins below)
            features: ['playpause','progress','current','duration','tracks','volume','fullscreen'],
            // Hide controls when playing and mouse is not over the video
            alwaysShowControls: true,
            // force iPad's native controls
            iPadUseNativeControls: false,
            // force iPhone's native controls
            iPhoneUseNativeControls: false,
            // force Android's native controls
            AndroidUseNativeControls: false,
            // forces the hour marker (##:00:00)
            alwaysShowHours: false,
            // show framecount in timecode (##:00:00:00)
            showTimecodeFrameCount: false,
            // used when showTimecodeFrameCount is set to true
            framesPerSecond: 25,
            // turns keyboard support on and off for this instance
            enableKeyboard: true,
            // when this player starts, it will pause other players
            pauseOtherPlayers: true,
            // array of keyboard commands
            keyActions: []

        });

    })


</script>

%{--<div id="fb-root"></div>--}%
%{--<script>(function(d, s, id) {--}%
    %{--var js, fjs = d.getElementsByTagName(s)[0];--}%
    %{--if (d.getElementById(id)) return;--}%
    %{--js = d.createElement(s); js.id = id;--}%
    %{--js.src = "//connect.facebook.net/fr_FR/sdk.js#xfbml=1&appId=462823223805657&version=v2.0";--}%
    %{--fjs.parentNode.insertBefore(js, fjs);--}%
%{--}(document, 'script', 'facebook-jssdk'));</script>--}%
%{--<script type="text/javascript">--}%
    %{--window.twttr = (function (d, s, id) {--}%
        %{--var t, js, fjs = d.getElementsByTagName(s)[0];--}%
        %{--if (d.getElementById(id)) return;--}%
        %{--js = d.createElement(s); js.id = id;--}%
        %{--js.src= "https://platform.twitter.com/widgets.js";--}%
        %{--fjs.parentNode.insertBefore(js, fjs);--}%
        %{--return window.twttr || (t = { _e: [], ready: function (f) { t._e.push(f) } });--}%
    %{--}(document, "script", "twitter-wjs"));--}%
%{--</script>--}%