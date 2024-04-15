package com.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Products {
    @Column(name = "vendor_id")
    @NotNull
    Long vendorId;
    @Id
    @GeneratedValue
            (strategy = GenerationType.AUTO)
    Long productId;
    @Column(name = "product_name")
    @NotNull
    @NotBlank(message = "Product Name cannot be Empty")
    String productName;
    @Column(name = "price")
    @NotNull(message = "Price cannot be Empty")
    float price;
    @Column(name = "inserted_ts")
    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime insertedTS = LocalDateTime.now();
    @Column(name = "row_sts")
    String rowSTS;
}
