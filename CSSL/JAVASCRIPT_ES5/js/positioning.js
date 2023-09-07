//<script>태그는 제외, 순수 자스코드만
var button=document.querySelector('button');//button태그 없는 문서에서 사용시 null
console.log(button);
console.log(button.textContent);
var fieldset=document.querySelector('fieldset');
var div=document.createElement('div');//div태그 생성
div.setAttribute('style','width:100px;height:100px;line-height:100px;text-align:center;background-color:red;border-radius:50px');
//속성 부여 div.style도 됨
div.textContent='DIV';
//createTextnode도 됨, div에 또 붙이기 귀찮아서 이거 쓴대
fieldset.append(div);
//fieldset에 div붙임