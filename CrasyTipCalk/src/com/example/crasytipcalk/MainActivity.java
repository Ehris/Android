package com.example.crasytipcalk;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	private static final String TOTAL_BILL = "TOTAL_BILL";
	private static final String CURRENT_TIP = "CURRENT_TIP";
	private static final String BILL_WHITOUT_TIP = "BILL_WHITOUT_TIP";
	
	private double billWhithoutTip;
	private double currentTip;
	private double totalBill;
	
	EditText billWhithoutTipET;
	EditText currentTipET;
	EditText totalBillET;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if (savedInstanceState == null) {
			
			billWhithoutTip = 0.0;
			currentTip = 0.15;
			totalBill = 0.0;
			
		}else {
			
			billWhithoutTip = savedInstanceState.getDouble(BILL_WHITOUT_TIP);
			currentTip = savedInstanceState.getDouble(CURRENT_TIP);
			totalBill = savedInstanceState.getDouble(TOTAL_BILL);
			
		}
		
		billWhithoutTipET = (EditText)findViewById(R.id.billEditText);
		currentTipET = (EditText)findViewById(R.id.tipEditText);
		totalBillET = (EditText)findViewById(R.id.finalBillEditText);
		
		billWhithoutTipET.addTextChangedListener(billWhithoutTipListener);
		
	}
	
	private TextWatcher billWhithoutTipListener  = new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			try {
				billWhithoutTip = Double.parseDouble(s.toString());
			} catch (NumberFormatException e) {
				billWhithoutTip = 0.0;
			}
			updateTipAndFinalBill();
		}
		
		private void updateTipAndFinalBill() {
			
			double tipAmount = Double.parseDouble(currentTipET.getText().toString());
			
			totalBill = billWhithoutTip + (billWhithoutTip * tipAmount);
			
			totalBillET.setText(String.format("%.02f", totalBill));
			
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			
		}
	};
	
	protected void onSaveInstanceState(Bundle outState) {
		
		super.onSaveInstanceState(outState);
		outState.putDouble(TOTAL_BILL, totalBill);
		outState.putDouble(BILL_WHITOUT_TIP, billWhithoutTip);
		outState.putDouble(CURRENT_TIP, currentTip);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
