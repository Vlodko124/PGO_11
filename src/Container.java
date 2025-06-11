public abstract class Container {
    protected static int serialCounter = 1;

    protected String serialNumber;
    protected double cargoMass;
    protected double height;
    protected double tareWeight;
    protected double depth;
    protected double maxCapacity;

    public Container(double height, double tareWeight, double depth, double maxCapacity) {
        this.height = height;
        this.tareWeight = tareWeight;
        this.depth = depth;
        this.maxCapacity = maxCapacity;
        this.serialNumber = generateSerialNumber();
    }

    protected abstract String getType();

    private String generateSerialNumber() {
        return "KON-" + getType() + "-" + serialCounter++;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public abstract void loadCargo(double mass) throws OverfillException;

    public abstract void emptyCargo();

    public double getCargoMass() {
        return cargoMass;
    }

    public double getMaxCapacity() {
        return maxCapacity;
    }
}
