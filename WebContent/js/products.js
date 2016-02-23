/**
 *
 */
productsData={

		cusomterNew:
		[
		 {
			 productid : 1,
			 productName:"Mobility",
			 subproducts:[{
				 productid : 2,
				 productName:"Mobile Workforce Manager",
				 productDesc:"On-demand mobility requires on-demand solutions."
			 },{
				 productid : 3,
				 productName:"Mobile Application Manager",
				  productDesc:"On-demand mobility requires on-demand solutions."
			 },{
				 productid : 4,
				 productName:"Voice Cypheer: Voice Encryption",
				  productDesc:"On-demand mobility requires on-demand solutions."
			 },{
				 productid : 5,
				 productName:"Mobility New Services",
				  productDesc:"On-demand mobility requires on-demand solutions."
			 }],



		 },	 {
				 			 productid : 6,
				 			 productName:"Machine to Machine",
				 			 subproducts:[{
				 				 productid : 7,
				 				 productName:"M2M management center",
				 				 productDesc:"On-demand mobility requires on-demand solutions."
				 			 },{
				 				 productid : 8,
				 				 productName:"Think Space Develop",
				 				  productDesc:"On-demand mobility requires on-demand solutions."
				 			 },{
				 				 productid :9,
				 				 productName:"Proffessional Services",
				 				  productDesc:"On-demand mobility requires on-demand solutions."
				 			 },{
				 				 productid :10,
				 				 productName:"Medium Business Prodcuts and Services",
				 				  productDesc:"On-demand mobility requires on-demand solutions."
				 			 }],



				 		 },	 {
						 			 productid : 11,
						 			 productName:"Networks and Internet",
						 			 subproducts:[{
						 				 productid : 12,
						 				 productName:"Software Defined Network",
						 				 productDesc:"SDN provides the flexibility to choose from a variety of networking hardware enabling virtualized functionality, centralized management and integration through application programming interfaces (APIs). ."
						 			 },{
						 				 productid : 13,
						 				 productName:"Ethernet",
						 				  productDesc:"Extend your office network across town, across the globe, or into the cloud. Our Ethernet portfolio seamlessly delivers data and applications that can help improve your business performance. We offer a complete, highly available Ethernet portfolio in increments from 1 Mbps to 10 Gbps,* with business continuity and management tools.."
						 			 },{
						 				 productid :14,
						 				 productName:"Dedicated Intenet Services",
						 				  productDesc:"On-demand mobility requires on-demand solutions."
						 			 },{
						 				 productid :15,
						 				 productName:"Wavelenght Services",
						 				  productDesc:"On-demand mobility requires on-demand solutions."
						 			 }],



						 		 }


		 ]


}

loadData=function(){


	for(i=0;i<productsData.cusomterNew.length;i++){
	  parentElement=$("<div class='row-offcanvas row-offcanvas-right'>");
		headElemnt="<h3 class='head'>"+productsData.cusomterNew[i].productName+"</h3>"
		parentElement.append(headElemnt)
	    ulElement=$("<ul class='bxSlider'>");
	  for(j=0;j<productsData.cusomterNew[i].subproducts.length;j++){
	   liElement="<li>"+
		 "<h4>"+productsData.cusomterNew[i].subproducts[j].productName+"</h4>"+
		 "<p>"+productsData.cusomterNew[i].subproducts[j].productDesc+"</p>"+
     "<button  class='btn btn-default viewdetails'>View Detail</button>"+
		 "</li>";

	    ulElement.append(liElement);
	  }
	  parentElement.append(ulElement);
		$(".pageBody").prepend(parentElement);

	}
	$('.bxSlider').bxSlider({
	  minSlides: 3,
	  maxSlides: 4,
	  slideWidth: 250,
	  slideMargin: 10
	});

}
$(document).ready(function(){
	loadData();

});
