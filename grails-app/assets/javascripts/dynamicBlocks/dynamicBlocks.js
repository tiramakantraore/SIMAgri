/*
 * Adds a new item.
 */
function addItem(id, elem, min, max, onComplete, limitMessage, removeBtnLabel,removeOffset,removeBtnId,tabprefix) {
	// checks if we have reached maximum number of elements

	if (!max || $('[id^=' + id + ']').length < max) {
		// increments the item counter
		var $countElem = $('#count_' + id);

		var num = parseInt($countElem.html()) + 1;
        var numForName = parseInt($countElem.html()) ;
		$countElem.html(num);
		// creates new item and adds the index number to it
		var $newElem = $('<div></div>').html(elem).attr({'id' : id + num,'class':'row'});
        var $btnContainer = $('<div></div>').attr({'class' :'col-xs-2 col-xs-offset'+removeOffset}).appendTo($newElem);
        var $removeButton;
        var $tabprefix=tabprefix;
		// creates the "Remove" button
        if (removeBtnId=="-1") {
            $removeButton = $('<input type="button"/>').appendTo($btnContainer);
        }else {
            $removeButton = $('#'+removeBtnId);
        }
		$removeButton.attr({'id' : 'remove_' + id, 'value' : removeBtnLabel ? removeBtnLabel : 'Remove', 'disabled' : 'disabled'});
		// binds handler to the 'click' JS event of the "Remove" button
		$removeButton.click(function() {
			removeItem(id, num, min);
		});

		// changes IDs of all elements inside new item
		indexItem($newElem, num);
        indexItemName($newElem, numForName,$tabprefix);
		indexCheckableInputs($newElem, num);
        // changes Caption of of all label inside new item
        indexLabel($newElem, num);
		// appends new item to the parent element
		$('#parent_' + id).append($newElem);
		// enables "Remove" buttons if there are more than minimum number of elements on the page
		if ($('[id^=' + id + ']').length > min) {
			$('[id^=remove_' + id + ']').removeAttr('disabled');
		}
		// executes the 'onComplete' JS function if it exists
		if (window[onComplete] instanceof Function) {
			window[onComplete](num);
		}
	} else {
		// displays a message if the maximum limit is reached
		alert(limitMessage ? limitMessage : 'You cannot add more elements.');
	}
}

/*
 * Removes an item.
 */
function removeItem(id, num, min) {
	$('#' + id + num).remove();
	if ($('[id^=' + id + ']').length <= min) {
		$('[id^=remove_' + id + ']').attr('disabled', 'disabled');
	}
}

/*
 * Changes ID of every item's child by adding index number to it.
 */
function indexItem($elem, num) {
    $elem.children().each(function() {
        var nodeId = $(this).attr('id');
        if (nodeId) {
            $(this).attr('id', nodeId + num);
        }
        indexItem($(this), num);
    });
}
/*
 * Changes NAME of every item's child by adding index number to it.
 */
function indexItemName($elem, num,$tabprefix) {

    $elem.children().each(function() {
        var nodeId = $(this).attr('name');
        if (nodeId) {
            $(this).attr('name', $tabprefix+'_'+num+'.'+nodeId);
        }
        indexItemName($(this), num,$tabprefix);
    });
}
/*
 * Changes Label of every item's child by adding index number to it.
 */
function indexLabel($elem, num) {
    $elem.children().each(function() {
        if ( $(this).is( "label" ) ) {
            $(this).html( $(this).html()+" "+num);
        }
//        var nodeId = $(this).attr('id');
//        if (nodeId) {
//            $(this).attr('id', nodeId + num);
//        }
        indexLabel($(this), num);
    });
}
/*
 * Changes names of radio inputs by adding the index number to it.
 */
function indexCheckableInputs($elem, num) {
	$elem.find('input[type=radio]').each(function() {
		$(this).attr('name', $(this).attr('name') + num);
	});
	$elem.find('input[type=checkbox]').each(function() {
		$(this).prev().attr('name', $(this).prev().attr('name') + '.' + num);
		$(this).attr('name', $(this).attr('name') + '.' + num);
	});
}