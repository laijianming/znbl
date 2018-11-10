/**
 * 常规js验证类。
 */


//jquery validate 身份证号码验证
jQuery.validator.addMethod("isIdcard", function(value, element) {
	return this.optional(element) || isIdentityCodeValid(value);
}, "请正确输入您的身份证号码");


//jquery validate 日期验证
jQuery.validator.addMethod("isDate", function(value, element) {
	return this.optional(element) || isDate(value);
}, "请正确输入日期格式");

//jquery validate 验证
jQuery.validator.addMethod("isPhone", function(value, element) {
	return this.optional(element) || checkPhone(value);
}, "请正确输入电话号码格式");

//jquery validate 手机号码验证
jQuery.validator.addMethod("isMobile", function(value, element) {
	return this.optional(element) || checkMobile(value);
}, "请正确输入电话号码格式");

//jquery validate 日期验证
jQuery.validator.addMethod("isMoney", function(value, element) {
	return this.optional(element) || isMoney(value);
}, "请正确输入金额格式");

//字符验证
jQuery.validator.addMethod("stringCheck", function(value, element) {
	return this.optional(element) || /^[\u4e00-\u9fa5_a-zA-Z0-9]+$/.test(value);
},"只能包括中文字、英文字母、数字和下划线");

//jquery validate 大于，param是比较的ID
jQuery.validator.addMethod("moreThan", function(value, element, param) {
	if(Number(value)>Number($("#"+param).val())||Number(value)==Number($("#"+param).val())){
		//$("#"+param).trigger("keyup");
		return true;
	}
	return false;
}, $.validator.format("输入值必须大于或者等于起始值!"));

//jquery validate 小于，param是比较的ID
jQuery.validator.addMethod("lessThan", function(value, element, param) {
	if(Number(value)<Number($("#"+param).val())||Number(value)==Number($("#"+param).val())){
	 // $("#"+param).trigger("keyup");
	  return true;
	}else return false;
	 //return value < $("#"+param).val();
}, $.validator.format("输入值必须小于或者等于截止值!"));

//必须以特定字符串开头验证
jQuery.validator.addMethod("begin", function(value, element, param) {
	var begin = new RegExp("^" + param);
	return this.optional(element) || (begin.test(value));
}, $.validator.format("必须以 {0} 开头!"));

// 验证两次输入值是否不相同
jQuery.validator.addMethod("notEqualTo", function(value, element, param) {
	return value != $(param).val();
}, $.validator.format("两次输入不能相同!"));

// 验证值不允许与特定值等于
jQuery.validator.addMethod("notEqual", function(value, element, param) {
	return value != param;
}, $.validator.format("输入值不允许为{0}!"));

	
/**
 * 验证是否邮箱
 * 成功返回true,验证不通过返回false.
 */
function checkEmail(strEmail) {
	//var emailReg = /^[_a-z0-9]+@([_a-z0-9]+\.)+[a-z0-9]{2,3}$/; 
	var emailReg = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
	if (emailReg.test(strEmail)) {
		return true;
	} else {
		return false;
	}
};

/*
 用途：校验ip地址的格式 
 输入：strIP：ip地址 
 返回：如果通过验证返回true,否则返回false； 
 */
function isIP(strIP) {
	if (isNull(strIP)) {
		return false;
	}
	var re = /^(\d+)\.(\d+)\.(\d+)\.(\d+)$/g //匹配IP地址的正则表达式 
	if (re.test(strIP)) {
		if (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256
				&& RegExp.$4 < 256) {
			return true;
		}
	}
	return false;
};

/* 
 用途：检查输入手机号码是否正确 
 输入：strMobile：字符串 
 返回：如果通过验证返回true,否则返回false 
 */
function checkMobile(strMobile) {
	var regu = /^[1][0-9][0-9]{9}$/;///^(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
	var re = new RegExp(regu);
	if (re.test(strMobile)) {
		return true;
	} else {
		return false;
	}
};

/* 
 用途：检查输入的电话号码格式是否正确 
 输入：strPhone：字符串 
 返回：如果通过验证返回true,否则返回false 
 */
