var a1=3;
let b1=3;

function test(){  //all var are allocated in the memory (var = undefined)

    console.log("test 1",a1);
    console.log("test 1",b1);

    var a1=2;
    let b1=2;

    console.log("test 2",a1);
    console.log("test 2",b1);
}

test();

console.log("global",a1);
console.log("global",b1);
