$(document).ready(function() {
	jQuery("#toggle').change(function() {
        $('td > input[type=checkbox]').prop('checked', $(this).prop('checked'))
    })
})