function checkPhone(strPhone) {
	//var phoneRegWithArea = /^[0][1-9]{2,3}-[0-9]{5,10}$/;
	var phoneRegNoArea = /^[1-9]{1}[0-9]{5,8}$/;
	var phoneRegWithArea =/^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
	if (strPhone.length > 9) {
		if (phoneRegWithArea.test(strPhone)) {
			return true;
		} else {
			return false;
		}
	} else {
		if (phoneRegNoArea.test(strPhone)) {
			return true;
		} else {
			return false;
		}
	}
};

/* 
 用途：检查输入字符串是否为空或者全部都是空格 
 输入：str 
 返回：如果全是空返回true,否则返回false 
 */
function isNull(str) {
	if (str == "") {
		return true;
	}
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	return re.test(str);
};

/* 
 用途：检查输入对象的值是否符合整数格式 
 输入：str 输入的字符串 
 返回：如果通过验证返回true,否则返回false 
 */
function isInteger(str) {
	var regu = /^[-]{0,1}[0-9]{1,}$/;
	return regu.test(str);
};

/* 
 用途：检查输入字符串是否符合正整数格式 
 输入：s：字符串 
 返回：如果通过验证返回true,否则返回false 
 */
function isNumber(s) {
	var regu = "^[0-9]+$";
	var re = new RegExp(regu);
	if (s.search(re) != -1) {
		return true;
	} else {
		return false;
	}
};

/* 
 用途：检查输入字符串是否是带小数的数字格式,可以是负数 
 输入：str：字符串 
 返回：如果通过验证返回true,否则返回false 
 */
function isDecimal(str) {
	if (isInteger(str)) {
		return true;
	}
	var re = /^[-]{0,1}(\d+)[\.]+(\d+)$/;
	if (re.test(str)) {
		if (RegExp.$1 == 0 && RegExp.$2 == 0) {
			return false;
		}
		return true;
	} else {
		return false;
	}
};

/* 
 用途：检查输入对象的值是否符合端口号格式 
 输入：str 输入的字符串 
 返回：如果通过验证返回true,否则返回false 
 */
function isPort(str) {
	return (isNumber(str) && str < 65536);
};

/* 
 用途：检查输入字符串是否符合金额格式,格式定义为带小数的正数，小数点后最多三位 
 输入：s：字符串 
 返回：如果通过验证返回true,否则返回false 
 */
function isMoney(s) {
	var regu = "^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$";
	var re = new RegExp(regu);
	if (re.test(s)) {
		return true;
	} else {
		return false;
	}
};


/* 
 用途：检查输入字符串是否只由英文字母和数字组成 
 输入：s：字符串 
 返回：如果通过验证返回true,否则返回false 
 */
function isNumberOrLetter(s) {
	//判断是否是数字或字母 
	var regu = "^[0-9a-zA-Z]+$";
	var re = new RegExp(regu);
	if (re.test(s)) {
		return true;
	} else {
		return false;
	}
};

/* 
 用途：检查输入字符串是否只由汉字、字母、数字组成 
 输入：s：字符串 
 返回：如果通过验证返回true,否则返回false 
 */
function isChinaOrNumbOrLett(s) {
	//判断是否是汉字、字母、数字组成 
	var regu = "^[0-9a-zA-Z\u4e00-\u9fa5]+$";
	var re = new RegExp(regu);
	if (re.test(s)) {
		return true;
	} else {
		return false;
	}
};

/* 
 用途：判断是否是日期 
 输入：date：日期；fmt：日期格式 
 返回：如果通过验证返回true,否则返回false 
 */
function isDate(date, fmt) {
	if (fmt == null) {
		fmt = "yyyyMMdd";
	}
	var yIndex = fmt.indexOf("yyyy");
	if (yIndex == -1) {
		return false;
	}
	var year = date.substring(yIndex, yIndex + 4);
	var mIndex = fmt.indexOf("MM");
	if (mIndex == -1) {
		return false;
	}
	var month = date.substring(mIndex, mIndex + 2);
	var dIndex = fmt.indexOf("dd");
	if (dIndex == -1) {
		return false;
	}
	var day = date.substring(dIndex, dIndex + 2);
	if (!isNumber(year) || year > "2100" || year < "1900") {
		return false;
	}
	if (!isNumber(month) || month > "12" || month < "01") {
		return false;
	}
	if (day > getMaxDay(year, month) || day < "01") {
		return false;
	}
	return true;
};
function getMaxDay(year, month) {
	if (month == 4 || month == 6 || month == 9 || month == 11) {
		return "30";
	}
	if (month == 2) {
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			return "29";
		} else {
			return "28";
		}
		return "31";
		;
	}
};

