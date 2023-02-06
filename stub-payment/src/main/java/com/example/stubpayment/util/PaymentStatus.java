package com.example.stubpayment.util;

import com.example.stubpayment.util.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentStatus {

    private Status status;

    private String user_message;

}
