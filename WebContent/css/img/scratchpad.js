/*
 * This is a JavaScript Scratchpad.
 *
 * Enter some JavaScript, then Right Click or choose from the Execute Menu:
 * 1. Run to evaluate the selected text (Ctrl+R),
 * 2. Inspect to bring up an Object Inspector on the result (Ctrl+I), or,
 * 3. Display to insert the result in a comment after the selection. (Ctrl+L)
 */


for(i=1;i<=100;i++){

  
}

inc=2;
currentWidth=0;
    myVar = setTimeout(increaseTimer, 1000);



function increaseTimer(){
currentWidth=currentWidth+inc;
  width=currentWidth+"%";
  
    $(".progress .progress-bar").width(width);
      $(".progress .progress-bar .percentage").width(width);


}