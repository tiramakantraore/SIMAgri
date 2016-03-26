<div class="homePageContent ">

<div class="row">
    <div class="col-sm-12 col-md-12 ">
<g:if test="${pageAccueilInstance}">
        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel" >
            <!-- Indicators -->
            %{--<ol class="carousel-indicators">--}%
                %{--<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>--}%
                %{--<li data-target="#carousel-example-generic" data-slide-to="1"></li>--}%
                %{--<li data-target="#carousel-example-generic" data-slide-to="2"></li>--}%
            %{--</ol>--}%

            <!-- Wrapper for slides -->
            %{--<g:set var="images" value="${pageAccueilInstance?.getMesImageRandomize()}" />--}%
            <g:if test="${images}">
                <div class="carousel-inner">

                            <g:each status="i" var="image" in="${images}">
                                <g:if test="${image?.canRender()}">
                                    <div class="item${i==0?' active':''}">
                                             <img src="${createLink(controller: 'monImage', action: 'showImg',params:[id:image?.id])}" width="900" alt=""/>
                                            <div class="carousel-caption">
                                                ${image.description}
                                            </div>

                                    </div>
                                </g:if>
                            </g:each>
                </div>
            </g:if>
                %{--<div class="item">--}%
                    %{--<asset:image src="logoSIMAgri.png" width="80%" height="80%"  alt="..." />--}%
                    %{--<div class="carousel-caption">--}%
                        %{--...--}%
                    %{--</div>--}%
                %{--</div>--}%
            %{--<div class="item">--}%
                %{--<asset:image src="logoSIMAgri.png" width="80%" height="80%"  alt="..." />--}%
                %{--<div class="carousel-caption">--}%
                    %{--...--}%
                %{--</div>--}%
            %{--</div>--}%



            <!-- Controls -->
            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
            </a>
            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right"></span>
            </a>
        </div>
