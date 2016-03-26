package simagritaglibs

class MyFlashShowerTagLib {
    static namespace = 'myTags'
    def showFlash = { attrs ->
        String message = flash.remove('message')
        String error = flash.remove('error')
        if (!message && !error) {
            return
        }

        String type = message ? 'info' : 'error'
        String text = message ?: error
        out << """
		<script>
		\$(document).ready(function() {
			SpringSecurityUI.message('${type}', "${text.encodeAsHTML()}", 3000);
		});
		</script>
		"""
    }
}
