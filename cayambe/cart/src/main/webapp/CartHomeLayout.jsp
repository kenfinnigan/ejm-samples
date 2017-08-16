<%@ taglib uri='/WEB-INF/tiles.tld' prefix='tiles' %>

<html>
<head>
<title><tiles:getAsString name="Title"/></title>
<style>
a:link {font-size: 10pt; text-decoration: underline; color: #003366}
A:visited {font-size: 10pt; font-weight:bold text-decoration: underline; color: #003366}
a:active {font-size: 10pt; font-weight:bold text-decoration: underline; color: #003366}
a:hover {font-size: 10pt; font-weight:bold text-decoration: underline; color: #225588}

body
{
font-size: 10pt;
font-weight:bold;
text-decoration: none;
color: #003366;
}
</style>
</head>
<body>
<table width="100%" cellspacing="2" cellpadding="2" border="0">
	<tr>
	    <td colspan="2"><tiles:insert attribute='Header'/></td>
	</tr>
	<tr>
	    <td width="20%"  valign="top"><tiles:insert attribute='Navigation'/></td>
	    <td width="80%" valign="top"><tiles:insert attribute='Hierarchy'/><hr noshade><tiles:insert attribute='Body'/></td>
	</tr>
	<tr>
	    <td colspan="2"><tiles:insert attribute='Footer'/></td>
	</tr>
</table>
</body>
</html>
