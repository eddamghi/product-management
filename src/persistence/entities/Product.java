package persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    @Getter @Setter private int id;
    @Getter @Setter private String name;
    @Getter @Setter private float price;
    @Getter @Setter private int quantity_in_stock;
}
