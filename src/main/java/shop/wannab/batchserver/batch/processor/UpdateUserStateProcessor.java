package shop.wannab.batchserver.batch.processor;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import shop.wannab.batchserver.global.NullCheckUtil;
import shop.wannab.batchserver.user.domain.entity.State;
import shop.wannab.batchserver.user.domain.entity.User;

@Component
@StepScope
@RequiredArgsConstructor
public class UpdateUserStateProcessor implements ItemProcessor<User, User> {

    @Override
    public User process(User user) {
        NullCheckUtil.nullCheck(this, user);
        if (user.getLastLoginAt().isBefore(LocalDate.now().minusMonths(3))) {
            user.setState(State.INACTIVATE);
            return user;
        }else {
            return null;
        }
    }
}
