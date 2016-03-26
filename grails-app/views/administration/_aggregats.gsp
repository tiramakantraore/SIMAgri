<p class="titre">Types Aggrégats</p>
<div class="my1Columns radioboxbg">

    <g:each in="${aggregats}" var="aggregat"  status="i">
        <li class="unliststyle">
            <g:radio id="${aggregat}_${i}" name="aggregat" value="${aggregat}" checked="${aggregat == "Prix"}" class="myRadiostyle" onclick="ongroupeclick();"/>
            <label for="${aggregat}_${i}" class="labelClass" data-toggle="tooltip"  title="${aggregat}">${aggregat}</label>
        </li>
    </g:each>
</div>