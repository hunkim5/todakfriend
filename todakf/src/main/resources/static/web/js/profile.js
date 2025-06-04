
/*
 * profile.js
 * 프로필 보기
 */

$(document).ready(function(){
    var popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'))
    var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
        return new bootstrap.Popover(popoverTriggerEl, {
              template: '<div class="popover" role="tooltip"><div class="popover-arrow"></div><div class="profile_'+$(popoverTriggerEl).attr("memberId")+' border-bottom small p-1"></div><div class="p-2 small"></div></div>'
            , html: true
            , title : 'profile'
            , content : 'profile' 
        })
    })

    /* 닉네임 클릭했을때 프로필 팝업 띄우기 */
    $(".profile_nickName").click(function(e){
        var memberId = $(this).attr("memberId");

        fnSendAjax("/profile/member?memberId="+memberId, null, 'GET', function(data){
            var bCnt = data.profileVo.boardCnt; //게시글 카운트
            var cCnt = data.profileVo.commentCnt; //댓글 카운트
            var gCnt = data.profileVo.bookCnt; //방명록 카운트
            var pCnt = data.profileVo.blackDay; //패널티 카운트
            var mCnt = data.profileVo.memberCnt; //계정 갯수
            var regDate = data.profileVo.regDate;
            var levelCss = data.profileVo.levelCss;
            var level = data.profileVo.level;
            var sex = data.profileVo.sex;
            var titleHtml = "";
            var contentHtml = "";
            if(pCnt > 99998){pCnt = "영구정지"}
            contentHtml += "게시글 : <a href='/profile/board/list?memberId="+memberId+"' class='text-primary me-5'>"+bCnt+"</a>";
            contentHtml += "댓글 : <a href='/profile/comment/list?memberId="+memberId+"' class='text-primary'>"+cCnt+"</a><br/>";
            contentHtml += "방명록 : <a href='/profile/member/main?memberId="+memberId+"' class='text-primary me-5'>"+gCnt+"</a>";
            contentHtml += "사진첩 : <a href='/profile/poto/list?memberId="+memberId+"' class='text-primary'>0</a><br/>";
            //contentHtml += "계정수 : <span class='text-dark me-5'>"+mCnt+"</span>";
            contentHtml += "패널티 : <span class='text-dark me-5'>"+pCnt+"</span>";
            contentHtml += "<span class=''><a href='javascript:messageModal(\""+memberId+"\");' class='text-primary'>대화 하기</a></span><br/>";
            if("T" == levelCss){
                //contentHtml += "이벤트 : 3<br/>";
                contentHtml += "제휴만료 : "+data.profileVo.partnerDate+"일까지<br/>";
            }
            contentHtml += "가입일시 : "+regDate+"<br/>";
            
            
            titleHtml += "<span class='badge lev"+levelCss+"'>"+level+"</span>&nbsp;";
            
            if(sex == 'M'){
                titleHtml += '<span> <i class="bi bi-gender-male text-primary" style="margin-left: -3px; margin-right: 0px;"></i> </span>';
            }else if(sex == 'W'){
                titleHtml += '<span> <i class="bi bi-gender-female text-danger" style="margin-left: -3px; margin-right: 0px;"></i> </span>';
            }else{
                titleHtml += '<span> <i class="bi bi-gender-neuter text-secondary" style="margin-left: -3px; margin-right: 0px;"></i> </span>';
            }
            
            
            titleHtml += '<span class="align-bottom">'+data.profileVo.nickName+'</span>';
            titleHtml += "<i class='bi bi-x-lg float-end' onclick='profile_close(\""+memberId+"\")'></i>";

            $(".profile_"+memberId).html(titleHtml);
            $(".profile_"+memberId).next().html(contentHtml);//프로필 html 넣기
        });

    });
    
});



function messageModal(memberId){
    profile_close(memberId);
    $('#messageModal').modal("show");
    $.ajax({
        url: "/message/content/list?page=1&recvId="+memberId
        , type: "POST"
        , async: true
        , data : null
        , dataType : 'html'
        , contentType : "application/json; charset=UTF-8"
        , success: function(data){
            $("#messageModal").find(".modal-content").html(data)
        }
        , error: function(request, status, error) {
            alert('에러! 고객센터로 문의 바랍니다.');
        }
    })
}



function profile_close(memberId){
    $(".profile_"+memberId).parent().popover('hide');
}