define([
	"bootstrap",
], function() {
	$(".admin-logo").on("click", function() {
		location.href = "/admin/";
	});
	$(".admin-menu>ul>li").on("click", function() {
		var menu=$(this).attr("menu");
		location.href ="/admin/"+menu;		
	});
	if (_ctx.menuId && _ctx.menuId !=="") {
		$(".admin-menu>ul>li[menu='"+_ctx.menuId+"']")
			.addClass("active");
	}
	function showSection(section, jqElement, handler) {
		$("section").hide();
		var more=$(jqElement).attr("more");
		if (more && 
				more !== "") {
			section="."+more;
		}
		$(section).show();
		handler(section, jqElement);
	}
	
	function initMgmt(handler) {
		$(".btn-admin-add").on("click", function() {
			showSection(".admin-add",this, handler);
		});
		showSection(".admin-list",null, handler);
	}
	return {
		initMgmt:initMgmt,
		showSection:showSection,
	}
});