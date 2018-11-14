import static org.junit.jupiter.api.Assertions.*;

import org.car.rental.CarRentalApplication;
import org.car.rental.database.CarRentalDB;
import org.car.rental.resources.CarUtil;
import org.junit.jupiter.api.Test;

class CarRentalTest {

	CarRentalApplication app = new CarRentalApplication();

	@Test
	void testTotalCarInvoice() {
		CarUtil.loadCarInvoiceFile();
		System.out.println(CarRentalDB.getCarInvoiceList().toString());
		assertEquals(27, CarRentalDB.getCarInvoiceList().size());
	}
	
	@Test
	void testBookACarWithoutOverlapping() {
		CarRentalApplication app = new CarRentalApplication();
		//input param - car Number, Start Date(mm-dd-yyy), Start Time(HH:mm), number of Days
		assertTrue(app.getService().bookACar(103, "04-02-2018", "10:15", 3));
		assertTrue(app.getService().bookACar(103, "04-05-2018", "10:20", 1));
		assertTrue(app.getService().bookACar(103, "03-11-2018", "10:15", 2));
		assertTrue(app.getService().bookACar(103, "03-17-2018", "10:20", 1));
	}
	
	@Test
	void testBookACarWithOverlapping() {
		CarRentalApplication app = new CarRentalApplication();
		//input param - car Number, Start Date(mm-dd-yyy), Start Time(HH:mm), number of Days
		assertTrue(app.getService().bookACar(105, "04-02-2018", "10:15", 3));
		//overlap 5 minutes before the end date of previous booking
		assertFalse(app.getService().bookACar(105, "04-05-2018", "10:10", 1));
		//reserve new one without overlapping
		assertTrue(app.getService().bookACar(107, "04-02-2018", "10:15", 3));
		//overlap with couple of days
		assertFalse(app.getService().bookACar(107, "04-03-2018", "10:20", 1));
		//reserver new booking without overlapping
		assertTrue(app.getService().bookACar(110, "04-07-2018", "10:15", 3));
		//reserver date overlap the previous booking date
		assertFalse(app.getService().bookACar(110, "04-05-2018", "10:20", 7));
	}
	

}
