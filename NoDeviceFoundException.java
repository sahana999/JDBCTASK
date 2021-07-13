package com.fis.app.exce;
     public class NoDeviceFoundException extends Exception {
		private int Deviceid;
		
		public NoDeviceFoundException(int Deviceid)
		{
			this.Deviceid = Deviceid;
		}
		
		public String toString()
		{
			return " Device "+Deviceid+ " Not found  ";
		}
}
