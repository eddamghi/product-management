package presentation.products;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import models.ProductModel;
import persistence.ProductImpl;
import persistence.entities.Product;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductsController implements Initializable {
	@FXML private TextField textName;
	@FXML private TextField textPrice;
	@FXML private TextField textQuantity;
	@FXML private TextField textSearch;
	@FXML public TableView<Product> tableProductView;
	@FXML private TableColumn<Product, Integer> columnID;
	@FXML private TableColumn<Product, String> columnName;
	@FXML private TableColumn<Product, Float> columnPrice;
	@FXML private TableColumn<Product, Integer> columnQuantity;
	private final ObservableList<Product> productList = FXCollections.observableArrayList();
	private ProductModel productModel;
	 @Override
	 public void initialize(URL url, ResourceBundle resourceBundle) {
		 productModel=new ProductModel(new ProductImpl());
		 tableProductView.setItems(productList);

		 columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
		 columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		 columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		 columnQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity_in_stock"));
		 loadProducts();
		 textSearch.textProperty().addListener((observable, oldValue, newValue) -> {
			 if (newValue.isEmpty()) {
				 loadProducts();
			 } else {
				 productList.clear();
				 productList.setAll(productModel.getByKeyword(newValue));
			 }
		 });

		 tableProductView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> editProduct(newValue));
		 tableProductView.setEditable(true);
		 columnName.setCellFactory(TextFieldTableCell.forTableColumn());
		 columnPrice.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
		 columnQuantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
	 }
	@FXML
	private void addProduct(){
		String name = textName.getText();
		float price = Float.parseFloat(textPrice.getText());
		int quantity = Integer.parseInt(textQuantity.getText());
		if (name.isEmpty()){
			Alert alert=new Alert (Alert.AlertType.WARNING);
			alert.setContentText("Veuillez entrez un nom!");
			alert.show();
		}else{
			Product p = new Product();
			p.setName(name);
			p.setPrice(price);
			p.setQuantity_in_stock(quantity);
			productModel.addProduct(p);
			loadProducts();}
	}
	@FXML
	private void deleteProduct(){
		MultipleSelectionModel<Product> mp = tableProductView.getSelectionModel();
		if(mp != null){
			productModel.delete(mp.getSelectedItem());
			productList.remove(mp.getSelectedIndex());
		}
	}
	private void loadProducts(){
		productList.clear();
		productList.addAll(productModel.getAll());
	}
	private void editProduct(Product p) {
		if( p != null) {
			textName.setText(p.getName());
			textPrice.setText(String.valueOf(p.getPrice()));
			textQuantity.setText(String.valueOf(p.getQuantity_in_stock()));
		}
	}
	public void updateProduct() {
		Product product = tableProductView.getSelectionModel().getSelectedItem();
		product.setName(textName.getText());
		product.setPrice(Float.parseFloat(textPrice.getText()));
		product.setQuantity_in_stock(Integer.parseInt(textQuantity.getText()));
		productModel.update(product);
		loadProducts();
	}
	public void updateNameDirectly(TableColumn.CellEditEvent edited_cell){
		Product product = tableProductView.getSelectionModel().getSelectedItem();
		product.setName(edited_cell.getNewValue().toString());
		productModel.update(product);
	}
	public void updatePriceDirectly(TableColumn.CellEditEvent edited_cell){
		Product product = tableProductView.getSelectionModel().getSelectedItem();
		product.setPrice(Float.parseFloat(edited_cell.getNewValue().toString()));
		productModel.update(product);
	}
	public void updateQuantityDirectly(TableColumn.CellEditEvent edited_cell){
		Product product = tableProductView.getSelectionModel().getSelectedItem();
		product.setQuantity_in_stock(Integer.parseInt(edited_cell.getNewValue().toString()));
		productModel.update(product);
	}
}



