var myInput= document.getElementById("myInput");
myInput.onmouseover = function(){
	this.focus();
	alert("qingshuru");
};
myInput.onfocus = function(){
	this.select();
};