<%@ page import="simagri.Utilisateur" %>


            <div class="well small" style="min-height:250px">
                <g:message code="choisir.reseau"/>
                <div  class="k-content" >
                    <div id="treeviewSMS" class="groupe-section"></div>
                    <div id="result" class="groupe-section"></div>

                    %{--<div class="groupe-section">--}%
                        %{--<div class="console"></div>--}%
                    %{--</div>--}%


                </div>
            </div>
			
			<div class="col-sm-6 col-md-6 offset-1 workarea">


					<g:form name="sendSMSForm" class="well small form-horizontal" >
                        <g:hiddenField name="ReseauxIdsSMS" />
                            <div class="row">
                                <div class="col-sm-4 col-md-4 offset-8">
                                    <label for="destinatairesSMS">
                                        <g:message code="alerte.destinataires.label" default="Destinataires" />
                                    </label>
                                    <g:select id="destinatairesSMS" name="destinatairesSMS" from="${Utilisateur.findAllById(0 as Long)}" multiple="multiple" optionKey="id" size="5" value="${smsToReseauxInstance?.destinataires*.id}" class="many-to-many col-sm-6 col-md-6"/>

                                </div>
                            </div>

                            <br>
                            <div class="row">

                                <div class="fieldcontain ${hasErrors(bean: smsToReseauxInstance, field: 'yourTextMessage', 'error')} required col-sm-12 col-md-12">

                                    <div id="infodiv"></div>
                                    <g:textArea name="yourTextMessage" id="yourTextMessage" cols="50" rows="10" maxlength="2000" class="col-sm-12 col-md-12" required="" value="${smsToReseauxInstance?.yourTextMessage}" placeHolder="Votre message ici (maximum 1600 caractères)"/>
                                </div>
                            </div>
                           %{--<g:hiddenField name="ReseauxIdsSMS" />--}%
					</g:form>
                    <div class="btn-flat  btn-primary validerSMS" onclick="updateReseauxIds();">

                        <g:message code="sendSMS.button.label" default="Envoyer" />
                    </div>

				
			</div>

