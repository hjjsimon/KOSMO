

//첫번째 인자로 전달된 url주소에서 데이타([] 배열 혹은 {})를 가져오는 커스텀 훅 함수

import axios from "axios";


//useReducer로 State관리시 사용할 함수(훅함수가 아니다)
export const fetchReducer =async url=>await axios.get(url);


