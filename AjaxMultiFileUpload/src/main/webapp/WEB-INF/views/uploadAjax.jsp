<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>ajax 멀티 파일 업로드</title>
<style>
.fileDrop, .uploadedList {
	width: 50%;
	height: 300px;
	border: 1px dotted blue;
	margin: 0 auto;
}

h3 {
	text-align: center;
}

small {
	margin-left: 3px;
	font-weight: bold;
	color: gray;
}
</style>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>
	<h3>Ajax File Upload</h3>
	<div class='fileDrop'></div>
	<input type="button" value="모두삭제" />
	<div class='uploadedList'></div>

	<script>
	$(".fileDrop").on("dragenter dragover", function(event){
		event.preventDefault();		
	});
	
	$(".fileDrop").on("drop", function(event){
		alert("aaaa");
		event.preventDefault();	
// 		event.originalEvent는 순수한 원래의 DOM이벤트를 가지고 옮
//		JQuery를 사용할경우  순수한 DOM이벤트가 아님
//		dataTransfer는 이벤트와 같이 전달된 데이터를 의미
//		그 안에 포함된 파일 데이터를 찾아내기 위해 dataTransfer.files
//		를 사용함	
		let files = event.originalEvent.dataTransfer.files;			
// 		let file = files[0];
// 		파일이름을 바로 알아낼수 있음
// 		file.name;
// 		alert(file.name+"입니다")
					
		let formData = new FormData();
		$.each(files,function(index,item){
			formData.append("multiFile", item);
		});				
		
		$.ajax({
			  url: '/ajaxMultiFileUpload/uploadAjax.bbs',
			  data: formData,
//			  복수개를 업로드시 
			  dataType:'json',	
			  type: 'POST',
//			  processData: false는
//			  데이터를 일반적인 query string으로 변활할 것인지를 결정, 기본값은 true , 
//			  'application/x-www-form-urlencoded' 타입으로 전송, 다른 형식으로 데이터를
//			  보내기 위하여 자동 변환하고 싶지 않은 경우는 false로 지정
			  processData: false,
			  
//			  contentType: false 는
//			  기본값은 'application/x-www-form-urlencoded', 파일의 경우 'multipart/form-data'
//			  방식으로 전송하기 위해서 false
			  contentType: false,			 
			  success: function(data){
				  let str ="";			 
				  			  
				  $.each(data,function(index, fileName){
					  alert("bbb");
					  if(checkImageType(fileName)){						 
						  str ="<div><a href=displayFile.bbs?fileName="+getImageLink(fileName)+">" //getImageLink s_없는 이미지 이름이 넘어감
								  +"<img src='displayFile.bbs?fileName="+fileName+"'/>"	//s_가 붙어있는 이미지이름
								  +"</a><small class='human' data-src='"+fileName+"'>X</small></div>"; //X표가 클릭되었는지?
					  }else{
						  str = "<div><a href='displayFile.bbs?fileName="+fileName+"'>" 
								  + getOriginalName(fileName)+"</a>"
								  +"<small class='human' data-src='"+fileName+"'>X</small></div>";
					  }
					  
					  $(".uploadedList").append(str);
				  });				 
			  },
			  error : function(xhr){
					alert("error html = " + xhr.statusText);
			  }			  
			});	
	});
	
	function checkImageType(fileName){			
// 		/i는 대소문자 구분 하지 말라는 뜻임
		let pattern = /.jpg|.gif|.png/i;		
		return fileName.match(pattern);		
	}
	
	function getImageLink(fileName){		
		let front = fileName.substr(0,12);
		let end = fileName.substr(14);
		alert(front + end);		
		return front + end;	
	}
	
	function getOriginalName(fileName){		
		let idx = fileName.indexOf("_") + 1 ;
		return fileName.substr(idx);	
	}
	
	$(".uploadedList").on("click","small",function(event){ //클릭했을때 small function이 동작한다
		let that = $(this);   //that= event 일으킨놈 
		$.ajax({
			url:"/ajaxMultiFileUpload/deleteFile.bbs",
			type:"post",
			data:{
				fileName:$(this).attr("data-src") //attr 파라미터가 하나일떄는 읽어오는것 attr("human","1234")는 휴먼에 1234 쓰는것 
			},
			dataType:"text",
			success:function(result){
			if(result=='deleted'){
				that.parent("div").remove();  //이벤트를 일으킨놈의 부모 div태그 삭제 ==> 화면에 뿌려져있는 애들 삭제됨
				alert("삭제성공");
			}
		}
		});
	});
	
	//$(선택자)
	// $("input").on("click",function(){  //input태그를 찾아서 (#,.이 없으니까 #:id , .:클래스)	
	$("input[type=button]").on("click",function(){ //input태그에서 타입이 버튼인거만 동작 
	//$("input[value=모두삭제]").on("click",function(){	//input태그의 value속성이 모두삭제인것만 동작 
		let files = [];
			$.each($(".human"),function(idnex,item){
				//files[index] =$(this).attr("data-src");
				files.push($(this).attr("data-src"));
				});
			
	//배열을 직렬화해서 전송함
	//직렬화 했을떄는 files : 값 형식으로 전송되고
	//직렬화 하지 않을떄는 files[]: 값 이런 형식으로 전송됨
	
		$.ajaxSettings.traditional = true;
		$.ajax({
			url:"/ajaxMultuFileUpload/deleteAllFiles.bbs",
			type:"post",
			data:{files:files},
			dataType:"text",
			success:function(result){
				if(result=='deleted'){
					$(".uploadedList").children().remove();
					alert("삭제성공");
				}
			}
		})
		}
	}
		})
</script>





</body>
</html>