package engine.repository;

import engine.logic.UserQuizComplet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserQuizCompletRepository extends JpaRepository<UserQuizComplet, Long> {

    // Attention on fait JQL (pas SQL) du coup il faut se référer aux noms de zones
    // dans le class en partiulier : iduser est u.user.id !!!!! est pas  userid qui est le nom
    // dans la table !!!!!!!!!!
    @Query(value = "select u from UserQuizComplet u where u.user.id = :iduser", nativeQuery = false)
    public Page<UserQuizComplet> findByIdUser(@Param("iduser") Long iduser, Pageable pageable);
}
