<!DOCTYPE html>
<html>
	<head>
		<title>Fixed and Saving Deposit Rates</title>
		<link href="${rc.getContextPath()}/resources/css/cssrest.css" rel="stylesheet" type="text/css" />
		<link href="${rc.getContextPath()}/resources/css/projectStyle.css" rel="stylesheet" type="text/css" />
		<script src="${rc.getContextPath()}/resources/js/jquery-2.1.1.min.js"></script>
		<!-- using Jquery UI -->
		<link href="${rc.getContextPath()}/resources/css/plugin/jquery-ui.min.1.11.2.css" rel="stylesheet" type="text/css" />
		<script src="${rc.getContextPath()}/resources/js/plugin/jquery-ui.min.1.11.2.js"></script>	
		<!-- Custom JS for this page -->
		<script src="${rc.getContextPath()}/resources/js/mas.js"></script>	
	</head>
	<body>
		<div id="container">
			<div id="header">
				<h1>Fixed &amp; Saving Deposit Rates Comparision</h1>				
			</div>
			<div id="content" class="singleColumn">
				<div id="searchSection">
					<p>Enter the Date Range</p>
					<p class="smallerFont">(Enter date in format <span class="italicFont">YYYY-MM</span>, only support a max range of <span class="boldFont">96months</span> (8 years))</p>
					<form id="submitRequest" name="searchRequest" action="${rc.getContextPath()}/search" method="post">
						<ul>
							<li>
								<label for="fromDate">From<span class="mandatory">*</span></label>
								<input type="text" name="fromDate" id="fromDate" class="monthPicker" value="${(FromMonth)!}">
							</li>
							<li>
								<label for="toDate">To<span class="mandatory">*</span></label>
								<input type="text" name="toDate" id="toDate" class="monthPicker" value="${(ToMonth)!}">
							</li>
						</ul>	
						<input type="hidden" name="masJsonResponse" id="masJsonResponse" >
						<input type="submit" value="Retrieve Interest Rate">						
					<#if errorMessage??>
						<p class="errorMessage">${(errorMessage)!}</p>
					<#else>
						<p id="searchError" class="errorMessage hide"></p>
					</#if>	
					</form>					
				</div>
				<div id="overviewSection">
					<h2>Summary<@customMacro.displayFromToMonth /></h2>	
					<h3>Average Rate</h3>
				<#if SummaryAverageModel??>
					<table>
						<!-- Header -->
						<tr>
							<th rowspan="2">&nbsp;</th>
							<th colspan="3">Fixed Deposit</th>
							<th rowspan="2">Saving Deposit</th>
						</tr>						
						<tr>
							<th>3 Months</th>
							<th>6 Months</th>
							<th>12 Months</th>
						</tr>
						<!-- Repeated dataset -->
						<tr>
							<td>Bank</td>
							<td>${(SummaryAverageModel.banks_fixed_deposits_3m)!}</td>
							<td>${(SummaryAverageModel.banks_fixed_deposits_6m)!}</td>
							<td>${(SummaryAverageModel.banks_fixed_deposits_12m)!}</td>
							<td>${(SummaryAverageModel.banks_savings_deposits)!}</td>
						</tr>
						<tr>
							<td>Financial Companies</td>
							<td>${(SummaryAverageModel.fc_fixed_deposits_3m)!}</td>
							<td>${(SummaryAverageModel.fc_fixed_deposits_6m)!}</td>
							<td>${(SummaryAverageModel.fc_fixed_deposits_12m)!}</td>
							<td>${(SummaryAverageModel.fc_savings_deposits)!}</td>
						</tr>
					</table>
				<#else>
					<p>Not Available</p>
				</#if>	
					
					<!-- if single month, dispplay message Not Applicable -->
					<h3>Trend</h3>
				<#if SummaryTrendModel??>
					<table>
						<!-- Header -->
						<tr>
							<th rowspan="2">&nbsp;</th>
							<th colspan="3">Fixed Deposit</th>
							<th rowspan="2">Saving Deposit</th>
						</tr>						
						<tr>
							<th>3 Months</th>
							<th>6 Months</th>
							<th>12 Months</th>
						</tr>
						<!-- 2 dataset -->
						<!-- earliest month -->
						<tr>
							<td>&nbsp;</td>
							<td class="boldFont" colspan="4">${(EarliestModel.end_of_month)!}</td>
						</tr>
						<tr>
							<td>Bank</td>
							<td>${(EarliestModel.banks_fixed_deposits_3m)!}</td>
							<td>${(EarliestModel.banks_fixed_deposits_6m)!}</td>
							<td>${(EarliestModel.banks_fixed_deposits_12m)!}</td>
							<td>${(EarliestModel.banks_savings_deposits)!}</td>
						</tr>
						<tr>
							<td>Financial Companies</td>
							<td>${(EarliestModel.fc_fixed_deposits_3m)!}</td>
							<td>${(EarliestModel.fc_fixed_deposits_6m)!}</td>
							<td>${(EarliestModel.fc_fixed_deposits_12m)!}</td>
							<td>${(EarliestModel.fc_savings_deposits)!}</td>
						</tr>
						<!-- latest month -->
						<tr class="altBackgroundColor">
							<td>&nbsp;</td>
							<td class="boldFont" colspan="4">${(LatestModel.end_of_month)!}</td>
						</tr>
						<tr class="altBackgroundColor">
							<td>Bank</td>
							<td>${(LatestModel.banks_fixed_deposits_3m)!"NA"}</td>
							<td>${(LatestModel.banks_fixed_deposits_6m)!"NA"}</td>
							<td>${(LatestModel.banks_fixed_deposits_12m)!"NA"}</td>
							<td>${(LatestModel.banks_savings_deposits)!"NA"}</td>
						</tr>
						<tr class="altBackgroundColor">
							<td>Financial Companies</td>
							<td>${(LatestModel.fc_fixed_deposits_3m)!"NA"}</td>
							<td>${(LatestModel.fc_fixed_deposits_6m)!"NA"}</td>
							<td>${(LatestModel.fc_fixed_deposits_12m)!"NA"}</td>
							<td>${(LatestModel.fc_savings_deposits)!"NA"}</td>
						</tr>
						<tr class="highlightTable">
							<td class="boldFont" colspan="5">Trend</td>
						</tr>
						<tr>
							<td>Bank</td>
							<@customMacro.displayTrend trendValue="${(SummaryTrendModel.trendBankFixDeposit3Months)!}" />
							<@customMacro.displayTrend trendValue="${(SummaryTrendModel.trendBankFixDeposit6Months)!}" />
							<@customMacro.displayTrend trendValue="${(SummaryTrendModel.trendBankFixDeposit12Months)!}" />
							<@customMacro.displayTrend trendValue="${(SummaryTrendModel.trendBankSavingDeposit)!}" />
						</tr>
						<tr>
							<td>Financial Companies</td>
							<@customMacro.displayTrend trendValue="${(SummaryTrendModel.trendFCFixDeposit3Months)!}" />
							<@customMacro.displayTrend trendValue="${(SummaryTrendModel.trendFCFixDeposit6Months)!}" />
							<@customMacro.displayTrend trendValue="${(SummaryTrendModel.trendFCFixDeposit12Months)!}" />
							<@customMacro.displayTrend trendValue="${(SummaryTrendModel.trendFCSavingDeposit)!}" />
						</tr>
					</table>
				<#else>
					<p>Not Available</p>
				</#if>										
				</div>			
				<div id="detailSection">								
					<h2>Details<@customMacro.displayFromToMonth /></h2>
				<#if DetailModel??>					
					<table class="tableAltColor">
						<!-- Header -->
						<tr>
							<th rowspan="2">&nbsp;</th>
							<th colspan="3">Fixed Deposit</th>
							<th rowspan="2">Saving Deposit</th>
						</tr>						
						<tr>
							<th>3 Months</th>
							<th>6 Months</th>
							<th>12 Months</th>
						</tr>
						<!-- repeated dataset, latest to earliest -->
						<@customMacro.displayDetail />																
					</table>
				<#else>
					<p>No Records Retrieved</p>
				</#if>						
				</div>
			</div>
			<div id="footer">
				<p>&copy;2018 Technical Coding Assignment</p>
			</div>			
		</div>		
	</body>
</html>