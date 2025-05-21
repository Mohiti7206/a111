package com.idsargus.akpmsadminservice.Mvc.Controller;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminEmailTemplateEntity2;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.*;
import com.idsargus.akpmsadminservice.Mvc.Service.AdminEmailTemplateService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@RestController
//@RequestMapping("/books")
//public class BookController {
//
//    @Autowired
//    private BookService bookService;
//
//
////    @GetMapping("/books")
////    public List<Book> getAllBooks() {
////        return bookService.getAllBooks();
////    }
//
//
//    @GetMapping("/books")
//    public Page<Book> getBooks(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size
//    ) {
//        Pageable pageable = PageRequest.of(page, size);
//        return bookService.getBooks(pageable);
//    }
//
//
//    @PostMapping("/add")
//    public String addBook(@RequestParam String title, @RequestParam Long authorId) {
//        try {
//            bookService.saveBook(title, authorId);
//            return "Book saved successfully!";
//        } catch (Exception e) {
//            return "Error: " + e.getMessage();
//        }
//    }
//}
// BookController.java


//@RestController
//@RequestMapping("/emailtemplates")
//public class AdminEmailTemplateController {
//
//
//    @Autowired
//    private final AdminEmailTemplateService adminEmailTemplateService;
//
//    public AdminEmailTemplateController(AdminEmailTemplateService adminEmailTemplateService) {
//        this.adminEmailTemplateService = adminEmailTemplateService;
//    }
//
//
//
//
//
//    @GetMapping("/gettemp")
//    public List<AdminEmailTemplateDTO> getBooks(
////            @RequestParam(defaultValue = "0") int page,
////            @RequestParam(defaultValue = "10") int size
//    ) {
//        String sortBy = "id";
//        String direction = "asc";
//
////        Pageable pageable = PageRequest.of(
////                page,
////                size,
////                Sort.by(Sort.Order.by(sortBy).with(Sort.Direction.fromString(direction)))
////        );
//
////        return adminEmailTemplateService.getBooks(pageable);
//        return adminEmailTemplateService.getBooks();
//    }
//
//
//
////    @GetMapping("/all")
////    public Page<AdminEmailTemplateDTO> getBooks(
////            @RequestParam(defaultValue = "0") int page,
////            @RequestParam(defaultValue = "10") int size
////
////    ) {
////
////        String sortBy="id";
////        String direction="asc";
////
////
////        Pageable pageable = PageRequest.of(
////                page,
////                size,
////                Sort.by(Sort.Order.by(sortBy).with(Sort.Direction.fromString(direction)))
////        );
////
////        return adminEmailTemplateService.getBooks( pageable);
////    }
//
//}
// AdminEmailTemplateController.java












@RestController
@RequestMapping("/v1/adminapi/emailtemplates")
public class AdminEmailTemplateController {

    private final AdminEmailTemplateService adminEmailTemplateService;

    public AdminEmailTemplateController(AdminEmailTemplateService adminEmailTemplateService) {
        this.adminEmailTemplateService = adminEmailTemplateService;
    }

    @GetMapping("/search/all")
    public ResponseEntity<List<AdminEmailTemplateResponseDTO>> getEmailTemplates(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50000") int size,
            @RequestParam(defaultValue = "id") String columnName,  // Default sort by 'id'
            @RequestParam(defaultValue = "desc") String sortDirection // Default sort direction 'asc'
    ) {
        System.out.println("1");

        String sortBy = columnName;
        String direction= sortDirection;

        if (sortBy.equals("created_by")) {
            sortBy = "userCreatedBy.firstName";
        } else if (sortBy.equals("modified_by")) {
            sortBy = "userModifiedBy.firstName";
        }




        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Order.by(sortBy).with(Sort.Direction.fromString(direction)))
        );
        return ResponseEntity.ok(adminEmailTemplateService.getAll1(pageable));

    }















    @PostMapping("/add")
    public ResponseEntity<AdminEmailTemplateResponseDTO> addEmailTemplate(
            @Validated(ValidationGroups.Create.class)  @RequestBody AdminEmailTemplateRequestDto adminEmailTemplateRequestDto) {

        // Call the service method to save the email template
        AdminEmailTemplateResponseDTO savedTemplate = adminEmailTemplateService.addEmailTemplate(adminEmailTemplateRequestDto);

        // Return the saved template in the response
        return ResponseEntity.ok(savedTemplate);
    }



    @PatchMapping("/update/{id}")
//    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
    @CachePut(value = "emailTemplateCache", key = "#id")
    public ResponseEntity<?> update(
            @Validated(ValidationGroups.Update.class)  @RequestBody AdminEmailTemplateRequestDto adminEmailTemplateRequestDto,
            @PathVariable Integer id) {


        try {
            System.out.println("id: " + id);
            System.out.println("name: " + adminEmailTemplateRequestDto.getName());
            System.out.println("semail: " + adminEmailTemplateRequestDto.getSubscriptionEmail());

            AdminEmailTemplateResponseDTO adminEmailTemplateDTO = adminEmailTemplateService.updateTemplate(adminEmailTemplateRequestDto, id);
            return ResponseEntity.ok(adminEmailTemplateDTO);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }



    @PatchMapping("/updateActivationStatus/{id}")
//    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
    @CachePut(value = "emailTemplateCache", key = "#id")
    public ResponseEntity<?> updateTemplateActivationStatus(
            @RequestBody ActivationStatusRequestDto activationStatusRequestDto,
            @PathVariable Integer id) {


        try {
//            System.out.println("id: " + id);
            boolean status = activationStatusRequestDto.isEnabled();
//            System.out.println("status = "+status);
            AdminEmailTemplateResponseDTO adminEmailTemplateDTO = adminEmailTemplateService.updateTemplateActivationStatus(id,status);
            return ResponseEntity.ok(adminEmailTemplateDTO);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }


















}
