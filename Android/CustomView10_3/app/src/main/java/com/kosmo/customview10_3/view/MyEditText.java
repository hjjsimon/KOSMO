package com.kosmo.customview10_3.view;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatEditText;

//1]기존 뷰(EditText) 상속
public class MyEditText extends AppCompatEditText{

    //2]생성자 정의
    //자바코드에서 new연산자로 직접 MyEditText객체 생성시
    public MyEditText(Context context) {
        super(context);
    }
    //XML에 등록된 위젯이 생성될때 호출되는 셍성자
    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //3]인터페이스 정의(내부 인터페이스)
    public interface OnTextLengthListener{
        //매개변수:사용자가 입력한 문자열 갯수
        void onTextLength(int textLength);
    }
    //4]인터페이스 타입의 리스너 선언
    private OnTextLengthListener onTextLengthListener;
    //5]위 필드의 setter 작성
    public void setOnTextLengthListener(OnTextLengthListener onTextLengthListener) {
        this.onTextLengthListener = onTextLengthListener;
    }

    //입력 상자에 글을 입력할때마다 호출된다
    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        //EditText에 기존 기능(TextChanged) 유지(super.onTextChanged(text, start, lengthBefore, lengthAfter);)하면서
        // 나만의 새로운 기능 추가
        //커스텀 뷰(내가 만든 에디티텍스트)에서 문자열의 길이를
        //내가 정의한 인터페이스의 추상 메소드에 매개변수로 전달 해줌
        if(onTextLengthListener !=null){
            onTextLengthListener.onTextLength(text.length());
        }
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }
}
