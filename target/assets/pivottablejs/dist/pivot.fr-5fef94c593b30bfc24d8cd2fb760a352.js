(function(){var e,t,n,r,i;r=$.pivotUtilities.numberFormat;i=$.pivotUtilities.aggregatorTemplates;e=r({thousandsSep:" ",decimalSep:","});t=r({digitsAfterDecimal:0,thousandsSep:" ",decimalSep:","});n=r({digitsAfterDecimal:1,scaler:100,suffix:"%",thousandsSep:" ",decimalSep:","});$.pivotUtilities.locales.fr={localeStrings:{renderError:"Une erreur est survenue en dessinant le tableau crois&eacute;.",computeError:"Une erreur est survenue en calculant le tableau crois&eacute;.",uiRenderError:"Une erreur est survenue en dessinant l'interface du tableau crois&eacute; dynamique.",selectAll:"Tous",selectNone:"Aucun",tooMany:"(trop de valeurs &agrave; afficher)",filterResults:"Filtrer les valeurs",totals:"Totaux",vs:"sur",by:"par"},aggregators:{Nombre:i.count(t),"Nombre de valeurs uniques":i.countUnique(t),"Liste de valeurs uniques":i.listUnique(", "),Somme:i.sum(e),"Somme en entiers":i.sum(t),Moyenne:i.average(e),"Ratio de sommes":i.sumOverSum(e),"Borne sup&eacute;rieure 80%":i.sumOverSumBound80(true,e),"Borne inf&eacute;rieure 80%":i.sumOverSumBound80(false,e),"Somme en proportion du totale":i.fractionOf(i.sum(),"total",n),"Somme en proportion de la ligne":i.fractionOf(i.sum(),"row",n),"Somme en proportion de la colonne":i.fractionOf(i.sum(),"col",n),"Nombre en proportion du totale":i.fractionOf(i.count(),"total",n),"Nombre en proportion de la ligne":i.fractionOf(i.count(),"row",n),"Nombre en proportion de la colonne":i.fractionOf(i.count(),"col",n)},renderers:{Tableau:$.pivotUtilities.renderers["Table"],Barres:$.pivotUtilities.gchart_renderers["Bar Chart"],Lignes:$.pivotUtilities.gchart_renderers["Line Chart"],Aires:$.pivotUtilities.gchart_renderers["Area Chart"],Camembert:$.pivotUtilities.gchart_renderers["Pie Chart"],"Barres Empilées":$.pivotUtilities.gchart_renderers["Stacked Bar Chart"]}}}).call(this)