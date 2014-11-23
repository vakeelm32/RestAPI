<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Rest Example: Post</title>
	
	<script src="js/jquery-min.js"></script>
	<script src="js/polyfill.js"></script>
	<script src="js/post.js"></script>
</head>
<body>

<h1>API Post Example</h1>

<form id="post_example" name="post_example" action="#">
	<table>
			<tr>
				<td>NAME :</td>
				<td><input type="text" name="name" id="name" maxlength="50" ></td>
			</tr>
			<!-- Made a change to PC_PARTS_CODE input after episode 6, input should ONLY numeric -->
			<tr>
				<td>INTERESTE :</td>
				<td><input type="text" name="interest" id="interest" maxlength="10" value="reading"></td>
			</tr>
			<tr>
				<td>DEVELOPER :</td>
				<td><input type="text" name="developer" id="developer" maxlength="50" value="ATG"></td>
			</tr>
			<tr>
				<td>CTC :</td>
				<td><input type="text" name="ctc" id="ctc" maxlength="5" value="25000"></td>
			</tr>
			<tr>
				<td>HOMETOWN :</td>
				<td><input type="text" name="hometown" id="hometown" maxlength="100" value="Delhi"></td>
			</tr>
			<tr>
				<td>COMPANY :</td>
				<td><input type="text" name="company" id=company maxlength="100" value="Nvizion"></td>
			</tr>
			<tr>
				<td>POSITION :</td>
				<td><input type="text" name="postion" id="postion" maxlength="100" ></td>
			</tr>
			<tr>
				<td>TECHNOLOGY :</td>
				<td><input type="text" name="technology" id="technology" maxlength="100" ></td>
			</tr>
			<tr>
				<td>PHONENUMBER :</td>
				<td><input type="text" name="phonenumber" id="phonenumber" maxlength="100" ></td>
			</tr>
			
			<!-- <tr>
				<td></td>
				<td><input type="button" name="submit_it" id="submit_it" value="Submit"> Using Jackson Mapper</td>
			</tr> -->
			
			<tr>
				<td></td>
				<td><input type="button" name="submit_it2" id="submit_it2" value="Submit v2"> Using JSON Array and Object</td>
			</tr>
	</table>
</form>

<br />
<div id="div_ajaxResponse"></div>

</body>
</html>