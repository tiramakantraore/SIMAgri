%{--<%@page defaultCodec="none" %>--}%
<div id="count_${id}" style="display: none;">0</div>
<div id="parent_${id}"></div>
<g:if test="${!addBtnId}">
	<input id="add_${id}" type="button" value="Add"/>
</g:if>


<asset:script type='text/javascript'>
	function initializeTag(addButton, id, elem, min, max, onComplete, limitReachedMsg, removeBtnLabel,removeOffset,removeBtnId,tabprefix) {
		// bind event handler to the "click" JS event of the "Add" button
		addButton.click(function() {
			addItem(id, elem, min, max, onComplete, limitReachedMsg, removeBtnLabel,removeOffset,removeBtnId,tabprefix);
		});

		// add the initial number of items
		for (var i = 0; i < min; i++) {
			addButton.click();
		}
	}

	$(function () {
		var addButton = ${raw(addBtnId ? "\$('#$addBtnId')" : "\$('#add_$id')")};
			initializeTag(addButton, '${id}', '${elem}', ${min}, ${max}, '${onComplete}', '${limitReachedMsg}', '${removeBtnLabel}','${removeOffset}','${removeBtnId}','${tabprefix}');

	});
</asset:script>
