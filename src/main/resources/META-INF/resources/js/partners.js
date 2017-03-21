require([
	"common",
], function() {
	var common = require("common");
	
	function loadList() {
		$.ajax({
			url: "/admin/api/partner/list",
			success: function(list) {
				var itemHTML = "";

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
					//common.showSection(".admin-partner-update", $(this), handler);
				});
			},
		});
	}
	
	var handler = function(section, jqElement) {
		if (section ===".admin-list") {
			loadList();
		}
		else if (section ===".admin-add") {
			
		}
		else if (section ===".admin-update") {
			
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
	
	$(".btn-admin-cancel").on("click", function() {
		common.showSection(".admin-list", null, handler);
	});
	
	
	common.initMgmt(handler);
});