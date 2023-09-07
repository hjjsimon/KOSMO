

const ACT_TYPE={ALL:'all',ERROR:'error',LOGIN:'login',LOGOUT:'logout'};

//리듀서의 목적은 State변경
//리듀서:현재 State를 받아 action의 type(요청 종류)에 따라 새로운 State를 반환한다
const usersReducer=(state,action)=>{
    
    switch(action.type){
        case ACT_TYPE.ALL://모든 사용자 목록            
            return {...state,users:action.users,isLogin:action.isLogin};
        case ACT_TYPE.LOGIN://로그인 처리           
            return {...state,isLogin:action.isLogin};
        case ACT_TYPE.LOGOUT://로그아웃 처리
            return {...state,isLogin:null};
        case ACT_TYPE.ERROR://에러 발생시
            //error는 액션에 전달된 에러내용으로
            //isLogin과 users는 초기 상태로
            return {isLogin:null,users:[],error:action.error};
        default:
            throw new Error(`없는 액션 타입입니다:${action.type}`)
    }
};
export default usersReducer;