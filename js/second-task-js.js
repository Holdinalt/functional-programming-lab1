const calc = (n) => {
    const digits = n.toString().split('').map(Number);
    let sum = 0;
    for (let digit of digits){
        sum += Math.pow(digit, 5)
    }

    return sum
}

const max = 9999
let out = 0;

for (let i = 0; i <= max; i++ ){
    if(calc(i) === i){
        out += i
    }
}

console.log(out)