/**
 * Created by tiramakan on 10/07/2014.
 */
var tableToExcel = (function() {

    var uri = 'data:application/vnd.ms-excel;base64,'
        , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>'
        , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
        , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) };
    return function(table, name, filename) {
        var tableDoc;
        if (!table.nodeType) tableDoc =document.getElementsByClassName(table)[0];  //Modification joel 10/07/2014
        var ctx = {worksheet: name || 'Worksheet', table: tableDoc.innerHTML};
        window.location.href = uri + base64(format(template, ctx));
//        tableDoc.href = uri + base64(format(template, ctx));//Modification joel 16/09/2014
//        tableDoc.download = filename;
//        tableDoc.click();
    }
})();