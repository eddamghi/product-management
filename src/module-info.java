module TPJavaFx {
	 requires javafx.base;
	 requires javafx.fxml;
	 requires javafx.graphics;
	 requires javafx.controls;
	 requires lombok;
	 requires java.sql;
	 requires org.xerial.sqlitejdbc;
	 opens presentation;
	 opens presentation.products;
	 opens persistence.entities to javafx.base;
}