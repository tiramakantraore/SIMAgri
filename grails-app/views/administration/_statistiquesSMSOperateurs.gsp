<div class="contentbox">
        <div class="title">Statistiques par opérateur</div>

    <br>
    <div class="contentStatistiques">
        <ul class="alternate">
            <li>

                <g:if test="${smsMO}">
                    <ul>
                        <li>
                            <strong>SMS Sortants (MO)</strong>
                            <ul class="alternate">

                                <g:each in="${smsMO}" var="g">
                                    <li>${g.operateur} :  <span style="color:green;font-size:1.0em ">
                                        <strong><g:formatNumber number="${g.smsCount}" format="###,##0" /></strong></span>
                                    </li>
                                </g:each>
                            </ul>
                        </li>
                    </ul>
                </g:if>



                <g:if test="${smsMT}">
                    <ul>
                        <li>
                            <strong>SMS Entrants (MT)</strong>
                            <ul style="list-style-type:lower-alpha;">
                                <g:each in="${smsMT}" var="g">
                                    <li>${g.operateur} :  <span style="color:green;font-size:1.0em ">
                                        <strong><g:formatNumber number="${g.smsCount}" format="###,##0" /></strong></span>
                                    </li>
                                </g:each>
                            </ul>
                        </li>
                    </ul>
                </g:if>
            </li>
        </ul>
    </div>
</div>