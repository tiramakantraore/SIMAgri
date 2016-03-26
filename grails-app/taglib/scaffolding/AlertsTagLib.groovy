package scaffolding

class AlertsTagLib {

	static namespace = "bootstrap"

	def alert = { attrs, body ->
		out << '<div class="alert alert-warning ' << attrs?.class?.tokenize()?.join(" ") << '">'
		out << '<p>' << body() << '</p>'
		out << '</div>'

	}

}
