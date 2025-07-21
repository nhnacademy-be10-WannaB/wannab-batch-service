package shop.wannab.batchserver.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.wannab.batchserver.user.domain.entity.UserGrade;

public interface UserGradeRepository extends JpaRepository<UserGrade, Long> {
}

