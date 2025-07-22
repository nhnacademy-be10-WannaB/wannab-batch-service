package shop.wannab.batchserver.batch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSpend {

    private Long userId;
    private Long totalBookPriceSum;
    private Long totalDiscountAmountSum;

    public Long getActualSpent() {
        return totalBookPriceSum - totalDiscountAmountSum;
    }

}
