/**Application js*/
// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better 
// to create separate JavaScript files as needed.
//= require jquery
//= require jquery-ui-1.10.3.custom
//= require bootstrap
//= require jquery.form
//= require date
//= require moment
//= require bootstrap-datepicker
//= require bootstrap-datepicker.fr
//= require accounting.min
//= require numeric
//= require upclick
//= require kendo/kendo.web.min
//= require kendo/cultures/kendo.culture.fr.min
//= require kendo/console
//= require kendo/js/kendo.web.ext
//= require jquery.jqGrid-4.6.0/js/jquery.jqGrid.src
//= require jquery.jqGrid-4.6.0/js/i18n/grid.locale-fr
//= require jgrowl/jquery.jgrowl
//= require span2field/span2field
//= require dynamicBlocks/dynamicBlocks
//= require Highstock/js/highstock
//= require Highstock/js/themes/grid
//= require Highcharts/js/modules/exporting
//= require slickquiz/slickQuiz.js
//= require slickquiz/slickQuiz-config
//= require counter/raphael
//= require counter/jquery-monthlyGraph
//= require counter/popup
// require counter/modernizr-2.0.min.js
//= require jasny-bootstrap/jasny-bootstrap
//= require fullcalendar
//= require fullcalendar.fr
//= require jquery.qtip.min
//= require pivottablejs/dist/pivot.js
//= require pivottablejs/dist/getCharts
//= require pivottablejs/dist/gchart_renderers
//= require pivottablejs/dist/pivot.fr
//= require pivottablejs/dist/exportPivot
//= require daterangepicker
//= require jquery_pivot
//= require jquery.dataTables
//= require dataTables.tableTools
//= require dataTables.scroller
//= require jquery.maskedinput
//= require mediaelements/mediaelement-and-player
//= require_self

jQuery.fn.exists = function(){return this.length>0;};
function supports_history_api() {
    if (window.history && history.pushState)
    return !!(window.history && history.pushState);
}
if (typeof jQuery !== 'undefined') {
    (function ($) {
        $('#spinner').ajaxStart(function () {
            $(this).fadeIn();
        }).ajaxStop(function () {
            $(this).fadeOut();
        });
    })(jQuery);
}
if (!Array.prototype.filter) {
    Array.prototype.filter = function(fun /*, thisp*/) {
        var len = this.length >>> 0;
        if (typeof fun != "function")
            throw new TypeError();

        var res = [];
        var thisp = arguments[1];
        for (var i = 0; i < len; i++) {
            if (i in this) {
                var val = this[i]; // in case fun mutates this
                if (fun.call(thisp, val, i, this))
                    res.push(val);
            }
        }
        return res;
    };
}
String.prototype.contains = function(it) { return this.indexOf(it) != -1; };
Array.prototype.hasObject = (
    !Array.indexOf ? function (o)
    {
        var l = this.length + 1;
        while (l -= 1)
        {
            if (this[l - 1] === o)
            {
                return true;
            }
        }
        return false;
    } : function (o)
    {
        return (this.indexOf(o) !== -1);
    }
    );

$.datepicker.setDefaults($.datepicker.regional['fr']);
function getFormObj(formsel) {
    var formObj = {};
    var inputs = formsel.serializeArray();
    $.each(inputs, function (i, input) {
        formObj[input.name] = input.value;
    });
    return formObj;
}

function isNumberKey(evt) {

    if (evt.shiftKey == true) {
        evt.preventDefault();
    }
    // Allow Only: keyboard 0-9, numpad 0-9, backspace, tab, left arrow, right arrow, delete
    return (evt.keyCode >= 48 && evt.keyCode <= 57) || (evt.keyCode >= 96 && evt.keyCode <= 105) || evt.keyCode == 8 || evt.keyCode == 9 || evt.keyCode == 37 || evt.keyCode == 39 || evt.keyCode == 46 || evt.keyCode == 110;

}
function myformatDate(date){
    if(!date.getDate()){return '';}

    return $.datepicker.formatDate( 'dd/mm/yy', date );
}
// function that gathers IDs of checked nodes
function checkedNodeIds(nodes, checkedNodes) {
    for (var i = 0; i < nodes.length; i++) {
        if (nodes[i].checked) {
            checkedNodes.push(nodes[i].id);
        }

        if (nodes[i].hasChildren) {
            checkedNodeIds(nodes[i].children.view(), checkedNodes);
        }
    }
}
function saveToBrowser(name,value){
    if(typeof(Storage) !== "undefined") {
      //  localStorage.clear();//a deplacer dans un menu d'administration
        if ((name!== "undefined") && (value!== "undefined") && (value!== "")) {

            localStorage.setItem(name,value);
//            console.log("");
//            console.log("To Local storage : "+JSON.stringify(value));
        }
    }
}

