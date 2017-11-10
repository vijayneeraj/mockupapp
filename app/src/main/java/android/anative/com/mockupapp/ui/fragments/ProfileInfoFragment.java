package android.anative.com.mockupapp.ui.fragments;

import android.anative.com.mockupapp.R;
import android.anative.com.mockupapp.datbase.ProfileTable;
import android.anative.com.mockupapp.ui.dialogs.ConfirmationDialog;
import android.anative.com.mockupapp.ui.dialogs.SelectionDialog;
import android.anative.com.mockupapp.utilis.AppUtils;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import petrov.kristiyan.colorpicker.ColorPicker;

/**
 * Created by Neeraj VijayVargiya on 10/11/17.
 */

public class ProfileInfoFragment extends BaseFragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener, ColorPicker.OnChooseColorListener, ConfirmationDialog.ConfirmationDialogListener {
    public static final String QUALIFICATION_ENGINEER = "Engineer";
    public static final String QUALIFICATION_IT = "IT";
    public static final String QUALIFICATION_MARKETING = "Marketing";
    public static final String QUALIFICATION_PGDBA = "PGDBA";
    public static final String QUALIFICATION_GRADUATE = "Graduate";
    public static final String QUALIFICATION_MTECH = "M.Tech";
    View view;
    EditText userName, commentBox;
    TextView department, txt_pets, txt_date;
    Button submitBtn;
    RadioGroup radioGroup;
    CheckBox engineer, it, marketing, pgdba, graduate, mtech;
    ImageView img_cal;
    List<String> qualificationList;
    String errorValidation;
    String date;
    String gender;
    ColorPicker colorPicker;
    FrameLayout colorBox;

