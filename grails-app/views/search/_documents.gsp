<br>
<g:each status="i" var="document" in="${topNDocuments}">
    <a href="${g.createLink(controller:"s3Asset", action:"showConsulter", id:document.id)}" target="_blank">
        <p class="docContent"><span class="${(document?.mimeType=='application/pdf')?'pdf_class':((document?.mimeType=='application/vnd.ms-excel')?'csv_class':((document?.mimeType=='application/rtf')?'rtf_class':'image_class'))}"></span>${document?.title}</p>
    </a>
</g:each>

