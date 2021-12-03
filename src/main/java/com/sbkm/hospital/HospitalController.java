package com.sbkm.hospital;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    HospitalController(DoctorRepository repository, UserRepository userRepository, TimetableRepository timetableRepository, PasswordEncoder passwordEncoder, PatientRepository patientRepository, PatientRecordRepository patientRecordRepository) {
        this.doctorRepository = repository;
        this.userRepository = userRepository;
        this.timetableRepository = timetableRepository;
        this.passwordEncoder = passwordEncoder;
        this.patientRepository = patientRepository;
        this.patientRecordRepository = patientRecordRepository;
    }

    @GetMapping("/test")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PreAuthorize("hasAuthority('patient:read')")
    String test() {
        System.out.println("test mapping incoming");
        return "answer to test string";
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

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }

    @PostMapping("/patientRecord")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PreAuthorize("hasAuthority('doctor:write')")
    @ResponseBody
    public ResponseEntity<?> patientRecord(@ModelAttribute PatientRecordDto patientRecordDto){
        System.out.println(patientRecordDto.getDoctor_id());
        if(!doctorRepository.existsById(patientRecordDto.getDoctor_id())){
            return new ResponseEntity<>("Unknown Doctor ID", HttpStatus.BAD_REQUEST);
        }
        if(!patientRepository.existsById(patientRecordDto.getPatient_id())){
            return new ResponseEntity<>("Unknown Patient ID", HttpStatus.BAD_REQUEST);
        }

        PatientRecord patientRecord = new PatientRecord();
        patientRecord.setDoctor_id(patientRecordDto.getDoctor_id());
        patientRecord.setPatient_id(patientRecordDto.getPatient_id());
        patientRecord.setDate_of_receipt(patientRecordDto.getDate_of_receipt());
        patientRecord.setRecord(patientRecordDto.getRecord());
        patientRecordRepository.save(patientRecord);
        return new ResponseEntity<>("Record added successfully", HttpStatus.OK);

    }

    @GetMapping("/calendar")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public @ResponseBody
    Iterable<Timetable> getDoctorSchedule(@RequestParam String id) {
        Iterable<Timetable> tt = timetableRepository.getSchedule(Long.valueOf(id));
        return tt;
    }

    @GetMapping("/viewRecords")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PreAuthorize("hasAuthority('patient:read')")
    public @ResponseBody
    Iterable<PatientRecord> getPatientRecord(@RequestParam String id) {
        Iterable<PatientRecord> patientRecord = patientRecordRepository.viewRecords(Long.valueOf(id));
        return patientRecord;
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