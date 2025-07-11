package shop.wannab.batchserver.batch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSpend {
    @Getter
    private Long userId;
    private int totalBookPriceSum;
    private int totalDiscountAmountSum;

    public UserSpend() {
    }

    public UserSpend(Long userId, Long totalBookPriceSum, Long totalDiscountAmountSum) {
        this.userId = userId;
        this.totalBookPriceSum = totalBookPriceSum.intValue();
        this.totalDiscountAmountSum = totalDiscountAmountSum.intValue();
    }

    public int getActualSpent() {
        return totalBookPriceSum - totalDiscountAmountSum;
    }

}
