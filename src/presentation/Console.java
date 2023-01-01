package presentation;

import persistence.SingletonConnectionDatabase;

import java.sql.SQLException;

public class Console {
	 public static void main(String[] args) {
		  var connection = SingletonConnectionDatabase.getConnection();
		  try {
				var statement = connection
						  .prepareStatement("SELECT * FROM Product");
				var resultSet = statement.executeQuery();
				while (resultSet.next()) {
					 System.out.println(resultSet.getString("name"));
				}
		  } catch (SQLException e) {
				throw new RuntimeException(e);
		  }
	 }
}
