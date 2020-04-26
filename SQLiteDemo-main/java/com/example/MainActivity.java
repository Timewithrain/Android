package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    Integer selectNumber;
    TextView tvStatus;
    EditText etName;
    EditText etGender;
    EditText etAge;
    Button btnAdd;
    Button btnQuery;
    Button btnUpdate;
    Button btnDelete;
    ListView lvPerson;
    List<Person> list;
    DAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DatabaseOpenHelper helper = new DatabaseOpenHelper(this);
//        helper.getWritableDatabase();

        //初始化设置被选中项的值为-1表示未选中任何项目
        selectNumber = -1;
        dao = new DAO(this);
        tvStatus = findViewById(R.id.activity_main_tv_status);
        etName = findViewById(R.id.activity_main_et_name);
        etGender = findViewById(R.id.activity_main_et_gender);
        etAge = findViewById(R.id.activity_main_et_age);
        btnAdd = findViewById(R.id.activity_main_btn_add);
        btnQuery = findViewById(R.id.activity_main_btn_query);
        btnUpdate = findViewById(R.id.activity_main_btn_update);
        btnDelete = findViewById(R.id.activity_main_btn_delete);
        lvPerson = findViewById(R.id.activity_main_lv_persons);

        btnAdd.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        lvPerson.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_main_btn_add:
                addPerson();
                tvStatus.setText("添加完成");
                break;
            case R.id.activity_main_btn_query:
                queryPerson();
                tvStatus.setText("查询完成");
                break;
            case R.id.activity_main_btn_update:
                updatePerson();
                tvStatus.setText("更新完成");
                break;
            case R.id.activity_main_btn_delete:
                deletePerson();
                tvStatus.setText("删除完成");
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        selectNumber = i;
        Person person = list.get(selectNumber);
        etName.setText(person.getName());
        etGender.setText(person.getGender());
        etAge.setText(person.getAge()+"");
    }

    void queryPerson(){
        list = dao.query();
        String[] persons = listToArray(list);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,persons);
        lvPerson.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvPerson.setAdapter(adapter1);
        //查询完成后设置未选中任何项目，设置输入栏为空
        selectNumber = -1;
        etName.setText("");
        etGender.setText("");
        etAge.setText("");
    }

    void addPerson(){
        //若selectNumber不为-1则不能进行添加操作
        if (selectNumber==-1){
            Person person = new Person();
            Integer id = dao.getPersonNumber()+1;
            String name = etName.getText().toString();
            String gender = etGender.getText().toString();
            String age = etAge.getText().toString();
            Boolean nameNull = name.equals("");
            Boolean genderNull = gender.equals("");
            Boolean ageNull = age.equals("");
            if(!nameNull&&!genderNull&&!ageNull){
                person.setId(id);
                person.setName(name);
                person.setGender(gender);
                person.setAge(Integer.parseInt(age));
                dao.insert(person);
                queryPerson();
            }
        }
    }

    void updatePerson(){
        if (selectNumber!=-1){
            Person person = list.get(selectNumber);
            String name = etName.getText().toString();
            String gender = etGender.getText().toString();
            String age = etAge.getText().toString();
            Boolean nameNull = name.equals("");
            Boolean genderNull = gender.equals("");
            Boolean ageNull = age.equals("");
            if(!nameNull&&!genderNull&&!ageNull){
                person.setName(name);
                person.setGender(gender);
                person.setAge(Integer.parseInt(age));
                dao.update(person);
                queryPerson();
            }
        }
    }

    void deletePerson(){
        if (selectNumber!=-1){
            Person person = list.get(selectNumber);
            dao.delete(person.getId());
            //删除以后刷新列表
            queryPerson();
        }
    }

    private String[] listToArray(List<Person> list){
        String[] array = new String[list.size()];
        for (int i=0;i<list.size();i++){
            StringBuffer sb = new StringBuffer();
            Person p = list.get(i);
            sb.append("姓名:"+p.getName()+", 性别:"+p.getGender()+", 年龄:"+p.getAge());
            array[i] = sb.toString();
        }
        return array;
    }

}
