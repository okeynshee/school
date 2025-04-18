class Act9 {
    public static void main (String[] args) {
        System.out.println("Activity 9");
        System.out.println(University.getUniversityName());

        CSDepartment csd = new CSDepartment();
        System.out.println(csd.getAccreditationLevel());
        System.out.println(csd.getDepartmentInfo());
    }
}

abstract class University {
    public static String getUniversityName() {
        return "University of the Kenshee";
    }

    final public String getAccreditationLevel() {
        return "Bachelors";
    }

    public abstract String getDepartmentInfo();
}

class CSDepartment extends University {
    @Override
    public String getDepartmentInfo() {
        return "CCS is the Best";
    }
}
