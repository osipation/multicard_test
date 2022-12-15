package com.osipation.multicard_test.entity;

import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "purchase_info")
@XmlRootElement
public class PurchaseInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "purchase_item")
    private PurchaseItem purchaseItem;

    @Column(name = "count")
    private int count;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User userId;
}
