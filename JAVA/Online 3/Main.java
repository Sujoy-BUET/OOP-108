import java.util.ArrayList;
import java.util.List;

class Medication {
    public String name;
    public List<String> allergies;

    public Medication(String s) {
        name = s;
        allergies = new ArrayList<>();
    }

    public void addAllergen(String s) {
        allergies.add(s);
    }
}

abstract class Person {
    public String name;
    public int age;
    public String gender;
    public String contactInfo;

    public Person(String s, int a, String g, String c) {
        name = s;
        age = a;
        gender = g;
        contactInfo = c;
    }

    abstract void showInfo();
}

class Patient extends Person {
    public String patientID;
    public List<String> allergies;
    public List<Medication> medications;
    public List<MedicalProcedure> medicalProcedures;

    public Patient(String s, int age, String gender, String email, String id) {
        super(s, age, gender, email);
        patientID = id;
        allergies = new ArrayList<>();
        medications = new ArrayList<>();
        medicalProcedures = new ArrayList<>();
    }

    public void addAllergy(String s) {
        allergies.add(s);
    }

    public void addMedicalProcedure(MedicalProcedure procedure) {
        medicalProcedures.add(procedure);
    }

    public void showInfo() {
        System.out.println("Patient info: ");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("ContactInfo: " + contactInfo);
        System.out.println("PatientID: " + patientID);
        System.out.println("Allergies: " + allergies);
        System.out.println("Medications: ");
        for (Medication med : medications) {
            System.out.println("  - " + med.name);
        }
        System.out.println("Medical Procedures: ");
        for (MedicalProcedure proc : medicalProcedures) {
            System.out.println("  - " + proc.procedureName + " (ID: " + proc.procedureId + ")");
        }
        System.out.println();
    }
}

class Doctor extends Person implements Prescribable {
    public int doctorId;
    public List<String> specialization;

    public Doctor(String s, int age, String gender, String email, int id) {
        super(s, age, gender, email);
        this.doctorId = id;
        this.specialization = new ArrayList<>();
    }

    public void addSpecialization(String s) {
        specialization.add(s);
    }

    public void showInfo() {
        System.out.println("Doctor info: ");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("ContactInfo: " + contactInfo);
        System.out.println("DoctorID: " + doctorId);
        System.out.println("Specializations: " + specialization);
        System.out.println();
    }

    public boolean isCertifiedFor(String procedureName) {
        for (String sp : specialization) {
            if (sp.equalsIgnoreCase(procedureName)) {
                return true;
            }
        }
        return false;
    }

    public void prescribe(Patient p, Medication m) {
        try {
            // Check for allergies
            for (String patientAllergy : p.allergies) {
                for (String medicationAllergen : m.allergies) {
                    if (patientAllergy.equalsIgnoreCase(medicationAllergen)) {
                        throw new RuntimeException("Cannot prescribe " + m.name + " to " + p.name +
                                " due to allergy: " + patientAllergy);
                    }
                }
            }

            // If no allergies, add medication to patient
            p.medications.add(m);
            System.out.println("Successfully prescribed " + m.name + " to " + p.name);

        } catch (RuntimeException e) {
            System.out.println("Prescription failed: " + e.getMessage());
        }
    }
}

abstract class MedicalProcedure {
    public String procedureId;
    public String procedureName;
    public String scheduleTime;
    public Doctor performedBy;

    public abstract boolean validate();
}

class Surgery extends MedicalProcedure {
    public boolean anesthesiaGiven;

    public Surgery(String id, String name, String schedule, Doctor doctor, boolean anesthesia) {
        procedureId = id;
        procedureName = name;
        scheduleTime = schedule;
        performedBy = doctor;
        anesthesiaGiven = anesthesia;
    }

