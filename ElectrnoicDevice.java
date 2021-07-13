package com.fis.app.model;
   import java.util.Objects;

	public class ElectrnoicDevice {
		
		private int deviceId;
		private String deviceType;
		private String brandName;
		private int cost;
		private int power;
		private int starRatings;
		private String color;
		
		
		
		public ElectrnoicDevice() {
			super();
		}

		public ElectrnoicDevice(int deviceId, String deviceType, String brandName, int cost, int power, int starRatings,
				String color) {
			super();
			this.deviceId = deviceId;
			this.deviceType = deviceType;
			this.brandName = brandName;
			this.cost = cost;
			this.power = power;
			this.starRatings = starRatings;
			this.color = color;
		}

		public int getDeviceId() {
			return deviceId;
		}

		public void setDeviceId(int deviceId) {
			this.deviceId = deviceId;
		}

		public String getDeviceType() {
			return deviceType;
		}

		public void setDeviceType(String deviceType) {
			this.deviceType = deviceType;
		}

		public String getBrandName() {
			return brandName;
		}

		public void setBrandName(String brandName) {
			this.brandName = brandName;
		}

		public int getCost() {
			return cost;
		}

		public void setCost(int cost) {
			this.cost = cost;
		}

		public int getPower() {
			return power;
		}

		public void setPower(int power) {
			this.power = power;
		}

		public int getStarRatings() {
			return starRatings;
		}

		public void setStarRatings(int starRatings) {
			this.starRatings = starRatings;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}

		@Override
		public int hashCode() {
			return Objects.hash(brandName, color, cost, deviceId, deviceType, power, starRatings);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ElectrnoicDevice other = (ElectrnoicDevice) obj;
			return Objects.equals(brandName, other.brandName) && Objects.equals(color, other.color) && cost == other.cost
					&& deviceId == other.deviceId && Objects.equals(deviceType, other.deviceType) && power == other.power
					&& starRatings == other.starRatings;
		}

		@Override
		public String toString() {
			return "ElectronoicDevice [deviceId=" + deviceId + ", deviceType=" + deviceType + ", brandName=" + brandName
					+ ", cost=" + cost + ", power=" + power + ", starRatings=" + starRatings + ", color=" + color + "]";
		}
		
		
	}
