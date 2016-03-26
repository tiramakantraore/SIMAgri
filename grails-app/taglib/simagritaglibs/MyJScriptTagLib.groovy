package simagritaglibs

class MyJScriptTagLib {
    static namespace = 'myScript'

    def script = { attrs ->
        def link = g.createLink(
                controller: 'scripts',
                action: attrs.name ?: controllerName)
        out << """<script type="text/javascript" src="${link}.js"></script>"""
    }
}
