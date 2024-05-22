package com.stripe.Demo.paiement.avec.stripe.web;

import com.google.gson.Gson;
import com.stripe.Demo.paiement.avec.stripe.model.CheckOutPaiement;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StripeController {
    public static void init(){
        Stripe.apiKey="pk_test_51MCgkyGuosnQxb0HRx3gO83PwAGNmIWZ1qA07cJ6Dv5qkHJeawLaHERZgKh6zRR1Q5Xkdb1pMOpOg8Lu321r5dmZ00uFC7074o";
    }
    private static  Gson gson=new Gson();
    @PostMapping("/payment")
    public String paiementWithCheckPage(@RequestBody CheckOutPaiement payement) throws StripeException {
     //init();
        SessionCreateParams params=SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT).setSuccessUrl(payement.getSuccessUrl())
                .setCancelUrl(payement.getCancelUrl())
                .addLineItem(SessionCreateParams.LineItem.builder().setQuantity(payement.getQuantity())
                        .setPriceData(
                                SessionCreateParams.LineItem.PriceData.builder()
                                        .setCurrency(payement.getCurrency()).setUnitAmount(payement.getAmount())
                                        .setProductData(SessionCreateParams.LineItem.PriceData.ProductData
                                                .builder().setName(payement.getName()).build())
                                        .build())
                        .build())
                .build();
        //Create a stripe session
        Session session=Session.create(params);
        Map<String,String> responseData=new HashMap<>();
        responseData.put("id",session.getId());
        return gson.toJson(responseData);

    }
}
