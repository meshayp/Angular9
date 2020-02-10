
//---------redecalre function-------------- (OK)
function f1(){
    console.log("f1 V1");
}

function f1(){
    console.log("f1 V2");
}

f1();  //-->  f1 V2


//---------redecalre var-------------- (OK)
var a1=0;
var a1=9;
console.log(a1);  //--> 9


//---------redecalre const-------------- (NOT OK)
const b1=0;
const b1=9;
console.log(b1);  //--> SyntaxError: Identifier 'b1' has already been declared


//---------redecalre let-------------- (NOT OK)
let c1=0;
let c1=9;
console.log(c1);  //--> SyntaxError: Identifier 'c1' has already been declared






