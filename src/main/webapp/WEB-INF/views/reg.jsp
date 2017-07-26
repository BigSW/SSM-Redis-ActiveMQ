<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>demo</title>
</head>
<script type="text/javascript" src="recource/js/jquery.js"></script>
<script language="javascript">
	function get_mobile_code(){
       /*  $.post('sms.jsp', {mobile:jQuery.trim($('#mobile').val())}, function(msg) {
            alert(jQuery.trim(unescape(msg)));
			if(msg=='success'){
				RemainTime();
			}
        }); */
        var phone =$('#mobile').val();
		$.ajax({
			url:"user/checkCode",
			data:{
				phone:phone
			},
			success : function(data) {
				if(data.status=="success"){
					RemainTime();
				}
			}
		});
	};
	var iTime = 59;
	var Account;
	function RemainTime(){
		document.getElementById('zphone').disabled = true;
		var iSecond,sSecond="",sTime="";
		if (iTime >= 0){
			iSecond = parseInt(iTime%60);
			iMinute = parseInt(iTime/60)
			if (iSecond >= 0){
				if(iMinute>0){
					sSecond = iMinute + "mm:" + iSecond + "ss";
				}else{
					sSecond = iSecond + "ss";
				}
			}
			sTime=sSecond;
			if(iTime==0){
				clearTimeout(Account);
				sTime='get phone yanzhenma';
				iTime = 59;
				document.getElementById('zphone').disabled = false;
			}else{
				Account = setTimeout("RemainTime()",1000);
				iTime=iTime-1;
			}
		}else{
			sTime='no dao jishi';
		}
		document.getElementById('zphone').value = sTime;
	}
</script>
<body>
<form action="reg.jsp" method="post" name="formUser" onSubmit="return register();">
	<table width="100%"  border="0" align="left" cellpadding="5" cellspacing="3">
		<tr>
			<td align="right">phone<td>
		<input id="mobile" name="mobile" type="text" size="25" class="inputBg" /><span style="color:#FF0000"> *</span> 
        <input id="zphone" type="button" value="send code" onClick="get_mobile_code();"></td>
        </tr>
		<tr>
			<td align="right">code</td>
			<td><input type="text" size="8" name="mobile_code" class="inputBg" /></td>
		</tr>
	</table>
</form>
</body>
</html>