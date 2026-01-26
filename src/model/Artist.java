package model;

// import model.Validatable;

public class Artist implements Validatable {
    private int id;
    private String name;
    private String country;

    public Artist(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCountry() { return country; }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Artist name cannot be empty.");
        this.name = name;
    }

    public void setCountry(String country) {
        if (country == null || country.trim().isEmpty())
            throw new IllegalArgumentException("Country cannot be empty.");
        this.country = country;
    }

    @Override
    public boolean isValid() {
        return name != null && !name.trim().isEmpty() && country != null && !country.trim().isEmpty();
    }

    @Override
    public String toString() {
        return id + ". " + name + " (" + country + ")";
    }
}