/* 
 用途：字符1是否以字符串2结束 
 输入：str1：字符串；str2：被包含的字符串 
 返回：如果通过验证返回true,否则返回false 
 */
function isLastMatch(str1, str2) {
	var index = str1.lastIndexOf(str2);
	if (str1.length == index + str2.length) {
		return true;
	}
	return false;
};

/* 
 用途：字符1是否以字符串2开始 
 输入：str1：字符串；str2：被包含的字符串 
 返回：如果通过验证返回true,否则返回false 
 */
function isFirstMatch(str1, str2) {
	var index = str1.indexOf(str2);
	if (index == 0) {
		return true;
	}
	return false;
};

/* 
 用途：字符1是包含字符串2 
 输入：str1：字符串；str2：被包含的字符串 
 返回：如果通过验证返回true,否则返回false 
 */
function isMatch(str1, str2) {
	var index = str1.indexOf(str2);
	if (index == -1) {
		return false;
	}
	return true;
};

/* 
 用途：检查输入的起止日期是否正确，规则为两个日期的格式正确，且结束如期>=起始日期 
 输入：startDate：起始日期，字符串; endDate：结束如期，字符串 
 返回：如果通过验证返回true,否则返回false 
 */
function checkTwoDate(startDate, endDate) {
	if (!isDate(startDate)) {
		alert("起始日期不正确!");
		return false;
	} else if (!isDate(endDate)) {
		alert("终止日期不正确!");
		return false;
	} else if (startDate > endDate) {
		alert("起始日期不能大于终止日期!");
		return false;
	}
	return true;
};

/* 
 用途：检查复选框被选中的数目 
 输入：checkboxID：字符串 
 返回：返回该复选框中被选中的数目 
 */
function checkSelect(checkboxID) {
	var check = 0;
	var i = 0;
	if (document.all(checkboxID).length > 0) {
		for (i = 0; i < document.all(checkboxID).length; i++) {
			if (document.all(checkboxID).item(i).checked) {
				check += 1;
			}
		}
	} else {
		if (document.all(checkboxID).checked) {
			check = 1;
		}
	}
	return check;
}
function getTotalBytes(varField) {
	if (varField == null) {
		return -1;
	}
	var totalCount = 0;
	for (i = 0; i < varField.value.length; i++) {
		if (varField.value.charCodeAt(i) > 127) {
			totalCount += 2;
		} else {
			totalCount++;
		}
	}
	return totalCount;
}
function getFirstSelectedValue(checkboxID) {
	var value = null;
	var i = 0;
	if (document.all(checkboxID).length > 0) {
		for (i = 0; i < document.all(checkboxID).length; i++) {
			if (document.all(checkboxID).item(i).checked) {
				value = document.all(checkboxID).item(i).value;
				break;
			}
		}
	} else {
		if (document.all(checkboxID).checked) {
			value = document.all(checkboxID).value;
		}
	}
	return value;
}
function getFirstSelectedIndex(checkboxID) {
	var value = -2;
	var i = 0;
	if (document.all(checkboxID).length > 0) {
		for (i = 0; i < document.all(checkboxID).length; i++) {
			if (document.all(checkboxID).item(i).checked) {
				value = i;
				break;
			}
		}
	} else {
		if (document.all(checkboxID).checked) {
			value = -1;
		}
	}
	return value;
}
function selectAll(checkboxID, status) {
	if (document.all(checkboxID) == null) {
		return;
	}
	if (document.all(checkboxID).length > 0) {
		for (i = 0; i < document.all(checkboxID).length; i++) {
			document.all(checkboxID).item(i).checked = status;
		}
	} else {
		document.all(checkboxID).checked = status;
	}
}
function selectInverse(checkboxID) {
	if (document.all(checkboxID) == null) {
		return;
	}
	if (document.all(checkboxID).length > 0) {
		for (i = 0; i < document.all(checkboxID).length; i++) {
			document.all(checkboxID).item(i).checked = !document
					.all(checkboxID).item(i).checked;
		}
	} else {
		document.all(checkboxID).checked = !document.all(checkboxID).checked;
	}
}
function checkDate(value) {
	if (value == '') {
		return true;
	}
	if (value.length != 8 || !isNumber(value)) {
		return false;
	}
	var year = value.substring(0, 4);
	if (year > "2100" || year < "1900") {
		return false;
	}
	var month = value.substring(4, 6);
	if (month > "12" || month < "01") {
		return false;
	}
	var day = value.substring(6, 8);
	if (day > getMaxDay(year, month) || day < "01") {
		return false;
	}
	return true;
};

