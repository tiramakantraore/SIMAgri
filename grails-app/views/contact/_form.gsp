<%@ page import="simagri.Contact" %>

<f:with bean="contactInstance">
    <f:field property="nom"/>
    <f:field property="email"/>
    <f:field property="telephone"/>
    <f:field property="sujet"/>
    <f:field property="message"/>
    <f:field property="publier"/>
</f:with>

