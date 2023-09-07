
import axios from "axios";
import { useEffect, useState } from "react";

//네트웍(url)에서 데이타([] 배열 혹은 {})를 가져오는 커스텀 훅 함수
//deps:의존성 배열
const useFetch = (url,deps=[])=>{
  //마운트 될때 콜백함수 실행됨
  /*
  const [data,setData] = useState(()=>{
      axios.get(url)
      //네트웍으로 부터 받은 데이타를 State인 data로 설정
      .then(function(response){setData(response.data)})
      .catch(function(error){setData([]);console.log(error);});
  });*/
  //혹은
  
  const [data,setData] = useState([]);
  let responseData;
  useEffect(()=>{
        axios.get(url)      
        .then(res=>{
            responseData=res.data;
            res.data instanceof Array ?
            setData(prevState=>[...res.data]):
            setData(prevState=>({...res.data}));// {}객체를 반환할때는 반드시 ({}) 왜냐하면 함수불락으로 해석되니까
          })
        .catch(err=>{
          responseData instanceof Array ?
          setData([]) :
          setData({});
          console.log(err)})
      },deps)  
  return [data,setData];//배열 [데이타,세터] 형태로 반환
};


export default useFetch;