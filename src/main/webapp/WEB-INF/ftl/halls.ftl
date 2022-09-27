<div id="header">
    <h2>Halls</h2>
</div>
<div id="content">
    <fieldset>
        <legend>Create hall</legend>
        <form name="car" action="/admin/panel/halls/add" method="post">
            Number of seats: <input type="text" name="numberOfSeats"/><br/>
            <input type="submit" value="Create"/>
        </form>
    </fieldset>
    <br/>
    <table class="datatable">
        <tr>
            <th>ID</th>
            <th>Number of seats</th>
        </tr>
        <#list halls as hall>
            <tr>
                <td>${hall.serialNumber}</td>
                <td>${hall.numberOfSeats}</td>
            </tr>
        </#list>
    </table>
</div>