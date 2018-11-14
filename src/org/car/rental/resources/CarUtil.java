package org.car.rental.resources;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.car.rental.database.CarRentalDB;
import org.car.rental.model.Car;
import org.car.rental.model.CarType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CarUtil {
	
	private static DateFormat sdf = new SimpleDateFormat("mm-dd-yyyy hh:mm");

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); 
        return cal.getTime();
    }
    
	public static Date convertToDate(String dateStr, String time) {
		Date date = null;
		try {
			date = sdf.parse(dateStr + " " + time);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		return date;
	}
	
	public static String dateToString(Date date) {
		return (date != null) ? sdf.format(date) : "";
	}
	
	public static void loadCarInvoiceFile() {
		try {
			File file = new File("carinvoice.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
                if(line.startsWith("Name,") || line.startsWith("--------")){
                    continue;
                }

				String[] carInfo = line.split(","); 
				CarRentalDB.addCarToDB(new Car(carInfo[0].trim(), CarType.fromString(carInfo[1].trim()), carInfo[2].trim()), Integer.parseInt(carInfo[3].trim()));
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean dateBetween(Date date, Date dateStart, Date dateEnd) {
	    if (date != null && dateStart != null && dateEnd != null) {
	        if (date.after(dateStart) && date.before(dateEnd)) {
	            return true;
	        }
	        else {
	            return false;
	        }
	    }
	    return false;
	}

}
