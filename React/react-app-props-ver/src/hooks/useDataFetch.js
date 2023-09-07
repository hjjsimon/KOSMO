

//첫번째 인자로 전달된 url주소에서 데이타([] 배열 혹은 {})를 가져오는 커스텀 훅 함수

import axios from "axios";
import { useEffect, useState } from "react";

//deps:의존성 배열
const useFetch=(url,deps=[])=>{
   /*
   Warning: Can't perform a React state update on a component that hasn't mounted yet. This indicates that you have a side-effect in your render function that asynchronously later calls tries to update the component. Move this work to useEffect instead.
   아래 코드는 최초 마운트시 data는 undefined
   데이타를 가져온 후 State가 변경되서 재 렌더링이 일어난다
   회원은 컨텍스트를 사용해서 문제가 없으나 왜냐하면 Template(재 런더링후)의
   컨텍스트에 사용자 목록이 저장되어 있으니까
   게시판은 최초 마운트시 boards가 undefined가 됨으로
   List로 내린 boards가 undefined라 boards is undefined라는 에러
   이를 해결하기위 위해 useEffect()훅으로 변경
   /*
   const [data,setData]= useState(()=>{
      axios.get(url)
             .then(res=>{
                setData(preState=>[...res.data])
             })
             .catch(err=>{
                setData([]);
             })
      });
   */
    const [data,setData]= useState([]);
    useEffect(()=>{
        console.log('데이타베이스에서 글을 페치합니다');
        axios.get(url)
             .then(res=>{
               //목록(사용자,게시판)을 반환할때는 []형태,하나를 반환할때(게시판 상세보기) {}형태
               //즉 []나 {}에 따라 스프레드를 달리해야한다
               console.log('res.data:',res.data);
               res.data instanceof Array ?
               setData(preState=>[...res.data]) :
               setData(preState=>({...res.data}))
             })
             .catch(err=>{
                console.log(err);
             })

    },deps);
    return [data,setData];//배열 반환
};

export default useFetch;