/* 
 用途：检查输入的起止日期是否正确，规则为两个日期的格式正确或都为空且结束日期>=起始日期 
 输入：startDate：起始日期，字符串; endDate： 结束日期，字符串 
 返回：如果通过验证返回true,否则返回false 
 */
function checkPeriod(startDate, endDate) {
	if (!checkDate(startDate)) {
		alert("起始日期不正确!");
		return false;
	} else if (!checkDate(endDate)) {
		alert("终止日期不正确!");
		return false;
	} else if (startDate > endDate) {
		alert("起始日期不能大于终止日期!");
		return false;
	}
	return true;
};

/* 
 用途：检查证券代码是否正确 
 输入：secCode:证券代码 
 返回：如果通过验证返回true,否则返回false 
 */
function checkSecCode(secCode) {
	if (secCode.length != 6) {
		return false;
	}
	if (!isNumber(secCode)) {
		return false;
	}
	return true;
};

/*
 function:cTrim(sInputString,iType) 
 description:字符串去空格的函数 
 parameters:iType：1=去掉字符串左边的空格;2=去掉字符串左边的空格;0=去掉字符串左边和右边的空格 
 return value:去掉空格的字符串 
 */
function cTrim(sInputString, iType) {
	var sTmpStr = ' ';
	var i = -1;
	if (iType == 0 || iType == 1) {
		while (sTmpStr == ' ') {
			++i;
			sTmpStr = sInputString.substr(i, 1);
		}
		sInputString = sInputString.substring(i);
	}
	if (iType == 0 || iType == 2) {
		sTmpStr = ' ';
		i = sInputString.length;
		while (sTmpStr == ' ') {
			--i;
			sTmpStr = sInputString.substr(i, 1);
		}
		sInputString = sInputString.substring(0, i + 1);
	}
	return sInputString;
};

//身份证号合法性验证 
//支持15位和18位身份证号
//支持地址编码、出生日期、校验位验证
function isIdentityCodeValid(code) { 
          var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
          var tip = "";
          var pass= true;
          
          if(!code || !/^[1-9]\d{5}((1[89]|20)\d{2})(0[1-9]|1[0-2])(0[1-9]|[12]\d|3[01])\d{3}[\dx]$/i.test(code)){
              tip = "身份证号格式错误";
              pass = false;
          }
          
         else if(!city[code.substr(0,2)]){
              tip = "地址编码错误";
              pass = false;
          }
          else{
              //18位身份证需要验证最后一位校验位
              if(code.length == 18){
                  code = code.split('');
                  //∑(ai×Wi)(mod 11)
                  //加权因子
                  var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
                  //校验位
                  var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
                  var sum = 0;
                  var ai = 0;
                  var wi = 0;
                  for (var i = 0; i < 17; i++)
                  {
                      ai = code[i];
                      wi = factor[i];
                      sum += ai * wi;
                  }
                  var last = parity[sum % 11];
                  if(parity[sum % 11] != code[17]){
                      tip = "校验位错误";
                      pass =false;
                  }
              }
          }
          //if(!pass) alert(tip);
          return pass;
      }