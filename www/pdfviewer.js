/* MIT licensed */
// (c) 2017 Ingens Networks
//

var exec = require('cordova/exec');

var PDFViewer = {

	// Callback when the user chooses the 'Done' button
	// called from native
	_onClose: function() {
		this.onClose();
	},

	/* The interface that you will use to access functionality */

	// Show a webpage, will result in a callback to onLocationChange
	showPDF: function(loc, callback) {
		exec(callback, null, 'PDFViewer', 'showPDF', [loc]);
	},

	// close the browser, will NOT result in close callback
	close: function() {
		exec(null, null, 'PDFViewer', 'close', []);
	},

	// Not Implemented
	jsExec: function(jsString) {}
};

module.exports = PDFViewer;