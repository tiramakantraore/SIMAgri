var tableToExcel=function(){var e="data:application/vnd.ms-excel;base64,",t='<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>',n=function(e){return window.btoa(unescape(encodeURIComponent(e)))},r=function(e,t){return e.replace(/{(\w+)}/g,function(e,n){return t[n]})};return function(i,s,o){var u;if(!i.nodeType)u=document.getElementsByClassName(i)[0];var a={worksheet:s||"Worksheet",table:u.innerHTML};window.location.href=e+n(r(t,a))}}()