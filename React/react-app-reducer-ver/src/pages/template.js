import { Outlet } from "react-router-dom";
import Header from "./header";


export default function Template(){    
    
    return <>
        <Header />
        <div className="container">
           {/*라우팅 된 자식중 하나가 아래 Outlet 위치에 표시된다 */}
            <Outlet/>
        </div>
    
    </>
}