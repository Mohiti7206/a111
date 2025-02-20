//package com.idsargus.akpmsadminservice.entity;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//
//@Entity
//@Getter
//@Setter
////@Table(name = "hourly_task")  -- previously
//@Table(name = "payment_prod_hourly")
//public class AdminHourlyTaskEntity extends AdminBaseAuditableEntity {
//
//	private static final long serialVersionUID = 1L;
//
//	// private String name;
////	@OneToOne
////	@JoinColumn(name = "task_name")
////	//@JoinColumn(name = "name")  ------previously
////    private AdminHourlyTaskName name;
//
////@ManyToOne
////	@JoinColumns({ @JoinColumn(name = "batch_id", referencedColumnName = "id", unique = false, nullable = true) })
////	private PaymentBatch paymentBatch;
//	@Column(name = "detail") ///name = "description" changed by Tausif
//	private String details;
//
//	//added by Tausif time , date received , task completed
//	@Column(name = "time")
//	private String time;
//	@Column(name = "hours")
//	private String hours;
//
//	@Column(name = "minutes")
//	private String minutes;
//	@Column(name = "date_received")
//	private LocalDateTime dateReceived;
//
//	// Added hourlyTask on 27.4.23
//	@ManyToOne
//	@JoinColumns({ @JoinColumn(name = "task_name", referencedColumnName = "id", unique = false, nullable = true) })
//	private AdminHourlyTaskName hourlyTask;
//
//	@Column(name = "date_task_completed")
//	private LocalDateTime taskCompleted;
//
//	@Transient
//	private String userTimeZone = "";
//
//	@PrePersist
//	public void prePersist() {
//		LocalDateTime now;
//		LocalDateTime now1;
//		LocalDateTime now2;
//		if (this.userTimeZone.equalsIgnoreCase("IST")) {
//			now = this.dateReceived.plusHours(12L).plusMinutes(30L);
//			this.dateReceived = LocalDateTime.of(now.toLocalDate(), LocalTime.MIDNIGHT);
//			now1 = this.taskCompleted.plusHours(12L).plusMinutes(30L);
//			this.taskCompleted = LocalDateTime.of(now1.toLocalDate(), LocalTime.MIDNIGHT);
//			now2 = LocalDateTime.now().plusHours(12L).plusMinutes(30L);
//			this.setCreatedOn(LocalDateTime.of(now2.toLocalDate(), LocalTime.MIDNIGHT));
//		} else {
//			now = this.dateReceived.plusHours(0L).plusMinutes(0L);
//			this.dateReceived = LocalDateTime.of(now.toLocalDate(), LocalTime.MIDNIGHT);
//			now1 = this.taskCompleted.plusHours(0L).plusMinutes(0L);
//			this.taskCompleted = LocalDateTime.of(now1.toLocalDate(), LocalTime.MIDNIGHT);
//			now2 = LocalDateTime.now().plusHours(0L).plusMinutes(0L);
//			this.setCreatedOn(LocalDateTime.of(now2.toLocalDate(), LocalTime.MIDNIGHT));
//		}
//
//		System.out.println("--------------------------" + (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//	}
//
//	@PreUpdate
//	public void preUpdate() {
//		LocalDateTime now;
//		LocalDateTime now1;
//		LocalDateTime now2;
//
//		if (this.userTimeZone.equalsIgnoreCase("IST")) {
//			now = this.dateReceived.plusHours(12L).plusMinutes(30L);
//			this.dateReceived = LocalDateTime.of(now.toLocalDate(), LocalTime.MIDNIGHT);
//			now1= this.taskCompleted.plusHours(12L).plusMinutes(30L);
//			this.taskCompleted = LocalDateTime.of(now1.toLocalDate(), LocalTime.MIDNIGHT);
//			now2 = LocalDateTime.now().plusHours(0L).plusMinutes(0L);
//			this.setModifiedOn(LocalDateTime.of(now2.toLocalDate(), LocalTime.MIDNIGHT));
//
//		} else {
//			now = this.dateReceived.plusHours(0L).plusMinutes(0L);
//			this.dateReceived = LocalDateTime.of(now.toLocalDate(), LocalTime.MIDNIGHT);
//			now1 = this.taskCompleted.plusHours(0L).plusMinutes(0L);
//			this.taskCompleted = LocalDateTime.of(now1.toLocalDate(), LocalTime.MIDNIGHT);
//			now2 = LocalDateTime.now().plusHours(12L).plusMinutes(30L);
//			this.setModifiedOn(LocalDateTime.of(now2.toLocalDate(), LocalTime.MIDNIGHT));
//		}
//
//	}
//
//
//
////commented upto chargeable by Tausif
////	@OneToOne
////	@JoinColumn(name = "department_id")
////	private AdminDepartmentEntity department;
////
////	@NotNull
////	@Column(name = "chargeable", columnDefinition = "BIT default 1", nullable = false)
////	private Boolean chargeable;
//
//	//added on 11.4.23 by arnrl
////@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH,
////            CascadeType.REFRESH})
////    @JoinColumns(@JoinColumn(name = "created_by", referencedColumnName = "id", nullable = false, unique = false))
////    private User CreatedByName;
//
//
//	@Transient
//	private User CreatedByName;
//
////	@NotNull
////	@Column(name = "enabled", columnDefinition = "BIT default 1", nullable = false)
////	private Boolean enabled;
//
////	@NotNull
////	@Column(name = "deleted", columnDefinition = "BIT default 0", nullable = false)
////	private Boolean deleted;
//
//	@Transient
//	private String taskName ;
//	public String getTaskName() {
//   return (this.hourlyTask == null) ? null : this.hourlyTask.getName();
//   }
// //   private Integer nameId;
//
//   public Integer getNameId() {
//	  return (this.hourlyTask == null) ? null : this.hourlyTask.getId();
//   }
//// public Integer getNameId() {
////	  return (this.name == null) ? null : this.name.getId();
////   }
////	public String getCreatedByName(){
////		return (this.getCreatedBy() == null) ? null : this.getCreatedBy().getFirstName()+ " "+ this.getCreatedBy().getLastName();
////
////	}
//
//	//
//}
