import java.util.*;

public class ContainerShip {
    String name;
    private int maxContainerNum;
    private double maxWeight;
    private double maxSpeed;
    List<Container> containers;

    public ContainerShip(String name, double maxSpeed, int maxContainerNum, double maxWeight) {
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.maxContainerNum = maxContainerNum;
        this.maxWeight = maxWeight;
        this.containers = new ArrayList<>();
    }

    public boolean addContainer(Container c) {
        if (containers.size() >= maxContainerNum || getTotalWeight() + c.getCargoMass() > maxWeight * 1000) {
            System.out.println("Cannot add container to ship: " + name);
            return false;
        }
        containers.add(c);
        return true;
    }

    public void removeContainer(String serialNumber) {
        containers.removeIf(c -> c.getSerialNumber().equals(serialNumber));
    }

    public void printShipInfo() {
        System.out.println("Ship: " + name + ", Speed: " + maxSpeed + " knots");
        System.out.println("Carrying " + containers.size() + " containers:");
        for (Container c : containers) {
            System.out.println(" - " + c.getSerialNumber() + " with " + c.getCargoMass() + " kg cargo");
        }
    }

    private double getTotalWeight() {
        return containers.stream().mapToDouble(Container::getCargoMass).sum();
    }
}
