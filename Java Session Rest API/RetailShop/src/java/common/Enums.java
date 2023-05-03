package common;

import java.util.EnumMap;

/**
 *
 * @author RetailShop
 */
public class Enums {

    public enum UserType {
        Admin,
        Retailer,
        User,
    }

    public enum OrderStatus {
        Received,
        Approved,
        OnHold,
        Cancelled,
        Delivered,
        Completed,
        Returned,
    }

    public enum PaymentStatus {
        Pending,
        COD,
        Paypal,
        GooglePay,
        PhonePay,
        Paytm,
        RazorPay,
    }

    public EnumMap<UserType, Short> UserTypeEnum = new EnumMap<>(UserType.class);
    public EnumMap<OrderStatus, Short> OrderStatusEnum = new EnumMap<>(OrderStatus.class);
    public EnumMap<PaymentStatus, Short> PaymentStatusEnum = new EnumMap<>(PaymentStatus.class);

    public Enums() {

        UserTypeEnum.put(UserType.Admin, Short.parseShort("1"));
        UserTypeEnum.put(UserType.Retailer, Short.parseShort("2"));
        UserTypeEnum.put(UserType.User, Short.parseShort("3"));

        OrderStatusEnum.put(OrderStatus.Received, Short.parseShort("0"));
        OrderStatusEnum.put(OrderStatus.Approved, Short.parseShort("1"));
        OrderStatusEnum.put(OrderStatus.OnHold, Short.parseShort("2"));
        OrderStatusEnum.put(OrderStatus.Cancelled, Short.parseShort("3"));
        OrderStatusEnum.put(OrderStatus.Delivered, Short.parseShort("4"));
        OrderStatusEnum.put(OrderStatus.Completed, Short.parseShort("5"));
        OrderStatusEnum.put(OrderStatus.Returned, Short.parseShort("6"));

        PaymentStatusEnum.put(PaymentStatus.Pending, Short.parseShort("0"));
        PaymentStatusEnum.put(PaymentStatus.COD, Short.parseShort("1"));
        PaymentStatusEnum.put(PaymentStatus.Paypal, Short.parseShort("2"));
        PaymentStatusEnum.put(PaymentStatus.GooglePay, Short.parseShort("3"));
        PaymentStatusEnum.put(PaymentStatus.PhonePay, Short.parseShort("4"));
        PaymentStatusEnum.put(PaymentStatus.Paytm, Short.parseShort("5"));
        PaymentStatusEnum.put(PaymentStatus.RazorPay, Short.parseShort("6"));

    }
}
