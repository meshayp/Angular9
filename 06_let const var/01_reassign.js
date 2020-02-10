//---------reassign var-------------- (OK)
var a1=0;
a1=9;
console.log(a1);  //--> 9



//---------reassign let-------------- (OK)
let b1=0;
b1=9;
console.log(b1);  //--> 9



//---------reassign const-------------- (NOT OK)
const c1=0;
c1=9;
console.log(c1);  //--> TypeError: Assignment to constant variable.






