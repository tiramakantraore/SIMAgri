<script type="text/javascript">
   var URL =$(location).attr('href')+"#",
            menuPrix= "Prix",
            menuOffres="Offres",
            menuStocks ="Stocks",
           menuActualites ="Actualités",
           menuAgenda="IdAgenda",
           activeMenu=URL+menuPrix;
   var isLoaded=false;
    var URLSWF=jQuery(location).attr('href');
    var sondageChart;
    var nbMonthLastPrices=0;
    var theErrorSelector=$('#error');
    var tablePrix=$('#tablePrix');
    var tableOffre=$('#tableOffre');
    var tableStock=$('#tableStock');
   var listeMarches=$('#listeMarches');

    var  periodePrix=$('#periodePrix');
    var  fromDatePrix=$('#fromDatePrix');
    var  toDatePrix=$('#toDatePrix');
    var typePrix=$('#typePrix');
    var typeOffre=$('#typeOffre');
   var reseauSelected=$('#reseauSelected');
   var magazinsSelected=$('#magazinsSelected');
   var panelCategorieProduits=$('#panelCategorieProduits');
   var panelRegions=$('#panelRegions');
    var sondageForm=$('#sondageForm');
    var sondageGraph=$('#sondageGraph');
    var buttonBack=$('#buttonBack');

   var rendu=$('#rendu');
   var btnPrixListe=$('#btnPrixListe');
   var btnPrixHisto=$('#btnPrixHisto');
   var btnPrixCamembert=$('#btnPrixCamembert');

   var btnOffreListe=$('#btnOffreListe');
   var btnOffreHisto=$('#btnOffreHisto');
   var btnOffreCamembert=$('#btnOffreCamembert');


   var btnStockListe=$('#btnStockListe');
   var btnStockHisto=$('#btnStockHisto');
   var btnStockCamembert=$('#btnStockCamembert');
   var panelProduit=$('[data-render="listeProduit"]');


   var prixlistehtml=$('#prix-liste');
   var prixhistohtml=$('#prix-histo');
   var prixcamemberthtml=$('#prix-camembert');
   var listeActualites=$('#listeActualites');

   var stocklistehtml=$('#stock-liste');
   var stockhistohtml=$('#stock-histo');
   var stockcamemberthtml=$('#stock-camembert');
   var produit=$('#produit');

   var offrelistehtml=$('#offre-liste');
   var offrehistohtml=$('#offre-histo');
   var offrecamemberthtml=$('#offre-camembert');
   var mesureSelected=$('#mesureSelected');
   var mesureStockSelected=$('#mesureSelectedStock');
   var listeMagazin=$('[data-render="listeMagazin"]');
   var productsSelected = [];
   var marchesSelected= [];
   var categoriesSelected=[];
   var regionsSelected=[];
   var myDatatable;
   var myOffreDatatable;
   var myStockDatatable;
   var btnclicked="listePrix";
   var produitsItems;
   var categoriesItems;
   var regionsItems;
   var marchesItems;
   var isProductsLoaded=false,isMarketsLoaded=false,
           isTypePrixLoaded=false,isTypeOffreLoaded=false,
           isPeriodeLoaded=false, isProduitLoaded=false,isOrientationLoaded=false;

   var toDayDatePrix;
   var toDayDateOffres;
   var n = 1;
   var nbjrs=n*30;
   var beginDate;
   var beginDatePrix;
   var tableauDeBord=$('#dashboardTab');
