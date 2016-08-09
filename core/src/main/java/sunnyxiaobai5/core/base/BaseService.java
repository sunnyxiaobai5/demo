package sunnyxiaobai5.core.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T extends BaseEntity, K extends BaseDTO, ID extends Serializable> {

    T fromDTO(K k);

    List<T> fromDTO(List<K> kList);

    K fromEntity(T t);

    List<K> fromEntity(List<T> tList);

    T save(T t);

    T save(K k);

    List<T> saveAll(List<T> tList);

    List<T> saveAllDTO(List<K> kList);

    T findOne(ID id);

    K findOneDTO(ID id);

    List<T> findAll();

    List<K> findAllDTO();

    List<T> findAll(List<ID> idList);

    List<K> findAllDTO(List<ID> idList);

    Page<T> findAll(Pageable pageable);

    Page<K> findAllDTO(Pageable pageable);

    void delete(ID id);

    void delete(T entity);

    void deleteAllByID(List<ID> idList);

    void deleteAll(List<T> tList);
}
