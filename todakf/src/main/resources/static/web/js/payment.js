/*
 * payment.js
 * 결제
 */
function payappPay(){
    fnSendAjax("/member/payment/popup", null, 'POST', function(data) {
        PayApp.setDefault('userid',     'pc123pc');
        PayApp.setDefault('shopname',   '토닥이닷컴');
        PayApp.setParam('goodname', '토닥이닷컴-유료회원비');
        PayApp.setParam('price',  data.price);
        PayApp.setParam('recvphone', data.mobileNo);
        PayApp.setParam('feedbackurl', '');
        PayApp.setParam('checkretry', 'y');
        PayApp.setParam('returnurl', 'https://todak-2.com/member/payment/success');
        PayApp.setParam('var1', data.memberId);
        PayApp.setParam('smsuse', 'n');
        //PayApp.setParam('openpaytype', 'card','phone','kakaopay','naverpay','smilepay','rbank','applepay','payco');
        PayApp.setParam('redirectpay', '1');
        PayApp.setParam('skip_cstpage', 'y');
        PayApp.payrequest();
    });
}

function receivePaymentStatus(status) {
    if(status === 'success'){
        location.reload();
    }
}