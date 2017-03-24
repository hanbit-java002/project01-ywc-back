require([
	"common",
], function() {
	var common = require("common");
	var currentPage = 1;
	var pagesPerPaging = 2;
	var rowsPerPage = 2;
	
	function loadList(page) {
		currentPage = page;
		$.ajax({
			url: "/admin/api/partner/list",
			data: {
				currentPage: currentPage,
				rowsPerPage: rowsPerPage,
			},
			success: function(result) {
				var itemHTML = "";
				var list= result.list;
				var count= result.count;
				
				for (var i=0; i<list.length; i++) {
					var item = list[i];
					itemHTML += "<tr partner-id='" + item.partners_id + "'>";
					itemHTML += "<td>" + (i+1) + "</td>";
					itemHTML += "<td>" + item.partners_name + "</td>";
					itemHTML += "<td>" + item.partners_id + "</td>";
					itemHTML += "<td>" + item.partners_major + "</td>";
					itemHTML += "<td>" + item.partners_specialty+ "</td>";
					itemHTML += "<td>" + item.partners_range+ "</td>";
					itemHTML += "<td>" + item.partners_specialty+ "</td>";
					itemHTML += "</tr>";
				}
				
				$(".admin-list table>tbody").html(itemHTML);
				$(".admin-list table>tbody>tr").on("click", function() {
					common.showSection(".admin-update", $(this), handler);
				});
				
				//pager
				
				var firstPage=1 ;
				var lastPage = parseInt(count/rowsPerPage) + (count%rowsPerPage >= 0?1:0);
				var startPage = parseInt((currentPage-1)/pagesPerPaging)*pagesPerPaging+1;
				var endPage = lastPage <= startPage+pagesPerPaging-1?lastPage: startPage+pagesPerPaging-1;
				
				var pagerHtml="";
				
			    if (startPage>1) {
			    	pagerHtml+="<li page="+firstPage+"><a href='#'>&laquo;</a></li>";
			    	console.log(startPage);
			    	pagerHtml+="<li page="+(startPage-1)+"><a href='#'>&lt;</a></li>";
			    }
			    for (var i= startPage; i<endPage+1;i++){
			    	pagerHtml+="<li page="+i+"><a href='#'>"+i+"</a></li>";
			    }
			    
			    if (startPage+pagesPerPaging-1< lastPage) {
				    pagerHtml+="<li page="+(startPage+pagesPerPaging)+"><a href='#'>&gt;</a></li>";
				    pagerHtml+="<li page="+lastPage+"><a href='#'>&raquo;</a></li>";
			    }
			    
			    $(".admin-paging").html(pagerHtml);
			    
				$(".admin-paging>li>a").on("click", function() {
					var page=$(this).parent().attr("page");
					loadList(page);
				});
			    
			},
		});
	}
	

	
	var handler = function(section, jqElement) {
		if (section ===".admin-list") {
			loadList(currentPage);
		}
		else if (section ===".admin-add") {
			$("#add-partner_name").val("");
	    	$("#add-partner_desc").val(""); 
	    	$("#add-partner_major").val(""); 
	    	$("#add-partner_specialty").val("");
	    	$("#add-partner_range").val("");
	    	$("#add-partner_img").val("");
	    	$(".btn-admin-file").text("파일선택");
		}
		else if (section ===".admin-update") {
			$("#upt-partner_name").val("");
	    	$("#upt-partner_desc").val(""); 
	    	$("#upt-partner_major").val(""); 
	    	$("#upt-partner_specialty").val("");
	    	$("#upt-partner_range").val("");
	    	$("#upt-partner_img").val("");
	    	
	    	var partnerId =jqElement.attr("partner-id");
	    	
	    	$.ajax({
	    		url:"/admin/api/partner/"+partnerId,
	    		method: "GET",
	    		success: function(partner) {
	    			$("#upt-partner_id").val(partner.partners_id);
	    			$("#upt-partner_name").val(partner.partners_name);
	    	    	$("#upt-partner_desc").val(partner.partners_desc); 
	    	    	$("#upt-partner_major").val(partner.partners_major); 
	    	    	$("#upt-partner_specialty").val(partner.partners_specialty);
	    	    	$("#upt-partner_range").val(partner.partners_range);
	    	    	$("#upt-partner_img").val("");
	    	    	$(".btn-admin-file").html("<img src='" + partner.partners_img +
							"?ts=" + Date.now() + "'>");
				},
	    	});
		}
	};
	
	$(".btn-admin-save").on("click", function() {
		var partnerName =$("#add-partner_name").val();
    	var partnerDesc =$("#add-partner_desc").val(); 
    	var partnerMajor =$("#add-partner_major").val(); 
    	var partnerSpecialty =$("#add-partner_specialty").val(); 
    	var partnerRange =$("#add-partner_range").val();
    	var partnerImg =$("#add-partner_img").val();
    	
    	var formData = new FormData();
    	formData.append("partnerName",partnerName);
    	formData.append("partnerDesc",partnerDesc);
    	formData.append("partnerMajor",partnerMajor);
    	formData.append("partnerSpecialty",partnerSpecialty);
    	formData.append("partnerRange",partnerRange);
   
    	
    	var files = $("#add-partner_img")[0].files;
    	
    	formData.append("partnerImg",files[0]);
		$.ajax({
			url: "/admin/api/partner/add",
			method: "POST",
			data: formData,
			processData: false,
			contentType: false,
			success: function() {
				common.showSection(".admin-list", null, handler);
			},
			error: function() {
				alert("저장에 실패했습니다.");
			},
		});
		

	});
	$(".btn-admin-update").on("click", function() {
		var partnerId =$("#upt-partner_id").val();
		var partnerName =$("#upt-partner_name").val();
    	var partnerDesc =$("#upt-partner_desc").val(); 
    	var partnerMajor =$("#upt-partner_major").val(); 
    	var partnerSpecialty =$("#upt-partner_specialty").val(); 
    	var partnerRange =$("#upt-partner_range").val();
    	var partnerImg =$("#upt-partner_img").val();
    	
    	var formData = new FormData();
    	formData.append("partnerName",partnerName);
    	formData.append("partnerDesc",partnerDesc);
    	formData.append("partnerMajor",partnerMajor);
    	formData.append("partnerSpecialty",partnerSpecialty);
    	formData.append("partnerRange",partnerRange);
   
    	
    	var files = $("#upt-partner_img")[0].files;
    	
    	formData.append("partnerImg",files[0]);
    	
    	$.ajax({
    		url:"/admin/api/partner/"+partnerId,
    		method: "POST",
    		processData: false,
    		contentType: false,
    		data: formData,
    		success: function() {
    			common.showSection(".admin-list", null, handler);
			},
    	});
		
	});
	
	$(".btn-admin-cancel").on("click", function() {
		common.showSection(".admin-list", null, handler);
	});
	
	
	common.initMgmt(handler);
});