//    var btnHtml="<br/> <div type='button' class='btn-flat  btn-medium' style= 'align:center;display:none;' onclick='retourSondage();' >";
//    btnHtml+="<i class='icon-home icon-black'></i> Retour";
//    btnHtml+="</div>";
   var listeProduits=$('#listeProduits');
   var  columnSelected=$('#columnSelected');
   var periodeSelected=$('#periodeSelected');
   var produitSelected=$('#produitSelected');
   var produitStockSelected=$('#produitStockSelected');

   var orientationTableauPrix=$('#orientationTabPrix');
   var searchInput=$('#search');
   var resultat=$('#resultsPrix');
   var pivotPrix=$('#pivot-prix');
   var fieldsPrix = [
       // filterable fields
       {name: 'Marche',         type: 'string', filterable: true,rowLabelable: true, columnLabelable: true, filterType: 'regexp'},
       {name: 'Produit',        type: 'string', filterable: true,rowLabelable: true, columnLabelable: true, filterType: 'regexp'},
       {name: 'TypePrix',        type: 'string', filterable: true,rowLabelable: true, columnLabelable: true, filterType: 'regexp'},
       {name: 'Moyenne',  type: 'float',  rowLabelable: false, summarizable: 'avg',
           displayFunction: function(value,field){
               return accounting.formatMoney(value, "", 0, ".", ",");
           }}
   ];
   var fieldsOffre = [
       // filterable fields

       {name: 'Auteur',         type: 'string', filterable: true, filterType: 'regexp'},
       {name: 'Mobile',        type: 'string', filterable: true,rowLabelable: true, filterType: 'regexp'},
       {name: 'Produit',        type: 'string', filterable: true,rowLabelable: true, filterType: 'regexp'},
       {name: 'TypeOffre',        type: 'string', filterable: true,rowLabelable: false, columnLabelable: true, filterType: 'regexp'},
       {name: 'Mesure',        type: 'string', filterable: true,rowLabelable: true, columnLabelable: true, filterType: 'regexp'},
       {name: 'Categorie',        type: 'string', filterable: true,rowLabelable: true,  filterType: 'regexp'},
       {name: 'Lieux',        type: 'string', filterable: true,rowLabelable: true, columnLabelable: true, filterType: 'regexp'},
       {name: 'Qte',  type: 'integer',  rowLabelable: false,  summarizable: 'sum'
          },
       {name: 'PU',  type: 'float',  rowLabelable: false, summarizable: 'sum',
           displayFunction: function(value,field){
               return accounting.formatMoney(value, "", 0, ".", ",");
           }},
       {name: 'Total',  type: 'float',  rowLabelable: false, summarizable: 'sum',
           displayFunction: function(value,field){
               return accounting.formatMoney(value, "", 0, ".", ",");
           }}
   ];
   var fieldsStocks = [
       // filterable fields
       {name: 'Magazin',         type: 'string', filterable: true,rowLabelable: true, columnLabelable: true, filterType: 'regexp'},
       {name: 'Mesure',         type: 'string', filterable: true,rowLabelable: true, columnLabelable: true, filterType: 'regexp'},
       {name: 'Produit',        type: 'string', filterable: true,rowLabelable: true, columnLabelable: true, filterType: 'regexp'},
       {name: 'Stock',  type: 'float',  rowLabelable: false, summarizable: 'sum',
           displayFunction: function(value,field){
               return accounting.formatMoney(value, "", 0, ".", ",");
           }}
   ];
   var liste=$('[data-render="liste"]');
   var listeMarche=$('[data-render="listeMarche"]');
   var filtre=$('[data-render="filtre"]');
   var panelTypePrix=$('#panelTypePrix');
   var panelTypeOffre=$('#panelTypeOffre');
   var panelProduits=$('#panelProduits');
   var periode=$('[data-render="periode"]');
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
               enabled: false
           }

       });
       // Radialize the colors
       Highcharts.getOptions().colors = Highcharts.map(Highcharts.getOptions().colors, function(color) {
           return {
               radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
               stops: [
                   [0, color],
                   [1, Highcharts.Color(color).brighten(-0.4).get('rgb')] // darken
               ]
           };
       });
   }
   function setupPivot(resultatSelector,input,pivotSelector){
       input.callbacks = {afterUpdateResults: function(){
           $('#'+resultatSelector).children('table').dataTable({
               "sDom": "<'row'<'span6'l><'span6'f>>t<'row'<'span6'i><'span6'p>>",
               "bJQueryUI": false,
               "iDisplayLength": 50,
               "aLengthMenu": [[25, 50, 100, -1], [25, 50, 100, "All"]],
               "stripeClasses": [ 'strip1', 'strip1' ],
               bFilter: false, bInfo: false,
               "bAutoWidth": true,
               "sScrollX": "150%",
               "sScrollXInner": "150%",
               "bDeferRender": true,
               "bScrollCollapse": true,
               "pagingType": "full_numbers",
               "bSort":true,
               "bProcessing": true,
               "oLanguage": {
                   "sLengthMenu": "_MENU_ lignes par page",
                   "sZeroRecords": "Désolé, aucun enregistrement trouvé",
                   "sInfo": " _START_ à _END_ de _TOTAL_ lignes",
                   "sInfoEmpty": " 0 to 0 of 0 records",
                   "sSearch":"Chercher",
                   "sProcessing": "Veuillez patienter chargement en cours...",
                   "oPaginate": {
                       "sFirst": "Première",
                       "sPrevious": "Précédente",
                       "sNext":"Suivante",
                       "sLast":"Dernière"
                   },
                   "sInfoFiltered": "(filtré à partir de _MAX_ lignes)"
               }

           });
      }};
      $('#'+pivotSelector).pivot_display('setup', input);

   }

   function setupOffrePivot(resultatSelector,input,pivotSelector){
       input.callbacks = {afterUpdateResults: function(){
           $('#'+resultatSelector).children('table').dataTable({
               "sDom": "<'row'<'span6'l><'span6'f>>t<'row'<'span6'i><'span6'p>>",
               "bJQueryUI": false,
               "iDisplayLength": 50,
               "aLengthMenu": [[25, 50, 100, -1], [25, 50, 100, "All"]],
               "stripeClasses": [ 'strip1', 'strip1' ],
               bFilter: false, bInfo: false,
               "bAutoWidth": true,
               "sScrollX": "150%",
               "sScrollXInner": "150%",
               "bDeferRender": true,
               "bScrollCollapse": true,
               "pagingType": "full_numbers",
               "bSort":true,
               "bProcessing": true,
               "oLanguage": {
                   "sLengthMenu": "_MENU_ lignes par page",
                   "sZeroRecords": "Désolé, aucun enregistrement trouvé",
                   "sInfo": " _START_ à _END_ de _TOTAL_ lignes",
                   "sInfoEmpty": " 0 to 0 of 0 records",
                   "sSearch":"Chercher",
                   "sProcessing": "Veuillez patienter chargement en cours...",
//                   "aoColumns" :[
//                       { sWidth: '12.5%', sClass: "alignRight"  },
//                       { sWidth: '12.5%', sClass: "alignRight"  },
//                       { sWidth: '12.5%', sClass: "alignRight"  },
//                       { sWidth: '12.5%' , sClass: "alignRight" },
//                       { sWidth: '12.5%' , sClass: "alignRight" },
//                       { sWidth: '12.5%', sClass: "alignRight"  },
//                       { sWidth: '12.5%' , sClass: "alignRight" },
//                       { sWidth: '12.5%', sClass: "alignRight" }
//                   ],
                   "oPaginate": {
                       "sFirst": "Première",
                       "sPrevious": "Précédente",
                       "sNext":"Suivante",
                       "sLast":"Dernière"
                   },
                   "sInfoFiltered": "(filtré à partir de _MAX_ lignes)"
               }

           });
       }};
       $('#'+pivotSelector).pivot_display('setup', input);

   }

   function buildTableauPrix() {
       SelectionDesMarches();
       selectionDesProduits();
       selectionReseau();
       onclickOption();
       var searchText=searchInput.val();
       var columnSelectedVal=columnSelected.val();
       var orientationTableauPrixVal=orientationTableauPrix.val();
      $.ajax({
           url: "${createLink(controller:'administration', action:'setLastPrices')}",
           data: {fromDate:fromDatePrix.val(),
               toDate:toDatePrix.val(),produitsIds:productsSelected,marchesIds:marchesSelected,typePrix:typePrix.val(),
               columnSelected:columnSelectedVal,searchvalue:searchText,reseauId:reseauSelected.val()},
           dataType: "text",
           accepts: "text/json",
           success: function(data, status){
               $('#my-pivot-table-prix_length').empty();
               if (orientationTableauPrixVal=='Simple'){
                   setupPivot("resultsPrix",{json:data, fields: fieldsPrix,  rowLabels:["Marche","Produit"],columnLabels:["TypePrix"],  summaries:["Moyenne"],
                       skipBuildContainers:true,resultsDivID:"resultsPrix",pivotTableId:"pivot-table-prix",resultRowsId:"resultsPrixData"},'pivot-prix');

               }else {
                   setupPivot("resultsPrix",{json:data, fields: fieldsPrix,  rowLabels:["Produit"],columnLabels:["Marche","TypePrix"],  summaries:["Moyenne"],
                       skipBuildContainers:true,resultsDivID:"resultsPrix",pivotTableId:"pivot-table-prix",resultRowsId:"resultsPrixData"},'pivot-prix');
                 }
               $('#pivot-table-prix_length').prependTo("#my-pivot-table-prix_length");
           //    $('#pivot-table-prix_length").hide();
           }
       });
   }
   function buildTableauOffres() {
       selectionDesProduits();
       selectionReseau();
       setTypeOffreValue();
       onclickOption();
       var searchText=searchInput.val();
       var columnSelectedVal=columnSelected.val();
       var periodeSelectedVal=periodeSelected.val();
       var summaries=[],rowLabels=[];
//       if (typeOffre.val()=='Achat') {
//           summaries=["Qte"];
//           rowLabels=["Auteur","Mobile","Lieux","Produit","Mesure"];
//       }else {
           summaries=["Qte","PU","Total"];
           rowLabels=["Auteur","Mobile","Lieux","Produit","Mesure"];
//       }

      $.ajax({
           url: "${createLink(controller:'administration', action:'setLastOffres')}",
           data: {fromDate:fromDatePrix.val(),
               toDate:toDatePrix.val(),produitsIds:productsSelected,typeOffre:typeOffre.val(),idMesure:mesureSelected.val(),
               columnSelected:columnSelectedVal,searchvalue:searchText,reseauId:reseauSelected.val()},
           dataType: "text",
           accepts: "text/json",
           success: function(data, status){
               $('#my-pivot-table-offre_length').empty();

               setupOffrePivot("resultsOffres",{json:data, fields: fieldsOffre,columnLabels:["TypeOffre"],  rowLabels:rowLabels,  summaries:summaries,skipBuildContainers:true,resultsDivID:"resultsOffres",pivotTableId:"pivot-table-offre",resultRowsId:"resultsOffresData"},'pivot-offres');
               $('#pivot-table-offre_length').prependTo("#my-pivot-table-offre_length");
           }
       });
   }
   function buildTableauStock() {
       selectionDesProduits();
       selectionReseau();
       SelectionDesMagazins();
       onclickOption();
       var searchText=searchInput.val();
       var columnSelectedVal=columnSelected.val();
       var periodeSelectedVal=periodeSelected.val();
       var mesureStockSelectedVal=mesureStockSelected.val();
      $.ajax({
           url: "${createLink(controller:'administration', action:'setLastStocks')}",
           data: {fromDate:fromDatePrix.val(),
               toDate:toDatePrix.val(),produitsIds:productsSelected,idMesure:mesureStockSelected.val(),
               columnSelected:columnSelectedVal,searchvalue:searchText,reseauId:reseauSelected.val(),
               magazinsIds:margazinsSelected},
           dataType: "text",
           accepts: "text/json",
           success: function(data, status){
               $('#my-pivot-table-stock_length').empty();

               setupPivot("resultsStock",{json:data, fields: fieldsStocks,  rowLabels:["Produit","Mesure"],columnLabels:["Magazin"],  summaries:["Stock"],skipBuildContainers:true,resultsDivID:"resultsStock",pivotTableId:"pivot-table-stock",resultRowsId:"resultsStockData"},'pivot-stock');
               $('#pivot-table-stock_length').prependTo("#my-pivot-table-stock_length");
           }
       });
   }
   function onSearchChange() {
       buildTablePrix();
       buildTableOffre();
       buildTableStock();

   }
   function onclickOption(){
       var filterOption=$("input[type='radio'][name='filterOption']:checked").val();
       columnSelected.val(filterOption);

       saveLocally();
   }
   function getPeriodeOption(){
       var date= new Date(),firstDay,lastDay;

       var filterOption=$("input[type='radio'][name='filterPeriodeOption']:checked").val();
       periodeSelected.val(filterOption);
       switch (periodeSelected.val()) {
           case "Aujourd&#39;hui":
               toDayDatePrix=moment(Date.parse('today')).format('DD/MM/YYYY');


               beginDate=toDayDatePrix;

               fromDatePrix.val(beginDate);
               toDatePrix.val(toDayDatePrix);

               beginDatePrix=beginDate;
               periodePrix.find('span').html(beginDate + ' - ' + beginDate);
               

               break;
           case "La semaine":
               var curr = new Date;
               firstDay = new Date(curr.setDate(curr.getDate() - curr.getDay()));
               lastDay = new Date(curr.setDate(curr.getDate() - curr.getDay()+6));
//               firstDay = Date.today().previous().monday();
//               lastDay = Date.today().next().sunday();


                if (lastDay>date)
                    lastDay=date;
               toDayDatePrix=moment(lastDay).format('DD/MM/YYYY');

               beginDate=moment(firstDay).format('DD/MM/YYYY');

               fromDatePrix.val(beginDate);
               toDatePrix.val(toDayDatePrix);

               beginDatePrix=beginDate;
               periodePrix.find('span').html(beginDate + ' - ' + toDayDatePrix);
               
               break;
           case "Le mois":


               firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
               lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
               if (lastDay>date)
                   lastDay=date;
               toDayDatePrix=moment(lastDay).format('DD/MM/YYYY');
               beginDate=moment(firstDay).format('DD/MM/YYYY');

               fromDatePrix.val(beginDate);
               toDatePrix.val(toDayDatePrix);

               beginDatePrix=beginDate;
               periodePrix.find('span').html(beginDate + ' - ' + toDayDatePrix);
               break;
           case "Le trimestre":
               firstDay = new Date(date.getFullYear(), date.getMonth()-2, 1);
               lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
               if (lastDay>date)
                   lastDay=date;
               toDayDatePrix=moment(lastDay).format('DD/MM/YYYY');

//               n = -3;
//               nbjrs=n*30;


               beginDate=moment(firstDay).format('DD/MM/YYYY');

               fromDatePrix.val(beginDate);
               toDatePrix.val(toDayDatePrix);

               beginDatePrix=beginDate;
               periodePrix.find('span').html(beginDate + ' - ' + toDayDatePrix);
               
               break;
           case "L&#39;ann&eacute;e":
               firstDay = new Date(date.getFullYear(), 0, 1);
               lastDay = new Date(date.getFullYear(), 11, 31);
               if (lastDay>date)
                   lastDay=date;
               toDayDatePrix=moment(lastDay).format('DD/MM/YYYY');
//
//               n = -12;
//               nbjrs=n*30;


               beginDate=moment(firstDay).format('DD/MM/YYYY');

               fromDatePrix.val(beginDate);
               toDatePrix.val(toDayDatePrix);

               beginDatePrix=beginDate;
               periodePrix.find('span').html(beginDate+ ' - ' + toDayDatePrix);
               
               break;
       }

   }
   function onclickPeriodeOption(){
       buildTablePrix();
       buildTableOffre();
       buildTableStock();
       reBuildListActualite();
       saveLocally();

   }
   function onclickTypePrix(){
       var filterTypePrixOption=$("input[type='radio'][name='filterTypePrix']:checked").val();
       typePrix.val(filterTypePrixOption);
       
       buildTablePrix();
       buildPriceHistoChart();
       buildPricePieChart();
       saveLocally();
   }
   function selectionReseau() {
       var reseauOption = $("input[type='radio'][name='reseau']:checked").val();

       reseauSelected.val(reseauOption);
   }

   function onclickReseauOption(){
       selectionReseau();
       buildTablePrix();
       buildTableOffre();
       buildTableStock();
       reBuildListActualite();
       saveLocally();
   }

   function onclickOrientationTabPrix(){
       var filterorientationTabPrix=$("input[type='radio'][name='filterorientationTabPrix']:checked").val();
       orientationTableauPrix.val(filterorientationTabPrix);
       buildTablePrix();
       saveLocally();
   }
   function saveLocally(){
       if (isLoaded) {
               saveToBrowser("listeCategories",categoriesSelected);
               saveToBrowser("listeRegions",regionsSelected);
               saveToBrowser("listeProduits",productsSelected);
               saveToBrowser("produitSelected",produitSelected.val());
               saveToBrowser("listeMarches",marchesSelected);
               saveToBrowser("filterTypePrix",typePrix.val());
               saveToBrowser("filterorientationTabPrix",orientationTableauPrix.val());
               saveToBrowser("filterTypeOffre",typeOffre.val());
               saveToBrowser("filterPeriodeOption",periodeSelected.val());
               saveToBrowser("filterOption",columnSelected.val());
               saveToBrowser("reseau",reseauSelected.val());
               saveToBrowser("magazins",magazinsSelected.val());
       }
   }
   function LoadFromLocal(){
       isProductsLoaded=loadCheckItemsFromBrowser(produitsItems,"produits_","listeProduits");
       isMarketsLoaded=loadCheckItemsFromBrowser(marchesItems,"marches_","listeMarches");
       loadCheckItemsFromBrowser(categoriesItems,"categories_","categoriesSelected");
       loadCheckItemsFromBrowser(regionsItems,"regions_","regionsSelected");


       isTypePrixLoaded=loadRadioFromBrowser("filterTypePrix");
       isTypeOffreLoaded=loadRadioFromBrowser("filterTypeOffre");
       isOrientationLoaded=loadRadioFromBrowser("filterorientationTabPrix");
       isPeriodeLoaded=loadRadioFromBrowser("filterPeriodeOption");
       isProduitLoaded=loadRadioFromBrowser("produit");
       loadRadioFromBrowser("filterOption");
       loadRadioFromBrowser("reseau");
       selectionReseau();

//      onclickPeriodeOption();
   }
   function setTypeOffreValue() {
       var filterTypeOffreOption = $("input[type='radio'][name='filterTypeOffre']:checked").val();
       typeOffre.val(filterTypeOffreOption);
   }
   function onclickTypeOffre(){
       setTypeOffreValue();
       buildTableOffre();
       buildOffreHistoChart();
       buildOffrePieChart();
       saveLocally();

   }
    function setFileName(){
        var startDate =$('#fromDatePrix').val();
        var endDate =$('#toDatePrix').val();
        return "Prix"+"_"+startDate+"_to_"+endDate;
    }

   function cacher_selecteur(selecteur,texte){
       selecteur.html(texte).addClass('note_pas_de_donnees');
    //   pas_de_donnees();
   }
   function montrer_selecteur(selecteur){
       selecteur.css("display", "inline-block").removeClass('note_pas_de_donnees');
   }

   function pas_de_donnees() {
       theErrorSelector.text("Il n y'a pas de données pour cette requête").addClass('note_pas_de_donnees').wait(2000).removeClass('note_pas_de_donnees').text("");
   }
   function onClickListe(){
       panelProduit.hide();
       btnclicked="listePrix";
       btnPrixListe.hide();
       btnPrixHisto.show();
       btnPrixCamembert.show();
       btnPrixHisto.removeClass("active");
       btnPrixCamembert.removeClass("active");
       btnPrixListe.addClass("active");
       $('[data-render-type="price"]').hide();
       $('[data-render="graphique"]').hide();
       $('[data-render="liste"]').show();
       $('[data-render="listeMarche"]').show();
       $('#Affichage').show();

       prixlistehtml.show(500);
   }
   function onClickHisto(){
       btnclicked="histoPrix";

       buildPriceHistoChart();

       btnPrixListe.removeClass("active");
       btnPrixCamembert.removeClass("active");
       btnPrixHisto.addClass("active");
       $('#Affichage').hide();
       btnPrixListe.show();
       btnPrixHisto.hide();
       panelProduit.show();
       btnPrixCamembert.show();
       $('[data-render="liste"]').hide();
       $('[data-render-type="price"]').hide();
       $('[data-render="graphique"]').show();
       $('[data-render="listeMarche"]').show();
       prixhistohtml.show(500);
   }

   function onClickCamembert(){
       btnclicked="camembertPrix";
       buildPricePieChart();
       $('[data-render="affichage"]').hide();

       btnPrixListe.removeClass("active");
       btnPrixHisto.removeClass("active");
       btnPrixCamembert.addClass("active");
       panelProduit.show();
       btnPrixListe.show();
       btnPrixHisto.show();
       btnPrixCamembert.hide();
       $('[data-render-type="price"]').hide();
       $('[data-render="graphique"]').show();
       $('[data-render="liste"]').hide();
       $('[data-render="listeMarche"]').show();
       prixcamemberthtml.show(500);
   }


   function onClickOffreListe(){
      $('[data-render-type="price"]').hide();
      $('[data-render="graphique"]').hide();
      $('[data-render="liste"]').show();
       $('#ChoixMesure').show();
       btnclicked="listeOffre";
       btnOffreListe.hide();
       btnOffreHisto.show();
       btnOffreCamembert.show();

       btnOffreHisto.removeClass("active");
       btnOffreCamembert.removeClass("active");
       btnOffreListe.addClass("active");
       offrehistohtml.hide();
       offrecamemberthtml.hide();
       $('#panelTypePrix').hide();
       $('#panelTypeOffre').show();
       offrelistehtml.show(500);
   }
   function onClickOffreHisto(){
       btnclicked="histoOffre";
       $('#ChoixMesure').hide();
       $('[data-render="affichage"]').hide();
       buildOffreHistoChart();
      $('[data-render="liste"]').hide();
      $('[data-render-type="offre"]').hide();
      $('[data-render="graphique"]').show();
       btnOffreListe.removeClass("active");
       btnOffreCamembert.removeClass("active");
       btnOffreHisto.addClass("active");
       offrelistehtml.hide();
       offrecamemberthtml.hide();

       btnOffreListe.show();
       btnOffreHisto.hide();
       btnOffreCamembert.show();
       panelTypePrix.hide();
       panelTypeOffre.show();
       panelProduits.show();
       offrehistohtml.show(500);
   }

   function onClickOffreCamembert(){
       btnclicked="camembertOffre";
       $('#ChoixMesure').hide();
       $('[data-render="affichage"]').hide();
       buildOffrePieChart();
      $('[data-render-type="offre"]').hide();
      $('[data-render="graphique"]').show();
       liste.hide();
       btnOffreListe.removeClass("active");
       btnOffreHisto.removeClass("active");
       btnOffreCamembert.addClass("active");

       btnOffreListe.show();
       btnOffreHisto.show();
       btnOffreCamembert.hide();

       offrelistehtml.hide();
       offrehistohtml.hide();
       panelTypePrix.hide();
       panelTypeOffre.show();
       panelProduits.show();
       offrecamemberthtml.show(500);

   }






   function onClickStockListe(){
      $('[data-render-type="price"]').hide();
      $('[data-render="graphique"]').hide();
       liste.show();
       panelTypePrix.hide();
       panelTypeOffre.hide();
       btnclicked="listeStock";
       panelProduit.hide();
       btnStockHisto.removeClass("active");
       btnStockCamembert.removeClass("active");
       btnStockListe.addClass("active");
       stockhistohtml.hide();
       stockcamemberthtml.hide();
       btnStockListe.hide();
       btnStockHisto.show();
       btnStockCamembert.show();
       stocklistehtml.show(500);
   }
   function onClickStockHisto(){
       btnclicked="histoStock";
       panelTypePrix.hide();
       panelTypeOffre.hide();
       $('[data-render="affichage"]').hide();
       buildStockHistoChart();
       liste.hide();
      $('[data-render-type="offre"]').hide();
      $('[data-render="graphique"]').show();
       btnStockListe.removeClass("active");
       btnStockCamembert.removeClass("active");
       btnStockHisto.addClass("active");
       stocklistehtml.hide();
       panelProduit.show();
       btnStockListe.show();
       btnStockHisto.hide();
       btnStockCamembert.show();
       stockcamemberthtml.hide();
       stockhistohtml.show(500);
   }

   function onClickStockCamembert(){
       btnclicked="camembertStock";
       $('[data-render="affichage"]').hide();
       buildStockPieChart();
       $('[data-render-type="offre"]').hide();
       $('[data-render="graphique"]').show();
       liste.hide();
       btnStockListe.removeClass("active");
       btnStockHisto.removeClass("active");
       btnStockCamembert.addClass("active");
       panelProduit.show();
       btnStockListe.show();
       btnStockHisto.show();
       btnStockCamembert.hide();

       stocklistehtml.hide();
       stockhistohtml.hide();
       panelTypePrix.hide();
       panelTypeOffre.hide();
       stockcamemberthtml.show(500);
   }

   function produitChoisiClick(){
       selectionDuProduit();
       buildPriceHistoChart();
       buildPricePieChart();
       saveLocally();

   }
   function produitStockChoisiClick(){
       selectionDuProduitStock();
       buildStockHistoChart();
       buildStockPieChart();
       saveLocally();

   }
   function produitClick(){
        productsSelected = [];
       listeProduits.find('input:checked').each(function() {
           productsSelected.push($(this).attr('value'));
       });
        buildTablePrix();
        buildTableOffre();
        buildTableStock();
        saveLocally();

   }
   function categorieClick(){
        categoriesSelected = [];
       $('#listeCategories').find('input:checked').each(function () {
           categoriesSelected.push($(this).attr('value'));
       });
       fireOnSelectCategorie(categoriesSelected);
   }
   function regionClick(){
        regionsSelected = [];
       $('#listeRegions').find('input:checked').each(function () {
           regionsSelected.push($(this).attr('value'));
       });
       fireOnSelectRegion(regionsSelected);
   }
   function fireOnSelectRegion(optionarray)
   {

       jQuery.ajax({
           url: "${createLink(controller:'autoComplete', action:'updateMarchesFromRegion')}",
           data: {regions:JSON.stringify(optionarray),instanceName:"marches",ctnerTemplateProd:"my1Columns"},
           cache: false,
           success: function(html) {
               listeMarches.html(html);
           }
       });


   }
   function fireOnSelectCategorie(optionarray)
   {

       jQuery.ajax({
           url: "${createLink(controller:'autoComplete', action:'updateProduitsFromCategories')}",
           data: {categories:JSON.stringify(optionarray),instanceName:"produits",ctnerTemplateProd:"my1Columns"},
           cache: false,
           success: function(html) {
               listeProduits.html(html);
           }
       });

       jQuery.ajax({
           url: "${createLink(controller:'autoComplete', action:'updateProduitsFromCategories')}",
           data: {categories:JSON.stringify(optionarray),instanceName:"produit",ctnerTemplateProd:"my4Columns",isRadio:true},
           cache: false,
           success: function(html) {
               produit.html(html);
           }
       });



   }
   function SelectionDesMarches() {
       marchesSelected = [];
       $('#listeMarches').find('input:checked').each(function () {
           marchesSelected.push($(this).attr('value'));
       });
   }
   function selectionDesProduits() {
       productsSelected = [];
       $('#listeProduits').find('input:checked').each(function () {
           productsSelected.push($(this).attr('value'));
       });
   }
   function selectionDuProduit() {
       var produitOption = $("input[type='radio'][name='produit']:checked").val();

       produitSelected.val(produitOption);

   }
   function selectionDuProduitStock() {
       var produitOption = $("input[type='radio'][name='produitStock']:checked").val();

       produitStockSelected.val(produitOption);

   }

   function SelectionDesMagazins() {
       margazinsSelected = [];
       $('#listeMagazins').find('input:checked').each(function () {
           margazinsSelected.push($(this).attr('value'));
       });
   }
   function marcheClick(){
       SelectionDesMarches();
       buildTablePrix();
       buildPriceHistoChart();
       buildPricePieChart();
       saveLocally();

   }
   function magazinClick(){
       SelectionDesMagazins();
       buildStockHistoChart();
       buildStockPieChart();
       buildTableauStock();
       saveLocally();

   }

   function selectionMesure() {
       var mesureOption = $("input[type='radio'][name='mesure']:checked").val();
       mesureSelected.val(mesureOption);
   }
   function selectionMesureStock() {
       var mesureOption = $("input[type='radio'][name='mesureStock']:checked").val();
       mesureStockSelected.val(mesureOption);
   }
   function onclickProduitOption(){
       selectionDesProduits();
       buildPriceHistoChart();
       buildPricePieChart();
       buildStockHistoChart();
       buildStockPieChart();
       buildOffreHistoChart();
       buildOffrePieChart();
       saveLocally();
   }
   function onclickMesureOption(){
       selectionMesure();
       buildTableauOffres();
       saveLocally();
   }

   function onclickMesureStockOption(){
       selectionMesureStock();
       buildTableauStock();
       saveLocally();
   }


   function buildStockHistoChart() {

       if ( btnclicked=="histoStock") {

           var barType = 'column';
           selectionReseau();
           SelectionDesMagazins();
           selectionDesProduits();
           selectionDuProduitStock();
           var nomProduit=$("label[for='" +  $("input[type='radio'][name='produitStock']:checked").attr("id")+ "']").text();

           var container =$('#stock-histo-container');
           var url = "${createLink(controller:'home', action:'setBarStock')}";

          $.getJSON(url, {fromDate: fromDatePrix.val(),reseauId:reseauSelected.val(),produitSelected:produitStockSelected.val(),toDate: toDatePrix.val(),
                      magazinsIds:margazinsSelected},
                   function (data1) {
                       /** Declare options after success callback. */

                       if (!data1.isEmpty) {
                           montrer_selecteur(container);
                           var stockBarchart = new Highcharts.Chart({
                               chart: {
                                   renderTo: 'stock-histo-container',
                                   type: 'column',
                                   zoomType: 'xy',
                                   animation: {
                                       duration: 1500,
                                       easing: 'easeOutBounce'
                                   },
                                   borderRadius: 0,
                                   borderWidth: 0

                               },
                               credits: {
                                   enabled: false
                               },
                               title: {

                                   text: ' <p> Histogramme Stocks/magazin du produit : '+nomProduit+': </p>'
                               },
                               subtitle: {
                                   text: ''
                               },
                               legend: {
                                   layout: 'vertical',
                                   align: 'right',
                                   verticalAlign: 'middle',
                                   borderWidth: 0
                               },
                               navigation: {
                                   buttonOptions: {
                                       align: 'right'
                                   }
                               },
                               tooltip: {
                                   formatter: function () {
                                       return '' +
                                               this.x + ': ' + this.y + ' ';
                                   }
                               },
                               plotOptions: {
                                   series: {
                                       colorByPoint: true
                                   },
                                   column: {
                                       pointPadding: 0.2,
                                       borderWidth: 0
                                   }
                               },
                               xAxis: {
                                   categories: []

                               },
                               yAxis: {
                                   title: {
                                       text: 'Stocks  : '
                                   }
                               },
                             exporting: {
                               enabled: false
                                   }

                           });
                           while (stockBarchart.series.length > 0)
                               stockBarchart.series[0].remove(true);
                           stockBarchart.xAxis[0].setCategories(data1.categories);
                           stockBarchart.addSeries({
                               name: data1.name,
                               data: data1.aaData
                           });

                           stockBarchart.hideLoading();
                       } else {
                           cacher_selecteur(container,"Il n y a pas de données pour le produit "+nomProduit);
                       }

                   }
           );
       }
   }

   function buildStockPieChart() {
       if (btnclicked=="camembertStock") {
           selectionReseau();
           SelectionDesMagazins();
           selectionDesProduits();
           selectionDuProduitStock();
           var nomProduit=$("label[for='" +  $("input[type='radio'][name='produitStock']:checked").attr("id")+ "']").text();
       var container =$('#stock-camembert-container');
       var url="${createLink(controller:'home', action:'setPieStock')}";
      $.getJSON(url, {reseauId:reseauSelected.val(),magazinsIds:margazinsSelected,produitSelected:produitStockSelected.val(),fromDate:fromDatePrix.val(),toDate:toDatePrix.val(),produitsIds:productsSelected},
               function(data1){
                   /** Declare options after success callback. */

                   if (!data1.isEmpty) {
                       montrer_selecteur(container);


                     var  pieStockChart = new Highcharts.Chart({
                           chart: {
                               renderTo: 'stock-camembert-container',
                               type: 'pie',
                               zoomType: 'xy',
                               marginRight:150,
                               borderRadius: 0,
                               animation: {
                                   duration: 1500,
                                   easing: 'easeOutBounce'
                               },

                               plotShadow: true,
                               borderWidth: 0
                               %{--,--}%
                               %{--ajaxSource: "${createLink(controller:'home', action:'setPieSeries')}?data=#produit,#fromDate,#toDate,#categorieTarifaire,#marketIds"--}%
                           },
                           title: {
                               text: ' Repartition stock/magazin du produit '+nomProduit

                           },
                           credits:{
                               enabled:false
                           },
                           tooltip: {
                               formatter: function() {
                                   return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';
                               },
                               percentageDecimals: 1
                           },


                           plotOptions: {
                               pie: {
                                   allowPointSelect: true,
                                   cursor: 'pointer',
                                   dataLabels: {
                                       enabled: false
                                   },
                                   showInLegend: true
                               }
                           },
                           xAxis: {
                               categories: []

                           },
                           legend: {
                               layout: 'vertical',
                               align: 'right',
                               margin:400,
                               padding: 100,
                               itemMarginTop: 5,
                               itemMarginBottom: 5,
                               floating:true,
                               maxHeight:300,
//                               verticalAlign: 'middle',
                               borderWidth: 0
                           }
                           ,
                           yAxis: {
                               title: {
                                   text: 'Stock (kg)'
                               }
                           }

                       });
                       while(pieStockChart.series.length > 0)
                           pieChart.series[0].remove(true);
                       pieStockChart.addSeries({
                           name: "Repartition",
                           type: 'pie',
                           data: data1.aaData
                       });
                       pieStockChart.hideLoading();



                   }else {
                       cacher_selecteur(container,"Il n'y a pas de données pour le produit : "+prodSelected);
                   }

               }
       );
       }
   }
   function reBuildListActualite(){

       if (activeMenu.contains(menuActualites)) {
           getPeriodeOption();
           selectionReseau();
           jQuery.ajax({
               url: "${createLink(controller:'administration', action:'actualites')}",
               data: {reseauId:reseauSelected.val(),fromDate:fromDatePrix.val(),toDate:toDatePrix.val()},
               cache: false,
               success: function(html) {
                   if (html=='')
                       listeActualites.html("<p>Désolé il n y a pas de données pour les paramètres sélectionnés</p>");
                   else
                       listeActualites.html(html);
               }
           });
       }

   }
   function buildPricePieChart() {
      if ( btnclicked=="camembertPrix"){
       var url="${createLink(controller:'home', action:'setPieSeries')}";
          selectionReseau();
          selectionDesProduits();
          selectionDuProduit();
          var nomProduit=$("label[for='" +  $("input[type='radio'][name='produit']:checked").attr("id") + "']").text();
       var typePrixVal=typePrix.val();
       var  marketIdsval="NA";
       var containerPie=$('#prix-camembert-container');

          $('#listeMarches').find('input:checked').each(function() {
              marchesSelected.push($(this).attr('value'));
          });
      $.getJSON(url, {fromDate:fromDatePrix.val(),produitsIds:productsSelected,produitSelected:produitSelected.val(),toDate:toDatePrix.val(),reseauId:reseauSelected.val(),categorieTarifaire:typePrixVal,marketIds:marchesSelected},
               function(data1){
                   /** Declare options after success callback. */

                   if (!data1.isEmpty) {

                       montrer_selecteur(containerPie);
                       pieChart = new Highcharts.Chart({
                           chart: {
                               renderTo: 'prix-camembert-container',
                               type: 'pie',
                               zoomType: 'xy',
//                               marginRight:150,
                               borderRadius: 0,
                               animation: {
                                   duration: 1500,
                                   easing: 'easeOutBounce'
                               },

                               plotShadow: true,
                               borderWidth: 0
                               %{--,--}%
                               %{--ajaxSource: "${createLink(controller:'home', action:'setPieSeries')}?data=#produit,#fromDate,#toDate,#categorieTarifaire,#marketIds"--}%
                           },
                           title: {

                               text: ' <p style="color:black"> Repartition du prix du produit '+nomProduit+' par marchés : </p>'

                           },
                           credits:{
                               enabled:false
                           },
                           subtitle: {
                               text: 'période : '+periodePrix.val()
                           },
                           tooltip: {
                               formatter: function() {
                                   return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';
                               },
                               percentageDecimals: 1
                           },


                           plotOptions: {
                               pie: {
                                   allowPointSelect: true,
                                   cursor: 'pointer',
                                   dataLabels: {
                                       enabled: false
                                   },
                                   showInLegend: true
                               }
                           },
                           xAxis: {
                               categories: []

                           },
                           legend: {
                               layout: 'vertical',
                               align: 'right',
                               margin:200,
                               padding: 3,
                               itemMarginTop: 5,
                               itemMarginBottom: 5,
                               floating:true,
                               maxHeight:300,
                               verticalAlign: 'middle',
                               borderWidth: 0
                           }
                           ,
                           yAxis: {
                               title: {
                                   text: 'Prix ('+typePrixVal+')'
                               }
                           }

                       });
                       while(pieChart.series.length > 0)
                           pieChart.series[0].remove(true);
                       pieChart.addSeries({
                           name: "Repartition",
                           type: 'pie',
                           data: data1.aaData
                       });
                       pieChart.hideLoading();

                   }else {
                       cacher_selecteur(containerPie,"Il n'y a pas de données pour le produit : "+prodSelected);
                   }

               }
       );
      }
   }

   function buildPriceHistoChart() {
       selectionDuProduit();
       if (( btnclicked=="histoPrix")|| (produitSelected.val()!="undefined")) {
           var url = "${createLink(controller:'home', action:'setBarPrix')}";
           selectionReseau();
           selectionDesProduits();

           var nomProduit=$("label[for='" +  $("input[type='radio'][name='produit']:checked").attr("id")+ "']").text();
           var categorieTarifaireVal = typePrix.val();
           var marketIdsval = "NA";

           $('#listeMarches').find('input:checked').each(function() {
               marchesSelected.push($(this).attr('value'));
           });


           var container =$('#prix-histo-container');
          $.getJSON(url, {fromDate: fromDatePrix.val(), toDate: toDatePrix.val(), categorieTarifaire: categorieTarifaireVal,
                      marketIds: marchesSelected,reseauId:reseauSelected.val(),produitSelected:produitSelected.val()},
                   function (data1) {
                       montrer_selecteur(container);
                       /** Declare options after success callback. */
                       if (!data1.isEmpty) {
                           var barType = 'bar';
//                       if (orientation.val()==='Vertical'){
                           barType = 'column';
//                       }
                           chart = new Highcharts.Chart({
                               chart: {
                                   renderTo: 'prix-histo-container',
                                   type: barType,
                                   zoomType: 'xy',
                                   animation: {
                                       duration: 1500,
                                       easing: 'easeOutBounce'
                                   },
                                   borderRadius: 0,
                                   borderWidth: 0

                               },

                               credits: {
                                   enabled: false
                               },
                               title: {

                                   text: ' '
                               },
                               subtitle: {
                                   text: ''
                               },
                               legend: {
                                   layout: 'vertical',
                                   align: 'right',
                                   verticalAlign: 'middle',
                                   borderWidth: 0
                               },

                               tooltip: {
                                   formatter: function () {
                                       return '' +
                                               this.x + ': ' + this.y + ' FCFA';
                                   }
                               },

                               plotOptions: {
                                   series: {
                                       colorByPoint: true
                                   },
                                   column: {
                                       pointPadding: 0.2,
                                       borderWidth: 0
                                   }
                               },
                               xAxis: {
                                   categories: []

                               },
                               yAxis: {
                                   title: {
                                       text: 'Prix (' + categorieTarifaireVal + ') période : ' + periodePrix.val()
                                   }
                               }


                           });
                           chart.xAxis[0].setCategories(data1.categories);

                           while (chart.series.length > 0)
                               chart.series[0].remove(true);
                           chart.addSeries({
                               name: data1.name,
                               data: data1.aaData
                           });
                           chart.setTitle({text: ' <p style="color:black"> Histogramme prix de ' + categorieTarifaireVal + ' du produit '+nomProduit+' par marché  </p> '});
                           chart.hideLoading();

                       } else {
                           cacher_selecteur(container,"Il n'y a pas de données : ");
                       }
                   }
           );
       }
   }
   function buildOffreHistoChart() {
       if ( btnclicked=="histoOffre") {
           selectionReseau();
           var url = "${createLink(controller:'home', action:'setBarOffresSeries')}";
           setTypeOffreValue();
           selectionDesProduits();
           selectionDuProduit();
           var nomProduit=$("label[for='" + produitSelected.val() + "']").text();
           var typeOffreVal = typeOffre.val();

           var marketIdsval = "NA";


           var container =$('#offre-histo-container');
          $.getJSON(url, {fromDate: fromDatePrix.val(), toDate: toDatePrix.val(), typeOffre: typeOffreVal,
                      produitsIds:productsSelected,produitSelected:produitSelected.val(),reseauId:reseauSelected.val()},
           function (data1) {

               if (!data1.isEmpty) {
                   montrer_selecteur(container);
                   var chartOffres = new Highcharts.Chart({
                       chart: {
                           renderTo: 'offre-histo-container',
                           zoomType: 'xy',
                           type: 'column',
                           animation: {
                               duration: 1500,
                               easing: 'easeOutBounce'
                           },
                           borderWidth: 0

                       },
                       credits: {
                           enabled: false
                       },
                       title: {

                           text: ' <p style="color:#1e1e1e"> Nombre d\'offres/Produit : </p>'
                       },
                       subtitle: {
                           text: 'Source:simagri'
                       },

                       legend: {
                           layout: 'vertical',
                           align: 'right',
                           verticalAlign: 'middle',
                           maxHeight: 300,
                           borderWidth: 0
                       },
                       navigation: {
                           buttonOptions: {
                               align: 'right'
                           }
                       },
                       tooltip: {
                           formatter: function () {
                               return '' +
                                       this.x + ': ' + this.y;
                           }
                       },

                       xAxis: {
                           categories: []

                       },
                       yAxis: [

                           {
                               title: {
                                   text: ' Nombres d\'Offres  ' + typeOffreVal,
                                   style: {
                                       color: '#4572A7'
                                   }

                               }

                           }
                       ]

                   });
                   chartOffres.xAxis[0].setCategories(data1.categories);
                   while (chartOffres.series.length > 0) {
                       chartOffres.series[0].remove(true);

                   }


                   for (var counter in data1.counter) {
                       // If there isn't one then add a new series.
                       chartOffres.addSeries({
                           name: data1.counter[counter].name,
//                                yAxis:1,
//                                type: 'spline',
                           marker: {
                               enabled: true
                           },
//                                dashStyle: 'shortdot',
                           data: data1.counter[counter].data
                       });

                   }
                   chartOffres.hideLoading();

               } else {


                   cacher_selecteur(container,"Il n'y a pas de données pour le produit : "+prodSelected);
               }
           }
           );
       }
      }

   function buildOffrePieChart() {
       onclickPeriodeOption();
       selectionReseau();
       selectionDesProduits();
       selectionDuProduit();
       var nomProduit=$("label[for='" + produitSelected.val() + "']").text();
       if ( btnclicked=="camembertOffre"){
           var url="${createLink(controller:'home', action:'setPieOffresSeries')}";
           setTypeOffreValue();

           var typeOffreVal=typeOffre.val();
           var  marketIdsval="NA";
           var containerPie=$('#offre-camembert-container');
          $.getJSON(url, {fromDate:fromDatePrix.val(),toDate:toDatePrix.val(),typeOffre:typeOffreVal,reseauId:reseauSelected.val(),marketIds:marketIdsval,produitSelected:produitSelected.val(),produitsIds:productsSelected},
                   function(data1){
                       /** Declare options after success callback. */

                       if (!data1.isEmpty) {
                           montrer_selecteur(containerPie);
                           var pieChart = new Highcharts.Chart({
                               chart: {
                                   renderTo: 'offre-camembert-container',
                                   type: 'pie',
                                   zoomType: 'xy',
                                   marginRight:150,
                                   animation: {
                                       duration: 1500,
                                       easing: 'easeOutBounce'
                                   },
                                   borderWidth: 0,
                                   plotShadow: true
                                   %{--,--}%
                                   %{--ajaxSource: "${createLink(controller:'home', action:'setPieSeries')}?data=#produit,#fromDate,#toDate,#categorieTarifaire,#marketIds"--}%
                               },
                               title: {

                                   text: ' <p style="color:darkred"> Repartition des offres ('+typeOffreVal+') de produits </p>'

                               },
                               credits:{
                                   enabled:false
                               },
                               subtitle: {
                                   text: 'période : '+periodePrix.val()
                               },
                               tooltip: {
                                   formatter: function() {
                                       return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';
                                   },
                                   percentageDecimals: 1
                               },


                               plotOptions: {
                                   pie: {
                                       allowPointSelect: true,
                                       cursor: 'pointer',
                                       dataLabels: {
                                           enabled: false
                                       },
                                       showInLegend: true
                                   }
                               },
                               xAxis: {
                                   categories: []

                               },
                               legend: {
                                   layout: 'vertical',
                                   align: 'right',
                                   margin:200,
                                   padding: 3,
                                   itemMarginTop: 5,
                                   itemMarginBottom: 5,
                                   floating:true,
                                   maxHeight:300,
                                   verticalAlign: 'middle',
                                   borderWidth: 0
                               }
                               ,
                               yAxis: {
                                   title: {
                                       text: 'Montant ('+typeOffreVal+')'
                                   }
                               }

                           });
                           while(pieChart.series.length > 0)
                               pieChart.series[0].remove(true);
                           pieChart.addSeries({
                               name: "Repartition",
                               type: 'pie',
                               data: data1.aaData
                           });
                           pieChart.hideLoading();

                       }else {
                           cacher_selecteur(containerPie,"Il n'y a pas de données pour le produit : "+prodSelected);
                       }

                   }
           );
       }
   }

   function buildTablePrix(){
       if (activeMenu.contains(menuPrix)) {
           getPeriodeOption();
           if (btnclicked == "listePrix") {
               buildTableauPrix();

           }
       }
   }
   function buildTableOffre(){
       if (activeMenu.contains(menuOffres)) {
           getPeriodeOption();

           if (btnclicked == "listeOffre") {
               buildTableauOffres();

           }
       }
   }



   function buildTableStock(){
       if (activeMenu.contains(menuStocks)) {
           getPeriodeOption();
           if (btnclicked == "listeStock") {
               buildTableauStock();

           }
       }
   }

   function toggleaccordion( selector, e ) {
      $(selector).click(function (e) {
            var chevState=$(e.target).siblings("i.indicator").toggleClass('icon-plus icon-minus');
           $(e.target).siblings("i.indicator").not(chevState).removeClass("icon-plus").addClass("icon-minus");

       });
   }

   $(document).ready(function(){
       nbMonthLastPrices =$('#nbMonthLastPrices').val();
       moment.lang('fr');
       toDayDatePrix = moment(Date.parse('today')).format('DD/MM/YYYY');

       n = -nbMonthLastPrices;
       nbjrs = n * 30;


       beginDate = moment(nbjrs.days().fromNow()).format('DD/MM/YYYY');

       fromDatePrix.val(beginDate);
       toDatePrix.val(toDayDatePrix);

       beginDatePrix = beginDate;

       periodePrix.daterangepicker(
               {
                   opens: 'right',
                   format: 'DD/MM/YYYY',
                   locale: {
                       applyLabel: 'Valider',
                       cancelLabel: 'Annuler',
                       fromLabel: 'Du',
                       toLabel: 'Au',
                       weekLabel: 'W',
                       customRangeLabel: 'Ma période',
                       daysOfWeek: moment()._lang._weekdaysMin.slice(),
                       monthNames: moment()._lang._monthsShort.slice(),
                       firstDay: 0
                   },
                   startDate: fromDatePrix,
                   endDate: toDayDatePrix
               },
               function (start, end) {

                   fromDatePrix.val(start.format('DD/MM/YYYY'));
                   toDatePrix.val(end.format('DD/MM/YYYY'));
                   periodePrix.find('span').html(start.format('DD/MM/YYYY') + ' - ' + end.format('DD/MM/YYYY'));
                   buildPriceHistoChart();
                   buildPricePieChart();
               }
       );
       periodePrix.find('span').html(moment(nbjrs.days().fromNow()).format('DD/MM/YYYY') + ' - ' + moment(Date.parse('today')).format('DD/MM/YYYY'));

      $('input[type="checkbox"]').click(function () {
           if ($(this).attr("value") == "periode") {
              $(".morePeriodeOpts").toggle();
           }
       });


      $('#accordion').find('.accordion-toggle').click(function (e) {
           var chevState =$(e.target).siblings("i.indicator").toggleClass('icon-plus icon-minus');
          $(e.target).siblings("i.indicator").not(chevState).removeClass("icon-plus").addClass("icon-minus");


       });

       $('#accordionpane').find('.accordion-toggle').click(function (e) {
           var chevState =$(e.target).siblings("i.indicator").toggleClass('icon-plus icon-minus');
           $(e.target).siblings("i.indicator").not(chevState).removeClass("icon-plus").addClass("icon-minus");


       });

      $('i.indicator').click(function (e) {
          $(e.target).prev().click();
       });



      $('#uncheck-allProducts').click(function () {
          $("input[type='checkbox'][name^='produits_']").prop('checked', false);
           produitClick();

       });
      $('#check-allProducts').click(function () {
          $("input[type='checkbox'][name^='produits_']").prop('checked', true);
           produitClick();
       });
       produitsItems=$("input[type='checkbox'][name^='produits_']");


       $('#uncheck-allMarches').click(function () {
           $("input[type='checkbox'][name^='marches_']").prop('checked', false);
           marcheClick();

       });
       $('#check-allMarches').click(function () {
           $("input[type='checkbox'][name^='marches_']").prop('checked', true);
           marcheClick();
       });

       $('#uncheck-allMagazins').click(function () {
           $("input[type='checkbox'][name^='magazins_']").prop('checked', false);
           marcheClick();

       });
       $('#check-allMagazins').click(function () {
           $("input[type='checkbox'][name^='magazins_']").prop('checked', true);
           marcheClick();
       });

       marchesItems=$("input[type='checkbox'][name^='marches_']");

       activeMenu = URL+menuPrix;

       $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {

           //  e.target activated tab
           //   e.relatedTarget previous tab
           liste.show();
           listeMarche.show();
           filtre.show();
           panelTypePrix.show();
           panelTypeOffre.show();
           panelCategorieProduits.show();
           panelRegions.show();
           panelProduit.hide();
           panelCategorieProduits.hide();
           panelRegions.hide();
           listeMagazin.hide();

           activeMenu =$(e.target).text();
           if (activeMenu.contains(menuPrix)) {
               btnclicked = "listePrix";
               panelTypeOffre.hide();
               panelTypePrix.show();
               $('#search').val("");

               periode.show();
               filtre.show();
               onclickOrientationTabPrix();

           } else {
               if (activeMenu.contains(menuOffres)) {
                   btnclicked = "listeOffre";
                   panelTypePrix.hide();
                   panelTypeOffre.show();
                   $('#search').val("");
                   listeMarche.hide();
                   periode.show();
                   buildTableOffre();
               } else {
                   if (activeMenu.contains(menuStocks)) {
                       btnclicked = "listeStock";
                       panelTypePrix.hide();
                       panelTypeOffre.hide();
                       $('#search').val("");


                       $('[data-render="listeMarche"]').hide();
                       listeMagazin.show();
                       periode.show();
                       buildTableStock();
                   }
                   else {
                       panelTypePrix.hide();
                       panelTypeOffre.hide();
                       panelProduits.hide();
                       panelProduit.hide();
                       listeMarche.hide();

                       liste.hide();
                       filtre.hide();

                   }
               }
           }

       });

      selectionDesProduits();
       LoadFromLocal();
//       if (!(isProductsLoaded || isMarketsLoaded || isTypePrixLoaded || isTypeOffreLoaded
//               || isPeriodeLoaded || isProduitLoaded || isOrientationLoaded))
       $('a[href="#IdPrix"]').tab('show');
        isLoaded=true;
      openMenuOnHover();

//       $('input[type="checkbox"][name="morePeriodeOpts"]').attr("checked","checked");
//       $(".morePeriodeOpts").toggle();

  });



</script>