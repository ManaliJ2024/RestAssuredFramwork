package api.payloads;

import java.util.Date;

public class BookingDates {

	private String checkin;
	private String checkout;
	
	public BookingDates() {
		
	}
	
	public BookingDates(String cin,String cout) {
		setCheckin(cin);
		setCheckout(cout);
	}

	public String getCheckin() {
		return checkin;
	}

	public void setCheckin(String cin) {
		this.checkin = cin;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String cout) {
		this.checkout = cout;
	}

}
