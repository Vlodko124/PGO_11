public class GasContainer extends Container implements IHazardNotifier {
    private double pressure;

    public GasContainer(double height, double tareWeight, double depth, double maxCapacity, double pressure) {
        super(height, tareWeight, depth, maxCapacity);
        this.pressure = pressure;
    }

    @Override
    protected String getType() {
        return "G";
    }

    @Override
    public void loadCargo(double mass) throws OverfillException {
        if (mass > maxCapacity) {
            notifyDanger(serialNumber);
            throw new OverfillException("Gas container overfilled!");
        }
        this.cargoMass = mass;
    }

    @Override
    public void emptyCargo() {
        this.cargoMass *= 0.05;
    }

    @Override
    public void notifyDanger(String containerNumber) {
        System.out.println("Gas container " + containerNumber + " is doing something sketchy!");
    }
}