    public static ProfileInfoFragment newInstance() {

        Bundle args = new Bundle();

        ProfileInfoFragment fragment = new ProfileInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.profile_info_fragment, container, false);
        }
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        qualificationList = new ArrayList<>();
        colorPicker = new ColorPicker(getActivity());
        colorPicker.setOnChooseColorListener(this);

        colorBox = (FrameLayout) view.findViewById(R.id.colorBox);
        userName = (EditText) view.findViewById(R.id.userName);
        commentBox = (EditText) view.findViewById(R.id.commentBox);
        department = (TextView) view.findViewById(R.id.department);
        txt_pets = (TextView) view.findViewById(R.id.txt_pets);
        txt_date = (TextView) view.findViewById(R.id.txt_date);
        submitBtn = (Button) view.findViewById(R.id.submitBtn);
        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        engineer = (CheckBox) view.findViewById(R.id.engineer);
        it = (CheckBox) view.findViewById(R.id.it);
        marketing = (CheckBox) view.findViewById(R.id.marketing);
        pgdba = (CheckBox) view.findViewById(R.id.pgdba);
        graduate = (CheckBox) view.findViewById(R.id.graduate);
        mtech = (CheckBox) view.findViewById(R.id.mtech);
        img_cal = (ImageView) view.findViewById(R.id.img_cal);
        department.setOnClickListener(this);
        colorBox.setOnClickListener(this);
        txt_pets.setOnClickListener(this);
        txt_date.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
        img_cal.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == R.id.male) {
                    gender = "Male";
                } else {
                    gender = "Female";
                }
                Toast.makeText(getActivity(), "id:" + i, Toast.LENGTH_SHORT).show();
            }
        });
        engineer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    qualificationList.add(QUALIFICATION_ENGINEER);
                } else {
                    qualificationList.remove(QUALIFICATION_ENGINEER);
                }
            }
        });
        it.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    qualificationList.add(QUALIFICATION_IT);
                } else {
                    qualificationList.remove(QUALIFICATION_IT);
                }
            }
        });
        marketing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    qualificationList.add(QUALIFICATION_MARKETING);
                } else {
                    qualificationList.remove(QUALIFICATION_MARKETING);
                }
            }
        });
        pgdba.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    qualificationList.add(QUALIFICATION_PGDBA);
                } else {
                    qualificationList.remove(QUALIFICATION_PGDBA);
                }
            }
        });
        graduate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    qualificationList.add(QUALIFICATION_GRADUATE);
                } else {
                    qualificationList.remove(QUALIFICATION_GRADUATE);
                }
            }
        });
        mtech.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    qualificationList.add(QUALIFICATION_MTECH);
                } else {
                    qualificationList.remove(QUALIFICATION_MTECH);
                }
            }
        });

    }

    @Override
    public void

    onClick(View view) {
        Calendar calendar = Calendar.getInstance();
        switch (view.getId()) {
            case R.id.department:
                // TODO: 10/11/17
                showDepartments();
                break;
            case R.id.txt_pets:
                showPets();
                // TODO: 10/11/17  
                break;
            case R.id.txt_date:
                // TODO: 10/11/17
                new DatePickerDialog(getActivity(), this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)).show();
                break;
            case R.id.submitBtn:
                // TODO: 10/11/17
                if (formValidation()) {
                    ConfirmationDialog confirmationDialog = new ConfirmationDialog(getActivity(), false, "");
                    confirmationDialog.setConfirmationDialog(this);
                    confirmationDialog.show();
                } else {
                    new ConfirmationDialog(getActivity(), true, errorValidation).show();
                }
                break;
            case R.id.img_cal:
                // TODO: 10/11/17
                new DatePickerDialog(getActivity(), this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)).show();

                break;
            case R.id.colorBox:
                colorPicker.show();
                break;
        }
    }

    private void showPets() {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            stringList.add("" + i);
        }
        new SelectionDialog<String>(getActivity(), "Select Pets Count", stringList, new SelectionDialog.SelectionDialogListner() {
            @Override
            public void itemSelected(Dialog dialog, int pos, Object object) {
                dialog.dismiss();
                String data = (String) object;
                txt_pets.setText(data);
            }
        }).show();
    }

    private void showDepartments() {
        List<String> listString = new ArrayList<>();
        listString = createDepartmentList(listString);
        new SelectionDialog<String>(getActivity(), "Select Department", listString, new SelectionDialog.SelectionDialogListner() {
            @Override
            public void itemSelected(Dialog dialog, int pos, Object object) {
                dialog.dismiss();
                String data = (String) object;
                department.setText(data);
            }
        }).show();
    }

    private List<String> createDepartmentList(List<String> list) {
        list.add("Physics");
        list.add("Maths");
        list.add("Chemistry");
        list.add("English");
        list.add("Spanish");
        list.add("French");
        list.add("Java");
        list.add("Xml");
        list.add("Electronics");
        list.add("Information Technology");
        return list;
    }

    private boolean formValidation() {
        boolean isValid = true;
        if (!AppUtils.isValidString(userName.getText().toString())) {
            errorValidation = "Invalid username!";
            return false;
        }
        if (!AppUtils.isValidString(department.getText().toString())) {
            errorValidation = "Please select department!";
            return false;
        }

        if (!AppUtils.isValidString(gender)) {
            errorValidation = "Please select the gender!";
            return false;
        }
        if (qualificationList.size() <= 0) {
            errorValidation = "Please select at least single qualification!";
            return false;
        }
        //place color validation here
        if (!AppUtils.isValidString(txt_pets.getText().toString())) {
            errorValidation = "Please select pet counts";
            return false;
        }
        if (!AppUtils.isValidString(commentBox.getText().toString())) {
            errorValidation = "Please write about your profile!";
            return false;
        }
        if (!AppUtils.isValidString(date)) {
            errorValidation = "Please select DOB!";
            return false;
        }
        return isValid;
    }

    private void clearData() {
        Log.e("dfsfd","ZXvcsvxs");
        userName.setText(null);
        department.setText(null);
        date = null;
        txt_date.setText("  /  / ");
        commentBox.setText(null);
        txt_pets.setText(null);
        new ConfirmationDialog(getActivity(), true, "Thanks! data saved.").show();

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        int day = datePicker.getDayOfMonth();
        date = day + " / " + month + " / " + year;
        txt_date.setText(date);
    }

    @Override
    public void onChooseColor(int position, int color) {
        colorBox.setBackgroundColor(color);
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onConfirmed() {
        ProfileTable profileTable = new ProfileTable();
        profileTable.set_id(System.currentTimeMillis());
        profileTable.setUser_name(userName.getText().toString());
        profileTable.setDepartment(department.getText().toString());
        profileTable.setGender(gender);
        profileTable.setQualification(qualificationList.toString());
        profileTable.setColor(colorBox.getDrawingCacheBackgroundColor());
        profileTable.setPets_count(txt_pets.getText().toString());
        profileTable.setAbout(commentBox.getText().toString());
        profileTable.setDate(txt_date.getText().toString());
        profileTable.save();
        clearData();

    }
}
