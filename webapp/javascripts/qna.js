
$(".answerWrite input[type=submit]").on('click', addAnswer);

function addAnswer(e) {
	e.preventDefault();
	
	var queryString = $("form[name=answer]").serialize();
	
	$.ajax({
	    type : 'post',
	    url : '/api/qna/addanswer.next',
	    data : queryString,
	    dataType : 'json',
	    error: onError,
	    success : onSuccess
	});
}

function onSuccess(data, status){
	var answer = data.answer;
	var answerEle = "<div class='answer'><b>" + answer.writer + "</b><p>" + answer.contents + "</p>" + 
		"<a class='answerDelete' href='/api/qna/deleteanswer.next?answerId=" + answer.answerId + "'>삭제</a></div>";
    $(".answers").prepend(answerEle);
   
    $("#countofComment> span").text(data.count );
}

function onError(data, status) {
	alert("error");
}

$(".answerDelete").on('click', deleteAnswer);
function deleteAnswer(e){
	e.preventDefault();
	
	var deletebtn = $(this);
	var url = deletebtn.attr("href");
	
	$.ajax({
	    type : 'get',
	    url : url,
	    dataType : 'json',
	    error: function onDeleteError(data,status){
	    	alert("error");
	    },
	    success : function onDeleteSuccess(data,status){
			console.log(data);
			if(data.result.status)
			{
				deletebtn.parent().remove();
				 $("#countofComment> span").text(data.count );
			}
	}
	});
}


