package com.kosmo.compoundbutton12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.kosmo.compoundbutton12.databinding.ActivityMainBinding;

import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private ActivityMainBinding binding;
    //스피너 아이템 저장용
    private String[] items;

    //[결과 출력을 위한 필드들]
    //체크박스
    private List<String> inters=new Vector<>();
    //라디오버튼
    private String gender="남성";
    //스피너
    private String curiculumn ="JSP/SERVLET";
    //토글버튼
    private String onOff="OFF";
    // 스위치
    private String bluetooth="ON";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //체크박스 제어
        binding.checkEntertainments.setChecked(false);
        binding.checkEconomics.setChecked(true);
        inters.add("경제");//최초 선택한 값 컬렉션에 저장

        //라디오버튼 제어
        //라디오 그룹으로 라디오버튼 체크 해제:clearCheck()
        binding.radioGroup.clearCheck();
        //라디오 그룹으로 라디오버튼 체크 :check(리소스 아이디)
        binding.radioGroup.check(R.id.radio_male);

        //토글버튼
        binding.toggleButton.setChecked(false);
        //스위치
        binding.switchButton.setChecked(true);
        //스피너에 아이템 바인딩하기
        items =getResources().getStringArray(R.array.items);
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,items);
        binding.spinner.setAdapter(adapter);
        //처음에 보여줄 아이템 선택:
        //xml속성에는 없음.AbsSpinner클래스가 갖고 있는 메소드임.
        binding.spinner.setSelection(5);

        //리스너 부착
        //체크박스
        binding.checkEconomics.setOnCheckedChangeListener(this);
        binding.checkEntertainments.setOnCheckedChangeListener(this);
        binding.checkPolitics.setOnCheckedChangeListener(this);
        binding.checkSports.setOnCheckedChangeListener(this);
        //토글버튼
        binding.toggleButton.setOnCheckedChangeListener(this);
        //스위치
        binding.switchButton.setOnCheckedChangeListener(this);

        //※라디오버튼-라디오그룹에 리스너 부착해라
        binding.radioGroup.setOnCheckedChangeListener((radioGroup,checkedId) ->{
            //checkedId는 체크된 라디오 버튼의 리소스 ID값(int)
            gender=((RadioButton)findViewById(checkedId)).getText().toString();
            Log.i("com.kosmo.compound",gender+" 선택");
            //체크된 아이디를 인자로 받지않는 다른 메소드에서 체크한 라디오버튼 알아내기
            RadioButton checkedRadio=(RadioButton)findViewById(binding.radioGroup.getCheckedRadioButtonId());
            Log.i("com.kosmo.compound",checkedRadio.getText()+" 선택");
        });
        //※스피너에는 setOnItemClickListener가 아니라 setOnItemSelectedListener를 붙여라.
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                //adapterView:여기서는 Spinner를 의미
                //view:하나의 아이템을 표시하는 텍스트 뷰(CheckedTextView)
                //position:The position of the view in the adapter
                //id:The row id of the item that is selected
                Log.i("com.kosmo.compound",String.format("position:%s,id:%s",position,id));
                Log.i("com.kosmo.compound",String.format("커리큘럼:%s",items[position]));
                Log.i("com.kosmo.compound",String.format("커리큘럼:%s",((TextView)view).getText()));
                curiculumn=((TextView)view).getText().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        //확인버튼 이벤트
        binding.button.setOnClickListener(view->{
            binding.textview.setText(String.format("체크박스 : %s%n라디오버튼 : %s%n토글버튼 : %s%n스위치 : %s%n스피터 : %s",
                        inters,
                        gender,
                        onOff,
                        bluetooth,
                        curiculumn
                    ));
        });
    }/////////onCreate

    //CompoundButton계열 버튼(CheckBox/ToggleButton/Switch)의 Checked속성이 변경될때마다 호출됨
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        Log.i("com.kosmo.compound","체크 여부:"+isChecked);
        if(compoundButton instanceof CheckBox){
            if(isChecked){
                Log.i("com.kosmo.compound",compoundButton.getText()+" 선택");
                inters.add(compoundButton.getText().toString());
            }
            else{
                Log.i("com.kosmo.compound",compoundButton.getText()+" 해제");
                inters.remove(compoundButton.getText());
            }
        }
        else if (compoundButton.getId()==R.id.toggleButton) {
            Log.i("com.kosmo.compound",isChecked?"토글 온":"토글 오프");
            onOff=isChecked?"ON":"OFF";
        }
        else{
            Log.i("com.kosmo.compound",isChecked?"블루투스 온":"블루투스 오프");
            bluetooth=isChecked?"ON":"OFF";
        }
    }
}