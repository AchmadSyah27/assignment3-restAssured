package com.jakartalabs.rest.assured;

public class AppEnums {

	public enum MenuNames {

		LOGIN("Log In"), BLOG("Blog");

		private String filter;

		private MenuNames(String filter) {
			this.filter = filter;
		}

		@Override
		public String toString() {
			return filter;

		}

	}

	public enum NavigationCommands {

		BACK("Back"), FORWARD("Forward"), REFRESH("Refresh");

		private String filter;

		private NavigationCommands(String filter) {
			this.filter = filter;
		}

		@Override
		public String toString() {
			return filter;

		}

	}
}