public class Main {
    public static void main(String[] args) {
        // Create doctor
        Doctor drSmith = new Doctor("Dr. John Smith", 45, "Male", "smith@hospital.com", 101);
        Doctor drAlan = new Doctor("Dr. Alan", 40, "Male", "alan@hospital.com", 102);

        drSmith.addSpecialization("Cardiology");
        drSmith.addSpecialization("heart surgery");
        drSmith.addSpecialization("Pathology");

        drAlan.addSpecialization("neurosurgery");
        drSmith.addSpecialization("Pathology");

        // Create medications
        Medication aspirin = new Medication("Aspirin");
        aspirin.addAllergen("Salicylate");

        Medication paracetamol = new Medication("Paracetamol");

        Medication atorvastatin = new Medication("Atorvastatin");

        // Create patient
        Patient jane = new Patient("Jane Doe", 30, "Female", "jane@gmail.com", "P001");
        jane.addAllergy("Salicylate");

        Patient tom = new Patient("Tom Harris", 55, "Male", "tom@abc.com", "P002");

        // Prescribe medications
        System.out.println("Prescribing Medications:");
        drSmith.prescribe(jane, aspirin);      // Should fail due to allergy
        drSmith.prescribe(jane, paracetamol);  // Should succeed
        drSmith.prescribe(tom, atorvastatin);  // Should succeed

        // Schedule a valid surgery
        Surgery heartSurgery = new Surgery("PR001", "Heart Surgery", "2025-07-01 10:00AM", drSmith, true);
        jane.addMedicalProcedure(heartSurgery);

        // Schedule an invalid surgery (e.g., doctor not certified for lung surgery)
        Surgery lungSurgery = new Surgery("PR002", "lung surgery", "2025-07-05 2:00PM", drAlan, true);
        tom.addMedicalProcedure(lungSurgery);

        // Schedule lab test
        LabTest bloodTest = new LabTest("PR003", "Blood Test", "2025-07-02 09:00AM", drSmith, "clinic");
        jane.addMedicalProcedure(bloodTest);

        // Validate procedures
        System.out.println("\nValidating Procedures:");
        heartSurgery.validate();
        lungSurgery.validate();
        bloodTest.validate();

        // Show final doctor info with all specializations
        System.out.println("\n=== DOCTOR FINAL INFO ===");
        drSmith.showInfo();

        // Show patient info with all medications, procedures
        System.out.println("\n=== PATIENT FINAL INFO: JANE ===");
        jane.showInfo();

        System.out.println("\n=== PATIENT FINAL INFO: TOM ===");
        tom.showInfo();
    }
}
