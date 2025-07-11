package shop.wannab.batchserver.user.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import shop.wannab.batchserver.user.domain.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;

public interface UserRepository extends JpaRepository<User, Long>{
    @EntityGraph(attributePaths = {"userGrade"})
    List<User> findAll();
}
