package app;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface DbWorker extends CrudRepository<Rate, Long> {

    Optional<Rate> findByDate(String date);
    Optional<Rate> findById(String date);
}
