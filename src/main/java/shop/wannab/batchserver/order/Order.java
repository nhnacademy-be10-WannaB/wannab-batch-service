package shop.wannab.batchserver.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @NotNull
    @Column(name = "order_name")
    private String orderName;

    @Column(name = "order_at")
    private LocalDateTime orderAt;

    @Setter
    @Column(name = "shipped_at")
    private LocalDateTime shippedAt;

    @Column(name = "delivery_want")
    private LocalDate deliveryWant;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Column(name = "total_book_price")
    private int totalBookPrice;

    @Column(name = "total_discount_amount")
    private int totalDiscountAmount;

    @Column(name = "shipping_fee")
    private int shippingFee;

    @NotNull
    @Column(name = "total_paving_price")
    private int totalPavingPrice;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "recipient_name")
    private String recipientName;

    @NotNull
    @Column(name = "recipient_email")
    private String recipientEmail;

    @NotNull
    @Column(name = "recipient_phone")
    private String recipientPhoneNumber;

    @NotNull
    @Column(name = "recipient_address")
    private String recipientAddress;
}
