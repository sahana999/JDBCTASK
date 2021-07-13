package com.fis.app.test;
import java.util.List;

	import com.fis.app.dao.ElectronicDeviceDAOImpl;
	import com.fis.app.dao.IElectronicDAO;
	import com.fis.app.exce.NoDeviceFoundException;
	import com.fis.app.model.ElectrnoicDevice;


	public class TestRunner {

		public static void main(String[] args) {
			IElectronicDAO dao = new ElectronicDeviceDAOImpl();
			try
			{
	boolean ed=dao.changeDeviceRating(120, 5);
			
		}catch(Exception e)
			{
			System.out.println(e);
			}


}
	}
