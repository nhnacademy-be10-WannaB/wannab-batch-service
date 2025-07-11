package shop.wannab.batchserver.batch.model;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;
import shop.wannab.batchserver.user.domain.entity.UserGrade;

@Component
@StepScope
@RequiredArgsConstructor
@Getter
public class GradePolicy {
    private List<UserGrade> userGradeList;
    private final EntityManager entityManager;

    @PostConstruct
    public void init() {
        this.userGradeList = entityManager.createQuery(
                "select p from UserGrade p order by p.gradeId asc",
                UserGrade.class
        ).getResultList();
    }


}
