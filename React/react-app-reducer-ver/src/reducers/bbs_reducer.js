//리듀서의 목적은 State변경
//리듀서:현재 State를 받아 action의 type(요청 종류)에 따라 새로운 State를 반환한다
const bbsReducer=(state,action)=>{
    
    switch(action.type){
        case 'loading':
            return {...state,isLoading:action.isLoading};
        case 'all':
            return {...state,boards:action.boards};
        case 'total':
            return {...state,totalSize:action.total};
        case 'nowPage':
            return {...state,nowPage:action.nowPage};
        case 'delete':
            return {...state,boards:action.boards};
        case 'write':
            return {...state,boards:action.boards};
        case 'update':
            /*
            const initialState={
                    isLoading:false,
                    boards:[],
                    pageSize:5,
                    totalSize:0,
                    nowPage:1
                }
                */
                const board = action.board;
                const boards_=state.boards.map(item=> item.id===board.id ?{...item,title:board.title,content:board.content}:item);  
                console.log('수정된 boards:',boards_);
            return {...state,boards:[...boards_]};
        default:
            throw new Error(`없는 액션 타입입니다:${action.type}`)
    }
};
export default bbsReducer;