</g:if>
</div>
     %{--<section id="carousel">--}%
         %{--<div id="myCarousel" class="carousel slide">--}%
             %{--<div class="carousel-inner">--}%
                 %{--<div class="item"><asset:image src="logoSIMAgri.png" width="224px" height="50%" />--}%
                     %{--<div class="carousel-caption">--}%
                         %{--<h4>Un marché de céréales</h4>--}%
                         %{--<p>Avec SIMAgri restez informé de l'évolution des prix sur les marchés de céréales</p>--}%
                     %{--</div>--}%
                 %{--</div>--}%
                 %{--<div class="item"><asset:image src="logoSIMAgri.png" width="224px" height="50%" />--}%
                     %{--<div class="carousel-caption">--}%
                         %{--<h4>Un marché de bovins</h4>--}%
                         %{--<p>SIMAgri couvre aussi les marchés de bovins comme celui-ci</p>--}%
                     %{--</div>--}%
                 %{--</div>--}%
                 %{--<div class="item"><asset:image src="logoSIMAgri.png" width="224px" height="50%" />--}%
                     %{--<div class="carousel-caption">--}%
                         %{--<h4>Un marché de moutons</h4>--}%
                         %{--<p>Les éléveurs de moutons ne sont pas oubliés</p>--}%
                     %{--</div>--}%
                 %{--</div>--}%

                 %{--<div class="item"><g:img dir="images/new_caroussel" file="communaute.png" alt="image01" style="width:100%;height:500px" />--}%
                     %{--<div class="carousel-caption">--}%
                         %{--<h4>Une communauté</h4>--}%
                         %{--<p>SIMAgri <span> <strong>imaginé</strong></span>, <span> <strong>conçu</strong></span> et <span> <strong>réalisé</strong></span>--}%
                             %{--par une communauté d'acteurs du dévéloppement pour les acteurs de toute la chaine de valeur.</p>--}%
                     %{--</div>--}%
                 %{--</div>--}%
                 %{--<div class="item"><g:img dir="images" file="paysans.jpg" alt="image01" style="width:100%;height:500px" />--}%
                 %{--<div class="carousel-caption">--}%
                 %{--<h4>Une communauté</h4>--}%
                 %{--<p>SIMAgri <span> <strong>imaginé</strong></span>, <span> <strong>conçu</strong></span> et <span> <strong>réalisé</strong></span>--}%
                 %{--par une communauté d'acteurs du dévéloppement pour les acteurs de toute la chaine de valeur.</p>--}%
                 %{--</div>--}%
                 %{--</div>--}%

                 %{--<div class="item"><g:img dir="images/inevitable" file="3.png" alt="image01" style="width:80%;height:500px"  />--}%
                     %{--<div class="carousel-caption">--}%
                         %{--<h4>Contactez-nous</h4>--}%
                         %{--<p>Pour de plus amples informations contactez-nous au <span><strong>+22650341139</strong></span>--}%
                             %{--ou ecrivez-nous à l'adresse <span><strong>afrique.verte@gmail.com</strong></span> </p>--}%
                     %{--</div>--}%
                 %{--</div>--}%
             %{--</div>--}%
         %{--</div>--}%
         %{--<!-- Carousel nav -->--}%
         %{--<a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>--}%
         %{--<a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>--}%

     %{--</section>--}%

      %{--<div class="col-sm-12 col-md-12 articleContent">--}%
          %{--<g:if test="${pageAccueilInstance?.corpsImg}">--}%
          %{--<img src="${createLink(controller: 'pageAccueil', action: 'imageCorps')}" width="100%" height="200" alt="" class="textwraptoRight"/>--}%
                       %{--${pageAccueilInstance?.corps}--}%
          %{--</g:if>--}%

       %{--</div>--}%
 </div>
</div>
    <br>
<div class="row" >
    <div class="col-sm-3 col-md-3">
        <a  href="${createLink(controller: 'pageUtilisateur', action: 'showPublic',params:[id:pageAccueilInstance?.pagePrix?.id])}" >
            <g:if test="${pageAccueilInstance?.pagePrix?.photo}">
                <img src="${createLink(controller: 'pageUtilisateur', action: 'imagePage',params:[id:pageAccueilInstance?.pagePrix?.id])}" height="100" alt=""/>
            </g:if>
        </a>

            <div style="display: inline-block;padding-top:5px; ">
                <p style="padding-bottom:1px;"> <strong>${pageAccueilInstance?.pagePrix?.nom}</strong> </p>

                <a  href="${createLink(controller: 'pageUtilisateur', action: 'showPublic',params:[id:pageAccueilInstance?.pagePrix?.id])}" >
                    ${pageAccueilInstance?.pagePrix?.contenu?.ellipsify()}
                </a>
            </div>

    </div> <!-- end of col-sm-8 col-md-8 tag-->
    <div class="col-sm-3 col-md-3">
        <a  href="${createLink(controller: 'pageUtilisateur', action: 'showPublic',params:[id:pageAccueilInstance?.pageOffre?.id])}" >
            <g:if test="${pageAccueilInstance?.pageOffre?.photo}">
                <img src="${createLink(controller: 'pageUtilisateur', action: 'imagePage',params:[id:pageAccueilInstance?.pageOffre?.id])}" height="100" alt=""/>
            </g:if>
        </a>

        <div style="display: inline-block;padding-top:5px;">
            <p style="padding-bottom:1px;"> <strong>${pageAccueilInstance?.pageOffre?.nom}</strong> </p>


            <a  href="${createLink(controller: 'pageUtilisateur', action: 'showPublic',params:[id:pageAccueilInstance?.pageOffre?.id])}" >
                ${pageAccueilInstance?.pageOffre?.contenu?.ellipsify()}
            </a>
        </div>

    </div> <!-- end of col-sm-8 col-md-8 tag-->
    <div class="col-sm-3 col-md-3">
        <a  href="${createLink(controller: 'pageUtilisateur', action: 'showPublic',params:[id:pageAccueilInstance?.pageStock?.id])}" >
            <g:if test="${pageAccueilInstance?.pageStock?.photo}">
                <img src="${createLink(controller: 'pageUtilisateur', action: 'imagePage',params:[id:pageAccueilInstance?.pageStock?.id])}" height="100" alt=""/>
            </g:if>
        </a>

        <div style="display: inline-block;padding-top:5px;">
            <p style="padding-bottom:1px;"> <strong>${pageAccueilInstance?.pageStock?.nom}</strong> </p>
             <a  href="${createLink(controller: 'pageUtilisateur', action: 'showPublic',params:[id:pageAccueilInstance?.pageStock?.id])}" >
                ${pageAccueilInstance?.pageStock?.contenu?.ellipsify()}
            </a>
        </div>

    </div> <!-- end of col-sm-8 col-md-8 tag-->
    <div class="col-sm-3 col-md-3">
        <a  href="${createLink(controller: 'pageUtilisateur', action: 'showPublic',params:[id:pageAccueilInstance?.pageReseau?.id])}">
            <g:if test="${pageAccueilInstance?.pageReseau?.photo}">
                <img src="${createLink(controller: 'pageUtilisateur', action: 'imagePage',params:[id:pageAccueilInstance?.pageReseau?.id])}" height="100" alt=""/>
            </g:if>
        </a>

        <div style="display: inline-block;padding-top:5px;">
            <p style="padding-bottom:1px;"> <strong>${pageAccueilInstance?.pageReseau?.nom}</strong> </p>


                <a  href="${createLink(controller: 'pageUtilisateur', action: 'showPublic',params:[id:pageAccueilInstance?.pageReseau?.id])}">
                    ${pageAccueilInstance?.pageReseau?.contenu?.ellipsify()}
                </a>
        </div>
    </div> <!-- end of col-sm-8 col-md-8 tag-->
  </div>
<div class="row" >
<hr class="myhrClass" />
</div>

<div class="row" >
    <div class="col-sm-5 col-md-5 classActualite ">
        <h3>${g.message(code:"actualites.text", default:"Actualités")} </h3>

    </div>
    <div class="col-sm-1 col-md-1 " style="padding-left: 0;padding-right: 0;width:2px;">

    </div>
    <div class="col-sm-5 col-md-5 classDocumentation">
        <h3> ${g.message(code:"documentation.text", default:"Documentation")}</h3>

    </div>
</div>
<div class="row" >
    <div class="col-sm-5 col-md-5 ">
        <br>
        <g:each status="i" var="info" in="${topNInfos}">
           <div class="docContent">
                <p> <span style="font-size:11px;">${info?.date} </span>
                    <g:set var="infoURL" value="${info?.url?:g.createLink(controller:"info", action:"showPublic", id:info.id )}" />
                    %{--<g:formatDate date="${info?.date}"/>--}%
                    <a  href="${infoURL}"  target="_blank">
                        ${info?.titre}
                    </a>
                  %{--<a  href="${g.createLink(controller:"info", action:"showPublic", id:info.id )}"  target="_blank">--}%
                        %{--${info?.titre}--}%
                    %{--</a>--}%
                </p>
                    %{--<span><prettytime:display date="${info?.date}"/></span>--}%
           </div>
        </g:each>
    </div>
    <div class="col-sm-1 col-md-1 " style="padding-left: 0;padding-right: 0;width:2px;">

    </div>
    <div class="col-sm-5 col-md-5 " id="documents">

        <g:render template="documents" model="['topNDocuments':topNDocuments]"/>

            %{--<a href="${g.createLink(controller:"s3Asset", action:"showConsulter", id:document.id)}" target="_blank">--}%
                %{--<p class="docContent"><span class="pdf_class"></span>${document?.title}</p>--}%
            %{--</a>--}%
        %{--</g:each>--}%
    </div>
</div>