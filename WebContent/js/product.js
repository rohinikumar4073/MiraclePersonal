/**
 *
 */

bundlesData={

		cusomterNew:
		[
		 {
			 productid : 1,
			 productName:"Shop Bundles",
			 subproducts:[{
				 productid : 10091,
				 productName:"Mobile Workforce Manager + Voice Cypheer: Voice Encryption",
				 productDesc:"On-demand mobility requires on-demand solutions. "
			 },{
				 productid : 10092,
				 productName:"Mobile Application Manager+Mobility New Services",
				  productDesc:"On-demand mobility requires on-demand solutions."
			 },{
				 productid :10093,
				 productName:"Voice Cypheer: Voice Encryptio n+ M2M management center",
				  productDesc:"On-demand mobility requires on-demand solutions."
			 }],



		 }


		 ]


}
function getRandomInt(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}
loadData=function(){


		for(i=0;i<bundlesData.cusomterNew.length;i++){
		  parentElement=$("<div class='row-offcanvas row-offcanvas-right'>");
			headElemnt="<h3 class='head'>"+bundlesData.cusomterNew[i].productName+"</h3>"
			parentElement.append(headElemnt)
		    ulElement=$("<ul class='bxSlider'>");
		  for(j=0;j<bundlesData.cusomterNew[i].subproducts.length;j++){
		   liElement="<li>"+
			 "<h4>"+bundlesData.cusomterNew[i].subproducts[j].productName+"</h4>"+
			 "<p>"+bundlesData.cusomterNew[i].subproducts[j].productDesc+"</p>"+
			 "<h4>"+"$"+ getRandomInt(100,1000)+"</h4>"+
	     "<button  class='btn btn-default viewdetails'onclick='window.location.href=.product.html'>View Detail</button>"+
			 "</li>";

		    ulElement.append(liElement);
		  }
		  parentElement.append(ulElement);
			$(".pageBody ").append(parentElement);

		}

	$('.bxSlider').bxSlider({
	  minSlides:2,
	  maxSlides: 4,
	  slideWidth: 300,
	  slideMargin: 30
	});
}
$(document).ready(function(){
	loadData();

});
