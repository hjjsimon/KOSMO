/*
export let width=100;
export let height=100;
export const 면적=(width,height)=>width*height*0.5;
*/


let width=100;
let height=100;
const 면적=(width,height)=>width*height*0.5;

console.log('삼각형 가로폭:',width);
console.log('삼각형 세로폭:',height);
console.log('면적:',면적(100,100));
console.log('{width,height,면적}출력:',{width,height,면적});
//export {키:값,....} 시 문법 오류 즉 shorthand property 기법을 적용해야 한다
//또한 export default로 export시는  {width,height,면적}로 받을 수 없다
export {width,height,면적};

