<%@ taglib uri='/WEB-INF/tiles.tld' prefix='tiles' %>

<table width="100%" cellspacing="2" cellpadding="2" border="0">
	<tr>
	    <td rowspan=2 valign=top width="200">
		   <tiles:insert attribute='CategoryList'/>
		</td>
        <td><tiles:insert attribute='ManageCategoryHeader'/></td>
    </tr>
    <tr>
         <td align=center width="500"><tiles:insert attribute='ManageCategoryBody'/></td>
	</tr>
</table>
