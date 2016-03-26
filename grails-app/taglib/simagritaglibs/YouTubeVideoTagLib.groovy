package simagritaglibs

/**
 * Created with IntelliJ IDEA.
 * User: Tiramakan
 * Date: 10/03/13
 * Time: 10:59
 * To change this template use File | Settings | File Templates.
 *  <source type="video/youtube" src="http://www.youtube-nocookie.com/v/${videoKey}?fs=1&amp;amp;hl=en_US;showinfo=0;rel=0;theme=light" />
 *  <source type="video/youtube" src="http://www.youtube-nocookie.com/v/${videoKey}\" />
 */


class YouTubeVideoTagLib {
    def video={attrs->
        def videoKey = attrs['videoKey']
        def width=attrs['width']?:"640"
        def height=attrs['height']?:"385"
        out << """
        <video width=$width height=$height  preload=\"none\"  '>
        <source type="video/youtube" src=\"http://www.youtube.com/watch?v=$videoKey\" />
        </video>
        """

    }
}
//, class="embed-responsive-item"