require([
	"common",
], function() {
	var currentStore= {};
	var common = require("common");
	
	var handler = function(section, jqElement) {
		if (section === ".admin-list") {
			loadList();
		}
		else if (section === ".admin-space-add") {
			$("#add-space_name").val("");	
		}
		else if (section === ".admin-space-desc-add") {
			$("#add-space-desc_name").val("");			
			
			$.ajax({
				url:"/admin/api/space/list",
				success: function(list) {
					var itemsHTML="";
					for (var i=0; i<list.length; i++) {
						var item = list[i];
						itemsHTML += "<li><a href='#' item-id='";
						itemsHTML += item["space_id"] + "'>";
						itemsHTML += item["space_name"];
						itemsHTML += "</a></li>";
					}
					$("#add-space").html(itemsHTML);
					$("#add-space li a").on("click", function(event) {
						event.preventDefault();
						var codeName = $(this).text();
						$("#btn-txt-add-space-desc").text(codeName);
						var codeId=$(this).attr("item-id");
						currentStore.spaceId = codeId;
					});
					
				}
			});
		}
	}
	function loadList() {
		$.ajax({
			url: "/admin/api/space/list",
			success: function(list) {
				var itemHTML = "";
				
				for (var i=0; i<list.length; i++) {
					var item = list[i];
					
					itemHTML += "<tr space-id='" + item.space_id + "'>";
					itemHTML += "<td>" + (i+1) + "</td>";
					itemHTML += "<td>" + item.space_name + "</td>";
					itemHTML += "<td>" + item.space_id + "</td>";
					itemHTML += "<td>" + item.desc_num + "</td>";
					itemHTML += "<td>" + item.gallery_num + "</td>";
					itemHTML += "</tr>";
				}
				
				$(".admin-list .admin-space table>tbody").html(itemHTML);
				$(".admin-list .admin-space table>tbody>tr").on("click", function() {
					//common.showSection(".admin-update", $(this), handler);
				});
			},
		});
		$.ajax({
			url: "/admin/api/space/descList",
			success: function(list) {
				var itemHTML = "";
				
				for (var i=0; i<list.length; i++) {
					var item = list[i];
					
					itemHTML += "<tr space-desc-id='" + item.space_desc_id + "'>";
					itemHTML += "<td>" + (i+1) + "</td>";
					itemHTML += "<td>" + item.space_desc_name + "</td>";
					itemHTML += "<td>" + item.space_desc_id + "</td>";
					itemHTML += "<td>" + item.space_name  + "</td>";
					itemHTML += "<td>" + item.gallery_num + "</td>";
					itemHTML += "</tr>";
				}
				
				$(".admin-list .admin-space-desc table>tbody").html(itemHTML);
				$(".admin-list .admin-space table>tbody>tr").on("click", function() {
					//common.showSection(".admin-update", $(this), handler);
				});
			},
		});
	}
	$(".admin-space-add .btn-admin-save").on("click", function() {
		var spaceName=$("#add-space_name").val().trim();	
		
		if (spaceName === "") {
			alert("공간명을 입력하세요,");
			$("#add-space_name").focus();
			return;
		}
		$.ajax({
			url: "/admin/api/space/add",
			method: "PUT",
			data : {
				spaceName: spaceName,
			},
			success: function() {
				common.showSection(".admin-list", null, handler);
			},
			error: function() {
				alert("입력실패 했습니다.");
			},
		});
	});
	$(".admin-space-desc-add .btn-admin-save").on("click", function() {
		var spaceDescName=$("#add-space-desc_name").val().trim();	
		
		if (spaceDescName === "") {
			alert("세부공간명을 입력하세요,");
			$("#add-space-desc_name").focus();
			return;
		}
		else if (!currentStore.spaceId) {
			alert("공간을 선택하세요,");
			return;
		}
		$.ajax({
			url: "/admin/api/space/desc/add",
			method: "PUT",
			data : {
				spaceDescName: spaceDescName,
				spaceId: currentStore.spaceId,
			},
			success: function() {
				common.showSection(".admin-list", null, handler);
			},
			error: function() {
				alert("입력실패 했습니다.");
			},
		});
	});
	
	$(".btn-admin-cancel").on("click", function() {
		common.showSection(".admin-list", null, handler);
	});
	
	common.initMgmt(handler);
	
});