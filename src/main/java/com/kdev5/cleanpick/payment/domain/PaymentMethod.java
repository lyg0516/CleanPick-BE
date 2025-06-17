package com.kdev5.cleanpick.payment.domain;

import com.kdev5.cleanpick.customer.domain.Customer;
import com.kdev5.cleanpick.global.entity.BaseTimeEntity;
import com.kdev5.cleanpick.payment.domain.enumeration.CardType;
import com.kdev5.cleanpick.payment.domain.enumeration.PayType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment_method")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentMethod extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PayType type;

    @Column(length = 255)
    private String info;

    private String cardCompany;

    private String cardNumber;

    private String billingKey;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @Builder
    public PaymentMethod(Customer customer, PayType type, String info, String cardCompany, String cardNumber,
        String billingKey, CardType cardType) {
        this.customer = customer;
        this.type = type;
        this.info = info;
        this.cardCompany = cardCompany;
        this.cardNumber = cardNumber;
        this.billingKey = billingKey;
        this.cardType = cardType;
    }
}