import React from 'react';
import ReactDOM from 'react-dom/client';
import AppNoOutlet from './App_No_Outlet';
import { BrowserRouter } from 'react-router-dom';//npm install react-router-dom@6
import AppOutlet from './App_Outlet';


const root = ReactDOM.createRoot(document.getElementById('root'));
 {/*최상위 컴포넌트를 BrowserRouter컴포넌트로 감싸야한다*/}
root.render(
  <BrowserRouter>
    {/* Outlet컴포넌트 미 사용 버전 */}
    {/* <AppNoOutlet /> */}
    {/* Outlet컴포넌트 사용 버전 */}
    <AppOutlet/>
  </BrowserRouter>
);
