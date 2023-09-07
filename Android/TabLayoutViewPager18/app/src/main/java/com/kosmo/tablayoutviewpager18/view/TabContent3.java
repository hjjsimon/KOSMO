package com.kosmo.tablayoutviewpager18.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.kosmo.tablayoutviewpager18.MainActivity;
import com.kosmo.tablayoutviewpager18.databinding.FragmentTabContent3Binding;


//1]Fragement상속
//※androidx.fragment.app.Fragment 상속
public class TabContent3 extends Fragment {

    private FragmentTabContent3Binding binding;

    //2]onCreateView()오버 라이딩
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i("com.kosmo.tablayout","TabContent3의 onCreateView");

        binding= FragmentTabContent3Binding.inflate(inflater,container,false);
        View view=binding.getRoot();
        //2번)프래그먼트->프래그먼트에서 전송한 데이타 받기

        if(((MainActivity)getActivity()).tabContent1 !=null){
            //dataTransfer이벤트(내가 만든 이벤트)가 발생할때마다(TabContent1에서 버튼 클릭시:onDataTransfer()메소드를 호출할 때마다)
            //data->binding.textView.setText(data)이 실행된다
            //이벤트(인터페이스)가 등록된 프래그먼트의 세터 호출
            ((MainActivity)getActivity()).tabContent1.setOnDataTransferListener(data->binding.textView.setText(data));

        }
        //3]루트 뷰 반환
        return view;
    }//////////////////////

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //프래그먼트는 뷰보다 오래 지속됩니다.프래그먼트의 onDestroyView() 메서드에서 결합 클래스 인스턴스 참조를 정리해야 합니다.

        //바인딩 클래스(TabmenuLayout1Binding) 인스턴스 참조를 null로 설정
        binding=null;
    }
}