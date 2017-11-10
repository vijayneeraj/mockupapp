package android.anative.com.mockupapp.ui.dialogs;

import android.anative.com.mockupapp.MyApplication;
import android.anative.com.mockupapp.R;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


/**
 * @author Manish Kumar
 *         <p>
 *         SelectionDialog
 *         <p>
 *         <p>
 *         This Class is use for show list for select any single item
 */
public class SelectionDialog<T> extends Dialog implements
        View.OnClickListener, AdapterView.OnItemClickListener {
    Context context;
    SelectionDialog dia;

    /**
     * Application class object
     */
    MyApplication myapp;

    /**
     * This is header for this dialog
     */
    String msg = "";

    /**
     * This is list of any object
     */
    List<T> dataList;

    /**
     * Listener for this dialog
     */
    SelectionDialogListner listner;

    //All Views of Dialog
    TextView tv_diaheader;
    ListView lv_items;

    /**
     * @param context
     * @param msg
     * @param dataList
     * @param listner
     */

    public SelectionDialog(Context context, String msg, List<T> dataList, SelectionDialogListner listner) {
        super(context);
        this.context = context;
        dia = this;
        this.dataList = dataList;
        this.listner = listner;
        this.msg = msg;
        myapp = (MyApplication) context.getApplicationContext();

    }

    /**
     * Set header for this dialog
     *
     * @param msg
     */
    public void setMessage(String msg) {
        this.msg = msg;
    }


    /**
     * This method is call when dialog is create
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        //Set No title for dialog
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        //Set transprent color for background of dialog
        getWindow().setBackgroundDrawableResource(R.color.colorBlack);

        // inflate view of this dialog
        LayoutInflater inflate = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflate.inflate(R.layout.dialog_selectiondialog, null);

        //set dialog view
        setContentView(layout);


        //setup dialog window param
        WindowManager.LayoutParams wlmp = getWindow().getAttributes();
        wlmp.gravity = Gravity.BOTTOM;
        wlmp.width = WindowManager.LayoutParams.MATCH_PARENT;
        setTitle(null);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        setOnCancelListener(null);
        setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                tv_diaheader.setText(msg.isEmpty() ? "" : msg);
            }
        });

        //Setup dialog views and clicks
        tv_diaheader = (TextView) layout.findViewById(R.id.tv_diaheader);
        lv_items = (ListView) layout.findViewById(R.id.lv_items);
        DataAdapter adapter = new DataAdapter(context, dataList);
        lv_items.setAdapter(adapter);
//        if (adapter.getCount() > 5) {
//            View item = adapter.getView(0, null, lv_items);
//            int d_h = lv_items.getDividerHeight();
//            item.measure(0, 0);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) (5.5 * (item.getMeasuredHeight() + d_h)));
//            lv_items.setLayoutParams(params);
//        }


        lv_items.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        if (v.getId() == R.id.tv_okbtn) {
//            this.dismiss();
//        }
    }

    /**
     * This method is use for listen item click from {@link #lv_items}
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (this.listner != null) {
            this.listner.itemSelected(SelectionDialog.this, position, dataList.get(position));
        }
    }

    /**
     * SelectionDialogListner
     * This is use for listen item click
     *
     * @param <T>
     */
    public interface SelectionDialogListner<T> {

        /**
         * This metho d is call when {@link #lv_items} item click is call
         *
         * @param dialog
         * @param pos
         * @param object
         */
        void itemSelected(Dialog dialog, int pos, T object);

    }

    /**
     * DataAdapter
     * <p>
     * This class is use for {@link #lv_items} adapter
     */
    class DataAdapter extends ArrayAdapter<T> {

        public DataAdapter(Context context, List<T> objects) {
            super(context, R.layout.item_dialog_adapter_selection, R.id.tv_item, objects);
        }


    }


}
