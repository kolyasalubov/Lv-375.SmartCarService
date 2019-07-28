package ua.ita.smartcarservice.repository.files;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.files.AvatarEntity;

@Repository
public interface AvatarRepository extends JpaRepository<AvatarEntity, Long> {

    AvatarEntity findByUsername(String username);

    void deleteByUsername(String username);

}
