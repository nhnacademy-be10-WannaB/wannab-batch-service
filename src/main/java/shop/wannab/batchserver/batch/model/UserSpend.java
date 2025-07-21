package shop.wannab.batchserver.batch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSpend {

    private Long userId;
    private int totalBookPriceSum;
    private int totalDiscountAmountSum;

    public int getActualSpent() {
        return totalBookPriceSum - totalDiscountAmountSum;
    }

}
