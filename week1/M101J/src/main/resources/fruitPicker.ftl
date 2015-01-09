<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Fruit Picker</title>
</head>
<body>
<form method="POST" action="/favorite_fruit">
    <p>What is your favorite fruit?</p>
    <#list fruits as fruit>
        <input type="radio" name="fruit" value="${fruit}">${fruit}</input><br />
    </#list>
    <input type="submit" value="Submit" />
</form>

</body>
</html>