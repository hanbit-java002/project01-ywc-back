require([
	"common",
], function() {
	var common = require("common");
	var handler = function(section, jqElement) {
		if (section === ".admin-list") {
			loadList();
		}
		else if (section === ".admin-space-add") {
			
		}
		else if (section === ".admin-space-desc-add") {
			
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
			success: function() {
				
			},
		});
	}
	common.initMgmt(handler);
	
});