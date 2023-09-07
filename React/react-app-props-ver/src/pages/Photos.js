
//무한 스크롤 구현하기
//https://github.com/thebuilder/react-intersection-observer
//npm install react-intersection-observer

import useFetch from "../hooks/useFetch";
import React, { useEffect, useState } from "react";
import { useInView } from "react-intersection-observer";
import axios from "axios";
const Photos = ()=>{

  //모든 사진 페치(가져오기)
  const [photos,setPhotos]=useState([]);
  const [albumId,setAlbumId] = useState(1);
  //페칭(모든 데이타를 가져왔는지) 여부 판단용
  const [isFetching,setIsFetching] = useState(false);
  //https://react-intersection-observer.vercel.app/?path=/story/introduction--page
  
  //useInView훅 함수은 다음을 반환 
  //ref : 모니터링 하고자 하는 DOM요소에 할당할 ref객체. 페치한 아이템의 마지막 요소에 할당한다
  //inView : ref를 할당한 요소가 보이면 true,안보이면 false값을 갖는 변수로 자동으로 변한다
  const [ref,inView] = useInView();
  console.log('albumId:',albumId);
  //albumId가 변할때마다 앨범 데이타를 가져오자
  useEffect(()=>{
    setIsFetching(true);//페치 시작 즉 데이타를 가져오기 시작한다
    axios.get(`https://jsonplaceholder.typicode.com/photos?albumId=${albumId}`)
        .then(res=>{
          setPhotos(prevState=>[...prevState,...res.data]);
          setIsFetching(false);//페치 끝.데이타를 전부 자져왔다
        });
  },[albumId]); 

  //무한 스크롤링 구현하기
  useEffect(()=>{
    //마지막 요소가 보이고(true) 페치중이 아니라면 앨범아이디를 
    //증가시키서 다음 아이템들을 페치해오자
    //즉 마지막요소는 처음에 inView상태가 false였다가 사용자가 스크롤링을 하면
    //   마지막요소의 inView상태가 true가 되면 다음 앨범아이디에 해당하는
    //   데이타를 가져오자
    if(inView && !isFetching) setAlbumId(prevState=>prevState+1)
  },[inView,isFetching]);

  return <>
    {photos.map((photo,index)=>
        <React.Fragment key={photo.id}>
          {photos.length-1 ==index ? //마지막 요소인 경우:마지막 DOM요소에 ref 할당-모니터링하기위해서
            (<div className="card" ref={ref}>
              <div className="card-header" style={{backgroundColor:"#B0F7CC"}}>{photo.title} <span className="badge badge-danger">{photo.id}</span></div>
              <div className="card-body"><img src={photo.thumbnailUrl} className="img-thumbnail" alt="Cinque Terre"/></div>
              <div className="card-footer" onClick={()=>{window.open(`${photo.url}`,'_blank')}}  style={{cursor:'pointer'}}>{photo.url}</div>
            </div>) :
            (
              <>
                <div className="card" >
                  <div className="card-header" style={{backgroundColor:"#B0F7CC"}}>{photo.title} <span className="badge badge-danger">{photo.id}</span></div>
                  <div className="card-body"><img src={photo.thumbnailUrl} className="img-thumbnail" alt="Cinque Terre"/></div>
                  <div className="card-footer" onClick={()=>{window.open(`${photo.url}`,'_blank')}} style={{cursor:'pointer'}}>{photo.url}</div>
                </div>
                <hr/> 
              </>
            )
        
          }
          
        </React.Fragment>      
      )
    }
    
  </>
};

export default Photos;