package com.idsargus.akpmsadminservice.Mvc.Repository;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminQcPointEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminQcPointResponseDto;
//import com.idsargus.akpmsadminservice.entity.AdminQcPointEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AdminQcPointRepository extends JpaRepository<AdminQcPointEntityMvc, Integer>, CrudRepository<AdminQcPointEntityMvc,Integer> {



//    @Cacheable("adminQcPoints")
//@Query(value =
//    "SELECT "+
//       " e.id as qc_point_id ,"+
//       " e.created_on,"+
//       " e.modified_on,"+
//       " e.name as qc_point_name,"+
//       " e.enabled as qc_status,"+
//       " e.description,"+
//       " e.sub_department_id,"+
//       " e.deleted,"+
//       " e.parent_id,"+
//       " e.department_id as d_id,"+
//       " p.name AS parent_name,"+
//       " d.name AS department_name,"+
//       " e.coding_prod_type_id,"+
//       " cp.name AS coding_prod_types,"+
//       " CONCAT(cb.first_name, ' ', cb.last_name) AS created_by_user_name,"+
//       " CONCAT(mb.first_name, ' ', mb.last_name) AS modified_by_user_name,"+
//       " GROUP_CONCAT(c.id) AS list_of_child_qc_points_ids,"+
//       " GROUP_CONCAT(c.name ORDER BY c.name SEPARATOR ',') AS list_of_child_qc_points_names, "+  // Remove spaces between names
//       " cp.name AS coding_prod_types_name "+
//       " FROM qc_point e "+
//   " LEFT JOIN qc_point p ON e.parent_id = p.id "+
//   " LEFT JOIN department d ON e.department_id = d.id "+
//   " LEFT JOIN user cb ON e.created_by = cb.id "+
//   " LEFT JOIN user mb ON e.modified_by = mb.id "+
//   " LEFT JOIN coding_productivity_type cp ON e.coding_prod_type_id = cp.id "+
//   " LEFT JOIN qc_point c ON c.parent_id = e.id "+
//   "where (:query is null or CONCAT(e.name ) LIKE %:query%) " +
//   "AND (:departmentId is null or e.department_id = :departmentId)" +
//   "AND (:status is null or e.enabled=:status)  "+
//   " GROUP BY e.id " +
//        " ORDER BY " +
//        "CASE " +
//        "   WHEN :sortDirection = 'asc' THEN " +
//        "       CASE " +
//        "           WHEN :columnName = 'parent_name' THEN parent_name " +
//        "           WHEN :columnName = 'qc_point_name' THEN qc_point_name " +
//        "           WHEN :columnName = 'department_name' THEN department_name " +
//        "           WHEN :columnName = 'qc_status' THEN qc_status " +
//        "           WHEN :columnName = 'coding_prod_types_name' THEN coding_prod_types_name " +
//        "           ELSE NULL  " +
//        "       END " +
//        "   END ASC, " +
//        "CASE " +
//        "   WHEN :sortDirection = 'desc' THEN " +
//        "       CASE " +
//        "           WHEN :columnName = 'parent_name' THEN parent_name " +
//        "           WHEN :columnName = 'qc_point_name' THEN qc_point_name " +
//        "           WHEN :columnName = 'department_name' THEN department_name " +
//        "           WHEN :columnName = 'qc_status' THEN qc_status " +
//        "           WHEN :columnName = 'coding_prod_types_name' THEN coding_prod_types_name " +
//        "           ELSE NULL  " +
//        "       END " +
//        "   END DESC, " +
//        "qc_point_id DESC"
//          , countQuery = "SELECT COUNT(e.id) FROM qc_point e " +
//        "WHERE (:query IS NULL OR CONCAT(e.name ) LIKE %:query%)  " +
//        "             AND (:departmentId IS NULL OR e.department_id = :departmentId) " +
//        "             AND (:status IS NULL OR e.enabled = :status) "
//        , nativeQuery = true)
//        Page<Object[]> findAllQcPointsNative(
//        @Param("columnName") String columnName,
//        @Param("sortDirection") String sortDirection,
//        Pageable pageable,
//        @Param("query") String query,
//        @Param("status") Boolean status,
//        @Param("departmentId") Integer departmentId
//);
//






    @Query(value =
            "SELECT "+
                    " e.id as qc_point_id ,"+
                    " e.created_on,"+
                    " e.modified_on,"+
                    " e.name as qc_point_name,"+
                    " e.enabled as qc_status,"+
                    " e.description,"+
                    " e.sub_department_id,"+
                    " e.deleted,"+
                    " e.parent_id,"+
                    " e.department_id as d_id,"+
                    " p.name AS parent_name,"+
                    " d.name AS department_name,"+
                    " e.coding_prod_type_id,"+
                    " cp.name AS coding_prod_types,"+
                    " CONCAT(cb.first_name, ' ', cb.last_name) AS created_by_user_name,"+
                    " CONCAT(mb.first_name, ' ', mb.last_name) AS modified_by_user_name,"+
                    " GROUP_CONCAT(c.id) AS list_of_child_qc_points_ids,"+
                    " GROUP_CONCAT(c.name ORDER BY c.name SEPARATOR ',') AS list_of_child_qc_points_names, "+  // Remove spaces between names
                    " cp.name AS coding_prod_types_name "+
                    " FROM qc_point e "+
                    " LEFT JOIN qc_point p ON e.parent_id = p.id "+
                    " LEFT JOIN department d ON e.department_id = d.id "+
                    " LEFT JOIN user cb ON e.created_by = cb.id "+
                    " LEFT JOIN user mb ON e.modified_by = mb.id "+
                    " LEFT JOIN coding_productivity_type cp ON e.coding_prod_type_id = cp.id "+
                    " LEFT JOIN qc_point c ON c.parent_id = e.id where (:query is null or CONCAT(e.name,e.description ) LIKE %:query%) " +
                    "AND (:departmentId is null or e.department_id = :departmentId)" +
                    "AND (:status is null or e.enabled=:status)  "+
                    " GROUP BY e.id " +
                    " ORDER BY " +
                    "CASE " +
                    "   WHEN :sortDirection = 'asc' THEN " +
                    "       CASE " +
                    "           WHEN :columnName = 'parent_name' THEN parent_name " +
                    "           WHEN :columnName = 'qc_point_name' THEN qc_point_name " +
                    "           WHEN :columnName = 'department_name' THEN department_name " +
                    "           WHEN :columnName = 'qc_status' THEN qc_status " +
                    "           WHEN :columnName = 'coding_prod_types_name' THEN coding_prod_types_name " +
                    "           ELSE NULL  " +
                    "       END " +
                    "   END ASC, " +
                    "CASE " +
                    "   WHEN :sortDirection = 'desc' THEN " +
                    "       CASE " +
                    "           WHEN :columnName = 'parent_name' THEN parent_name " +
                    "           WHEN :columnName = 'qc_point_name' THEN qc_point_name " +
                    "           WHEN :columnName = 'department_name' THEN department_name " +
                    "           WHEN :columnName = 'qc_status' THEN qc_status " +
                    "           WHEN :columnName = 'coding_prod_types_name' THEN coding_prod_types_name " +
                    "           ELSE NULL  " +
                    "       END " +
                    "   END DESC, " +
                    "qc_point_id DESC"
            , countQuery = "SELECT COUNT(e.id) FROM qc_point e " +
            "WHERE (:query IS NULL OR CONCAT(e.name, e.description ) LIKE %:query%)  " +
            "             AND (:departmentId IS NULL OR e.department_id = :departmentId) " +
            "             AND (:status IS NULL OR e.enabled = :status) "
            , nativeQuery = true)
    Page<Object[]> findAllQcPointsNative(
            @Param("columnName") String columnName,
            @Param("sortDirection") String sortDirection,
            Pageable pageable,
            @Param("query") String query,
            @Param("status") Boolean status,
            @Param("departmentId") Integer departmentId
    );




























    AdminQcPointEntityMvc findByName(String name);






    @Query("SELECT p FROM AdminQcPointEntityMvc p " +
            "where  (:query is null or CONCAT(p.name,p.description) LIKE %:query%)" +
            "AND p.deleted = 0 "+
            "AND (:departmentId is null or p.department.id = :departmentId ) "+
            "AND (:status is null or p.enabled = :status )"
    )
    List<AdminQcPointEntityMvc> fetchExcelDownload(
            @Param("query") String query ,
            @Param("departmentId") Integer departmentId,
            @Param("status") Boolean status
    );


    @Query("SELECT i FROM AdminQcPointEntityMvc i where i.deleted = 0 and parent = null ORDER BY i.name")
    @Cacheable(key = "#parent-only")
    public List<AdminQcPointResponseDto> findParentForAdmin();


//    @Query("SELECT i FROM AdminQcPointEntity i where i.deleted = 0 and parent = null ORDER BY i.name")
//    @Cacheable(key = "#parent-only")
//    public List<AdminQcPointEntity> findParentForAdmin();








}