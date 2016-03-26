<div id="deletePopup" style="display: none;">

<g:form action="delete">
    <g:hiddenField name="id" value="${eventInstance.id}" />
    <g:hiddenField name="occurrenceStart" value="${occurrenceStart}" />

    %{--<p>Would you like to delete only this event, or all events in the series?</p>--}%
    <p>Voulez-vous supprimer seulement cet événement, ou tous les événements de la série?</p>
    <table>
        <tbody>
        <tr>
            <td><div type="submit" name="deleteType" value="occurrence">Seulement cet événement</div></td>
            <td>Tous les autres événements restent inchangés</td>
        </tr>

        <tr>
            <td><div type="submit" name="deleteType" value="following">Cet événement et les suivants</div></td>
            <td>Cet événement et les événements suivants seront changés.</td>
        </tr>
        <tr>
            <td><div type="submit" name="deleteType" value="all">Tous les événements de la série</div></td>
            <td>Tous les autres événements de la série restent inchangés.</td>
        </tr>
        </tbody>

    </table>

</g:form>

</div>

