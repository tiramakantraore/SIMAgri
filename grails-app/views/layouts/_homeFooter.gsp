<%@ page import="simagri.TypePartenaire" %>
    <div  class="row footerarea">
    <div class="col-sm-6 col-md-6 " style="padding-top: 0">
       <div  class="row">

       <g:link controller="pageUtilisateur" action="showByPartnerType" params="[typePartenaire: 'ICCO']"  title="Partenaire ICCO" class="partnersLogo">
        <asset:image src="logo_ICCO_90x35px.png" height="35px"/></g:link>
       <g:link controller="pageUtilisateur" action="showByPartnerType" params="[typePartenaire:'IICD']" title="Partenaire IICD" class="partnersLogo">
           <asset:image src="logo_IICD_90x35px.png" height="35px"/></g:link>
       <g:link controller="pageUtilisateur" action="showByPartnerType" params="[typePartenaire:'TTC']" title="Partenaire TEXT TO CHANGE" class="partnersLogo">
           <asset:image src="logo_TTC_90x35px.png" height="35px"/></g:link>
       <g:link controller="pageUtilisateur" action="showByPartnerType" params="[typePartenaire:'TFK']"  title="Partenaire INTER-PROFESSION TABLE FILIERE KARITE" class="partnersLogo">
           <asset:image src="logo_TFK_45x35px.png" height="35px"/></g:link>
       <g:set var="typePartenaire" value="$session.typePartenaire"/>

       <g:link controller="pageUtilisateur" action="showByPartnerType" params="[typePartenaire:typePartenaire]"  title="Partenaire ${session.organisation?.toUpperCase()}" class="partnersLogo">
           <asset:image src="$session.footerLogo" height="35px"/></g:link>
       <g:link controller="pageUtilisateur" action="showByPartnerType" params="[typePartenaire:'BAMIG']" title="Partenaire BAMIG" class="partnersLogo">
           <asset:image src="logo_Bamig_45x35px.png" height="35px"/></g:link>
       </div>
        <div class="row footeraddr" style="padding-top: 5px">
            <div class="col-sm-8 col-md-8">
                <address>
                    Concepteur:
                    <a href="mailto:bamigsoft@bamig.com&cc=tiramakantraore@gmail.com?subject=simagri" style="color: #FAB300"
                       title="${developer?:'Joël Hyacinthe TRAORE'}" >BAMIG</a><br>
               </address>
            </div>

        </div>

    </div>
    <div class="col-sm-6 col-md-6 ">
        <div class="row footeraddr" style="padding-top:10px">
            <div class="col-sm-3 col-md-3">
                <address>
                    ${session.organisation?:'Aprossa Afrique Verte'}<br>
                    <a href="${createLink(uri: '/partenaires')}" target="_blank" style="color: #FAB300">Partenaires</a>
                </address>
            </div>
            <div class="col-sm-5 col-md-5 ">
            <address>
                Contact ${session.organisation?:'Aprossa Afrique Verte'}<br>
                ${g.message(code:"telephone.text", default:"Téléphone")} :${session.TelOrganisation?:'+22650341139'}<br>
                 <g:if test="${session.FaxOrganisation}">
                    Fax :${session.FaxOrganisation}<br>
                 </g:if>

                Mobile :${session.MobileOrganisation?:'+22670723552'} <br>
            </address>
            </div>
            <div class="col-sm-3 col-md-3">
            <address>
                Email :<br>
                <a href="mailto:${session.EmailOrganisation}" style="color: #FAB300">${session.EmailOrganisation}</a>
            <g:if test="${session.EmailOrganisation2}">
                <a href="mailto:${session.EmailOrganisation2}" style="color: #FAB300">${session.EmailOrganisation2}</a>
            </g:if>
            </address>
            </div>
            <div class="col-sm-1 col-md-1 ">
                <asset:image src="cereal_footer.png"/>
            </div>

        </div>
    </div>
</div>




                                                                        