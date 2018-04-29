<#macro displayFromToMonth>
	<#if FromMonth??>from ${FromMonth}</#if> <#if ToMonth??>to ${ToMonth}</#if>
</#macro>

<#macro displayTrend trendValue>
	<#if trendValue == 'Up'>
		<td><span class="upTrend">Up</span></td>	
	<#elseif trendValue == 'Down'>
		<td><span class="downTrend">Down<span></td>
	<#else>
		<td>${trendValue}</td>
	</#if>	
</#macro>

<#macro displayDetail>
	<#list DetailModel as detail>
	  	<tr>
			<td>&nbsp;</td>
			<td class="boldFont" colspan="4">${(detail.end_of_month)!}</td>
		</tr>
		<tr>
			<td>Bank</td>
			<td>${(detail.banks_fixed_deposits_3m)!"NA"}</td>
			<td>${(detail.banks_fixed_deposits_6m)!"NA"}</td>
			<td>${(detail.banks_fixed_deposits_12m)!"NA"}</td>
			<td>${(detail.banks_savings_deposits)!"NA"}</td>
		</tr>
		<tr>
			<td>Financial Companies</td>
			<td <#if detail.whichFixD3MHigher == 'FinancialCompanies'>class="boldFont italicFont"</#if> >${(detail.fc_fixed_deposits_3m)!"NA"}</td>
			<td <#if detail.whichFixD6MHigher == 'FinancialCompanies'>class="boldFont italicFont"</#if> >${(detail.fc_fixed_deposits_6m)!"NA"}</td>
			<td <#if detail.whichFixD12MHigher == 'FinancialCompanies'>class="boldFont italicFont"</#if> >${(detail.fc_fixed_deposits_12m)!"NA"}</td>
			<td <#if detail.whichSaveDHigher == 'FinancialCompanies'>class="boldFont italicFont"</#if> >${(detail.fc_savings_deposits)!"NA"}</td>
		</tr>
    </#list>            
</#macro>