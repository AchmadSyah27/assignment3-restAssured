package restassured.assignment.utils;

import java.io.File;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class DataUtils {

	static String env = null == System.getProperty("env") ? "stage" : System.getProperty("env");

	static String dataFilePath = env.toLowerCase().equals("prod")
			? System.getProperty("user.dir") + File.separator + "resources" + File.separator + "TestDataProd.xlsx"
			: System.getProperty("user.dir") + File.separator + "resources" + File.separator + "TestData.xlsx";

	public static String getDataFromExcel(String sheetName, String fieldName) {
		String result = "error extracting value";
		System.out.println("Selected TestData File is:" + dataFilePath);

		try {
			Fillo fillo = new Fillo();
			Connection connection = fillo.getConnection(dataFilePath);
			String strQuery = "Select * from " + sheetName + " where ID='" + fieldName + "'";
			Recordset recordset = connection.executeQuery(strQuery);

			while (recordset.next()) {
				result = recordset.getField("Value");
			}

			recordset.close();
			connection.close();
		} catch (FilloException e) {
			e.printStackTrace();
		}

		return result;
	}
}
