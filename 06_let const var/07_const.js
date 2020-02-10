const num=4;


//num=5;  //--> cannot reassign


//const num1;  //--> SyntaxError: Missing initializer in const declaration



const arr=[1,2];



console.log(arr); //--> [1,2]

arr.push(3);


console.log(arr); //--> [1,2,3]

arr[0]=7;

console.log(arr); //--> [7,2,3]


arr=[7,7,7];


