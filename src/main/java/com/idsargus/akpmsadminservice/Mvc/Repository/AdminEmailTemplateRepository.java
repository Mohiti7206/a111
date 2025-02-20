package com.idsargus.akpmsadminservice.Mvc.Repository;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminEmailTemplateEntity2;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminEmailTemplateResponseDTO;
import com.idsargus.akpmsadminservice.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//public interface AdminEmailTemplateRepository extends JpaRepository<AdminEmailTemplateEntity2, Integer> {
public interface AdminEmailTemplateRepository extends JpaRepository<AdminEmailTemplateEntity2, Integer>, CrudRepository<AdminEmailTemplateEntity2,Integer> {



        @Cacheable
        @Query(
                "SELECT new com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminEmailTemplateResponseDTO(" +
                        "e.id, " +
                        "e.createdOn , " +
                        "e.modifiedOn, " +
                        "e.name," +
                        "e.content, " +
                        "e.subscriptionEmail, " +
                        "e.enabled,  " +
                        "e.status, " +
                        "e.is_deleted, " +
                        "CONCAT(createdBy.firstName, ' ', createdBy.lastName), " +
                        "CONCAT(modifiedBy.firstName, ' ', modifiedBy.lastName)  " +
                        ") " +
                        " FROM AdminEmailTemplateEntity2 e " +
                        " LEFT JOIN e.createdBy createdBy "  +
                        " LEFT JOIN e.modifiedBy  modifiedBy "
        )
        List<AdminEmailTemplateResponseDTO> findAll1(
                Pageable pageable
        );


//        @Modifying
//        @Query("UPDATE AdminEmailTemplateEntity2 e " +
//                "SET e.name = :name, " +
//                "e.content = :content, " +
//                "e.subscriptionEmail = :subscriptionEmail, " +
//                "e.enabled = :enabled, " +
//                "e.status = :status, " +
//                "e.is_deleted = :isDeleted, " +
//                "e.userModifiedBy = :userModifiedBy " +
//                "WHERE e.id = :id")
//        int updateTemplate(
//                @Param("id") Integer id,
//                @Param("name") String name,
//                @Param("content") String content,
//                @Param("subscriptionEmail") Boolean subscriptionEmail,
//                @Param("enabled") Boolean enabled,
//                @Param("status") Boolean status,
//                @Param("isDeleted") Boolean isDeleted,
//                @Param("userModifiedBy")Integer userModifiedBy
//        );

//        @Modifying
//        @Query("UPDATE AdminEmailTemplateEntity2 e " +
//                "SET e.enabled = :enabled " +
//                "WHERE e.id = :id")
//        int updateTemplateActivationStatus(
//                @Param("id") Integer id,
//                @Param("enabled") Boolean enabled
//
//        );

        @Query("SELECT e FROM AdminEmailTemplateEntity2 e WHERE e.id = :id")
        Optional<AdminEmailTemplateEntity2> findById(@Param("id") Integer id);


















//    @Query("SELECT new com.ids.example.BookDTO(b.id, b.title, b.author.name, b.category, b.uservv.name) " +
//            "FROM Book b LEFT JOIN b.author LEFT JOIN b.uservv  " +
//            "WHERE (COALESCE(:userIds) IS NULL OR b.uservv.id IN (:userIds)) ")
//    Page<BookDTO> findAllBooks(
//            @Param("userIds") List<Long> user,
//            Pageable pageable
//    );


//    @Cacheable
//    @Query(
//            value = "SELECT b.id AS id, b.title AS title, a.name AS author_name, b.category AS category, u.name AS user_name " +
//                    "FROM books b " +
//                    "LEFT JOIN author a ON b.author_id = a.id " +
//                    "LEFT JOIN uservv u ON b.user_id = u.id " +
//                "WHERE (COALESCE(:userIds, NULL) IS NULL OR u.id IN (:userIds))",
//            nativeQuery = true
//    )
//    Page<BookDTO> findAllBooks(
//            @Param("userIds") List<Long> userIds,
//            Pageable pageable
//    );


}

