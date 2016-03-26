/**
 * Created by tiramakan on 12/03/2016.
 */
// Major feature activation
$.pjax({
    // Multiple area update
    // Fallback area matching
    area: [
        '#header, #primary, #secondary',
        '#container',
        'body'
    ],
    // Sync and load
    load: {
        head: 'base, meta, link',
        css: true,
        script: true
    },
    // On memory cache
    cache: {
        click: true, submit: true, popstate: true,
        get: true, post: false
    },
    // Rewrite source document without rendering
    rewrite: function(document, area) {
        $(area, document).find('img').each(function(){
            this.setAttribute('data-original', this.src);
            this.setAttribute('src', '/img/gray.gif');
        }).addClass('delay');
    },
    // Override setting
    // Enabling control
    scope: {
        search: ['/search/'],
        $search: { form: 'form:not([method])' },
        '/': ['/', '#search', '!/contact/']
    }
});

// 6 events and 30 callbacks exists.
$(document).bind('pjax:ready', function() {
    //$("img.delay").lazyload();
});
