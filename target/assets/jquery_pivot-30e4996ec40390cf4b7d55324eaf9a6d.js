(function(e){"use strict";var t,n={},r,i,s,o;var u={setup:function(e){t=this;if(e.callbacks)n=e.callbacks;if(e.resultsDivID){s=e.resultsDivID}else{s="results"}if(e.pivotTableId){i=e.pivotTableId}else{i="pivot-table"}if(e.resultRowsId){o=e.resultRowsId}else{o="result-rows"}if(e.url!==undefined)u.process_from_url(e);else u.process(e)},process:function(t){if(n.beforePopulate){n.beforePopulate()}var i=u;pivot.init(t);r=t.resultsTitle;if(t.skipBuildContainers===undefined||t.skipBuildContainers===false)i.build_containers();i.populate_containers();e(document).on("change",".row-labelable",function(e){i.update_label_fields("row")});e(document).on("change",".column-labelable",function(e){i.update_label_fields("column")});e(document).on("change",".summary",function(e){i.update_summary_fields()});u.update_results();if(n.afterPopulate){n.afterPopulate()}},process_from_url:function(t){var n=/\.json$/i,r;if(n.test(t.url))r="text/json";else r="text/csv";e.ajax({url:t.url,dataType:"text",accepts:"text/csv",success:function(e,n){if(r==="text/json")t["json"]=e;else t["csv"]=e;u.process(t)}})},populate_containers:function(){u.build_toggle_fields("#row-label-fields",pivot.fields().rowLabelable,"row-labelable");u.build_toggle_fields("#column-label-fields",pivot.fields().columnLabelable,"column-labelable");u.build_toggle_fields("#summary-fields",pivot.fields().summarizable,"summary");u.build_filter_list()},reprocess_display:function(e){if(e.rowLabels===undefined)e.rowLabels=[];if(e.columnLabels===undefined)e.columnLabels=[];if(e.summaries===undefined)e.summaries=[];if(e.filters===undefined)e.filters={};if(e.callbacks===undefined)e.callbacks={};if(e.callbacks.beforeReprocessDisplay){e.callbacks.afterReprocessDisplay()}pivot.filters().set(e.filters);pivot.display().summaries().set(e.summaries);pivot.display().rowLabels().set(e.rowLabels);pivot.display().columnLabels().set(e.columnLabels);u.populate_containers();u.update_results();if(e.callbacks.afterReprocessDisplay){e.callbacks.afterReprocessDisplay()}},build_containers:function(){var n='<div class="pivot_header_fields">'+'  <div class="pivot_field">'+'  <span class="pivot_header2">Filter Fields</span>'+'   <div id="filter-list"></div>'+"  </div>"+'  <div class="pivot_field">'+'  <span class="pivot_header2">Row Label Fields</span>'+'   <div id="row-label-fields"></div>'+"  </div>"+'  <div class="pivot_field">'+'  <span class="pivot_header2">Column Label Fields</span>'+'   <div id="column-label-fields"></div>'+"  </div>"+'  <div class="pivot_field">'+'  <span class="pivot_header2">Summary Fields</span>'+'   <div id="summary-fields"></div>'+"  </div>"+"</div>";e(t).append(n)},build_filter_list:function(){var t='<select id="select-constructor">';t+="<option></option>";e.each(pivot.fields().filterable,function(e,n){t+="<option>"+n.name+"</option>"});t+="</select>";e("#filter-list").empty().append(t);e.each(pivot.filters().all(),function(e,t){u.build_filter_field(e,t)});e("#select-constructor").change(function(){u.build_filter_field(e(this).val())})},build_filter_field:function(t,n){var r,i,s=pivot.fields().get(t);if(t==="")return;var o=e("#filter-list").find('select[data-field="'+s.name+'"]');if(o.length)return;if(s.filterType==="regexp")r=u.build_regexp_filter_field(s,n);else r=u.build_select_filter_field(s,n);i='<a class="remove-filter-field" style="cursor:pointer;">(X)</a></label>';e("#filter-list").append("<div><hr/><label>"+s.name+i+r+"</div>");if(e.fn.chosen!==undefined)e("select.filter").chosen();else if(e.fn.select2!==undefined)e("select.filter").select2();e("select.filter").on("change",function(e){u.update_filtered_rows()});e("input[type=text].filter").on("keyup",function(t){var n=this,r=e(n).val();setTimeout(function(){if(e(n).val()===r)u.update_filtered_rows()},500)});e(".remove-filter-field").click(function(){e(this).parents("div").first().remove();u.update_filtered_rows()})},build_select_filter_field:function(e,t){var n='<select class="filter col-sm-3 col-md-3" '+(e.filterType==="multiselect"?"multiple":"")+' data-field="'+e.name+'">'+"<option></option>",r=[];for(var i in e.values){r.push(i)}r=r.sort();jQuery.each(r,function(r,i){var s="";if(i===t)s='selected="selected"';n+='<option value="'+i+'" '+s+">"+e.values[i].displayValue+"</option>"});n+="</select>";return n},build_regexp_filter_field:function(e,t){if(t===undefined)t="";return'<input type="text" class="filter col-sm-3 col-md-3" data-field="'+e.name+'" value="'+t+'">'},update_filtered_rows:function(){var t={},n;e(".filter").each(function(r){n=pivot.fields().get(e(this).attr("data-field"));if(n){if(e(this).val()!==null&&e(this).val()[0]!==""){if(n.filterType==="regexp")t[e(this).attr("data-field")]=new RegExp(e(this).val(),"i");else t[e(this).attr("data-field")]=e(this).val()}}});pivot.filters().set(t);u.update_results()},build_toggle_fields:function(t,n,r){e(t).empty();e.each(n,function(n,i){e(t).append('<label class="checkbox">'+'<input type="checkbox" class="'+r+'" '+'data-field="'+i.name+'" '+"> "+i.name+"</label>")});var i;if(r==="row-labelable")i=pivot.display().rowLabels().get;else if(r==="column-labelable")i=pivot.display().columnLabels().get;else i=pivot.display().summaries().get;for(var s in i){var o=e(t+' input[data-field="'+s+'"]');o.prop("checked",true);u.orderChecked(t,o)}e(t+" input").on("click",function(){if(this.checked){u.orderChecked(t,this)}else{var n=e(this).parent().detach()[0];e(t).append(n)}})},orderChecked:function(t,n){var r=e(t+" input:checked");var i=e(n).parent().detach()[0];var s=e(t).children();if(r.length-1===0)e(t).prepend(i);else if(s.length<r.length)e(t).append(i);else e(s[r.length-1]).before(i)},update_result_details:function(){var t="";var n="";e.each(pivot.filters().all(),function(e,t){n+="<em>"+e+"</em>"+" => "+t+" "})},update_results:function(){if(n&&n.beforeUpdateResults){n.beforeUpdateResults()}var t=pivot.results().all(),r=pivot.config(),a=pivot.results().columns(),f="",l;var c=e("#"+s),h;c.empty();f+="<table id="+i+' class="table table-condensed"><thead>';if(r.columnLabels.length>0&&r.summaries.length>0){var p="",d="";e.each(r.summaries,function(e,t){p+="<th>"+t+"</th>"});f+="<tr>";e.each(a,function(e,t){switch(t.type){case"row":f+='<th rowspan="2">'+t.fieldName+"</th>";break;case"column":f+='<th colspan="'+t.width+'">'+t.fieldName+"</th>";d+=p;break}});f+="</tr><tr>"+d+"</tr>"}else{f+="<tr>";e.each(a,function(t,n){if(n.type!=="column"||r.summaries.length<=1){f+="<th>"+n.fieldName+"</th>"}else{e.each(r.summaries,function(e,t){f+="<th>"+t+"</th>"})}});f+="</tr>"}f+="</thead></tr><tbody id="+o+"></tbody></table>";c.append(f);h=e("#"+o);e.each(t,function(t,n){f="<tr>";e.each(a,function(t,i){if(i.type!=="column")f+="<td>"+n[i.fieldName]+"</td>";else{e.each(r.summaries,function(e,t){if(n[i.fieldName]!==undefined)f+="<td>"+n[i.fieldName][t]+"</td>";else f+="<td>&nbsp;</td>"})}});f+="</tr>";h.append(f)});u.update_result_details();if(n&&n.afterUpdateResults){n.afterUpdateResults()}},update_label_fields:function(t){var n=[];e("."+t+"-labelable:checked").each(function(t){n.push(e(this).attr("data-field"))});pivot.display()[t+"Labels"]().set(n);u.update_results()},update_summary_fields:function(){var t=[];e(".summary:checked").each(function(n){t.push(e(this).attr("data-field"))});pivot.display().summaries().set(t);u.update_results()}};e.fn.pivot_display=function(t){if(u[t]){return u[t].apply(this,Array.prototype.slice.call(arguments,1))}else if(typeof t==="object"||!t){return u.init.apply(this,arguments)}else{e.error("Method "+t+"  doesn't exists")}}})(jQuery)