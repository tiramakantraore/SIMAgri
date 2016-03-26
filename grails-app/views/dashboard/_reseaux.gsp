<%@ page import="simagri.Reseau" %>
<div class="tab-pane" id="statsReseaux">
        <div class="contentbox">
        <g:render template="/home/reseauxList"/>
            <div class="pagination">
                <util:remotePaginate update="listContent" action="list"  total="${reseauxList?.size()?:0}" controller="home" action="filterReseaux"
                                     update="reseauxList" max="5" alwaysShowPageSizes="true" pageSizes="[5, 10,15,20,100]"/>

            </div>
        </div>
</div>


