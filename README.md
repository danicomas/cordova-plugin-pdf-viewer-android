### Plugin's Purpose
This cordova plugin uses com.github.barteksc:android-pdf-viewer project and show a PDF reader with a callback button(optional)

## Supported Platforms ##

* Android 4+
* Cordova/Phonegap >=4.0.0

### Open a Document File ###

```js
var options = {
	  showButtons: 0, //0: no buttons; 1: ok button, 2: ok and cancel button
	  cancel: "Cancel", //text for cancel button
	  ok: "OK" //text for ok button
  };

var url = "/storage/emulated/0/Android/data/com.ionicframework.pdfjstest/files/sample.pdf";  
PDFViewer.showPDF(url, options, function(result) {
	  //result == '0' -> click on cancel, DONE or backpress button
	  //result == '1' -> click on OK button
  });
```