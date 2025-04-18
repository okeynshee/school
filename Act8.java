class Act8 {
    public static void main(String[] args) {
        System.out.println("Activity 8");
        Student kenshee = new Student("Kenshee", 20, "Cebu City");
        kenshee.displayName();
        kenshee.displayAge();
        kenshee.displayAddress();
    }
}

abstract class Person {
    public String name;
    private int age;
    
    public Person(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    protected String address;
}

class Student extends Person {
    
    public Student(String name, int age, String address) {
        super(name, age, address);
    }
    
    public void displayName() {
        System.out.println("Name: " + name);
    }
    
    public void displayAge() {
        System.out.println("Age: " + getAge());
    }
    
    public void displayAddress() {
        System.out.println("Address: " + address);
    }
}