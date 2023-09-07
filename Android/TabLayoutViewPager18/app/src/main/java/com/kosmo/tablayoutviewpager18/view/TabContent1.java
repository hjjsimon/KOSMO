package com.kosmo.tablayoutviewpager18.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kosmo.tablayoutviewpager18.databinding.FragmentTabContent1Binding;


//1]Fragement상속
//※androidx.fragment.app.Fragment 상속
public class TabContent1 extends Fragment {

    private FragmentTabContent1Binding binding;

    //2]onCreateView()오버 라이딩
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i("com.kosmo.tablayout","TabContent1의 onCreateView");
        binding= FragmentTabContent1Binding.inflate(inflater,container,false);
        View view=binding.getRoot();

        //1번)액티비티->프래그먼트(로 전송한 데이타 받기):getArgments()
        Bundle bundle=getArguments();
        if(bundle !=null){
            binding.textDisplay.setText(bundle.getString("ActivityToFragment"));
        }
        //버튼의 이벤트 처리
        //STEP1. 데이타 전송 이벤트 용 인터페이스 정의
        //STEP2. 인터페이스 타입의 필드 정의
        //STEP3. 다른 프래그먼트로 전송을 위한  세터 정의 및
        //       액티비티로 전송을 위한 onAttach 오버라이딩
        //2번)프래그먼트->프래그먼트로 데이터 전송
        binding.btnFragment.setOnClickListener(v->{
            if(onDataTransferListener !=null){
                onDataTransferListener.onDataTransfer("다른 프래그먼트로 전송하는 데이타");
            }
        });
        //3번)프래그먼트->액티비티로 데이터 전송
        binding.btnActivity.setOnClickListener(v->{
            if(onDataTransferListener !=null){
                onDataTransferListener.onDataTransfer("액티비티로 전송하는 데이타");
            }
        });

        //3]루트 뷰 반환
        return view;
    }//////////////////////

    //STEP1. 데이타 전송 이벤트 용 인터페이스 정의(프래그먼트 혹은 액티비티로 전송을 위한)
    public interface OnDataTransferListener{
        //data:전송할 데이타.모든 타입 가능
        void onDataTransfer(String data);
    }
    //STEP2. 인터페이스 타입의 필드 정의 및 세터 정의
    private OnDataTransferListener onDataTransferListener;

    //STEP3. 다른 프래그먼트로 전송을 위한  세터 정의
    public void setOnDataTransferListener(OnDataTransferListener onDataTransferListener) {
        this.onDataTransferListener = onDataTransferListener;
    }
    //STEP3.액티비티로 전송을 위한 onAttach 오버라이딩
    //      ※단,현재 프래그먼트가 부착된 액티비티는 OnDataTransferListener를 구현한다


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.onDataTransferListener=(OnDataTransferListener)context;
    }/////////////////

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //프래그먼트는 뷰보다 오래 지속됩니다.프래그먼트의 onDestroyView() 메서드에서 결합 클래스 인스턴스 참조를 정리해야 합니다.

        //바인딩 클래스(TabmenuLayout1Binding) 인스턴스 참조를 null로 설정
        binding=null;
    }
}