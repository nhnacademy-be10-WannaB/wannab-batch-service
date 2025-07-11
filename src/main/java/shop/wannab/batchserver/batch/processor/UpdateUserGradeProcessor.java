package shop.wannab.batchserver.batch.processor;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import shop.wannab.batchserver.batch.model.GradePolicy;
import shop.wannab.batchserver.batch.model.UserSpend;
import shop.wannab.batchserver.global.NullCheckUtil;
import shop.wannab.batchserver.user.domain.entity.User;
import shop.wannab.batchserver.user.domain.entity.UserGrade;

@Component
@StepScope
@RequiredArgsConstructor
public class UpdateUserGradeProcessor implements ItemProcessor<UserSpend, User> {

    private final EntityManager entityManager;
    private final GradePolicy gradePolicy;
    private UserGrade standardUserGrade;
    private UserGrade royalUserGrade;
    private UserGrade goldUserGrade;
    private UserGrade platinumUserGrade;

    @PostConstruct
    public void init() {
        List<UserGrade> userGradeList = gradePolicy.getUserGradeList();
        for (UserGrade userGrade : userGradeList) {
            switch (userGrade.getGradeName()) {
                case "Standard" -> standardUserGrade = userGrade;
                case "Royal" -> royalUserGrade = userGrade;
                case "Gold" -> goldUserGrade = userGrade;
                case "Platinum" -> platinumUserGrade = userGrade;
                default -> throw new IllegalArgumentException("UserGradeTable updated but code isn't, userGrade: " + userGrade.getGradeName()+" isn't supported");
            }
        }
    }

    @Override
    public User process(UserSpend usage) {
        NullCheckUtil.nullCheck(this, usage);
        User user = entityManager.find(User.class, usage.getUserId());
        Long currentGradeId = user.getUserGrade().getGradeId();
        Long newGradeId = determineNewGrade(usage.getActualSpent());

        NullCheckUtil.nullCheck(this, currentGradeId, newGradeId);
        if (currentGradeId == newGradeId){
            return null;
        }else{
            user.setUserGrade(getUserGradeByGradeId(newGradeId));
            return user;
        }
    }

    public Long determineNewGrade(int actualSpent) {
        if (actualSpent < 100000) {
            return standardUserGrade.getGradeId();
        } else if (actualSpent < 200000) {
            return royalUserGrade.getGradeId();
        } else if (actualSpent < 300000) {
            return goldUserGrade.getGradeId();
        } else {
            return platinumUserGrade.getGradeId();
        }
    }

    public UserGrade getUserGradeByGradeId(Long gradeId) {
        List<UserGrade> userGradeList = gradePolicy.getUserGradeList();
        for (UserGrade userGrade : userGradeList) {
            if (userGrade.getGradeId().equals(gradeId)) {
                return userGrade;
            }
        }
        throw new IllegalArgumentException("Unsupported user grade: " + gradeId);
    }
}
