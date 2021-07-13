package com.fis.app.dao;
  import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.List;

	import com.fis.app.exce.NoDeviceFoundException;
	import com.fis.app.model.ElectrnoicDevice;




	public class ElectronicDeviceDAOImpl implements IElectronicDAO{

		Connection con = null;

		String insertDeviceQuery = "insert into fis.electronicdevice values(?,?,?,?,?,?,?)";
		String selectAllDevices = "select * from fis.electronicdevice";
		String selectDeviceBasedOnID = "select * from fis.electronicdevice where deviceId = ?";
		String updateQuery = "Update fis.electronicdevice set cost=? where deviceId=?";
		String updateRatingQuery = "Update fis.electronicdevice set deviceType=? where deviceId=?";
@Override
		public boolean addDevice(ElectrnoicDevice device) {

			con = DatabaseUtil.getConnection();
			boolean isInserted = false;
			if (con != null) {

				int deviceId = device.getDeviceId();
				String deviceType = device.getDeviceType();
				String brandName = device.getBrandName();
				int cost = device.getCost();
				int power = device.getPower();
				int starRatings = device.getStarRatings();
				String color = device.getColor();
PreparedStatement ps;
				try {
					ps = con.prepareStatement(insertDeviceQuery);

					ps.setInt(1, deviceId);
					ps.setString(2, deviceType);
					ps.setString(3, brandName);
					ps.setInt(4, cost);
					ps.setInt(5, power);
					ps.setInt(6, starRatings);
					ps.setString(7, color);

					int i = ps.executeUpdate(); 
					if (i > 0)
						isInserted = true;

					return isInserted;

				} 
				catch (SQLException e) {

					System.out.println("Invalid \n");
				}
			}
			return false;
		}
		@Override
		public List<ElectrnoicDevice> getAllDevices() {

			con = DatabaseUtil.getConnection();
			List<ElectrnoicDevice> deviceList = new ArrayList<>();
			if (con != null) {

				try {

					PreparedStatement ps = con.prepareStatement(selectAllDevices);

					ResultSet rs = ps.executeQuery();

					while (rs.next()) {
						ElectrnoicDevice temp = new ElectrnoicDevice(); // default emp,

						temp.setDeviceId(rs.getInt(1));
						temp.setDeviceType(rs.getString(2));
						temp.setBrandName(rs.getString(3));
						temp.setCost(rs.getInt(4));
						temp.setPower(rs.getInt(5));
						temp.setStarRatings(rs.getInt(6));
						temp.setColor(rs.getString(7));

						deviceList.add(temp);
					}
				} 
				catch (SQLException e) {
					System.out.println("Wrong access or invalid id");
				}
			}
			return deviceList;
		}
		@Override
		public boolean changeDevicePrice(int id, int newPrice) throws NoDeviceFoundException {

			con = DatabaseUtil.getConnection();
			boolean isUpdated = false;
			if (con != null) {

				PreparedStatement ps;
				try {
					ps = con.prepareStatement(updateQuery);
					ps.setInt(1, newPrice);
					ps.setInt(2, id);


					int i = ps.executeUpdate(); 
					if (i > 0)
						isUpdated = true;
					return isUpdated;

				} 
				catch (SQLException e) {

					System.out.println("Invalid Data Enter\n");
				}
			}
			return isUpdated;
		}
		@Override
		public boolean changeDeviceRating(int deviceId, int newRating) throws NoDeviceFoundException {
			con = DatabaseUtil.getConnection();
			boolean isUpdated = false;
			if (con != null) {

				PreparedStatement ps;
				try {
					ps = con.prepareStatement(updateRatingQuery);
					ps.setInt(1, newRating);
					ps.setInt(2, deviceId);


					int i = ps.executeUpdate(); 
					if (i > 0)
						isUpdated = true;

					return isUpdated;

				} 
				catch (SQLException e) {

					System.out.println("Enter Correct Data");
				}
			}
			return isUpdated;
		}
		@Override
		public boolean deleteDevice(int deviceId) throws NoDeviceFoundException {
			String deleteDevice = "DELETE FROM fis.electronicdevice WHERE deviceId=?";

			con = DatabaseUtil.getConnection();
			boolean isUpdated = false;
			if (con != null) {

				PreparedStatement ps;
				try {
					ps = con.prepareStatement(deleteDevice);
					ps.setInt(1, deviceId);


					int i = ps.executeUpdate(); 
					if (i > 0)
						isUpdated = true;

					return isUpdated;

				} 
				catch (SQLException e) {

					System.out.println("Enter Correct Data");
				}
			}
			return isUpdated;
		}
		@Override
		public List<ElectrnoicDevice> getDevicesBasedOnBrandNameAndType(String brandName, String type) {

			String EmployeeBasedOnBrandNameandTypeQuery = "select * from fisapp.electronicdevice where deviceType = ? AND brandName = ?";
			con = DatabaseUtil.getConnection();
			List<ElectrnoicDevice> DevList = new ArrayList<>();
			if (con != null) {

				PreparedStatement ps;
				try {
					ps = con.prepareStatement(EmployeeBasedOnBrandNameandTypeQuery);
					ps.setString(1, type);
					ps.setString(2, brandName);

					ResultSet rs = ps.executeQuery();
					boolean isFound = false;
					while (rs.next()) {
						isFound = true;
						ElectrnoicDevice temp = new ElectrnoicDevice();

						temp.setDeviceId(rs.getInt("idelectronicDevice"));
						temp.setDeviceType(rs.getString("deviceType"));
						temp.setBrandName(rs.getString("brandName"));
						temp.setCost(rs.getInt("cost"));
						temp.setPower(rs.getInt("power"));
						temp.setStarRatings(rs.getInt("starRating"));
						temp.setColor(rs.getString("color"));

						DevList.add(temp);
					} 
				}
				catch (SQLException e) {
					System.out.println("Enter Correct Data");
				}

			}
			return DevList;
		}


		@Override
		public int countDeviceType(String type) {
			String countDevType="SELECT deviceType, COUNT(deviceType) AS NumberofDevice FROM fisapp.electronicdevice where deviceType= ? group by deviceType ";

			con = DatabaseUtil.getConnection();
			int total = 0;
			if (con != null) {

				try {
					PreparedStatement ps;
					ps = con.prepareStatement(countDevType);
					ps.setString(1, type);


					ResultSet rs = ps.executeQuery(); 
					while(rs.next()) {
						total=rs.getInt(2);
					}
				} 
				catch (SQLException e) {

					System.out.println("Enter Correct Data");
				}
			}

			return total;
		}
		@Override
		public int getSumofPriceBasedOnType(String type) {

			String SumBasedOnTypeQuery = "SELECT SUM(cost) FROM fisapp.electronicdevice GROUP BY ?";

			con = DatabaseUtil.getConnection();
			int sum = 0;
			if (con != null) {
				try {
					PreparedStatement ps = con.prepareStatement(SumBasedOnTypeQuery);
					ps.setString(1, type);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						sum = rs.getInt("SUM(cost)");
					}
				} 
				catch (SQLException e) {
					System.out.println("Enter Correct Data");
				}
			}
			return sum;
		}


		@Override
		public List<ElectrnoicDevice> getDevicesBasedOnPriceRangeandType(int range1, int range2, String Type) {
			List<ElectrnoicDevice> DevList = new ArrayList<>();
			String DevicesBasedOnPriceRangeandTypeQuery="SELECT * FROM fisapp.electronicdevice where cost between ? and ? and deviceType=? ";

			if (con != null) {
				try {
					PreparedStatement ps = con.prepareStatement(DevicesBasedOnPriceRangeandTypeQuery);
					
					ps.setInt(1, range1);
					ps.setInt(2, range2);
					ps.setString(3, Type);

					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						ElectrnoicDevice temp = new ElectrnoicDevice();

						temp.setDeviceId(rs.getInt(1));
						temp.setDeviceType(rs.getString(2));
						temp.setBrandName(rs.getString(3));
						temp.setCost(rs.getInt(4));
						temp.setPower(rs.getInt(5));
						temp.setStarRatings(rs.getInt(6));
						temp.setColor(rs.getString(7));

						DevList.add(temp);
					}
				} 
				catch (SQLException e) {
					System.out.println("Problem fetching Data!! Try Again");
				}
			}
			return DevList;
		}
	}


