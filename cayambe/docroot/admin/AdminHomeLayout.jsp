<%@ taglib uri='/WEB-INF/tiles.tld' prefix='tiles' %>

<html>
<head>
<title><tiles:getAsString name="Title"/></title>
<LINK HREF="import/admin.css" TYPE="text/css" REL="stylesheet">
</head>
<body>
<table width="100%" cellspacing="2" cellpadding="2" border="0">
	<tr>
	    <td align=center><tiles:insert attribute='Header'/></td>
	</tr>
	<tr>
	    <td><hr noshade><tiles:insert attribute='Body'/><hr noshade></td>
	</tr>
	<tr>
	    <td><tiles:insert attribute='Footer'/></td>
	</tr>
</table>
</body>
</html>
