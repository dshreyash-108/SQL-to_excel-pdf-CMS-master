function goHome(){
	document.getElementById("panel").innerHTML = 'Welcome to Content Management System.';
}
function showMIS(){
	document.getElementById("panel").innerHTML = '<form method="post" action="export">Enter the Duration of MIS<br>From : <input type="date" name="fdate" required><br>To : <input type="date" name="tdate" placeholder="To" required><input type="hidden" name="flag" value="mis"><br><input type="submit" value="Submit"></form>';
}
function showAdnet(){
	document.getElementById("panel").innerHTML = '<form method="post" action="export">Enter the Duration for Adnet<br><input type="text" name="month" placeholder="Enter Month" required><br><input type="text" name="year" placeholder="Enter Year" required><br>Agency : '+window.agency+'<input type="hidden" name="flag" value="adnet"><br><input type="submit" value="Submit"></form>';	
}
function showChurnMgmt(){
	document.getElementById("panel").innerHTML = '<form method="post" action="export">Enter the Date for Churn<br>Date : <input type="date" name="ldate" required><br>Agency : '+window.agency+'<input type="hidden" name="flag" value="churn_mgmt"><br><input type="submit" value="Submit"></form>';
}
function showLogs(){
	document.getElementById("panel").innerHTML = '<form method="post" action="export">Enter the Date of Logs<br>Date : <input type="date" name="ldate" required><br><input type="hidden" name="flag" value="logs"><input type="submit" value="Submit"></form>';
}
function showRenewals(){
	document.getElementById("panel").innerHTML = '<form method="post" action="export">Enter the Duration of Renewals<br>From : <input type="date" name="fdate" placeholder="From" required><br>To : <input type="date" name="tdate" placeholder="To" required><br>Agency : '+window.agency+'<input type="hidden" name="flag" value="renewal_details"><br><input type="submit" value="Submit"></form>';
}
function showActivations(){
	document.getElementById("panel").innerHTML = '<form method="post" action="export">Enter the Date of Activations<br>Date : <input type="date" name="ldate" required><br><input type="hidden" name="flag" value="activation_details"><input type="submit" value="Submit"></form>';	
}
function showCallbacks(){
	document.getElementById("panel").innerHTML = '<form method="post" action="export">Enter the Duration of Callbacks<br>From : <input type="date" name="fdate" placeholder="From" required><br>To : <input type="date" name="tdate" placeholder="To" required><br>Agency : '+window.agency+'<input type="hidden" name="flag" value="callbacks"><br><input type="submit" value="Submit"></form>';
}
function showActiveBase(){
	document.getElementById("panel").innerHTML = '<form method="post" action="export">Enter the Date of Active Base<br>Date : <input type="date" name="ldate" required><br><input type="hidden" name="flag" value="active_base"><input type="submit" value="Submit"></form>';	
}