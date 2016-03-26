<p class="titre">Groupement</p>
<div class="my1Columns radioboxbg">

    <g:each in="${groupes}" var="groupe"  status="i">
        <li class="unliststyle">
            <g:radio id="${groupe}_${i}" name="groupe" value="${groupe}" checked="${groupe == "Annee"}" class="myRadiostyle" onclick="ongroupeclick();"/>
            <label for="${groupe}_${i}" class="labelClass" data-toggle="tooltip"  title="${groupe}">${groupe}</label>
        </li>
    </g:each>
</div>