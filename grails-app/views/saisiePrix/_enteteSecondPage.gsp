<%@ page import="simagri.Marche" %>
<div class="row">

    <dl>

        <g:if test="${marche}">
            <dt><g:message code="price.market.label" default="Nom marché" /> : <g:fieldValue bean="${marche}" field="nom"/></dt>


        </g:if>

        <g:if test="${priceInstance?.date}">
            <dt><g:message code="price.date.label" default="Date" /> :
                <g:fieldValue bean="${priceInstance}" field="date"/></dt>


        </g:if>

    </dl>
    %{----}%
%{--<div class="fieldcontain ${hasErrors(bean: priceInstance, field: 'market', 'error')} col-sm-12 col-md-12">--}%
    %{--<label for="market">--}%
        %{--<g:message code="price.market.label" default="Marche" />--}%

    %{--</label>--}%
    %{--${priceInstance?.market?.nom}--}%
    %{--<g:select id="market" name="market.id" from="${Marche.list()}" optionKey="id"  value="${priceInstance?.market?.id}" noSelection="['':'']" readonly="true"/>--}%
%{--</div>--}%

%{--<div class="fieldcontain ${hasErrors(bean: priceInstance, field: 'date', 'error')} col-sm-12 col-md-12">--}%
    %{--<label for="date">--}%
        %{--<g:message code="price.date.label" default="Date" />--}%
    %{--</label>--}%
    %{--<gDate:jqDatePicker name="date" choiceYear="true" choiceMonth="true" value="${priceInstance?.date?.format('dd/MM/yyyy')?:new Date().format('dd/MM/yyyy')}" readonly="true"/>--}%
%{--</div>--}%
%{--</div>--}%
%{--</div>--}%
<br>
