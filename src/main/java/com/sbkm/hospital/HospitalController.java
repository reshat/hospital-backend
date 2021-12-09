package com.sbkm.hospital;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class HospitalController {
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final TimetableRepository timetableRepository;
    private final PasswordEncoder passwordEncoder;
    private final PatientRepository patientRepository;
    private final PatientRecordRepository patientRecordRepository;
    private final AppointmentTableRepository appointmentTableRepository;
    private final GeneralService generalService;

    HospitalController(DoctorRepository repository, UserRepository userRepository, TimetableRepository timetableRepository, PasswordEncoder passwordEncoder, PatientRepository patientRepository, PatientRecordRepository patientRecordRepository, AppointmentTableRepository appointmentTableRepository, UserDetailsServiceImp userDetailsServiceImp, GeneralService calendarService) {
        this.doctorRepository = repository;
        this.userRepository = userRepository;
        this.timetableRepository = timetableRepository;
        this.passwordEncoder = passwordEncoder;
        this.patientRepository = patientRepository;
        this.patientRecordRepository = patientRecordRepository;
        this.appointmentTableRepository = appointmentTableRepository;
        this.generalService = calendarService;
    }

    @GetMapping("/test")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PreAuthorize("hasAuthority('patient:read')")
    String test() {
        System.out.println("test mapping incoming");
        return "answer to test string";
    }
    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public @ResponseBody
    User getAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        User user = userRepository.findByLogin(auth.getName()).orElse(new User());
        return user;
    }
  
    @GetMapping("/general")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    String general() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        GeneralInformation data = new GeneralInformation();
        String result = objectMapper.writeValueAsString(data);
        return result;
    }
    @GetMapping("/doctors")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public @ResponseBody
    Iterable<Doctor> getAllDoctor() {
        Iterable<Doctor> doctors = doctorRepository.findAll();
        return doctors;
    }
    @PostMapping("/signup")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @ResponseBody
    public ResponseEntity<?> registerUser(@ModelAttribute SignUpDto signUpDto){

        if(userRepository.existsByLogin(signUpDto.getLogin())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }


        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setName(signUpDto.getName());
        user.setSurname(signUpDto.getSurname());
        user.setPatronymic(signUpDto.getPatronymic());
        user.setLogin(signUpDto.getLogin());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setRole(Role.PATIENT);

        Patient patient = new Patient();
        patient.setName(signUpDto.getName());
        patient.setSurname(signUpDto.getSurname());
        patient.setPatronymic(signUpDto.getPatronymic());

        userRepository.save(user);
        patientRepository.save(patient);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }

    @PostMapping("/changeInfo")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PreAuthorize("hasAuthority('patient:write')")
    @ResponseBody
    public ResponseEntity<?> changeInfo(@ModelAttribute PatientDto patientDto){
        if(!patientRepository.existsById(patientDto.getPatient_id())){
            return new ResponseEntity<>("Unknown Patient ID", HttpStatus.BAD_REQUEST);
        }
        patientRepository.changeInfo(patientDto.getPatient_id(), patientDto.getName(), patientDto.getSurname(), patientDto.getPatronymic(), patientDto.getBirth_date());
        return new ResponseEntity<>("Info successfully changed", HttpStatus.OK);
    }

    @PostMapping("/patientRecord")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PreAuthorize("hasAuthority('doctor:write')")
    @ResponseBody
    public ResponseEntity<?> patientRecord(@ModelAttribute PatientRecordDto patientRecordDto){
        if(!doctorRepository.existsById(patientRecordDto.getDoctor_id())){
            return new ResponseEntity<>("Unknown Doctor ID", HttpStatus.BAD_REQUEST);
        }
        if(!patientRepository.existsById(patientRecordDto.getPatient_id())){
            return new ResponseEntity<>("Unknown Patient ID", HttpStatus.BAD_REQUEST);
        }

        PatientRecord patientRecord = new PatientRecord();
        patientRecord.setDoctorId(patientRecordDto.getDoctor_id());
        patientRecord.setPatientId(patientRecordDto.getPatient_id());
        patientRecord.setDateOfReceipt(patientRecordDto.getDate_of_receipt());
        patientRecord.setRecord(patientRecordDto.getRecord());
        patientRecordRepository.save(patientRecord);
        return new ResponseEntity<>("Record added successfully", HttpStatus.OK);
    }

    @GetMapping("/calendar")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public @ResponseBody
    Iterable<Calendar> getDoctorSchedule(@RequestParam String id) throws Exception {
        if(!doctorRepository.existsById(Long.valueOf(id))){
            throw new Exception("Unknown Doctor ID");
        }
        Iterable<Calendar> tt = generalService.getSchedule(Long.valueOf(id));
        return tt;
    }

    @GetMapping("/appointmentFreeSlots")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public @ResponseBody
    Iterable<AppointmentFreeSlots> getAppointmentSlots(@RequestParam String id) throws Exception {
        if(!doctorRepository.existsById(Long.valueOf(id))){
            throw new Exception("Unknown Doctor ID");
        }
        Iterable<AppointmentFreeSlots> afs = generalService.getFreeSlots(Long.valueOf(id));
        return afs;
    }

    @PostMapping("/makeAnAppointment")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PreAuthorize("hasAuthority('patient:write')")
    @ResponseBody
    public ResponseEntity<?> makeAnAppointment(@ModelAttribute AppointmentTableDto appointmentTableDto){
        if(!doctorRepository.existsById(appointmentTableDto.getDoctor_id())){
            return new ResponseEntity<>("Unknown Doctor ID", HttpStatus.BAD_REQUEST);
        }
        if(!patientRepository.existsById(appointmentTableDto.getPatient_id())){
            return new ResponseEntity<>("Unknown Patient ID", HttpStatus.BAD_REQUEST);
        }
        if(appointmentTableRepository.countByPatientId(appointmentTableDto.getPatient_id()) >= 2){
            return new ResponseEntity<>("Reached maximum number of appointments", HttpStatus.BAD_REQUEST);
        }
        if(!appointmentTableRepository.existsByDoctorIdAndDateOfReceiptAndTimeOfReceipt(appointmentTableDto.getDoctor_id(), appointmentTableDto.getDate_of_receipt(), appointmentTableDto.getTime_of_receipt())){
            return new ResponseEntity<>("Incorrect time or date", HttpStatus.BAD_REQUEST);
        }
//        int hours = appointmentTableDto.getAppointment_duration().getHour();
//        int minutes = appointmentTableDto.getAppointment_duration().getMinute();
//        if(!appointmentTableRepository.existsByDoctorIdAndDateOfReceiptAndReceiptStartLessThanEqualAndReceiptEndGreaterThanEqual(appointmentTableDto.getDoctor_id(), appointmentTableDto.getDate_of_receipt(), appointmentTableDto.getTime_of_receipt(), appointmentTableDto.getTime_of_receipt().plusHours(hours).plusMinutes(minutes))){
//            return new ResponseEntity<>("Incorrect time", HttpStatus.BAD_REQUEST);
//        }
        appointmentTableRepository.makeAnAppointment(appointmentTableDto.getPatient_id(), appointmentTableDto.getDoctor_id(), appointmentTableDto.getDate_of_receipt(), appointmentTableDto.getTime_of_receipt());
        return new ResponseEntity<>("Appointment made successfully", HttpStatus.OK);
    }
  
    @GetMapping("/viewRecords")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PreAuthorize("hasAuthority('patient:read')")
    public @ResponseBody
    Iterable<ViewRecords> getPatientRecords(@RequestParam String id) throws Exception {
        if(!patientRepository.existsById(Long.valueOf(id))){
            throw new Exception("Unknown Patient ID");
        }
        Iterable<ViewRecords> patientRecord = generalService.viewRecords(Long.valueOf(id));
        return patientRecord;
    }

    @GetMapping("/viewPatients")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PreAuthorize("hasAuthority('doctor:read')")
    public @ResponseBody
    Iterable<ViewPatients> getPatients(@RequestParam String id) throws Exception {
        if(!doctorRepository.existsById(Long.valueOf(id))){
            throw new Exception("Unknown Doctor ID");
        }
        Iterable<ViewPatients> patients = generalService.viewPatients(Long.valueOf(id));
        return patients;
    }

    @PostMapping("/changeTime")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PreAuthorize("hasAuthority('admin:write')")
    public ResponseEntity<?> changeTime(@ModelAttribute ChangeTimeDto changeTimeDto){
        if(!timetableRepository.existsByDoctorIdAndWorkDate(changeTimeDto.getDoctorId(), changeTimeDto.getDateOfReceipt())){
            return new ResponseEntity<>("Incorrect date or doctor id", HttpStatus.BAD_REQUEST);
        }
        timetableRepository.changeTime(changeTimeDto.getDoctorId(), changeTimeDto.getDateOfReceipt(), changeTimeDto.getReceiptStart(), changeTimeDto.getReceiptEnd());
        return new ResponseEntity<>("Time was changed", HttpStatus.OK);
    }
}
  /*@GetMapping("/testAdd")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    void testAdd() {
        User test_user = new User(
                "user",
                "$2a$12$h8nWjz6cz08vpxC3KCd2leRIh5GGvjghd1pQRfi3wnJazvvpvZn0i",
                Role.PATIENT);
        userRepository.save(test_user);
    }*/