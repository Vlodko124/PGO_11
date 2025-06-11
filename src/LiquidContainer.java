public class LiquidContainer extends Container implements IHazardNotifier {
    private boolean isHazardous;

    public LiquidContainer(double height, double tareWeight, double depth, double maxCapacity, boolean isHazardous) {
        super(height, tareWeight, depth, maxCapacity);
        this.isHazardous = isHazardous;
    }

    @Override
    protected String getType() {
        return "L";
    }

    @Override
    public void loadCargo(double mass) throws OverfillException {
        double maxAllowed = isHazardous ? maxCapacity * 0.5 : maxCapacity * 0.9;

        if (mass > maxAllowed) {
            notifyDanger(serialNumber);
            throw new OverfillException("Cargo exceeds safe limit.");
        }
        this.cargoMass = mass;
    }

    @Override
    public void emptyCargo() {
        this.cargoMass = 0;
    }

    @Override
    public void notifyDanger(String containerNumber) {
        System.out.println("Dangerous operation attempted on container: " + containerNumber);
    }
}
