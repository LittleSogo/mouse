package com.mouse.dao.repository.resource;

import com.mouse.dao.entity.resource.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer>, JpaSpecificationExecutor<CategoryEntity> {
    /**
     * 根据类目等级查询类目
     *
     * @param level   类目等级
     * @param deleted
     * @return
     */
    Optional<List<CategoryEntity>> findByLevelAndDeleted(String level, Boolean deleted);


    /**
     * 根据父类目ID查询商品类目
     *
     * @param pId 父类目ID
     * @return
     */
    Optional<List<CategoryEntity>> findByPidAndDeleted(Integer pId, Boolean deleted);

    Optional<List<CategoryEntity>> findByLevelAndIdIn(String level, List<Integer> ids);
}