    public boolean validate() {
        try {
            if (!anesthesiaGiven) {
                throw new RuntimeException("Surgery " + procedureName + " is invalid: Anesthesia not given");
            }

            if (!performedBy.isCertifiedFor(procedureName)) {
                throw new RuntimeException("Surgery " + procedureName + " is invalid: Doctor " +
                        performedBy.name + " is not certified for this procedure");
            }

            System.out.println("Surgery " + procedureName + " is valid and can be performed");
            return true;

        } catch (RuntimeException e) {
            System.out.println("Validation failed: " + e.getMessage());
            return false;
        }
    }
}

class LabTest extends MedicalProcedure {
    public String location;

    public LabTest(String id, String name, String schedule, Doctor doctor, String loc) {
        procedureId = id;
        procedureName = name;
        scheduleTime = schedule;
        performedBy = doctor;
        location = loc;
    }

    public boolean validate() {
        try {
            if (!location.equalsIgnoreCase("clinic") && !location.equalsIgnoreCase("hospitalPremise")) {
                throw new RuntimeException("Lab Test " + procedureName + " is invalid: Must be done at clinic or hospitalPremise, not " + location);
            }

            System.out.println("Lab Test " + procedureName + " is valid and can be performed at " + location);
            return true;

        } catch (RuntimeException e) {
            System.out.println("Validation failed: " + e.getMessage());
            return false;
        }
    }
}

interface Prescribable {
    void prescribe(Patient p, Medication m);
}

public class Main {
    public static void main(String[] args) {
        // Create doctors
        Doctor drSmith = new Doctor("Dr. John Smith", 45, "Male", "smith@hospital.com", 101);
        Doctor drAlan = new Doctor("Dr. Alan", 40, "Male", "alan@hospital.com", 102);

        drSmith.addSpecialization("Cardiology");
        drSmith.addSpecialization("Heart Surgery");
        drSmith.addSpecialization("Pathology");

        drAlan.addSpecialization("neurosurgery");
        drAlan.addSpecialization("Pathology");

        // Create medications
        Medication aspirin = new Medication("Aspirin");
        aspirin.addAllergen("Salicylate");

        Medication paracetamol = new Medication("Paracetamol");

        Medication atorvastatin = new Medication("Atorvastatin");

        // Create patients
        Patient jane = new Patient("Jane Doe", 30, "Female", "jane@gmail.com", "P001");
        jane.addAllergy("Salicylate");

        Patient tom = new Patient("Tom Harris", 55, "Male", "tom@abc.com", "P002");

        // Prescribe medications
        System.out.println("=== PRESCRIBING MEDICATIONS ===");
        drSmith.prescribe(jane, aspirin);      // Should fail due to allergy
        drSmith.prescribe(jane, paracetamol);  // Should succeed
        drSmith.prescribe(tom, atorvastatin);  // Should succeed

        // Schedule a valid surgery
        Surgery heartSurgery = new Surgery("PR001", "Heart Surgery", "2025-07-01 10:00AM", drSmith, true);
        jane.addMedicalProcedure(heartSurgery);

        // Schedule an invalid surgery (doctor not certified for lung surgery)
        Surgery lungSurgery = new Surgery("PR002", "lung surgery", "2025-07-05 2:00PM", drAlan, true);
        tom.addMedicalProcedure(lungSurgery);

        // Schedule lab test
        LabTest bloodTest = new LabTest("PR003", "Blood Test", "2025-07-02 09:00AM", drSmith, "clinic");
        jane.addMedicalProcedure(bloodTest);

        // Validate procedures
        System.out.println("\n=== VALIDATING PROCEDURES ===");
        heartSurgery.validate();
        lungSurgery.validate();
        bloodTest.validate();

        // Show final doctor info with all specializations
        System.out.println("\n=== DOCTOR FINAL INFO ===");
        drSmith.showInfo();
        drAlan.showInfo();

        // Show patient info with all medications, procedures
        System.out.println("=== PATIENT FINAL INFO: JANE ===");
        jane.showInfo();

        System.out.println("=== PATIENT FINAL INFO: TOM ===");
        tom.showInfo();
    }
}