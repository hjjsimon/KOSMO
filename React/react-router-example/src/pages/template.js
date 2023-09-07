


//최상위 컴포넌트에서 라우팅시 Template컴포넌트를 부모로 하고 

import { Outlet } from "react-router-dom";
import Header from "./header";

//나머지 컴포넌트를 자식(서브 라우트)으로 구성한다
export default function Template(){
    return <>
        <Header/>
        <div className="container">
            {/* Outlet컴포넌트 위치에 URL패턴에 따른 라우트 컴포넌트가 그려진다
                즉 Home,About,Users등이 자식 컴포넌트가 된다
            */}
            <Outlet/>
        </div>
    
    </>
}