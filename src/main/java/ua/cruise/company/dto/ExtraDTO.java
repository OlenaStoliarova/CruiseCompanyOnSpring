package ua.cruise.company.dto;

public class ExtraDTO {
    private Long id;
    private String name;

    public ExtraDTO() {
    }

    public ExtraDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ExtraDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