function loadCheckItemsFromBrowser(items,prefix,name){
    if(typeof(Storage) !== "undefined") {
        if (name) {
            var localValue=localStorage.getItem(name);
            if ((localValue!= null)&& (localValue>"")) {
                    items.prop('checked', false);
                items.filter(function () {
                    return (localValue.split(',').hasObject(this.name.replace(prefix, "")));
                    }).prop('checked', true);

                 //       console.log("Items From Local storage : "+localValue);

               return true
            }
            else return false

        }
    }
}
function loadRadioFromBrowser(name){
    if(typeof(Storage) !== "undefined") {
        if (name) {
            var localValue=localStorage.getItem(name);
            var radioItem=$("input[type='radio'][name='"+name+"'][value='"+localValue+"']");
            if (radioItem)
                radioItem.prop('checked', true);
            if ((localValue!= null)&& (localValue>"")) {
               // console.log("From Local storage" +name+" : "+localValue);
            }

        }
    }
}
function openMenuOnHover(){
    //$(".nav li").on("click", function () {
    //    $(".nav li").removeClass("active");
    //    $(this).addClass("active");
    //});
    $('ul.nav li.dropdown').hover(function() {
        $(this).find('.dropdown-menu').stop(true, true).delay(5).fadeIn();
    },
    function() {
        $(this).find('.dropdown-menu').stop(true, true).delay(5).fadeOut();
    });
}
function openVerticalMenuOnHover(){
    $('ul.nav li.dropdown').hover(function() {
        $(this).find('.dropdown-menu').stop(true, true).delay(5).fadeIn();
    }, function() {
        $(this).find('.dropdown-menu').stop(true, true).delay(5).fadeOut();
    });
}
function stringToIntList(str) {

    var temp;
    temp = [];
    for (var a in str ) {
        if (str.hasOwnProperty(a)) {

            temp[a] = parseInt(str[a], 10); // Explicitly include base as per Álvaro's comment
        }
    }
    if (str==''){
        return '';
    }
    else
       return temp
}
function showSpinner() {
//    jQuery("#spinner').show();
    $('.spinnertext').show();
}
function hideSpinner(anchor) {
//    jQuery("#spinner').hide();
    $('.spinnertext').hide();
   //  if(anchor != undefined){
            //location.hash =anchor;
    // }

}

//$("#spinner").bind("ajaxSend", function() {
//    $(this).fadeIn();
//}).bind("ajaxComplete", function() {
//    $(this).fadeOut();
//});

function emptyFileInputField(formObj,selector) {
    $(formObj).find(selector).val('');
//    hideAndRemoveFileAttachment(formObj);
}

function submitForm(formObj, url,inputField,successMessage,updateSelector) {
    //showSpinner();
    $(formObj).ajaxSubmit({
        async: true,
        cache:false,
        url: url,
        //data:{update:updateSelector},
        success: function (data) {
            if (successMessage>"") {
                $.jGrowl(successMessage, {
                    sticky: false,
                    header: 'Notification',
                    theme: 'iphone'
                });
      //          hideSpinner();
            }
            if (updateSelector>''){
                $("#"+updateSelector).html(data);
            }else {
              emptyFileInputField(formObj,inputField);  //reset and hide input file info
                $(formObj).trigger("reset");   //reset other form elements
            }
        }
    })
}
function submitFormAsync(formObj, url,inputField,successMessage,updateSelector) {
//    showSpinner();
    $(formObj).ajaxSubmit({
        async: true,
        cache:false,
        url: url,
        //data:{update:updateSelector},
        success: function (data) {
            if (successMessage>"") {
                $.jGrowl(successMessage, {
                    sticky: false,
                    header: 'Notification',
                    theme: 'iphone'
                });
  //              hideSpinner();
            }
            if (updateSelector>''){
                $("#"+updateSelector).html(data);
            }else {
                emptyFileInputField(formObj,inputField);  //reset and hide input file info
                $(formObj).trigger("reset");   //reset other form elements
            }
        }
    })
}

function submitFormWithCkEditor(formObj, url,inputField,successMessage,updateSelector) {
    $(formObj).ajaxSubmit({
        async: true,
        cache:false,
        url: url,
        data: {contenuCKEdit:CKEDITOR.instances.contenu.getData(),update:updateSelector},
        success: function (data) {
            if (successMessage>"") {

                $.jGrowl(successMessage, {
                    sticky: false,
                    header: 'Notification',
                    theme: 'iphone'
                });
            }
            if (url.contains("/modifier")|| url.contains("/edit")||url.contains("/delete")){
                $("#"+updateSelector).html(data);
            }else {
                CKEDITOR.instances.contenu.setData("");
                $(formObj).trigger("reset");   //reset other form elements
            }
        }
    });

}
    function radializeIt(){

        Highcharts.setOptions({
            lang: {
                months: [ 'Jan',
                    'Fev',
                    'Mar',
                    'Avr',
                    'Mai',
                    'Jun',
                    'Jul',
                    'Aou',
                    'Sep',
                    'Oct',
                    'Nov',
                    'Dec'],
                weekdays: ['Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi'],

                contextButtonTitle:'Exporter',
                downloadJPEG:'Télécharger image JPEG',
                downloadPNG :'Télécharger image PNG',
                downloadPDF:'Télécharger document PDF',
                downloadSVG:'Télécharger document SVG',
                loading:'Veuillez patienter, chargement du graphique en cours ... ',
                printChart:'Imprimer',
                rangeSelectorFrom:'du',
                rangeSelectorTo:'au',
                thousandsSep:' '

            },  navigation: {
                buttonOptions: {
                    align: 'right'
                }
            },
            exporting: {
                enabled: true
            }

        });
        // Radialize the colors
        //Highcharts.getOptions().colors = Highcharts.map(Highcharts.getOptions().colors, function(color) {
        //    return {
        //        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        //        stops: [
        //            [0, color],
        //            [1, Highcharts.Color(color).brighten(-0.4).get('rgb')] // darken
        //        ]
        //    };
        //});
    }

    $(function() {
        radializeIt()
    });



// handle the back and forward buttons
$(window).bind('popstate', function(event) {

    // if the event has our history data on it, load the page fragment with AJAX
    var state = event.originalEvent.state;
    if (state) {
        container.load(state.path);
    }
});
// when the page first loads update the history entry with the URL
// needed to recreate the 'first' page with AJAX
history.replaceState({ path: window.location.href }, '');
