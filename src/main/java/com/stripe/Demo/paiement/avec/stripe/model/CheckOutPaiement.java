package com.stripe.Demo.paiement.avec.stripe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckOutPaiement {
    private String name;
    private String currency;
    // our success and cancel url stripe will redirect to this links
    private String successUrl;
    private String cancelUrl;
    private long amount;
    private long quantity;
}
