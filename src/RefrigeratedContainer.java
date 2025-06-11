public class RefrigeratedContainer extends Container {
    private String productType;
    private double temperature;
    private double requiredTemperature;

    public RefrigeratedContainer(double height, double tareWeight, double depth, double maxCapacity,
                                 String productType, double temperature, double requiredTemperature) {
        super(height, tareWeight, depth, maxCapacity);
        this.productType = productType;
        this.temperature = temperature;
        this.requiredTemperature = requiredTemperature;
    }

    @Override
    protected String getType() {
        return "C";
    }

    @Override
    public void loadCargo(double mass) throws OverfillException {
        if (!productType.equalsIgnoreCase(productType)) {
            throw new IllegalArgumentException("Container stores only same type of product.");
        }
        if (temperature < requiredTemperature) {
            throw new IllegalArgumentException("Container temperature is too low!");
        }
        if (mass > maxCapacity) {
            throw new OverfillException("Refrigerated container overfilled!");
        }
        this.cargoMass = mass;
    }

    @Override
    public void emptyCargo() {
        this.cargoMass = 0;
    }
}
