<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>File Search Engine</title>
</head>
<body>
<h1>File Search Engine</h1>
<br/>
<form action="search-servlet" target="_blank">
    <label for="#fileName">
        File Name -
        <input id="#fileName" name="fileName" required placeholder="Enter here" />
    </label>
    <br />
    <label for="#depth">
        Max Depth to Search -
        <input id="#depth" name="depth" placeholder="Recursive search (default=4)" />
    </label>
    <br />
    <label for="#includeFolders">
        Include Folders
        <input id="#includeFolders" type = "checkbox" checked="checked" name = "includeFolders" />
    </label>
    <br />
    <label>

    </label>
    <button>Search</button>
</form>
</body>
</html>