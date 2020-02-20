package ua.cruise.company.dto;

import java.util.List;

public class ShipDTO {
    private Long id;
    private String name;
    private int capacity;
    private String routeName;
    private int oneTripDurationDays;
    private List<SeaportDTO> visitingPorts;
    private List<ExtraDTO> extras;

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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public int getOneTripDurationDays() {
        return oneTripDurationDays;
    }

    public void setOneTripDurationDays(int oneTripDurationDays) {
        this.oneTripDurationDays = oneTripDurationDays;
    }

    public List<SeaportDTO> getVisitingPorts() {
        return visitingPorts;
    }

    public void setVisitingPorts(List<SeaportDTO> visitingPorts) {
        this.visitingPorts = visitingPorts;
    }

    public List<ExtraDTO> getExtras() {
        return extras;
    }

    public void setExtras(List<ExtraDTO> extras) {
        this.extras = extras;
    }

    @Override
    public String toString() {
        return "ShipDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", routeName='" + routeName + '\'' +
                ", oneTripDurationDays=" + oneTripDurationDays +
                ", visitingPorts=" + visitingPorts +
                ", extras=" + extras +
                '}';
    }
}
