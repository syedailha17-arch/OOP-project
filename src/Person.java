public abstract class Person {
    
    private String id;
    private String name;

    // Constructor
    public Person(String id, String name) {
        setId(id);
        setName(name);
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Setters with validation bcz setters without validation is pointless.
    public final void setId(String id) {
        if (id == null || id.isBlank()) {
            System.out.println("Error: ID cannot be empty!");
            return;
        }
        this.id = id;
    }

    public final void setName(String name) {
        if (name == null || name.isBlank()) {
            System.out.println("Error: Name cannot be empty!");
            return;
        }
        this.name = name;
    }

    // Abstract method: every subclass MUST implement this
    public abstract void display();
}