function hideSpan(e){$("#"+e+"_span").css("display","none");$("#"+e+"_input").css("display","inline");$("#"+e+"_input").focus()}function hideInput(e){$("#"+e+"_input").css("display","none");$("#"+e+"_span").html($("#"+e+"_input").val()||"...");$("#"+e+"_span").css("display","inline")}function hideSpanSelectSingle(e){$("#"+e+"_span").css("display","none");$("#"+e+"_input").css("display","inline");$("#"+e+"_input").focus()}function hideInputSelectSingle(e){$("#"+e+"_input").css("display","none");$("#"+e+"_span").html($("#"+e+"_input option:selected").text()||"...");$("#"+e+"_span").css("display","inline")}function hideInputSelectMultiple(e){$("#"+e+"_input").css("display","none");if($("#"+e+"_input option:selected").length>0){$("#"+e+"_span").html("");$("#"+e+"_span").append("<ul>");$.each($("#"+e+"_input option:selected"),function(t,n){$("#"+e+"_span ul").append("<li>"+n.textContent+"</li>")});$("#"+e+"_span").append("</ul>")}$("#"+e+"_span").css("display","inline")}function changeCheckBoxSpan(e,t,n){var r;if($("#"+e+"_input").is(":checked")){r=n;$("#"+e+"_span").removeClass("editableCheckBoxChecked").addClass("editableCheckBoxUnchecked")}else{r=t;$("#"+e+"_span").removeClass("editableCheckBoxUnchecked").addClass("editableCheckBoxChecked")}$("#"+e+"_input").trigger("click");$("#"+e+"_span").html(r)}function colorConf(e,t){var n=e.css("color");e.animate({color:t||"green"},100,function(){e.animate({color:n},2e3)})}