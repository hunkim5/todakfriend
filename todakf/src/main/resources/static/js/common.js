function sido(){
	$.ajax({
		async:false,
        url: "/common/sido",
        type: "POST",
        data: '',
        success: function(res) {
            for(i=0; i < res.length; i++){
				console.log(res[i].sido);
				$('#sido').append("<option value='"+res[i].sido+"'>"+res[i].sido+"</option>");
            }
        },
        error: function(response) {
            alert(response.responseText);
        }
    });
}
function partner(){
	$.ajax({
		async:false,
        url: "/common/partner",
        type: "POST",
        data: '',
        success: function(res) {
            for(i=0; i < res.length; i++){
				console.log(res[i].sido);
				$('#partner').append("<option value='"+res[i].partner_seq+"'>"+res[i].partner_name+"</option>");
            }
        },
        error: function(response) {
            alert(response.responseText);
        }
    });
}