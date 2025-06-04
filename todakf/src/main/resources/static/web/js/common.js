
/*ajax 공통 호출*/
function fnSendAjax(url, data, type, callBack){
	$("#div_load_image").center();
	$("#div_load_image").show();
	$.ajax({
		url: url
		, type: type
		, async: true
		, data : data
		, dataType : 'json'
		, contentType : "application/json; charset=UTF-8"
		, success: function(data){
			callBack(data);
			$("#div_load_image").hide();
		}
		, error: function(request, status, error) {
			alert(request.status + " " + status + " 입니다. 고객센터 문의바람!");
		}
	});
}




var summerNoteEditor = function(){
    /* 썸머노트 에디터 생성*/
    $('#summernote').summernote({
        height: 300,
        minHeight: null,
        maxHeight: null,
        focus: true,
        lang:'ko-KR',
        placeholder: '부적절하거나 불쾌감을 줄 수 있는 컨텐츠는 제재를 받을 수 있습니다.',
        toolbar: [
                  ['groupName', ['list of button']],
                  ['style', ['bold', 'italic', 'underline']],
                  ['color', ['color']],
                  ['fontsize', ['fontsize']],
                  ['para', ['paragraph']],
                  ['Insert', ['picture']],
                  ['Misc', ['fullscreen']]
               ],
        callbacks: {
            onImageUpload: function(files, editor, welEditable) {
              for (var i = files.length - 1; i >= 0; i--) {
                  //서버로 파일업로드(10메가 이하 이미지만 가능)
                  ajaxUpload(files[i]);
              }
            }
          }
    });
}


//파일 업로드 체크
function fileCheck(file){
    var nMaxImageSize = 10*1024*1024;
    var rFilter = /^(image\/bmp|image\/gif|image\/jpg|image\/jpeg|image\/png)$/i;
    if (!rFilter.test(file.type)) {
        alert("이미지파일 (jpg,gif,png,bmp)만 업로드 가능합니다.");
        return false;
    }else if(file.size > nMaxImageSize){
        alert("이미지 용량이 10MB를 초과하여 등록할 수 없습니다.");
        return false;
    }
    return true;
}

$.fn.center = function () {
	this.css('top', Math.max(0,(($(window).height()-$(this).outerHeight())/2) + $(window).scrollTop())+'px');
	this.css('left', Math.max(0,(($(window).width()-$(this).outerWidth())/2) + $(window).scrollLeft())+'px');
	return this;
}

function goUrl(url){
    $("#div_load_image").center();
    $("#div_load_image").show();
    location.href = url;
}

/*앱에서 새로고침 클릭시 발생되는 이벤트*/
function goReload(){
	$("#div_load_image").center();
	$("#div_load_image").show();
	//location.reload();
}

function goBack(){
	$("#div_load_image").center();
	$("#div_load_image").show();
	window.history.back();
}

function goForward(){
	$("#div_load_image").center();
	$("#div_load_image").show();
	window.history.forward();
}


/* 본인인증 팝업창 */
function nice_popup(gubn){
	$("#div_load_image").center();
    $("#div_load_image").show();
    if(submitFlag){
        return false;
    }else{
        $.ajax({
            url: "/auth/authReqSeq?gubn="+gubn
            , type: "POST"
            , data : null
            , dataType : 'html'
            , contentType : "application/json; charset=UTF-8"
            , success: function(data){
                $("#phoneNumber").val("");
                $("#name").val("");
                $("#myAuthPopup").html(data)
                $("#div_load_image").hide();
                submitFlag = false;
                return false;
            }
            , error: function(request, status, error) {
                alert('에러! 고객센터로 문의 바랍니다.');
            }
        });
    }
    submitFlag = true;
}

/*스크립트 날짜 형식 변환*/
function dateFormat(date) {
    let month = date.getMonth() + 1;
    let day = date.getDate();
    let hour = date.getHours();
    let minute = date.getMinutes();
    let second = date.getSeconds();

    month = month >= 10 ? month : '0' + month;
    day = day >= 10 ? day : '0' + day;
    hour = hour >= 10 ? hour : '0' + hour;
    minute = minute >= 10 ? minute : '0' + minute;
    second = second >= 10 ? second : '0' + second;

    return date.getFullYear() + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
}

/*현재 시간 가져오기*/
function getTime(){
	var today = new Date();
	
	var hours = ('0' + today.getHours()).slice(-2); 
	var minutes = ('0' + today.getMinutes()).slice(-2);
	var seconds = ('0' + today.getSeconds()).slice(-2); 
	
	var timeString = hours + ':' + minutes  + ':' + seconds;
	
	return timeString;
	
}

function xssFilter(str) { 
    str = str.replace(/\</g, "&lt;");
    str = str.replace(/\>/g, "&gt;");
    return str; 
}

function isEmpty(value) {
	   !!value?.trim() ?  this.value : "";
	}

function isEmpty(value, str) {
    if (value === null || value === undefined || value === "") {
        return str;
    }else{
        return value;
    }
}


/*뒤로가기 이벤트 제어*/
window.onpageshow = function(event) {
    if ( event.persisted || (window.performance && window.performance.navigation.type == 2)) {
        $("#div_load_image").hide();
    }
}

