package com.idsargus.akpmsadminservice.controllers;
//import com.idsargus.akpmsadminservice.entity.UserLogsEntity;
import com.idsargus.akpmsadminservice.repository.UserLogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("/api/logs-generate")
public class LogsGenerateController {
	@Autowired
	private UserLogsRepository userLogsRepo;
	
//	https://akpms2.idsil.in:9003/api/logs-generate/saveUserLoginFile
	
//	@PostMapping("/saveUserLoginFile")
//	public ResponseEntity<?> logGenerate(@RequestBody UserLogsEntity logs) {
//	    // Null check for the request body
//	    if (logs == null) {
//	        return ResponseEntity.badRequest().body("Log data must not be null.");
//	    }
//
//	    // Additional null checks for required fields in logs (if any)
//	    if (logs.getUserId() == null || logs.getLoginTiming() == null) {
//	        return ResponseEntity.badRequest().body("User ID and login time must not be null.");
//	    }
//
//	    try {
//	        userLogsRepo.save(logs);
//	        return ResponseEntity.ok("Log file generated successfully.");
//	    } catch (Exception e) {
//	        // Log the exception (optional)
//	        // logger.error("Error saving user logs", e);
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//	                             .body("An error occurred while saving the log data: " + e.getMessage());
//	    }
//	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//    private static final Path ROOT_DIRECTORY = Paths.get("/opt/akpms-backend/UserLogs");
//      private static final Path ROOT_DIRECTORY = Paths.get("/home/ubuntu/UserLogs");
//	private static final Path ROOT_DIRECTORY = Paths.get("/var/www/html/UserLogs");

//    @PostMapping("/saveUserLoginFile")
//    public ResponseEntity<?> logGenerate(@RequestBody UserLogsEntity logs) {
//        FileOutputStream fos = null;
//
//        // Generate directory structure and filename
//        LocalDate currentDate = LocalDate.now();
//        String year = String.valueOf(currentDate.getYear());
//        String month = currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
//        String day = String.format("%02d", currentDate.getDayOfMonth());
//        String username = logs.getUsername();
//
//        Path yearPath = ROOT_DIRECTORY.resolve(year);
//        Path monthPath = yearPath.resolve(month);
//        Path dayPath = monthPath.resolve(day);
//        Path userPath = dayPath.resolve(username);
//
//        // Ensure the directory structure exists
//        try {
//            Files.createDirectories(userPath);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(500).body("An error occurred while creating directories.");
//        }
//
//        // Generate filename
//        String filename = String.format("%s_%s.txt", username, currentDate.format(DateTimeFormatter.BASIC_ISO_DATE));
//        Path filePath = userPath.resolve(filename);
//
//        try {
//            fos = new FileOutputStream(filePath.toFile(), true);
//            String content = String.format(
//                    "User ID: %d\nRequestAPI: %s\nResponseTime: %s\nRequest CurrentDateTime: %s\nUsername: %s\nLogin Timing: %s\nLogout Timing: %s\nDepartment: %s\nSession Timeout: %s\n\n",
//                    logs.getUserId(),
//                    logs.getRequestAPI(),
//                    logs.getResponseTime(),
//                    logs.getRequestCurrentDateTime(),
//                    logs.getUsername(),
//                    logs.getLoginTiming(),
//                    logs.getLogoutTiming(),
//                    logs.getDepartment(),
//                    logs.getSessionTimeout()
//            );
//            fos.write(content.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(500).body("An error occurred while generating the log file.");
//        } finally {
//            try {
//                if (fos != null) {
//                    fos.close();
//                }
//            } catch (IOException ex) {
//                ex.printStackTrace();
//                return ResponseEntity.status(500).body("An error occurred while closing the stream.");
//            }
//        }
//         UserLogsEntity savedLogs = userLogsRepo.save(logs);
////        return ResponseEntity.ok(savedLogs);
//        return ResponseEntity.ok("Log file generated successfully.");
//    }
}