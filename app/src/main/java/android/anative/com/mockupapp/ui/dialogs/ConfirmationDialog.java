package android.anative.com.mockupapp.ui.dialogs;

import android.anative.com.mockupapp.R;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by Neeraj VijayVargiya on 10/11/17.
 */

public class ConfirmationDialog extends Dialog implements View.OnClickListener {
    private Context context;
    TextView txt_sure, btn_yes, btn_no;
    boolean isErrorDialog = false;
    String error;
    private ConfirmationDialogListener confirmationDialogListener;

    public void setConfirmationDialog(ConfirmationDialogListener confirmationDialogListener) {
        this.confirmationDialogListener = confirmationDialogListener;
    }

    public ConfirmationDialog(@NonNull Context context, boolean isErrorDialog, String error) {
        super(context);
        this.context = context;
        this.isErrorDialog = isErrorDialog;
        this.error = error;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDialog();
    }

    private void initDialog() {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        //Set transprent color for background of dialog
        getWindow().setBackgroundDrawableResource(R.color.colorBlack);

        // inflate view of this dialog
        LayoutInflater inflate = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflate.inflate(R.layout.dialog_confirmation, null);

        //set dialog view
        setContentView(layout);


        //setup dialog window param
        WindowManager.LayoutParams wlmp = getWindow().getAttributes();
        if (isErrorDialog) {
            wlmp.gravity = Gravity.BOTTOM;

        } else {
            wlmp.gravity = Gravity.CENTER;

        }
        wlmp.width = WindowManager.LayoutParams.MATCH_PARENT;
        setTitle(null);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        setOnCancelListener(null);
        btn_no = (TextView) findViewById(R.id.btn_no);
        btn_yes = (TextView) findViewById(R.id.btn_yes);
        txt_sure = (TextView) findViewById(R.id.txt_sure);
        if (isErrorDialog) {
            btn_yes.setVisibility(View.GONE);
            btn_no.setText("Ok");
            txt_sure.setText(error);
        }
        btn_no.setOnClickListener(this);
        btn_yes.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_no:
                dismiss();
                break;
            case R.id.btn_yes:
                // save to db
                if (confirmationDialogListener != null) {
                    confirmationDialogListener.onConfirmed();
                }
                dismiss();
                break;
        }
    }


    public interface ConfirmationDialogListener {
        void onConfirmed();
    }
}
