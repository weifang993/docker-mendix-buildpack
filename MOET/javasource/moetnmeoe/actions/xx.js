require(["dojo/window", "dojo/query", "dojo/dom-style", "dojo/NodeList-dom"], function(win,query,domStyle){
  var cpcftr = dom.byId("CPCHeaderFooter_widget_CPCFooter_0");
	var vs = win.getBox();
	var viewportHeight = vs.h;
	
	var footer = dojo.query(".CPCFooter");
	var footerInfo = dojo.position(footer[0],false);
	var footerTop = footerInfo.y;
	
	var runningTotal = dojo.query(".stickyFooter");
	var runningTotalInfo = dojo.position(runningTotal[0],false);
	var runningTotalHeight = runningTotalInfo.h;
	
	var runningTotalParent = runningTotal[0].parentNode;
	var parentInfo = dojo.position(runningTotalParent,false);
	var wdth = parentInfo.w;
	
	var newWidth = wdth.toString() + "px";
	var tp = viewportHeight-runningTotalHeight;
	var newTop = tp.toString() + "px";
	
	if (viewportHeight > footerTop + 10) {
		var tp = footerTop-runningTotalHeight;
		var newTop = tp.toString() + "px";
		domStyle.set(runningTotal[0], "top", newTop);
		domStyle.set(runningTotal[0], "position", "static"); 
	}
	if (viewportHeight <= footerTop + 10) {
		domStyle.set(runningTotal[0], "position", "fixed"); 
		domStyle.set(runningTotal[0], "width", newWidth); 
	}
	
	dojo.query(".mx-scrollcontainer-wrapper").forEach(function(node){
    	dojo.connect(node, "onscroll", function(evt){
		var vs = win.getBox();
		var viewportHeight = vs.h;
		
		var footer = dojo.query(".CPCFooter");
		var footerInfo = dojo.position(footer[0],false);
		var footerTop = footerInfo.y;
		
		var runningTotal = dojo.query(".stickyFooter");
		var runningTotalInfo = dojo.position(runningTotal[0],false);
		var runningTotalHeight = runningTotalInfo.h;
		
		var tp = viewportHeight-runningTotalHeight;
		var newTop = tp.toString() + "px";
		domStyle.set(runningTotal[0], "top", newTop);
		
		var runningTotalParent = runningTotal[0].parentNode;
		var parentInfo = dojo.position(runningTotalParent,false);
		var wdth = parentInfo.w;
		var newWidth = wdth.toString() + "px";
		
		if (viewportHeight > footerTop + 10) {
			var tp = footerTop-runningTotalHeight;
			var newTop = tp.toString() + "px";
			domStyle.set(runningTotal[0], "top", newTop);
			domStyle.set(runningTotal[0], "position", "static"); 
		}
		if (viewportHeight <= footerTop + 10) {
			domStyle.set(runningTotal[0], "position", "fixed"); 
			domStyle.set(runningTotal[0], "width", newWidth); 
		}
			
	});
}});