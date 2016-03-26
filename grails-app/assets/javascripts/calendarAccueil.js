function renderCalendar(selectorName) {
    $("#calendarAccueil").fullCalendar({
        events: 'event/list',
        cache: true,
        type: 'POST',
        aspectRatio: 1,
        lang: 'fr',
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },
        error: function(e) {
            alert('erreur pendant le chargement des evenements '+e);
        },
        color: 'yellow',   // a non-ajax option
        textColor: 'black', // a non-ajax option,
        eventRender: function(event, element) {
            $(element).addClass(event.cssClass);

            var occurrenceStart = event.occurrenceStart;
            var occurrenceEnd = event.occurrenceEnd;

            var data = {id: event.id, occurrenceStart: occurrenceStart, occurrenceEnd: occurrenceEnd};

            $(element).qtip({
                content: {
                    text: ' ',
                    ajax: {
                        url: "event/show",
                        type: "GET",
                        data: data
                    }
                },
                show: {
                    event: 'click',
                    solo: true
                },
                hide: {
                    event: 'click'
                },
                style: {
                    width: '500px',
                    widget: true
                },
                position: {
                    my: 'bottom middle',
                    at: 'top middle',
                    viewport: true
                }
            });
        },
        eventMouseover: function(event, jsEvent, view) {
            $(this).addClass("active");
        },
        eventMouseout: function(event, jsEvent, view) {
            $(this).removeClass("active");
        }
    });
}